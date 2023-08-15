<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="省份" prop="subjectId">
        <el-select v-model="queryParams.provinceId" placeholder="请选择省份" clearable @change="handleQuery">
          <el-option
            v-for="province in provinces"
            :key="province.value"
            :label="province.label"
            :value="province.value"
          />
        </el-select>
      </el-form-item>

      <el-form-item label="专业" prop="subjectId">
        <el-select v-model="queryParams.majorId" placeholder="请选择专业" clearable @change="handleQuery">
          <el-option
            v-for="major in majors"
            :key="major.value"
            :label="major.label"
            :value="major.value"
          />
        </el-select>
      </el-form-item>

      <el-form-item label="学科" prop="subjectId">
        <el-select v-model="queryParams.subjectId" placeholder="请选择学科" clearable @change="handleQuery">
          <el-option
            v-for="subject in subjects"
            :key="subject.value"
            :label="subject.label"
            :value="subject.value"
          />
        </el-select>
      </el-form-item>

      <el-form-item label="试题类型" prop="subjectId">
        <el-select v-model="queryParams.entryType" placeholder="请选择试题类型" clearable @change="handleQuery">
          <el-option
            v-for="entryType in entryTypes"
            :key="entryType.value"
            :label="entryType.label"
            :value="entryType.value"
          />
        </el-select>
      </el-form-item>

      <el-form-item label="出题年份" prop="year">
        <el-input
          v-model="queryParams.year"
          placeholder="请输入出题年份"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['quizquestion:quizquestion:add']"
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['quizquestion:quizquestion:edit']"
        >修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['quizquestion:quizquestion:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['quizquestion:quizquestion:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="quizquestionList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="序号" align="center" width="100" type="index"/>
      <el-table-column label="题干" align="left" prop="stem"/>
      <el-table-column label="试题类别" align="center" width="180" prop="sourceType">
        <template slot-scope="scope">
          <span>{{ formatName(scope.row.sourceType, sourceTypes) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="试题类型" align="center" width="180" prop="entryType">
        <template slot-scope="scope">
          <span>{{ formatName(scope.row.entryType, entryTypes) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="出题年份" align="center" width="180" prop="year"/>
      <el-table-column label="难度" align="center" width="180" prop="difficulty">
        <template slot-scope="scope">
          <span>{{ formatName(scope.row.difficulty, difficulties) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" width="200" prop="createTime"/>
      <!--      <el-table-column label="省份" align="center" prop="provinceId" />-->
      <!--      <el-table-column label="专业" align="center" prop="majorId" />-->
      <!--      <el-table-column label="学科" align="center" prop="subjectId" />-->
      <!--      <el-table-column label="试题类别" align="center" prop="sourceType" />-->
      <!--      <el-table-column label="选项个数" align="center" prop="optionSize" />-->
      <!--      <el-table-column label="选项,选择题才会有" align="center" prop="optionContent" />-->
      <!--      <el-table-column label="答案" align="center" prop="answer" />-->
      <!--      <el-table-column label="解析" align="center" prop="analysis" />-->

      <!--      <el-table-column label="备注" align="center" prop="remark" />-->
      <el-table-column label="操作" align="center" width="100" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['quizquestion:quizquestion:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['quizquestion:quizquestion:remove']"
          >删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改题库试题对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="45%" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <div class="select-container">
          <el-form-item label="省份" prop="provinceId">
            <el-select v-model="form.provinceId" placeholder="请选择省份" clearable>
              <el-option
                v-for="province in provinces"
                :key="province.value"
                :label="province.label"
                :value="province.value"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="专业" prop="majorId">
            <el-select v-model="form.majorId" placeholder="请选择专业" clearable>
              <el-option
                v-for="major in majors"
                :key="major.value"
                :label="major.label"
                :value="major.value"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="学科" prop="subjectId">
            <el-select v-model="form.subjectId" placeholder="请选择学科" clearable>
              <el-option
                v-for="subject in subjects"
                :key="subject.value"
                :label="subject.label"
                :value="subject.value"
              />
            </el-select>
          </el-form-item>
        </div>

        <div class="select-container">
          <el-form-item label="试题类别" prop="sourceType">
            <el-select v-model="form.sourceType" placeholder="请选择试题类别" clearable>
              <el-option
                v-for="sourceType in sourceTypes"
                :key="sourceType.value"
                :label="sourceType.label"
                :value="sourceType.value"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="试题类型" prop="entryType">
            <el-select v-model="form.entryType" placeholder="请选择试题类型" clearable>
              <el-option
                v-for="entryType in entryTypes"
                :key="entryType.value"
                :label="entryType.label"
                :value="entryType.value"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="选项数量" prop="optionSize">
            <el-input v-model="form.optionSize" type="number"/>
          </el-form-item>
        </div>

        <el-form-item label="难度" prop="difficulty">
          <el-radio-group v-model="form.difficulty">
            <!-- 使用 v-for 循环渲染不同的难度选项 -->
            <el-radio v-for="item in difficulties" :key="item.value" :label="item.value">
              {{ item.label }}
            </el-radio>
          </el-radio-group>
        </el-form-item>

        <el-form-item label="题干" prop="stem">
          <el-input v-model="form.stem" type="textarea" placeholder="请输入内容"/>
        </el-form-item>
        <el-form-item label="选项">
          <editor v-model="form.optionContent" :min-height="60"/>
        </el-form-item>
        <el-form-item label="答案" prop="answer">
          <el-input v-model="form.answer" type="textarea" placeholder="请输入内容"/>
        </el-form-item>
        <el-form-item label="解析" prop="analysis">
          <el-input v-model="form.analysis" type="textarea" placeholder="请输入内容"/>
        </el-form-item>
        <el-form-item label="出题年份" prop="year">
          <el-input v-model="form.year" placeholder="请输入出题年份"/>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {listQuizquestion, getQuizquestion, delQuizquestion, addQuizquestion, updateQuizquestion} from "@/api/quizquestion/quizquestion";

export default {
  name: "Quizquestion",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 题库试题表格数据
      quizquestionList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 难度默认
      radio: 10,
      // 默认省份
      provinces: [
        {"label": "湖北省", "value": 42},
        {"label": "湖南省", "value": 43},
        {"label": "河南省", "value": 41}
      ],
      // 默认专业
      majors: [
        {"label": "文化课", "value": 10},
        {"label": "计算机", "value": 20},
        {"label": "人工智能", "value": 30}
      ],
      // 默认学科
      subjects: [
        {"label": "语文", "value": 50},
        {"label": "数学", "value": 51},
        {"label": "英语", "value": 52}
      ],
      // 默认试题类别
      sourceTypes: [
        {"label": "同步检测", "value": 10},
        {"label": "单元练习", "value": 20},
        {"label": "专业综合", "value": 30},
        {"label": "课前预习", "value": 40},
        {"label": "课后巩固", "value": 50},
        {"label": "课中训练", "value": 60},
      ],
      // 默认试题类型
      entryTypes: [
        {"label": "单选题", "value": 11},
        {"label": "多选题", "value": 12},
        {"label": "判断题", "value": 13},
        {"label": "填空题", "value": 20},
        {"label": "简答题", "value": 30},
        {"label": "组合题", "value": 40},
      ],
      // 默认试题类型
      difficulties: [
        {"label": "容易", "value": 10},
        {"label": "一般", "value": 20},
        {"label": "困难", "value": 30}
      ],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        provinceId: null,
        majorId: null,
        subjectId: null,
        sourceType: null,
        entryType: null,
        stem: null,
        difficulty: null,
        year: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        provinceId: [
          {required: true, message: "省份不能为空", trigger: "change"}
        ],
        majorId: [
          {required: true, message: "专业不能为空", trigger: "change"}
        ],
        subjectId: [
          {required: true, message: "学科不能为空", trigger: "change"}
        ],
        sourceType: [
          // 10.同步检测 20.单元练习 30.专业综合 40.课前预习 50.课后巩固 60.课中训练不能为空
          {required: true, message: "试题类别不能为空", trigger: "change"}
        ],
        entryType: [
          //11.单选 12.多选 13.判断 20.填空题 30.简答题 40.组合题不能为空
          {required: true, message: "试题类型不能为空", trigger: "change"}
        ],
        stem: [
          {required: true, message: "题干不能为空", trigger: "blur"}
        ],
        difficulty: [
          //:10.容易 20.一般 30.困难不能为空
          {required: true, message: "难度不能为空", trigger: "change"}
        ],
        year: [
          {required: true, message: "出题年份不能为空", trigger: "blur"}
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询题库试题列表 */
    getList() {
      this.loading = true;
      listQuizquestion(this.queryParams).then(response => {
        this.quizquestionList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        provinceId: null,
        majorId: null,
        subjectId: null,
        sourceType: null,
        entryType: null,
        stem: null,
        optionSize: null,
        optionContent: null,
        difficulty: 10,
        answer: null,
        analysis: null,
        year: null,
        parentId: null,
        delFlag: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 格式化名称
    formatName(value, mapping) {
      const item = mapping.find(item => item.value === value);
      return item ? item.label : "";
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加题库试题";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getQuizquestion(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改题库试题";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateQuizquestion(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addQuizquestion(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    }
    ,
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('试题删除后无法恢复, 确定删除吗？').then(function () {
        return delQuizquestion(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    }
    ,
    /** 导出按钮操作 */
    handleExport() {
      this.download('quizquestion/quizquestion/export', {
        ...this.queryParams
      }, `quizquestion_${new Date().getTime()}.xlsx`)
    }
  }
}
;
</script>

<style scoped>
.select-container {
  display: flex;
}
</style>
