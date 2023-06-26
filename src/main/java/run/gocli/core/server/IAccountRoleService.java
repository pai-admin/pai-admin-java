package run.gocli.core.server;

import com.baomidou.mybatisplus.extension.service.IService;
import run.gocli.admin.vo.AccountRoleVo;
import run.gocli.admin.vo.MenuVo;
import run.gocli.core.entity.AccountRole;

import java.util.List;

public interface IAccountRoleService extends IService<AccountRole> {
    List<String> getButtons(Integer accountId);
    List<String> getRoles(Integer accountId);
    List<MenuVo> getMenus(Integer accountId);
    List<AccountRoleVo> getAccountRole(Integer accountId);
    Boolean setRole(Integer accountId, int[] roles);
}
