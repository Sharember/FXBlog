package cn.fanhub.fxblogui.repository;

import cn.fanhub.fxblogui.entity.Article;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ArticleRepository extends MongoRepository<Article, Long> {
}