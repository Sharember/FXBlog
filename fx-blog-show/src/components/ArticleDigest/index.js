import React, { PureComponent } from 'react';
import { List, Avatar, Icon } from 'antd';
import styles from './index.less';



const IconText = ({ type, text }) => (
  <span>
    <Icon type={type} style={{ marginRight: 8 }} />
    {text}
  </span>
);

export default class ArticleDigest extends PureComponent {

  // constructor(props) {
  //   super(props);
  //   // const pagination = {
  //   //   pageSize: 10,
  //   //   current: 1,
  //   //   total: listData.length,
  //   //   onChange: (() => {}),
  //   // };
  // }
  
  render() {
    const { dataSource } = this.props;
   
    return (
      <List
        itemLayout="vertical"
        size="large"
        //pagination={pagination}
        dataSource={dataSource}
        renderItem={item => (
          <List.Item
            key={item.title}
            actions={[<IconText type="star-o" text="156" />, <IconText type="like-o" text="156" />, <IconText type="message" text="2" />]}
            extra={<img width={272} alt="logo" src="https://gw.alipayobjects.com/zos/rmsportal/mqaQswcyDLcXyDKnZfES.png" />}
          >
            <List.Item.Meta
              avatar={<Avatar src={item.avatar} />}
              title={<a href={item.href}>{item.title}</a>}
              description={item.description}
            />
            {item.content}
          </List.Item>
        )}
      />
    );
  }
}


