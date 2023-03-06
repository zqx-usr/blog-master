package top.yehonghan.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.yehonghan.annotation.OptLog;
import top.yehonghan.dto.RoleDTO;
import top.yehonghan.dto.UserRoleDTO;
import top.yehonghan.service.RoleService;
import top.yehonghan.util.Result;
import top.yehonghan.vo.ConditionVO;
import top.yehonghan.vo.PageResult;
import top.yehonghan.vo.RoleVO;

import javax.validation.Valid;
import java.util.List;

import static top.yehonghan.constant.OptTypeConst.REMOVE;
import static top.yehonghan.constant.OptTypeConst.SAVE_OR_UPDATE;

/**
 * ClassName: RoleController
 * Description: 角色模块
 *
 * @Author: yehonghan
 * @Create: 2023/1/6 16:09
 * @Version: V1.0
 */
@Api(tags = "角色模块")
@RestController
public class RoleController {
    @Autowired
    private RoleService roleService;

    /**
     * 查询用户角色选项
     *
     * @return {@link Result<UserRoleDTO>} 用户角色选项
     */
    @ApiOperation(value = "查询用户角色选项")
    @GetMapping("/admin/users/role")
    public Result<List<UserRoleDTO>> listUserRoles() {
        return Result.ok(roleService.listUserRoles());
    }

    /**
     * 查询角色列表
     *
     * @param conditionVO 条件
     * @return {@link Result<RoleDTO>} 角色列表
     */
    @ApiOperation(value = "查询角色列表")
    @GetMapping("/admin/roles")
    public Result<PageResult<RoleDTO>> listRoles(ConditionVO conditionVO) {
        return Result.ok(roleService.listRoles(conditionVO));
    }

    /**
     * 保存或更新角色
     *
     * @param roleVO 角色信息
     * @return {@link Result<>}
     */
    @OptLog(optType = SAVE_OR_UPDATE)
    @ApiOperation(value = "保存或更新角色")
    @PostMapping("/admin/role")
    public Result<?> saveOrUpdateRole(@RequestBody @Valid RoleVO roleVO) {
        roleService.saveOrUpdateRole(roleVO);
        return Result.ok();
    }

    /**
     * 删除角色
     *
     * @param roleIdList 角色id列表
     * @return {@link Result<>}
     */
    @OptLog(optType = REMOVE)
    @ApiOperation(value = "删除角色")
    @DeleteMapping("/admin/roles")
    public Result<?> deleteRoles(@RequestBody List<Integer> roleIdList) {
        roleService.deleteRoles(roleIdList);
        return Result.ok();
    }

}
