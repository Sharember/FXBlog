package cn.fanhub.fxblogui.model;

import cn.fanhub.fxblogui.entity.Article;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ArticleDigestVO {

    private long id;

    private String name;

    private String url;

    private String digest;

    private String firstImgUrl = "https://gw.alipayobjects.com/zos/rmsportal/mqaQswcyDLcXyDKnZfES.png";

    private long likeNum;

    private long visitNum;

    private long discussNum;

    public static ArticleDigestVO convertToArticleDigestVO(Article article) {

        ArticleDigestVO articleDigestVO = new ArticleDigestVO();

        articleDigestVO.setId(article.getId());
        articleDigestVO.setName(article.getName());
        articleDigestVO.setUrl(article.getUrl());
        articleDigestVO.setDiscussNum(article.getDiscusses().size());
        articleDigestVO.setDigest(article.getDigest() == null ? article.getContent().substring(0, 200) : article.getDigest());
        articleDigestVO.setFirstImgUrl(article.getFirstImgUrl());
        articleDigestVO.setLikeNum(article.getLikeNum());
        articleDigestVO.setVisitNum(article.getVisitNum());

        return articleDigestVO;
    }
}