package cn.fanhub.fxblogui.entity;

import cn.fanhub.fxblogui.annotation.AutoInc;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * @author chengfan
 * @date 2018-2-8 22:17:25
 */
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