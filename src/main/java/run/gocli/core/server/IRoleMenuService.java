package run.gocli.core.server;

import com.baomidou.mybatisplus.extension.service.IService;
import run.gocli.admin.vo.RoleMenuVo;
import run.gocli.core.entity.RoleMenu;

import java.util.List;

public interface IRoleMenuService extends IService<RoleMenu> {
    List<RoleMenuVo> getRoleMenu(Integer id);
    Boolean setRoleMenu(Integer roleId, int[] menuIds);
}
