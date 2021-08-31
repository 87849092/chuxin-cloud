package com.chuxin.chuxincommon.core.utils.robot;

import com.alibaba.fastjson.JSONObject;

/**
 * @author 初心
 * @create 2021-08-31 20:02
 */
public class LinkMessage extends BaseMessage {
    private final String content;
    private final String title;
    private final String messageUrl;

    public LinkMessage(String content, String title, String messageUrl) {
        this.content = content;
        this.title = title;
        this.messageUrl = messageUrl;
    }

    @Override
    public String toRequestBody() {
        JSONObject msgBody = new JSONObject(3);
        // 消息类型为 text
        msgBody.put("msgtype", DingTaskDTO.MSG_TYPE_LINK);
        // 消息内容
        JSONObject text = new JSONObject(3);
        text.put("text", content);
        text.put("title", title);
        text.put("messageUrl", messageUrl);
        msgBody.put(DingTaskDTO.MSG_TYPE_LINK, text);
        return msgBody.toString();
    }
}
