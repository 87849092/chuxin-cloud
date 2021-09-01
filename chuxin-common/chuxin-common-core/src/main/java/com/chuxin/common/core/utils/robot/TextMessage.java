package com.chuxin.common.core.utils.robot;

import com.alibaba.fastjson.JSONObject;

/**
 * @author 初心
 * @create 2021-08-31 20:01
 */
public class TextMessage extends BaseMessage {
    private final String content;

    public TextMessage(String content) {
        super();
        this.content = content;
    }

    @Override
    public String toRequestBody() {
        JSONObject msgBody = new JSONObject(3);
        // 消息类型为 text
        msgBody.put("msgtype", DingTaskDTO.MSG_TYPE_TEXT);
        // 消息内容
        JSONObject text = new JSONObject(1);
        text.put("content", content);
        msgBody.put("text", text);
        // 要 at 的人的电话号码
        JSONObject at = new JSONObject(2);
        at.put("isAtAll", isAtAll());
        at.put("atMobiles", getAtMobiles());
        msgBody.put("at", at);
        return msgBody.toString();
    }
}
