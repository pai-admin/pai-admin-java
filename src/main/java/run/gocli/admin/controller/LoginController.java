package run.gocli.admin.controller;

import com.wf.captcha.ArithmeticCaptcha;
import com.wf.captcha.base.Captcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import run.gocli.admin.req.LoginReq;
import run.gocli.admin.vo.CodeVo;
import run.gocli.admin.vo.LoginVo;
import run.gocli.component.AppComponent;
import run.gocli.core.entity.Account;
import run.gocli.core.server.IAccountService;
import run.gocli.core.server.RedisService;
import run.gocli.utils.AuthPermission;
import run.gocli.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import run.gocli.utils.StrUtil;

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
    public R<LoginVo> login(@RequestBody LoginReq loginReq){
        // 验证验证码
        String code = redisService.get(appComponent.getTokenKey() + loginReq.getVerifyId());
        // 验证成功删除验证码
        redisService.delete(appComponent.getTokenKey() + loginReq.getVerifyId());
        if (code.equals("")) {
            return R.error("验证码已过期");
        }
        if (!code.equals(StrUtil.md5(loginReq.getVerifyCode()))) {
            return R.error("验证码不正确");
        }
        // 查询管理员信息
        Account account = accountService.getByUsername(loginReq.getUsername());
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
        return R.success(loginVo);
    }
}
