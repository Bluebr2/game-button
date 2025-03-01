package com.game.onebuttonadventure;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import android.widget.TextView;
import static com.game.onebuttonadventure.TelaPrincipal.enviaLang;


public class Config extends AppCompatActivity {

    // BANCO DE DADOS
    private int recebeLan = enviaLang;
    // BANCO DE DADOS

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        //CARREGA BANCO DE DADOS
        bancoDeDadosConfig();

    }

    @Override
    protected void onStart() {
        super.onStart();
        hideNavigationBar();
        bancoDeDadosConfig();
    }

    @Override
    protected void onResume() {
        super.onResume();
        hideNavigationBar();
        bancoDeDadosConfig();
    }
    private void hideNavigationBar(){
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        );
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @SuppressLint("SetTextI18n")
    private void bancoDeDadosConfig(){
        TextView bug = (TextView)findViewById(R.id.msgBug);
        if(recebeLan == 1){

            bug.setText("Achou um BUG? Contate-nos!");
        }else if (recebeLan == 0){

            bug.setText("Did you find a BUG? Contact us!");

        }
    }

    // CARREGAR BANCO

    // EVENTOS DE CLICK
    public  void onClickVoltarConfig(View view){
        // CHAMANDO
        Intent intent = new Intent(Config.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.in_rigth, R.anim.fade_out);
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
