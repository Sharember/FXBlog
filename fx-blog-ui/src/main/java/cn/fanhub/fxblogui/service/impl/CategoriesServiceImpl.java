package cn.fanhub.fxblogui.service.impl;

import cn.fanhub.fxblogui.entity.Categories;
import cn.fanhub.fxblogui.repository.CategoriesRepository;
import cn.fanhub.fxblogui.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriesServiceImpl implements CategoriesService {

    @Autowired
    private CategoriesRepository categoriesRepository;

    @Override
    public Categories save(Categories categories) {
        return categoriesRepository.save(categories);
    }

    @Override
    public <S extends Categories> Iterable<S> save(Iterable<S> iterable) {
        return categoriesRepository.save(iterable);
    }

    @Override
    public Categories update(Categories categories) {
        return categoriesRepository.save(categories);
    }

    @Override
    public void delete(Categories categories) {
        categoriesRepository.delete(categories);
    }

    @Override
    public List<Categories> getList() {
        return categoriesRepository.findAll();
    }

    @Override
    public Categories getByName(String name) {
        return categoriesRepository.getCategoriesByName(name);
    }
}