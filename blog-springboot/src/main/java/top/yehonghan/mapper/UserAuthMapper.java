package top.yehonghan.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.yehonghan.vo.ConditionVO;
import top.yehonghan.entity.UserAuth;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.yehonghan.dto.UserBackDTO;

import java.util.List;

/**
* @author 86152
* @description 针对表【tb_user_auth】的数据库操作Mapper
* @createDate 2022-12-11 14:40:22
* @Entity top.yehonghan.entity.UserAuth
*/
@Mapper
public interface UserAuthMapper extends BaseMapper<UserAuth> {

    /**
     * 查询后台用户列表
     *
     * @param current   页码
     * @param size      大小
     * @param condition 条件
     * @return {@link List <UserBackDTO>} 用户列表
     */
    List<UserBackDTO> listUsers(@Param("current") Long current, @Param("size") Long size, @Param("condition") ConditionVO condition);

    /**
     * 查询后台用户数量
     *
     * @param condition 条件
     * @return 用户数量
     */
    Integer countUser(@Param("condition") ConditionVO condition);
}




