package com.example.recycleritemanimator;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.recycleritemanimator.adapter.VoiceChatAdapter;
import com.example.recycleritemanimator.animator.UpShowAnimator;
import com.example.recycleritemanimator.msg.ChatMessage;
import com.example.recycleritemanimator.msg.MessageFactory;
import com.example.recycleritemanimator.utils.TextUtils;
import com.example.recycleritemanimator.widget.MicView;
import com.example.recycleritemanimator.widget.VerticalItemDecoration;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.animators.BaseItemAnimator;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

/**
 * @author lipengfei
 * @date 2019/12/13
 * @email 1219742019@qq.com
 * @description 自定义RecyclerView Item动画示例
 */
public class MainActivity extends AppCompatActivity {

    //聊天RecyclerView
    private RecyclerView mRecyclerView;
    private VoiceChatAdapter mAdapter;
    private List<ChatMessage> mChatContents;

    //底部的麦克风按钮
    private MicView mMicView;

    //安排好的聊天对话
    private String[] mChatStrs;

    //语句形如：robot:你好，我是机器人
    private static final String PREFIX_STR_ROTBOT = "robot";//机器人说话前缀
    private static final String PREFIX_STR_MINE = "mine"; //我说话的前缀
    private static final String SEPARATOR = ":";//分隔符

    //特殊消息字符串
    private static final String SPECIAL_STR_FUNNY = "[滑稽]";
    private static final String SPECIAL_STR_RECALL = "[撤回]";

    //每句对话的时间间隔
    private static final int MESSAGE_DELAY_TIME = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化控件
        mRecyclerView = (RecyclerView) findViewById(R.id.chat_rv);
        mMicView = (MicView) findViewById(R.id.v_Mic);
        mMicView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击麦克风，开始对话
                startChat();
            }
        });

        //初始化对话数据
        mChatStrs = getResources().getStringArray(R.array.message_say);

        //配置recyclerView
        mChatContents = new ArrayList<ChatMessage>();
        mAdapter = new VoiceChatAdapter(this, mChatContents);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new VerticalItemDecoration((int) getResources().getDimension(R.dimen.decoration)));

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        //因为有些地方需要拿尺寸，所以在这里初始化动画(非必须)


        /**
         *   ----- 以下是配置动画 -----
         * */
/*
        //加长时间的默认动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //设置添加、移除时间为1s
        mRecyclerView.getItemAnimator().setAddDuration(1000);
        mRecyclerView.getItemAnimator().setRemoveDuration(1000);
*/

/*
        //设置简单的上浮动画
        mRecyclerView.setItemAnimator(new SlideInUpAnimator());
        //动画时间设置长，便于观察
        mRecyclerView.getItemAnimator().setAddDuration(500);
        mRecyclerView.getItemAnimator().setRemoveDuration(500);*/

        //360度旋转上丢动画
        mRecyclerView.setItemAnimator(new UpShowAnimator(mRecyclerView.getHeight(),mMicView.getHeight()));
        //动画时间设置长，便于观察
        mRecyclerView.getItemAnimator().setAddDuration(500);
        mRecyclerView.getItemAnimator().setRemoveDuration(500);

    }

    //用于不断添加对话的handler
    private static final int WHAT = 0X1001;
    private Handler mHandler = new Handler(Looper.getMainLooper()) {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int index = (int) msg.obj;

            //如果对话未结束，就继续添加下去
            if (index < mChatStrs.length) {
                say((int) msg.obj);
            }
        }
    };

    //开始聊天
    private void startChat() {
        //清空数据
        mChatContents.clear();
        mAdapter.notifyDataSetChanged();
        mHandler.removeCallbacksAndMessages(null);
        //开始添加消息
        mHandler.obtainMessage(WHAT, 0).sendToTarget();
    }

    //说第几条消息
    private void say(int index) {
        if (index >= mChatStrs.length) {
            return;
        }

        String sayStr = mChatStrs[index];
        String[] wordsInfo = sayStr.split(SEPARATOR);
        String who = "";
        String words = "";
        if (!TextUtils.isEmpty(wordsInfo) && wordsInfo.length == 2) {
            who = wordsInfo[0];
            words = wordsInfo[1];
        }

        if (who.equals(PREFIX_STR_MINE)) {
            //添加我的文本消息
            mChatContents.add(MessageFactory.getMineTextMessage(words));
            mAdapter.notifyItemChanged(mChatContents.size() - 1);
        } else if (who.equals(PREFIX_STR_ROTBOT)) {

            //添加机器人的消息
            if (words.equals(SPECIAL_STR_FUNNY)) {
                //滑稽
                mChatContents.add(MessageFactory.getRobotPicMessage(getDrawable(R.drawable.funny)));
                mAdapter.notifyItemChanged(mChatContents.size() - 1);
            } else if (words.equals(SPECIAL_STR_RECALL)) {
                //撤回
                recallRobtPic();
            } else {
                //文本
                mChatContents.add(MessageFactory.getRobotTextMessage(words));
                mAdapter.notifyItemChanged(mChatContents.size() - 1);
            }
        }

        //说下一句消息
        Message msg = mHandler.obtainMessage(WHAT, ++index);
        mHandler.sendMessageDelayed(msg, MESSAGE_DELAY_TIME);
    }

    //撤回机器人的特定消息
    private void recallRobtPic() {
        for (int i = mChatContents.size() - 1; i >= 0; i--) {

//            if (mChatContents.get(i).type == ChatMessage.MESSAGE_PIC_ROBOT) {
            if ("你好，木头".equals(mChatContents.get(i).text)) {
                mChatContents.remove(i);
                mAdapter.notifyItemRemoved(i);
                Toast toast = Toast.makeText(this, null, Toast.LENGTH_SHORT);
                toast.setText("Robot撤回了第" + (i + 1) + "条消息");
                toast.show();
                break;
            }
        }
    }
}
