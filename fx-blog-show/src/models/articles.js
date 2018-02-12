import { getArticleDigests } from '../services/articles';

export default {
  namespace: 'global',

  state: {
    articleDigists: [],
  },

  effects: {
    *fetch(_, { call, put }) {
      const response = yield call(getArticleDigests);
      yield put({
        type: 'save',
        payload: response,
      });
    },
  }
}