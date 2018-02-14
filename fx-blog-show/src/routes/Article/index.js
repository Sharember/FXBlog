import React, { Component }  from 'react';
import { connect } from 'dva';
import ArticleDetail from '../../components/ArticleDetail'

@connect(({ article, loading }) => ({
  article,
  loading: loading.effects['article/fetchCurrentArticle'],
}))
export default class Article extends Component {

  componentDidMount() {
    const { location } = this.props;
    this.props.dispatch({
      type: 'article/fetchCurrentArticle',
      payload: location.pathname.split('/')[3],
    });
  }

  render() {
    const { article } = this.props;
    const { currentArticle } = article;
    return (
      <div>
        {
          currentArticle.name ? 
            <ArticleDetail
              data={currentArticle}
            >
            </ArticleDetail>
          :
          null
        }
      </div>
      
    )
  }
}