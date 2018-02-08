package cn.fanhub.fxblogui.repository;

import cn.fanhub.fxblogui.entity.Visitor;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author chengfan
 * @date 2018-2-8 22:18:15
 */
public interface VisitorRepository extends MongoRepository<Visitor,String> {
}