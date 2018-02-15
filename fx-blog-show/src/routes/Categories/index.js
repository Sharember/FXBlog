import React, { Component }  from 'react';
import { connect } from 'dva';
import ArticleDigest from '../../components/ArticleDigest'

@connect(({ categories, loading }) => ({
  categories,
  loading: loading.effects['categories/fetch'],
}))
export default class CategoriesInfo extends Component {
  
  componentDidMount() {
    const { location } = this.props;
    this.props.dispatch({
      type: 'categories/fetch',
      payload: location.pathname.split('/')[3],
    });
  }

  render() {
    const { categories, loading } = this.props;
    const { categoriesInfo } = categories;
    //console.log(categoriesInfo)
    return (
      <ArticleDigest
        loading={loading}
        dataSource={categoriesInfo}
      >

      </ArticleDigest>
    )
  }
}
