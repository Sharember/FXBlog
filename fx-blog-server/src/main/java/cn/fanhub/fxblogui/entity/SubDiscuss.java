package cn.fanhub.fxblogui.entity;

import lombok.Data;
import lombok.ToString;

/**
 * @author chengfan
 * @date 2018-2-8 22:17:19
 */
@Data
@ToString
public class SubDiscuss extends BaseEntity {

    private String author;

    private String ip;

    private String content;

}