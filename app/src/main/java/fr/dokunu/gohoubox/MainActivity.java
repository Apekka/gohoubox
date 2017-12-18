package fr.dokunu.gohoubox;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;


public class MainActivity extends Activity {

    GridView gridView;
    String color;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        super.onCreate(savedInstanceState);
        setContentView(fr.dokunu.gohoubox.R.layout.activity_main);
        ActionBar actionbar = getActionBar();
        actionbar.setTitle("DoKuBox");
        actionbar.setIcon(fr.dokunu.gohoubox.R.drawable.aogin);

        final String[] couleurs = new String[]{
                "#f44336", "#f44336", "#3EC3B1", "#3EC3B1", "#3EC3B1",
                "#3EC3B1", "#3EC3B1", "#2A8036", "#2A8036", "#2A8036",
                "#2A8036", "#00493b", "#00493b", "#00493b", "#00493b",
                "#00493b", "#00493b", "#00493b", "#00493b", "#ff8800",
                "#ff8800", "#ff8800", "#ff8800", "#ff8800", "#ff8800",
                "#ff8800", "#33210d", "#33210d", "#33210d", "#33210d",
                "#33210d", "#33210d", "#33210d", "#ff9800", "#ff5722",
                "#795548", "#9e9e9e", "#607d8b", "#f44336", "#e91e63",
                "#9c27b0", "#673ab7", "#3f51b5", "#2196f3",
                "#03a9f4", "#00bcd4", "#009688", "#4caf50", "#8bc34a",
                "#cddc39", "#ffeb3b", "#ffc107", "#ff9800", "#ff5722",};

        final String[] sons = new String[]{
                "quoiii", "le_bruit_de_lo", "jcomprends_po_vraiment", "je_vais_te_marabouter",
                "parler_chinois", "tu_pourras_pas_assumer", "v_twiggew", "mais_je_joue_pas_nash",
                "tu_devrais_jouer_gief", "nan_jsuis_pas_algerien", "oui_bonjour",
                "ya_les_hendeks_qui_arrivent", "devant_35_personnes", "f5_f5",
                "la_video_cest_une_preuve", "lac_de_la_zup", "lache_moi", "le_trepied_il_est_foutu",
                "on_appelle_les_hendek", "bien_sur_cette_chose_est_dure", "bonne_journee_et_bonnes_vacances",
                "fracasse_camion", "il_est_dur", "je_vais_dormir_chez_des_amis", "merde", "salut_cest_nyxo44",
                "laissez_nous_nos_tolissos", "putaaain", "sa_mere_putain", "tolisso_a_200_credits",
                "sur_des_tolissos_achat_revente"};


        /*
        Intitialisation
         */
            gridView = (GridView) findViewById(fr.dokunu.gohoubox.R.id.gridView);

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, sons) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);

                    color = couleurs[position]; // Transparent
                    view.setBackgroundColor(Color.parseColor(color));
                    ((TextView) view).setText(sons[position]);
                    ((TextView) view).setTextSize(11);
                    ((TextView) view).setTextColor(Color.parseColor("#FFFFFF"));
                    return view;
                }
            };


            gridView.setAdapter(adapter);

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {

                    if (mp != null){
                        if (mp.isPlaying()){
                            mp.stop();
                        }
                        mp.release();
                    }
                    String son = ((TextView) view).getText().toString();
                    Uri myUri = Uri.parse("android.resource://fr.dokunu.gohoubox/raw/"+son);

                    mp = MediaPlayer.create(getApplicationContext() , myUri);
                    if (mp!=null){
                        mp.start();
                    }
                }
            });
        }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        switch(keyCode){
            case KeyEvent.KEYCODE_BACK :

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

                // set dialog message
                alertDialogBuilder
                        .setMessage("Souhaitez vous quitter l'application ?")
                        .setCancelable(false)
                        .setPositiveButton("Oui",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {

                                System.exit(0);
                            }
                        })
                        .setNegativeButton("Annuler",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // if this button is clicked, just close
                                // the dialog box and do nothing
                            }
                        });
                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();
                // show it
                alertDialog.show();
        }
        return false;
    }



    //MENU CONTEXTUEL

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(fr.dokunu.gohoubox.R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == fr.dokunu.gohoubox.R.id.action_settings) {
            Intent intent = new Intent(MainActivity.this, Curieux.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
