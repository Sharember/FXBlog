import { getArticleDigests } from '../services/articles';

export default {
  namespace: 'article',

  state: {
    articleDigists: [],
  },

  effects: {
    //payload: pageNum
    *fetch({ payload }, { call, put }) {
      const response = yield call(getArticleDigests, payload);
      if (response.data.success) {
        yield put({
          type: 'getArticleDigests',
          payload: response.data.value,
        });
      }
    },
  },

  reducers: {
    getArticleDigests(state, { payload }) {
      return {
        ...state,
        articleDigists: payload,
      };
    },
  }
}