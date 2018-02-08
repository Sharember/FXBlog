package cn.fanhub.fxblogui.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;
import java.util.List;

/**
 * <p>泛型 ： M 表示实体类型；ID表示主键类型
 * @author chengfan
 */
@NoRepositoryBean
public interface BaseRepository<M, ID extends Serializable> extends PagingAndSortingRepository<M, ID> {

    /**
     * Delete.
     *
     * @param id the id
     */
    void delete(ID id);

    /**
     * Find all list.
     *
     * @return the list
     */
    List<M> findAll();

    /**
     * Find all list.
     *
     * @param sort the sort
     * @return the list
     */
    List<M> findAll(Sort sort);

    /**
     * Find all page.
     *
     * @param pageable the pageable
     * @return the page
     */
    Page<M> findAll(Pageable pageable);

    /**
     * Save list.
     *
     * @param <S>  the type parameter
     * @param iterable the iterable
     * @return the list
     */
    <S extends M> List<S> save(Iterable<S> iterable);

    /**
     * Gets by name.
     *
     * @param name the name
     * @return the by name
     */
    M getByName(String name);
}