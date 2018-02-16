import React, { PureComponent } from 'react';
import { Link } from 'dva/router';
import styles from './index.less'

const loadJs = function(d,a){
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

const thirdJs = () => { 
  var appid = 'cytt1jd7p'; 
  var conf = 'prod_77ead76b7a230fb46cfca224b1b1bc63'; 
  var width = window.innerWidth || document.documentElement.clientWidth; 
  if ( width < 960 ) { 
    window.document.write('<script id="changyan_mobile_js" charset="utf-8" type="text/javascript" src="http://changyan.sohu.com/upload/mobile/wap-js/changyan_mobile.js?client_id=' + appid + '&conf=' + conf + '"><\/script>'); 
  } else { 
    
    loadJs("http://changyan.sohu.com/upload/changyan.js",function() {
      window.changyan.api.config({appid:appid,conf:conf})
    });
  }
  loadJs('http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5, function(){
      window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdMiniList":false,"bdPic":"","bdStyle":"1","bdSize":"16"},"share":{},"image":{"viewList":["mshare","weixin","sqq","tsina","qzone","evernotecn","youdao"],"viewText":"分享到：","viewSize":"24"},"selectShare":{"bdContainerClass":null,"bdSelectMiniList":["mshare","weixin","sqq","tsina","qzone","evernotecn","youdao"]}};
    }));
 }
 

export default class ArticleFooter extends PureComponent {

  constructor(props) {
    super(props);
    let { likeNum } = this.props;
    if(!likeNum) {
      likeNum = 0;
    }
    this.state = {
      likeNum, 
    }
  }

  likeClick = () => {
    const { onLike } = this.props;
    const likeNum = this.state.likeNum + 1;
    this.setState({
      likeNum,
    })
    onLike();
  }

  render() {
    const { lastArticle, nextArticle } = this.props;
    thirdJs();
    return (
      <div className={styles.footer}>
        <div className={styles.lastNext}>
          {
            lastArticle === "无" ?
              <span>上一篇: 已经是第一篇啦</span>
            :
              <Link to={`/article/name/${lastArticle}`}>上一篇: {lastArticle}</Link>  
          }
          {
            nextArticle === "无" ?
              <span>下一篇: 已经是最后一篇啦</span>
            :
              <Link to={`/article/name/${nextArticle}`} style={{ float: "right" }}>下一篇: {nextArticle}</Link>
          }
          
        </div>
        <div className={styles.center}>
          <a className={styles.like} onClick={this.likeClick}>喜欢 | {this.state.likeNum}</a>
          <br /><br />
          <div className="bdsharebuttonbox" style={{ float : "right", margin: "2%"}}>
            <a className="bds_more" data-cmd="more"> </a> 
            <a className="bds_mshare" data-cmd="mshare" title="分享到一键分享"> </a>
            <a className="bds_weixin" data-cmd="weixin" title="分享到微信"> </a>
            <a className="bds_sqq" data-cmd="sqq" title="分享到QQ好友"> </a>
            <a className="bds_tsina" data-cmd="tsina" title="分享到新浪微博"> </a>
            <a className="bds_qzone" data-cmd="qzone" title="分享到QQ空间"> </a>
            <a className="bds_evernotecn" data-cmd="evernotecn" title="分享到印象笔记"> </a>
            <a className="bds_youdao" data-cmd="youdao" title="分享到有道云笔记"> </a>
          </div>
        </div>
        
        <div id="SOHUCS" className={styles.discuss}></div> 
      </div>
    )
  }
}