package top.yehonghan.consumer;

import com.alibaba.fastjson.JSON;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.yehonghan.dto.ArticleSearchDTO;
import top.yehonghan.dto.MaxwellDataDTO;
import top.yehonghan.entity.Article;
import top.yehonghan.mapper.ElasticsearchMapper;
import top.yehonghan.util.BeanCopyUtils;

import static top.yehonghan.constant.MQPrefixConst.MAXWELL_QUEUE;

/**
 * ClassName: MaxWellConsumer
 * Description: maxwell监听数据
 *
 * @Author: yehonghan
 * @Create: 2023/1/7 13:00
 * @Version: V1.0
 */
@Component
@RabbitListener(queues = MAXWELL_QUEUE)
public class MaxWellConsumer {
    @Autowired
    private ElasticsearchMapper elasticsearchMapper;

    @RabbitHandler
    public void process(byte[] data) {
        // 获取监听信息
        MaxwellDataDTO maxwellDataDTO = JSON.parseObject(new String(data), MaxwellDataDTO.class);
        // 获取文章数据
        Article article = JSON.parseObject(JSON.toJSONString(maxwellDataDTO.getData()), Article.class);
        // 判断操作类型
        switch (maxwellDataDTO.getType()) {
            case "insert":
            case "update":
                // 更新es文章
                elasticsearchMapper.save(BeanCopyUtils.copyObject(article, ArticleSearchDTO.class));
                break;
            case "delete":
                // 删除文章
                elasticsearchMapper.deleteById(article.getId());
                break;
            default:
                break;
        }
    }

}
