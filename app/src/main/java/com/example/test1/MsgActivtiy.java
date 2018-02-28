package com.example.test1;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/2/27.
 */

public class MsgActivtiy extends BaseActivity {

    private List<Msg> msgList = new ArrayList<>();

    private EditText inputText;

    private Button send;

    private RecyclerView msgRecyclerView;

    private MsgAdapter msgAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg);
        initMsg();
        inputText = (EditText) findViewById(R.id.msg_edit);
        send = (Button) findViewById(R.id.msg_send);
        msgRecyclerView = (RecyclerView) findViewById(R.id.msg_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        msgRecyclerView.setLayoutManager(layoutManager);
        msgAdapter = new MsgAdapter(msgList);
        msgRecyclerView.setAdapter(msgAdapter);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = inputText.getText().toString();
                if (!"".equals(content)){
                    Msg msg = new Msg(content,Msg.TYPE_SENT);
                    msgList.add(msg);
                    msgAdapter.notifyItemInserted(msgList.size() -1);
                    msgRecyclerView.scrollToPosition(msgList.size() -1);
                    inputText.setText("");
                }else {
                    Toast.makeText(MsgActivtiy.this,"消息不能为空",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initMsg(){

        Msg msg = new Msg("Hello guy. ",Msg.TYPE_RECEIVED);
        msgList.add(msg);
        Msg msg1 = new Msg("Hello , who is that ? ",Msg.TYPE_SENT);
        msgList.add(msg1);
        Msg msg2 = new Msg("This is Tom. Nice to meet you. ",Msg.TYPE_RECEIVED);
        msgList.add(msg2);
    }
}
