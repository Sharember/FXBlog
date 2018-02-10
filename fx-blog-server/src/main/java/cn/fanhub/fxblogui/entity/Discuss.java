package cn.fanhub.fxblogui.entity;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chengfan
 * @date 2018-2-8 22:17:12
 */
@Data
@ToString
public class Discuss extends BaseEntity{

    private String author;

    private String ip;

    private String content;

    private List<Discuss> subDiscuss = new ArrayList<>();
}