import request from '@/utils/request'

// 查询题库试题列表
export function listQuizquestion(query) {
  return request({
    url: '/quizquestion/quizquestion/list',
    method: 'get',
    params: query
  })
}

// 查询题库试题详细
export function getQuizquestion(id) {
  return request({
    url: '/quizquestion/quizquestion/' + id,
    method: 'get'
  })
}

// 新增题库试题
export function addQuizquestion(data) {
  return request({
    url: '/quizquestion/quizquestion',
    method: 'post',
    data: data
  })
}

// 修改题库试题
export function updateQuizquestion(data) {
  return request({
    url: '/quizquestion/quizquestion',
    method: 'put',
    data: data
  })
}

// 删除题库试题
export function delQuizquestion(id) {
  return request({
    url: '/quizquestion/quizquestion/' + id,
    method: 'delete'
  })
}

