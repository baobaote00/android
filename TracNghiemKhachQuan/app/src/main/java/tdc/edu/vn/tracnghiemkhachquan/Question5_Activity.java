package tdc.edu.vn.tracnghiemkhachquan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import tdc.edu.vn.tracnghiemkhachquan.data_models.Question;
import tdc.edu.vn.tracnghiemkhachquan.data_models.TrueFalseQuestion;

public class Question5_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question5_layout);

        Button btnNext = findViewById(R.id.btnNext);
        Button btnPre = findViewById(R.id.btnPre);
        Button btnSkip = findViewById(R.id.btnSkip);

        btnNext.setOnClickListener(v -> {
            Intent intent = getIntent();
            intent.setClass(Question5_Activity.this, Result_Activity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
        });

        btnPre.setOnClickListener(v -> {
            Intent intent = getIntent();
            intent.setClass(Question5_Activity.this, Question4_Activity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
        });

        btnSkip.setOnClickListener(v -> {
            Intent intent = getIntent();
            intent.setClass(Question5_Activity.this, Result_Activity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
        });

        TextView txt1 = findViewById(R.id.textView7);
        TextView txt2 = findViewById(R.id.textView8);
        TextView txt3 = findViewById(R.id.textView9);

        TextView description = findViewById(R.id.description);
        List<TextView> cbs = Arrays.asList(txt1,txt2,txt3);
        Question.init();
        TrueFalseQuestion question1 = (TrueFalseQuestion)Question.questions.get(4);
        description.setText(question1.getQuestionDescription());
        for (int i = 0; i < cbs.size(); i++) {
            List<String> questionChoice = question1.getQuestionChoice();
            cbs.get(i).setText(questionChoice.get(i));
        }
    }
}
