package com.game.onebuttonadventure;


import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;

import java.util.Calendar;
import java.util.Random;



import static com.game.onebuttonadventure.TelaPrincipal.enviaLang;



public class MainActivity extends AppCompatActivity {

    // BANCO DE DADOS
    private Integer bar = 0;
    private Integer barEvent = 500;
    public static Integer equipButton = 1;
    public static Integer eventoPerso1 = 0;
    public static Integer eventoPerso2 = 0;
    public static Integer contarKillEvent1 = 0;
    private Integer buff1Perm = 0;
    private Integer buff2Perm = 0;
    private Integer buff3Perm = 0;
    private Integer lvl = 0;
    public static Integer enviaLvl = 0;
    public static Integer personagem = 1;
    public static   Integer contarClick = 0;
    private Integer coin = 0;
    private int recebeConfig = enviaLang;
    private MediaPlayer musicaTelaPrincipal;
    // BANCO DE DADOS
    private SharedPreferences mainBan;
    private SharedPreferences savelvl;
    private SharedPreferences saveOffLvl;
    private SharedPreferences saveEvent;
    private SharedPreferences.Editor saveEditorEvent;
    private SharedPreferences.Editor saveOffLvlEditor;
    private SharedPreferences.Editor editorlvl;
    private SharedPreferences.Editor editorMainBan;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        carregarGifs();
        musicaTela();
        robot();
        detectHour();
        lerDado();
        // Salva o Banco de Dados
        salvarBancoDeDados();
        dicas();

        // DIA OU NOITE
        //detectDayOurNigth();
        //carregarBuffs();
        // CARREGA BUFFS ATIVOS
    }

    @Override
    protected void onStart() {
        super.onStart();
        hideSystemUI();
        robot();
        salvarBancoDeDados();
        carregarGifs();
        detectHour();
        lerDado();
        dicas();
    }

    @Override
    protected void onResume() {
        super.onResume();
        salvarBancoDeDados();
        carregarGifs();
        detectHour();
        robot();
        lerDado();
        dicas();
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

    // CARREGAMENTO DE DADOS
    private void carregarGifs(){
        ImageView gifBtn = (ImageView)findViewById(R.id.btnPrincipal);
        Drawable btn1 = getResources().getDrawable(R.drawable.button_inicial2off);
        Drawable btn2 = getResources().getDrawable(R.drawable.btn2off);
        Drawable btn3 = getResources().getDrawable(R.drawable.btn3off);
        Drawable btn4 = getResources().getDrawable(R.drawable.btn4off);

        if (equipButton == 1){
            gifBtn.setImageDrawable(btn1);
            Glide.with(this)
                    .load(R.drawable.button_inicial).asGif().into(gifBtn);

        }else if (equipButton == 2){
            gifBtn.setImageDrawable(btn2);
            Glide.with(this)
                    .load(R.drawable.btn2).asGif().into(gifBtn);
        }else if (equipButton == 3){
            gifBtn.setImageDrawable(btn2);
            Glide.with(this)
                    .load(R.drawable.btn3).asGif().into(gifBtn);
        }else if (equipButton == 4){
            gifBtn.setImageDrawable(btn3);
            Glide.with(this)
                    .load(R.drawable.btn4).asGif().into(gifBtn);
        }

        ProgressBar barEvent1 = (ProgressBar)findViewById(R.id.barProgressEvent);
        ImageView imageMonsterEvent = (ImageView)findViewById(R.id.btnMonsterEvent);

        if(barEvent1.getVisibility() == View.VISIBLE && imageMonsterEvent.getVisibility() == View.VISIBLE){
            Glide.with(this)
                    .load(R.drawable.moeda).asGif().into(imageMonsterEvent);
        }



    }

    private void dicas(){
        final ImageView telaPrinc = (ImageView)findViewById(R.id.imageDica);
        telaPrinc.setVisibility(View.VISIBLE);
        Drawable drawableus1 = getResources().getDrawable(R.drawable.dica_eventonoite_us);
        Drawable drawableus2 = getResources().getDrawable(R.drawable.dica_mapaescolha_us);
        Drawable drawableus3 = getResources().getDrawable(R.drawable.dica_noitecoin_us);
        Drawable drawableus4 = getResources().getDrawable(R.drawable.dia_personagemforca_us);
        Drawable drawablept1 = getResources().getDrawable(R.drawable.dica_eventonoite_pt);
        Drawable drawablept2  = getResources().getDrawable(R.drawable.dica_mapaescolha_pt);
        Drawable drawablept3  = getResources().getDrawable(R.drawable.dica_noitecoin_pt);
        Drawable drawablept4  = getResources().getDrawable(R.drawable.dia_personagemforca_pt);
        Random imageRandom = new Random();
        int image = imageRandom.nextInt(400);

        if (recebeConfig == 0){
            if (image >= 0 && image < 100){
                telaPrinc.setImageDrawable(drawableus1);

            }else if(image >= 100 && image < 200){
                telaPrinc.setImageDrawable(drawableus2);

            }else if(image >= 200 && image < 300){
                telaPrinc.setImageDrawable(drawableus3);

            }else if(image >= 300 && image < 400){
                telaPrinc.setImageDrawable(drawableus4);

            }

        }else{
            if (image >= 0 && image < 100){
                telaPrinc.setImageDrawable(drawablept1);

            }else if(image >= 100 && image < 200){
                telaPrinc.setImageDrawable(drawablept2);

            }else if(image >= 200 && image < 300){
                telaPrinc.setImageDrawable(drawablept3);

            }else if(image >= 300 && image < 400){
                telaPrinc.setImageDrawable(drawablept4);

            }

        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                telaPrinc.setVisibility(View.INVISIBLE);
            }
        },15000);

    }
    private void robot(){

        Runnable myRunnable = new Runnable() {
            @SuppressLint("SetTextI18n")
            @Override
            public void run() {
                while (buff3Perm == 1) {
                    try {
                        Thread.sleep(10000); // Waits for 1 second (1000 milliseconds)
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    carregarGifs();
                    contarClick++;
                    TextView moeda = (TextView)findViewById(R.id.textCoins);
                    moeda.setText("Coin: "+ coin.toString());
                    ProgressBar barEvent1 = (ProgressBar)findViewById(R.id.barProgressEvent);
                    ImageView imageMonsterEvent = (ImageView)findViewById(R.id.btnMonsterEvent);
                    final ImageButton btnPrincipal = (ImageButton)findViewById(R.id.btnPrincipal);
                    Calendar c = Calendar.getInstance();
                    int hour = c.get(Calendar.HOUR_OF_DAY);

                    // COIN
                    try {
                        if (hour >= 5 && hour <= 18){
                            Random coinRandom = new Random();
                            int coinVaria = coinRandom.nextInt(500);
                            if (coinVaria >= 0 && coinVaria <= 5){
                                if(buff1Perm > 0){
                                    coin = coin + 150*2;
                                }else {
                                    coin = coin + 150;
                                }
                            }else if(coinVaria > 5 && coinVaria <= 200){
                                if(buff1Perm > 0){
                                    coin = coin + 50*2;
                                }else {
                                    coin = coin + 50;
                                }
                            }else if (coinVaria > 200 && coinVaria <= 500){
                                if(buff1Perm > 0){
                                    coin = coin + 20*2;
                                }else {
                                    coin = coin + 20;
                                }
                            }
                        }else if (hour > 18 && hour <= 23.59){
                            Random coinRandom = new Random();
                            int coinVaria = coinRandom.nextInt(500);
                            if (coinVaria >= 0 && coinVaria <= 5){
                                if(buff1Perm > 0){
                                    coin = coin + 500*2;
                                }else {
                                    coin = coin + 500;
                                }
                            }else if(coinVaria > 5 && coinVaria <= 200){
                                if(buff1Perm > 0){
                                    coin = coin + 250*2;
                                }else {
                                    coin = coin + 250;
                                }
                            }else if (coinVaria > 200 && coinVaria <= 500){
                                if(buff1Perm > 0){
                                    coin = coin + 120*2;
                                }else {
                                    coin = coin + 120;
                                }
                            }

                        }else if (hour >= 0.0 && hour <= 4.59){
                            Random coinRandom = new Random();
                            int coinVaria = coinRandom.nextInt(500);
                            if (coinVaria >= 0 && coinVaria <= 5){
                                if(buff1Perm > 0){
                                    coin = coin + 750*2;
                                }else {
                                    coin = coin + 750;
                                }
                            }else if(coinVaria > 5 && coinVaria <= 200){
                                if(buff1Perm > 0){
                                    coin = coin + 500*2;
                                }else {
                                    coin = coin + 500;
                                }
                            }else if (coinVaria > 200 && coinVaria <= 500){
                                if(buff1Perm > 0){
                                    coin = coin + 250*2;
                                }else {
                                    coin = coin + 250;
                                }
                            }
                        }
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                    // COIN

                    //EVENTO
                    try {
                        if (barEvent1.getVisibility() == View.INVISIBLE && imageMonsterEvent.getVisibility() == View.INVISIBLE){
                            Random randAtivaEvent = new Random();

                            int ativadoEvent1 = randAtivaEvent.nextInt(500);

                            if (hour >= 5 && hour <= 18){
                                if (ativadoEvent1 >= 0 && ativadoEvent1 <= 2){
                                    barEvent1.setVisibility(View.VISIBLE);
                                    imageMonsterEvent.setVisibility(View.VISIBLE);
                                }

                            }else if (hour > 18 && hour <= 23.59){
                                if (ativadoEvent1 >= 0 && ativadoEvent1 <= 10){
                                    barEvent1.setVisibility(View.VISIBLE);
                                    imageMonsterEvent.setVisibility(View.VISIBLE);
                                }

                            }else if (hour >= 0.0 && hour <= 4.59){
                                if (ativadoEvent1 >= 0 && ativadoEvent1 <= 25){
                                    barEvent1.setVisibility(View.VISIBLE);
                                    imageMonsterEvent.setVisibility(View.VISIBLE);
                                }
                            }


                        }
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                    //EVENTO


                    // ACESSANDO OS COMPONETES DA TELA
                    ProgressBar barLvl = (ProgressBar)findViewById(R.id.barLvl);
                    TextView textLvl = (TextView)findViewById(R.id.textLvl);

                    // ACESSANDO OS COMPONETES DA TELA

                    // QUANDO A BARRA ENCHER, ZERA E ACRESCENTA 1 LVL
                    barLvl.setProgress(bar);
                    if (bar >= 200000){
                        lvl++;
                        bar = 0; }
                    // // QUANDO A BARRA ENCHER, ZERA E ACRESCENTA 1 LVL

                    // XP POR LVL
                    if (lvl >= 0 && lvl <= 10){
                        if (buff2Perm > 0){
                            bar = bar + 5000*2;
                        }else{
                            bar = bar + 5000;
                        }

                    }else if (lvl > 10 && lvl <= 25){
                        if (buff2Perm > 0){
                            bar = bar + 4900*2;
                        }else{
                            bar = bar + 4900;
                        }
                    }else if (lvl > 25 && lvl <= 45){
                        if (buff2Perm > 0){
                            bar = bar + 3500*2;
                        }else{
                            bar = bar + 3500;
                        }
                    }else if (lvl > 45 && lvl <= 65){
                        if (buff2Perm > 0){
                            bar = bar + 3500*2;
                        }else{
                            bar = bar + 3500;
                        }
                    }else if (lvl > 65 && lvl <= 90){
                        if (buff2Perm > 0){
                            bar = bar + 3200*2;
                        }else{
                            bar = bar + 3200;
                        }
                    }else if (lvl > 90 && lvl <= 125){
                        if (buff2Perm > 0){
                            bar = bar + 2500*2;
                        }else{
                            bar = bar + 2500;
                        }
                    }else if (lvl > 125 && lvl <= 175){
                        if (buff2Perm > 0){
                            bar = bar + 2200*2;
                        }else{
                            bar = bar + 2200;
                        }
                    }else if (lvl > 175 && lvl <= 275){
                        if (buff2Perm > 0){
                            bar = bar + 1950*2;
                        }else{
                            bar = bar + 1950;
                        }
                    }
                    else if (lvl > 275 && lvl <= 300){
                        if (buff2Perm > 0){
                            bar = bar + 1750*2;
                        }else{
                            bar = bar + 1750;
                        }
                    }
                    else if (lvl > 300 && lvl <= 350){
                        if (buff2Perm > 0){
                            bar = bar + 1500*2;
                        }else{
                            bar = bar + 1500;
                        }
                    }else if (lvl > 350 && lvl <= 390){
                        if (buff2Perm > 0){
                            bar = bar + 1200*2;
                        }else{
                            bar = bar + 1200;
                        }
                    }else if (lvl > 390 && lvl <= 425){
                        if (buff2Perm > 0){
                            bar = bar + 900*2;
                        }else{
                            bar = bar + 900;
                        }
                    }else if (lvl > 425 && lvl <= 485){
                        if (buff2Perm > 0){
                            bar = bar + 850*2;
                        }else{
                            bar = bar + 850;
                        }
                    }else if (lvl > 485 && lvl <= 530){
                        if (buff2Perm > 0){
                            bar = bar + 700*2;
                        }else{
                            bar = bar + 700;
                        }
                    }else if (lvl > 530 && lvl <= 680){
                        if (buff2Perm > 0){
                            bar = bar + 600*2;
                        }else{
                            bar = bar + 600;
                        }
                    }else if (lvl > 680 && lvl <= 750){
                        if (buff2Perm > 0){
                            bar = bar + 550*2;
                        }else{
                            bar = bar + 550;
                        }
                    }else if (lvl > 750 && lvl <= 900){
                        if (buff2Perm > 0){
                            bar = bar + 220*2;
                        }else{
                            bar = bar + 220;
                        }
                    }else if (lvl > 900 && lvl <= 998){
                        if (buff2Perm > 0){
                            bar = bar + 100*2;
                        }else{
                            bar = bar + 100;
                        }
                    }else if (lvl > 998 && lvl <= 1000){
                        if (buff2Perm > 0){
                            bar = bar + 1*2;
                        }else{
                            bar = bar + 1;
                        }
                    }
                    // XP POR LVL

                    textLvl.setText("lvl " + lvl.toString());

                }
            }
        };
        Thread myThread = new Thread(myRunnable);
        myThread.start();
    }
    private void detectHour(){
        // IDENTIFICAR SE É DIA OU NOITE
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        if (hour >= 0.0 && hour <= 4.59){
            // VIDEO AUTO PLAY
            VideoView videoTelaPrincipal = (VideoView)findViewById(R.id.videoTelaPrincipalInicio);
            videoTelaPrincipal.setVideoPath("android.resource://com.game.onebuttonadventure/raw/tela_nova_noite");
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
            VideoView videoTelaPrincipal = (VideoView)findViewById(R.id.videoTelaPrincipalInicio);
            videoTelaPrincipal.setVideoPath("android.resource://com.game.onebuttonadventure/raw/tela_nova_dia");
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
            VideoView videoTelaPrincipal = (VideoView)findViewById(R.id.videoTelaPrincipalInicio);
            videoTelaPrincipal.setVideoPath("android.resource://com.game.onebuttonadventure/raw/tela_nova_tarde");
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
            VideoView videoTelaPrincipal = (VideoView)findViewById(R.id.videoTelaPrincipalInicio);
            videoTelaPrincipal.setVideoPath("android.resource://com.game.onebuttonadventure/raw/tela_nova_noite");
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
    @SuppressLint("SetTextI18n")
    private void salvarBancoDeDados(){
        enviaLvl = lvl;
        TextView txtDay = (TextView)findViewById(R.id.textDay);
        TextView txtHisto = (TextView)findViewById(R.id.txtHist);
        TextView txtConq = (TextView)findViewById(R.id.txtMiss);
        TextView txtMap = (TextView)findViewById(R.id.txtMap);
        TextView txtIven = (TextView)findViewById(R.id.txtInven);
        ImageView buff1Image = (ImageView)findViewById(R.id.imageBuff1);
        ImageView buff2Image = (ImageView)findViewById(R.id.imageBuff2);
        ImageView buff3Image = (ImageView)findViewById(R.id.imageBuff3);
        try {
            if (recebeConfig == 0){
                txtHisto.setText("History");
                txtIven.setText("Inventary");
                txtMap.setText("Map");
                txtConq.setText("Missions");

            }else if (recebeConfig == 1){
                txtHisto.setText("História");
                txtIven.setText("Inventário");
                txtMap.setText("Mapa");
                txtConq.setText("Missões");

            }

            if (buff1Perm > 0){
                buff1Image.setVisibility(View.VISIBLE);
            } else if (buff2Perm > 0) {
                buff2Image.setVisibility(View.VISIBLE);

            } else if (buff3Perm > 0) {
                buff3Image.setVisibility(View.VISIBLE);
            }else {
                buff1Image.setVisibility(View.INVISIBLE);
                buff2Image.setVisibility(View.INVISIBLE);
                buff3Image.setVisibility(View.INVISIBLE);
            }



        }catch (Exception e) {
            e.printStackTrace();
        }

        // CARREGAR AS INFORMAÇÕES SALVAS
        TextView textLvl = (TextView)findViewById(R.id.textLvl);
        textLvl.setText("lvl " + lvl.toString());
        ProgressBar barLvl = (ProgressBar)findViewById(R.id.barLvl);
        barLvl.setProgress(bar);
        TextView moeda = (TextView)findViewById(R.id.textCoins);
        moeda.setText("Coin: "+ coin.toString());
        // CARREGAR AS INFORMAÇÕES SALVAS

            mainBan = getSharedPreferences("salver", MODE_PRIVATE);
            coin = mainBan.getInt("coin", 0);
            personagem = mainBan.getInt("personagem", 1);
            equipButton = mainBan.getInt("button", 1);
            buff1Perm = mainBan.getInt("buff1", 0);
            buff2Perm = mainBan.getInt("buff2", 0);
            buff3Perm = mainBan.getInt("buff3", 0);
            eventoPerso1 = mainBan.getInt("event1",0);
            eventoPerso2 = mainBan.getInt("event1.2",0);

            saveOffLvl = getSharedPreferences("savelvl", MODE_PRIVATE);
            lvl = saveOffLvl.getInt("lvlSalvo",0);
            bar = saveOffLvl.getInt("barSalvo", 0);

            saveEvent = getSharedPreferences("saveEvent1", MODE_PRIVATE);
            contarKillEvent1 = saveEvent.getInt("event1Click",0);

            savelvl = getSharedPreferences("teste", MODE_PRIVATE);
            contarClick = savelvl.getInt("salvarClick", 0);
    }
    private void lerDado(){
        mainBan = PreferenceManager.getDefaultSharedPreferences(this);
        coin = mainBan.getInt("coin", 0);
        personagem = mainBan.getInt("personagem", 1);
        buff1Perm = mainBan.getInt("buff1",0);
        buff2Perm = mainBan.getInt("buff2",0);
        buff3Perm = mainBan.getInt("buff3",0);
        equipButton = mainBan.getInt("button", 1);
        eventoPerso1 = mainBan.getInt("event1",0);
        eventoPerso2 = mainBan.getInt("event1.2",0);
    }
    private void musicaTela(){
            // TOCAR E LOOP DE MUSICA
            musicaTelaPrincipal = MediaPlayer.create(this,R.raw.tela_principal);
            //musicaTelaPrincipal.setLooping(true);
            musicaTelaPrincipal.seekTo(0);
            musicaTelaPrincipal.setVolume(0.5f,0.5f);
            musicaTelaPrincipal.start();
            if (musicaTelaPrincipal != null){
                musicaTelaPrincipal.setLooping(true);
            }
            // TOCAR E LOOP DE MUSICA


    }
    // CARREGAMENTO DE DADOS

    // EVENTOS DE CLICK
    @SuppressLint({"SetTextI18n", "ApplySharedPref"})
    public void youClickInButtonInicial(View view){
        carregarGifs();
        contarClick++;
        TextView moeda = (TextView)findViewById(R.id.textCoins);
        moeda.setText("Coin: "+ coin.toString());
        ProgressBar barEvent1 = (ProgressBar)findViewById(R.id.barProgressEvent);
        ImageView imageMonsterEvent = (ImageView)findViewById(R.id.btnMonsterEvent);
        final ImageButton btnPrincipal = (ImageButton)findViewById(R.id.btnPrincipal);
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);

        // COIN
        try {
            if (hour >= 5 && hour <= 18){
                Random coinRandom = new Random();
                int coinVaria = coinRandom.nextInt(500);
                if (coinVaria >= 0 && coinVaria <= 5){
                    if(buff1Perm > 0){
                        coin = coin + 150*2;
                    }else {
                        coin = coin + 150;
                    }
                }else if(coinVaria > 5 && coinVaria <= 200){
                    if(buff1Perm > 0){
                        coin = coin + 50*2;
                    }else {
                        coin = coin + 50;
                    }
                }else if (coinVaria > 200 && coinVaria <= 500){
                    if(buff1Perm > 0){
                        coin = coin + 20*2;
                    }else {
                        coin = coin + 20;
                    }
                }
            }else if (hour > 18 && hour <= 23.59){
                Random coinRandom = new Random();
                int coinVaria = coinRandom.nextInt(500);
                if (coinVaria >= 0 && coinVaria <= 5){
                    if(buff1Perm > 0){
                        coin = coin + 500*2;
                    }else {
                        coin = coin + 500;
                    }
                }else if(coinVaria > 5 && coinVaria <= 200){
                    if(buff1Perm > 0){
                        coin = coin + 250*2;
                    }else {
                        coin = coin + 250;
                    }
                }else if (coinVaria > 200 && coinVaria <= 500){
                    if(buff1Perm > 0){
                        coin = coin + 120*2;
                    }else {
                        coin = coin + 120;
                    }
                }

            }else if (hour >= 0.0 && hour <= 4.59){
                Random coinRandom = new Random();
                int coinVaria = coinRandom.nextInt(500);
                if (coinVaria >= 0 && coinVaria <= 5){
                    if(buff1Perm > 0){
                        coin = coin + 750*2;
                    }else {
                        coin = coin + 750;
                    }
                }else if(coinVaria > 5 && coinVaria <= 200){
                    if(buff1Perm > 0){
                        coin = coin + 500*2;
                    }else {
                        coin = coin + 500;
                    }
                }else if (coinVaria > 200 && coinVaria <= 500){
                    if(buff1Perm > 0){
                        coin = coin + 250*2;
                    }else {
                        coin = coin + 250;
                    }
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        // COIN

        //EVENTO
        try {
            if (barEvent1.getVisibility() == View.INVISIBLE && imageMonsterEvent.getVisibility() == View.INVISIBLE){
                Random randAtivaEvent = new Random();

                int ativadoEvent1 = randAtivaEvent.nextInt(500);

                if (hour >= 5 && hour <= 18){
                    if (ativadoEvent1 >= 0 && ativadoEvent1 <= 2){
                        barEvent1.setVisibility(View.VISIBLE);
                        imageMonsterEvent.setVisibility(View.VISIBLE);
                    }

                }else if (hour > 18 && hour <= 23.59){
                    if (ativadoEvent1 >= 0 && ativadoEvent1 <= 10){
                        barEvent1.setVisibility(View.VISIBLE);
                        imageMonsterEvent.setVisibility(View.VISIBLE);
                    }

                }else if (hour >= 0.0 && hour <= 4.59){
                    if (ativadoEvent1 >= 0 && ativadoEvent1 <= 25){
                        barEvent1.setVisibility(View.VISIBLE);
                        imageMonsterEvent.setVisibility(View.VISIBLE);
                    }
                }


            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        //EVENTO


        // ACESSANDO OS COMPONETES DA TELA
        ProgressBar barLvl = (ProgressBar)findViewById(R.id.barLvl);
        TextView textLvl = (TextView)findViewById(R.id.textLvl);

        // ACESSANDO OS COMPONETES DA TELA

        // QUANDO A BARRA ENCHER, ZERA E ACRESCENTA 1 LVL
        barLvl.setProgress(bar);
        if (bar >= 200000){
            lvl++;
            bar = 0; }
        // // QUANDO A BARRA ENCHER, ZERA E ACRESCENTA 1 LVL

        // XP POR LVL
        if (lvl >= 0 && lvl <= 10){
            if (buff2Perm > 0){
                bar = bar + 5000*2;
            }else{
                bar = bar + 5000;
            }

        }else if (lvl > 10 && lvl <= 25){
            if (buff2Perm > 0){
                bar = bar + 4900*2;
            }else{
                bar = bar + 4900;
            }
        }else if (lvl > 25 && lvl <= 45){
            if (buff2Perm > 0){
                bar = bar + 3500*2;
            }else{
                bar = bar + 3500;
            }
        }else if (lvl > 45 && lvl <= 65){
            if (buff2Perm > 0){
                bar = bar + 3500*2;
            }else{
                bar = bar + 3500;
            }
        }else if (lvl > 65 && lvl <= 90){
            if (buff2Perm > 0){
                bar = bar + 3200*2;
            }else{
                bar = bar + 3200;
            }
        }else if (lvl > 90 && lvl <= 125){
            if (buff2Perm > 0){
                bar = bar + 2500*2;
            }else{
                bar = bar + 2500;
            }
        }else if (lvl > 125 && lvl <= 175){
            if (buff2Perm > 0){
                bar = bar + 2200*2;
            }else{
                bar = bar + 2200;
            }
        }else if (lvl > 175 && lvl <= 275){
            if (buff2Perm > 0){
                bar = bar + 1950*2;
            }else{
                bar = bar + 1950;
            }
        }
        else if (lvl > 275 && lvl <= 300){
            if (buff2Perm > 0){
                bar = bar + 1750*2;
            }else{
                bar = bar + 1750;
            }
        }
        else if (lvl > 300 && lvl <= 350){
            if (buff2Perm > 0){
                bar = bar + 1500*2;
            }else{
                bar = bar + 1500;
            }
        }else if (lvl > 350 && lvl <= 390){
            if (buff2Perm > 0){
                bar = bar + 1200*2;
            }else{
                bar = bar + 1200;
            }
        }else if (lvl > 390 && lvl <= 425){
            if (buff2Perm > 0){
                bar = bar + 900*2;
            }else{
                bar = bar + 900;
            }
        }else if (lvl > 425 && lvl <= 485){
            if (buff2Perm > 0){
                bar = bar + 850*2;
            }else{
                bar = bar + 850;
            }
        }else if (lvl > 485 && lvl <= 530){
            if (buff2Perm > 0){
                bar = bar + 700*2;
            }else{
                bar = bar + 700;
            }
        }else if (lvl > 530 && lvl <= 680){
            if (buff2Perm > 0){
                bar = bar + 600*2;
            }else{
                bar = bar + 600;
            }
        }else if (lvl > 680 && lvl <= 750){
            if (buff2Perm > 0){
                bar = bar + 550*2;
            }else{
                bar = bar + 550;
            }
        }else if (lvl > 750 && lvl <= 900){
            if (buff2Perm > 0){
                bar = bar + 220*2;
            }else{
                bar = bar + 220;
            }
        }else if (lvl > 900 && lvl <= 998){
            if (buff2Perm > 0){
                bar = bar + 100*2;
            }else{
                bar = bar + 100;
            }
        }else if (lvl > 998 && lvl <= 1000){
            if (buff2Perm > 0){
                bar = bar + 1*2;
            }else{
                bar = bar + 1;
            }
        }
        // XP POR LVL

        textLvl.setText("lvl " + lvl.toString());



        // QUANDO A VIDA DO MONSTRO ZERAR, PASSA DE STAGE
        // QUANDO A VIDA DO MONSTRO ZERAR, PASSA DE STAGE

    }
    public void youClickInButtonEvent(View view){
        ProgressBar barEvent1 = (ProgressBar)findViewById(R.id.barProgressEvent);
        ImageView imageMonsterEvent = (ImageView)findViewById(R.id.btnMonsterEvent);
        barEvent1.setProgress(barEvent);

        if(barEvent1.getVisibility() == View.VISIBLE && imageMonsterEvent.getVisibility() == View.VISIBLE){
            Random randomDano = new Random();
            int dano = randomDano.nextInt(500);

            if (dano >= 0 && dano <= 5){
                barEvent = barEvent - 50;
            }else if (dano >= 5 && dano <= 35){
                barEvent = barEvent - 20;
            }else if (dano >= 35 && dano <= 75){
                barEvent = barEvent - 15;
            }else if (dano >= 75 && dano <= 210){
                barEvent = barEvent - 10;
            }else if (dano >= 210 && dano <= 500){
                barEvent = barEvent - 5;
            }

            if (barEvent <= 0){
                barEvent = 500;
                barEvent1.setVisibility(View.INVISIBLE);
                imageMonsterEvent.setVisibility(View.INVISIBLE);
                contarKillEvent1++;
            }
        }


    }
    public void onClickConquistaButton (View view){
        // CHAMANDO
        Intent intent = new Intent(MainActivity.this, Coquistas.class);
        startActivity(intent);
        overridePendingTransition(R.anim.bottom_in, R.anim.top_out);
        musicaTelaPrincipal.stop();
        finish();

    }
    public void onClickHistoryButton (View view){
        // CHAMANDO
        Intent intent = new Intent(MainActivity.this, History.class);
        startActivity(intent);
        overridePendingTransition(R.anim.bottom_in, R.anim.top_out);
        musicaTelaPrincipal.stop();
        finish();

    }
    public void onClickConfigButton (View view){
        // CHAMANDO
        Intent intent = new Intent(MainActivity.this, Config.class);
        startActivity(intent);
        overridePendingTransition(R.anim.in_rigth, R.anim.fade_out);
        musicaTelaPrincipal.stop();
        finish();

    }
    public void onClickLojaButton (View view){
        // CHAMANDO/
        Intent intent = new Intent(MainActivity.this, Loja.class);
        startActivity(intent);
        overridePendingTransition(R.anim.in_left, R.anim.fade_out);
        musicaTelaPrincipal.stop();
        finish();

    }
    public void onClickMapaButton (View view){
        // CHAMANDO
        Intent intent = new Intent(MainActivity.this, Mapa.class);
        startActivity(intent);
        overridePendingTransition(R.anim.bottom_in, R.anim.top_out);
        musicaTelaPrincipal.stop();
        finish();

    }
    public void onClickBossButton (View view){
        // CHAMANDO
        Intent intent = new Intent(MainActivity.this, Boss.class);
        startActivity(intent);
        overridePendingTransition(R.anim.bottom_in, R.anim.top_out);
        musicaTelaPrincipal.stop();
        finish();

    }
    public void onClickInventButton (View view){
        // CHAMANDO
        Intent intent = new Intent(MainActivity.this, Inventario.class);
        startActivity(intent);
        overridePendingTransition(R.anim.bottom_in, R.anim.top_out);
        musicaTelaPrincipal.stop();
        finish();

    }
    public void onClickEventButton (View view){
        // CHAMANDO
        Intent intent = new Intent(MainActivity.this, Tela_Evento.class);
        startActivity(intent);
        overridePendingTransition(R.anim.bottom_in, R.anim.top_out);
        musicaTelaPrincipal.stop();
        finish();

    }
    // EVENTOS DE CLICK

    @SuppressLint("ApplySharedPref")
    @Override
    protected void onPause() {
        super.onPause();
        try {
            editorMainBan = mainBan.edit();
            editorMainBan.putInt("coin", coin);
            editorMainBan.commit();
            editorMainBan.putInt("personagem", personagem);
            editorMainBan.commit();
            editorMainBan.putInt("button", equipButton);
            editorMainBan.commit();
            editorMainBan.putInt("buff1", buff1Perm);
            editorMainBan.commit();
            editorMainBan.putInt("buff2", buff2Perm);
            editorMainBan.commit();
            editorMainBan.putInt("buff3", buff3Perm);
            editorMainBan.commit();
            editorMainBan.putInt("event1", eventoPerso1);
            editorMainBan.commit();
            editorMainBan.putInt("event1.2", eventoPerso2);
            editorMainBan.commit();

            saveOffLvlEditor = saveOffLvl.edit();
            saveOffLvlEditor.putInt("lvlSalvo", lvl);
            saveOffLvlEditor.commit();
            saveOffLvlEditor.putInt("barSalvo", bar);
            saveOffLvlEditor.commit();

            saveEditorEvent = saveEvent.edit();
            saveEditorEvent.putInt("event1Click", contarKillEvent1);
            saveEditorEvent.commit();

            editorlvl = savelvl.edit();
            editorlvl.putInt("salvarClick", contarClick);
            editorlvl.commit();

        }catch (Exception e) {
            e.printStackTrace();
        }
        musicaTelaPrincipal.stop();
    }

    @SuppressLint("ApplySharedPref")
    @Override
    protected void onStop(){
        super.onStop();
        // SALVA PROGRESSO DO USUÁRO
        try {
            editorMainBan = mainBan.edit();
            editorMainBan.putInt("coin", coin);
            editorMainBan.commit();
            editorMainBan.putInt("personagem", personagem);
            editorMainBan.commit();
            editorMainBan.putInt("button", equipButton);
            editorMainBan.commit();
            editorMainBan.putInt("buff1", buff1Perm);
            editorMainBan.commit();
            editorMainBan.putInt("buff2", buff2Perm);
            editorMainBan.commit();
            editorMainBan.putInt("buff3", buff3Perm);
            editorMainBan.commit();
            editorMainBan.putInt("event1", eventoPerso1);
            editorMainBan.commit();
            editorMainBan.putInt("event1.2", eventoPerso2);
            editorMainBan.commit();

            saveOffLvlEditor = saveOffLvl.edit();
            saveOffLvlEditor.putInt("lvlSalvo", lvl);
            saveOffLvlEditor.commit();
            saveOffLvlEditor.putInt("barSalvo", bar);
            saveOffLvlEditor.commit();

            saveEditorEvent = saveEvent.edit();
            saveEditorEvent.putInt("event1Click", contarKillEvent1);
            saveEditorEvent.commit();


            editorlvl = savelvl.edit();
            editorlvl.putInt("salvarClick", contarClick);
            editorlvl.commit();

        }catch (Exception e) {
            e.printStackTrace();
        }

        // SALVA PROGRESSO DO USUÁRO
        musicaTelaPrincipal.stop();


    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @SuppressLint("ApplySharedPref")
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // SALVA PROGRESSO DO USUÁRO
        try {
            editorMainBan = mainBan.edit();
            editorMainBan.putInt("coin", coin);
            editorMainBan.commit();
            editorMainBan.putInt("personagem", personagem);
            editorMainBan.commit();
            editorMainBan.putInt("button", equipButton);
            editorMainBan.commit();
            editorMainBan.putInt("buff1", buff1Perm);
            editorMainBan.commit();
            editorMainBan.putInt("buff2", buff2Perm);
            editorMainBan.commit();
            editorMainBan.putInt("buff3", buff3Perm);
            editorMainBan.commit();
            editorMainBan.putInt("event1", eventoPerso1);
            editorMainBan.commit();
            editorMainBan.putInt("event1.2", eventoPerso2);
            editorMainBan.commit();

            saveOffLvlEditor = saveOffLvl.edit();
            saveOffLvlEditor.putInt("lvlSalvo", lvl);
            saveOffLvlEditor.commit();
            saveOffLvlEditor.putInt("barSalvo", bar);
            saveOffLvlEditor.commit();

            saveEditorEvent = saveEvent.edit();
            saveEditorEvent.putInt("event1Click", contarKillEvent1);
            saveEditorEvent.commit();

            editorlvl = savelvl.edit();
            editorlvl.putInt("salvarClick", contarClick);
            editorlvl.commit();

        }catch (Exception e) {
            e.printStackTrace();
        }

        // SALVA PROGRESSO DO USUÁRO
        musicaTelaPrincipal.stop();
        finishAffinity();

    }
}
