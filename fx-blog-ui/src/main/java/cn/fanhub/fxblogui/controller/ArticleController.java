package cn.fanhub.fxblogui.controller;

import cn.fanhub.fxblogui.entity.Article;
import cn.fanhub.fxblogui.model.Result;
import cn.fanhub.fxblogui.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/article")
@RestController
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @GetMapping("/{id}")
    public Result<Article> getArticle(@PathVariable long id) {
        return Result.of(articleService.getOne(id));
    }

    @GetMapping("/all")
    public Result<List<Article>> getArticles() {
        return Result.of(articleService.getAll());
    }

    @GetMapping("/tag/{tagName}")
    public Result<List<Article>> getArticlesByTag(@PathVariable String tagName) {
        return Result.of(articleService.getByTagName(tagName));
    }

    @GetMapping("/categories/{categoriesName}")
    public Result<List<Article>> getArticlesByCategories(@PathVariable String categoriesName) {
        return Result.of(articleService.getByCategoriesName(categoriesName));
    }

    @PutMapping
    public Result<Article> updateArticle(@RequestBody Article article) {
        return Result.of(articleService.update(article));
    }

    @PostMapping
    public Result<Article> add(@RequestBody Article article){
        return Result.of(articleService.save(article));
    }
}