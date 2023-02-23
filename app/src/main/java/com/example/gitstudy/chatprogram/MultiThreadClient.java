package com.example.gitstudy.chatprogram;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.gitstudy.R;

import java.io.OutputStream;
import java.net.Socket;

public class MultiThreadClient extends AppCompatActivity {
    EditText input, show;
    Button send;
    OutputStream os;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multithreadclient);
        input=findViewById(R.id.input);
        show=findViewById(R.id.show);
        send=findViewById(R.id.send);
        Socket s;
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg)
            {
                // 如果消息来自于子线程
                if (msg.what == 0x123)
                {
                    // 将读取的内容追加显示在文本框中
                    show.append("\n" + msg.obj.toString());
                }
            }
        };
        try {
            s = new Socket("127.0.0.1", 30000);
            // 客户端启动ClientThread线程不断读取来自服务器的数据
            new Thread(new ClientThread(s, handler)).start();
            os = s.getOutputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
        send.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try {
                    // 将用户在文本框内输入的内容写入网络
                    os.write((input.getText().toString() + "\r\n").getBytes("utf-8"));
                    // 清空input文本框
                    input.setText("");
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }
}