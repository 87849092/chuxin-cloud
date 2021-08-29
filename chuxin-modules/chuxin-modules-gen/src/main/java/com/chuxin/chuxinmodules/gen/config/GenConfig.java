package com.chuxin.chuxinmodules.gen.config;

import lombok.Getter;
import lombok.Setter;

/**
 * @author 初心
 * @date 2021/8/29 10:24
 */
@Getter
@Setter
public class GenConfig {
    /**
     * 作者
     */
    public static String author;
    /**
     * 包名路径
     */
    public static String packageName;
    /**
     * 去掉表前缀
     */
    public static boolean authRemoveTablePre = false;
    /**
     * 表前缀
     */
    public static String tablePrefix;
}
