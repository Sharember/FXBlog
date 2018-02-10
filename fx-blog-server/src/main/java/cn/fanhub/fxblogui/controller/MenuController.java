package cn.fanhub.fxblogui.controller;

import cn.fanhub.fxblogui.entity.Menu;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menu")
public class MenuController extends BaseCRUDController<Menu, Long> {
}