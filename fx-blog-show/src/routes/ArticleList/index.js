import React, { Component }  from 'react';
import { connect } from 'dva';

import ArticleDigest from '../../components/ArticleDigest'
const listData = [];
for (let i = 0; i < 5; i++) {
  listData.push({
    href: 'http://ant.design',
    title: `ant design part ${i}`,
    avatar: 'https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png',
    description: 'Ant Design, a design language for background applications, is refined by Ant UED Team.',
    content: 'We supply a series of design principles, practical patterns and high quality design resources (Sketch and Axure), to help people create their product prototypes beautifully and efficiently.',
  });
}
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
    const { article } = this.props;
    const { articleDigists } = article;
    return (
      <ArticleDigest
        dataSource={articleDigists}
      >

      </ArticleDigest>
    )
  }
}