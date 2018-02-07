package cn.fanhub.fxblogui.repository;

import cn.fanhub.fxblogui.entity.Visitor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VisitorRepository extends MongoRepository<Visitor,String> {
}