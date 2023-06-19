package run.gocli.core.server.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import run.gocli.core.dao.MenuDao;
import run.gocli.core.entity.Menu;
import run.gocli.core.server.IMenuService;

@Service
public class MenuServiceImpl extends ServiceImpl<MenuDao, Menu> implements IMenuService {
}
