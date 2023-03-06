package top.yehonghan.service;

import top.yehonghan.entity.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import top.yehonghan.vo.PageVO;

import java.util.List;

/**
* @author 86152
* @description 针对表【tb_page(页面)】的数据库操作Service
* @createDate 2023-01-05 11:03:32
*/
public interface PageService extends IService<Page> {

    /**
     * 保存或更新页面
     *
     * @param pageVO 页面信息
     */
    void saveOrUpdatePage(PageVO pageVO);

    /**
     * 删除页面
     *
     * @param pageId 页面id
     */
    void deletePage(Integer pageId);

    /**
     * 获取页面列表
     *
     * @return {@link List <PageVO>} 页面列表
     */
    List<PageVO> listPages();


}
