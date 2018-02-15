import { 
  getCategoriesInfo
} from '../services/categories';

export default {
  namespace: 'categories',

  state: {
    categoriesInfo: [],
  },

  effects: {
    //payload: String => categoriesName
    *fetch({ payload }, { call, put }) {
      const response = yield call(getCategoriesInfo, payload);
      if (response.data.success) {
        yield put({
          type: 'getCategoriesInfo',
          payload: response.data.value,
        });
      }
    },
  },
  reducers: {
    getCategoriesInfo(state, { payload }) {
      
      return {
        ...state,
        categoriesInfo: payload,
      };
    },
  }
}