import React, { PureComponent } from 'react';
import ReactDOM from 'react-dom';
import { Input, Row, Col, Icon } from 'antd';
import marked from 'marked';
import highlight from 'highlight.js';
import styles from './index.less';
import cNames from 'classnames';

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
    content: '',
    panelClass: 'md-panel',
    mode: 'split',
    isFullScreen: false,
  }
  componentDidMount() {
    // cache dom node
    this.textControl = ReactDOM.findDOMNode(this.refs.editor);
    this.previewControl = ReactDOM.findDOMNode(this.refs.preview);
  }

  componentWillUnmount() {
    this.textControl = null;
    this.previewControl = null;
  }

  onChange = (e) => {
    this.setState({
      content: e.target.value,
      // result: marked(this.props.content || '')
    });
  }

  getToolBar = () => {
    return (
      <div className="md-toolbar clearfix">
        <Icon type="link" onClick={this.linkText} />
      </div>
    );
  }

  getExternalBtn = () => {
    return React.Children.map(this.props.children, (option) => {
      if (option.type === 'option') {
        return <li className="tb-btn"><a {...option.props}>{option.props.children}</a></li>;
      }
    });
  }

  getModeBar = () => {
    const checkActive = mode => cNames({ active: this.state.mode === mode });

    return (
      <ul className="md-modebar">
        <li className="tb-btn pull-right">
          <a className={checkActive('preview')} onClick={this.changeMode('preview')} title="预览模式">
            <i className="fa fa-eye" />
          </a>
        </li> { /* preview mode */ }
        <li className="tb-btn pull-right">
          <a className={checkActive('split')} onClick={this.changeMode('split')} title="分屏模式">
            <i className="fa fa-columns" />
          </a>
        </li> { /* split mode */ }
        <li className="tb-btn pull-right">
          <a className={checkActive('edit')} onClick={this.changeMode('edit')} title="编辑模式">
            <i className="fa fa-pencil" />
          </a>
        </li> { /* edit mode */ }
        <li className="tb-btn spliter pull-right" />
        <li className="tb-btn pull-right"><a title="全屏模式" onClick={this.toggleFullScreen}><i className="fa fa-arrows-alt" /></a></li> {/* full-screen */}
      </ul>
    );
  };

  // // event handlers
  // onChange = () => {
  //   this.isDirty = true;// set dirty
  //   if (this.ltr) clearTimeout(this.ltr);

  //   this.ltr = setTimeout(() => {
  //     this.setState({ result: marked(this.textControl.value) }); // change state
  //   }, 300);
  // }
  changeMode = (mode) => {
    return () => {
      this.setState({ mode });
    };
  }
  toggleFullScreen = (e) => {
    this.setState({ isFullScreen: !this.state.isFullScreen });
  }
  // default text processors
  preInputText = (text, preStart, preEnd) => {
    const start = this.textControl.selectionStart;
    const end = this.textControl.selectionEnd;
    const origin = this.textControl.value;

    if (start !== end) {
      const exist = origin.slice(start, end);
      text = text.slice(0, preStart) + exist + text.slice(preEnd);
      preEnd = preStart + exist.length;
    }
    this.textControl.value = origin.slice(0, start) + text + origin.slice(end);
    // pre-select
    this.textControl.setSelectionRange(start + preStart, start + preEnd);
    this.setState({ result: marked(this.textControl.value) }); // change state
  }
  boldText = () => {
    this.preInputText('**加粗文字**', 2, 6);
  }
  italicText = () => {
    this.preInputText('斜体文字', 1, 5);
  }
  linkText = () => {
    this.preInputText('[链接文本](www.yourlink.com)', 1, 5);
  }
  blockquoteText = () => {
    this.preInputText('> 引用', 2, 4);
  }
  codeText = () => {
    this.preInputText('```\ncode block\n```', 4, 14);
  }
  pictureTex = () => {
    this.preInputText('![alt](www.yourlink.com)', 2, 5);
  }
  listUlText = () => {
    this.preInputText('- 无序列表项0\n- 无序列表项1', 2, 8);
  }
  listOlText = () => {
    this.preInputText('1. 有序列表项0\n2. 有序列表项1', 3, 9);
  }
  headerText = () => {
    this.preInputText('## 标题', 3, 5);
  }

  render() {
    const panelClass = cNames(['md-panel', { fullscreen: this.state.isFullScreen }]);
    const editorClass = cNames(['md-editor', { expand: this.state.mode === 'edit' }]);
    const previewClass = cNames(['md-preview', 'markdown', { expand: this.state.mode === 'preview', shrink: this.state.mode === 'edit' }]);

    return (
      <div className={panelClass}>
        <div className={styles.header}>
          {this.getModeBar()}
          {this.getToolBar()}
        </div>
        <Row >
          <Col span={12} className={editorClass}>
            <TextArea
              rows={100}
              autosize={{ minRows: 33 }}
              value={this.state.content}
              onChange={this.onChange}
            />
          </Col>
          <Col span={12} >
            <View
              className={previewClass}
              ref="preview"
              content={this.state.content}
            />
          </Col>
        </Row>
      </div>
    );
  }
}
