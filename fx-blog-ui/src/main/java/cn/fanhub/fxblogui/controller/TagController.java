package cn.fanhub.fxblogui.controller;

import cn.fanhub.fxblogui.entity.Tag;
import cn.fanhub.fxblogui.model.Result;
import cn.fanhub.fxblogui.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/tag")
@RestController
public class TagController {

    @Autowired
    private TagService TagService;

    @GetMapping
    public Result<List<Tag>> getAll() {
        return Result.of(TagService.getList());
    }

    @GetMapping
    public Result<Tag> update(Tag Tag) {
        return Result.of(TagService.update(Tag));
    }

    @PostMapping
    public Result<Tag> add(Tag Tag) {
        return Result.of(TagService.save(Tag));
    }

    @DeleteMapping
    public Result delete(Tag Tag) {
        TagService.delete(Tag);
        return Result.success();
    }
}