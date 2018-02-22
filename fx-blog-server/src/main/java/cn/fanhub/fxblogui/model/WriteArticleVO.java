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
    private long key;

    public static WriteArticleVO resolve(Article article) {
        WriteArticleVO writeArticleVO = new WriteArticleVO();
        writeArticleVO.setName(article.getName());
        writeArticleVO.setTags(article.getTags().stream().map(Tag::getName).collect(Collectors.toList()));
        writeArticleVO.setKey(article.getId());
        return writeArticleVO;
    }

}