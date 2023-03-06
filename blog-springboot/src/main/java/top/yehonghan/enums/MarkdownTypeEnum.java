package top.yehonghan.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ClassName: MarkdownTypeEnum
 * Description: Markdown文章类型枚举
 *
 * @Author: yehonghan
 * @Create: 2023/1/4 16:26
 * @Version: V1.0
 */
@Getter
@AllArgsConstructor
public enum MarkdownTypeEnum {
    /**
     * 普通文章
     */
    NORMAL("", "normalArticleImportStrategyImpl"),
    /**
     * Hexo文章
     */
    HEXO("hexo", "hexoArticleImportStrategyImpl");

    /**
     * 类型
     */
    private final String type;

    /**
     * 策略
     */
    private final String strategy;

    public static String getMarkdownType(String name) {
        if (name == null) {
            return NORMAL.getStrategy();
        }
        for (MarkdownTypeEnum value : MarkdownTypeEnum.values()) {
            if (value.getType().equalsIgnoreCase(name)) {
                return value.getStrategy();
            }
        }
        return null;
    }
}

