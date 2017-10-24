package dev.strategygame;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;


public class Main extends AppCompatActivity implements View.OnTouchListener {
    float x, y, prevx, prevy, transx, transy;
    ImageView mapLol;
    int[] elements={R.id.loc1};
    float[] xShift=new float[128];
    float[] yShift=new float[128];
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.map);
        mapLol = (ImageView) findViewById(R.id.fuckinlol);
        try {
            mapLol.setOnTouchListener(this);
            for(int i=0;i<elements.length;i++){
                ImageView picture = (ImageView)findViewById(elements[i]);
                xShift[i]=picture.getTranslationX();
                yShift[i]=picture.getTranslationY();
            }
        } catch (Exception e) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "ЧТО ЗА ХУЙНЯ ТО БлЯТЬ А", Toast.LENGTH_LONG);
            toast.show();
        }


    }
    @SuppressWarnings("ResourceType")
    @Override
    public boolean onTouch(View v, MotionEvent event) {

        transx = mapLol.getTranslationX();
        transy = mapLol.getTranslationY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: // нажатие
                prevx = event.getX();
                prevy = event.getY();
            case MotionEvent.ACTION_MOVE: // движение
                x = event.getX();
                y = event.getY();
                if (prevx > x) {
                    transx = transx + ((x - prevx) * 2);
                    prevx = x;
                } else {
                    transx = transx - ((prevx - x) * 2);
                    prevx = x;
                }
                if (prevx > y) {
                    transy = transy + ((y - prevy) * 2);
                    prevy = y;
                } else {
                    transy = transy - ((prevy - y) * 2);
                    prevy = y;
                }
                if (transy > 718) transy = 718;
                if (transy < -718) transy = -718;
                if (transx > 918) transx = 918;
                if (transx < -918) transx = -918;
                mapLol.setTranslationY(transy);
                mapLol.setTranslationX(transx);
                Log.e("Debuginf move", transx + "  " + transy);
                break;

        }
        for(int i=0;i<elements.length;i++){
            ImageView elem = (ImageView)findViewById(elements[i]);
            elem.setTranslationX(xShift[i]+transx);
            elem.setTranslationY(yShift[i]+transy);

        }

        return true;
    }



}
