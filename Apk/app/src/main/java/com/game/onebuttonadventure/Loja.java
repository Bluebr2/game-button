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
import android.preference.PreferenceManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;




import static com.game.onebuttonadventure.TelaPrincipal.enviaLang;


public class Loja extends AppCompatActivity {

    private AlertDialog alerta;
    private int recebeBuff = 0;
    private int recebeCoin = 0;
    private MediaPlayer musicaLoja;
    private int recebeLna = enviaLang;
    SharedPreferences cod_loja;
    AlertDialog.Builder biulder;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loja);
        musicaTela();
    }

    @Override
    protected void onStart() {
        super.onStart();
        hideSystemUI();
        detectLang();
    }

    @Override
    protected void onResume() {
        super.onResume();
        hideSystemUI();
        detectLang();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        hideSystemUI();
    }

    // FULLSCREEN
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


    @SuppressLint("SetTextI18n")
    private void detectLang(){

        TextView text1 = (TextView)findViewById(R.id.txtTopLoja);
        TextView text2 = (TextView)findViewById(R.id.txtGet2xp);
        TextView text3 = (TextView)findViewById(R.id.txt2xpoTime);
        TextView text4 = (TextView)findViewById(R.id.txt2xpp);
        TextView text5 = (TextView)findViewById(R.id.txt2xppTime);
        TextView text6 = (TextView)findViewById(R.id.txtClickRobot);
        TextView text7 = (TextView)findViewById(R.id.txtClickTimeRobot);

        if (recebeLna == 0){
            text1.setText("Store");
            text2.setText("Get 2x more points by clicking the button.");
            text3.setText("Permanent.");
            text4.setText("Earn 2x more XP.");
            text5.setText("Permanent.");
            text6.setText("Every 10 seconds, the robot clicks the button once.");
            text7.setText("Permanent.");

        }else if (recebeLna == 1){
            text1.setText("Loja");
            text2.setText("Consiga 2x mais pontos clicando no botão.");
            text3.setText("Permanente.");
            text4.setText("Ganhe 2x mais XP.");
            text5.setText("Permanente.");
            text6.setText("A cada 10 segundos, o robô clica no botão uma vez.");
            text7.setText("Permanente.");

        }

    }
    private void musicaTela(){
            // TOCAR E LOOP DE MUSICA
            musicaLoja = MediaPlayer.create(this,R.raw.loja_song);
            musicaLoja.seekTo(0);
            musicaLoja.setVolume(0.5f,0.5f);
            musicaLoja.start();
            // TOCAR E LOOP DE MUSICA
            if (musicaLoja != null){
                musicaLoja.setLooping(true);
            }
            // TOCAR E LOOP DE MUSICA


    }
    // CARREGAR DADO

    // EVENTOS DE CLICK
    public void onClicVoltarLoja(View view){
        // CHAMANDO
        Intent intent = new Intent(Loja.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.in_left, R.anim.fade_out);
        musicaLoja.stop();
        finish();
    }
    @SuppressLint("ApplySharedPref")
    public void onClickBtnBuy1(View view){
        cod_loja = PreferenceManager.getDefaultSharedPreferences(this);
        recebeBuff = cod_loja.getInt("buff1", 0);
        recebeCoin = cod_loja.getInt("coin", 0);
        biulder = new AlertDialog.Builder(this);
        try {
            if (recebeLna == 0){
                biulder.setTitle("Store");
                biulder.setMessage("Do you want to buy this Buff? \n");
                biulder.setPositiveButton("Purchase: 800.000", new DialogInterface.OnClickListener() {
                    @SuppressLint("ApplySharedPref")
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (recebeCoin >= 800000){
                            recebeCoin = recebeCoin - 800000;
                            recebeBuff = 1;
                            cod_loja.edit().putInt("coin", recebeCoin).commit();
                            cod_loja.edit().putInt("buff1", recebeBuff).commit();

                        }else {
                            biulder.setTitle("Store");
                            biulder.setMessage("You don't have enough Coins!");
                            biulder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    alerta.hide();
                                }
                            });


                        }

                    }
                });

                if (recebeBuff > 0){
                    biulder.setTitle("Store");
                    biulder.setMessage("You already own this BUFF!");
                    biulder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            alerta.hide();
                        }
                    });
                }

            }else{
                biulder.setTitle("Loja");
                biulder.setMessage("Você deseja comprar este Buff? \n");
                biulder.setPositiveButton("Comprar: 800.000", new DialogInterface.OnClickListener() {
                    @SuppressLint("ApplySharedPref")
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (recebeCoin >= 800000){
                            recebeCoin = recebeCoin - 800000;
                            recebeBuff = 1;
                            cod_loja.edit().putInt("coin", recebeCoin).commit();
                            cod_loja.edit().putInt("buff1", recebeBuff).commit();

                        }else{
                            biulder.setTitle("Loja");
                            biulder.setMessage("Você não possui Coins suficientes!");
                            biulder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    alerta.hide();
                                }
                            });
                        }

                    }
                });

                if (recebeBuff > 0){
                    biulder.setTitle("Loja");
                    biulder.setMessage("Você já possui este BUFF!");
                    biulder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            alerta.hide();
                        }
                    });
                }


            }

        }catch (Exception e) {
            e.printStackTrace();
        }

        //cria o AlertDialog
        alerta = biulder.create();
        //Exibe
        alerta.show();

    }
    public void onClickBtnBuy2(View view){
        cod_loja = PreferenceManager.getDefaultSharedPreferences(this);
        recebeBuff = cod_loja.getInt("buff2", 0);
        recebeCoin = cod_loja.getInt("coin", 0);
        biulder = new AlertDialog.Builder(this);
        try {
           if (recebeLna == 0){
               biulder.setTitle("Store");
               biulder.setMessage("Do you want to buy this Buff? \n");
               biulder.setPositiveButton("Purchase: 1.200.000", new DialogInterface.OnClickListener() {
                   @SuppressLint("ApplySharedPref")
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       if (recebeCoin >= 1200000){
                           recebeCoin = recebeCoin - 1200000;
                           recebeBuff = 1;
                           cod_loja.edit().putInt("coin", recebeCoin).commit();
                           cod_loja.edit().putInt("buff2", recebeBuff).commit();

                       }else{

                           biulder.setTitle("Store");
                           biulder.setMessage("You don't have enough Coins!");
                           biulder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                               @Override
                               public void onClick(DialogInterface dialog, int which) {
                                   alerta.hide();
                               }
                           });
                       }

                   }
               });

               if (recebeBuff > 0){
                   biulder.setTitle("Store");
                   biulder.setMessage("You already own this BUFF!");
                   biulder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialog, int which) {
                           alerta.hide();
                       }
                   });
               }

           }else{
               biulder.setTitle("Loja");
               biulder.setMessage("Você deseja comprar este Buff? \n");
               biulder.setPositiveButton("Comprar: 1.200.000", new DialogInterface.OnClickListener() {
                   @SuppressLint("ApplySharedPref")
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       if (recebeCoin >= 1200000){
                           recebeCoin = recebeCoin - 1200000;
                           recebeBuff = 1;
                           cod_loja.edit().putInt("coin", recebeCoin).commit();
                           cod_loja.edit().putInt("buff2", recebeBuff).commit();

                       }else{
                           biulder.setTitle("Loja");
                           biulder.setMessage("Você não possue Coins suficientes!");
                           biulder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                               @Override
                               public void onClick(DialogInterface dialog, int which) {
                                   alerta.hide();
                               }
                           });
                       }

                   }
               });

               if (recebeBuff > 0){
                   biulder.setTitle("Loja");
                   biulder.setMessage("Você já possui este BUFF!");
                   biulder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialog, int which) {
                           alerta.hide();
                       }
                   });
               }


           }

        }catch (Exception e) {
            e.printStackTrace();
        }

        //cria o AlertDialog
        alerta = biulder.create();
        //Exibe
        alerta.show();
    }
    public void onClickBtnBuy3(View view){cod_loja = PreferenceManager.getDefaultSharedPreferences(this);
        recebeBuff = cod_loja.getInt("buff3", 0);
        recebeCoin = cod_loja.getInt("coin", 0);
        biulder = new AlertDialog.Builder(this);
        try {
            if (recebeLna == 0){
                biulder.setTitle("Store");
                biulder.setMessage("Do you want to buy this Buff? \n");
                biulder.setPositiveButton("Purchase: 120.000", new DialogInterface.OnClickListener() {
                    @SuppressLint("ApplySharedPref")
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (recebeCoin >= 120000){
                            recebeCoin = recebeCoin - 120000;
                            recebeBuff = 1;
                            cod_loja.edit().putInt("coin", recebeCoin).commit();
                            cod_loja.edit().putInt("buff3", recebeBuff).commit();

                        }else{

                            biulder.setTitle("Store");
                            biulder.setMessage("You don't have enough Coins!");
                            biulder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    alerta.hide();
                                }
                            });
                        }

                    }
                });

                if (recebeBuff > 0){
                    biulder.setTitle("Store");
                    biulder.setMessage("You already own this BUFF!");
                    biulder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            alerta.hide();
                        }
                    });
                }

            }else{
                biulder.setTitle("Loja");
                biulder.setMessage("Você deseja comprar este Buff? \n");
                biulder.setPositiveButton("Comprar: 120.000", new DialogInterface.OnClickListener() {
                    @SuppressLint("ApplySharedPref")
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (recebeCoin >= 120000){
                            recebeCoin = recebeCoin - 120000;
                            recebeBuff = 1;
                            cod_loja.edit().putInt("coin", recebeCoin).commit();
                            cod_loja.edit().putInt("buff3", recebeBuff).commit();

                        }else{
                            biulder.setTitle("Loja");
                            biulder.setMessage("Você não possue Coins suficientes!");
                            biulder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    alerta.hide();
                                }
                            });
                        }

                    }
                });

                if (recebeBuff > 0){
                    biulder.setTitle("Loja");
                    biulder.setMessage("Você já possui este BUFF!");
                    biulder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            alerta.hide();
                        }
                    });
                }


            }

        }catch (Exception e) {
            e.printStackTrace();
        }

        //cria o AlertDialog
        alerta = biulder.create();
        //Exibe
        alerta.show();

    }
    // EVENTOS DE CLICK

    @Override
    protected void onPause() {
        super.onPause();
        musicaLoja.stop();
    }

    @Override
    public void onStop(){
        super.onStop();
        musicaLoja.stop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        musicaLoja.stop();
        finishAffinity();
    }
}
