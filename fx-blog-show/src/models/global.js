import { 
  getTotalArticle
} from '../services/articles';

export default {
  namespace: 'global',

  state: {
    total: 0,
  },

  effects: {
    //payload: pageNum
    *fetchTotalArticle(_, { call, put }) {
      const response = yield call(getTotalArticle);
      if (response.data.success) {
        yield put({
          type: 'updateState',
          total: response.data.value,
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
  }
}