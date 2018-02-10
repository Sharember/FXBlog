package cn.fanhub.fxblogui;

import cn.fanhub.fxblogui.entity.Menu;
import cn.fanhub.fxblogui.service.MenuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FxBlogServerApplicationTests {
	@Autowired
	private MenuService menuService;

	@Test
	public void testMenu() {
		Menu menu = new Menu();
		menu.setUrl("menu");
		menu.setName("menu");

		Menu subMenu = new Menu();
		subMenu.setUrl("menu2");
		subMenu.setName("menu2");

		menu.setMenus(Collections.singletonList(subMenu));


		menuService.save(subMenu);
		menuService.save(menu);
	}

}
