package run.gocli.core.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import run.gocli.core.entity.RoleMenu;

@Mapper
public interface RoleMenuDao extends BaseMapper<RoleMenu> {
}
