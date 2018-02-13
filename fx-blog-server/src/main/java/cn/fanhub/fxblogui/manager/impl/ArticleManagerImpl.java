package cn.fanhub.fxblogui.manager.impl;

import cn.fanhub.fxblogui.entity.Article;
import cn.fanhub.fxblogui.entity.Categories;
import cn.fanhub.fxblogui.entity.Tag;
import cn.fanhub.fxblogui.manager.ArticleManger;
import cn.fanhub.fxblogui.model.ArticleDigestVO;
import cn.fanhub.fxblogui.service.ArticleService;
import cn.fanhub.fxblogui.service.CategoriesService;
import cn.fanhub.fxblogui.service.TagService;
import cn.fanhub.fxblogui.util.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chengfan
 * @date 2018-2-8 22:17:29
 */
@Component
public class ArticleManagerImpl implements ArticleManger {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoriesService categoriesService;

    @Autowired
    private TagService tagService;

    @Autowired
    private MongoTemplate mongo;

    /**
     * Save article article.
     *
     * @param article the article
     * @return the article
     */
    @Override
    public Article saveArticle(Article article) {
        // 新增 article
        final long articleId = IdUtil.getNextId(Article.class.getSimpleName(), mongo);
        tagService.save(
                article.getTags().stream().peek(tag -> tag.getArticles().add(articleId)).collect(Collectors.toList())
        );
        categoriesService.save(
                article.getCategories().stream().peek(categories -> categories.getArticles().add(articleId)).collect(Collectors.toList())
        );
        return articleService.save(article);
    }

    /**
     * Gets by tag name.
     *
     * @param tagName the tag name
     * @return the by tag name
     */
    @Override
    public List<Article> getByTagName(String tagName) {
        Tag tag = tagService.getByName(tagName);
        return articleService.getAll(tag.getArticles());
    }

    /**
     * Gets by categories name.
     *
     * @param categoriesName the categories name
     * @return the by categories name
     */
    @Override
    public List<Article> getByCategoriesName(String categoriesName) {
        Categories categories = categoriesService.getByName(categoriesName);
        return articleService.getAll(categories.getArticles());
    }

    /**
     * Gets one.
     *
     * @param id the id
     * @return the one
     */
    @Override
    public Article getOne(long id) {
        return articleService.getOne(id);
    }

    /**
     * Gets all.
     *
     * @return the all
     */
    @Override
    public List<Article> getAll() {
        return articleService.getAll();
    }

    /**
     * Update article.
     *
     * @param article the article
     * @return the article
     */
    @Override
    public Article update(Article article) {
        return articleService.update(article);
    }

    /**
     * Save article.
     *
     * @param article the article
     * @return the article
     */
    @Override
    public Article save(Article article) {
        return articleService.update(article);
    }

    /**
     * Gets article digests.
     *
     * @param pageable the pageable
     * @return the article digests
     */
    @Override
    public List<ArticleDigestVO> getArticleDigests(Pageable pageable) {
        Page<Article> page = articleService.getPage(pageable);
        return page.getContent().stream().map(ArticleDigestVO::convertToArticleDigestVO).collect(Collectors.toList());
    }
}