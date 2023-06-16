package run.gocli.core.server.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import run.gocli.core.dao.AccountLogDao;
import run.gocli.core.entity.AccountLog;
import run.gocli.core.server.IAccountLogService;

@Service
public class AccountLogServiceImpl extends ServiceImpl<AccountLogDao, AccountLog> implements IAccountLogService {
}
