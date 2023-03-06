package top.yehonghan.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import top.yehonghan.entity.Page;
import top.yehonghan.service.PageService;
import top.yehonghan.mapper.PageMapper;
import org.springframework.stereotype.Service;
import top.yehonghan.service.RedisService;
import top.yehonghan.util.BeanCopyUtils;
import top.yehonghan.vo.PageVO;

import java.util.List;
import java.util.Objects;

import static top.yehonghan.constant.RedisPrefixConst.PAGE_COVER;

/**
* @author 86152
* @description 针对表【tb_page(页面)】的数据库操作Service实现
* @createDate 2023-01-05 11:03:32
*/
@Service
public class PageServiceImpl extends ServiceImpl<PageMapper, Page> implements PageService{

    @Autowired
    private RedisService redisService;
    @Autowired
    private PageMapper pageMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveOrUpdatePage(PageVO pageVO) {
        Page page = BeanCopyUtils.copyObject(pageVO, Page.class);
        this.saveOrUpdate(page);
        // 删除缓存
        redisService.del(PAGE_COVER);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deletePage(Integer pageId) {
        pageMapper.deleteById(pageId);
        // 删除缓存
        redisService.del(PAGE_COVER);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<PageVO> listPages() {
        List<PageVO> pageVOList;
        // 查找缓存信息，不存在则从mysql读取，更新缓存
        Object pageList = redisService.get(PAGE_COVER);
        if (Objects.nonNull(pageList)) {
            pageVOList = JSON.parseObject(pageList.toString(), List.class);
        } else {
            pageVOList = BeanCopyUtils.copyList(pageMapper.selectList(null), PageVO.class);
            redisService.set(PAGE_COVER, JSON.toJSONString(pageVOList));
        }
        return pageVOList;
    }
}




