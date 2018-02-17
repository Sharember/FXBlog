import request from '../utils/request';


export function getMenuInfo() {
   return request('/menu');
}