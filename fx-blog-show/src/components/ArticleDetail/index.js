import React, { PureComponent } from 'react';
import ArticleHeader from './ArticleHeader';
import ArticleContent from './ArticleContent';
import ArticleFooter from './ArticleFooter';
import styles from './index.less'

export default class ArticleDetail extends PureComponent {
  render() {
    const { data } = this.props;
    return (
      <div style={{ width: "100%" }}>
        <ArticleHeader 
          name={data.name}
          createTime={data.createTime}
          categories={data.categories}
          tags={data.tags}
          visitNum={data.visitNum}
        />
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