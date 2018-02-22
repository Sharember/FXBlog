package cn.fanhub.fxblogui.service;

import cn.fanhub.fxblogui.entity.Tag;

import java.util.List;

/**
 * @author chengfan
 * @date 2018-2-8 22:18:53
 */
public interface TagService extends BaseService<Tag, Long> {

    List<Tag> getAllTagName();
}