package run.gocli.core.server.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import run.gocli.core.dao.AccountLogDao;
import run.gocli.core.entity.Account;
import run.gocli.core.entity.AccountLog;
import run.gocli.core.server.IAccountLogService;
import run.gocli.utils.DateUtil;
import run.gocli.utils.StrUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@Service
public class AccountLogServiceImpl extends ServiceImpl<AccountLogDao, AccountLog> implements IAccountLogService {
    @Override
    public Boolean writeLog(Account account, HttpServletRequest request, Integer code, String flag, String title) {
        AccountLog logData = new AccountLog();
        logData.setAccountId(account.getAccountId());
        logData.setUsername(account.getUsername());
        logData.setTitle(title);
        logData.setMethod(request.getMethod().toLowerCase(Locale.ROOT));
        logData.setFlag(flag);
        logData.setIp(StrUtil.getIpAddress(request));
        logData.setCode(code);
        logData.setUa(request.getHeader("user-agent"));
        logData.setCreateTime(DateUtil.getCurrentDateTime(null,0));
        return save(logData);
    }
}
