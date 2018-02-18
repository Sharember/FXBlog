import React from 'react';
import PropTypes from 'prop-types';
import { Layout, Icon } from 'antd';
import DocumentTitle from 'react-document-title';
import { connect } from 'dva';
import { Route, Redirect, Switch } from 'dva/router';
import { ContainerQuery } from 'react-container-query';
import classNames from 'classnames';
import { enquireScreen } from 'enquire-js';
import GlobalHeader from '../components/GlobalHeader';
import GlobalFooter from '../components/GlobalFooter';
import CardSider from '../components/CardSider';
import NotFound from '../routes/Exception/404';
import { getRoutes } from '../utils/utils';


import logo from '../assets/logo.jpg';

const { Content } = Layout;


const query = {
  'screen-xs': {
    maxWidth: 575,
  },
  'screen-sm': {
    minWidth: 576,
    maxWidth: 767,
  },
  'screen-md': {
    minWidth: 768,
    maxWidth: 991,
  },
  'screen-lg': {
    minWidth: 992,
    maxWidth: 1199,
  },
  'screen-xl': {
    minWidth: 1200,
  },
};

let isMobile;
enquireScreen((b) => {
  isMobile = b;
});

class BaseLayout extends React.PureComponent {
  static childContextTypes = {
    location: PropTypes.object,
  }
  state = {
    isMobile,
  };
  getChildContext() {
    const { location } = this.props;
    return {
      location,
    };
  }
  componentDidMount() {
    enquireScreen((mobile) => {
      this.setState({
        isMobile: mobile,
      });
    });
    this.props.dispatch({
      type: 'global/fetchCardInfo',
    });
    this.props.dispatch({
      type: 'global/fetchTotalArticle',
    });

    this.props.dispatch({
      type: 'global/fetchMenuData',
    });
  }
  getPageTitle() {
    const { routerData, location } = this.props;
    const { pathname } = location;
    let title = 'blog';
    if (routerData[pathname] && routerData[pathname].name) {
      title = `${routerData[pathname].name} - blog`;
    }
    return title;
  }
  getBashRedirect = () => {
    // According to the url parameter to redirect
    // 这里是重定向的,重定向到 url 的 redirect 参数所示地址
    const urlParams = new URL(window.location.href);

    const redirect = urlParams.searchParams.get('redirect');
    // Remove the parameters in the url
    if (redirect) {
      urlParams.searchParams.delete('redirect');
      window.history.replaceState(null, 'redirect', urlParams.href);
    } else {
      return '/home';
    }
    return redirect;
  }
  render() {
    const {
      routerData, match, location, global
    } = this.props;
    const bashRedirect = this.getBashRedirect();
    const { cardInfo, menuData } = global;
    const layout = (
        <Layout>
          <GlobalHeader
            menuData={menuData}
            logo={logo}
            location={location}
            isMobile={this.state.isMobile}
          />
          <Layout>
            <Content style={{ 
              margin: '6% 5% 0 15%', 
              height: '100%', 
              backgroundColor: '#fff',
              padding: '2% 4% 2% 4%',
              boxShadow: '1px 1px 2px #cccaca',
            }}>
              <Switch>
                {
                  getRoutes(match.path, routerData).map(item =>
                    (
                      <Route
                        key={item}
                        path={item.path}
                        render={props => <item.component {...props} />}
                      />
                    )
                  )
                }
                <Redirect exact from="/" to={bashRedirect} />
                <Route render={NotFound} />
              </Switch>
            </Content>
            <CardSider
              style={{
                backgroundColor: '#fff',
                padding: '2% 4% 0 4%'
              }}
              dataSource={cardInfo}
            ></CardSider>
          </Layout>
          <GlobalFooter
            links={[{
              key: '简书 主页',
              title: '简书 主页',
              href: 'https://www.jianshu.com/u/8dc5811b228f',
              blankTarget: true,
            }, {
              key: 'github',
              title: <Icon type="github" />,
              href: 'https://github.com/cfshuming',
              blankTarget: true,
            }, {
              key: ' csdn 博客',
              title: 'csdn 博客',
              href: 'http://blog.csdn.net/qq_31655965',
              blankTarget: true,
            }]}
            copyright={
              <div>
                Copyright <Icon type="copyright" /> 2018 create by shuming
              </div>
            }
          />
        </Layout>
    );

    return (
      <DocumentTitle title={this.getPageTitle()}>
        <ContainerQuery query={query}>
          {params => <div className={classNames(params)}>{layout}</div>}
        </ContainerQuery>
      </DocumentTitle>
    );
  }
}

export default connect(({ global, loading }) => ({
  global,
}))(BaseLayout);