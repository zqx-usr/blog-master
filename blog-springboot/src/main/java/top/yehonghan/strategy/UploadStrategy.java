package top.yehonghan.strategy;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * ClassName: UploadStrategy
 * Description: 上传策略
 *
 * @Author: yehonghan
 * @Create: 2023/1/6 10:28
 * @Version: V1.0
 */
public interface UploadStrategy {
    /**
     * 上传文件
     *
     * @param file 文件
     * @param path 上传路径
     * @return {@link String} 文件地址
     */
    String uploadFile(MultipartFile file, String path);

    /**
     * 上传文件
     *
     * @param fileName    文件名
     * @param inputStream 输入流
     * @param path        路径
     * @return {@link String}
     */
    String uploadFile(String fileName, InputStream inputStream, String path);
}
