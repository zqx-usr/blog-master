package top.yehonghan.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.yehonghan.dto.LabelOptionDTO;
import top.yehonghan.dto.ResourceDTO;
import top.yehonghan.service.ResourceService;
import top.yehonghan.util.Result;
import top.yehonghan.vo.ConditionVO;
import top.yehonghan.vo.ResourceVO;

import javax.validation.Valid;
import java.util.List;

/**
 * ClassName: ResourceController
 * Description: 资源模块
 *
 * @Author: yehonghan
 * @Create: 2023/1/6 16:06
 * @Version: V1.0
 */
@Api(tags = "资源模块")
@RestController
public class ResourceController {
    @Autowired
    private ResourceService resourceService;

    /**
     * 查看资源列表
     *
     * @return {@link Result<ResourceDTO>} 资源列表
     */
    @ApiOperation(value = "查看资源列表")
    @GetMapping("/admin/resources")
    public Result<List<ResourceDTO>> listResources(ConditionVO conditionVO) {
        return Result.ok(resourceService.listResources(conditionVO));
    }

    /**
     * 删除资源
     *
     * @param resourceId 资源id
     * @return {@link Result<>}
     */
    @ApiOperation(value = "删除资源")
    @DeleteMapping("/admin/resources/{resourceId}")
    public Result<?> deleteResource(@PathVariable("resourceId") Integer resourceId) {
        resourceService.deleteResource(resourceId);
        return Result.ok();
    }

    /**
     * 新增或修改资源
     *
     * @param resourceVO 资源信息
     * @return {@link Result<>}
     */
    @ApiOperation(value = "新增或修改资源")
    @PostMapping("/admin/resources")
    public Result<?> saveOrUpdateResource(@RequestBody @Valid ResourceVO resourceVO) {
        resourceService.saveOrUpdateResource(resourceVO);
        return Result.ok();
    }

    /**
     * 查看角色资源选项
     *
     * @return {@link Result<LabelOptionDTO>} 角色资源选项
     */
    @ApiOperation(value = "查看角色资源选项")
    @GetMapping("/admin/role/resources")
    public Result<List<LabelOptionDTO>> listResourceOption() {
        return Result.ok(resourceService.listResourceOption());
    }


}
