package run.gocli.core.server;

import com.baomidou.mybatisplus.extension.service.IService;
import run.gocli.core.entity.AccountRole;

import java.util.List;

public interface IAccountRoleService extends IService<AccountRole> {
    List<String> getButtons(Integer accountId);
    List<String> getRoles(Integer accountId);
}
