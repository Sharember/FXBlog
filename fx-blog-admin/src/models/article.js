import { queryArticleListByCategories, updateTags, queryArticleContent, saveContent } from '../services/article';

export default {
  namespace: 'article',

  state: {
    articleListForNew: [],
    content: '',
    currentId: -1,
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
    *fetchArticleContent({ payload }, { call, put }) {
      const response = yield call(queryArticleContent, payload);
      if (response.success) {
        yield put({
          type: 'updateState',
          content: response.value.content,
          currentId: response.value.id,
        });
      }
    },
    *updateTags({ payload }, { call }) {
      yield call(updateTags, payload);
    },
    *saveContent({ payload }, { call }) {
      yield call(saveContent, payload);
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
