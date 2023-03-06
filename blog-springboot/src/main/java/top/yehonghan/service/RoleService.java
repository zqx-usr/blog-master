package top.yehonghan.service;

import top.yehonghan.dto.RoleDTO;
import top.yehonghan.dto.UserRoleDTO;
import top.yehonghan.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import top.yehonghan.vo.ConditionVO;
import top.yehonghan.vo.PageResult;
import top.yehonghan.vo.RoleVO;

import java.util.List;

/**
* @author 86152
* @description 针对表【tb_role】的数据库操作Service
* @createDate 2023-01-06 16:11:19
*/
public interface RoleService extends IService<Role> {
    /**
     * 获取用户角色选项
     *
     * @return 角色
     */
    List<UserRoleDTO> listUserRoles();

    /**
     * 查询角色列表
     *
     * @param conditionVO 条件
     * @return 角色列表
     */
    PageResult<RoleDTO> listRoles(ConditionVO conditionVO);

    /**
     * 保存或更新角色
     *
     * @param roleVO 角色
     */
    void saveOrUpdateRole(RoleVO roleVO);

    /**
     * 删除角色
     * @param roleIdList 角色id列表
     */
    void deleteRoles(List<Integer> roleIdList);

}
