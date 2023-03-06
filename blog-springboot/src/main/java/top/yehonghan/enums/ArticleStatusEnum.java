package top.yehonghan.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ClassName: ArticleStatusEnum
 * Description: 文章状态枚举
 *
 * @Author: yehonghan
 * @Create: 2023/1/4 16:19
 * @Version: V1.0
 */
@Getter
@AllArgsConstructor
public enum ArticleStatusEnum {

    /**
     * 公开
     */
    PUBLIC(1, "公开"),
    /**
     * 私密
     */
    SECRET(2, "私密"),
    /**
     * 草稿
     */
    DRAFT(3, "草稿");

    /**
     * 状态
     */
    private final Integer status;

    /**
     * 描述
     */
    private final String desc;

}
