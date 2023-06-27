package run.gocli.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import run.gocli.admin.req.*;
import run.gocli.admin.vo.*;
import run.gocli.core.server.*;
import run.gocli.utils.AuthPermission;
import run.gocli.utils.R;

import java.util.List;

@Api(tags = "管理端接口")
@RestController
@RequestMapping("/admin")
@Slf4j
public class SystemController {
    @Autowired
    private IMenuService menuService;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IAccountService accountService;
    @Autowired
    private IAccountRoleService accountRoleService;
    @Autowired
    private IDeptServer deptServer;

    @GetMapping("/menu/list")
    @ApiOperation(value = "获取菜单列表接口", tags = "菜单管理")
    @AuthPermission(name = "菜单列表", auth = "admin:menu:list")
    public R<List<MenuListVo>> menuList() {
        List<MenuListVo> menuListVos = menuService.getMenuList();
        return R.success(menuListVos);
    }

    @PostMapping("/menu/add")
    @ApiOperation(value = "添加菜单接口", tags = "菜单管理")
    @AuthPermission(name = "添加菜单", auth = "admin:menu:add")
    public R<AddMenuVo> addMenu(@Validated @RequestBody AddMenuReq request) {
        AddMenuVo addMenuVo = menuService.addMenu(request);
        return addMenuVo != null ? R.success(addMenuVo).msg("添加成功") : R.error("添加失败");
    }

    @PutMapping("/menu/edit")
    @ApiOperation(value = "修改菜单接口", tags = "菜单管理")
    @AuthPermission(name = "修改菜单", auth = "admin:menu:edit")
    public R<Boolean> editMenu(@Validated @RequestBody AddMenuReq request) {
        Boolean res = menuService.editMenu(request);
        return res ? R.success(true).msg("修改成功") : R.error("修改失败");
    }

    @DeleteMapping("/menu/del")
    @ApiOperation(value = "删除菜单接口", tags = "菜单管理")
    @AuthPermission(name = "删除菜单", auth = "admin:menu:del")
    public R<Boolean> delMenu(String ids) {
        Boolean res = menuService.delMenu(ids);
        return res ? R.success(true).msg("删除成功") : R.error("删除失败");
    }

    @GetMapping("/role/list")
    @ApiOperation(value = "角色列表接口", tags = "角色管理")
    @AuthPermission(name = "角色列表", auth = "admin:role:list")
    public R<List<RoleListVo>> roleList(RoleReq request) {
        List<RoleListVo> roleListVos = roleService.getRoleList(request);
        return R.success(roleListVos).msg("角色列表");
    }

    @PostMapping("/role/add")
    @ApiOperation(value = "添加角色接口", tags = "角色管理")
    @AuthPermission(name = "添加角色", auth = "admin:role:add")
    public R<Boolean> roleAdd(@Validated @RequestBody AddRoleReq request) {
        Boolean res = roleService.addRole(request);
        return res ? R.success(true).msg("添加成功") : R.error("添加失败");
    }

    @PutMapping("/role/edit")
    @ApiOperation(value = "修改角色接口", tags = "角色管理")
    @AuthPermission(name = "修改角色", auth = "admin:role:edit")
    public R<Boolean> roleEdit(@Validated @RequestBody AddRoleReq request) {
        Boolean res = roleService.editRole(request);
        return res ? R.success(true).msg("修改成功") : R.error("修改失败");
    }

    @DeleteMapping("/role/del")
    @ApiOperation(value = "删除角色接口", tags = "角色管理")
    @AuthPermission(name = "删除角色", auth = "admin:role:del")
    public R<Boolean> roleDel(String ids) {
        Boolean res = roleService.delRole(ids);
        return res ? R.success(true).msg("删除成功") : R.error("删除失败");
    }

    @GetMapping("/account/list")
    @ApiOperation(value = "管理员列表接口", tags = "管理员管理")
    @AuthPermission(name = "管理员列表", auth = "admin:account:list")
    public R<List<AccountListVo>> adminList(AccountReq request) {
        IPage<AccountListVo> adminIPage = accountService.getAccountList(request);
        for (AccountListVo adminListVo: adminIPage.getRecords()) {
            adminListVo.setRoleLists(accountRoleService.getAccountRole(adminListVo.getAccountId()));
        }
        return R.success(adminIPage.getRecords()).count(adminIPage.getTotal());
    }

    @PostMapping("/account/add")
    @ApiOperation(value = "添加管理员", tags = "管理员管理")
    @AuthPermission(name = "添加管理员", auth = "admin:admin:add")
    public R<Boolean> addAdmin(@Validated @RequestBody AddAccountReq request) {
        Boolean res = accountService.addAccount(request);
        return res ? R.success(true).msg("添加成功") : R.error("添加失败");
    }

    @PutMapping("/account/edit")
    @ApiOperation(value = "修改管理员", tags = "管理员管理")
    @AuthPermission(name = "修改管理员", auth = "admin:account:edit")
    public R<Boolean> editAdmin(@Validated @RequestBody AddAccountReq request) {
        Boolean res = accountService.editAccount(request);
        return res ? R.success(true).msg("修改成功") : R.error("修改失败");
    }

    @DeleteMapping("/account/del")
    @ApiOperation(value = "删除管理员", tags = "管理员管理")
    @AuthPermission(name = "删除管理员", auth = "admin:account:del")
    public R<Boolean> delAdmin(String ids) {
        Boolean res = accountService.delAccount(ids);
        return res ? R.success(true).msg("删除成功") : R.error("删除失败");
    }

    @GetMapping("/dept/list")
    @ApiOperation(value = "部门列表接口", tags = "部门管理")
    @AuthPermission(name = "部门列表", auth = "admin:dept:list")
    public R<List<DeptListVo>> deptList(DeptReq request) {
        List<DeptListVo> deptListVos = deptServer.getDeptList(request);
        return R.success(deptListVos).msg("部门列表");
    }

    @PostMapping("/dept/add")
    @ApiOperation(value = "添加部门接口", tags = "部门管理")
    @AuthPermission(name = "添加部门", auth = "admin:dept:add")
    public R<Boolean> depteAdd(@Validated @RequestBody AddDeptReq request) {
        Boolean res = deptServer.addDept(request);
        return res ? R.success(true).msg("添加成功") : R.error("添加失败");
    }

    @PutMapping("/dept/edit")
    @ApiOperation(value = "修改部门接口", tags = "部门管理")
    @AuthPermission(name = "修改部门", auth = "admin:dept:edit")
    public R<Boolean> deptEdit(@Validated @RequestBody AddDeptReq request) {
        Boolean res = deptServer.editDept(request);
        return res ? R.success(true).msg("修改成功") : R.error("修改失败");
    }

    @DeleteMapping("/dept/del")
    @ApiOperation(value = "删除部门接口", tags = "部门管理")
    @AuthPermission(name = "删除部门", auth = "admin:dept:del")
    public R<Boolean> deptDel(String ids) {
        Boolean res = deptServer.delDept(ids);
        return res ? R.success(true).msg("删除成功") : R.error("删除失败");
    }
}
