package com.example.recycleritemanimator.msg;

/**
 * @author lipengfei
 * @date 2019/12/13
 * @email 1219742019@qq.com
 * @description 消息父类
 */
public class ChatMessage {
    public static final int MESSAGE_TEXT_ROBOT = 1001; //机器人的消息
    public static final int MESSAGE_TEXT_MINE = 1002;//“我”说的消息

    public static final int MESSAGE_PIC_ROBOT = 1003; //机器人发送的图片消息

    public int type;
    public int owner;
    public String text;

    //由谁说的话
    public static final int OWNER_ROBOT = 0;
    public static final int OWNER_MINE = 1;

    ChatMessage(int type) {
        this.type = type;
    }

}
