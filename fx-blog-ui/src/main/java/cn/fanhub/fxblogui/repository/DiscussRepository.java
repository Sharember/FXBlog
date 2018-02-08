package cn.fanhub.fxblogui.repository;

import cn.fanhub.fxblogui.entity.Discuss;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author chengfan
 * @date 2018-2-8 22:18:06
 */
public interface DiscussRepository extends MongoRepository<Discuss,String> {
}