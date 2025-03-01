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
import android.widget.ScrollView;
import android.widget.VideoView;


import static com.game.onebuttonadventure.MainActivity.eventoPerso1;
import static com.game.onebuttonadventure.MainActivity.eventoPerso2;
import static com.game.onebuttonadventure.MainActivity.personagem;
import static com.game.onebuttonadventure.TelaPrincipal.enviaLang;

public class Inventario extends AppCompatActivity {
    private AlertDialog inven;
    private AlertDialog.Builder builder;
    private MediaPlayer musicaInven;
    private int recebeL = enviaLang;
    private Integer comprarAnim2 = 0;
    private int update = 1;
    private int comprarAnim4 = 0;
    private int comprarAnim5 = 0;
    private int comprarBtn2 = 0;
    private int comprarBtn3 = 0;
    private int comprarBtn4 = 0;
    private int recebeValor = 0;
    private int recebePer = 0;
    private int recebePerEvent1 = 0;
    private int recebePerEvent12 = 0;
    private int recebeButton = 0;
    ScrollView scrow;
    SharedPreferences cod_main;

    SharedPreferences saveInen;
    SharedPreferences.Editor editorIven;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideSystemUI();
        setContentView(R.layout.activity_inventario);
        salvarBanco();
        carregarGifs();
        musicaTela();
    }

    @Override
    protected void onStart() {
        super.onStart();
        hideSystemUI();
        salvarBanco();
        carregarGifs();
    }

    @Override
    protected void onResume() {
        super.onResume();
        hideSystemUI();
        salvarBanco();
        carregarGifs();
    }
    // FULLSCREEN

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus){
            hideSystemUI();
            showSystemUI();
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
    private void showSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                // Note that system bars will only be "visible" if none of the
                // LOW_PROFILE, HIDE_NAVIGATION, or FULLSCREEN flags are set.
                if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                    hideSystemUI();
                    // TODO: The system bars are visible. Make any desired
                    // adjustments to your UI, such as showing the action bar or
                    // other navigational controls.
                } else {
                    // TODO: The system bars are NOT visible. Make any desired
                    // adjustments to your UI, such as hiding the action bar or
                    // other navigational controls.
                }
            }
        });
    }
    // FULLSCREEN

    // CARREGA DADOS
    private void carregarGifs(){
        VideoView telaInven = (VideoView)findViewById(R.id.videoGifInven);
        if (personagem == 1 | update == 1){
            // VIDEO AUTO PLAY
            telaInven.setVideoPath("android.resource://com.game.onebuttonadventure/raw/leonard_normal");
            telaInven.start();
            telaInven.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.setLooping(true);
                }
            });
            // VIDEO AUTO PLAY

        }else if (personagem == 2 | update == 2){
            // VIDEO AUTO PLAY
            telaInven.setVideoPath("android.resource://com.game.onebuttonadventure/raw/leonard_druida");
            telaInven.start();
            telaInven.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.setLooping(true);
                }
            });
            // VIDEO AUTO PLAY

        }else if (personagem == 3 | update == 3){
            // VIDEO AUTO PLAY
            telaInven.setVideoPath("android.resource://com.game.onebuttonadventure/raw/leonard_trevas");
            telaInven.start();
            telaInven.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.setLooping(true);
                }
            });
            // VIDEO AUTO PLAY

        }else if (personagem == 4 | update == 4){
            // VIDEO AUTO PLAY
            telaInven.setVideoPath("android.resource://com.game.onebuttonadventure/raw/rei_normal");
            telaInven.start();
            telaInven.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.setLooping(true);
                }
            });
            // VIDEO AUTO PLAY

        }else if (personagem == 5 | update == 5){
            // VIDEO AUTO PLAY
            telaInven.setVideoPath("android.resource://com.game.onebuttonadventure/raw/rei_toxic");
            telaInven.start();
            telaInven.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.setLooping(true);
                }
            });
            // VIDEO AUTO PLAY

        }else if (personagem == 6 | update == 6){
            // VIDEO AUTO PLAY
            telaInven.setVideoPath("android.resource://com.game.onebuttonadventure/raw/rei_robot");
            telaInven.start();
            telaInven.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.setLooping(true);
                }
            });
            // VIDEO AUTO PLAY

        }

    }
    private void musicaTela(){
            // TOCAR E LOOP DE MUSICA
            musicaInven = MediaPlayer.create(this,R.raw.invent_song);
            musicaInven.seekTo(0);
            musicaInven.setVolume(0.5f,0.5f);
            musicaInven.start();
            if (musicaInven != null){
                musicaInven.setLooping(true);
            }
            // TOCAR E LOOP DE MUSICA

    }
    private void salvarBanco(){
        try {
            saveInen = getSharedPreferences("inventario", MODE_PRIVATE);
            comprarAnim2 = saveInen.getInt("anim2", 0);
            comprarAnim4 = saveInen.getInt("anim4", 0);
            comprarAnim5 = saveInen.getInt("anim5",0);
            update = saveInen.getInt("update", 1);
            comprarBtn2 = saveInen.getInt("btn2",0);
            comprarBtn3 = saveInen.getInt("btn3",0);
            comprarBtn4 = saveInen.getInt("btn4",0);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    // CARREGA DADOS

    // EVENTOS DE CLICK
    public void onClickVoltar(View view){
        // CHAMANDO
        Intent intent = new Intent(Inventario.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.in_rigth, R.anim.fade_out);
        musicaInven.stop();
        finish();
    }
    public void onClickAnim1(View view){
        //comprarBtn2 = 0;
        //comprarBtn3 = 0;
        //comprarBtn4 = 0;
        //comprarAnim2 = 0;
        //comprarAnim4 = 0;
        //comprarAnim5 = 0;
        scrow = (ScrollView)findViewById(R.id.scrollInven);
        cod_main = PreferenceManager.getDefaultSharedPreferences(this);
        recebePer = cod_main.getInt("personagem", 1);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        try {
            builder.setTitle("Leonard");
            if (recebeL == 0){
                builder.setMessage("Name: Leonard\nSkin: Normal\nDamage: 1.500\nLife: 5.000\nPowers: Shield / Fire Punch / Ice Punch\n" +
                        "Xp: null\nCoin: null");
            }else if (recebeL == 1){
                builder.setMessage("Nome: Leonard\nSkin: Normal\nDano: 1.500\nVida: 5.000\nPoderes: Escudo / Soco de Fogo / Soco de Gelo\n" +
                        "Xp: sem\nCoin: sem");
            }

            if (recebePer == 1 ){
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        inven.hide();

                    }
                });
            }else{
                if (recebeL == 0){
                    builder.setPositiveButton("Equip", new DialogInterface.OnClickListener() {
                        @SuppressLint("ApplySharedPref")
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            recebePer = 1;
                            update = 1;
                            cod_main.edit().putInt("personagem", recebePer).commit();
                            carregarGifs();
                            inven.hide();

                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            inven.hide();
                        }
                    });
                }else if (recebeL == 1){
                    builder.setPositiveButton("Equipar", new DialogInterface.OnClickListener() {
                        @SuppressLint("ApplySharedPref")
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            recebePer = 1;
                            update = 1;
                            cod_main.edit().putInt("personagem", recebePer = 1).commit();
                            carregarGifs();
                            inven.hide();

                        }
                    });
                    builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            inven.hide();
                        }
                    });
                }

            }
            inven = builder.create();
            inven.show();
            scrow.fullScroll(ScrollView.FOCUS_UP);

        }catch (Exception e) {
            e.printStackTrace();
        }



    }
    public void onClickAnim2(View view){
        scrow = (ScrollView)findViewById(R.id.scrollInven);
        cod_main = PreferenceManager.getDefaultSharedPreferences(this);
        recebeValor = cod_main.getInt("coin",0);
        recebePer = cod_main.getInt("personagem", 1);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        try {
            if (recebeL == 0){
                builder.setMessage("Name: Leonard\nSkin: Druid\nDamage: 3.000\nLife: 8.000\nPowers: Shield / Heavenly Tree / Furious Sea\n" +
                        "Xp: x0.5\nCoin: x0.5");
            }else if (recebeL == 1){
                builder.setMessage("Nome: Leonard\nSkin: Druida\nDano: 3.000\nVida: 8.000\nPoderes: Escudo / Árvore Celestial / Mar Furioso\n" +
                        "Xp: x0.5\nCoin: x0.5");
            }

            if (comprarAnim2 == 0){
                if(recebeL == 0){
                    builder.setPositiveButton("Purchase: 50.000", new DialogInterface.OnClickListener() {
                        @SuppressLint("ApplySharedPref")
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if ( recebeValor >= 50000){
                                recebeValor = recebeValor - 50000;
                                comprarAnim2 = 1;
                                cod_main.edit().putInt("coin", recebeValor).commit();
                                inven.hide();
                            }


                        }
                    });

                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            inven.hide();
                        }
                    });
                }else if (recebeL == 1){
                    builder.setPositiveButton("Comprar: 50.000", new DialogInterface.OnClickListener() {
                        @SuppressLint("ApplySharedPref")
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if ( recebeValor >= 50000){
                                recebeValor = recebeValor - 50000;
                                comprarAnim2 = 1;
                                cod_main.edit().putInt("coin", recebeValor).commit();
                                inven.hide();
                            }


                        }
                    });
                    builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            inven.hide();
                        }
                    });
                }

            }

            if (comprarAnim2 == 1) {
                if (recebePer == 2) {
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            inven.hide();
                        }
                    });

                }else {
                    if (recebeL == 0) {
                        builder.setPositiveButton("Equip", new DialogInterface.OnClickListener() {
                            @SuppressLint("ApplySharedPref")
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                recebePer = 2;
                                update = 2;
                                cod_main.edit().putInt("personagem", recebePer).commit();
                                carregarGifs();
                                inven.hide();
                            }
                        });

                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                inven.hide();
                            }
                        });
                    } else if (recebeL == 1) {
                        builder.setPositiveButton("Equipar", new DialogInterface.OnClickListener() {
                            @SuppressLint("ApplySharedPref")
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                recebePer = 2;
                                update = 2;
                                cod_main.edit().putInt("personagem", recebePer).commit();
                                carregarGifs();
                                inven.hide();

                            }
                        });
                        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                inven.hide();
                            }
                        });
                    }
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }



        inven = builder.create();
        inven.show();
        scrow.fullScroll(ScrollView.FOCUS_UP);

    }
    public void onClickAnim3(View view){
        scrow = (ScrollView)findViewById(R.id.scrollInven);
        cod_main = PreferenceManager.getDefaultSharedPreferences(this);
        recebePerEvent1 = cod_main.getInt("event1", 0);
        recebePer = cod_main.getInt("personagem", 1);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        try {
            builder.setTitle("Leonard");
            if (recebeL == 0){
                builder.setMessage("Name: Leonard\nSkin: Demon God\nDamage: 12.000\nLife: 25.000\nPowers: Shield / Meteor / Light of Darkness\n" +
                        "Xp: x2\nCoin: x2");
            }else if (recebeL == 1){
                builder.setMessage("Nome: Leonard\nSkin: Demon God\nDano: 12.000\nVida: 25.000\nPoderes: Escudo / Meteoro / Luz da Escuridão\n" +
                        "Xp: x2\nCoin: x2");
            }

            if (eventoPerso1 == 0){
                builder.setPositiveButton("Event", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        inven.show();
                    }
                });

            }else{
                if (recebePer == 3 | update == 3){
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            inven.hide();
                            carregarGifs();

                        }
                    });
                }else{
                    if (recebeL == 0){
                        builder.setPositiveButton("Equip", new DialogInterface.OnClickListener() {
                            @SuppressLint("ApplySharedPref")
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                recebePer = 3;
                                update = 3;
                                cod_main.edit().putInt("personagem", recebePer).commit();
                                carregarGifs();
                                inven.hide();

                            }
                        });
                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                inven.hide();
                            }
                        });
                    }else if (recebeL == 1){
                        builder.setPositiveButton("Equipar", new DialogInterface.OnClickListener() {
                            @SuppressLint("ApplySharedPref")
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                recebePer = 3;
                                update = 3;
                                cod_main.edit().putInt("personagem", recebePer).commit();
                                carregarGifs();
                                inven.hide();

                            }
                        });
                        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                inven.hide();
                            }
                        });
                    }

                }


            }



        }catch (Exception e) {
            e.printStackTrace();
        }
        carregarGifs();
        inven = builder.create();
        inven.show();
        scrow.fullScroll(ScrollView.FOCUS_UP);

    }
    public void onClickAnim4(View view){
        scrow = (ScrollView)findViewById(R.id.scrollInven);
        cod_main = PreferenceManager.getDefaultSharedPreferences(this);
        recebeValor = cod_main.getInt("coin",0);
        recebePer = cod_main.getInt("personagem", 1);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        try {
            if (recebeL == 0){
                builder.setMessage("Name: Barthur\nSkin: Normal\nDamage: 5.000\nLife: 12.000\nPowers: Shield / King's Sword/ Sun Crown\n" +
                        "Xp: x0.8\nCoin: x0.8");
            }else if (recebeL == 1){
                builder.setMessage("Nome: Barthur\nSkin: Normal\nDano: 5.000\nVida: 12.000\nPoderes: Escudo / Espada Do Rei / Coroa Do Sol\n" +
                        "Xp: x0.8\nCoin: x0.8");
            }

            if (comprarAnim4 == 0){
                if(recebeL == 0){
                    builder.setPositiveButton("Purchase: 120.000", new DialogInterface.OnClickListener() {
                        @SuppressLint("ApplySharedPref")
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if ( recebeValor >= 120000){
                                recebeValor = recebeValor - 120000;
                                comprarAnim4 = 1;
                                cod_main.edit().putInt("coin", recebeValor).commit();
                                inven.hide();
                            }

                        }
                    });
                    builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            inven.hide();
                        }
                    });
                }else if (recebeL == 1){
                    builder.setPositiveButton("Comprar: 120.000", new DialogInterface.OnClickListener() {
                        @SuppressLint("ApplySharedPref")
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if ( recebeValor >= 120000){
                                recebeValor = recebeValor - 120000;
                                comprarAnim4 = 1;
                                cod_main.edit().putInt("coin", recebeValor).commit();
                                inven.hide();
                            }


                        }
                    });
                    builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            inven.hide();
                        }
                    });
                }



            }

            if (comprarAnim4 == 1) {
                if (recebePer == 4) {
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            inven.hide();
                        }
                    });

                }else {
                    if (recebeL == 0) {
                        builder.setPositiveButton("Equip", new DialogInterface.OnClickListener() {
                            @SuppressLint("ApplySharedPref")
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                recebePer = 4;
                                update = 4;
                                cod_main.edit().putInt("personagem", recebePer).commit();
                                carregarGifs();
                                inven.hide();

                            }
                        });
                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                inven.hide();
                            }
                        });

                    } else if (recebeL == 1) {
                        builder.setPositiveButton("Equipar", new DialogInterface.OnClickListener() {
                            @SuppressLint("ApplySharedPref")
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                recebePer = 4;
                                update = 4;
                                cod_main.edit().putInt("personagem", recebePer).commit();
                                carregarGifs();
                                inven.hide();
                            }
                        });
                        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                inven.hide();
                            }
                        });
                    }
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }



        inven = builder.create();
        inven.show();
        scrow.fullScroll(ScrollView.FOCUS_UP);

    }
    public void onClickAnim5(View view){
        scrow = (ScrollView)findViewById(R.id.scrollInven);
        cod_main = PreferenceManager.getDefaultSharedPreferences(this);
        recebeValor = cod_main.getInt("coin",0);
        recebePer = cod_main.getInt("personagem", 1);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        try {
            if (recebeL == 0){
                builder.setMessage("Name: Barthur\nSkin: Toxic King\nDamage: 7.500\nLife: 16.000\nPowers: Shield / Toxic Cloud/ Atomic Bomb\n" +
                        "Xp: x1.0\nCoin: x1.0");
            }else if (recebeL == 1){
                builder.setMessage("Nome: Barthur\nSkin: Rei Tóxico\nDano: 7.500\nVida: 16.000\nPoderes: Escudo / Nuvem Tóxica / Bomba Atômica\n" +
                        "Xp: x1.0\nCoin: x1.0");
            }

            if (comprarAnim5 == 0){
                if(recebeL == 0){
                    builder.setPositiveButton("Purchase: 175.000", new DialogInterface.OnClickListener() {
                        @SuppressLint("ApplySharedPref")
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if ( recebeValor >= 175000){
                                recebeValor = recebeValor - 175000;
                                comprarAnim5 = 1;
                                cod_main.edit().putInt("coin", recebeValor).commit();
                                inven.hide();
                            }


                        }
                    });
                    builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            inven.hide();
                        }
                    });
                }else if (recebeL == 1){
                    builder.setPositiveButton("Comprar: 175.000", new DialogInterface.OnClickListener() {
                        @SuppressLint("ApplySharedPref")
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if ( recebeValor >= 175000){
                                recebeValor = recebeValor - 175000;
                                comprarAnim5 = 1;
                                cod_main.edit().putInt("coin", recebeValor).commit();
                                inven.hide();
                            }


                        }
                    });
                    builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            inven.hide();
                        }
                    });
                }


            }

            if (comprarAnim5 == 1) {
                if (recebePer == 5) {
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {


                        }
                    });

                }else {
                    if (recebeL == 0) {
                        builder.setPositiveButton("Equip", new DialogInterface.OnClickListener() {
                            @SuppressLint("ApplySharedPref")
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                recebePer = 5;
                                update = 5;
                                cod_main.edit().putInt("personagem", recebePer).commit();
                                carregarGifs();

                            }
                        });
                    } else if (recebeL == 1) {
                        builder.setPositiveButton("Equipar", new DialogInterface.OnClickListener() {
                            @SuppressLint("ApplySharedPref")
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                recebePer = 5;
                                update = 5;
                                cod_main.edit().putInt("personagem", recebePer).commit();
                                carregarGifs();

                            }
                        });
                    }
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }



        inven = builder.create();
        inven.show();
        scrow.fullScroll(ScrollView.FOCUS_UP);

    }
    public void onClickAnim6(View view){
        scrow = (ScrollView)findViewById(R.id.scrollInven);
        cod_main = PreferenceManager.getDefaultSharedPreferences(this);
        recebePer = cod_main.getInt("personagem", 1);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        try {
            builder.setTitle("Barthur");
            if (recebeL == 0){
                builder.setMessage("Name: Barthur\nSkin: Robot\nDamage: 15.000\nLife: 35.000\nPowers: Shield / Bright Shots / Laser\n" +
                        "Xp: x2\nCoin: x2");
            }else if (recebeL == 1){
                builder.setMessage("Nome: Barthur\nSkin: Robô\nDano: 15.000\nVida: 35.000\nPoderes: Escudo / Disparos Brilhantes / Laser\n" +
                        "Xp: x2\nCoin: x2");
            }

            if (eventoPerso2 == 0){
                builder.setPositiveButton("Event", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        inven.show();
                    }
                });

            }else{
                if (eventoPerso2 == 1 | update == 6){
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            inven.hide();

                        }
                    });
                }else{
                    if (recebeL == 0){
                        builder.setPositiveButton("Equip", new DialogInterface.OnClickListener() {
                            @SuppressLint("ApplySharedPref")
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                recebePer = 6;
                                update = 6;
                                cod_main.edit().putInt("personagem", recebePer).commit();
                                carregarGifs();
                                inven.hide();

                            }
                        });
                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                inven.hide();
                            }
                        });
                    }else if (recebeL == 1){
                        builder.setPositiveButton("Equipar", new DialogInterface.OnClickListener() {
                            @SuppressLint("ApplySharedPref")
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                recebePer = 6;
                                update = 6;
                                cod_main.edit().putInt("personagem", recebePer).commit();
                                carregarGifs();
                                inven.hide();

                            }
                        });
                        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                inven.hide();
                            }
                        });
                    }

                }


            }



        }catch (Exception e) {
            e.printStackTrace();
        }
        inven = builder.create();
        inven.show();
        scrow.fullScroll(ScrollView.FOCUS_UP);

    }

    public void onClickBtn1(View view){

        scrow = (ScrollView)findViewById(R.id.scrollInven);
        cod_main = PreferenceManager.getDefaultSharedPreferences(this);
        recebeValor = cod_main.getInt("coin",0);
        recebeButton = cod_main.getInt("button", 1);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        try {
            builder.setTitle("Leonard");
            if (recebeL == 0){
                builder.setTitle("Button");
            }else if (recebeL == 1){
                builder.setMessage("Button");
            }

            if (recebePer == 1 ){
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        inven.hide();

                    }
                });
            }else{
                if (recebeL == 0){
                    builder.setPositiveButton("Equip", new DialogInterface.OnClickListener() {
                        @SuppressLint("ApplySharedPref")
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            recebeButton = 1;
                            cod_main.edit().putInt("button", recebeButton).commit();
                            inven.hide();

                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            inven.hide();
                        }
                    });
                }else if (recebeL == 1){
                    builder.setPositiveButton("Equipar", new DialogInterface.OnClickListener() {
                        @SuppressLint("ApplySharedPref")
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            recebeButton = 1;
                            cod_main.edit().putInt("button", recebeButton).commit();
                            inven.hide();

                        }
                    });
                    builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            inven.hide();
                        }
                    });
                }

            }
            inven = builder.create();
            inven.show();
            scrow.fullScroll(ScrollView.FOCUS_UP);

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void onClickBtn2(View view){
        scrow = (ScrollView)findViewById(R.id.scrollInven);
        cod_main = PreferenceManager.getDefaultSharedPreferences(this);
        recebeValor = cod_main.getInt("coin",0);
        recebeButton = cod_main.getInt("button", 1);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        try {
            if (recebeL == 0){
                builder.setTitle("Button - 2");
            }else if (recebeL == 1){
                builder.setTitle("Button - 2");
            }

            if (comprarBtn2 == 0){
                if(recebeL == 0){
                    builder.setPositiveButton("Purchase: 80.000", new DialogInterface.OnClickListener() {
                        @SuppressLint("ApplySharedPref")
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if ( recebeValor >= 80000){
                                recebeValor = recebeValor - 80000;
                                comprarBtn2 = 1;
                                cod_main.edit().putInt("coin", recebeValor).commit();
                                inven.hide();
                            }


                        }
                    });

                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            inven.hide();
                        }
                    });
                }else if (recebeL == 1){
                    builder.setPositiveButton("Comprar: 80.000", new DialogInterface.OnClickListener() {
                        @SuppressLint("ApplySharedPref")
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if ( recebeValor >= 80000){
                                recebeValor = recebeValor - 80000;
                                comprarBtn2 = 1;
                                cod_main.edit().putInt("coin", recebeValor).commit();
                                inven.hide();
                            }


                        }
                    });
                    builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            inven.hide();
                        }
                    });
                }

            }

            if (comprarBtn2 == 1) {
                if (recebeButton == 2) {
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            inven.hide();
                        }
                    });

                }else {
                    if (recebeL == 0) {
                        builder.setPositiveButton("Equip", new DialogInterface.OnClickListener() {
                            @SuppressLint("ApplySharedPref")
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                recebeButton = 2;
                                cod_main.edit().putInt("button", recebeButton).commit();
                                inven.hide();
                            }
                        });

                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                inven.hide();
                            }
                        });
                    } else if (recebeL == 1) {
                        builder.setPositiveButton("Equipar", new DialogInterface.OnClickListener() {
                            @SuppressLint("ApplySharedPref")
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                recebeButton = 2;
                                cod_main.edit().putInt("button", recebeButton).commit();
                                inven.hide();

                            }
                        });
                        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                inven.hide();
                            }
                        });
                    }
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }



        inven = builder.create();
        inven.show();
        scrow.fullScroll(ScrollView.FOCUS_UP);
    }
    public void onClickBtn3(View view){
        scrow = (ScrollView)findViewById(R.id.scrollInven);
        cod_main = PreferenceManager.getDefaultSharedPreferences(this);
        recebeValor = cod_main.getInt("coin",0);
        recebeButton = cod_main.getInt("button", 1);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        try {
            if (recebeL == 0){
                builder.setTitle("Button - 3");
            }else if (recebeL == 1){
                builder.setTitle("Button - 3");
            }

            if (comprarBtn3 == 0){
                if(recebeL == 0){
                    builder.setPositiveButton("Purchase: 135.000", new DialogInterface.OnClickListener() {
                        @SuppressLint("ApplySharedPref")
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if ( recebeValor >= 135000){
                                recebeValor = recebeValor - 135000;
                                comprarBtn3 = 1;
                                cod_main.edit().putInt("coin", recebeValor).commit();
                                inven.hide();
                            }


                        }
                    });

                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            inven.hide();
                        }
                    });
                }else if (recebeL == 1){
                    builder.setPositiveButton("Comprar: 135.000", new DialogInterface.OnClickListener() {
                        @SuppressLint("ApplySharedPref")
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if ( recebeValor >= 135000){
                                recebeValor = recebeValor - 135000;
                                comprarBtn3 = 1;
                                cod_main.edit().putInt("coin", recebeValor).commit();
                                inven.hide();
                            }


                        }
                    });
                    builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            inven.hide();
                        }
                    });
                }

            }

            if (comprarBtn3 == 1) {
                if (recebeButton == 3) {
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            inven.hide();
                        }
                    });

                }else {
                    if (recebeL == 0) {
                        builder.setPositiveButton("Equip", new DialogInterface.OnClickListener() {
                            @SuppressLint("ApplySharedPref")
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                recebeButton = 3;
                                cod_main.edit().putInt("button", recebeButton).commit();
                                inven.hide();
                            }
                        });

                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                inven.hide();
                            }
                        });
                    } else if (recebeL == 1) {
                        builder.setPositiveButton("Equipar", new DialogInterface.OnClickListener() {
                            @SuppressLint("ApplySharedPref")
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                recebeButton = 3;
                                cod_main.edit().putInt("button", recebeButton).commit();
                                inven.hide();

                            }
                        });
                        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                inven.hide();
                            }
                        });
                    }
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }



        inven = builder.create();
        inven.show();
        scrow.fullScroll(ScrollView.FOCUS_UP);

    }
    public void onClickBtn4(View view){
        scrow = (ScrollView)findViewById(R.id.scrollInven);
        cod_main = PreferenceManager.getDefaultSharedPreferences(this);
        recebeValor = cod_main.getInt("coin",0);
        recebeButton = cod_main.getInt("button", 1);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        try {
            if (recebeL == 0){
                builder.setTitle("Button - 3");
            }else if (recebeL == 1){
                builder.setTitle("Button - 3");
            }

            if (comprarBtn4 == 0){
                if(recebeL == 0){
                    builder.setPositiveButton("Purchase: 150.000", new DialogInterface.OnClickListener() {
                        @SuppressLint("ApplySharedPref")
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if ( recebeValor >= 150000){
                                recebeValor = recebeValor - 150000;
                                comprarBtn4 = 1;
                                cod_main.edit().putInt("coin", recebeValor).commit();
                                inven.hide();
                            }


                        }
                    });

                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            inven.hide();
                        }
                    });
                }else if (recebeL == 1){
                    builder.setPositiveButton("Comprar: 150.000", new DialogInterface.OnClickListener() {
                        @SuppressLint("ApplySharedPref")
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if ( recebeValor >= 150000){
                                recebeValor = recebeValor - 150000;
                                comprarBtn4 = 1;
                                cod_main.edit().putInt("coin", recebeValor).commit();
                                inven.hide();
                            }


                        }
                    });
                    builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            inven.hide();
                        }
                    });
                }

            }

            if (comprarBtn4 == 1) {
                if (recebeButton == 4) {
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            inven.hide();
                        }
                    });

                }else {
                    if (recebeL == 0) {
                        builder.setPositiveButton("Equip", new DialogInterface.OnClickListener() {
                            @SuppressLint("ApplySharedPref")
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                recebeButton = 4;
                                cod_main.edit().putInt("button", recebeButton).commit();
                                inven.hide();
                            }
                        });

                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                inven.hide();
                            }
                        });
                    } else if (recebeL == 1) {
                        builder.setPositiveButton("Equipar", new DialogInterface.OnClickListener() {
                            @SuppressLint("ApplySharedPref")
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                recebeButton = 4;
                                cod_main.edit().putInt("button", recebeButton).commit();
                                inven.hide();

                            }
                        });
                        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                inven.hide();
                            }
                        });
                    }
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }



        inven = builder.create();
        inven.show();
        scrow.fullScroll(ScrollView.FOCUS_UP);

    }
    // EVENTOS DE CLICK

    @SuppressLint("ApplySharedPref")
    @Override
    protected void onPause() {
        super.onPause();
        try {
            editorIven = saveInen.edit();
            editorIven.putInt("anim2", comprarAnim2);
            editorIven.commit();
            editorIven.putInt("anim4", comprarAnim4);
            editorIven.commit();
            editorIven.putInt("anim5", comprarAnim5);
            editorIven.commit();
            editorIven.putInt("btn2", comprarBtn2);
            editorIven.commit();
            editorIven.putInt("btn3", comprarBtn3);
            editorIven.commit();
            editorIven.putInt("btn4", comprarBtn4);
            editorIven.commit();
            editorIven.putInt("update", update);
            editorIven.commit();
        }catch (Exception e) {
            e.printStackTrace();
        }
        musicaInven.stop();
    }

    @SuppressLint("ApplySharedPref")
    @Override
    protected void onStop() {
        super.onStop();
        try {
            editorIven = saveInen.edit();
            editorIven.putInt("anim2", comprarAnim2);
            editorIven.commit();
            editorIven.putInt("anim4", comprarAnim4);
            editorIven.commit();
            editorIven.putInt("anim5", comprarAnim5);
            editorIven.commit();
            editorIven.putInt("btn2", comprarBtn2);
            editorIven.commit();
            editorIven.putInt("btn3", comprarBtn3);
            editorIven.commit();
            editorIven.putInt("btn4", comprarBtn4);
            editorIven.commit();
            editorIven.putInt("update", update);
            editorIven.commit();
        }catch (Exception e) {
            e.printStackTrace();
        }
        musicaInven.stop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @SuppressLint("ApplySharedPref")
    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            editorIven = saveInen.edit();
            editorIven.putInt("anim2", comprarAnim2);
            editorIven.commit();
            editorIven.putInt("anim4", comprarAnim4);
            editorIven.commit();
            editorIven.putInt("anim5", comprarAnim5);
            editorIven.commit();
            editorIven.putInt("btn2", comprarBtn2);
            editorIven.commit();
            editorIven.putInt("btn3", comprarBtn3);
            editorIven.commit();
            editorIven.putInt("btn4", comprarBtn4);
            editorIven.commit();
            editorIven.putInt("update", update);
            editorIven.commit();
        }catch (Exception e) {
            e.printStackTrace();
        }
        musicaInven.stop();
        finishAffinity();
    }
}