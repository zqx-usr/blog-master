package top.yehonghan.service;

import top.yehonghan.dto.UniqueViewDTO;
import top.yehonghan.entity.UniqueView;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 86152
* @description 针对表【tb_unique_view】的数据库操作Service
* @createDate 2023-01-05 11:04:35
*/
public interface UniqueViewService extends IService<UniqueView> {

    /**
     * 获取7天用户量统计
     *
     * @return 用户量
     */
    List<UniqueViewDTO> listUniqueViews();
}
