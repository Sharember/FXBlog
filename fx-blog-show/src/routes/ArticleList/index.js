import React, { Component }  from 'react';
import { connect } from 'dva';

import ArticleDigest from '../../components/ArticleDigest'

@connect(({ article, global, loading }) => ({
  article,
  global,
  loading: loading.effects['article/fetch'],
}))
export default class ArticleList extends Component {

  state = {
    current: 1,
  }

  componentDidMount() {
    this.props.dispatch({
      type: 'article/fetch',
      payload: 0,
    });
  }

  getNextPage = (page) => {
    this.props.dispatch({
      type: 'article/fetch',
      payload: page - 1,
    });
    this.setState({
      current: page,
    })
  }

  render() {
    const { article, global, loading } = this.props;
    const { articleDigists } = article;
    const { total } = global;
    return (
      <ArticleDigest
        loading={loading}
        current={this.state.current}
        total={total}
        getNextPageInfo={this.getNextPage}
        dataSource={articleDigists}
      >

      </ArticleDigest>
    )
  }
}