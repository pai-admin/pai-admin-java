package run.gocli.core.server.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import run.gocli.core.dao.RoleDao;
import run.gocli.core.entity.Role;
import run.gocli.core.server.IRoleService;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleDao, Role> implements IRoleService {
}
