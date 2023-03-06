package top.yehonghan.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.yehonghan.entity.Resource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author 86152
* @description 针对表【tb_resource】的数据库操作Mapper
* @createDate 2023-01-05 11:03:51
* @Entity top.yehonghan.entity.Resource
*/
@Mapper
public interface ResourceMapper extends BaseMapper<Resource> {

}




