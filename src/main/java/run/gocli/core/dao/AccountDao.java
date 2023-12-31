package run.gocli.core.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import run.gocli.admin.vo.AccountListVo;
import run.gocli.admin.vo.AccountVo;
import run.gocli.core.entity.Account;

import java.util.List;

@Mapper
public interface AccountDao extends BaseMapper<Account> {
    AccountVo getInfo(@Param("accountId") Integer accountId);
    IPage<AccountListVo> getAccountList(IPage<AccountListVo> page, @Param("username") String username, @Param("deptId") Integer deptId);
    List<String> getApis(@Param("accountId") Integer accountId);
}
