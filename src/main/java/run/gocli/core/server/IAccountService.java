package run.gocli.core.server;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import run.gocli.admin.req.AccountReq;
import run.gocli.admin.req.AddAccountReq;
import run.gocli.admin.req.EditAccountReq;
import run.gocli.admin.req.EditPwdReq;
import run.gocli.admin.vo.AccountListVo;
import run.gocli.admin.vo.AccountVo;
import run.gocli.admin.vo.AuthVo;
import run.gocli.core.entity.Account;

public interface IAccountService extends IService<Account> {
    Account getByUsername(String username);
    Boolean saveInfo(Integer accountId, EditAccountReq request);
    Boolean editPwd(Integer accountId, EditPwdReq request);
    AuthVo getAuths(Integer accountId);
    AccountVo getInfo(Integer accountId);

    Boolean delAccount(String ids);
    Boolean editAccount(AddAccountReq request);
    Boolean addAccount(AddAccountReq request);
    IPage<AccountListVo> getAccountList(AccountReq request);
}
