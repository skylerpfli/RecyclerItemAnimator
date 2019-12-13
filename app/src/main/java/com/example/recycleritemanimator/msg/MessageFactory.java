package com.example.recycleritemanimator.msg;

import android.graphics.drawable.Drawable;

/**
 * @author lipengfei
 * @date 2019/1/25
 * @email ly-lipengfei@dfl.com.cn
 * @description Message生产工厂
 */
public class MessageFactory {

    //机器人文本消息
    public static ChatMessage getRobotTextMessage(String text) {
        return new RobotTextMessage(text);
    }

    //我的文本消息
    public static ChatMessage getMineTextMessage(String text) {
        return new MineTextMessage(text);
    }

    //机器人的图片消息
    public static ChatMessage getRobotPicMessage(Drawable drawable) {
        return new RobotPicMessage(drawable);
    }
}
