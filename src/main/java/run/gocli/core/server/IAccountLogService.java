package run.gocli.core.server;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import run.gocli.admin.req.LogReq;
import run.gocli.admin.req.MyLogReq;
import run.gocli.admin.vo.LogVo;
import run.gocli.admin.vo.MyLogVo;
import run.gocli.core.entity.Account;
import run.gocli.core.entity.AccountLog;

import javax.servlet.http.HttpServletRequest;

public interface IAccountLogService extends IService<AccountLog> {
    Boolean writeLog(Account account, HttpServletRequest request, Integer code, String flag, String title);
    boolean delLog(String ids);
    IPage<AccountLog> getLog(Integer accountId, MyLogReq request);
    IPage<AccountLog> getLog(LogReq request);
}
