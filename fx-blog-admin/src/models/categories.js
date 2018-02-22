import { queryAdminCategoriesSelect } from '../services/categories';

export default {
  namespace: 'categories',

  state: {
    adminCategoriesSelectInfo: [],
  },
  effects: {
    *fetchAdminCategoriesSelectInfo(_, { call, put }) {
      const response = yield call(queryAdminCategoriesSelect);
      if (response.success) {
        yield put({
          type: 'updateState',
          adminCategoriesSelectInfo: response.value,
        });
      }
    },
  },

  reducers: {
    updateState(state, payload) {
      return {
        ...state,
        ...payload,
      };
    },
  },
};
