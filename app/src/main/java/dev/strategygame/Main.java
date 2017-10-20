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
    float x;
    float y;
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
        x = event.getX();
        y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: // нажатие
                Log.e("Debuginf down",x+"  "+y);
                break;
            case MotionEvent.ACTION_MOVE: // движение
                mapLol.setTranslationY(y);
                mapLol.setTranslationX(x);
                Log.e("Debuginf move",x+"  "+y);
                break;
            case MotionEvent.ACTION_UP: // отпускание
            case MotionEvent.ACTION_CANCEL:
                Log.e("Debuginf down",x+"  "+y);
                break;
        }

        return true;
    }


}
