import React, { PureComponent } from 'react';
import { List, Icon } from 'antd';
import { Link } from 'dva/router';
import marked from 'marked';


const IconText = ({ type, text }) => (
  <span>
    <Icon type={type} style={{ marginRight: 8 }} />
    {text}
  </span>
);
const Digest = ({ digest }) => (
  <div
    dangerouslySetInnerHTML={{
      __html: marked(digest, {sanitize: true})
    }}>
  </div>
)
  


export default class ArticleDigest extends PureComponent {
  
  render() {
    const { dataSource, loading, getNextPageInfo, total, current } = this.props;
    const pagination = {
      pageSize: 10,
      current,
      // todo get article num
      total,
      onChange: ((value) => {
        console.log(value)
        getNextPageInfo(value);
      }),
    };

    return (
      <List
        itemLayout="vertical"
        size="large"
        pagination={pagination}
        loading={loading}
        dataSource={dataSource}
        renderItem={item => (
          <List.Item
            key={item.name}
            actions={[<IconText type="eye-o" text={item.visitNum} />, <IconText type="like-o" text={item.likeNum} />, <IconText type="message" text={item.discussNum} />]}
            extra={<img width={170} alt="logo" src={item.firstImgUrl} />}
          >
            <List.Item.Meta
              //avatar={<Avatar src={item.avatar} />}
              title={<Link to={`/article/name/${item.name}`}>{item.name}</Link>}
              description={<Digest digest={item.digest} />}
            />
          </List.Item>
        )}
      />
    );
  }
}


