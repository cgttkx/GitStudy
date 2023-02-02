package com.example.gitstudy;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity6 extends AppCompatActivity {

    private HorVoiceView2 voiceView2;
    static boolean isPlay = true;
    static boolean flag = true;
    private MediaPlayer mediaPlayer;
    private SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        voiceView2 = (HorVoiceView2) findViewById(R.id.horvoiceview2);
        seekBar = (SeekBar)this.findViewById(R.id.seekBar2);
        //按钮初始默认是"播放"
        ImageButton button1 = (ImageButton)findViewById(R.id.btn);
        button1.setImageResource(R.mipmap.play);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            //当进度改变,第三个参数为true表示用户拖动，为false表示代码设置进度
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) { }
            //当开始拖动
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }
            //当拖动停止的时候调用
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity6.this,"拖动",Toast.LENGTH_SHORT).show();
                mediaPlayer.seekTo(seekBar.getProgress());
                button1.setImageResource(R.mipmap.stop);
                flag =true;
                voiceView2.startRecording();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while(flag){
                            voiceView2.addElement((int)Math.round(Math.random() * 100 + 1));
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
                button1.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View v){
                        if(isPlay){
                            ((ImageButton)v).setImageResource(R.mipmap.play);
                            voiceView2.stopRecord();
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    while(flag){
                                        voiceView2.addElement((int)Math.round(Math.random() * 100 + 1));
                                        try {
                                            Thread.sleep(100);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            }).start();
                            flag = false;
                            mediaPlayer.pause();
                            Toast.makeText(getApplicationContext(),"暂停播放",Toast.LENGTH_SHORT).show();
                        }

                        button1.setOnClickListener(new View.OnClickListener(){
                            public void onClick(View v){
                                if(isPlay){
                                    ((ImageButton)v).setImageResource(R.mipmap.stop);
                                    flag =true;
                                    voiceView2.startRecording();
                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            while(flag){
                                                voiceView2.addElement((int)Math.round(Math.random() * 100 + 1));
                                                try {
                                                    Thread.sleep(100);
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        }
                                    }).start();
                                    //1.初始化midiaplayer
                                    mediaPlayer = new MediaPlayer();
                                    try {
                                        mediaPlayer.reset();
                                        //2.设置要播放的资源位置  path 可以是网络路径 也可以是本地路径
                                        mediaPlayer.setDataSource("wdpy.mp3");
                                        //3.准备播放
                                        mediaPlayer.prepare();
                                        //4.开始播放
                                        mediaPlayer.start();
                                        //5.更新进度条
                                        updateSeekBar();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    Toast.makeText(getApplicationContext(),"开始播放",Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    ((ImageButton)v).setImageResource(R.mipmap.play);
                                    voiceView2.stopRecord();
                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            while(flag){
                                                voiceView2.addElement((int)Math.round(Math.random() * 100 + 1));
                                                try {
                                                    Thread.sleep(100);
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        }
                                    }).start();
                                    flag = false;
                                    mediaPlayer.pause();
                                    Toast.makeText(getApplicationContext(),"暂停播放",Toast.LENGTH_SHORT).show();
                                }
                                isPlay = !isPlay;
                            }
                        });
                    }
                });

            }
        });
    }

    //监听"播放/暂停"按钮
    public void button_onClick(View v){
        if(isPlay){
            ((ImageButton)v).setImageResource(R.mipmap.stop);
            flag =true;
            voiceView2.startRecording();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while(flag){
                        voiceView2.addElement((int)Math.round(Math.random() * 100 + 1));
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
            //1.初始化midiaplayer
            mediaPlayer = new MediaPlayer();
            try {
                mediaPlayer.reset();
                //2.设置要播放的资源位置  path 可以是网络路径 也可以是本地路径
                mediaPlayer.setDataSource("wdpy.mp3");
                //3.准备播放
                mediaPlayer.prepare();
                //4.开始播放
                mediaPlayer.start();
                //5.更新进度条
                updateSeekBar();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Toast.makeText(getApplicationContext(),"开始播放",Toast.LENGTH_SHORT).show();
        }
        else{
            ((ImageButton)v).setImageResource(R.mipmap.play);
            voiceView2.stopRecord();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while(flag){
                        voiceView2.addElement((int)Math.round(Math.random() * 100 + 1));
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
            flag = false;
            mediaPlayer.pause();
            Toast.makeText(getApplicationContext(),"暂停播放",Toast.LENGTH_SHORT).show();
        }
        isPlay = !isPlay;
    }
    //进度条更新
    private void updateSeekBar() {
        //1.获取当前播放的总长度
        final int duration = mediaPlayer.getDuration();
        //2.使用Timer 定时器 去定时获取当前进度
        final Timer timer = new Timer();
        final TimerTask task = new TimerTask() {
            @Override
            public void run() {
                //3.一秒获取一次当前进度
                int currentPosition = mediaPlayer.getCurrentPosition();
                // 设置seekBar进度
                seekBar.setMax(duration);
                //当前进度
                seekBar.setProgress(currentPosition);
            }
        };
        //100毫秒后 每隔1秒 执行一次run方法
        timer.schedule(task,100,1000);
        //当歌曲执行完毕时，把timer 和 timertask 取消
        //设置播放完成的监听
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                timer.cancel();
                task.cancel();
            }
        });
    }

}
