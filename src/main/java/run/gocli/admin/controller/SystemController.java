package run.gocli.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import run.gocli.admin.req.*;
import run.gocli.admin.vo.*;
import run.gocli.component.AppComponent;
import run.gocli.core.entity.DictData;
import run.gocli.core.server.*;
import run.gocli.utils.AuthPermission;
import run.gocli.utils.R;
import run.gocli.utils.StrUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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
    @Autowired
    private AppComponent appComponent;
    @Autowired
    private IDictTypeService dictTypeService;
    @Autowired
    private IDictDataService dictDataService;

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
        for (AccountListVo adminListVo : adminIPage.getRecords()) {
            adminListVo.setRoles(accountRoleService.getAccountRole(adminListVo.getAccountId()));
        }
        return R.success(adminIPage.getRecords()).count(adminIPage.getTotal());
    }

    @PostMapping("/account/add")
    @ApiOperation(value = "添加管理员", tags = "管理员管理")
    @AuthPermission(name = "添加管理员", auth = "admin:account:add")
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

    @PostMapping(value = "/upload/file")
    @ApiOperation(value = "文件上传", tags = "公共接口")
    @AuthPermission(name = "文件上传", needAuth = false)
    public R<UploadFileVo> upload(@RequestParam("file") MultipartFile multipartFile) {
        if (multipartFile.isEmpty() || Objects.equals(multipartFile.getOriginalFilename(), "")) {
            return R.error("文件内容为空");
        }
        // 文件上传路径
        String filePath = "upload" + File.separator + (new SimpleDateFormat("yyyy")).format(new Date()) + File.separator + (new SimpleDateFormat("MM")).format(new Date()) + File.separator + (new SimpleDateFormat("dd")).format(new Date());
        // 获取文件后缀
        String ext = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
        // 文件名
        String fileName = StrUtil.getUuid() + ext;
        // 保存文件
        if (!saveImg(multipartFile, filePath, fileName)) {
            return R.error("上传失败, 请重试");
        }
        // 上传文件返回
        UploadFileVo uploadFileVo = new UploadFileVo();
        uploadFileVo.setName(fileName);
        uploadFileVo.setPath(filePath + File.separator + fileName);
        uploadFileVo.setSize(multipartFile.getSize());
        return R.success(uploadFileVo).msg("上传成功");
    }

    // 保存图片
    private Boolean saveImg(MultipartFile multipartFile, String path, String fileName) {
        try {
            File file = new File(appComponent.getPath() + path);
            // 文件不存在创建
            if (!file.exists() && !file.isDirectory()) {
                file.mkdirs();
            }
            FileInputStream fileInputStream = (FileInputStream) multipartFile.getInputStream();
            // 保存文件 路径 + 文件名
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file + File.separator + fileName));
            byte[] bs = new byte[1024];
            int len;
            while ((len = fileInputStream.read(bs)) != -1) {
                bos.write(bs, 0, len);
            }
            bos.flush();
            bos.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @GetMapping("/download/{file}")
    @ApiOperation(value = "文件下载", tags = "公共接口")
    @AuthPermission(name = "文件下载", needAuth = false)
    public String download(@PathVariable("file") String fileName, HttpServletResponse response) throws IOException {
        if (fileName == null) return null;
        //设置文件路径
        File file = new File(appComponent.getPath() + fileName);
        if (file.exists()) {
            response.setContentType("application/force-download");
            response.addHeader("Content-Disposition", "attachment;fileName="
                    + fileName);
            byte[] buffer = new byte[1024];
            try (FileInputStream fis = new FileInputStream(file); BufferedInputStream bis = new BufferedInputStream(fis)) {
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                return "";
            } catch (Exception e) {
                log.error("下载文件失败：" + e.getMessage());
            }
        }
        return "fail";
    }

    @GetMapping("/dict_type/list")
    @ApiOperation(value = "字典列表接口", tags = "字典管理")
    @AuthPermission(name = "字典列表", auth = "admin:dict_type:list")
    public R<List<DictTypeListVo>> dictTypeList() {
        List<DictTypeListVo> dictTypeListVos = dictTypeService.getDictType();
        return R.success(dictTypeListVos).msg("字典列表");
    }

    @PostMapping("/dict_type/add")
    @ApiOperation(value = "添加字典接口", tags = "字典管理")
    @AuthPermission(name = "添加字典", auth = "admin:dict_type:add")
    public R<AddDictTypeVo> dictTypeAdd(@Validated @RequestBody AddDictTypeReq request) {
        AddDictTypeVo addDictType = dictTypeService.addDictType(request);
        return addDictType != null ? R.success(addDictType).msg("添加成功") : R.error("添加失败");
    }

    @PutMapping("/dict_type/edit")
    @ApiOperation(value = "修改字典接口", tags = "字典管理")
    @AuthPermission(name = "修改字典", auth = "admin:dict_type:edit")
    public R<Boolean> dictTypeEdit(@Validated @RequestBody AddDictTypeReq request) {
        Boolean res = dictTypeService.editDictType(request);
        return res ? R.success(true).msg("修改成功") : R.error("修改失败");
    }

    @DeleteMapping("/dict_type/del")
    @ApiOperation(value = "删除字典接口", tags = "字典管理")
    @AuthPermission(name = "删除字典", auth = "admin:dict_type:del")
    public R<Boolean> dictTypeDel(String ids) {
        Boolean res = dictTypeService.delDictType(ids);
        return res ? R.success(true).msg("删除成功") : R.error("删除失败");
    }

    @GetMapping("/dict_data/list")
    @ApiOperation(value = "字典项目接口", tags = "字典管理")
    @AuthPermission(name = "字典列表", auth = "admin:dict_data:list")
    public R<List<DictDataListVo>> dictDataList(DictDataReq request) {
        IPage<DictData> dataIPage = dictDataService.getDictType(request);
        List<DictDataListVo> dictTypeListVos = new ArrayList<>();
        dataIPage.getRecords().forEach(dictData -> {
            DictDataListVo deptListVo = new DictDataListVo();
            BeanUtils.copyProperties(dictData , deptListVo);
            dictTypeListVos.add(deptListVo);
        });
        return R.success(dictTypeListVos).count(dataIPage.getTotal());
    }

    @PostMapping("/dict_data/add")
    @ApiOperation(value = "添加字典项目接口", tags = "字典管理")
    @AuthPermission(name = "添加字典", auth = "admin:dict_data:add")
    public R<Boolean> dictDataAdd(@Validated @RequestBody AddDictDataReq request) {
        Boolean res = dictDataService.addDictType(request);
        return res ? R.success(true).msg("添加成功") : R.error("添加失败");
    }

    @PutMapping("/dict_data/edit")
    @ApiOperation(value = "修改字典项目接口", tags = "字典管理")
    @AuthPermission(name = "修改字典", auth = "admin:dict_data:edit")
    public R<Boolean> dictDataEdit(@Validated @RequestBody AddDictDataReq request) {
        Boolean res = dictDataService.editDictType(request);
        return res ? R.success(true).msg("修改成功") : R.error("修改失败");
    }

    @DeleteMapping("/dict_data/del")
    @ApiOperation(value = "删除字典项目接口", tags = "字典管理")
    @AuthPermission(name = "删除字典", auth = "admin:dict_data:del")
    public R<Boolean> dictDataDel(String ids) {
        Boolean res = dictDataService.delDictType(ids);
        return res ? R.success(true).msg("删除成功") : R.error("删除失败");
    }
}
