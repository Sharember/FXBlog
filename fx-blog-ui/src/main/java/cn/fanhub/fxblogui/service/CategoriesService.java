package cn.fanhub.fxblogui.service;

import cn.fanhub.fxblogui.entity.Categories;

import java.util.List;

public interface CategoriesService {
    Categories save(Categories categories);

    <S extends Categories> Iterable<S> save(Iterable<S> var1);

    Categories update(Categories categories);

    void delete(Categories categories);

    List<Categories> getList();

    Categories getByName(String name);
}