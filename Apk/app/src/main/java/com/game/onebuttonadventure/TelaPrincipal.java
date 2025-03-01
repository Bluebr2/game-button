package com.game.onebuttonadventure;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.VideoView;

import java.util.Calendar;

public class TelaPrincipal extends AppCompatActivity {

    private MediaPlayer musica_telainicial;
    private Integer language = 0;
    public static int enviaLang = 0;
    private AlertDialog lingua;
    private SharedPreferences saveLang;
    private SharedPreferences.Editor editorLang;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);
        detectHour();
        salvarBancoDeDados();
        carregarMuica();
        carregaDados();

    }

    @Override
    protected void onStart() {
        super.onStart();
        hideSystemUI();
        salvarBancoDeDados();
        detectHour();
        carregaDados();
    }

    @Override
    protected void onResume() {
        super.onResume();
        hideSystemUI();
        detectHour();
        carregaDados();
    }

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
                        //| View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        //| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        //| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    private void detectHour(){
        Drawable drawableLn = getResources().getDrawable(R.drawable.language);
        Drawable drawablePt = getResources().getDrawable(R.drawable.idioma);
        ImageView idiOma = (ImageView)findViewById(R.id.fotoIdio);

        if (language == 0){
            idiOma.setImageDrawable(drawableLn);

        }else if (language == 1){
            idiOma.setImageDrawable(drawablePt);

        }



        // IDENTIFICAR SE É DIA OU NOITE
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        if (hour >= 0.0 && hour <= 4.59){
            // VIDEO AUTO PLAY
            VideoView videoTelaPrincipal = (VideoView)findViewById(R.id.videoTelaPrincipal);
            videoTelaPrincipal.setVideoPath("android.resource://com.game.onebuttonadventure/raw/inicionoite");
            videoTelaPrincipal.start();

            videoTelaPrincipal.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.setLooping(true);
                }
            });
            // VIDEO AUTO PLAY

        }else if (hour >= 5 && hour <= 11.59){

            // VIDEO AUTO PLAY
            VideoView videoTelaPrincipal = (VideoView)findViewById(R.id.videoTelaPrincipal);
            videoTelaPrincipal.setVideoPath("android.resource://com.game.onebuttonadventure/raw/iniciodia");
            videoTelaPrincipal.start();
            videoTelaPrincipal.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.setLooping(true);
                }
            });
            // VIDEO AUTO PLAY

        }else if (hour >= 12 && hour <= 17.59){

            // VIDEO AUTO PLAY
            VideoView videoTelaPrincipal = (VideoView)findViewById(R.id.videoTelaPrincipal);
            videoTelaPrincipal.setVideoPath("android.resource://com.game.onebuttonadventure/raw/iniciotarde");
            videoTelaPrincipal.start();
            videoTelaPrincipal.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.setLooping(true);
                }
            });
            // VIDEO AUTO PLAY

        }else if (hour >= 18 && hour <= 23.59){
            // VIDEO AUTO PLAY
            VideoView videoTelaPrincipal = (VideoView)findViewById(R.id.videoTelaPrincipal);
            videoTelaPrincipal.setVideoPath("android.resource://com.game.onebuttonadventure/raw/inicionoite");
            videoTelaPrincipal.start();
            videoTelaPrincipal.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.setLooping(true);
                }
            });
            // VIDEO AUTO PLAY

        }
        // IDENTIFICAR SE É DIA OU NOITE

    }
    private void carregarMuica(){
        // TOCAR E LOOP DE MUSICA
        musica_telainicial = MediaPlayer.create(this,R.raw.tela_inicial);
        musica_telainicial.seekTo(0);
        musica_telainicial.setVolume(0.5f,0.5f);
        musica_telainicial.start();
        if (musica_telainicial !=null){
            musica_telainicial.setLooping(true);
        }
        // TOCAR E LOOP DE MUSICA
    }
    @SuppressLint("SetTextI18n")
    private void carregaDados(){
        Drawable drawableLn = getResources().getDrawable(R.drawable.language);
        Drawable drawablePt = getResources().getDrawable(R.drawable.idioma);
        ImageView idiOma = (ImageView)findViewById(R.id.fotoIdio);
        if (language == 1){
            enviaLang = 1;
            idiOma.setImageDrawable(drawablePt);
        }else if (language == 0){
            enviaLang = 0;
            idiOma.setImageDrawable(drawableLn);
        }
    }
    private void salvarBancoDeDados(){
        try {
            saveLang = getSharedPreferences("lan", MODE_PRIVATE);
            language = saveLang.getInt("lan", 0);
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    // EVENTOS DE CLICK
    public void onClickFundo(View view){
        // CHAMANDO
        Intent intent = new Intent(TelaPrincipal.this, TelaPreta.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finish();
        //musica_telainicial.stop();
    }
    @SuppressLint("SetTextI18n")
    public void onClickLanguage(View view){
        carregaDados();
        Drawable drawableLn = getResources().getDrawable(R.drawable.language);
        Drawable drawablePt = getResources().getDrawable(R.drawable.idioma);
        ImageView idiOma = (ImageView)findViewById(R.id.fotoIdio);
        AlertDialog.Builder biulder = new AlertDialog.Builder(this);
        biulder.setTitle("Config");
        biulder.setPositiveButton("English", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                language = 0;
                carregaDados();

            }
        });

        biulder.setNegativeButton("Pt - BR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                language = 1;
                carregaDados();
            }
        });

        if (language == 1){
            enviaLang = 1;
            idiOma.setImageDrawable(drawablePt);
        }else if (language == 0){
            enviaLang = 0;
            idiOma.setImageDrawable(drawableLn);
        }

        lingua = biulder.create();
        lingua.show();
        carregaDados();

    }

    @Override
    protected void onPause() {
        super.onPause();
        try {
            editorLang = saveLang.edit();
            editorLang.putInt("lan", language);
            editorLang.apply();
        }catch (Exception e) {
            e.printStackTrace();
        }

        musica_telainicial.stop();
    }

    @Override
    protected void onStop(){
        super.onStop();
        musica_telainicial.stop();

        try {
            editorLang = saveLang.edit();
            editorLang.putInt("lan", language);
            editorLang.apply();
            musica_telainicial.stop();

        }catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            editorLang = saveLang.edit();
            editorLang.putInt("lan", language);
            editorLang.apply();

        }catch (Exception e) {
            e.printStackTrace();
        }

        musica_telainicial.stop();
        finishAffinity();
    }
}
