import React, { PureComponent } from 'react';
import { Spin, Divider } from 'antd';
import ArticleHeader from './ArticleHeader';
import ArticleContent from './ArticleContent';
import ArticleFooter from './ArticleFooter';
import styles from './index.less'

export default class ArticleDetail extends PureComponent {
  render() {
    const { data, loading, onLike } = this.props;
    return (
      <div style={{ width: "100%", overflowY: "hidden" }}>
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
        
        <Divider />
        <ArticleContent 
          content={data.content}
        />
        <Divider> 看完了，记得点个赞哟 </Divider>
        <ArticleFooter
          onLike={onLike}
          className={styles.center} 
          likeNum={data.likeNum}
          lastArticle={data.lastArticle}
          nextArticle={data.nextArticle}
        /> 
      </div>
    )
  }
}