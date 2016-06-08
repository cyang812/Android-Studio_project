package tech.cyang.button_event;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButtonListener bt = new ButtonListener();
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(bt);//只监听点击事件
        button.setOnTouchListener(bt);//可监听按下和拾起的事件
    }

    class ButtonListener implements View.OnClickListener, View.OnTouchListener {
        public void onClick(View v) {
            if(v.getId() == R.id.button){
                Log.d("test", "cansal button ---> click");
                button.setText("the button is clicked");
            }
        }

        public boolean onTouch(View v, MotionEvent event) {
            if(v.getId() == R.id.button){
                if(event.getAction() == MotionEvent.ACTION_UP){
                    Log.d("test", "cansal button ---> cancel");
                    button.setText("the is type one");
                }
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    Log.d("test", "cansal button ---> down");
                    button.setText("the is type two");
                }
            }
            return false;
        }

    }
}

