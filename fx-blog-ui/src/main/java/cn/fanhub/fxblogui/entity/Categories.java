package cn.fanhub.fxblogui.entity;

import cn.fanhub.fxblogui.annotation.AutoInc;
import cn.fanhub.fxblogui.annotation.CreateTime;
import cn.fanhub.fxblogui.annotation.UpdateTime;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@ToString
public class Categories {

    @AutoInc
    @Id
    @Field("_id")
    private long id;

    @CreateTime
    private Date createTime;

    @UpdateTime
    private Date updateTime;

    private String author;

    private String name;

    private List<Long> articles = new ArrayList<>();
}