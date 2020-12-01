package tdc.edu.vn.tracnghiemkhachquan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Result_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_layout);

        Button btnNext = findViewById(R.id.btnNext);
        Button btnPre = findViewById(R.id.btnPre);
        Button btnSkip = findViewById(R.id.btnSkip);

        btnNext.setOnClickListener(v -> {
            Intent intent = getIntent();
            intent.setClass(Result_Activity.this, Question1_Activity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
        });

        btnPre.setOnClickListener(v -> {
            Intent intent = getIntent();
            intent.setClass(Result_Activity.this, Question5_Activity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
        });
    }
}
