package top.yehonghan.service;

import top.yehonghan.dto.BlogBackInfoDTO;
import top.yehonghan.dto.BlogHomeInfoDTO;
import top.yehonghan.vo.BlogInfoVO;
import top.yehonghan.vo.WebsiteConfigVO;

/**
 * ClassName: BlogInfoService
 * Description: 博客信息服务
 *
 * @Author: yehonghan
 * @Create: 2023/1/5 9:48
 * @Version: V1.0
 */
public interface BlogInfoService {

    /**
     * 获取首页数据
     *
     * @return 博客首页信息
     */
    BlogHomeInfoDTO getBlogHomeInfo();

    /**
     * 获取后台首页数据
     *
     * @return 博客后台信息
     */
    BlogBackInfoDTO getBlogBackInfo();

    /**
     * 保存或更新网站配置
     *
     * @param websiteConfigVO 网站配置
     */
    void updateWebsiteConfig(WebsiteConfigVO websiteConfigVO);

    /**
     * 获取网站配置
     *
     * @return {@link WebsiteConfigVO} 网站配置
     */
    WebsiteConfigVO getWebsiteConfig();

    /**
     * 获取关于我内容
     *
     * @return 关于我内容
     */
    String getAbout();

    /**
     * 修改关于我内容
     *
     * @param blogInfoVO 博客信息
     */
    void updateAbout(BlogInfoVO blogInfoVO);

    /**
     * 上传访客信息
     */
    void report();

}
