package com.example.android.mediaplayerapp;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private SeekBar seekbar;

    private double startTime;
    private double finalTime;

    private Handler myHandler = new Handler();

    private Button rewind;
    private Button play;
    private Button pause;
    private Button forward;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(this, R.raw.song);
        seekbar = (SeekBar)findViewById(R.id.seekBar);

        startTime = mediaPlayer.getCurrentPosition();
        finalTime = mediaPlayer.getDuration();

        seekbar.setClickable(false);
        rewind = (Button) findViewById(R.id.rewind);
        play = (Button) findViewById(R.id.play);
        pause = (Button) findViewById(R.id.pause);
        forward = (Button) findViewById(R.id.forward);

//        rewind.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mediaPlayer.start();
//            }
//        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mediaPlayer.start();
                seekbar.setProgress((int)startTime);
                myHandler.postDelayed(UpdateSongTime,100);
                Toast.makeText(MainActivity.this,"Play",Toast.LENGTH_SHORT).show();
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.pause();
                rewind.setEnabled(false);
                forward.setEnabled(false);
                Toast.makeText(MainActivity.this,"Pause",Toast.LENGTH_SHORT).show();
            }
        });
//
//        forward.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                  int currentPosition = mediaPlayer.getCurrentPosition();
//                  mediaPlayer.
//            }
//        });

    }

    private Runnable UpdateSongTime = new Runnable() {
        public void run() {
            startTime = mediaPlayer.getCurrentPosition();
            seekbar.setProgress((int)startTime);
            myHandler.postDelayed(this, 100);
        }
    };
}
