import React, { PureComponent } from 'react';
import { Icon } from 'antd';
import { Link } from 'dva/router';
import styles from './index.less'

export default class ArticleHeader extends PureComponent {
  
  render() {
    const { name, createTime, categories, tags, discusses, visitNum } = this.props;
    const date = new Date();
    date.setTime(createTime);
    return (
      <div className={styles.header}>
        <h1 className={styles.auto} >{name}</h1>
        <span className={styles.auto} >
          <span className={styles.spanType} ><Icon type="clock-circle-o" /> {date.toLocaleDateString()} </span>
          <span className={styles.spanType} ><Icon type="folder" /> {categories.length > 1 ? categories.map(item => <Link to={`/categories/${item.name}`} key={item} />) : "无"}</span>
          <span className={styles.spanType} ><Icon type="tags-o" /> {tags.length > 1 ? tags.map(item => <Link to={`/tag/${item.name}`} key={item} />) : "无"}</span>
          {/* <span className={styles.spanType} ><Icon type="message" /> {discusses ? discusses.length : "无"}</span> */}
          <span className={styles.spanType} ><Icon type="eye-o" /> {visitNum}</span>
        </span>
      </div>
    )
  }
}