package cn.fanhub.fxblogui.repository;

import cn.fanhub.fxblogui.entity.Tag;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TagRepository extends MongoRepository<Tag,String> {

    Tag getTagByName(String name);
}