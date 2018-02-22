import request from '../utils/request';

const base = 'http://localhost:5210';

export async function queryArticleListByCategories(names) {
  return request(`${base}/article/admin/categories/${names}`);
}
