package top.yehonghan.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.yehonghan.dto.TalkBackDTO;
import top.yehonghan.dto.TalkDTO;
import top.yehonghan.entity.Talk;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.yehonghan.vo.ConditionVO;

import java.util.List;

/**
 * @author 86152
 * @description 针对表【tb_talk】的数据库操作Mapper
 * @createDate 2023-01-05 11:04:31
 * @Entity top.yehonghan.entity.Talk
 */
@Mapper
public interface TalkMapper extends BaseMapper<Talk> {

    /**
     * 获取说说列表
     *
     * @param current 页码
     * @param size    大小
     * @return {@link List <TalkDTO>}
     */
    List<TalkDTO> listTalks(@Param("current") Long current, @Param("size") Long size);

    /**
     * 查看后台说说
     *
     * @param current 页码
     * @param size    大小
     * @return {@link List<TalkBackDTO>}
     */
    List<TalkBackDTO> listBackTalks(@Param("current") Long current, @Param("size") Long size, @Param("condition") ConditionVO condition);


    /**
     * 根据id查看说说
     *
     * @param talkId 说说id
     * @return {@link TalkDTO} 说说信息
     */
    TalkDTO getTalkById(@Param("talkId") Integer talkId);


    /**
     * 根据id查看后台说说
     *
     * @param talkId 说说id
     * @return {@link TalkBackDTO} 说说信息
     */
    TalkBackDTO getBackTalkById(@Param("talkId") Integer talkId);

}




