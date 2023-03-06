package top.yehonghan.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;

import static top.yehonghan.enums.ZoneEnum.SHANGHAI;

/**
 * ClassName: MybatisPlusMetaObjectHandler
 * Description: MybatisPlus数据自动填充
 *
 * @Author: yehonghan
 * @Create: 2023/1/4 11:01
 * @Version: V1.0
 */
@Log4j2
@Component
public class MybatisPlusMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now(ZoneId.of(SHANGHAI.getZone())));
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now(ZoneId.of(SHANGHAI.getZone())));
    }
}
