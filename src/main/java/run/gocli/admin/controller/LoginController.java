package run.gocli.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wf.captcha.ArithmeticCaptcha;
import com.wf.captcha.base.Captcha;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import run.gocli.admin.req.*;
import run.gocli.admin.vo.*;
import run.gocli.component.AppComponent;
import run.gocli.core.entity.Account;
import run.gocli.core.entity.AccountLog;
import run.gocli.core.server.IAccountLogService;
import run.gocli.core.server.IAccountService;
import run.gocli.core.server.RedisService;
import run.gocli.utils.AccountInfo;
import run.gocli.utils.AuthPermission;
import run.gocli.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import run.gocli.utils.StrUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Api(tags = "管理端接口")
@RestController
@RequestMapping("/admin")
@Slf4j
public class LoginController {
    @Autowired
    private RedisService redisService;
    @Autowired
    private AppComponent appComponent;
    @Autowired
    private IAccountService accountService;
    @Autowired
    private IAccountLogService accountLogService;

    @GetMapping("/get-code")
    @ApiOperation(value = "获取验证码")
    @AuthPermission(name = "获取验证码", needLogin = false)
    public R<CodeVo> getCode() {
        // 获取uuid
        String uuid = StrUtil.getUuid();
        // 生成验证码
        Captcha captcha = new ArithmeticCaptcha();
        String code = captcha.text();
        // 保存验证码
        redisService.add(appComponent.getTokenKey() + uuid, StrUtil.md5(code), appComponent.getVerifyTtl(), TimeUnit.SECONDS);
        // 返回数据
        CodeVo codeVo = new CodeVo();
        codeVo.setVerifyId(uuid);
        codeVo.setBase64Content(captcha.toBase64());
        return R.success(codeVo);
    }

    @PostMapping("/login")
    @ApiOperation(value = "登录")
    @AuthPermission(name = "登录", needLogin = false)
    public R<LoginVo> login(HttpServletRequest request, @RequestBody LoginReq loginReq) {
        // 验证验证码
        String code = redisService.getObject(appComponent.getTokenKey() + loginReq.getVerifyId(), String.class);
        if (code==null) {
            return R.error("验证码已过期");
        }
        // 验证成功删除验证码
        redisService.delete(appComponent.getTokenKey() + loginReq.getVerifyId());

        if (!code.equals(StrUtil.md5(loginReq.getVerifyCode()))) {
            return R.error("验证码不正确");
        }

        // 查询管理员信息
        run.gocli.core.entity.Account account = accountService.getByUsername(loginReq.getUsername());
        if (account == null) {
            return R.error("用户名或者密码错误");
        }
        // 验证密码
        if (!Objects.equals(account.getPassword(), StrUtil.md5(loginReq.getPassword() + account.getSalt()))) {
            return R.error("用户名或者密码错误");
        }
        if (account.getStatus() != 1) {
            return R.error("账号已被禁用");
        }
        // 生成token
        String token = StrUtil.makeToken();
        redisService.add(appComponent.getTokenKey() + token, account, appComponent.getTokenTtl(), TimeUnit.SECONDS);
        LoginVo loginVo = new LoginVo();
        loginVo.setToken(token);
        // 登录日志
        accountLogService.writeLog(account, request, 200, "admin:login", "管理员登录");
        // 接口权限存储到redis中，以便鉴权
        List<String> apis = accountService.getApis(account.getAccountId());
        redisService.add(appComponent.getTokenKey() + "AUTH:" + account.getAccountId(), apis);
        return R.success(loginVo);
    }

    @GetMapping("/info")
    @ApiOperation(value = "账号信息")
    @AuthPermission(name = "账号信息", needAuth = false)
    public R<AccountVo> info(@AccountInfo Account account) {
        return R.success(accountService.getInfo(account.getAccountId()));
    }

    @PostMapping("/logout")
    @ApiOperation(value = "退出登录接口", tags = "账户登录")
    @AuthPermission(name = "退出登录", needAuth = false)
    public R<String> logout(HttpServletRequest request) {
        redisService.delete(appComponent.getTokenKey() + request.getHeader("authorization"));
        return R.success("退出成功");
    }

    @PostMapping("/editPwd")
    @ApiOperation(value = "修改密码接口", tags = "账户登录")
    @AuthPermission(name = "修改密码", needAuth = false)
    public R<String> editPwd(@AccountInfo Account account, @Validated @RequestBody EditPwdReq request) {
        if (Objects.equals(request.getNewPassword(), request.getOldPassword())) {
            return R.error("新旧密码不能相同");
        }
        Boolean res = accountService.editPwd(account.getAccountId(), request);
        return res ? R.success("修改成功") : R.error("修改失败，原密码不正确");
    }

    @GetMapping("/auth")
    @ApiOperation(value = "菜单权限接口", tags = "账户登录")
    @AuthPermission(name = "菜单权限接口", needAuth = false)
    public R<AuthVo> getMenu(@AccountInfo Account account) {
        return R.success(accountService.getAuths(account.getAccountId()));
    }

    @PostMapping("/info/edit")
    @ApiOperation(value = "保存账户信息接口", tags = "账户登录")
    @AuthPermission(name = "保存账户信息", needAuth = false)
    public R<String> saveInfo(@AccountInfo Account account, @Validated @RequestBody EditAccountReq request) {
        Boolean res = accountService.saveInfo(account.getAccountId(), request);
        return res ? R.success("修改成功") : R.error("修改失败");
    }

    @GetMapping("/log/my")
    @ApiOperation(value = "查看个人日志", tags = "账户登录")
    @AuthPermission(name = "查看个人日志", needAuth = false)
    public R<List<MyLogVo>> myLog(@AccountInfo Account account, @Validated MyLogReq request) {
        IPage<AccountLog> logIPage = accountLogService.getLog(account.getAccountId(), request);
        List<MyLogVo> myLogVos = new ArrayList<>();
        logIPage.getRecords().forEach(log -> {
            MyLogVo myLogVo = new MyLogVo();
            BeanUtils.copyProperties(log , myLogVo);
            myLogVos.add(myLogVo);
        });
        return R.success(myLogVos).count(logIPage.getTotal());
    }

    @GetMapping("/log/list")
    @ApiOperation(value = "查看日志", tags = "账户登录")
    @AuthPermission(name = "查看日志", auth = "admin:log:list")
    public R<List<LogVo>> getLog(@Validated LogReq request) {
        IPage<AccountLog> logIPage = accountLogService.getLog(request);
        List<LogVo> logVos = new ArrayList<>();
        logIPage.getRecords().forEach(log -> {
            LogVo deptListVo = new LogVo();
            BeanUtils.copyProperties(log , deptListVo);
            logVos.add(deptListVo);
        });
        return R.success(logVos).count(logIPage.getTotal());
    }

    @DeleteMapping("/log/del")
    @ApiOperation(value = "删除日志", tags = "账户登录")
    @AuthPermission(name = "删除日志", auth = "admin:log:del")
    public R<Boolean> delLog(String ids) {
        boolean res = accountLogService.delLog(ids);
        return res ? R.success(true).msg("删除成功") : R.error("删除失败");
    }
}
