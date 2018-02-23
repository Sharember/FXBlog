import { queryArticleListByCategories, updateTags } from '../services/article';

export default {
  namespace: 'article',

  state: {
    articleListForNew: [],
  },
  effects: {
    *fetchArticleListByCategories({ payload }, { call, put }) {
      const response = yield call(queryArticleListByCategories, payload);
      if (response.success) {
        yield put({
          type: 'updateState',
          articleListForNew: response.value,
        });
      }
    },
    *updateTags({ payload }, { call }) {
      yield call(updateTags, payload);
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
