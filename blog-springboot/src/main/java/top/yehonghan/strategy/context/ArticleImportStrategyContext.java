package top.yehonghan.strategy.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.yehonghan.enums.MarkdownTypeEnum;
import top.yehonghan.strategy.ArticleImportStrategy;

import java.util.Map;

/**
 * ClassName: ArticleImportStrategyContext
 * Description: 文章导入策略上下文
 *
 * @Author: yehonghan
 * @Create: 2023/1/6 12:39
 * @Version: V1.0
 */
@Service
public class ArticleImportStrategyContext {

    @Autowired
    private Map<String, ArticleImportStrategy> articleImportStrategyMap;

    public void importArticles(MultipartFile file, String type) {
        articleImportStrategyMap.get(MarkdownTypeEnum.getMarkdownType(type)).importArticles(file);
    }
}
