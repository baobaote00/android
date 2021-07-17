package tdc.edu.vn.multimedia.service;

import android.app.Service;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import tdc.edu.vn.multimedia.MainActivity;

public class MusicPlayService extends Service {
    //properties
    private ArrayList<String> listMusic;
    private int position = 0;
    private MediaPlayer mediaPlayer;
    private boolean isPlaying = false;
    //2.1 Define Binder Object
    private IBinder serviceBinder = new MusicServiceBinder();

    // Default constructor
    public MusicPlayService() {
    }

    //1. Define class of Binder object
    public class MusicServiceBinder extends Binder {
        public MusicPlayService getMusicService() {

            return MusicPlayService.this;
        }
    }

    //Get music path from SDCARD to the list of music
    private ArrayList<String> getMusicPath() {
        ArrayList<String> list = new ArrayList<String>();
        //Read music path from the Music Directory
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).getPath());
        Log.d("test", Environment.getExternalStorageDirectory().getPath());
//        Log.d("length", file.listFiles().length + "");

        if (file.listFiles() != null) {
            for (File theFile : file.listFiles()) {
                list.add(theFile.getAbsolutePath());
            }
        } else {
            Toast.makeText(getApplicationContext(), "Can not read any music file", Toast.LENGTH_LONG).show();
        }
        return list;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        listMusic = getMusicPath();
        mediaPlayer = new MediaPlayer();
        Log.d("listmusic", listMusic.toString());

//        Player player = findViewById(R.id.player);
//        player.setDelegate(this);

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
//        throw new UnsupportedOperationException("Not yet implemented");
        //2.2 Return Binder Object
        return serviceBinder;
    }

    // Define all function of the music service
    public String playMusic() {
        String name = "";
        if (!isPlaying) {
            if (mediaPlayer != null) {
                if (listMusic.size() > 0) {
                    Uri musicUri = Uri.parse(listMusic.get(position));
                    name = listMusic.get(position);
                    //mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mediaPlayer.setAudioAttributes(new AudioAttributes.Builder()
                            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                            .setUsage(AudioAttributes.USAGE_MEDIA)
                            .build()
                    );
                    mediaPlayer.setVolume(1.0f, 1.0f);
                    //Play complete listener
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            nextMusic();
                            playMusic();
                        }
                    });
                    try {
                        mediaPlayer.reset();
                        mediaPlayer.setDataSource(this, musicUri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    try {
                        mediaPlayer.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    mediaPlayer.start();
                    isPlaying = true;
                } else {
                    Toast.makeText(this, "There are not any music file to play!", Toast.LENGTH_LONG).show();
                }
            }
        } else {
            mediaPlayer.start();
        }
        return name;
//            Log.d("music", "Play");
    }

    // Define all function of the music service
    public void pauseMusic() {
        if (isPlaying) {
            mediaPlayer.pause();
        }
//        Log.d("music", "Pause");
    }

    // Define all function of the music service
    public void stopMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            //position = 0;
            isPlaying = false;

        }
//        Log.d("music", "Stop");
    }

    // Define all function of the music service
    public void prevMusic() {
        //Log.d("music", "Prev");
        if (isPlaying) {
            mediaPlayer.stop();
            isPlaying = false;
        }

        if (position == 0) {
            position = listMusic.size() - 1;
        } else {
            position--;
        }
    }

    // Define all function of the music service
    public void nextMusic() {
        if (isPlaying) {
            mediaPlayer.stop();
            isPlaying = false;
        }

        if (position == listMusic.size() - 1) {
            position = 0;
        } else {
            position++;
        }
        //Log.d("music", "Next");
    }

    @Override
    public void onDestroy() {
        stopMusic();
        ;
        mediaPlayer.release();
        super.onDestroy();
    }
}