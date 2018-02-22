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
              removeTag={this.props.removeTag(record.key)}
              addTag={this.props.addTag(record.key)}
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
  }
  onCellChange = (key, dataIndex) => {
    this.props.onCellChange(key, dataIndex);
  }
  onDelete = (key) => {
    this.props.onDelete(key);
  }

  onChange = (value) => {
    this.props.onSelectCategories(value);
  }

  handleAdd = () => {
    this.props.handleAdd();
  }

  render() {
    const { dataSource, categories } = this.props;
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
            options={categories}
            onChange={this.onChange}
            changeOnSelect
          />
        </div>
      </div>
    );
  }
}
