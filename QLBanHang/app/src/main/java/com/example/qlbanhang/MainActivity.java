package com.example.qlbanhang;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;

import com.example.qlbanhang.adapters.ProductAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static String AO_SO_MI_NAM = "Áo sơ mi nam";
    public static String AO_SO_MI_NU = "Áo sơ mi nữ";
    public static String QUAN_TAY = "Quần tây";

    private ArrayList<BanHang> listPro = new ArrayList<>();

    private ProductAdapter proAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnAdd = findViewById(R.id.btnAdd);
        Button btnExit = findViewById(R.id.btnExit);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_menu,menu);
        return true;
    }
}