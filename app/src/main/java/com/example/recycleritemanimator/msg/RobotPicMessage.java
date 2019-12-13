package com.example.recycleritemanimator.msg;

import android.graphics.drawable.Drawable;

/**
 * @author lipengfei
 * @date 2019/12/13
 * @email 1219742019@qq.com
 * @description 机器人图片消息
 */
public class RobotPicMessage extends ChatMessage {

    public Drawable drawable;

    RobotPicMessage(Drawable d) {
        super(ChatMessage.MESSAGE_PIC_ROBOT);
        drawable = d;
        owner = ChatMessage.OWNER_ROBOT;
    }

}
