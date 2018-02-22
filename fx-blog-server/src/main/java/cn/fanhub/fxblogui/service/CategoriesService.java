package cn.fanhub.fxblogui.service;

import cn.fanhub.fxblogui.entity.Categories;
import cn.fanhub.fxblogui.model.AdminCategoriesSelectVO;

import java.util.List;

/**
 * @author chengfan
 * @date 2018-2-8 22:18:50
 */
public interface CategoriesService extends BaseService<Categories, Long> {

    List<AdminCategoriesSelectVO> getAdminCategoriesSelectVO();
}