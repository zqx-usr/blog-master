package top.yehonghan.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.yehonghan.dto.TagBackDTO;
import top.yehonghan.entity.Tag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.yehonghan.vo.ConditionVO;

import java.util.List;

/**
* @author 86152
* @description 针对表【tb_tag】的数据库操作Mapper
* @createDate 2023-01-05 11:04:24
* @Entity top.yehonghan.entity.Tag
*/
@Mapper
public interface TagMapper extends BaseMapper<Tag> {

    /**
     * 查询后台标签列表
     *
     * @param current   页码
     * @param size      大小
     * @param condition 条件
     * @return {@link List<TagBackDTO>} 标签列表
     */
    List<TagBackDTO> listTagBackDTO(@Param("current") Long current, @Param("size") Long size, @Param("condition") ConditionVO condition);

    /**
     * 根据文章id查询标签名
     *
     * @param articleId 文章id
     * @return {@link List<String>} 标签名列表
     */
    List<String> listTagNameByArticleId(Integer articleId);
}




