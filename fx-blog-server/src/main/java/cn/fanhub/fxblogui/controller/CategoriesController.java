package cn.fanhub.fxblogui.controller;

import cn.fanhub.fxblogui.entity.Categories;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chengfan
 * @date 2018-2-8 22:16:22
 */
@RequestMapping("/categories")
@RestController
public class CategoriesController extends BaseCRUDController<Categories, Long> {

}