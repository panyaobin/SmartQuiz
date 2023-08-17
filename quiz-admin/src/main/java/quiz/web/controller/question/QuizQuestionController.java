package quiz.web.controller.question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import quiz.common.annotation.Log;
import quiz.common.core.controller.BaseController;
import quiz.common.core.domain.AjaxResult;
import quiz.common.core.page.TableDataInfo;
import quiz.common.enums.BusinessType;
import quiz.common.utils.poi.ExcelUtil;
import quiz.question.domain.QuizQuestion;
import quiz.question.service.IQuizQuestionService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

/**
 * 题库试题Controller
 *
 * @author popo
 * @date 2023-08-14
 */
@RestController
@RequestMapping("/quizquestion/quizquestion")
public class QuizQuestionController extends BaseController {
    @Autowired
    private IQuizQuestionService quizQuestionService;

    /**
     * 查询题库试题列表
     */
    @PreAuthorize("@ss.hasPermi('quizquestion:quizquestion:list')")
    @GetMapping("/list")
    public TableDataInfo list(QuizQuestion quizQuestion) {
        startPage();
        List<QuizQuestion> list = quizQuestionService.selectQuizQuestionList(quizQuestion);
        return getDataTable(list);
    }

    /**
     * 导出题库试题列表
     */
    @PreAuthorize("@ss.hasPermi('quizquestion:quizquestion:export')")
    @Log(title = "题库试题", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response , QuizQuestion quizQuestion) {
        List<QuizQuestion> list = quizQuestionService.selectQuizQuestionList(quizQuestion);
        ExcelUtil<QuizQuestion> util = new ExcelUtil<QuizQuestion>(QuizQuestion.class);
        util.exportExcel(response , list , "题库试题数据");
    }

    /**
     * 获取题库试题详细信息
     */
    @PreAuthorize("@ss.hasPermi('quizquestion:quizquestion:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        QuizQuestion quizQuestion = quizQuestionService.selectQuizQuestionById(id);
        Optional.ofNullable(quizQuestion).orElseThrow(() -> new RuntimeException("试题信息不存在,请核对"));
        return success(quizQuestion);
    }

    /**
     * 新增题库试题
     */
    @PreAuthorize("@ss.hasPermi('quizquestion:quizquestion:add')")
    @Log(title = "题库试题", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody QuizQuestion quizQuestion) {
        quizQuestion.setCreateBy(getUsername());
        return toAjax(quizQuestionService.insertQuizQuestion(quizQuestion));
    }

    /**
     * 修改题库试题
     */
    @PreAuthorize("@ss.hasPermi('quizquestion:quizquestion:edit')")
    @Log(title = "题库试题", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody QuizQuestion quizQuestion) {
        quizQuestion.setUpdateBy(getUsername());
        return toAjax(quizQuestionService.updateQuizQuestion(quizQuestion));
    }

    /**
     * 删除题库试题
     */
    @PreAuthorize("@ss.hasPermi('quizquestion:quizquestion:remove')")
    @Log(title = "题库试题", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(quizQuestionService.removeQuizQuestionByIds(ids));
    }


    /**
     * 物理删除题库试题
     * @param ids 多个主键id
     * @return 接口提示信息
     */
    @Log(title = "题库试题删除", businessType = BusinessType.DELETE)
    @DeleteMapping("/delete/{ids}")
    public AjaxResult del(@PathVariable Long[] ids) {
        return toAjax(quizQuestionService.deleteQuizQuestionByIds(ids));
    }
}
