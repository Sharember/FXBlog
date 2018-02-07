package cn.fanhub.fxblogui.repository;

import cn.fanhub.fxblogui.entity.Article;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ArticleRepository extends MongoRepository<Article, Long> {

    List<Article> getArticlesById(List<Long> ids);
}