package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.myapplication.widgets.Action;
import com.example.myapplication.widgets.Player;

public class MainActivity extends AppCompatActivity implements Action {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Player player = findViewById(R.id.player);
        player.setAction(this);
    }

    @Override
    public void play() {
        Toast.makeText(this,"play",Toast.LENGTH_LONG).show();
    }

    @Override
    public void pause() {
        Toast.makeText(this,"pause",Toast.LENGTH_LONG).show();
    }

    @Override
    public void pre() {
        Toast.makeText(this,"pre",Toast.LENGTH_LONG).show();
    }

    @Override
    public void next() {
        Toast.makeText(this,"next",Toast.LENGTH_LONG).show();
    }

    @Override
    public void stop() {
        Toast.makeText(this,"stop",Toast.LENGTH_LONG).show();
    }
}