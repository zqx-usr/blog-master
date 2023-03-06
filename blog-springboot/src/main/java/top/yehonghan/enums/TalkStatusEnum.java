package top.yehonghan.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ClassName: TalkStatusEnum
 * Description: 说说状态枚举
 *
 * @Author: yehonghan
 * @Create: 2023/1/4 16:31
 * @Version: V1.0
 */
@Getter
@AllArgsConstructor
public enum TalkStatusEnum {
    /**
     * 公开
     */
    PUBLIC(1, "公开"),
    /**
     * 私密
     */
    SECRET(2, "私密");

    /**
     * 状态
     */
    private final Integer status;

    /**
     * 描述
     */
    private final String desc;

}
