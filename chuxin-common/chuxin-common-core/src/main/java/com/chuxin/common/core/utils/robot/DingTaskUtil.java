package com.chuxin.common.core.utils.robot;

import okhttp3.*;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author 初心
 * @create 2021-08-31 16:26
 */
public class DingTaskUtil {

    private static final OkHttpClient client = new OkHttpClient.Builder().callTimeout(30, TimeUnit.SECONDS).build();
    private static final MediaType mediaType = MediaType.parse("application/json;charset=utf-8");

    public static boolean dingTalkText(String postUrl, String content) {
        return dingTalkText(postUrl, content, null, false);
    }
    public static boolean dingTalkText(String postUrl, String content, String secret) {
        return dingTalkText(postUrl, content, null, false, secret);
    }

    /**
     * 发送消息
     * @param url 发送地址
     * @param message 发送消息
     */
    public static void doPostJson(String url,BaseMessage message){
        try {
            RequestBody requestBody = RequestBody.create(mediaType, message.toRequestBody());
            Request request = new Request.Builder().post(requestBody).url(url).build();
            try (Response response = client.newCall(request).execute()) {
                System.out.println(response.body());
            }
        }catch (Exception ignored){

        }

    }
    public static boolean dingTalkText(String postUrl, String content, List<String> atMobiles, boolean atAll)  {
        try {
            TextMessage textMessage =buildTextMessage(content, atMobiles, atAll);
            doPostJson(postUrl, textMessage);
        }catch (Exception ignored){

        }
        return true;
    }
    public static boolean dingTalkText(String postUrl, String content, List<String> atMobiles, boolean atAll,String secret)  {
        TextMessage textMessage =buildTextMessage(content, atMobiles, atAll);
        String sign = null;
        long timestamp = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        try {
            sign = getSign(timestamp,secret);
        } catch (Exception ignored) {

        }
        doPostJson(postUrl + "&timestamp=" + timestamp + "&sign=" + sign,textMessage);
        return true;
    }

    public static void dingTalkMarkdown(String postUrl, String title, String content) {
        dingTalkMarkdown(postUrl, title, content, null, false);
    }

    public static boolean dingTalkMarkdown(String postUrl, String title, String content, List<String> atMobiles, boolean atAll)  {
        try {
            MarkDownMessage markDownMessage =buildMarkDownMessage(content, title, atMobiles, atAll);
            doPostJson(postUrl, markDownMessage);
        }catch (Exception ignored){

        }
        return true;
    }

    public static boolean dingTalkLink(String postUrl, String textContent, String title, String messageUrl) {
        LinkMessage linkMessage = new LinkMessage(textContent,title, messageUrl);
        doPostJson(postUrl, linkMessage);
        return true;
    }


    /**
     * 构建文本信息串
     * @param content 内容
     * @param atMobiles 艾特的手机号
     * @param atAll 是否艾特全体 true= 艾特全体, false = 否 默认为false
     * @return TextMessage
     */
    public static TextMessage buildTextMessage(String content, List<String> atMobiles,boolean atAll) {
        TextMessage textMessage = new TextMessage(content);
        textMessage.setAtAll(atAll);
        textMessage.setAtMobiles(atMobiles);
        return textMessage;
    }
    /**
     * 构建markDown信息串
     * @param content 内容
     * @param title markDown标题
     * @param atMobiles 艾特的手机号
     * @param atAll 是否艾特全体 true= 艾特全体, false = 否 默认为false
     * @return TextMessage
     */
    public static MarkDownMessage buildMarkDownMessage(String content, String title, List<String> atMobiles, boolean atAll) {
        MarkDownMessage markDownMessage = new MarkDownMessage(content, title);
        markDownMessage.setAtAll(atAll);
        markDownMessage.setAtMobiles(atMobiles);
        return markDownMessage;
    }

    /**
     *
     * 自定义机器人获取签名
     * 创建机器人时选择加签获取secret以xxxx开头
     *
     * @param timestamp
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     * @throws InvalidKeyException
     */
    private static String getSign(Long timestamp, String secret) throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException {
        String stringToSign = timestamp + "\n" + secret;
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
        byte[] signData = mac.doFinal(stringToSign.getBytes(StandardCharsets.UTF_8));
        String sign = URLEncoder.encode(new String(Base64.encodeBase64(signData)), "UTF-8");
        return sign;
    }

}
