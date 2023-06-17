package run.gocli.core.server;

import com.baomidou.mybatisplus.extension.service.IService;
import run.gocli.admin.req.EditAccountReq;
import run.gocli.admin.req.EditPwdReq;
import run.gocli.core.entity.Account;

public interface IAccountService extends IService<Account> {
    Account getByUsername(String username);
    Boolean saveInfo(Integer accountId, EditAccountReq request);
    Boolean editPwd(Integer accountId, EditPwdReq request);
}
