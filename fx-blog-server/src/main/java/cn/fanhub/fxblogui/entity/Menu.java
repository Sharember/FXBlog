package cn.fanhub.fxblogui.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
public class Menu extends BaseEntity {

    private String url;
    private String name;
    private Categories categories;
    private Tag tag;
    private String icon;

    @DBRef
    private List<Menu> menus = new ArrayList<>();
}