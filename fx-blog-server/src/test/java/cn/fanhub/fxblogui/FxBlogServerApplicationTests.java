package cn.fanhub.fxblogui;

import cn.fanhub.fxblogui.entity.Article;
import cn.fanhub.fxblogui.entity.Categories;
import cn.fanhub.fxblogui.entity.Discuss;
import cn.fanhub.fxblogui.entity.Menu;
import cn.fanhub.fxblogui.entity.Tag;
import cn.fanhub.fxblogui.manager.ArticleManger;
import cn.fanhub.fxblogui.repository.ArticleRepository;
import cn.fanhub.fxblogui.repository.CategoriesRepository;
import cn.fanhub.fxblogui.service.ArticleService;
import cn.fanhub.fxblogui.service.MenuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FxBlogServerApplicationTests {
	@Autowired
	private MenuService menuService;

	@Autowired
	private ArticleManger articleManger;

	@Autowired
	private ArticleService articleService;

	@Autowired
	private ArticleRepository articleRepository;

	@Autowired
	private CategoriesRepository categoriesRepository;


	@Test
	public void testMenu() {
		Menu menu = new Menu();
		menu.setPath("/tag/name/study");
		menu.setName("study");

		Menu subMenu = new Menu();
		subMenu.setPath("/tag/name/java");
		subMenu.setName("java");

		menu.setChildren(Collections.singletonList(subMenu));

		Menu menu1 = new Menu();
		menu1.setPath("/categories/name/java");
		menu1.setName("java");

		Menu subMenu1 = new Menu();
		subMenu1.setPath("/categories/name/spring");
		subMenu1.setName("spring");
		menu1.setChildren(Collections.singletonList(subMenu1));

		//menuService.save(subMenu);
		menuService.save(menu);
		menuService.save(menu1);


	}
	
	@Test
	public void testArticle() {
		Tag tag = new Tag();
		tag.setName("study");
		Tag tag2 = new Tag();
		tag2.setName("java");
		Categories categories = new Categories();
		categories.setName("java");

		Categories categories2 = new Categories();
		categories2.setName("spring");
		Categories sub = new Categories();
		sub.setName("aop");
		//Categories sub2 = new Categories();
		//sub2.setName("ioc");

		categories2.setChildren(Collections.singletonList(sub));

		Discuss discuss = new Discuss();
		discuss.setContent("hhh");
		discuss.setIp("localhost");
		for (int i = 0; i < 20; i++) {
			Article article = new Article();

			article.setName("test" + i);
			article.setTags(Arrays.asList(tag, tag2));
			article.setCategories(categories2);
			article.setDiscusses(Collections.singletonList(discuss));
			article.setUrl("/test" + i);
			article.setVisitNum(10);
			article.setLikeNum(20);
			article.setDigest("i am zhai yao" + i);
			article.setFirstImgUrl("https://gw.alipayobjects.com/zos/rmsportal/mqaQswcyDLcXyDKnZfES.png");
			article.setContent("## 问题描述\n"
					+ "\n"
					+ "我们有一个需求，需要根据 spring bean 获取到它的接口然后做一些操作，大致逻辑如下：\n"
					+ "\n"
					+ "```java\n"
					+ "for (Map.Entry<String, Object> entry : serviceBeans.entrySet()) {\n"
					+ "    Object target = entry.getValue();\n"
					+ "    Class<?> ifClazz = null;\n"
					+ "    for (Class<?> clazz : target.getClass().getInterfaces()) {\n"
					+ "        if (clazz.getName().equals(entry.getKey())) {\n"
					+ "            ifClazz = clazz;\n"
					+ "            break;\n"
					+ "        }\n"
					+ "    }\n"
					+ "    if (null == ifClazz) {\n"
					+ "        throw new IllegalArgumentException(\"Registe The RPC Service(\"\n"
					+ "                                           + entry.getValue().getClass()\n"
					+ "                                           + \")has not implement\" + entry.getKey());\n"
					+ "    }\n"
					+ "    registerService(target, ifClazz);\n"
					+ "}\n"
					+ "\n"
					+ "```\n"
					+ "`serviceBeans` 是一个 map，key 为接口，value 为对应的 bean，因为 `registerService` 的特殊性，所以我们一定要保证该 bean 是实现了这个接口的。\n"
					+ "\n"
					+ "一般来说这样做是没有问题的，因为我们获取到的 value 本身就是 key 的一个实现类，类似于 `service` 和 `serviceImpl` 的关系。上面的写法甚至都有些多此一举。我们完全可以改成：\n"
					+ "\n"
					+ "```java\n"
					+ "for (Map.Entry<String, Object> entry : serviceBeans.entrySet()) {\n"
					+ "    registerService(entry.getValue(), entry.getKey());\n"
					+ "}\n"
					+ "\n"
					+ "```\n"
					+ "\n"
					+ "一般情况下这样也是不会有问题的，但是我们遇到了一个 bug。\n"
					+ "\n"
					+ "当 bean 同时被加上 aop 之后，并且是用 cglib 生成代理对象的时候，问题就来了。我们来看一下会有什么问题。\n"
					+ "\n"
					+ "- UseCglibAspect.java\n"
					+ "\n"
					+ "```java\n"
					+ "public class UseCglibAspect {\n"
					+ "    public void before() {\n"
					+ "        System.out.println(\"before\");\n"
					+ "    }\n"
					+ "}\n"
					+ "```\n"
					+ "\n"
					+ "- CglibService.java\n"
					+ "\n"
					+ "```java\n"
					+ "public interface CglibService {\n"
					+ "    String test();\n"
					+ "}\n"
					+ "```\n"
					+ "\n"
					+ "- CglibServiceImpl.java\n"
					+ "\n"
					+ "```java\n"
					+ "public class CglibServiceImpl implements CglibService {\n"
					+ "    @Override\n"
					+ "    public String test() {\n"
					+ "        return \"test\";\n"
					+ "    }\n"
					+ "}\n"
					+ "```\n"
					+ "- aop.xml\n"
					+ "\n"
					+ "```xml\n"
					+ "<beans xmlns=\"http://www.springframework.org/schema/beans\"\n"
					+ "       xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n"
					+ "       xmlns:aop=\"http://www.springframework.org/schema/aop\"\n"
					+ "       xsi:schemaLocation=\"http://www.springframework.org/schema/beans\n"
					+ "        http://www.springframework.org/schema/beans/spring-beans-3.0.xsd\n"
					+ "        http://www.springframework.org/schema/aop\n"
					+ "        http://www.springframework.org/schema/aop/spring-aop-3.0.xsd\"\n"
					+ "       default-autowire=\"byName\">\n"
					+ "\n"
					+ "    <aop:aspectj-autoproxy/>\n"
					+ "\n"
					+ "    <bean id=\"cglibService\" class=\"cn.fanhub.javayoushouldknow.spring.bean.impl.CglibServiceImpl\" />\n"
					+ "\n"
					+ "    <bean id=\"useCglibAspect\" class=\"cn.fanhub.javayoushouldknow.spring.aop.cglibProblem.UseCglibAspect\" />\n"
					+ "\n"
					+ "    <aop:config proxy-target-class=\"true\">\n"
					+ "        <aop:aspect id=\"cglib\" ref=\"useCglibAspect\">\n"
					+ "            "
					+
					"<aop:pointcut id=\"cglibtest\" expression=\"execution(* cn.fanhub.javayoushouldknow.spring.bean.impl.CglibServiceImpl.*(..))\" />\n"
					+ "            <aop:before pointcut-ref=\"cglibtest\" method=\"before\"/>\n"
					+ "        </aop:aspect>\n"
					+ "    </aop:config>\n"
					+ "\n"
					+ "</beans>\n"
					+ "```\n"
					+ "\n"
					+ "代码非常简单，就不细说了。写一个测试类来看一下会发生哪些问题。\n"
					+ "\n"
					+ "```java\n"
					+ "public class TestCglibProxy {\n"
					+ "    @Test\n"
					+ "    public void test(){\n"
					+ "        ApplicationContext context = new ClassPathXmlApplicationContext(\"classpath*:spring/aop.xml\");\n"
					+ "        CglibService cglibService = (CglibService) context.getBean(\"cglibService\");\n"
					+ "        System.err.println(cglibService.getClass().getName());\n"
					+ "        Class<?>[] interfaces = cglibService.getClass().getInterfaces();\n"
					+ "\n"
					+ "        System.err.println(\"+-----------------------cglibService----------------------------------+\");\n"
					+ "        for (Class<?> anInterface : interfaces) {\n"
					+ "            System.err.println(anInterface);\n"
					+ "        }\n"
					+ "    }\n"
					+ "}\n"
					+ "```\n"
					+ "\n"
					+ "我们试着拿一下 bean 的所有实现接口，按照正常的情况，这个时候应该可以拿到 `CglibService` 接口。但是事实上，我们得到的结果是这样的：\n"
					+ "\n"
					+ "```java\n"
					+ "cn.fanhub.javayoushouldknow.spring.bean.impl.CglibServiceImpl$$EnhancerBySpringCGLIB$$5567a513\n"
					+ "+-----------------------cglibService----------------------------------+\n"
					+ "interface org.springframework.aop.SpringProxy\n"
					+ "interface org.springframework.aop.framework.Advised\n"
					+ "interface org.springframework.cglib.proxy.Factory\n"
					+ "```\n"
					+ "\n"
					+ "我们并没有像预期那样拿到 `CglibService` 这个接口，反而拿到了三个奇怪的接口。\n"
					+ "\n"
					+ "\n"
					+ "## 问题探究\n"
					+ "\n"
					+ "为什么会这样呢？看上面的结果，我们会发现，除了三个奇怪的接口外，bean 的类名也很奇怪：`CglibServiceImpl$$EnhancerBySpringCGLIB$$5567a513`, 问题应该就出现在这里。\n"
					+ "\n"
					+ "从这个类名我们可以推测，这个类和 SpringCGLIB 有关。回忆一下 spring aop 的相关知识：\n"
					+ "\n"
					+ "当使用 aop 的时候，spring 会为代理的目标对象生成一个代理类，而生成代理类有两种方法：jdk 动态代理和 cglib 动态代理。\n"
					+ "\n"
					+ "jdk 动态代理：生成的代理类是接口的一个实现类。在本例中就是 `CglibService` 的一个实现类。\n"
					+ "cglib 动态代理： 生成的代理类是目标对象的一个子类。在本例中就是 `CglibServiceImpl` 的一个子类。\n"
					+ "\n"
					+ "由于我们强制 aop 使用 cglib 来生成代理对象，所以我们拿到的其实是 `CglibServiceImpl` 的一个子类，这个子类并没有实现 `CglibService` 接口，所以我们当然取不到它。\n"
					+ "\n"
					+ "**aop 什么时候会使用 cglib 动态代理，什么时候会使用 jdk 动态代理？**\n"
					+ "\n"
					+ "1、如果目标对象实现了接口，默认情况下会采用 JDK 的动态代理实现 AOP \n"
					+ "2、如果目标对象实现了接口，可以强制使用 CGLIB 实现 AOP（设置 proxy-target-class=\"true\" 即可）\n"
					+ "3、如果目标对象没有实现了接口，必须采用 CGLIB 库，spring 会自动在 JDK 动态代理和 CGLIB 之间转换\n"
					+ "\n"
					+ "看一下相关的源码：\n"
					+ "\n"
					+ "```java\n"
					+ "  \n"
					+ "public AopProxy createAopProxy(AdvisedSupport advisedSupport)   throws AopConfigException {   \n"
					+ "\n"
					+ "    //在此判断使用JDK动态代理还是CGLIB代理   \n"
					+ "\n"
					+ "    if (advisedSupport.isOptimize() || advisedSupport.isProxyTargetClass() || hasNoUserSuppliedProxyInterfaces"
					+ "(advisedSupport)) {   \n"
					+ "        if (!cglibAvailable) {   \n"
					+ "            throw new AopConfigException(   \n"
					+ "                    \"Cannot proxy target class because CGLIB2 is not available. \"  \n"
					+ "                            + \"Add CGLIB to the class path or specify proxy interfaces.\");   \n"
					+ "        }   \n"
					+ "        return CglibProxyFactory.createCglibProxy(advisedSupport);   \n"
					+ "\n"
					+ "    } else {   \n"
					+ "        return new JdkDynamicAopProxy(advisedSupport);   \n"
					+ "    }   \n"
					+ "}\n"
					+ "\n"
					+ "```\n"
					+ "\n"
					+ "关键代码是:`if (advisedSupport.isOptimize() || advisedSupport.isProxyTargetClass() || hasNoUserSuppliedProxyInterfaces"
					+ "(advisedSupport))`, 这里有三个判断条件，如果三个条件有一个满足的话，就使用 cglib 生成代理对象，否则使用 jdk 生成代理对象。\n"
					+ "\n"
					+ "- advisedSupport.isOptimize(): 是否启用优化，默认为 false。（cglib 在生成）\n"
					+ "- advisedSupport.isProxyTargetClass(): 是否对 class 进行代理，默认为 false。\n"
					+ "- hasNoUserSuppliedProxyInterfaces(advisedSupport): 这个类有没有实现接口，没有则为 true。\n"
					+ "\n"
					+ "> 因为 jdk 动态代理只能针对接口，而不能针对类，所以如果一个类没有实现任何接口，则只能使用 cglib。更多差异及细节请查阅 aop 相关知识。\n"
					+ "\n"
					+ "\n"
					+ "## 问题解决\n"
					+ "\n"
					+ "那么，我们怎么解决这个问题呢？\n"
					+ "\n"
					+ "我们要根据 `CglibServiceImpl$$EnhancerBySpringCGLIB$$5567a513` 来找到 `CglibServiceImpl`。可以调试一下，看看这个奇怪的类是什么结构: \n"
					+ "\n"
					+ "> ![image.png](http://upload-images.jianshu"
					+ ".io/upload_images/4047674-d586fb8e8a5da4e3.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)\n"
					+ "\n"
					+ "原来在这里！根据这个结构，我们可以通过反射来拿到 `CglibServiceImpl` ：\n"
					+ "\n"
					+ "```java\n"
					+ "    private Object getResourceTarget(Object beanInstance) {\n"
					+ "        if (AopUtils.isCglibProxy(beanInstance)) {\n"
					+ "            try {\n"
					+ "                Field h = beanInstance.getClass().getDeclaredField(\"CGLIB$CALLBACK_0\");\n"
					+ "                h.setAccessible(true);\n"
					+ "                Object dynamicAdvisedInterceptor = h.get(beanInstance);\n"
					+ "\n"
					+ "                Field advised = dynamicAdvisedInterceptor.getClass().getDeclaredField(\"advised\");\n"
					+ "                advised.setAccessible(true);\n"
					+ "\n"
					+ "                Object target = ((AdvisedSupport) advised.get(dynamicAdvisedInterceptor)).getTargetSource()"
					+ ".getTarget();\n"
					+ "                return target;\n"
					+ "            } catch (NoSuchFieldException e) {\n"
					+ "                e.printStackTrace();\n"
					+ "            } catch (IllegalAccessException e) {\n"
					+ "                e.printStackTrace();\n"
					+ "            } catch (Exception e) {\n"
					+ "                e.printStackTrace();\n"
					+ "            }\n"
					+ "\n"
					+ "        }\n"
					+ "        return beanInstance;\n"
					+ "    }\n"
					+ "```\n"
					+ "\n"
					+ "修改一下测试代码：\n"
					+ "\n"
					+ "```java\n"
					+ "public class TestCglibProxy {\n"
					+ "    @Test\n"
					+ "    public void test(){\n"
					+ "        ApplicationContext context = new ClassPathXmlApplicationContext(\"classpath*:spring/aop.xml\");\n"
					+ "        CglibService cglibService = (CglibService) context.getBean(\"cglibService\");\n"
					+ "        System.err.println(cglibService.getClass().getName());\n"
					+ "        Class<?>[] interfaces = cglibService.getClass().getInterfaces();\n"
					+ "\n"
					+ "        System.err.println(\"+-----------------------cglibService----------------------------------+\");\n"
					+ "        for (Class<?> anInterface : interfaces) {\n"
					+ "            System.err.println(anInterface);\n"
					+ "        }\n"
					+ "        \n"
					+ "        System.err.println(\"+-----------------------cglibService resource----------------------------------+\");\n"
					+ "        interfaces = getResourceTarget(cglibService).getClass().getInterfaces();\n"
					+ "        for (Class<?> anInterface : interfaces) {\n"
					+ "            System.err.println(anInterface);\n"
					+ "        }\n"
					+ "    }\n"
					+ "}\n"
					+ "```\n"
					+ "\n"
					+ "结果如下：\n"
					+ "\n"
					+ "```java\n"
					+ "cn.fanhub.javayoushouldknow.spring.bean.impl.CglibServiceImpl$$EnhancerBySpringCGLIB$$b50f26e\n"
					+ "+-----------------------cglibService----------------------------------+\n"
					+ "interface org.springframework.aop.SpringProxy\n"
					+ "interface org.springframework.aop.framework.Advised\n"
					+ "interface org.springframework.cglib.proxy.Factory\n"
					+ "+-----------------------cglibService resource----------------------------------+\n"
					+ "interface cn.fanhub.javayoushouldknow.spring.bean.CglibService\n"
					+ "```\n"
					+ "\n"
					+ "可以看到，我们成功通过 `getResourceTarget` 拿到了 `CglibServiceImpl`。\n"
					+ "\n"
					+ "除了上面的方法，有没有其他的方法呢？我们上面说过，jdk 动态代理生成的类是接口的实现类，而 cglib "
					+ "动态代理生成的类是源实现类的子类。也就是说，`CglibServiceImpl$$EnhancerBySpringCGLIB$$5567a513` 其实是 `CglibServiceImpl` "
					+ "的一个子类，那问题就好办了，我们可以这样做：\n"
					+ "\n"
					+ "```java\n"
					+ "System.err.println(\"+-----------------------cglibService resource method2----------------------------------+\");\n"
					+ "interfaces = cglibService.getClass().getSuperclass().getInterfaces();\n"
					+ "for (Class<?> anInterface : interfaces) {\n"
					+ "    System.err.println(anInterface);\n"
					+ "}\n"
					+ "```\n"
					+ "\n"
					+ "将上面的代码加入到测试类中看一下结果：\n"
					+ "\n"
					+ "```java\n"
					+ "cn.fanhub.javayoushouldknow.spring.bean.impl.CglibServiceImpl$$EnhancerBySpringCGLIB$$b50f26e\n"
					+ "+-----------------------cglibService----------------------------------+\n"
					+ "interface org.springframework.aop.SpringProxy\n"
					+ "interface org.springframework.aop.framework.Advised\n"
					+ "interface org.springframework.cglib.proxy.Factory\n"
					+ "+-----------------------cglibService resource----------------------------------+\n"
					+ "interface cn.fanhub.javayoushouldknow.spring.bean.CglibService\n"
					+ "+-----------------------cglibService resource method2----------------------------------+\n"
					+ "interface cn.fanhub.javayoushouldknow.spring.bean.CglibService\n"
					+ "```\n"
					+ "\n"
					+ "很好，我们同样拿到了 `CglibService` 接口。\n"
					+ "\n"
					+ "> 测试代码：[点击查看](https://github.com/CFshuming/JavaYouShouldKnow/blob/master/src/test/java/cn/fanhub/javayoushouldknow"
					+ "/spring/aop/cglibProblem/TestCglibProxy.java)，测试代码包括了 jdk 动态代理，cglib 动态代理以及普通 bean 的对比。\n"
					+ "\n"
					+ "\n");

			articleManger.saveArticle(article);
		}
	}

	@Test
	public void testMongo () {
		//List<Article> byIds = articleRepository.findNameAndTagsByIds(Arrays.asList(1L, 2L));
		//System.out.println();

		//for (Article article : articleService.getCreateTimeTop(5)) {
		//	System.out.println(article.getName());
		//}

		Article article = new Article();

		article.setName("testtest");
		article.setId(4);
		Tag tag2 = new Tag();
		tag2.setName("hhhhhhhh");
		article.getTags().add(tag2);

		articleManger.updateTags(article);
	}

}
