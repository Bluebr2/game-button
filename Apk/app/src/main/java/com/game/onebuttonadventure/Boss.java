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
import android.widget.VideoView;

import com.bumptech.glide.Glide;

import static com.game.onebuttonadventure.MainActivity.personagem;
import static com.game.onebuttonadventure.TelaPrincipal.enviaLang;

public class Boss extends AppCompatActivity {
    private int vida = 5000000;
    private int bossAtivo = 0;
    public static Integer contarKillBoss = 0;
    private Integer recebeLang = enviaLang;
    private Integer timer = 300;
    private AlertDialog alerta;
    private SharedPreferences saveBoss;
    private SharedPreferences.Editor editor;
    private MediaPlayer musicaTelaBoss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boss);
        musicaTela();
        informar();
        carregarGifs();
        recebeLang1();
        salvarBanco();
    }

    @Override
    protected void onStart() {
        super.onStart();
        hideSystemUI();
        carregarGifs();
        recebeLang1();
        salvarBanco();
    }

    @Override
    protected void onResume() {
        super.onResume();
        hideSystemUI();
        carregarGifs();
        recebeLang1();
        salvarBanco();
    }

    // FULLSCREEN
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

    private void contarTempo(){
        final TextView myTextView = (TextView) findViewById(R.id.txtReceberTimerBoss); //grab your tv
        Runnable myRunnable = new Runnable() {
            @SuppressLint("SetTextI18n")
            @Override
            public void run() {
                while (bossAtivo == 1){
                    try {
                        Thread.sleep(1000);
                    }catch (Exception e) {
                        e.printStackTrace();
                    }

                    timer = timer - 1;
                    myTextView.setText(timer.toString());
                    if (timer == 0){
                        bossAtivo = 0;
                        timer = 300;
                    }

                }

            }
        };

        Thread myThread = new Thread(myRunnable);
        myThread.start();
    }
    private void salvarBanco(){
        saveBoss = getSharedPreferences("bossTela", MODE_PRIVATE);
        contarKillBoss = saveBoss.getInt("bossTela",0);
    }
    private void musicaTela(){
        // VIDEO AUTO PLAY
        VideoView videoTelaBoss = (VideoView)findViewById(R.id.videoTelaBoss);
        videoTelaBoss.setVideoPath("android.resource://com.game.onebuttonadventure/raw/fundoboss");
        videoTelaBoss.start();

        videoTelaBoss.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });
        // VIDEO AUTO PLAY

            // TOCAR E LOOP DE MUSICA
            musicaTelaBoss = MediaPlayer.create(this,R.raw.boss_song);
            musicaTelaBoss.seekTo(0);
            musicaTelaBoss.setVolume(0.5f,0.5f);
            musicaTelaBoss.start();
            if (musicaTelaBoss != null){
                musicaTelaBoss.setLooping(true);
            }
            // TOCAR E LOOP DE MUSICA

    }
    @SuppressLint("SetTextI18n")
    private void recebeLang1(){
        TextView text = (TextView)findViewById(R.id.txtVidaBoss);
        if(recebeLang == 0){
            text.setText("Life");

        }else {
            text.setText("Vida");
        }
    }
    private void carregarGifs(){
        ImageView gifBoss = (ImageView)findViewById(R.id.gifBoss);
        Glide.with(this)
                .load(R.drawable.cube1).asGif().into(gifBoss);
    }
    private void informar(){
        AlertDialog.Builder biulder = new AlertDialog.Builder(this);
        if (recebeLang == 0){
            biulder.setTitle("Staff - Information about BOSS!");
            biulder.setMessage("Name: Paper Boss\n" + "Difficulty: Extreme\n" + "Life: 5.000.000\n" + "Time: 5 minutes");
            biulder.setPositiveButton("GO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    contarTempo();
                    //carregarGifs();
                    bossAtivo = 1;
                }
            });
            biulder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // CHAMANDO
                    Intent intent = new Intent(Boss.this, MainActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.in_rigth, R.anim.fade_out);
                    finish();
                }
            });
            contarTempo();
        }else {
            biulder.setTitle("Staff - Informções sobre o BOSS!");
            biulder.setMessage("Nome: Paper Boss\nDificuldade: Extrema\nVida: 5.000.000\nTempo: 5 minutos");
            biulder.setPositiveButton("Começar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    contarTempo();
                    //carregarGifs();
                    bossAtivo = 1;
                }
            });
            biulder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // CHAMANDO
                    Intent intent = new Intent(Boss.this, MainActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.in_rigth, R.anim.fade_out);
                    finish();
                }
            });
            contarTempo();
        }

        alerta = biulder.create();
        alerta.show();
    }

    public void voltarBoss(View view){
        // CHAMANDO
        Intent intent = new Intent(Boss.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.in_rigth, R.anim.fade_out);
        musicaTelaBoss.stop();
        finish();
    }
    public void onClickBoss(View view){
        AlertDialog.Builder biulder = new AlertDialog.Builder(this);
        ProgressBar progressBoss = (ProgressBar)findViewById(R.id.progressVidaBoss);
        progressBoss.setProgress(vida);
            if (personagem == 1){
                vida = vida - 1500;
            }else if (personagem == 2){
                vida = vida - 3000;

            }else if (personagem == 3){
                vida = vida - 12000;

            }else if (personagem == 4){
                vida = vida - 5000;

            }else if (personagem == 5){
                vida = vida - 7500;

            }else if (personagem == 6){
                vida = vida - 15000;

            }

        if (vida <= 0){
            biulder.setTitle("Staff");
            if (recebeLang == 0){
                biulder.setMessage("Congratulations, good job!\nSoon but we will bring a giant reward!");

            }else {
                biulder.setMessage("Parabéns, bom trabalho!\nLogo mas traremos uma gigante recompensa!");
            }
            biulder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // CHAMANDO
                    Intent intent = new Intent(Boss.this, MainActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.in_rigth, R.anim.fade_out);
                    finish();

                }
            });
            // CHAMANDO
            Intent intent = new Intent(Boss.this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.in_rigth, R.anim.fade_out);
            finish();

            contarKillBoss++;
        }


    }

    @SuppressLint("ApplySharedPref")
    @Override
    protected void onPause() {
        super.onPause();

        editor = saveBoss.edit();
        editor.putInt("bossTela", contarKillBoss);
        editor.commit();

        musicaTelaBoss.stop();
    }

    @SuppressLint("ApplySharedPref")
    @Override
    protected void onStop() {
        super.onStop();
        editor = saveBoss.edit();
        editor.putInt("bossTela", contarKillBoss);
        editor.commit();

        musicaTelaBoss.stop();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @SuppressLint("ApplySharedPref")
    @Override
    protected void onDestroy() {
        super.onDestroy();
        editor = saveBoss.edit();
        editor.putInt("bossTela", contarKillBoss);
        editor.commit();

        musicaTelaBoss.stop();
        finishAffinity();
    }
}
