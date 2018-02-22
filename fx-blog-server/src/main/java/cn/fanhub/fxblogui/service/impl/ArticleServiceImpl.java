package cn.fanhub.fxblogui.service.impl;

import cn.fanhub.fxblogui.entity.Article;
import cn.fanhub.fxblogui.repository.ArticleRepository;
import cn.fanhub.fxblogui.service.ArticleService;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author chengfan
 * @date 2018-2-8 22:18:19
 */
@Service
public class ArticleServiceImpl extends BaseServiceImpl<Article, Long> implements ArticleService{

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private ArticleRepository articleRepository;

    /**
     * Gets page.
     *
     * @param pageable the pageable
     * @return the page
     */
    @Override
    public Page<Article> getPage(Pageable pageable) {

        return super.baseRepository.findAll(pageable);
    }

    /**
     * Gets name by id.
     *
     * @param id the id
     * @return the name by id
     */
    @Override
    public String getNameById(long id) {
        return Optional
                .ofNullable(super.baseRepository.getNameById(id))
                .orElse(new Article("无"))
                .getName();
    }

    /**
     * Gets visit num top.
     *
     * @param topNum the top num
     * @return the visit num top
     */
    @Override
    public List<Article> getVisitNumTop(int topNum) {
        DBObject dbObject = new BasicDBObject();
        DBObject fieldObject = new BasicDBObject();

        fieldObject.put("visitNum", true);

        fieldObject.put("name", true);

        Query query = new BasicQuery(dbObject, fieldObject);

        query.with(new Sort(new Order(Direction.DESC, "visitNum")));
        query.limit(topNum);
        return this.mongoTemplate.find(query, Article.class);
    }

    /**
     * Gets create time top.
     *
     * @param topNum the top num
     * @return the create time top
     */
    @Override
    public List<Article> getCreateTimeTop(int topNum) {
        DBObject dbObject = new BasicDBObject();

        DBObject fieldObject = new BasicDBObject();

        fieldObject.put("createTime", true);

        fieldObject.put("name", true);

        Query query = new BasicQuery(dbObject, fieldObject);
        query.with(new Sort(new Order(Direction.DESC, "createTime")));
        query.limit(topNum);

        return this.mongoTemplate.find(query, Article.class);
    }

    /**
     * Gets correlation top.
     *
     * @param topNum the top num
     * @return the correlation top
     */
    @Override
    public List<Article> getCorrelationTop(int topNum) {

        // todo 暂时没用，并且写错了
        DBObject dbObject = new BasicDBObject();
        DBObject fieldObject = new BasicDBObject();

        fieldObject.put("createTime", true);

        fieldObject.put("name", true);

        Query query = new BasicQuery(dbObject, fieldObject);

        query.with(new Sort(new Order(Direction.ASC, "")));
        query.limit(topNum);
        return this.mongoTemplate.find(query, Article.class);
    }

    /**
     * Gets name and tags by ids.
     *
     * @param ids the ids
     * @return the name and tags by ids
     */
    @Override
    public List<Article> getNameAndTagsByIds(List<Long> ids) {
        return articleRepository.findNameAndTagsByIds(ids);
    }
}