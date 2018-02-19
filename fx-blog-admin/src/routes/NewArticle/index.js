import React, { Component } from 'react';
import { connect } from 'dva';
import { Row, Col } from 'antd';
import CategoriesTree from '../../components/CategoriesTree';
import Editor from '../../components/Editor';
import ArticleList from '../../components/ArticleList';
import styles from './index.less';

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
          <Col span={4}>
            <CategoriesTree
              className={styles.categories}
            />
          </Col>
          <Col span={4}>
            <ArticleList />
          </Col>
          <Col span={16}>
            <Editor />
          </Col>
        </Row>
      </div>
    );
  }
}
