/**
 * Class UserId
 * Author: cfn <cfn@leapy.cn>
 * Date 2022/7/11
 */
package run.gocli.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface AccountId {
}
