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
            key={item.name}
            actions={[<IconText type="eye-o" text={item.visitNum} />, <IconText type="like-o" text={item.likeNum} />, <IconText type="message" text={item.discussNum} />]}
            extra={<img width={170} alt="logo" src={item.firstImgUrl} />}
          >
            <List.Item.Meta
              //avatar={<Avatar src={item.avatar} />}
              title={<a href={item.url}>{item.name}</a>}
              description={item.digest}
            />
          </List.Item>
        )}
      />
    );
  }
}


