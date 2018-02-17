import { isUrl } from '../utils/utils';

function formatter(data,  parentAuthority) {
  return data.map((item) => {
    let { path } = item;
    if (!isUrl(path)) {
      path = item.path;
    }
    const result = {
      ...item,
      path,
      authority: item.authority || parentAuthority,
    };
    if (item.children) {
      result.children = formatter(item.children, item.authority);
    }
    return result;
  });
}

export const getMenuData = (data) => formatter(data);
