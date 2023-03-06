package top.yehonghan.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.yehonghan.entity.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author 86152
* @description 针对表【tb_user_role】的数据库操作Mapper
* @createDate 2023-01-05 11:04:49
* @Entity top.yehonghan.entity.UserRole
*/
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {

}




