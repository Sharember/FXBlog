package cn.fanhub.fxblogui.controller;

import cn.fanhub.fxblogui.model.Result;
import cn.fanhub.fxblogui.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.io.Serializable;
import java.util.List;

/**
 * 默认引入一个 service, 使用 manager 的 controller 不能使用该类
 * @author chengfan
 * @date 2018-2-8 22:03:40
 */
public abstract class BaseCRUDController <T, ID extends Serializable>{

    @Autowired
    protected BaseService<T, ID> baseService;

    /**
     * Gets article.
     *
     * @param id the id
     * @return the article
     */
    @GetMapping("/{id}")
    public Result<T> getArticle(@PathVariable ID id) {
        return Result.of(baseService.getOne(id));
    }

    /**
     * Gets all.
     *
     * @return the all
     */
    @GetMapping
    public Result<List<T>> getAll() {
        return Result.of(baseService.getAll());
    }

    /**
     * Update result.
     *
     * @param t the t
     * @return the result
     */
    @PutMapping
    public Result<T> update(T t) {
        return Result.of(baseService.update(t));
    }

    /**
     * Add result.
     *
     * @param t the t
     * @return the result
     */
    @PostMapping
    public Result<T> add(T t) {
        return Result.of(baseService.save(t));
    }

    /**
     * Delete result.
     *
     * @param t the t
     * @return the result
     */
    @DeleteMapping
    public Result delete(T t) {
        baseService.delete(t);
        return Result.success();
    }
}