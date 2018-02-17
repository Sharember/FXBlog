import { 
  getTotalArticle,
  getCardInfo
} from '../services/articles';

export default {
  namespace: 'global',

  state: {
    total: 0,
    cardInfo: [],
  },

  effects: {
    *fetchTotalArticle(_, { call, put }) {
      const response = yield call(getTotalArticle);
      if (response.data.success) {
        yield put({
          type: 'updateState',
          total: response.data.value,
        });
      }
    },
    *fetchCardInfo(_, { call, put }) {
      const response = yield call(getCardInfo);
      if (response.data.success) {
        yield put({
          type: 'updateState',
          cardInfo: response.data.value.cards,
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