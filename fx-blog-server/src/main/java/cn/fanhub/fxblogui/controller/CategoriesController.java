package cn.fanhub.fxblogui.controller;

import cn.fanhub.fxblogui.entity.Categories;
import cn.fanhub.fxblogui.model.AdminCategoriesSelectVO;
import cn.fanhub.fxblogui.model.Result;
import cn.fanhub.fxblogui.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author chengfan
 * @date 2018-2-8 22:16:22
 */
@RequestMapping("/categories")
@RestController
public class CategoriesController extends BaseCRUDController<Categories, Long> {

    @Autowired
    private CategoriesService categoriesService;

    @GetMapping("/select")
    public Result<List<AdminCategoriesSelectVO>> getAdminCategoriesSelectVO() {
        return Result.of(categoriesService.getAdminCategoriesSelectVO());
    }
}