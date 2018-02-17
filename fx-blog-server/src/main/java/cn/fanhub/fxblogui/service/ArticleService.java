package cn.fanhub.fxblogui.service;

import cn.fanhub.fxblogui.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author chengfan
 * @date 2018-2-8 22:18:40
 */
public interface ArticleService extends BaseService<Article, Long>{
    /**
     * Gets page.
     *
     * @param pageable the pageable
     * @return the page
     */
    Page<Article> getPage(Pageable pageable);

    /**
     * Gets name by id.
     *
     * @param id the id
     * @return the name by id
     */
    String getNameById(long id);

    /**
     * Gets visit num top.
     *
     * @param topNum the top num
     * @return the visit num top
     */
    List<Article> getVisitNumTop(int topNum);

    /**
     * Gets create time top.
     *
     * @param topNum the top num
     * @return the create time top
     */
    List<Article> getCreateTimeTop(int topNum);

    /**
     * Gets correlation top.
     *
     * @param topNum the top num
     * @return the correlation top
     */
    List<Article> getCorrelationTop(int topNum);
}