import React, { Component }  from 'react';
import { connect } from 'dva';

import ArticleDigest from '../../components/ArticleDigest'

@connect(({ article, loading }) => ({
  article,
  loading: loading.effects['article/fetch'],
}))
export default class ArticleList extends Component {

  componentDidMount() {
    this.props.dispatch({
      type: 'article/fetch',
      payload: 0,
    });
  }

  render() {
    const { article, loading } = this.props;
    const { articleDigists } = article;
    return (
      <ArticleDigest
        loading={loading}
        dataSource={articleDigists}
      >

      </ArticleDigest>
    )
  }
}