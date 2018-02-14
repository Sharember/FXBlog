package cn.fanhub.fxblogui.service.impl;

import cn.fanhub.fxblogui.entity.Article;
import cn.fanhub.fxblogui.service.ArticleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author chengfan
 * @date 2018-2-8 22:18:19
 */
@Service
public class ArticleServiceImpl extends BaseServiceImpl<Article, Long> implements ArticleService{

    @Override
    public Page<Article> getPage(Pageable pageable) {
        return super.baseRepository.findAll(pageable);
    }

    @Override
    public String getNameById(long id) {
        return super.baseRepository.getNameById(id);
    }
}