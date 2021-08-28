package com.chuxin.chuxincommon.core.utils.ip;


import javax.servlet.http.HttpServletRequest;

/**
 * @author 初心
 * @create 2021-08-27 16:06
 */
public class IpUtils {
    public static final String NATIVE_IP_HOSTS = "0:0:0:0:0:0:0:1";
    public static final String NATIVE_IP_LOCATION = "127.0.0.1";
    public static final String UNKNOWN = "unknown";

    private static String getIpAddr(HttpServletRequest request) {
        {
            String ip = null;
            String ipAddresses = request.getHeader("X-Forwarded-For");
            if (ipAddresses == null || ipAddresses.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddresses)) {
                ipAddresses = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddresses == null || ipAddresses.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddresses)) {
                ipAddresses = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddresses == null || ipAddresses.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddresses)) {
                ipAddresses = request.getHeader("HTTP_CLIENT_IP");
            }
            if (ipAddresses == null || ipAddresses.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddresses)) {
                ipAddresses = request.getHeader("X-Real-IP");
            }
            if (ipAddresses != null && ipAddresses.length() != 0) {
                ip = ipAddresses.split(",")[0];
            }
            if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddresses)) {
                ip = request.getRemoteAddr();
            }
            return NATIVE_IP_HOSTS.equals(ip) ? NATIVE_IP_LOCATION : ip;
        }
    }
}
