package top.yehonghan.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.yehonghan.entity.WebsiteConfig;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author 86152
* @description 针对表【tb_website_config】的数据库操作Mapper
* @createDate 2023-01-05 11:04:54
* @Entity top.yehonghan.entity.WebsiteConfig
*/
@Mapper
public interface WebsiteConfigMapper extends BaseMapper<WebsiteConfig> {

}




