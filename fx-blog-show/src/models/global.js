import { 
  getTotalArticle,
  getCardInfo
} from '../services/articles';

import { 
  getMenuInfo
} from '../services/menu';

import { getMenuData } from '../common/menu';

export default {
  namespace: 'global',

  state: {
    total: 0,
    cardInfo: [],
    menuData: [],
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
    *fetchMenuData(_, { call, put }) {
      const response = yield call(getMenuInfo);
      if (response.data.success) {
        yield put({
          type: 'updateState',
          menuData: getMenuData(response.data.value),
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