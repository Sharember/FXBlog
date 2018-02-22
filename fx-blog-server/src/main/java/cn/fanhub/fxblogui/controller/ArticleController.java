package cn.fanhub.fxblogui.controller;

import cn.fanhub.fxblogui.entity.Article;
import cn.fanhub.fxblogui.manager.ArticleManger;
import cn.fanhub.fxblogui.model.AllCardInfoVO;
import cn.fanhub.fxblogui.model.ArticleDetailVO;
import cn.fanhub.fxblogui.model.ArticleDigestVO;
import cn.fanhub.fxblogui.model.Result;
import cn.fanhub.fxblogui.model.WriteArticleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author chengfan
 * @date 2018-2-8 22:16:38
 */
@RequestMapping("/article")
@RestController
public class ArticleController {
    @Autowired
    private ArticleManger articleManger;

    /**
     * Gets article.
     *
     * @param id the id
     * @return the article
     */
    @GetMapping("/{id}")
    public Result<Article> getArticle(@PathVariable long id) {
        return Result.of(articleManger.getOne(id));
    }

    /**
     * Gets articles.
     *
     * @return the articles
     */
    @GetMapping("/all")
    public Result<List<Article>> getArticles() {
        return Result.of(articleManger.getAll());
    }

    /**
     * Gets card info.
     *
     * @return the card info
     */
    @GetMapping("/card")
    public Result<AllCardInfoVO> getCardInfo() {
        return Result.of(articleManger.getAllCardInfo());
    }

    /**
     * Gets articles by tag.
     *
     * @param tagName the tag name
     * @return the articles by tag
     */
    @GetMapping("/tag/{tagName}/page/{pageNum}")
    public Result<Map<String, Object>> getArticlesByTag(@PathVariable String tagName, @PathVariable int pageNum) {
        PageRequest pageRequest = new PageRequest(pageNum, 10);
        return Result.of(articleManger.getByTagName(tagName, pageRequest));
    }

    /**
     * Gets articles by categories.
     *
     * @param categoriesName the categories name
     * @return the articles by categories
     */
    @GetMapping("/categories/{categoriesName}/page/{pageNum}")
    public Result<Map<String, Object>> getArticlesByCategories(@PathVariable String categoriesName, @PathVariable int pageNum) {
        PageRequest pageRequest = new PageRequest(pageNum, 10);
        return Result.of(articleManger.getByCategoriesName(categoriesName, pageRequest));
    }

    /**
     * Gets article digests.
     *
     * @param pageNum the page num
     * @return the article digests
     */
    @GetMapping("/digests/page/{pageNum}")
    public Result<List<ArticleDigestVO>> getArticleDigests(@PathVariable int pageNum) {
        PageRequest pageRequest = new PageRequest(pageNum, 10);
        return Result.of(articleManger.getArticleDigests(pageRequest));
    }

    /**
     * Gets article by name.
     *
     * @param articleName the article name
     * @return the article by name
     */
    @GetMapping("/name/{articleName}")
    public Result<ArticleDetailVO> getArticleByName(@PathVariable String articleName) {

        return Result.of(articleManger.getByName(articleName));
    }

    /**
     * Gets total article.
     *
     * @return the total article
     */
    @GetMapping("/total/num")
    public Result<Long> getTotalArticle() {
        return Result.of(articleManger.getArticleCount());
    }


    @GetMapping("/admin/categories/{categoriesNames}")
    public Result<List<WriteArticleVO>> getWriteArticleVO(@PathVariable List<String> categoriesNames) {
        return Result.of(articleManger.getWriteArticleVO(categoriesNames));
    }

    /**
     * Update article result.
     *
     * @param article the article
     * @return the result
     */
    @PutMapping
    public Result<Article> updateArticle(@RequestBody Article article) {
        return Result.of(articleManger.update(article));
    }

    /**
     * Add result.
     *
     * @param article the article
     * @return the result
     */
    @PostMapping
    public Result<Article> add(@RequestBody Article article){
        return Result.of(articleManger.saveArticle(article));
    }

    /**
     * Like result.
     *
     * @param name the name
     * @return the result
     */
    @PostMapping("/name/{name}/like")
    public Result like(@PathVariable String name){
        articleManger.like(name);
        return Result.success();
    }

    /**
     * Visit result.
     *
     * @param name the name
     * @return the result
     */
    @PostMapping("/name/{name}/visit")
    @Deprecated
    public Result visit(@PathVariable String name){
        articleManger.visit(name);
        return Result.success();
    }
}