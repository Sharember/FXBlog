import React, { PureComponent } from 'react';
import { List, Icon } from 'antd';
import { Link } from 'dva/router';
import marked from 'marked';
import styles from './index.less'

const discuss = function() { 
  var appid = 'cytt1jd7p'; 
  var conf = 'prod_77ead76b7a230fb46cfca224b1b1bc63'; 
  var width = window.innerWidth || document.documentElement.clientWidth; 
  if ( width < 960 ) { 
  window.document.write('<script id="changyan_mobile_js" charset="utf-8" type="text/javascript" src="http://changyan.sohu.com/upload/mobile/wap-js/changyan_mobile.js?client_id=' + appid + '&conf=' + conf + '"><\/script>'); 
  } else { 
    var loadJs=function(d,a){
      var c=document.getElementsByTagName("head")[0]||document.head||document.documentElement;
      var b=document.createElement("script");
      b.setAttribute("type","text/javascript");
      b.setAttribute("charset","UTF-8");
      b.setAttribute("src",d);
      if (typeof a==="function"){
        if (window.attachEvent){
          b.onreadystatechange=function() {
            var e=b.readyState;
            if(e==="loaded"||e==="complete") {
              b.onreadystatechange=null;a()
            }
          }
        } else {
          b.onload=a
        }
      }
      c.appendChild(b)
    };
    loadJs("http://changyan.sohu.com/upload/changyan.js",function() {
      window.changyan.api.config({appid:appid,conf:conf})
    });
  }
 }

  

export default class ArticleFooter extends PureComponent {
  constructor(props){
    super(props);
    //启动畅言
    discuss();
  }
  render() {
    const { likeNum, lastArticle, nextArticle } = this.props;

    return (
      <div className={styles.footer}>
        <div>
          <Link to={`/article/name/${lastArticle}`}>上一篇: {lastArticle}</Link>  
          <Link to={`/article/name/${nextArticle}`} style={{ float: "right" }}>下一篇: {nextArticle}</Link>
        </div>
        <div id="SOHUCS"></div> 
      </div>
    )
  }
}