package run.gocli.utils;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;

/**
 * 字符串
 */
public class StrUtil {
    /**
     * 获取随机字符串
     * @return String
     */
    public static String generateNonceStr(Integer bit) {
        // 字符串
        String str = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz!@#$%^&*()";
        Random random = new SecureRandom();
        char[] nonceChars = new char[bit];
        // 随机
        for (int index = 0; index < nonceChars.length; ++index) {
            nonceChars[index] = str.charAt(random.nextInt(str.length()));
        }
        // 返回随机字符串
        return new String(nonceChars);
    }

    /**
     * 创建随机卡号
     * @param bit 位数
     * @return String
     */
    public static String buildCardNo(Integer bit) {
        // 字符串
        String str = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ";
        Random random = new SecureRandom();
        char[] nonceChars = new char[bit];
        // 随机
        for (int index = 0; index < nonceChars.length; ++index) {
            nonceChars[index] = str.charAt(random.nextInt(str.length()));
        }
        // 返回随机字符串
        return new String(nonceChars);
    }

    /**
     * MD5加密
     * @param str String
     * @return String
     */
    public static String md5(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取uuid
     * @return String
     */
    public static String getUuid() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        return str.replace("-", "");
    }

    /**
     * 获取域名
     * @param httpServletRequest
     * @return
     */
    public static String getDomain(HttpServletRequest httpServletRequest) {
        String port = String.valueOf(httpServletRequest.getServerPort());
        String scheme = httpServletRequest.getScheme();
        String server = httpServletRequest.getServerName();
        String url;
        if (port.equals("443")) {
            url = scheme + "s://" + server;
        } else if (port.equals("80")) {
            url = scheme + "://" + server;
        } else {
            url = scheme + "://" + server + ":" + port;
        }
        return url;
    }

    // 获取客户端ip
    public static String getIpAddress(HttpServletRequest request) {
        String ipAddress = null;
        try {
            ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                if (ipAddress.equals("127.0.0.1")) {
                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                        ipAddress = inet.getHostAddress();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (ipAddress != null && ipAddress.length() > 15) {
                if (ipAddress.indexOf(",") > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
        } catch (Exception e) {
            ipAddress="";
        }
        return ipAddress;
    }

    public static String makeToken() {
        return md5( getUuid() + generateNonceStr(8));
    }
}
