package com.example.recycleritemanimator.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.recycleritemanimator.R;
import com.example.recycleritemanimator.msg.ChatMessage;
import com.example.recycleritemanimator.msg.RobotPicMessage;
import com.example.recycleritemanimator.msg.RobotTextMessage;

import java.util.List;


/**
 * @author lipengfei
 * @date 2019/8/20
 * @email ly-lipengfei@dfl.com.cn
 * @description 录音界面显示的adapter
 */
public class VoiceChatAdapter extends RecyclerView.Adapter<VoiceChatAdapter.ViewHolder> {
    private Context mContext;
    private static final String TAG = "VoiceChatAdapter";
    private List<ChatMessage> mChatMsgs;

    private final static int TYPE_HOLDER_TEXT = 101;//文本类消息
    private final static int TYPE_HOLDER_PIC = 102; //图片类消息

    public VoiceChatAdapter(Context context, List<ChatMessage> chatMsgs) {
        mContext = context;
        mChatMsgs = chatMsgs;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        int type;
        View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
        }
    }

    static class TextHolder extends ViewHolder {
        TextView mContent;

        TextHolder(@NonNull View itemView) {
            super(itemView);
            type = TYPE_HOLDER_TEXT;
            mContent = (TextView) itemView.findViewById(R.id.tv_content_say);
        }
    }

    static class PicHolder extends ViewHolder {
        ImageView mPic;

        public PicHolder(@NonNull View itemView) {
            super(itemView);
            type = TYPE_HOLDER_PIC;
            mPic = (ImageView) itemView.findViewById(R.id.iv_sys_say);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
        View itemView;
        ViewHolder holder = null;
        switch (type) {
            case ChatMessage.MESSAGE_TEXT_MINE:
                itemView = LayoutInflater.from(mContext).inflate(R.layout.chat_from_mine_text, viewGroup, false);
                holder = new TextHolder(itemView);
                break;
            case ChatMessage.MESSAGE_TEXT_ROBOT:
                itemView = LayoutInflater.from(mContext).inflate(R.layout.chat_from_robot_text, viewGroup, false);
                holder = new TextHolder(itemView);
                break;
            case ChatMessage.MESSAGE_PIC_ROBOT:
                itemView = LayoutInflater.from(mContext).inflate(R.layout.chat_from_robot_pic, viewGroup, false);
                holder = new PicHolder(itemView);
                break;
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int positon) {
        ChatMessage msg = mChatMsgs.get(positon);
        switch (viewHolder.type) {
            case TYPE_HOLDER_TEXT:
                ((TextHolder) viewHolder).mContent.setText(msg.text);
                break;
            case TYPE_HOLDER_PIC:
                ((PicHolder) viewHolder).mPic.setBackground(((RobotPicMessage) msg).drawable);
                break;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mChatMsgs.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mChatMsgs.get(position).type;
    }

}