package cn.fanhub.fxblogui.util;

import cn.fanhub.fxblogui.entity.SequenceInfo;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

/**
 * @author chengfan
 * @date 2018-2-8 22:19:00
 */
public class IdUtil {
    /**
     * 获取下一个自增ID
     *
     * @param collName
     *            集合（这里用类名，就唯一性来说最好还是存放长类名）名称
     * @return 序列值
     */
    public static Long getNextIdAndUpdate(String collName, MongoTemplate mongo) {
        Query query = new Query(Criteria.where("collName").is(collName));
        Update update = new Update();
        update.inc("seqId", 1);
        FindAndModifyOptions options = new FindAndModifyOptions();
        options.upsert(true);
        options.returnNew(true);
        SequenceInfo seq = mongo.findAndModify(query, update, options, SequenceInfo.class);
        return seq.getSeqId();
    }

    public static Long getNextId(String collName, MongoTemplate mongo) {
        Query query = new Query(Criteria.where("collName").is(collName));
        SequenceInfo seq = mongo.findOne(query, SequenceInfo.class);
        return seq == null ? 1 : seq.getSeqId();
    }
}