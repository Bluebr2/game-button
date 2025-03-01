package com.game.onebuttonadventure;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import static com.game.onebuttonadventure.MainActivity.contarKillEvent1;
import static com.game.onebuttonadventure.MainActivity.eventoPerso1;
import static com.game.onebuttonadventure.MainActivity.eventoPerso2;
import static com.game.onebuttonadventure.TelaPrincipal.enviaLang;

public class Tela_Evento extends AppCompatActivity {
    private int recebeCoins = 0;
    private int recebeLigua = enviaLang;
    SharedPreferences cod_event1;
    private AlertDialog alerta;
    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela__evento);
        recebeMain();
    }

    @Override
    protected void onStart() {
        super.onStart();
        hideSystemUI();
        recebeMain();
    }

    @Override
    protected void onResume() {
        super.onResume();
        hideSystemUI();
        recebeMain();
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

    public void onClickVoltar(View view){
        Intent intent = new Intent(Tela_Evento.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.in_left, R.anim.fade_out);
        finish();
    }

    @SuppressLint({"ApplySharedPref", "SetTextI18n"})
    private void recebeMain(){
        cod_event1 = PreferenceManager.getDefaultSharedPreferences(this);
        recebeCoins = cod_event1.getInt("coin",0);
        TextView textEvento1 = (TextView)findViewById(R.id.textEvent1);
        ProgressBar pegarProgresso = (ProgressBar)findViewById(R.id.progressEvent1);
        pegarProgresso.setProgress(contarKillEvent1);
        textEvento1.setText(contarKillEvent1.toString() + "/240");

        if (contarKillEvent1 == 80){
            recebeCoins = recebeCoins + 2000;
            cod_event1.edit().putInt("coin", recebeCoins).commit();
        }else if (contarKillEvent1 == 120){
            recebeCoins = recebeCoins + 5000;
            cod_event1.edit().putInt("coin", recebeCoins).commit();
        }else if (contarKillEvent1 == 180){
            recebeCoins = recebeCoins + 5000;
            cod_event1.edit().putInt("coin", recebeCoins).commit();
        }else if (contarKillEvent1 == 240){
            recebeCoins = recebeCoins + 6000;
            cod_event1.edit().putInt("coin", recebeCoins).commit();
        }else if (contarKillEvent1 == 300){
            recebeCoins = recebeCoins + 6500;
            cod_event1.edit().putInt("coin", recebeCoins).commit();
        }else if (contarKillEvent1 == 340){
            recebeCoins = recebeCoins + 6500;
            cod_event1.edit().putInt("coin", recebeCoins).commit();
        }else if (contarKillEvent1 == 400){
            recebeCoins = recebeCoins + 6500;
            cod_event1.edit().putInt("coin", recebeCoins).commit();
        }else if (contarKillEvent1 == 500){
            recebeCoins = recebeCoins + 200000;
            cod_event1.edit().putInt("coin", recebeCoins).commit();
        }

        if (contarKillEvent1 == 200){
            builder = new AlertDialog.Builder(this);
            if (recebeLigua == 0){
                builder.setTitle("Event");
                builder.setMessage("Congratulations, you have unlocked (Leonard - Demon King)!");
            }else{
                builder.setTitle("Evento");
                builder.setMessage("Parabéns, você desbloqueou (Leonard - Demon King)!");

            }
            eventoPerso1 = 1;
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    alerta.hide();
                }
            });

            alerta = builder.create();
            alerta.show();
        }else{

        }

        if (contarKillEvent1 == 600){
            builder = new AlertDialog.Builder(this);
            if (recebeLigua == 0){
                builder.setTitle("Event");
                builder.setMessage("Congratulations, you have unlocked (Barthur - Robot)!");
            }else {
                builder.setTitle("Evento");
                builder.setMessage("Parabéns, você desbloqueou (Barthur - Robot)!");
            }
            eventoPerso2 = 1;

            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    alerta.hide();
                }
            });
            alerta = builder.create();
            alerta.show();
        }else {

        }



    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finishAffinity();
    }
}