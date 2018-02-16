import { 
  getArticleDigests,
  getArticleByName,
  getArticleByCategories,
  getArticleByTag
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
    },
    *fechArticleByCategories({ payload }, { call, put }) {
      const response = yield call(getArticleByCategories, payload);
      if (response.data.success) {
        yield put({
          type: 'getArticleDigests',
          payload: response.data.value,
        });
      }
    },
    *fechArticleByTag({ payload }, { call, put }) {
      const response = yield call(getArticleByTag, payload);
      if (response.data.success) {
        yield put({
          type: 'getArticleDigests',
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