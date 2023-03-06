package top.yehonghan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.yehonghan.entity.UserRole;
import top.yehonghan.service.UserRoleService;
import top.yehonghan.mapper.UserRoleMapper;
import org.springframework.stereotype.Service;

/**
* @author 86152
* @description 针对表【tb_user_role】的数据库操作Service实现
* @createDate 2023-01-05 11:04:49
*/
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole>
    implements UserRoleService{

}




