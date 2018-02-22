package cn.fanhub.fxblogui.model;

import cn.fanhub.fxblogui.entity.Categories;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class AdminCategoriesSelectVO {
    private String value;
    private String label;
    private List<AdminCategoriesSelectVO> children = new ArrayList<>();

    public static AdminCategoriesSelectVO convertFromCategories(Categories categories) {
        AdminCategoriesSelectVO categoriesSelectVO = new AdminCategoriesSelectVO();
        categoriesSelectVO.setValue(categories.getName());
        categoriesSelectVO.setLabel(categories.getName());
        categoriesSelectVO.setChildren(
                categories.getChildren()
                    .stream()
                    .map(AdminCategoriesSelectVO::convertFromCategories)
                    .collect(Collectors.toList()));

        return categoriesSelectVO;
    }
}