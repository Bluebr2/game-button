package com.game.onebuttonadventure;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;


import static com.game.onebuttonadventure.Boss.contarKillBoss;
import static com.game.onebuttonadventure.MainActivity.contarClick;
import static com.game.onebuttonadventure.MainActivity.enviaLvl;
import static com.game.onebuttonadventure.TelaPrincipal.enviaLang;


public class Coquistas extends AppCompatActivity {

    private Integer recebeConfig = enviaLang;
    private MediaPlayer musicaMission;
    AlertDialog alerta;


    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coquistas);
        musicaTela();
        carregarGifs();
        //Pega o progresso
        pegaProgresso();
        avisoJogador();
    }

    @Override
    protected void onStart() {
        super.onStart();
        hideSystemUI();
        pegaProgresso();
        carregarGifs();
    }

    @Override
    protected void onResume() {
        super.onResume();
        hideSystemUI();
        pegaProgresso();
        carregarGifs();
    }

    // FULLSCREEN
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus){
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

    // CARREGAR BANCO DE DADOS
    private void avisoJogador(){
        AlertDialog.Builder biulder = new AlertDialog.Builder(this);
        biulder.setTitle("STAFF");
        if (recebeConfig == 1){
            biulder.setMessage("Caro Jogador, as recompensas das conquistas já estão sendo criadas!");
        }else if (recebeConfig == 0){
            biulder.setMessage("Dear Player, achievement rewards are already being created!");
        }

        biulder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        //cria o AlertDialog
        alerta = biulder.create();
        //Exibe
        alerta.show();

    }
    @SuppressLint("SetTextI18n")
    public void pegaProgresso(){
        TextView text1 = (TextView)findViewById(R.id.textTopoConq);
        TextView text2 = (TextView)findViewById(R.id.textMisssionsToClick);
        TextView text3 = (TextView)findViewById(R.id.textViewA);
        TextView text4 = (TextView)findViewById(R.id.textViewB);
        TextView text5 = (TextView)findViewById(R.id.textViewC);
        TextView text6 = (TextView)findViewById(R.id.textMonsterMissions);
        TextView text7 = (TextView)findViewById(R.id.textViewD);
        TextView text8 = (TextView)findViewById(R.id.textViewE);
        TextView text9 = (TextView)findViewById(R.id.textViewF);
        TextView text10 = (TextView)findViewById(R.id.textLevelMissions);
        TextView text11 = (TextView)findViewById(R.id.textViewG);
        TextView text12 = (TextView)findViewById(R.id.textViewH);
        TextView text13 = (TextView)findViewById(R.id.textViewI);
        TextView text14 = (TextView)findViewById(R.id.textViewJ);
        TextView text15 = (TextView)findViewById(R.id.textViewL);
        TextView text16 = (TextView)findViewById(R.id.textViewM);
        TextView text17 = (TextView)findViewById(R.id.textViewN);
        TextView text18 = (TextView)findViewById(R.id.textViewO);
        TextView text19 = (TextView)findViewById(R.id.textViewP);
        TextView text20 = (TextView)findViewById(R.id.textViewQ);
        TextView text21 = (TextView)findViewById(R.id.textViewR);
        TextView text22 = (TextView)findViewById(R.id.textViewS);
        TextView text23 = (TextView)findViewById(R.id.textViewT);
        if (recebeConfig == 0){
            text1.setText("Missions");
            text2.setText("MISSIONS TO CLICK");
            text3.setText("CLICK MORE THAN 500 TIMES");
            text4.setText("CLICK MORE THAN 1000 TIMES");
            text5.setText("CLICK MORE THAN 50.000 TIMES");
            text6.setText("BOSS MISSIONS");
            text7.setText("KILL 5 BOSSES");
            text8.setText("KILL 25 BOSSES");
            text9.setText("KILL 150 BOSSES");
            text10.setText("LEVEL MISSIONS");
            text11.setText("GET TO LEVEL 10");
            text12.setText("GET TO LEVEL 15");
            text13.setText("GET TO LEVEL 25");
            text14.setText("GET TO LEVEL 50");
            text15.setText("GET TO LEVEL 75");
            text16.setText("GET TO LEVEL 100");
            text17.setText("GET TO LEVEL 125");
            text18.setText("GET TO LEVEL 150");
            text19.setText("GET TO LEVEL 175");
            text20.setText("GET TO LEVEL 225");
            text21.setText("GET TO LEVEL 350");
            text22.setText("GET TO LEVEL 500");
            text23.setText("GET TO LEVEL 999");
        }else{
            text1.setText("Missões");
            text2.setText("MISSÕES DE CLICK");
            text3.setText("CLICK 500 VEZES");
            text4.setText("CLICK 1000 VEZES");
            text5.setText("CLICK 50.000 VEZES");
            text6.setText("MISSÕES DE CHEFES");
            text7.setText("MATE 250 CHEFES");
            text8.setText("MATE 500 CHEFES");
            text9.setText("MATE 1000 CHEFES");
            text10.setText("LEVEL MISSIONS");
            text11.setText("CHEGUE AO LEVEL 10");
            text12.setText("CHEGUE AO LEVEL 15");
            text13.setText("CHEGUE AO LEVEL 25");
            text14.setText("CHEGUE AO LEVEL 50");
            text15.setText("CHEGUE AO LEVEL 75");
            text16.setText("CHEGUE AO LEVEL 100");
            text17.setText("CHEGUE AO LEVEL 125");
            text18.setText("CHEGUE AO LEVEL 150");
            text19.setText("CHEGUE AO LEVEL 175");
            text20.setText("CHEGUE AO LEVEL 225");
            text21.setText("CHEGUE AO LEVEL 350");
            text22.setText("CHEGUE AO LEVEL 500");
            text23.setText("CHEGUE AO LEVEL 999");

        }
        // PEGA O PROGRESSO DOS CLICKS
        ProgressBar barClick500 = (ProgressBar)findViewById(R.id.bar500Click);
        ProgressBar barClick1000 = (ProgressBar)findViewById(R.id.bar1000Clicks);
        ProgressBar barClick50000 = (ProgressBar)findViewById(R.id.bar50000Clicks);
        barClick500.setProgress(contarClick);
        barClick1000.setProgress(contarClick);
        barClick50000.setProgress(contarClick);
        // PEGA O PROGRESSO DOS CLICKS

        // PEGA O PROGRESSO DOS MOSNTROS
        ProgressBar bar250Monstros = (ProgressBar)findViewById(R.id.bar250Enimes);
        ProgressBar bar500Monstros = (ProgressBar)findViewById(R.id.bar500Enimes);
        ProgressBar bar1000Monstros = (ProgressBar)findViewById(R.id.bar1000Enimes);
        bar250Monstros.setProgress(contarKillBoss);
        bar500Monstros.setProgress(contarKillBoss);
        bar1000Monstros.setProgress(contarKillBoss);
        // PEGA O PROGRESSO DOS MOSNTROS

        // PEGA O PROGRESSO DO LVL
        ProgressBar barlvl10 = (ProgressBar)findViewById(R.id.barLvl10);
        ProgressBar barlvl15 = (ProgressBar)findViewById(R.id.barLvl15);
        ProgressBar barlvl25 = (ProgressBar)findViewById(R.id.barLvl25);
        ProgressBar barlvl50 = (ProgressBar)findViewById(R.id.barLvl50);
        ProgressBar barlvl75 = (ProgressBar)findViewById(R.id.barLvl75);
        ProgressBar barlvl100 = (ProgressBar)findViewById(R.id.barLvl100);
        ProgressBar barlvl125 = (ProgressBar)findViewById(R.id.barLvl125);
        ProgressBar barlvl150 = (ProgressBar)findViewById(R.id.barLvl150);
        ProgressBar barlvl175 = (ProgressBar)findViewById(R.id.barLvl175);
        ProgressBar barlvl225 = (ProgressBar)findViewById(R.id.barLvl225);
        ProgressBar barlvl350 = (ProgressBar)findViewById(R.id.barLvl350);
        ProgressBar barlvl500 = (ProgressBar)findViewById(R.id.barLvl500);
        ProgressBar barlvl999 = (ProgressBar)findViewById(R.id.barLvl999);
        barlvl10.setProgress(enviaLvl);
        barlvl15.setProgress(enviaLvl);
        barlvl25.setProgress(enviaLvl);
        barlvl50.setProgress(enviaLvl);
        barlvl75.setProgress(enviaLvl);
        barlvl100.setProgress(enviaLvl);
        barlvl125.setProgress(enviaLvl);
        barlvl150.setProgress(enviaLvl);
        barlvl175.setProgress(enviaLvl);
        barlvl225.setProgress(enviaLvl);
        barlvl350.setProgress(enviaLvl);
        barlvl500.setProgress(enviaLvl);
        barlvl999.setProgress(enviaLvl);
        // PEGA O PROGRESSO DO LVL
    }
    private void carregarGifs(){
        ImageView gifTeste = (ImageView)findViewById(R.id.gifConq);
        Glide.with(this)
                .load(R.drawable.conq).asGif().into(gifTeste);
    }
    private void musicaTela(){
            // TOCAR E LOOP DE MUSICA
            musicaMission = MediaPlayer.create(this,R.raw.mission_song);
            //musicaTelaPrincipal.setLooping(true);
            musicaMission.seekTo(0);
            musicaMission.setVolume(0.5f,0.5f);
            musicaMission.start();
            if (musicaMission != null){
                musicaMission.setLooping(true);
            }
            // TOCAR E LOOP DE MUSICA

    }
    // CARREGAR BANCO DE DADOS

    // EVENTOS DE CLICK
    public  void onClickVoltarConquista(View view){

        // CHAMANDO
        Intent intent = new Intent(Coquistas.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.in_rigth, R.anim.fade_out);
        musicaMission.stop();
        finish();

    }
    // EVENTOS DE CLICK


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop(){
        super.onStop();
        musicaMission.stop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        musicaMission.stop();
        finishAffinity();
    }
}
