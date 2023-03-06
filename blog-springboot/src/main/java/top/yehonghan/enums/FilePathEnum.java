package top.yehonghan.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ClassName: FilePathEnum
 * Description: 文件路径枚举
 *
 * @Author: yehonghan
 * @Create: 2023/1/4 16:23
 * @Version: V1.0
 */
@Getter
@AllArgsConstructor
public enum FilePathEnum {
    /**
     * 头像路径
     */
    AVATAR("upload/avatar/", "头像路径"),
    /**
     * 文章图片路径
     */
    ARTICLE("upload/articles/", "文章图片路径"),
    /**
     * 音频路径
     */
    VOICE("upload/voice/", "音频路径"),
    /**
     * 照片路径
     */
    PHOTO("upload/photos/", "相册路径"),
    /**
     * 配置图片路径
     */
    CONFIG("upload/config/", "配置图片路径"),
    /**
     * 说说图片路径
     */
    TALK("upload/talks/", "说说图片路径"),
    /**
     * md文件路径
     */
    MD("upload/markdown/", "md文件路径");

    /**
     * 路径
     */
    private final String path;

    /**
     * 描述
     */
    private final String desc;

}
