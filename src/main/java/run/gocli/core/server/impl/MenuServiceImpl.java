package run.gocli.core.server.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import run.gocli.admin.req.AddMenuReq;
import run.gocli.admin.vo.AddMenuVo;
import run.gocli.admin.vo.MenuListVo;
import run.gocli.core.dao.MenuDao;
import run.gocli.core.entity.Menu;
import run.gocli.core.server.IMenuService;
import run.gocli.utils.DateUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MenuServiceImpl extends ServiceImpl<MenuDao, Menu> implements IMenuService {
    @Override
    public List<MenuListVo> getMenuList() {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("rank");
        queryWrapper.orderByAsc("id");
        List<Menu> menus = list(queryWrapper);
        List<MenuListVo> menuListVos = new ArrayList<>();
        menus.forEach(menu -> {
            MenuListVo menuListVo = new MenuListVo();
            BeanUtils.copyProperties(menu , menuListVo);
            menuListVos.add(menuListVo);
        });
        return menuListVos;
    }

    @Override
    public AddMenuVo addMenu(AddMenuReq request) {
        Menu menu = new Menu();
        BeanUtils.copyProperties(request , menu);
        menu.setCreateTime(DateUtil.getCurrentDateTime());
        boolean res = save(menu);
        AddMenuVo addMenuVo = new AddMenuVo();
        addMenuVo.setMenuId(menu.getMenuId());
        return res ? addMenuVo : null;
    }

    @Override
    public Boolean editMenu(AddMenuReq request) {
        Menu menu = new Menu();
        BeanUtils.copyProperties(request , menu);
        menu.setUpdateTime(DateUtil.getCurrentDateTime());
        return updateById(menu);
    }

    @Override
    public Boolean delMenu(String ids) {
        return removeBatchByIds(Arrays.asList(ids.split(",")));
    }
}
