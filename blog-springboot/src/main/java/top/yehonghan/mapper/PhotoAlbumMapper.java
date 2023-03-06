package top.yehonghan.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.yehonghan.dto.PhotoAlbumBackDTO;
import top.yehonghan.entity.PhotoAlbum;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.yehonghan.vo.ConditionVO;

import java.util.List;

/**
* @author 86152
* @description 针对表【tb_photo_album(相册)】的数据库操作Mapper
* @createDate 2023-01-05 11:03:46
* @Entity top.yehonghan.entity.PhotoAlbum
*/
@Mapper
public interface PhotoAlbumMapper extends BaseMapper<PhotoAlbum> {

    /**
     * 查询后台相册列表
     *
     * @param current   页码
     * @param size      大小
     * @param condition 条件
     * @return {@link List < PhotoAlbumBackDTO >} 相册列表
     */
    List<PhotoAlbumBackDTO> listPhotoAlbumBacks(@Param("current") Long current, @Param("size") Long size, @Param("condition") ConditionVO condition);

}




