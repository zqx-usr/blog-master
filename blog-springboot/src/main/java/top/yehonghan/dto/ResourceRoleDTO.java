package top.yehonghan.dto;

import lombok.Data;

import java.util.List;

/**
 * ClassName: ResourceRoleDTO
 * Description: 资源角色
 *
 * @Author: yehonghan
 * @Create: 2023/1/4 10:49
 * @Version: V1.0
 */
@Data
public class ResourceRoleDTO {

    /**
     * 资源id
     */
    private Integer id;

    /**
     * 路径
     */
    private String url;

    /**
     * 请求方式
     */
    private String requestMethod;

    /**
     * 角色名
     */
    private List<String> roleList;

}
