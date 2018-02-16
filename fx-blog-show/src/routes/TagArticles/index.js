import React, { Component }  from 'react';
import { connect } from 'dva';
import ArticleDigest from '../../components/ArticleDigest'

@connect(({ article, loading }) => ({
  article,
  loading: loading.effects['article/fechArticleByTag'],
}))
export default class TagArticles extends Component {
  
  componentDidMount() {
    const { location } = this.props;
    this.props.dispatch({
      type: 'article/fechArticleByTag',
      payload: location.pathname.split('/')[3],
    });
  }

  render() {
    const { article, loading } = this.props;
    const { articleDigists } = article;
    //console.log(categoriesInfo)
    return (
      <ArticleDigest
        loading={loading}
        dataSource={articleDigists}
      >

      </ArticleDigest>
    )
  }
}
