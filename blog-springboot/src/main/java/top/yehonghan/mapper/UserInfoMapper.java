package top.yehonghan.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.yehonghan.entity.UserInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author 86152
* @description 针对表【tb_user_info】的数据库操作Mapper
* @createDate 2023-01-06 16:50:49
* @Entity top.yehonghan.entity.UserInfo
*/
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {

}




