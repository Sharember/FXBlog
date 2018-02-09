package cn.fanhub.fxblogui.controller;

import cn.fanhub.fxblogui.entity.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chengfan
 * @date 2018-2-8 22:16:25
 */
@RequestMapping("/tag")
@RestController
public class TagController extends BaseCRUDController<Tag, Long> {

}