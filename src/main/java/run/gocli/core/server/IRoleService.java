package run.gocli.core.server;

import com.baomidou.mybatisplus.extension.service.IService;
import run.gocli.admin.req.AddRoleReq;
import run.gocli.admin.req.RoleReq;
import run.gocli.admin.vo.RoleListVo;
import run.gocli.core.entity.Role;

import java.util.List;

public interface IRoleService extends IService<Role> {
    List<RoleListVo> getRoleList(RoleReq request);
    Boolean addRole(AddRoleReq request);
    Boolean editRole(AddRoleReq request);
    Boolean delRole(String ids);
}
