package cn.fanhub.fxblogui.manager;

import cn.fanhub.fxblogui.entity.Article;
import cn.fanhub.fxblogui.model.AllCardInfoVO;
import cn.fanhub.fxblogui.model.ArticleDetailVO;
import cn.fanhub.fxblogui.model.ArticleDigestVO;
import cn.fanhub.fxblogui.model.WriteArticleVO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * @author chengfan
 * @date 2018-2-8 22:17:35
 */
public interface ArticleManger {
    /**
     * Save article article.
     *
     * @param article the article
     * @return the article
     */
    Article saveArticle(Article article);

    /**
     * Gets by tag name.
     *
     * @param tagName the tag name
     * @return the by tag name
     */
    Map<String, Object> getByTagName(String tagName, PageRequest pageRequest);

    /**
     * Gets by categories name.
     *
     * @param categoriesName the categories name
     * @return the by categories name
     */
    Map<String, Object> getByCategoriesName(String categoriesName, PageRequest pageRequest);

    /**
     * Gets one.
     *
     * @param id the id
     * @return the one
     */
    Article getOne(long id);

    /**
     * Gets all.
     *
     * @return the all
     */
    List<Article> getAll();

    /**
     * Update article.
     *
     * @param article the article
     * @return the article
     */
    Article update(Article article);


    /**
     * Gets article digests.
     *
     * @param pageable the pageable
     * @return the article digests
     */
    List<ArticleDigestVO> getArticleDigests(Pageable pageable);

    /**
     * Gets by name.
     *
     * @param articleName the article name 
     * @return the by name
     */
    ArticleDetailVO getByName(String articleName);

    /**
     * Like.
     *
     * @param name the name
     */
    void like(String name);

    /**
     * Visit.
     *
     * @param name the name
     */
    @Deprecated
    void visit(String name);

    /**
     * Gets article count.
     *
     * @return the article count
     */
    long getArticleCount();

    /**
     * Gets all card info.
     *
     * @return the all card info
     */
    AllCardInfoVO getAllCardInfo();

    List<WriteArticleVO> getWriteArticleVO(List<String> categoriesName);
}