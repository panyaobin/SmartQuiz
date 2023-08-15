package quiz.question.service;


import quiz.question.domain.QuizQuestion;

import java.util.List;

/**
 * 题库试题Service接口
 * 
 * @author popo
 * @date 2023-08-14
 */
public interface IQuizQuestionService 
{
    /**
     * 查询题库试题
     * 
     * @param id 题库试题主键
     * @return 题库试题
     */
    public QuizQuestion selectQuizQuestionById(Long id);

    /**
     * 查询题库试题列表
     * 
     * @param quizQuestion 题库试题
     * @return 题库试题集合
     */
    public List<QuizQuestion> selectQuizQuestionList(QuizQuestion quizQuestion);

    /**
     * 新增题库试题
     * 
     * @param quizQuestion 题库试题
     * @return 结果
     */
    public int insertQuizQuestion(QuizQuestion quizQuestion);

    /**
     * 修改题库试题
     * 
     * @param quizQuestion 题库试题
     * @return 结果
     */
    public int updateQuizQuestion(QuizQuestion quizQuestion);

    /**
     * 物理批量删除题库试题
     * 
     * @param ids 需要删除的题库试题主键集合
     * @return 结果
     */
    public int deleteQuizQuestionByIds(Long[] ids);

    /**
     * 逻辑批量删除题库试题
     *
     * @param ids 需要删除的题库试题主键集合
     * @return 结果
     */
    public int removeQuizQuestionByIds(Long[] ids);

    /**
     * 删除题库试题信息
     * 
     * @param id 题库试题主键
     * @return 结果
     */
    public int deleteQuizQuestionById(Long id);
}
