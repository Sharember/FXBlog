package cn.fanhub.fxblogui;

import cn.fanhub.fxblogui.entity.Article;
import cn.fanhub.fxblogui.entity.Categories;
import cn.fanhub.fxblogui.entity.Discuss;
import cn.fanhub.fxblogui.entity.Menu;
import cn.fanhub.fxblogui.entity.Tag;
import cn.fanhub.fxblogui.service.ArticleService;
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

	@Autowired
	private ArticleService articleService;

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
	
	@Test
	public void testArticle() {
		Tag tag = new Tag();
		tag.setName("study");
		Categories categories = new Categories();
		categories.setName("java");
		Discuss discuss = new Discuss();
		discuss.setContent("hhh");
		discuss.setIp("localhost");
		for (int i = 0; i < 20; i++) {
			Article article = new Article();

			article.setName("test" + i);
			article.setTags(Collections.singletonList(tag));
			article.setCategories(Collections.singletonList(categories));
			article.setDiscusses(Collections.singletonList(discuss));
			article.setUrl("/test" + i);
			article.setVisitNum(10);
			article.setLikeNum(20);
			article.setDigest("i am zhai yao" + i);
			article.setFirstImgUrl("https://gw.alipayobjects.com/zos/rmsportal/mqaQswcyDLcXyDKnZfES.png");
			article.setContent("content" + i);

			articleService.save(article);
		}
	}

}
