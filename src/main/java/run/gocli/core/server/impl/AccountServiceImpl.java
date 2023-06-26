package run.gocli.core.server.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import run.gocli.admin.req.AccountReq;
import run.gocli.admin.req.AddAccountReq;
import run.gocli.admin.req.EditAccountReq;
import run.gocli.admin.req.EditPwdReq;
import run.gocli.admin.vo.AccountListVo;
import run.gocli.admin.vo.AccountVo;
import run.gocli.admin.vo.AuthVo;
import run.gocli.core.dao.AccountDao;
import run.gocli.core.entity.Account;
import run.gocli.core.server.IAccountRoleService;
import run.gocli.core.server.IAccountService;
import run.gocli.utils.DateUtil;
import run.gocli.utils.StrUtil;

import java.util.Arrays;
import java.util.Objects;


@Service
public class AccountServiceImpl extends ServiceImpl<AccountDao, Account> implements IAccountService {
    @Autowired
    private IAccountRoleService accountRoleService;
    @Override
    public Account getByUsername(String username) {
        QueryWrapper<Account> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return getOne(queryWrapper);
    }

    @Override
    public Boolean saveInfo(Integer accountId, EditAccountReq request) {
        Account account = getById(accountId);
        account.setUsername(request.getUsername());
        account.setAvatar(request.getAvatar());
        account.setUpdateTime(DateUtil.getCurrentDateTime());
        return updateById(account);
    }

    @Override
    public Boolean editPwd(Integer accountId, EditPwdReq request) {
        Account account = getById(accountId);
        // 验证原密码是否正确
        if (!Objects.equals(StrUtil.md5(request.getOldPassword()) + account.getSalt(), account.getPassword())) {
            return false;
        }
        String salt = StrUtil.generateNonceStr(6);
        account.setSalt(salt);
        account.setPassword(StrUtil.md5(salt+request.getNewPassword()));
        account.setUpdateTime(DateUtil.getCurrentDateTime());
        return updateById(account);
    }

    @Override
    public AuthVo getAuths(Integer accountId) {
        AuthVo authVo = new AuthVo();
        authVo.setButtons(accountRoleService.getButtons(accountId));
        authVo.setRoles(accountRoleService.getRoles(accountId));
        authVo.setMenus(accountRoleService.getMenus(accountId));
        return authVo;
    }

    @Override
    public AccountVo getInfo(Integer accountId) {
        return this.baseMapper.getInfo(accountId);
    }

    @Override
    public Boolean delAccount(String ids) {
        return removeBatchByIds(Arrays.asList(ids.split(",")));
    }

    @Override
    public Boolean editAccount(AddAccountReq request) {
        Account account = new Account();
        BeanUtils.copyProperties(request, account);
        if (request.getPassword() != null) {
            String salt = StrUtil.generateNonceStr(6);
            account.setSalt(salt);
            account.setPassword(StrUtil.md5(salt+account.getPassword()));
        }
        account.setUpdateTime(DateUtil.getCurrentDateTime());
        boolean res = updateById(account);
        if (!res) {
            return false;
        }
        return accountRoleService.setRole(account.getAccountId(), request.getRoles());
    }

    @Override
    public Boolean addAccount(AddAccountReq request) {
        Account account = new Account();
        BeanUtils.copyProperties(request, account);
        String salt = StrUtil.generateNonceStr(6);
        account.setSalt(salt);
        account.setPassword(StrUtil.md5(salt+account.getPassword()));
        account.setCreateTime(DateUtil.getCurrentDateTime());
        boolean res = save(account);
        if (!res) {
            return false;
        }
        return accountRoleService.setRole(account.getAccountId(), request.getRoles());
    }

    @Override
    public IPage<AccountListVo> getAccountList(AccountReq request) {
        IPage<AccountListVo> page = new Page<>(request.getPage(), request.getLimit());
        return this.baseMapper.getAccountList(page, request.getUsername(), request.getStatus(), request.getRoleId());
    }
}
