package tdc.edu.vn.tracnghiemkhachquan;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
//import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import tdc.edu.vn.tracnghiemkhachquan.data_models.MultiQuestionsMultiChoices;
import tdc.edu.vn.tracnghiemkhachquan.data_models.Question;

public class Question1_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question1_layout);
        setTitle(R.string.label_1);
        Button btnNext = findViewById(R.id.btnNext);
        Button btnPre = findViewById(R.id.btnPre);
        Button btnSkip = findViewById(R.id.btnSkip);

        btnNext.setOnClickListener(v -> {
            Intent intent = getIntent();
            intent.setClass(Question1_Activity.this, Question2_Activity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
        });

        btnPre.setOnClickListener(v -> {
            Intent intent = getIntent();
            intent.setClass(Question1_Activity.this, Result_Activity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
        });

        btnSkip.setOnClickListener(v -> {
            Intent intent = getIntent();
            intent.setClass(Question1_Activity.this, Result_Activity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
        });

        TextView description = findViewById(R.id.description);
        CheckBox cb1 = findViewById(R.id.CBChooseA);
        CheckBox cb2 = findViewById(R.id.CBChooseB);
        CheckBox cb3 = findViewById(R.id.CBChooseC);
        CheckBox cb4 = findViewById(R.id.CBChooseD);
        List<CheckBox> cbs = Arrays.asList(cb1, cb2, cb3, cb4);
        Question.init();
        MultiQuestionsMultiChoices question1 = (MultiQuestionsMultiChoices)Question.questions.get(0);
        description.setText(question1.getQuestionDescription());
        for (int i = 0; i < cbs.size(); i++) {
            List<String> questionChoice = question1.getQuestionChoices();
            cbs.get(i).setText(questionChoice.get(i));
        }
    }
}
