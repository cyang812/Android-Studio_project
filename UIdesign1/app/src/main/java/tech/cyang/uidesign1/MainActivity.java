package tech.cyang.uidesign1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public TextView tv1;
    public RelativeLayout myView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("TAG","start");
        tv1 = (TextView)findViewById(R.id.textView);
        myView = (RelativeLayout)findViewById(R.id.myview);
        uiDesign();
    }

    private void uiDesign(){

            myView.setOnTouchListener(new View.OnTouchListener() {

            private float startX,startY,offsetX,offsetY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startX = event.getX();
                        startY = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        offsetX = event.getX()-startX;
                        offsetY = event.getY()-startY;


                        if (Math.abs(offsetX)>Math.abs(offsetY)) {
                            if (offsetX<-5) {
                                swipeLeft();
                            }else if (offsetX>5) {
                                swipeRight();
                            }
                        }else{
                            if (offsetY<-5) {
                                swipeUp();
                            }else if (offsetY>5) {
                                swipeDown();
                            }
                        }

                        break;
                }
                return true;
            }
        });
    }

    private void swipeLeft(){
        tv1.setText("left");
        Log.d("Uidesign","left");
    }

    private void swipeRight(){
        tv1.setText("right");
        Log.d("Uidesign","right");
    }

    private void swipeUp(){
        tv1.setText("up");
        Log.d("Uidesign","up");
    }

    private void swipeDown(){
        tv1.setText("down");
        Log.d("Uidesign","down");
    }

}
