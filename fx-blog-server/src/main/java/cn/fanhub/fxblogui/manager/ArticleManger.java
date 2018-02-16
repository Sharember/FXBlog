package cn.fanhub.fxblogui.manager;

import cn.fanhub.fxblogui.entity.Article;
import cn.fanhub.fxblogui.model.ArticleDetailVO;
import cn.fanhub.fxblogui.model.ArticleDigestVO;
import org.springframework.data.domain.Pageable;

import java.util.List;

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
    List<ArticleDigestVO> getByTagName(String tagName);

    /**
     * Gets by categories name.
     *
     * @param categoriesName the categories name
     * @return the by categories name
     */
    List<ArticleDigestVO> getByCategoriesName(String categoriesName);

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
}