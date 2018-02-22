import { queryArticleListByCategories } from '../services/article';

export default {
  namespace: 'article',

  state: {
    articleListForNew: [],
  },
  effects: {
    *fetchArticleListByCategories({ payload }, { call, put }) {
      const response = yield call(queryArticleListByCategories, payload);
      console.log(response);
      if (response.success) {
        yield put({
          type: 'updateState',
          articleListForNew: response.value,
        });
      }
    },
    *updateStateEff({ payload }, { put }) {
      yield put({
        type: 'updateState',
        ...payload,
      });
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
