package cn.fanhub.fxblogui.entity;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chengfan
 * @date 2018-2-8 22:17:05
 */
@Data
@ToString
public class Categories extends BaseEntity {

    private String author;

    private String name;

    private int articleNum;

    private List<Long> articles = new ArrayList<>();

    private List<Categories> children = new ArrayList<>();
}