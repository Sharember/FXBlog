import React, { PureComponent } from 'react';
import { Input, Icon } from 'antd';

import styles from './index.less';

export default class EditableCell extends PureComponent {
  state = {
    value: this.props.value,
    editable: false,
  }
  handleChange = (e) => {
    const { value } = e.target;
    this.setState({ value });
  }
  check = () => {
    this.setState({ editable: false });
    if (this.props.onChange) {
      this.props.onChange(this.state.value);
    }
  }
  edit = () => {
    this.setState({ editable: true });
  }
  render() {
    const { value, editable } = this.state;
    return (
      <div className={styles.editableCell}>
        {
          editable ? (
            <div className={styles.editableCellInputWrapper}>
              <Input
                value={value}
                onChange={this.handleChange}
                onPressEnter={this.check}
              />
              <Icon
                type="check"
                className={styles.editableCellIconCheck}
                onClick={this.check}
              />
            </div>
          ) : (
            <div className={styles.editableCellTextWrapper}>
              {<span style={{ fontSize: '20px', fontWeight: 'bold' }}>{value}</span> || ' '}
              <Icon
                type="edit"
                className={styles.editableCellIcon}
                onClick={this.edit}
              />
            </div>
          )
        }
      </div>
    );
  }
}
