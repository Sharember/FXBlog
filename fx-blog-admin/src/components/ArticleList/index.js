import React, { PureComponent } from 'react';
import { Table, Button, Popconfirm, Icon, Cascader } from 'antd';

import EditableTagGroup from '../EditableTagGroup';
import EditableCell from '../EditableCell';

import styles from './index.less';

export default class ArticleList extends PureComponent {
  constructor(props) {
    super(props);
    this.columns = [{
      title: '文章列表',
      key: 'name',
      width: '100%',
      colSpan: 2,
      render: (text, record) => (
        <div>
          <EditableCell
            value={text.name}
            onChange={this.onCellChange(record.key, 'name')}
          />
          <span style={{ display: 'inline-block' }}>
            <Icon
              type="tag-o"
              className={styles.icon}
            />
            <EditableTagGroup
              dataSource={text.tags}
            />
          </span>
        </div>
      ),
    }, {
      title: 'operation',
      dataIndex: 'operation',
      colSpan: 0,
      render: (text, record) => {
        return (
          <Popconfirm title="Sure to delete?" onConfirm={() => this.onDelete(record.key)}>
            <a href="#"><Icon type="delete" /></a>
          </Popconfirm>
        );
      },
    }];

    this.state = {
      dataSource: [{
        key: 0,
        name: 'Edward King 0',
        tags: ['32'],
      }],
      count: 1,
      options: [{
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
    };
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
  onDelete = (key) => {
    const dataSource = [...this.state.dataSource];
    this.setState({ dataSource: dataSource.filter(item => item.key !== key) });
  }

  onChange = (value) => {
    console.log(value);
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

  render() {
    const { dataSource, options } = this.state;
    const { columns } = this;
    return (
      <div className={styles.list}>
        <Table
          pagination={false}
          dataSource={dataSource}
          columns={columns}
        />
        <Button
          onClick={this.handleAdd}
          className={styles.button}
        >
          Add
        </Button>
        <div className={styles.select}>
          <Cascader
            options={options}
            onChange={this.onChange}
            changeOnSelect
          />
        </div>
      </div>
    );
  }
}
