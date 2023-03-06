package top.yehonghan.service;

import top.yehonghan.dto.FriendLinkBackDTO;
import top.yehonghan.dto.FriendLinkDTO;
import top.yehonghan.entity.FriendLink;
import com.baomidou.mybatisplus.extension.service.IService;
import top.yehonghan.vo.ConditionVO;
import top.yehonghan.vo.FriendLinkVO;
import top.yehonghan.vo.PageResult;

import java.util.List;

/**
* @author 86152
* @description 针对表【tb_friend_link】的数据库操作Service
* @createDate 2023-01-05 11:02:22
*/
public interface FriendLinkService extends IService<FriendLink> {
    /**
     * 查看友链列表
     *
     * @return 友链列表
     */
    List<FriendLinkDTO> listFriendLinks();

    /**
     * 查看后台友链列表
     *
     * @param condition 条件
     * @return 友链列表
     */
    PageResult<FriendLinkBackDTO> listFriendLinkDTO(ConditionVO condition);

    /**
     * 保存或更新友链
     *
     * @param friendLinkVO 友链
     */
    void saveOrUpdateFriendLink(FriendLinkVO friendLinkVO);
}
