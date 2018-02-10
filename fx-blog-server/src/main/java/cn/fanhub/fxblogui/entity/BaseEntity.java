package cn.fanhub.fxblogui.entity;

import cn.fanhub.fxblogui.annotation.AutoInc;
import cn.fanhub.fxblogui.annotation.CreateTime;
import cn.fanhub.fxblogui.annotation.UpdateTime;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Data
public abstract class BaseEntity {
    @AutoInc
    @Id
    @Field("_id")
    private long id;

    @CreateTime
    private Date createTime;

    @UpdateTime
    private Date updateTime;

}