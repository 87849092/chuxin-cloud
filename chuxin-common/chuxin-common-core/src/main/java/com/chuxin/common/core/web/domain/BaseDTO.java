package com.chuxin.common.core.web.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

/**
 * @author 初心
 * @date 2021/8/29 10:35
 */
@Setter
@Getter
public class BaseDTO implements Serializable {
    private static final long serialVersionUID = 8896998873102224097L;

    private String value;

    private String createByUserId;

    private Date createTime;

    private String updateByUserId;

    private Date modifiedTime;

    private Map<String, Object> params;

    public Map<String, Object> getParams() {
        if (null == params) {
            params = Collections.emptyMap();
        }
        return params;
    }
}
