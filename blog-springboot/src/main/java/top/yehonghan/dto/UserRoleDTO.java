package top.yehonghan.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: UserRoleDTO
 * Description: 返回用户角色信息
 *
 * @Author: yehonghan
 * @Create: 2023/1/4 10:32
 * @Version: V1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleDTO {
    /**
     * 角色id
     */
    private Integer id;

    /**
     * 角色名
     */
    private String roleName;
}
