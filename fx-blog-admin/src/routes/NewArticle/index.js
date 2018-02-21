import React, { Component } from 'react';
import { connect } from 'dva';
import { Row, Col } from 'antd';
import Editor from '../../components/Editor';
import ArticleList from '../../components/ArticleList';


@connect(({ article, categories, loading }) => ({
  article,
  categories,
  loading: loading.effects['article/fetch'],
}))
export default class TriggerException extends Component {
  state = {
    dataSource: [{
      name: 'test',
      key: 1,
      tags: [
        'tag1',
        'tag2',
      ],
    }, {
      name: 'hhhhhh',
      key: 2,
      tags: [
        'ha',
        'he',
      ],
    }],
    categoriesdata: [{
      value: 'zhejiang',
      label: 'java',
      children: [{
        value: 'hangzhou',
        label: 'spring',
        children: [{
          value: 'xihu',
          label: 'jpa',
        }],
      }],
    }, {
      value: 'jiangsu',
      label: 'spring',
      children: [{
        value: 'nanjing',
        label: 'Nanjing',
        children: [{
          value: 'zhonghuamen',
          label: 'jpa',
        }],
      }],
    }],
    count: 3,
  }

  onDelete = (key) => {
    console.log(key);
    const dataSource = [...this.state.dataSource];
    this.setState({ dataSource: dataSource.filter(item => item.key !== key) });
  }
  onCellChange = (key, dataIndex) => {
    return (value) => {
      const dataSource = [...this.state.dataSource];
      const target = dataSource.find(item => item.key === key);
      if (target) {
        target[dataIndex] = value;
        this.setState({ dataSource });
      }
    };
  }
  handleAdd = () => {
    const { count, dataSource } = this.state;
    const newData = {
      key: count,
      name: `Edward King${count}`,
      tags: ['32'],
    };
    this.setState({
      dataSource: [...dataSource, newData],
      count: count + 1,
    });
  }
  removeTag = (key) => {
    return (tags) => {
      const dataSource = [...this.state.dataSource];
      const target = dataSource.find(item => item.key === key);
      if (target) {
        target.tags = tags;
        this.setState({ dataSource });
      }
    };
  }

  addTag = (key) => {
    return (tags) => {
      const dataSource = [...this.state.dataSource];
      const target = dataSource.find(item => item.key === key);
      if (target) {
        target.tags = tags;
        this.setState({ dataSource });
      }
    };
  }

  render() {
    return (
      <div>
        <Row>
          <Col span={6}>
            <ArticleList
              dataSource={this.state.dataSource}
              onDelete={key => this.onDelete(key)}
              onCellChange={(key, dataIndex) => this.onCellChange(key, dataIndex)}
              handleAdd={this.handleAdd}
              removeTag={(key, tags) => this.removeTag(key, tags)}
              addTag={(key, tags) => this.addTag(key, tags)}
              categories={this.state.categoriesdata}
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
