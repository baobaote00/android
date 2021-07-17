package tdc.edu.vn.multimedia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import tdc.edu.vn.multimedia.service.MusicPlayService;
import tdc.edu.vn.multimedia.widgets.Player;
import tdc.edu.vn.multimedia.widgets.PlayerDelegation;

public class MainActivity extends AppCompatActivity implements PlayerDelegation {
    //List of music to play
//    private ArrayList<String> listMusic;
    private final int REQ_CODE = 1;
//    private int position = 0
//    private MediaPlayer mediaPlayer;
//    private boolean isPlaying = false;
    private MusicPlayService service = null;
    private TextView textView;

    //3.1 Define Service Connection
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            service = ((MusicPlayService.MusicServiceBinder)iBinder).getMusicService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Toast.makeText(getApplicationContext(), "Service is disconnected!", Toast.LENGTH_LONG).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Check Permission
        if (!checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQ_CODE);
            }
        } else {

//            mediaPlayer = new MediaPlayer();
            //get all music path from SDCARD
            initialization();
        }
    }

    //Check permission
    private boolean checkPermission(String permission) {
        int check = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            check = checkSelfPermission(permission);
        }
        return (check == PackageManager.PERMISSION_GRANTED);
    }

    // Processing the user permission
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQ_CODE) {
            if (permissions.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //get all music path from SDCARD
                initialization();
            }
        }
    }
    // Define the initialization method
    private void initialization(){
        //get all music path from SDCARD
//        listMusic = getMusicPath();
//        Log.d("listmusic", listMusic.toString());
//
        Player player = findViewById(R.id.player);
        player.setDelegate(this);
        //Make connecting to the music
        Intent intent = new Intent(MainActivity.this, MusicPlayService.class);
        bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE);
    }

    // Make disconnecting the service

    @Override
    protected void onDestroy() {
        unbindService(serviceConnection);
        super.onDestroy();
    }

//    //Get music path from SDCARD to the list of music
//    private ArrayList<String> getMusicPath() {
//        ArrayList<String> list = new ArrayList<String>();
//        //Read music path from the Music Directory
//        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).getPath());
//        Log.d("test", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).getPath());
//        Log.d("length", file.listFiles().length + "");
//
//        if (file.listFiles() != null) {
//            for (File theFile : file.listFiles()) {
//                list.add(theFile.getAbsolutePath());
//            }
//        } else {
//            Toast.makeText(MainActivity.this, "Can not read any music file", Toast.LENGTH_LONG).show();
//        }
//        return list;
//    }

    @Override
    public void play() {
        // Request the service to play the music song
        if (service != null){
            textView=findViewById(R.id.textview);
            textView.setText(service.playMusic());
        } else {
            Toast.makeText(this, "can not get the music service!", Toast.LENGTH_LONG).show();
        }
//
    }

    @Override
    public void stop() {
        // Request the service to play the music song
        if (service != null){
            service.stopMusic();
        } else {
            Toast.makeText(this, "can not get the music service!", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void pause() {
        // Request the service to play the music song
        if (service != null){
            service.pauseMusic();
        } else {
            Toast.makeText(this, "can not get the music service!", Toast.LENGTH_LONG).show();
        }
//
    }

    @Override
    public void next() {
        // Request the service to play the music song
        if (service != null){
            service.nextMusic();
        } else {
            Toast.makeText(this, "can not get the music service!", Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public void prev() {
        service.prevMusic();
        // Request the service to play the music song
        if (service != null){
            service.prevMusic();
        } else {
            Toast.makeText(this, "can not get the music service!", Toast.LENGTH_LONG).show();
        }
//        if (isPlaying) {
//            mediaPlayer.stop();
//            isPlaying = false;
//        }
//
//        if (position == 0) {
//            position = listMusic.size() - 1;
//        } else {
//            position--;
//        }
    }

    @Override
    protected void onStop() {
//        stop();;
//        mediaPlayer.release();
        super.onStop();
    }


}
