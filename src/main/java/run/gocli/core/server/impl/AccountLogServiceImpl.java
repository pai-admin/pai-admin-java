package run.gocli.core.server.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import run.gocli.admin.req.LogReq;
import run.gocli.admin.req.MyLogReq;
import run.gocli.core.dao.AccountLogDao;
import run.gocli.core.entity.Account;
import run.gocli.core.entity.AccountLog;
import run.gocli.core.server.IAccountLogService;
import run.gocli.utils.DateUtil;
import run.gocli.utils.StrUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
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
        logData.setCreateTime(DateUtil.getCurrentDateTime());
        return save(logData);
    }

    @Override
    public boolean delLog(String ids) {
        return removeBatchByIds(Arrays.asList(ids.split(",")));
    }

    @Override
    public IPage<AccountLog> getLog(Integer accountId, MyLogReq request) {
        IPage<AccountLog> iPage = new Page<>(request.getPage(), request.getLimit());
        QueryWrapper<AccountLog> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account_id", accountId);
        queryWrapper.orderByDesc("log_id");
        return page(iPage, queryWrapper);
    }

    @Override
    public IPage<AccountLog> getLog(LogReq request) {
        IPage<AccountLog> iPage = new Page<>(request.getPage(), request.getLimit());
        QueryWrapper<AccountLog> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(request.getTitle())) {
            queryWrapper.like("title", request.getTitle());
        }
        if (!StringUtils.isEmpty(request.getStartTime())) {
            queryWrapper.ge("create_time", request.getStartTime());
        }
        if (!StringUtils.isEmpty(request.getEndTime())) {
            queryWrapper.le("create_time", request.getEndTime());
        }
        queryWrapper.orderByDesc("log_id");
        return page(iPage, queryWrapper);
    }
}
