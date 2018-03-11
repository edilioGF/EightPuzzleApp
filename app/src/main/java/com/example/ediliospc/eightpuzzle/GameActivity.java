package com.example.ediliospc.eightpuzzle;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

import static java.lang.String.valueOf;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int COLUMNS = 3;
    private static final int DIMENSIONS = COLUMNS * COLUMNS;

    private static String[][] Win = {{"1","2","3"},{"4","5","6"},{"7","8","0"}};
    private static String[] Lista;
    private static String[][] Juego;
    private static int[] ids;
    private static int movs;
    private static Chronometer timer;
    private static long lastPause;
    private static boolean won = false;
    private static boolean sonido = true;
    private static MediaPlayer mp1;
    private static MediaPlayer mp2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ImageButton cero = (ImageButton)findViewById(R.id.button0);
        cero.setOnClickListener(this);
        ImageButton uno = (ImageButton)findViewById(R.id.button1);
        uno.setOnClickListener(this);
        ImageButton dos = (ImageButton)findViewById(R.id.button2);
        dos.setOnClickListener(this);
        ImageButton tres = (ImageButton)findViewById(R.id.button3);
        tres.setOnClickListener(this);
        ImageButton cuatro = (ImageButton)findViewById(R.id.button4);
        cuatro.setOnClickListener(this);
        ImageButton cinco = (ImageButton)findViewById(R.id.button5);
        cinco.setOnClickListener(this);
        ImageButton seis = (ImageButton)findViewById(R.id.button6);
        seis.setOnClickListener(this);
        ImageButton siete = (ImageButton)findViewById(R.id.button7);
        siete.setOnClickListener(this);
        ImageButton ocho = (ImageButton)findViewById(R.id.button8);
        ocho.setOnClickListener(this);
        Button nuevo = (Button)findViewById(R.id.buttonN);
        nuevo.setOnClickListener(this);
        Button pause = (Button)findViewById(R.id.pause);
        pause.setOnClickListener(this);
        Button sound = (Button)findViewById(R.id.sound);
        sound.setOnClickListener(this);
        mp1 = MediaPlayer.create(this,R.raw.mover);
        mp2 = MediaPlayer.create(this,R.raw.baraje);
        timer = (Chronometer)findViewById(R.id.mTimer);
        init();
        scramble();
    }

    private void StartTimer()
    {
        if(lastPause != 0){
            timer.setBase(timer.getBase() + SystemClock.elapsedRealtime() - lastPause);
        }
        else
        {
            timer.setBase(SystemClock.elapsedRealtime());
        }
        timer.start();
    }

    private void PauseTimer()
    {
        lastPause = SystemClock.elapsedRealtime();
        timer.stop();
    }

    private void ResetTimer()
    {
        timer.setBase(SystemClock.elapsedRealtime());
        lastPause = 0;
    }

    private void init() {
        ResetTimer();
        StartTimer();
        Lista = new String[DIMENSIONS];
        ids  = new int[DIMENSIONS];
        for (int i = 0; i < Lista.length; i++) {
            Lista[i] = valueOf(i);

            if (i == 0) {
                ids[i] = R.id.button0;
            } else if (i == 1) {
                ids[i] = R.id.button1;
            } else if (i == 2) {
                ids[i] = R.id.button2;
            } else if (i == 3) {
                ids[i] = R.id.button3;
            } else if (i == 4) {
                ids[i] = R.id.button4;
            } else if (i == 5) {
                ids[i] = R.id.button5;
            } else if (i == 6) {
                ids[i] = R.id.button6;
            } else if (i == 7) {
                ids[i] = R.id.button7;
            } else if (i == 8) {
                ids[i] = R.id.button8;
            }

        }
    }

    private void scramble() {
        movs=0;
        int index;
        int k = 0;
        String temp;
        Random random = new Random();
        Juego = new String[COLUMNS][COLUMNS];

        for (int i = Lista.length - 1; i > 0; i--) {
            index = random.nextInt(i + 1);
            temp = Lista[index];
            Lista[index] = Lista[i];
            Lista[i] = temp;
        }

        for (int x = 0; x < COLUMNS; x++) {
            for (int y = 0; y < COLUMNS; y++, k++) {
                Juego[x][y] = Lista[k];
            }
        }

         display();
    }

    private void display() {
        int k = 0;
        for (int i = 0; i < COLUMNS; i++) {
            for (int j = 0; j < COLUMNS; j++, k++) {

                if (Juego[i][j].equals("0")){
                    findViewById(ids[k]).setBackgroundResource(R.drawable.cuadro);
                } else if (Juego[i][j].equals("1")) {
                    findViewById(ids[k]).setBackgroundResource(R.drawable.cuadro1);
                } else if (Juego[i][j].equals("2")) {
                    findViewById(ids[k]).setBackgroundResource(R.drawable.cuadro2);
                } else if (Juego[i][j].equals("3")) {
                    findViewById(ids[k]).setBackgroundResource(R.drawable.cuadro3);
                } else if (Juego[i][j].equals("4")) {
                    findViewById(ids[k]).setBackgroundResource(R.drawable.cuadro4);
                } else if (Juego[i][j].equals("5")) {
                    findViewById(ids[k]).setBackgroundResource(R.drawable.cuadro5);
                } else if (Juego[i][j].equals("6")) {
                    findViewById(ids[k]).setBackgroundResource(R.drawable.cuadro6);
                }else if (Juego[i][j].equals("7")) {
                    findViewById(ids[k]).setBackgroundResource(R.drawable.cuadro7);
                } else if (Juego[i][j].equals("8")) {
                    findViewById(ids[k]).setBackgroundResource(R.drawable.cuadro8);
                }
            }
        }
        TextView txv = findViewById(R.id.myTextView);
        txv.setText(String.valueOf(movs));

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.button0:
                if(!won)
                check(Juego[0][0]);
                break;

            case R.id.button1:
                if(!won)
                check(Juego[0][1]);
                break;

            case R.id.button2:
                if(!won)
                check(Juego[0][2]);
                break;

            case R.id.button3:
                if(!won)
                check(Juego[1][0]);
                break;

            case R.id.button4:
                if(!won)
                check(Juego[1][1]);
                break;

            case R.id.button5:
                if(!won)
                check(Juego[1][2]);
                break;

            case R.id.button6:
                if(!won)
                check(Juego[2][0]);
                break;

            case R.id.button7:
                if(!won)
                check(Juego[2][1]);
                break;

            case R.id.button8:
                if(!won)
                check(Juego[2][2]);
                break;

            case R.id.buttonN:
                if(sonido)
                   mp2.start();
                scramble();
                ResetTimer();
                StartTimer();
                won = false;
                break;

            case R.id.pause:
                if(!won) {
                    PauseTimer();
                    AlertDialog alertDialog = new AlertDialog.Builder(this)
                            .setTitle("8 PUZZLE")
                            .setMessage("PAUSA")
                            .setCancelable(false)
                            .setNeutralButton("REANUDAR", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    StartTimer();
                                }
                            })
                            .create();
                    alertDialog.show();
                }
            break;

            case R.id.sound:
                if(sonido==true) {
                    sonido = false;
                    findViewById(R.id.sound).setBackgroundResource(R.drawable.nsound);
                }else {
                    sonido = true;
                    findViewById(R.id.sound).setBackgroundResource(R.drawable.sound);
                }
                break;

            default:
                break;
        }

    }

    private void check(String num) {
        int[] pos1 = new int[2];
        int[] pos2 = new int[2];

        getPosition(pos1,num);
        getPosition(pos2,"0");

        if(num=="0"){
            Toast.makeText(this,"Invalid",Toast.LENGTH_SHORT).show();
        } else if(pos1[0] == 0 && pos1[1] == 0) {
            if (pos2[0] == 0 && pos2[1] == 1 || pos2[0] == 1 && pos2[1] == 0)
                swap(pos1, pos2);
        } else if(pos1[0] == 0 && pos1[1] == 1){
            if(pos2[0] == 0 && pos2[1] == 0 || pos2[0] == 1 && pos2[1] == 1 || pos2[0] == 0 && pos2[1] == 2)
                swap(pos1, pos2);
        } else if(pos1[0] == 0 && pos1[1] == 2) {
            if (pos2[0] == 0 && pos2[1] == 1 || pos2[0] == 1 && pos2[1] == 2)
                swap(pos1, pos2);
        } else if(pos1[0] == 1 && pos1[1] == 0) {
            if (pos2[0] == 1 && pos2[1] == 1 || pos2[0] == 0 && pos2[1] == 0 ||pos2[0] == 2 && pos2[1] == 0)
                swap(pos1, pos2);
        } else if(pos1[0] == 1 && pos1[1] == 1) {
            if (pos2[0] == 1 && pos2[1] == 0 || pos2[0] == 1 && pos2[1] == 2 || pos2[0] == 0 && pos2[1] == 1 || pos2[0] == 2 && pos2[1] == 1)
                swap(pos1, pos2);
        } else if(pos1[0] == 1 && pos1[1] == 2) {
            if (pos2[0] == 0 && pos2[1] == 2 || pos2[0] == 1 && pos2[1] == 1 ||pos2[0] == 2 && pos2[1] == 2)
                swap(pos1, pos2);
        } else if(pos1[0] == 2 && pos1[1] == 0) {
            if (pos2[0] == 1 && pos2[1] == 0 || pos2[0] == 2 && pos2[1] == 1)
                swap(pos1, pos2);
        } else if(pos1[0] == 2 && pos1[1] == 1) {
            if (pos2[0] == 2 && pos2[1] == 0 || pos2[0] == 2 && pos2[1] == 2 ||pos2[0] == 1 && pos2[1] == 1)
                swap(pos1, pos2);
        } else if(pos1[0] == 2 && pos1[1] == 2) {
            if (pos2[0] == 2 && pos2[1] == 1 || pos2[0] == 1 && pos2[1] == 2)
                swap(pos1, pos2);
    }

}


    private void swap(int p1[], int p2[]) {
        String temp;
        temp = Juego[p1[0]][p1[1]];
        Juego[p1[0]][p1[1]] = Juego[p2[0]][p2[1]];
        Juego[p2[0]][p2[1]] = temp;
        movs++;
        display();
        if(sonido)
           mp1.start();
        if(Arrays.deepEquals(Juego,Win)) {
            Toast.makeText(this, "HAS GANADO!", Toast.LENGTH_LONG).show();
            PauseTimer();
            won = true;
        }
    }

    private int[] getPosition(int[]pos,String num) {

        for (int x = 0; x < COLUMNS; x++) {
            for (int y = 0; y < COLUMNS; y++) {

                if(Juego[x][y].equals(num)) {
                    pos[0] = x;
                    pos[1] = y;
                }

            }
        }
        return pos;
    }
}
