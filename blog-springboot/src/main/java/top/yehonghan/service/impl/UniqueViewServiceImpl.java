package top.yehonghan.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import top.yehonghan.dto.UniqueViewDTO;
import top.yehonghan.entity.UniqueView;
import top.yehonghan.service.RedisService;
import top.yehonghan.service.UniqueViewService;
import top.yehonghan.mapper.UniqueViewMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
* @author 86152
* @description 针对表【tb_unique_view】的数据库操作Service实现
* @createDate 2023-01-05 11:04:35
*/
@Service
public class UniqueViewServiceImpl extends ServiceImpl<UniqueViewMapper, UniqueView> implements UniqueViewService{

    @Autowired
    private RedisService redisService;
    @Autowired
    private UniqueViewMapper uniqueViewMapper;

    @Override
    public List<UniqueViewDTO> listUniqueViews() {
        DateTime startTime = DateUtil.beginOfDay(DateUtil.offsetDay(new Date(), -7));
        DateTime endTime = DateUtil.endOfDay(new Date());
        return uniqueViewMapper.listUniqueViews(startTime, endTime);
    }
}




