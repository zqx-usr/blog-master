package top.yehonghan.service;

import top.yehonghan.dto.LabelOptionDTO;
import top.yehonghan.dto.ResourceDTO;
import top.yehonghan.entity.Resource;
import com.baomidou.mybatisplus.extension.service.IService;
import top.yehonghan.vo.ConditionVO;
import top.yehonghan.vo.ResourceVO;

import java.util.List;

/**
* @author 86152
* @description 针对表【tb_resource】的数据库操作Service
* @createDate 2023-01-05 11:03:51
*/
public interface ResourceService extends IService<Resource> {
    /**
     * 添加或修改资源
     *
     * @param resourceVO 资源对象
     */
    void saveOrUpdateResource(ResourceVO resourceVO);

    /***
     * 删除资源
     *
     @param resourceId 资源id
     */
    void deleteResource(Integer resourceId);

    /**
     * 查看资源列表
     *
     * @param conditionVO 条件
     * @return 资源列表
     */
    List<ResourceDTO> listResources(ConditionVO conditionVO);

    /**
     * 查看资源选项
     *
     * @return 资源选项
     */
    List<LabelOptionDTO> listResourceOption();

}
