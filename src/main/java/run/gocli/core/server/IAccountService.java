package run.gocli.core.server;

import com.baomidou.mybatisplus.extension.service.IService;
import run.gocli.core.entity.Account;

public interface IAccountService extends IService<Account> {
    Account getByUsername(String username);
}
