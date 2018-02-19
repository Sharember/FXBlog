package cn.fanhub.fxblogui.manager.impl;

import cn.fanhub.fxblogui.entity.Article;
import cn.fanhub.fxblogui.entity.Categories;
import cn.fanhub.fxblogui.entity.Tag;
import cn.fanhub.fxblogui.manager.ArticleManger;
import cn.fanhub.fxblogui.model.AllCardInfoVO;
import cn.fanhub.fxblogui.model.ArticleDetailVO;
import cn.fanhub.fxblogui.model.ArticleDigestVO;
import cn.fanhub.fxblogui.service.ArticleService;
import cn.fanhub.fxblogui.service.CategoriesService;
import cn.fanhub.fxblogui.service.TagService;
import cn.fanhub.fxblogui.util.IdUtil;
import cn.fanhub.fxblogui.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
                article.getTags()
                        .stream()
                        .peek(tag -> {
                            List<Long> list = tag.getArticles();
                            list.add(articleId);
                            tag.setArticleNum(list.size());
                        })
                        .collect(Collectors.toList())
        );
        categoriesService.save(
                article.getCategories()
                        .stream()
                        .peek(categories -> {
                            List<Long> list = categories.getArticles();
                            list.add(articleId);
                            categories.setArticleNum(list.size());
                        })
                        .collect(Collectors.toList())
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
    public Map<String, Object> getByTagName(String tagName, PageRequest pageRequest) {
        Map<String, Object> map = new HashMap<>();
        Tag tag = tagService.getByName(tagName);
        map.put("total", tag.getArticleNum());
        map.put("articles",
                articleService.getAll(PageUtil.getPageArticleList(tag.getArticles(), pageRequest, tag.getArticleNum()))
                        .stream()
                        .map(ArticleDigestVO::convertToArticleDigestVO)
                        .collect(Collectors.toList()));

        return map;
    }

    /**
     * Gets by categories name.
     *
     * @param categoriesName the categories name
     * @return the by categories name
     */
    @Override
    public Map<String, Object> getByCategoriesName(String categoriesName, PageRequest pageRequest) {
        Categories categories = categoriesService.getByName(categoriesName);
        Map<String, Object> map = new HashMap<>();
        map.put("total", categories.getArticleNum());
        map.put("articles",
                articleService.getAll(PageUtil.getPageArticleList(categories.getArticles(), pageRequest, categories.getArticleNum()))
                        .stream()
                        .map(ArticleDigestVO::convertToArticleDigestVO)
                        .collect(Collectors.toList()));

        return map;
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
        // todo tag 修改等
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
        return page.getContent()
                .stream()
                .map(ArticleDigestVO::convertToArticleDigestVO)
                .collect(Collectors.toList());
    }

    /**
     * Gets by name.
     *
     * @param articleName the article name
     * @return the by name
     */
    @Override
    public ArticleDetailVO getByName(String articleName) {
        Article article = articleService.getByName(articleName);
        // 访问量 + 1
        article.setVisitNum(article.getVisitNum() + 1);

        articleService.save(article);
        ArticleDetailVO detailVO = ArticleDetailVO.convertToArticleDigestVO(article);
        detailVO.setLastArticle(articleService.getNameById(article.getId() - 1));
        detailVO.setNextArticle(articleService.getNameById(article.getId() + 1));
        return detailVO;
    }

    /**
     * Like.
     *
     * @param name the name
     */
    @Override
    public void like(String name) {
        Article article = articleService.getByName(name);
        article.setLikeNum(article.getLikeNum() + 1);
        articleService.save(article);
    }

    /**
     * Visit.
     *
     * @param name the name
     */
    @Override
    @Deprecated
    public void visit(String name) {
        Article article = articleService.getByName(name);
        article.setVisitNum(article.getVisitNum() + 1);
        articleService.save(article);
    }

    /**
     * Gets article count.
     *
     * @return the article count
     */
    @Override
    public long getArticleCount() {
        return articleService.getCount();
    }

    /**
     * Gets all card info.
     *
     * @return the all card info
     */
    @Override
    public AllCardInfoVO getAllCardInfo() {
        return new AllCardInfoVO()
                .convertCreateTop(articleService.getCreateTimeTop(5))
                .convertVisitTop(articleService.getVisitNumTop(5))
                .convertTags(tagService.getAllTagName());

    }
}