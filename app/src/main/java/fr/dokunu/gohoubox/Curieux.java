package fr.dokunu.gohoubox;

import android.app.ActionBar;
import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

public class Curieux extends Activity {

    ImageView image;
    MediaPlayer mp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(fr.dokunu.gohoubox.R.layout.curieux);
        image = (ImageView) findViewById(fr.dokunu.gohoubox.R.id.image);
        ActionBar actionbar = getActionBar();
        actionbar.setTitle("DoKuBox | A propos");
        actionbar.setIcon(fr.dokunu.gohoubox.R.drawable.aogin);

        mp = MediaPlayer.create(getApplicationContext() , fr.dokunu.gohoubox.R.raw.le_bruit_de_lo);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        switch(keyCode){
            case KeyEvent.KEYCODE_BACK :
                finish();
        }
        return false;
    }

}
