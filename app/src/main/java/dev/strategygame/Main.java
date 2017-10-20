package dev.strategygame;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;


public class Main extends AppCompatActivity implements View.OnTouchListener {
    float x,y,prevx,prevy,transx,transy;
    ImageView mapLol;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.map);
        mapLol = (ImageView)findViewById(R.id.fuckinlol);
        try {
            mapLol.setOnTouchListener(this);
        }catch (Exception e){
            Toast toast = Toast.makeText(getApplicationContext(),
                    "ЧТО ЗА ХУЙНЯ ТО БлЯТЬ А", Toast.LENGTH_LONG);
            toast.show();
        }




    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        transx= mapLol.getTranslationX();
        transy=mapLol.getTranslationY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: // нажатие
                prevx = event.getX();
                prevy = event.getY();
            case MotionEvent.ACTION_MOVE: // движение
                x=event.getX();
                y=event.getY();
                if(prevx>x){
                    transx=transx+((x-prevx)*2);
                    prevx=x;
                }else{
                    transx=transx-((prevx-x)*2);
                    prevx=x;
                }
                if(prevx>y){
                    transy=transy+((y-prevy)*2);
                    prevy=y;
                }else{
                    transy=transy-((prevy-y)*2);
                    prevy=y;
                }
                mapLol.setTranslationY(transy);
                mapLol.setTranslationX(transx);
                Log.e("Debuginf move",transx+"  "+transy);
                break;
            case MotionEvent.ACTION_UP: // отпускание
            case MotionEvent.ACTION_CANCEL:
                //Log.e("Debuginf down",transx+"  "+transy);
                break;
        }

        return true;
    }


}
