package run.gocli.core.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import run.gocli.admin.vo.AccountRoleVo;
import run.gocli.admin.vo.MenuVo;
import run.gocli.core.entity.AccountRole;

import java.util.List;

@Mapper
public interface AccountRoleDao extends BaseMapper<AccountRole> {
    List<String> getButtons(@Param("accountId") Integer accountId);
    List<String> getRoles(@Param("accountId") Integer accountId);
    List<MenuVo> getMenus(@Param("accountId") Integer accountId);
    List<AccountRoleVo> getAccountRole(@Param("accountId") Integer accountId);
}
