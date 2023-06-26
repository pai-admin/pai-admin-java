package run.gocli.core.server.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import run.gocli.admin.vo.RoleMenuVo;
import run.gocli.core.dao.RoleMenuDao;
import run.gocli.core.entity.RoleMenu;
import run.gocli.core.server.IRoleMenuService;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuDao, RoleMenu> implements IRoleMenuService {
    @Override
    public List<RoleMenuVo> getRoleMenu(Integer id) {
        QueryWrapper<RoleMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id", id);
        List<RoleMenu> roleMenus = list(queryWrapper);
        List<RoleMenuVo> roleMenuVos = new ArrayList<>();
        roleMenus.forEach(roleMenu -> {
            RoleMenuVo roleMenuVo = new RoleMenuVo();
            BeanUtils.copyProperties(roleMenu , roleMenuVo);
            // 获取角色权限
            roleMenuVos.add(roleMenuVo);
        });
        return roleMenuVos;
    }

    @Override
    public Boolean setRoleMenu(Integer roleId, int[] menuIds) {
        // 删除以往的数据
        removeById(roleId);
        // 重新添加数据
        List<RoleMenu> roleMenus = new ArrayList<>();
        for (Integer menuId: menuIds) {
            // 组合数据
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setRoleId(roleId);
            roleMenu.setMenuId(menuId);
            roleMenus.add(roleMenu);
        }
        return saveBatch(roleMenus);
    }
}
