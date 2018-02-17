package cn.fanhub.fxblogui.repository;

import cn.fanhub.fxblogui.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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

    /**
     * Find by id page.
     *
     * @param id the id
     * @param pageable the pageable
     * @return the page
     */
    Page<Article> findById(long id, Pageable pageable);



}