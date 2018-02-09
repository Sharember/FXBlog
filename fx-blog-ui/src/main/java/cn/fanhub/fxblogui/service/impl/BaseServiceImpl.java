package cn.fanhub.fxblogui.service.impl;

import cn.fanhub.fxblogui.repository.BaseRepository;
import cn.fanhub.fxblogui.service.BaseService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

/**
 * @author chengfan
 * @date 2018-2-8 22:18:24
 */
public class BaseServiceImpl<T, ID extends Serializable> implements BaseService<T, ID>{

    @Autowired
    private BaseRepository<T, ID> baseRepository;

    /**
     * Save t.
     *
     * @param t the t
     * @return the t
     */
    @Override
    public T save(T t) {
        return baseRepository.save(t);
    }

    /**
     * Save iterable.
     *
     * @param <S>  the type parameter
     * @param iterable the iterable
     * @return the iterable
     */
    @Override
    public <S extends T> Iterable<S> save(Iterable<S> iterable) {
        return baseRepository.save(iterable);
    }

    /**
     * Update t.
     *
     * @param t the t
     * @return the t
     */
    @Override
    public T update(T t) {
        return baseRepository.save(t);
    }

    /**
     * Delete.
     *
     * @param t the t
     */
    @Override
    public void delete(T t) {
        baseRepository.delete(t);
    }

    /**
     * Gets by name.
     *
     * @param name the name
     * @return the by name
     */
    @Override
    public T getByName(String name) {
        return baseRepository.getByName(name);
    }

    /**
     * Gets one.
     *
     * @param id the id
     * @return the one
     */
    @Override
    public T getOne(ID id) {
        return baseRepository.findOne(id);
    }

    @Override
    public List<T> getAll() {
        return baseRepository.findAll();
    }

    @Override
    public List<T> getAll(List<ID> ids) {
        List<T> list = Lists.newArrayList();
        baseRepository.findAll(ids).forEach(list::add);
        return list;
    }
}