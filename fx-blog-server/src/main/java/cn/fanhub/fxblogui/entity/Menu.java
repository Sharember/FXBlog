package cn.fanhub.fxblogui.entity;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
public class Menu extends BaseEntity {

    private String path;
    private String name;
    private Categories categories;
    private Tag tag;
    private String icon;

    private List<Menu> children = new ArrayList<>();
}