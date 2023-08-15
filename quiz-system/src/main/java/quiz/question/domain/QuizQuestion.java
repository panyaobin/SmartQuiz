package quiz.question.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import quiz.common.annotation.Excel;
import quiz.common.core.domain.BaseEntity;

/**
 * 题库试题对象 quiz_question
 *
 * @author popo
 * @date 2023-08-14
 */
public class QuizQuestion extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @Excel(name = "")

    private Long id;

    /**
     * 省份id
     */
    @Excel(name = "省份id")
    private Long provinceId;

    /**
     * 专业id
     */
    @Excel(name = "专业id")
    private Long majorId;

    /**
     * 学科id
     */
    @Excel(name = "学科id")
    private Long subjectId;

    /**
     * 试题类别 10.同步检测 20.单元练习 30.专业综合 40.课前预习 50.课后巩固 60.课中训练
     */
    @Excel(name = "试题类别 10.同步检测 20.单元练习 30.专业综合 40.课前预习 50.课后巩固 60.课中训练")
    private Integer sourceType;

    /**
     * 试题类型:11.单选 12.多选 13.判断 20.填空题 30.简答题 40.组合题
     */
    @Excel(name = "试题类型:11.单选 12.多选 13.判断 20.填空题 30.简答题 40.组合题")
    private Integer entryType;

    /**
     * 题干
     */
    @Excel(name = "题干")
    private String stem;

    /**
     * 选项个数
     */
    @Excel(name = "选项个数")
    private Integer optionSize;

    /**
     * 选项,选择题才会有
     */
    @Excel(name = "选项,选择题才会有")
    private String optionContent;

    /**
     * 难度:10.容易 20.一般 30.困难
     */
    @Excel(name = "难度:10.容易 20.一般 30.困难")
    private Integer difficulty;

    /**
     * 答案
     */
    @Excel(name = "答案")
    private String answer;

    /**
     * 解析
     */
    @Excel(name = "解析")
    private String analysis;

    /**
     * 出题年份
     */
    @Excel(name = "出题年份")
    private Integer year;

    /**
     * 父题id,组合题才有
     */
    private Long parentId;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    private String delFlag;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setMajorId(Long majorId) {
        this.majorId = majorId;
    }

    public Long getMajorId() {
        return majorId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }

    public Integer getSourceType() {
        return sourceType;
    }

    public void setEntryType(Integer entryType) {
        this.entryType = entryType;
    }

    public Integer getEntryType() {
        return entryType;
    }

    public void setStem(String stem) {
        this.stem = stem;
    }

    public String getStem() {
        return stem;
    }

    public void setOptionSize(Integer optionSize) {
        this.optionSize = optionSize;
    }

    public Integer getOptionSize() {
        return optionSize;
    }

    public void setOptionContent(String optionContent) {
        this.optionContent = optionContent;
    }

    public String getOptionContent() {
        return optionContent;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getYear() {
        return year;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getDelFlag() {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this , ToStringStyle.MULTI_LINE_STYLE)
                .append("id" , getId())
                .append("provinceId" , getProvinceId())
                .append("majorId" , getMajorId())
                .append("subjectId" , getSubjectId())
                .append("sourceType" , getSourceType())
                .append("entryType" , getEntryType())
                .append("stem" , getStem())
                .append("optionSize" , getOptionSize())
                .append("optionContent" , getOptionContent())
                .append("difficulty" , getDifficulty())
                .append("answer" , getAnswer())
                .append("analysis" , getAnalysis())
                .append("year" , getYear())
                .append("parentId" , getParentId())
                .append("delFlag" , getDelFlag())
                .append("createBy" , getCreateBy())
                .append("createTime" , getCreateTime())
                .append("updateBy" , getUpdateBy())
                .append("updateTime" , getUpdateTime())
                .append("remark" , getRemark())
                .toString();
    }
}
