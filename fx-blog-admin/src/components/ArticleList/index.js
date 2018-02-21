import React, { PureComponent } from 'react';
import { Menu } from 'antd';

import EditableTagGroup from '../EditableTagGroup';

import styles from './index.less';

export default class ArticleList extends PureComponent {
  handelClick = (name, index) => {
    console.log(name + index);
  }

  render() {
    const { dataSource } = this.props;
    return (
      <Menu className={styles.list}>
        {
          dataSource.map((item, index) => {
            return (
              <Menu.Item
                key={`${item.name}`}
                className={styles.item}
                onClick={() => this.handelClick(item.name, index)}
              >
                <div>
                  <h3>{item.name}</h3>
                  <EditableTagGroup
                    dataSource={item.tags}
                  />
                </div>
              </Menu.Item>
            );
          })
        }
      </Menu>
    );
  }
}
