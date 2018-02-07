package cn.fanhub.fxblogui.service;

import cn.fanhub.fxblogui.entity.Tag;

import java.util.List;

public interface TagService {
    Tag save(Tag tag);

    <S extends Tag> Iterable<S> save(Iterable<S> iterable);

    Tag update(Tag tag);

    void delete(Tag tag);

    List<Tag> getList();

    Tag getByName(String name);
}