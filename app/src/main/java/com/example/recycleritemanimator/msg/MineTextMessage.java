package com.example.recycleritemanimator.msg;

/**
 * @author lipengfei
 * @date 2019/12/13
 * @email 1219742019@qq.com
 * @description 我的文本消息
 */
public class MineTextMessage extends ChatMessage {

    MineTextMessage(String t) {
        super(ChatMessage.MESSAGE_TEXT_MINE);
        text = t;
        owner = ChatMessage.OWNER_MINE;
    }

}
