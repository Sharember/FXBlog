import React, { Component }  from 'react';
import { connect } from 'dva';
import ArticleDigest from '../../components/ArticleDigest'

@connect(({ article, loading }) => ({
  article,
  loading: loading.effects['article/fechArticleByTag'],
}))
export default class TagArticles extends Component {

  state = {
    current: 1,
  }

  componentDidMount() {
    const { location } = this.props;
    this.props.dispatch({
      type: 'article/fechArticleByTag',
      payload: {
          tag: location.pathname.split('/')[3],
          page: 0,
        }
    });
  }

  getNextPage = (page) => {
    const { location } = this.props;
    this.props.dispatch({
      type: 'article/fechArticleByTag',
      payload: {
          tag: location.pathname.split('/')[3],
          page: page - 1,
        }
    });
    this.setState({
      current: page,
    })
  }

  render() {
    const { article, loading } = this.props;
    const { articleAndTotal } = article;
    return (
      <ArticleDigest
        total={articleAndTotal.total}
        current={this.state.current}
        getNextPageInfo={this.getNextPage}
        loading={loading}
        dataSource={articleAndTotal.articles}
      >

      </ArticleDigest>
    )
  }
}
