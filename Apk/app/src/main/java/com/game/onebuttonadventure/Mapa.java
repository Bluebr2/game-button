package com.game.onebuttonadventure;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;




import java.util.Calendar;


import static com.game.onebuttonadventure.TelaPrincipal.enviaLang;


public class Mapa extends AppCompatActivity {
    private AlertDialog map;
    private int recebeLann = enviaLang;
    private float xCoOrdinate, yCoOrdinate;

    private MediaPlayer musicaMapa;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        musicaTela();
        onTouchImage();
        carregarGifs();
    }

    @Override
    protected void onStart() {
        super.onStart();
        hideSystemUI();
        carregarGifs();
    }

    @Override
    protected void onResume() {
        super.onResume();
        hideSystemUI();
        carregarGifs();
    }

    // FULLSCREEN
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }
    private void hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
    // FULLSCREEN

    // CARREGA DADOS
    private void carregarGifs(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("STAFF");

        if (recebeLann == 0){
            builder.setMessage("Dear Player, we are working to improve the features of the map!");
        }else if (recebeLann == 1){
            builder.setMessage("Caro Jogador, estamos trabalhando para melhorar as funcionalidades do mapa!");
        }

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        map = builder.create();
        map.show();

        ImageView sol = (ImageView)findViewById(R.id.imageSol);
        Drawable drawableSD = getResources().getDrawable(R.drawable.dia);
        Drawable drawableST = getResources().getDrawable(R.drawable.tarde);
        Drawable drawableSN = getResources().getDrawable(R.drawable.noite);
        // IDENTIFICAR SE É DIA OU NOITE
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);

        if (hour > 0.0 & hour <= 4.49){
            sol.setImageDrawable(drawableSN);

        }else if (hour > 5 & hour <= 11.59){
            sol.setImageDrawable(drawableSD);

        }else if (hour > 12 & hour <= 17.59) {
            sol.setImageDrawable(drawableST);

        }else if(hour > 18 & hour <= 23.59){
            sol.setImageDrawable(drawableSN);

        }

    }
    private void musicaTela(){
            // TOCAR E LOOP DE MUSICA
            musicaMapa= MediaPlayer.create(this,R.raw.mapa_song);
            musicaMapa.seekTo(0);
            musicaMapa.setVolume(0.5f,0.5f);
            musicaMapa.start();
            if (musicaMapa != null){
                musicaMapa.setLooping(true);
            }
            // TOCAR E LOOP DE MUSICA


    }
    @SuppressLint("ClickableViewAccessibility")
    private void onTouchImage(){
        ImageView imageView = (ImageView)findViewById(R.id.imageMpa);
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        xCoOrdinate = view.getX() - event.getRawX();
                        yCoOrdinate = view.getY() - event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        view.animate().x(event.getRawX() + xCoOrdinate).y(event.getRawY() + yCoOrdinate).setDuration(0).start();
                        break;
                    default:
                        return false;
                }
                return true;
            }
        });
    }
    // CARREGA DADOS

    // EVENTOS DE CLICK
    public void onClickVoltarMapa(View view){

        // CHAMANDO
        Intent intent = new Intent(Mapa.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.in_left, R.anim.fade_out);
        finish();
    }
    // EVENTOS DE CLICK

    @Override
    protected void onPause() {
        super.onPause();
        musicaMapa.stop();
    }

    @Override
    protected void onStop() {
        super.onStop();
        musicaMapa.stop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        musicaMapa.stop();
        finishAffinity();
    }
}
