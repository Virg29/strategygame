package dev.strategygame;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by DN on 31.10.2017.
 */

class Level extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) throws NullPointerException {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.level);
        Intent intent = getIntent();

        String levelId = intent.getStringExtra("levelId");
        if(levelId==null){                                                //лучше уж так иначе потом все нахуй забуду такшо будем коментировать
            throw new NullPointerException("Launch without parametrs"); //тут хочу получить id уровня и делать запрос к бд чтоб не ебаца с многими интентами такчто да
        }
        String[] data = new DBHelper(this).getAllParams(levelId); //тут приходит все 5 параметров уровня

    }
}
