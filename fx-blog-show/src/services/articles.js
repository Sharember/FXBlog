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
