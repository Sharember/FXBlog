package cn.fanhub.fxblogui.repository;

import cn.fanhub.fxblogui.entity.Categories;

/**
 * @author chengfan
 * @date 2018-2-8 22:18:01
 */
public interface CategoriesRepository extends BaseRepository<Categories,Long> {
    /**
     * Gets categories by name.
     *
     * @param name the name
     * @return the categories by name
     */
    Categories getCategoriesByName(String name);
}