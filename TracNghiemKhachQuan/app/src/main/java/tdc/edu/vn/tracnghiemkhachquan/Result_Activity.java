package tdc.edu.vn.tracnghiemkhachquan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import tdc.edu.vn.tracnghiemkhachquan.data_models.AbstractQuestion;
import tdc.edu.vn.tracnghiemkhachquan.data_models.Question;

public class Result_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_layout);

        Button btnNext = findViewById(R.id.btnNext);
        Button btnPre = findViewById(R.id.btnPre);
        Button btnSkip = findViewById(R.id.btnSkip);

        ListView l = findViewById(R.id.listView);
        ArrayAdapter<AbstractQuestion> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Question.questions);
        l.setAdapter(adapter);

        btnNext.setOnClickListener(v -> {
            Intent intent = getIntent();
            intent.setClass(Result_Activity.this, Question1_Activity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
        });

        btnSkip.setOnClickListener(v -> {
            Intent intent=new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
        });

        btnPre.setOnClickListener(v -> {
            Intent intent = getIntent();
            intent.setClass(Result_Activity.this, Question1_Activity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
        });
    }

}
