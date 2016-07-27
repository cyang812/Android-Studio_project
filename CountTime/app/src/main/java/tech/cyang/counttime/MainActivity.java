package tech.cyang.counttime;

import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText inputTime;
    private Button getTime,startTime,stopTime;
    private TextView showTime;
    private int time;
    private Timer timer = null;
    private TimerTask timerTask = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        inputTime = (EditText)findViewById(R.id.inputTime);
        getTime = (Button)findViewById(R.id.getTime);
        startTime = (Button)findViewById(R.id.startTime);
        stopTime = (Button)findViewById(R.id.stopTime);
        showTime = (TextView)findViewById(R.id.showTime);

        getTime.setOnClickListener(this);
        startTime.setOnClickListener(this);
        stopTime.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.getTime:
                showTime.setText(inputTime.getText().toString());
                time = Integer.parseInt(inputTime.getText().toString());
                break;

            case R.id.startTime:
                startTime();
                break;

            case R.id.stopTime:
                stopTime();
                break;

            default:
                break;
        }
    }

    //Handler
    private android.os.Handler mHandler = new android.os.Handler(){

        //接受消息并更新UI
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            showTime.setText(msg.arg1+"");//更新UI
            startTime();//更新完成后再次开始倒计时以便循环
        }
    };

    //开启倒计时
    private void startTime(){
        timer = new Timer();//实例化定时器
        //实例化定时器任务
        timerTask = new TimerTask() {
            @Override
            public void run() {
                //封装消息
                time--;
                Message message = mHandler.obtainMessage();
                message.arg1 = time;
                mHandler.sendMessage(message);
            }
        };
        //开启定时器，设置定时器任务，设置定时时长为1000ms
        timer.schedule(timerTask,1000);
    }

    private void stopTime(){
        timer.cancel();
    }
}
