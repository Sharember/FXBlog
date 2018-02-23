package cn.fanhub.fxblogui.config;

import cn.fanhub.fxblogui.annotation.AutoInc;
import cn.fanhub.fxblogui.annotation.CreateTime;
import cn.fanhub.fxblogui.annotation.UpdateTime;
import cn.fanhub.fxblogui.util.IdUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.time.LocalDateTime;

@Component
public class SaveEventListener extends AbstractMongoEventListener<Object> {
    private final static Logger logger = LoggerFactory.getLogger(SaveEventListener.class);
    @Autowired
    private MongoTemplate mongo;

    /**
     * 设置自增 id 以及自动填写插入和修改时间
     *
     * @param event the event
     */
    @Override
    public void onBeforeConvert(BeforeConvertEvent<Object> event) {
        final Object source = event.getSource();
        if (source != null) {
            ReflectionUtils.doWithFields(source.getClass(), field -> {
                ReflectionUtils.makeAccessible(field);
                // 如果字段添加了我们自定义的AutoValue注解
                if (field.isAnnotationPresent(AutoInc.class) && field.get(source) instanceof Number
                        && field.getLong(source) == 0) {
                    // 判断注解的字段是否为number类型且值是否等于0.如果大于0说明有ID不需要生成ID
                    // 设置自增ID
                    field.set(source, IdUtil.getNextIdAndUpdate(source.getClass().getSimpleName(), mongo));
                    logger.debug("集合的ID为=======================" + source);
                }
                if (field.isAnnotationPresent(CreateTime.class) && field.get(source) == null) {
                    field.set(source, LocalDateTime.now());
                }
                if (field.isAnnotationPresent(UpdateTime.class)) {
                    field.set(source, LocalDateTime.now());

                }
            });
        }
    }
}