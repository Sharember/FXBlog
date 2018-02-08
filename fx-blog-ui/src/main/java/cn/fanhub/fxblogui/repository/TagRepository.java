package cn.fanhub.fxblogui.repository;

import cn.fanhub.fxblogui.entity.Tag;

/**
 * @author chengfan
 * @date 2018-2-8 22:18:10
 */
public interface TagRepository extends BaseRepository<Tag, Long> {

    /**
     * Gets tag by name.
     *
     * @param name the name
     * @return the tag by name
     */
    Tag getTagByName(String name);
}