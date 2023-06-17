package run.gocli.core.server;

import com.baomidou.mybatisplus.extension.service.IService;
import run.gocli.core.entity.Account;
import run.gocli.core.entity.AccountLog;

import javax.servlet.http.HttpServletRequest;

public interface IAccountLogService extends IService<AccountLog> {
    Boolean writeLog(Account account, HttpServletRequest request, Integer code, String flag, String title);
}
