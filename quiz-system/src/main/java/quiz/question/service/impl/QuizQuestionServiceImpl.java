package quiz.question.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quiz.common.utils.DateUtils;
import quiz.question.domain.QuizQuestion;
import quiz.question.mapper.QuizQuestionMapper;
import quiz.question.service.IQuizQuestionService;

import java.util.List;

/**
 * 题库试题Service业务层处理
 * 
 * @author popo
 * @date 2023-08-14
 */
@Service
public class QuizQuestionServiceImpl implements IQuizQuestionService
{
    @Autowired
    private QuizQuestionMapper quizQuestionMapper;

    /**
     * 查询题库试题
     * 
     * @param id 题库试题主键
     * @return 题库试题
     */
    @Override
    public QuizQuestion selectQuizQuestionById(Long id)
    {
        return quizQuestionMapper.selectQuizQuestionById(id);
    }

    /**
     * 查询题库试题列表
     * 
     * @param quizQuestion 题库试题
     * @return 题库试题
     */
    @Override
    public List<QuizQuestion> selectQuizQuestionList(QuizQuestion quizQuestion)
    {
        return quizQuestionMapper.selectQuizQuestionList(quizQuestion);
    }

    /**
     * 新增题库试题
     * 
     * @param quizQuestion 题库试题
     * @return 结果
     */
    @Override
    public int insertQuizQuestion(QuizQuestion quizQuestion)
    {
        quizQuestion.setCreateTime(DateUtils.getNowDate());
        return quizQuestionMapper.insertQuizQuestion(quizQuestion);
    }

    /**
     * 修改题库试题
     * 
     * @param quizQuestion 题库试题
     * @return 结果
     */
    @Override
    public int updateQuizQuestion(QuizQuestion quizQuestion)
    {
        quizQuestion.setUpdateTime(DateUtils.getNowDate());
        return quizQuestionMapper.updateQuizQuestion(quizQuestion);
    }

    /**
     * 批量删除题库试题
     * 
     * @param ids 需要删除的题库试题主键
     * @return 结果
     */
    @Override
    public int deleteQuizQuestionByIds(Long[] ids)
    {
        return quizQuestionMapper.deleteQuizQuestionByIds(ids);
    }

    @Override
    public int removeQuizQuestionByIds(Long[] ids) {
        return quizQuestionMapper.removeQuizQuestionByIds(ids);
    }

    /**
     * 删除题库试题信息
     * 
     * @param id 题库试题主键
     * @return 结果
     */
    @Override
    public int deleteQuizQuestionById(Long id)
    {
        return quizQuestionMapper.deleteQuizQuestionById(id);
    }
}
