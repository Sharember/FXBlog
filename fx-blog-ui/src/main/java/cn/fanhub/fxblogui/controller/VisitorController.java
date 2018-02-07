package cn.fanhub.fxblogui.controller;

import cn.fanhub.fxblogui.entity.Article;
import cn.fanhub.fxblogui.entity.Categories;
import cn.fanhub.fxblogui.entity.Tag;
import cn.fanhub.fxblogui.entity.Visitor;
import cn.fanhub.fxblogui.repository.VisitorRepository;
import cn.fanhub.fxblogui.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/visit")
@RestController
public class VisitorController {
    @Resource
    private VisitorRepository visitorRepository;

    @Autowired
    private ArticleService articleService;

    @GetMapping
    public String visit(HttpServletRequest request){
        Visitor visitor = new Visitor();
        //visitor.setId(UUID.randomUUID().toString());
        visitor.setIp(request.getRemoteAddr());
        visitor.setVisitDate(new Date());

        visitorRepository.save(visitor);

        Long count = visitorRepository.count();

        return String.format("你是来自%s的第%d位访问者。",request.getRemoteAddr(),count);
    }

    @GetMapping("/article")
    public Article article(){
        Article article = new Article();
        List<Categories> list = new ArrayList<>();
        Categories categories = new Categories();
        categories.setName("test");
        list.add(categories);
        article.setCategories(list);

        List<Tag> tags = new ArrayList<>();
        Tag tag = new Tag();
        tag.setName("tag1");
        tags.add(tag);
        article.setTags(tags);

        article.setName("test");

        return articleService.save(article);

    }

    public static void main(String[] args) {
        Article article = new Article();
        List<Categories> list = new ArrayList<>();
        Categories categories = new Categories();
        categories.setName("test");
        list.add(categories);
        article.setCategories(list);

        List<Tag> tags = new ArrayList<>();
        Tag tag = new Tag();
        tag.setName("tag1");
        tags.add(tag);
        article.setTags(tags);

        article.setName("test");

        long id = 12l;
        for (Tag tag1 : article.getTags().stream().peek(stag -> stag.getArticles().add(id)).collect(Collectors.toList())) {
            System.out.println(tag1.getArticles());
        }
    }
}