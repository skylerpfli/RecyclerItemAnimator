package com.example.recycleritemanimator.msg;

/**
 * @author lipengfei
 * @date 2019/12/13
 * @email 1219742019@qq.com
 * @description 机器人文本消息
 */
public class RobotTextMessage extends ChatMessage {

    RobotTextMessage(String t) {
        super(ChatMessage.MESSAGE_TEXT_ROBOT);
        text = t;
        owner = ChatMessage.OWNER_ROBOT;
    }

}
