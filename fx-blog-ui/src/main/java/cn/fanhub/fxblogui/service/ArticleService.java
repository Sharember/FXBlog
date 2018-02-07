package cn.fanhub.fxblogui.service;

import cn.fanhub.fxblogui.entity.Article;

import java.util.List;

public interface ArticleService {

    Article save(Article article);

    Article update(Article article);

    void delete(Article article);

    Article getOne(long id);

    List<Article> getAll();
}