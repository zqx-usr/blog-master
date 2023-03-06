package top.yehonghan.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

 /**
 * ClassName: PhotoDTO
 * Description: 照片
 *
 * @Author: yehonghan
 * @Create: 2023/1/5 10:39
 * @Version: V1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhotoDTO {

    /**
     * 相册封面
     */
    private String photoAlbumCover;

    /**
     * 相册名
     */
    private String photoAlbumName;

    /**
     * 照片列表
     */
    private List<String> photoList;

}
