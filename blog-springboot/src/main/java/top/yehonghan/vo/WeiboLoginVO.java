package top.yehonghan.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * ClassName: WeiboLoginVO
 * Description: 微博登录
 *
 * @Author: yehonghan
 * @Create: 2023/1/5 10:38
 * @Version: V1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WeiboLoginVO {

    /**
     * code
     */
    @NotBlank(message = "code不能为空")
    @ApiModelProperty(name = "openId", value = "qq openId", required = true, dataType = "String")
    private String code;

}
