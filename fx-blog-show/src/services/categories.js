import request from '../utils/request';

export function getCategoriesInfo(name) {
  return request('/article/categories/' + name);
}