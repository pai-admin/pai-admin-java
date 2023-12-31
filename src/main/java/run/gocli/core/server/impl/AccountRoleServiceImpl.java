package run.gocli.core.server.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import run.gocli.admin.vo.AccountRoleVo;
import run.gocli.admin.vo.MenuVo;
import run.gocli.core.dao.AccountRoleDao;
import run.gocli.core.entity.AccountRole;
import run.gocli.core.server.IAccountRoleService;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountRoleServiceImpl extends ServiceImpl<AccountRoleDao, AccountRole> implements IAccountRoleService {
    @Override
    public List<String> getButtons(Integer accountId) {
        return this.baseMapper.getButtons(accountId);
    }

    @Override
    public List<String> getRoles(Integer accountId) {
        return this.baseMapper.getRoles(accountId);
    }

    @Override
    public List<MenuVo> getMenus(Integer accountId) {
        return this.baseMapper.getMenus(accountId);
    }

    @Override
    public List<AccountRoleVo> getAccountRole(Integer accountId) {
        return this.baseMapper.getAccountRole(accountId);
    }

    @Override
    public Boolean setRole(Integer accountId, int[] roles) {
        // 删除以往的数据
        removeById(accountId);
        // 重新添加数据
        List<AccountRole> adminRoles = new ArrayList<>();
        for (Integer roleId: roles) {
            System.out.println(roleId);
            // 组合数据
            AccountRole accountRole = new AccountRole();
            accountRole.setRoleId(roleId);
            accountRole.setAccountId(accountId);
            adminRoles.add(accountRole);
        }
        return saveBatch(adminRoles);
    }
}
