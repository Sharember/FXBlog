import request from '../utils/request';

export function getArticleDigests(page) {
  return request('/article/digests/page/' + page);
}

export function getArticleByName(name) {
  return request('/article/name/' + name);
}

export function getArticleByCategories(payload) {
  return request(`/article/categories//${payload.categories}/page/${payload.page}`);
}

export function getArticleByTag(payload) {
  return request(`/article/tag/${payload.tag}/page/${payload.page}`);
}

export async function like(name) {
  return request(`/article/name/${name}/like`, {
    method: 'POST',
  });
}

// export async function visit(name) {
//   return request(`/article/name/${name}/visit`, {
//     method: 'POST',
//   });
// }

export async function getTotalArticle() {
  return request(`/article/total/num`);
}

export async function getCardInfo() {
  return request(`/article/card`);
}
