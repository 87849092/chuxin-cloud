package com.chuxin.common.knife4j.annotation;

import com.chuxin.common.knife4j.config.Knife4jConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author 初心
 * @date 2021/8/30 21:05
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({ Knife4jConfiguration.class })
@Deprecated
public @interface EnableCustomKnife4j {
}
