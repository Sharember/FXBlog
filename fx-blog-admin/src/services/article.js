import request from '../utils/request';

const base = 'http://localhost:5210';

export async function queryArticleListByCategories(names) {
  return request(`${base}/article/admin/categories/${names}`);
}

export async function updateTags(payload) {
  return request(`${base}/article/tags`, {
    method: 'PUT',
    body: { ...payload },
  });
}
export async function queryArticleContent(name) {
  return request(`${base}/article/name/${name}`);
}

export async function saveContent(payload) {
  return request(`${base}/article/content`, {
    method: 'PUT',
    body: { ...payload },
  });
}

