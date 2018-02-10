package cn.fanhub.fxblogui.entity;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chengfan
 * @date 2018-2-8 22:17:22
 */
@Data
@ToString
public class Tag extends BaseEntity{

    private String author;

    private String name;

    private List<Long> articles = new ArrayList<>();
}