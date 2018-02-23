package cn.fanhub.fxblogui.model;

import cn.fanhub.fxblogui.entity.Article;
import cn.fanhub.fxblogui.entity.Tag;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class WriteArticleVO {
    private String name;
    private List<String> tags;
    private List<String> categories;
    private String content;
    private long key;

    public static WriteArticleVO resolveTag(Article article) {
        WriteArticleVO writeArticleVO = new WriteArticleVO();
        writeArticleVO.setName(article.getName());
        writeArticleVO.setTags(article.getTags().stream().map(Tag::getName).collect(Collectors.toList()));
        writeArticleVO.setKey(article.getId());
        return writeArticleVO;
    }

    public static Article reverseTag(WriteArticleVO writeArticleVO) {
        Article article = new Article();
        article.setId(writeArticleVO.getKey());
        article.setTags(writeArticleVO.tags.stream().map(Tag::new).collect(Collectors.toList()));
        return article;
    }

    public static WriteArticleVO resolveCategories(Article article) {
        WriteArticleVO writeArticleVO = new WriteArticleVO();
        writeArticleVO.setName(article.getName());
        writeArticleVO.setCategories(article.getCategories());
        writeArticleVO.setKey(article.getId());
        return writeArticleVO;
    }

    public static Article reverseCategories(WriteArticleVO writeArticleVO) {
        Article article = new Article();
        article.setId(writeArticleVO.getKey());
        article.setCategories(writeArticleVO.getCategories());
        return article;
    }

    public static WriteArticleVO resolveContent(Article article) {
        WriteArticleVO writeArticleVO = new WriteArticleVO();
        writeArticleVO.setName(article.getName());
        writeArticleVO.setContent(article.getContent());
        writeArticleVO.setKey(article.getId());
        return writeArticleVO;
    }

    public static Article reverseContent(WriteArticleVO writeArticleVO) {
        Article article = new Article();
        article.setId(writeArticleVO.getKey());
        article.setContent(writeArticleVO.getContent());
        return article;
    }

}