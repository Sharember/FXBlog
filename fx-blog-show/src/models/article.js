import { 
  getArticleDigests,
  getArticleByName
} from '../services/articles';

export default {
  namespace: 'article',

  state: {
    articleDigists: [],
    currentArticle: {},
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
    //payload: articleName
    *fetchCurrentArticle({ payload }, { call, put }) {
      const response = yield call(getArticleByName, payload);
      if (response.data.success) {
        yield put({
          type: 'getArticle',
          payload: response.data.value,
        });
      }
    }
  },

  reducers: {
    getArticleDigests(state, { payload }) {
      return {
        ...state,
        articleDigists: payload,
      };
    },
    getArticle(state, { payload }) {
      return {
        ...state,
        currentArticle: payload,
      };
    }
  }
}