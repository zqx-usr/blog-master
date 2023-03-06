package top.yehonghan.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

 /**
 * ClassName: ArticlePaginationDTO
 * Description: 文章上下篇
 *
 * @Author: yehonghan
 * @Create: 2023/1/5 10:39
 * @Version: V1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticlePaginationDTO {

    /**
     * id
     */
    private Integer id;

    /**
     * 文章缩略图
     */
    private String articleCover;

    /**
     * 标题
     */
    private String articleTitle;

}
