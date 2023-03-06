package top.yehonghan.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ClassName: UploadMethodEnum
 * Description: 上传方式枚举
 *
 * @Author: yehonghan
 * @Create: 2023/1/4 16:32
 * @Version: V1.0
 */
@Getter
@AllArgsConstructor
public enum UploadMethodEnum {
    /**
     * oss
     */
    OSS("oss", "ossUploadStrategyImpl"),
    /**
     * 本地
     */
    LOCAL("local", "localUploadStrategyImpl"),

    /**
     * cos
     */
    COS("cos", "cosUploadStrategyImpl");

    /**
     * 模式
     */
    private final String mode;

    /**
     * 策略
     */
    private final String strategy;

    /**
     * 获取策略
     *
     * @param mode 模式
     * @return {@link String} 搜索策略
     */
    public static String getStrategy(String mode) {
        for (UploadMethodEnum value : UploadMethodEnum.values()) {
            if (value.getMode().equals(mode)) {
                return value.getStrategy();
            }
        }
        return null;
    }

}
