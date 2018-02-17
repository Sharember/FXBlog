package cn.fanhub.fxblogui.model;

import cn.fanhub.fxblogui.entity.Article;
import cn.fanhub.fxblogui.entity.Categories;
import cn.fanhub.fxblogui.entity.Tag;
import lombok.Data;
import lombok.ToString;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString
public class ArticleDetailVO {

    private long id;

    private String name;

    private String content;

    private long likeNum;

    private long visitNum;

    private String lastArticle;

    private String nextArticle;

    private String createTime;

    private List<Categories> categories = new ArrayList<>();

    private List<Tag> tags = new ArrayList<>();

    public static ArticleDetailVO convertToArticleDigestVO(Article article) {

        ArticleDetailVO articleDetailVO = new ArticleDetailVO();

        articleDetailVO.setId(article.getId());
        articleDetailVO.setName(article.getName());
        articleDetailVO.setContent(article.getContent());
        articleDetailVO.setLikeNum(article.getLikeNum());
        articleDetailVO.setVisitNum(article.getVisitNum());
        articleDetailVO.setCreateTime(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(article.getCreateTime()));
        articleDetailVO.setCategories(article.getCategories());
        articleDetailVO.setTags(article.getTags());

        return articleDetailVO;
    }
}