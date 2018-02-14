import React, { PureComponent } from 'react';
import marked from 'marked';
import highlight from 'highlight.js';
import styles from './index.less'

marked.setOptions({
  renderer: new marked.Renderer(),
  gfm: true,
  tables: true,
  breaks: false,
  pedantic: false,
  sanitize: false,
  smartLists: true,
  smartypants: false,
  highlight: function (code) {
    highlight.initHighlightingOnLoad();
    return highlight.highlightAuto(code).value;
  }
});

const Content = ({ content }) => (
  <div
    dangerouslySetInnerHTML={{
      __html: marked(content)
    }}>
  </div>
)

export default class ArticleContent extends PureComponent {
  render() {
    const { content } = this.props;

    return (
      <div className={styles.content}>
        {<Content content={content} />}
      </div>
    )
  }
}