package run.gocli.core.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import run.gocli.core.entity.AccountRole;

import java.util.List;

@Mapper
public interface AccountRoleDao extends BaseMapper<AccountRole> {
    List<String> getButtons(@Param("accountId") int accountId);
    List<String> getRoles(@Param("accountId") int accountId);
}
