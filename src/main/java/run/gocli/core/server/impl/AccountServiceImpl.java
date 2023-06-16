package run.gocli.core.server.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import run.gocli.core.dao.AccountDao;
import run.gocli.core.entity.Account;
import run.gocli.core.server.IAccountService;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
public class AccountServiceImpl extends ServiceImpl<AccountDao, Account> implements IAccountService {
    @Override
    public Account getByUsername(String username) {
        QueryWrapper<Account> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return getOne(queryWrapper);
    }
}
