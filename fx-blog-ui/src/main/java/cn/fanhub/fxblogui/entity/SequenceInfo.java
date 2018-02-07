package cn.fanhub.fxblogui.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@ToString
public class SequenceInfo {
    @Id
    private String id;// 主键

    @Field
    private String collName;// 集合名称

    @Field
    private Long seqId;// 序列值
}