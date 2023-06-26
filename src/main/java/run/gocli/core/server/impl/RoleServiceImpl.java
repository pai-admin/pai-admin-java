package run.gocli.core.server.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import run.gocli.admin.req.AddRoleReq;
import run.gocli.admin.req.RoleReq;
import run.gocli.admin.vo.RoleListVo;
import run.gocli.core.dao.RoleDao;
import run.gocli.core.entity.Role;
import run.gocli.core.server.IRoleMenuService;
import run.gocli.core.server.IRoleService;
import run.gocli.utils.DateUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleDao, Role> implements IRoleService {
    @Autowired
    private IRoleMenuService roleMenuService;

    @Override
    public List<RoleListVo> getRoleList(RoleReq request) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        if (request.getName() != null && !request.getName().equals("")) {
            queryWrapper.like("name", request.getName());
        }
        List<Role> roles = list(queryWrapper);
        List<RoleListVo> roleListVos = new ArrayList<>();
        roles.forEach(role -> {
            RoleListVo roleListVo = new RoleListVo();
            BeanUtils.copyProperties(role , roleListVo);
            // 获取角色权限
            roleListVo.setMenuVos(roleMenuService.getRoleMenu(role.getRoleId()));
            roleListVo.setCheckedMenus(Arrays.stream(role.getCheckedMenus().split(",")).mapToInt(Integer::valueOf).toArray());
            roleListVos.add(roleListVo);
        });
        return roleListVos;
    }

    @Override
    public Boolean addRole(AddRoleReq request) {
        Role role = new Role();
        BeanUtils.copyProperties(request , role);
        role.setCreateTime(DateUtil.getCurrentDateTime());
        boolean res = save(role);
        if (!res) {
            return false;
        }
        return roleMenuService.setRoleMenu(role.getRoleId(), request.getMenus());
    }

    @Override
    public Boolean editRole(AddRoleReq request) {
        Role role = new Role();
        BeanUtils.copyProperties(request , role);
        role.setUpdateTime(DateUtil.getCurrentDateTime());
        boolean res = updateById(role);
        if (!res) {
            return false;
        }
        return roleMenuService.setRoleMenu(role.getRoleId(), request.getMenus());
    }

    @Override
    public Boolean delRole(String ids) {
        return removeBatchByIds(Arrays.asList(ids.split(",")));
    }
}
