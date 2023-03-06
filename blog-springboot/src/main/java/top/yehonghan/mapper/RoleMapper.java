package top.yehonghan.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.yehonghan.dto.ResourceRoleDTO;
import top.yehonghan.dto.RoleDTO;
import top.yehonghan.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.yehonghan.vo.ConditionVO;

import java.util.List;

/**
* @author 86152
* @description 针对表【tb_role】的数据库操作Mapper
* @createDate 2023-01-06 16:11:19
* @Entity top.yehonghan.entity.Role
*/
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    /**
     * 查询路由角色列表
     *
     * @return 角色标签
     */
    List<ResourceRoleDTO> listResourceRoles();

    /**
     * 根据用户id获取角色列表
     *
     * @param userInfoId 用户id
     * @return 角色标签
     */
    List<String> listRolesByUserInfoId(Integer userInfoId);

    /**
     * 查询角色列表
     *
     * @param current     页码
     * @param size        条数
     * @param conditionVO 条件
     * @return 角色列表
     */
    List<RoleDTO> listRoles(@Param("current") Long current, @Param("size") Long size, @Param("conditionVO") ConditionVO conditionVO);

}




