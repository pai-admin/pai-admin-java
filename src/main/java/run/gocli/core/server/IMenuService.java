package run.gocli.core.server;

import com.baomidou.mybatisplus.extension.service.IService;
import run.gocli.admin.req.AddMenuReq;
import run.gocli.admin.vo.AddMenuVo;
import run.gocli.admin.vo.MenuListVo;
import run.gocli.core.entity.Menu;

import java.util.List;

public interface IMenuService extends IService<Menu> {
    List<MenuListVo> getMenuList();
    AddMenuVo addMenu(AddMenuReq request);
    Boolean editMenu(AddMenuReq request);
    Boolean delMenu(String ids);
}
