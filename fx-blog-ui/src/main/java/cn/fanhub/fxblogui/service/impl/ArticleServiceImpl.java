package cn.fanhub.fxblogui.service.impl;

import cn.fanhub.fxblogui.entity.Article;
import cn.fanhub.fxblogui.repository.ArticleRepository;
import cn.fanhub.fxblogui.repository.CategoriesRepository;
import cn.fanhub.fxblogui.repository.DiscussRepository;
import cn.fanhub.fxblogui.repository.TagRepository;
import cn.fanhub.fxblogui.service.ArticleService;
import cn.fanhub.fxblogui.util.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CategoriesRepository categoriesRepository;

    @Autowired
    private DiscussRepository discussRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private MongoTemplate mongo;

    @Override
    public Article save(Article article) {

        // 新增 article
        final long articleId = IdUtil.getNextId(Article.class.getSimpleName(), mongo);
        tagRepository.save(
                article.getTags().stream().peek(tag -> tag.getArticles().add(articleId)).collect(Collectors.toList())
        );
        categoriesRepository.save(
                article.getCategories().stream().peek(categories -> categories.getArticles().add(articleId)).collect(Collectors.toList())
        );

        return articleRepository.save(article);
    }

    @Override
    public Article update(Article article) {
        return articleRepository.save(article);
    }

    @Override
    public void delete(Article article) {
        // todo 更新 tag
        // todo 更新 category
        // todo 删除评论
        // 删除功能暂时没什么卵用
        articleRepository.delete(article);

    }

    @Override
    public Article getOne(long id) {
        return articleRepository.findOne(id);
    }

    @Override
    public List<Article> getAll() {
        return articleRepository.findAll();
    }
}