package run.gocli.core.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import run.gocli.core.entity.Role;

@Mapper
public interface RoleDao extends BaseMapper<Role> {
}
