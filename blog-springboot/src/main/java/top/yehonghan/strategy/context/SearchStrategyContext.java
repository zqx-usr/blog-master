package top.yehonghan.strategy.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import top.yehonghan.dto.ArticleSearchDTO;
import top.yehonghan.strategy.SearchStrategy;

import java.util.List;
import java.util.Map;

import static top.yehonghan.enums.SearchMethodEnum.getStrategy;

/**
 * ClassName: SearchStrategyContext
 * Description: 搜索策略上下文
 *
 * @Author: yehonghan
 * @Create: 2023/1/6 10:47
 * @Version: V1.0
 */
@Service
public class SearchStrategyContext {

    /**
     * 搜索模式
     */
    @Value("${search.mode}")
    private String searchMode;

    @Autowired
    private Map<String, SearchStrategy> searchStrategyMap;

    /**
     * 执行搜索策略
     *
     * @param keywords 关键字
     * @return {@link List <ArticleSearchDTO>} 搜索文章
     */
    public List<ArticleSearchDTO> executeSearchStrategy(String keywords) {
        return searchStrategyMap.get(getStrategy(searchMode)).searchArticle(keywords);
    }

}
