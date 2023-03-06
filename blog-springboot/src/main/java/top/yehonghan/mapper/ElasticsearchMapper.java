package top.yehonghan.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import top.yehonghan.dto.ArticleSearchDTO;

/**
 * ClassName: ElasticsearchMapper
 * Description: elasticsearch
 *
 * @Author: yehonghan
 * @Create: 2023/1/7 13:02
 * @Version: V1.0
 */
@Mapper
public interface ElasticsearchMapper extends ElasticsearchRepository<ArticleSearchDTO,Integer> {
}
