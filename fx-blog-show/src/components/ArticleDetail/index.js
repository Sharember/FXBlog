import React, { PureComponent } from 'react';
import { Spin } from 'antd';
import ArticleHeader from './ArticleHeader';
import ArticleContent from './ArticleContent';
import ArticleFooter from './ArticleFooter';
import styles from './index.less'

export default class ArticleDetail extends PureComponent {
  render() {
    const { data, loading } = this.props;
    return (
      <div style={{ width: "100%" }}>
        <div style={{ width: "100%" }} className={styles.center} >
          <Spin 
            spinning={loading}
          >
          </Spin>
          <ArticleHeader
            className={styles.center} 
            name={data.name}
            createTime={data.createTime}
            categories={data.categories}
            tags={data.tags}
            visitNum={data.visitNum}
          />
        </div>
        
        <hr />
        <ArticleContent 
          content={data.content}
        />
        <hr />
        <ArticleFooter 
          lastArticle={data.lastArticle}
          nextArticle={data.nextArticle}
        /> 
      </div>
    )
  }
}