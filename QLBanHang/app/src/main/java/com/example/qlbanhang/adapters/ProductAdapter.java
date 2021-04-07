package com.example.qlbanhang.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.qlbanhang.BanHang;
import com.example.qlbanhang.MainActivity;
import com.example.qlbanhang.R;

import java.util.ArrayList;

public class ProductAdapter extends ArrayAdapter<BanHang> {
    private Activity context;
    private int layoutID;
    private ArrayList<BanHang> objects;

    public ProductAdapter(Activity context, int layoutID, ArrayList<BanHang> objects) {
        super(context, layoutID, objects);
        this.context = context;
        this.layoutID = layoutID;
        this.objects = objects;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = context.getLayoutInflater().inflate(R.layout.listview_layout,parent,false);
        ImageView proImage = view.findViewById(R.id.proImage);
        TextView txtNhanVien = view.findViewById(R.id.txtNhanVien);
        TextView txtSoluong = view.findViewById(R.id.txtSoLuong);

        BanHang banHang = objects.get(position);


        txtNhanVien.setText(banHang.getNhanVien());
        txtSoluong.setText(banHang.getSoLuong());
        return view;
    }

}
