/**
 * Class AuthPermission
 * Author: cfn <cfn@leapy.cn>
 * Date 2022/7/11
 */
package run.gocli.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthPermission {
    // 操作名称
    String name() default "";
    // 权限标识
    String auth() default "*";
    // 是否需要登录
    boolean needLogin() default true;
    // 是否需要权限
    boolean needAuth() default true;
}
