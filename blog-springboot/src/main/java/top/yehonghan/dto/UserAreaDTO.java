package top.yehonghan.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


 /**
 * ClassName: UserAreaDTO
 * Description: 用户地区
 *
 * @Author: yehonghan
 * @Create: 2023/1/5 10:39
 * @Version: V1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAreaDTO {

    /**
     * 地区名
     */
    private String name;

    /**
     * 数量
     */
    private Long value;

}
