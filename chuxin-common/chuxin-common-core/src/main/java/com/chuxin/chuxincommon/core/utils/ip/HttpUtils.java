package com.chuxin.chuxincommon.core.utils.ip;


import com.chuxin.chuxincommon.core.utils.StringUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author 初心
 * @create 2021-08-27 16:06
 */
public class HttpUtils {
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

    /**
     * 获取url host
     * @param url url地址
     * @param containsSchema
     * @return
     */
    private static String getHost(String url, boolean containsSchema) {
        if (StringUtils.isBlank(url)) {
            throw new IllegalArgumentException("Illegal url: " + url);
        }
        int start = containsSchema ? 0 : (url.startsWith("https://") ? 8 : 7);
        int thirdSlashIndex = url.indexOf("/", 8);
        if (thirdSlashIndex > 0) {
            return url.substring(start, thirdSlashIndex);
        } else {
            return url.substring(start);
        }
    }

    public static String getDomainByHost(String host) {
        if (StringUtils.isBlank(host)) {
            return host;
        }
        String[] vals = host.split("\\.");
        String newDomain = host;
        if (vals.length >= 2) {
            newDomain = vals[vals.length - 2] + "." + vals[vals.length - 1];
        }
        return newDomain;
    }

    public static Map<String, String> getHeaders(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement().toString().toLowerCase();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }

    public static String getScheme(HttpServletRequest request) {
        Map<String, String> headerNames = getHeaders(request);
        String protocol = headerNames.get("x-forwarded-proto");
        if (StringUtils.isBlank(protocol)) {
            return request.getScheme();
        }
        return protocol;
    }

    /**
     * 从request中获取参数key/value，若同一个key对应多个value，则仅保留第一个value
     *
     * @param request HttpServletRequest
     * @return Map String->String
     */
    public static Map<String, String> getParameterMap(HttpServletRequest request, boolean excludeFile) {
        if (request == null) {
            return Collections.emptyMap();
        }

        Map<String, String[]> parameterMap = new HashMap<>();
        for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
            parameterMap.put(entry.getKey(), entry.getValue());
        }

        if (excludeFile && request instanceof MultipartHttpServletRequest) {
            Iterator<String> fileNames = ((MultipartHttpServletRequest) request).getFileNames();
            while (fileNames.hasNext()) {
                parameterMap.remove(fileNames.next());
            }
        }

        return getParameterMap(parameterMap);
    }

    public static Map<String, String> getParameterMap(Map<String, String[]> paramMap) {
        if (MapUtils.isEmpty(paramMap)) {
            return Collections.emptyMap();
        }

        Map<String, String> parameterMap = new HashMap<>();
        for (Map.Entry<String, String[]> entry : paramMap.entrySet()) {
            parameterMap.put(entry.getKey(), entry.getValue()[0]);
        }
        return parameterMap;
    }
}
