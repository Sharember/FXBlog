import React, { PureComponent } from 'react';
import { Layout, Card, List } from 'antd';
import { Link } from 'dva/router';
import styles from './index.less';

const { Sider } = Layout;

export default class SiderMenu extends PureComponent {
  render() {
    const { dataSource } = this.props;
    return (
      <Sider
        trigger={null}
        collapsible
        breakpoint="lg"
        width={256}
      >
        {
          <List
            grid={{ gutter: 16, xs: 1, sm: 1, md: 1, lg: 1, xl: 1, xxl: 1 }}
            dataSource={dataSource}
            renderItem={item => (
              <List.Item>
                <Card title={item.title} className={styles.card} >
                {
                  item.content.map(name => (
                    <div key={name}>
                      <Link to={`/article/name/${name.split('(')[0]}`}>{name}</Link>
                    </div>
                  ))
                
                }</Card>
              </List.Item>
            )}
          />
        }
      </Sider>
    );
  }
}