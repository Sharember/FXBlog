package cn.fanhub.fxblogui.repository;

import cn.fanhub.fxblogui.entity.Categories;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoriesRepository extends MongoRepository<Categories,String> {
    Categories getCategoriesByName(String name);
}