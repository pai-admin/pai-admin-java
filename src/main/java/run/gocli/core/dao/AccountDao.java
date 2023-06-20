package run.gocli.core.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import run.gocli.admin.vo.AccountVo;
import run.gocli.core.entity.Account;

@Mapper
public interface AccountDao extends BaseMapper<Account> {
    AccountVo getInfo(@Param("accountId") Integer accountId);
}
