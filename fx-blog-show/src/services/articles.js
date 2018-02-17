import request from '../utils/request';

export function getArticleDigests(page) {
  return request('/article/digests/page/' + page);
}

export function getArticleByName(name) {
  return request('/article/name/' + name);
}

export function getArticleByCategories(name) {
  return request('/article/categories/' + name);
}

export function getArticleByTag(name) {
  return request('/article/tag/' + name);
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
