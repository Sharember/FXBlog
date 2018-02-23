import React, { PureComponent } from 'react';
import { Input, Row } from 'antd';
import { FaColumns, FaPencil, FaEye, FaArrowsAlt } from 'react-icons/lib/fa';
// import { FaColumns } from 'react-icons/lib/fa';
import marked from 'marked';
import highlight from 'highlight.js';
import styles from './index.less';

const { TextArea } = Input;

marked.setOptions({
  renderer: new marked.Renderer(),
  gfm: true,
  tables: true,
  breaks: true,
  pedantic: true,
  sanitize: false,
  smartLists: true,
  smartypants: false,
  highlight(code) {
    highlight.initHighlightingOnLoad();
    return highlight.highlightAuto(code).value;
  },
});

const View = ({ content }) => (
  <div
    dangerouslySetInnerHTML={{
      __html: marked(content),
    }}
    className={styles.view}
  />
);

export default class Editor extends PureComponent {
  state = {
    mode: 'split',
    isFullScreen: false,
  }
  componentDidMount() {
    // // cache dom node
    // this.textControl = ReactDOM.findDOMNode(this.refs.editor);
    // this.previewControl = ReactDOM.findDOMNode(this.refs.preview);
  }

  edit = () => {
    this.setState({ mode: 'edit' });
  }
  preview = () => {
    this.setState({ mode: 'preview' });
  }
  split = () => {
    this.setState({ mode: 'split' });
  }

  fullScreen = () => {
    this.setState({ isFullScreen: !this.state.isFullScreen });
  }


  render() {
    return (
      <div className={`${styles.editor} ${this.state.isFullScreen ? styles.fullscreen : ' '}`}>
        <div className={styles.header}>
          <nav>
            <a onClick={this.preview}><FaEye /></a>
            <a onClick={this.edit}><FaPencil /></a>
            <a onClick={this.split}><FaColumns /></a>
            <a onClick={this.fullScreen}><FaArrowsAlt /></a>
          </nav>
        </div>
        <Row >
          <div span={12} className={`${styles.mdEditor} ${this.state.mode === 'edit' ? styles.expand : ' '}`}>
            <TextArea
              rows={100}
              autosize={{ minRows: 31 }}
              value={this.props.dataSource}
              onChange={this.props.onEditorChange}
            />
          </div>
          <div span={12} className={`${styles.mdPreview} ${this.state.mode === 'preview' ? styles.expand : ' '} ${this.state.mode === 'edit' ? styles.shrink : ' '}`}>
            <View
              content={this.props.dataSource}
            />
          </div>
        </Row>
      </div>
    );
  }
}
