package cn.fanhub.fxblogui.repository;

import cn.fanhub.fxblogui.entity.Article;

import java.util.List;

/**
 * @author chengfan
 * @date 2018-2-8 22:17:48
 */
public interface ArticleRepository extends BaseRepository<Article, Long>{

    /**
     * Gets articles by id.
     *
     * @param ids the ids
     * @return the articles by id
     */
    List<Article> getArticlesById(List<Long> ids);

}