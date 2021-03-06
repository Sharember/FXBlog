package cn.fanhub.fxblogui.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chengfan
 * @date 2018-2-8 22:16:58
 */
@Data
@ToString
public class Article extends BaseEntity {

    private String name;

    private String url;

    private long visitNum;

    private String content;

    private String digest;

    private long likeNum;

    private String firstImgUrl;

    private List<String> categories;

    @DBRef
    private List<Tag> tags = new ArrayList<>();

    @DBRef
    private List<Discuss> discusses = new ArrayList<>();

    public Article() {
    }

    public Article(String name) {
        this.name = name;
    }
}