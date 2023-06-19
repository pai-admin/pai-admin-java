package run.gocli.core.server.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import run.gocli.core.dao.RoleMenuDao;
import run.gocli.core.entity.RoleMenu;
import run.gocli.core.server.IRoleMenuService;

@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuDao, RoleMenu> implements IRoleMenuService {
}
