package top.yehonghan.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.yehonghan.dto.LabelOptionDTO;
import top.yehonghan.dto.MenuDTO;
import top.yehonghan.dto.UserMenuDTO;
import top.yehonghan.service.MenuService;
import top.yehonghan.util.Result;
import top.yehonghan.vo.ConditionVO;
import top.yehonghan.vo.MenuVO;

import javax.validation.Valid;
import java.util.List;

/**
 * ClassName: MenuController
 * Description: 菜单模块
 *
 * @Author: yehonghan
 * @Create: 2023/1/4 15:18
 * @Version: V1.0
 */
@Api(tags = "菜单模块")
@RestController
public class MenuController {
    @Autowired
    private MenuService menuService;
    /**
     * 查看当前用户菜单
     *
     * @return {@link Result< UserMenuDTO >} 菜单列表
     */
    @ApiOperation(value = "查看当前用户菜单")
    @GetMapping("/admin/user/menus")
    public Result<List<UserMenuDTO>> listUserMenus() {
        return Result.ok(menuService.listUserMenus());
    }

    /**
     * 查询菜单列表
     *
     * @param conditionVO 条件
     * @return {@link Result<MenuDTO>} 菜单列表
     */
    @ApiOperation(value = "查看菜单列表")
    @GetMapping("/admin/menus")
    public Result<List<MenuDTO>> listMenus(ConditionVO conditionVO) {
        return Result.ok(menuService.listMenus(conditionVO));
    }

    /**
     * 新增或修改菜单
     *
     * @param menuVO 菜单
     * @return {@link Result<>}
     */
    @ApiOperation(value = "新增或修改菜单")
    @PostMapping("/admin/menus")
    public Result<?> saveOrUpdateMenu(@Valid @RequestBody MenuVO menuVO) {
        menuService.saveOrUpdateMenu(menuVO);
        return Result.ok();
    }

    /**
     * 删除菜单
     *
     * @param menuId 菜单id
     * @return {@link Result<>}
     */
    @ApiOperation(value = "删除菜单")
    @DeleteMapping("/admin/menus/{menuId}")
    public Result<?> deleteMenu(@PathVariable("menuId") Integer menuId){
        menuService.deleteMenu(menuId);
        return Result.ok();
    }

    /**
     * 查看角色菜单选项
     *
     * @return {@link Result<LabelOptionDTO>} 查看角色菜单选项
     */
    @ApiOperation(value = "查看角色菜单选项")
    @GetMapping("/admin/role/menus")
    public Result<List<LabelOptionDTO>> listMenuOptions() {
        return Result.ok(menuService.listMenuOptions());
    }

}
