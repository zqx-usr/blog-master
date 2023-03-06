package top.yehonghan.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * ClassName: PhotoVO
 * Description: 照片
 *
 * @Author: yehonghan
 * @Create: 2023/1/5 10:29
 * @Version: V1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(description = "照片")
public class PhotoVO {

    /**
     * 相册id
     */
    @NotNull(message = "相册id不能为空")
    @ApiModelProperty(name = "id", value = "相册id", required = true, dataType = "Integer")
    private Integer albumId;

    /**
     * 照片url列表
     */
    @ApiModelProperty(name = "photoUrlList", value = "照片列表", required = true, dataType = "List<String>")
    private List<String> photoUrlList;

    /**
     * 照片id列表
     */
    @ApiModelProperty(name = "photoIdList", value = "照片id列表", required = true, dataType = "List<Integer>")
    private List<Integer> photoIdList;

}
