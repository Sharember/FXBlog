package cn.fanhub.fxblogui.service;

import cn.fanhub.fxblogui.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author chengfan
 * @date 2018-2-8 22:18:40
 */
public interface ArticleService extends BaseService<Article, Long>{
    Page<Article> getPage(Pageable pageable);
}