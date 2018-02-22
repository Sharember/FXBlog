package cn.fanhub.fxblogui.service.impl;

import cn.fanhub.fxblogui.entity.Categories;
import cn.fanhub.fxblogui.model.AdminCategoriesSelectVO;
import cn.fanhub.fxblogui.service.CategoriesService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chengfan
 * @date 2018-2-8 22:18:33
 */
@Service
public class CategoriesServiceImpl extends BaseServiceImpl<Categories, Long> implements CategoriesService {

    @Override
    public List<AdminCategoriesSelectVO> getAdminCategoriesSelectVO() {
        return super.baseRepository.findAll().stream()
                .map(AdminCategoriesSelectVO::convertFromCategories)
                .collect(Collectors.toList());
    }
}