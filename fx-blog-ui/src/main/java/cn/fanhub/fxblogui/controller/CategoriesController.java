package cn.fanhub.fxblogui.controller;

import cn.fanhub.fxblogui.entity.Categories;
import cn.fanhub.fxblogui.model.Result;
import cn.fanhub.fxblogui.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/categories")
@RestController
public class CategoriesController {

    @Autowired
    private CategoriesService categoriesService;

    @GetMapping
    public Result<List<Categories>> getAll() {
        return Result.of(categoriesService.getList());
    }

    @GetMapping
    public Result<Categories> update(Categories categories) {
        return Result.of(categoriesService.update(categories));
    }

    @PostMapping
    public Result<Categories> add(Categories categories) {
        return Result.of(categoriesService.save(categories));
    }

    @DeleteMapping
    public Result delete(Categories categories) {
        categoriesService.delete(categories);
        return Result.success();
    }
}