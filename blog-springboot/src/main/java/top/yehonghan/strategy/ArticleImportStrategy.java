package top.yehonghan.strategy;

import org.springframework.web.multipart.MultipartFile;

/**
 * ClassName: ArticleImportStrategy
 * Description: 文章导入策略
 *
 * @Author: yehonghan
 * @Create: 2023/1/6 12:39
 * @Version: V1.0
 */
public interface ArticleImportStrategy {
    /**
     * 导入文章
     *
     * @param file 文件
     */
    void importArticles(MultipartFile file);
}
