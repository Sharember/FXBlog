package cn.fanhub.fxblogui.service.impl;

import cn.fanhub.fxblogui.entity.Tag;
import cn.fanhub.fxblogui.service.TagService;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chengfan
 * @date 2018-2-8 22:18:37
 */
@Service
public class TagServiceImpl extends BaseServiceImpl<Tag, Long> implements TagService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Tag> getAllTagName() {
        DBObject dbObject = new BasicDBObject();

        DBObject fieldObject = new BasicDBObject();

        fieldObject.put("name", true);
        fieldObject.put("articleNum", true);

        Query query = new BasicQuery(dbObject, fieldObject);
        query.with(new Sort(new Order(Direction.DESC, "articleNum")));
        return this.mongoTemplate.find(query, Tag.class);
    }
}