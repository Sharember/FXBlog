import request from '../utils/request';

const base = 'http://localhost:5210';

export async function queryAdminCategoriesSelect() {
  return request(`${base}/categories/select`);
}
