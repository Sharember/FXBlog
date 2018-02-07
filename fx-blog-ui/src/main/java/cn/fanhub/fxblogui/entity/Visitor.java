package cn.fanhub.fxblogui.entity;

import cn.fanhub.fxblogui.annotation.AutoInc;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Data
public class Visitor {
    @AutoInc
    @Id
    @Field("_id")
    private long id;

    @Field
    private String ip;

    @Field
    private Date visitDate;
}