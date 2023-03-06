package top.yehonghan.mapper;

import cn.hutool.core.date.DateTime;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.yehonghan.dto.UniqueViewDTO;
import top.yehonghan.entity.UniqueView;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Date;
import java.util.List;

/**
* @author 86152
* @description 针对表【tb_unique_view】的数据库操作Mapper
* @createDate 2023-01-05 11:04:35
* @Entity top.yehonghan.entity.UniqueView
*/
@Mapper
public interface UniqueViewMapper extends BaseMapper<UniqueView> {

    /**
     * 获取7天用户量
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return 用户量
     */
    List<UniqueViewDTO> listUniqueViews(@Param("startTime") Date startTime, @Param("endTime") Date endTime);
}




