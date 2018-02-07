package cn.fanhub.fxblogui.repository;

import cn.fanhub.fxblogui.entity.Discuss;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DiscussRepository extends MongoRepository<Discuss,String> {
}