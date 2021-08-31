package com.chuxin.chuxincommon.core.utils.robot;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

/**
 * @author 初心
 * @create 2021-08-31 20:01
 */
public class MarkDownMessage extends BaseMessage {
    private final String content;
    private final String title;

    public MarkDownMessage(String content, String title) {
        super();
        this.content = content;
        this.title = title;
    }

    @Override
    public String toRequestBody() {
        JSONObject msgBody = new JSONObject(3);
        // 消息类型为 markdown
        msgBody.put("msgtype", DingTaskDTO.MSG_TYPE_MARKDOWN);
        // 消息内容
        JSONObject markdown = new JSONObject(2);
        StringBuilder text = new StringBuilder(content);
        List<String> list = getAtMobiles();
        if (CollectionUtils.isNotEmpty(list)) {
            for (String value: list) {
                text.append("@").append(value);
            }
        }
        System.out.println(text);
        markdown.put("text", text);
        markdown.put("title",title);
        msgBody.put(DingTaskDTO.MSG_TYPE_MARKDOWN, markdown);
        // 要 at 的人的电话号码
        JSONObject at = new JSONObject(2);
        at.put("isAtAll", isAtAll());
        at.put("atMobiles", getAtMobiles());
        msgBody.put("at", at);
        return msgBody.toString();
    }
}
