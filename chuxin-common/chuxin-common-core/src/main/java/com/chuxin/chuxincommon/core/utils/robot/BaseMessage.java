package com.chuxin.chuxincommon.core.utils.robot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author 初心
 * @create 2021-08-31 20:00
 */
public abstract class BaseMessage {
    private List<String> atMobiles;

    private boolean atAll;

    public static final int EMPTY_LIST_SIZE = 1;


    /**
     * 转为Json格式的请求体
     * @return 当前消息对应的消息体
     */
    public abstract String toRequestBody();

    public void setAtMobiles(List<String> atMobiles) {
        if (atMobiles == null) {
            atMobiles = new ArrayList<>(EMPTY_LIST_SIZE);
        }
        this.atMobiles = atMobiles;
    }
    public void setAtAll(boolean atAll) {
        this.atAll = atAll;
    }

    public List<String> getAtMobiles() {
        return atMobiles != null ? atMobiles : Collections.emptyList();
    }

    public boolean isAtAll() {
        return atAll;
    }
}
