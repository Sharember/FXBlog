import React, { Component } from 'react';
import { connect } from 'dva';
import { Row, Col } from 'antd';
import _ from 'lodash';
import Editor from '../../components/Editor';
import ArticleList from '../../components/ArticleList';


@connect(({ article, categories, loading }) => ({
  article,
  categories,
  loading: loading.effects['article/fetchArticleListByCategories'],
}))
export default class TriggerException extends Component {
  componentDidMount() {
    this.props.dispatch({
      type: 'categories/fetchAdminCategoriesSelectInfo',
    });
  }

  onDelete = (key) => {
    const { article: { articleListForNew } } = this.props;
    const dataSource = [...articleListForNew];
    this.updateStateEff(dataSource.filter(item => item.key !== key));
  }
  onCellChange = (key, dataIndex) => {
    return (value) => {
      const { article: { articleListForNew } } = this.props;
      const dataSource = [...articleListForNew];
      const target = dataSource.find(item => item.key === key);
      if (target) {
        target[dataIndex] = value;
        this.updateStateEff(dataSource);
      }
    };
  }

  onSelectCategories = (value) => {
    this.props.dispatch({
      type: 'article/fetchArticleListByCategories',
      payload: value,
    });
  }

  handleAdd = () => {
    const { article: { articleListForNew } } = this.props;
    const dataSource = [...articleListForNew];
    const newData = {
      key: 100,
      name: 'Edward King',
      tags: ['32'],
    };
    this.updateStateEff(_.concat(dataSource, newData));
  }
  removeTag = (key) => {
    return (tags) => {
      const { article: { articleListForNew } } = this.props;
      const dataSource = [...articleListForNew];
      const target = dataSource.find(item => item.key === key);
      if (target) {
        target.tags = tags;
        this.updateStateEff(dataSource);
      }
    };
  }

  updateStateEff = (value) => {
    this.props.dispatch({
      type: 'article/updateStateEff',
      payload: {
        articleListForNew: value,
      },
    });
  }

  addTag = (key) => {
    return (tags) => {
      const { article: { articleListForNew } } = this.props;
      const dataSource = [...articleListForNew];
      const target = dataSource.find(item => item.key === key);
      if (target) {
        target.tags = tags;
        this.props.dispatch({
          type: 'article/updateStateEff',
          payload: {
            articleListForNew: dataSource,
          },
        });
      }
    };
  }

  render() {
    const { categories, article } = this.props;
    const { adminCategoriesSelectInfo } = categories;
    const { articleListForNew } = article;
    return (
      <div>
        <Row>
          <Col span={6}>
            <ArticleList
              dataSource={articleListForNew}
              onDelete={key => this.onDelete(key)}
              onCellChange={(key, dataIndex) => this.onCellChange(key, dataIndex)}
              handleAdd={this.handleAdd}
              removeTag={(key, tags) => this.removeTag(key, tags)}
              addTag={(key, tags) => this.addTag(key, tags)}
              onSelectCategories={(value => this.onSelectCategories(value))}
              categories={adminCategoriesSelectInfo}
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
