package cn.fanhub.fxblogui.service.impl;

import cn.fanhub.fxblogui.entity.Tag;
import cn.fanhub.fxblogui.repository.TagRepository;
import cn.fanhub.fxblogui.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Override
    public Tag save(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public <S extends Tag> Iterable<S> save(Iterable<S> iterable) {
        return tagRepository.save(iterable);
    }

    @Override
    public Tag update(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public void delete(Tag tag) {
        tagRepository.delete(tag);
    }

    @Override
    public List<Tag> getList() {
        return tagRepository.findAll();
    }

    @Override
    public Tag getByName(String name) {
        return tagRepository.getTagByName(name);
    }
}