import React, { Component } from 'react';
import { connect } from 'dva';
import { Row, Col } from 'antd';
import Editor from '../../components/Editor';
import ArticleList from '../../components/ArticleList';
// import styles from './index.less';

const data = [{
  name: 'test',
  tags: [
    'tag1',
    'tag2',
  ],
}, {
  name: 'hhhhhh',
  tags: [
    'ha',
    'he',
  ],
}];

@connect(({ article, categories, loading }) => ({
  article,
  categories,
  loading: loading.effects['article/fetch'],
}))
export default class TriggerException extends Component {
  render() {
    return (
      <div>
        <Row>
          <Col span={6}>
            <ArticleList
              dataSource={data}
            />
          </Col>
          <Col span={18}>
            <Editor />
          </Col>
        </Row>
      </div>
    );
  }
}
