import React, { PureComponent } from 'react';
import { Tag } from 'antd';

import styles from './index.less';

export default class ArticleList extends PureComponent {

  handelClick = (name, index) => {
    console.log(name + index);
  }

  render() {
    const { dataSource } = this.props;
    return (
      <div className={styles.list}>
        {
          dataSource.map((item, index) => {
            return (
              <div
                key={`${item.name}`}
                className={styles.item}
                onClick={() => this.handelClick(item.name, index)}
              >
                <h3>{item.name}</h3>
                <span>{item.tags.map(tag => <Tag key={`${item.name}${tag}`} color="green">{tag}</Tag>)}</span>

              </div>
            );
          })
        }
      </div>
    );
  }
}
