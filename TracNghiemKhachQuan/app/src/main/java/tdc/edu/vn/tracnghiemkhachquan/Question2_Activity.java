package tdc.edu.vn.tracnghiemkhachquan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import tdc.edu.vn.tracnghiemkhachquan.data_models.MultiQuestionOneChoice;
import tdc.edu.vn.tracnghiemkhachquan.data_models.MultiQuestionsMultiChoices;
import tdc.edu.vn.tracnghiemkhachquan.data_models.Question;
import tdc.edu.vn.tracnghiemkhachquan.view_model.RadioButtonGroup;

public class Question2_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question2_layout);

        Button btnNext = findViewById(R.id.btnNext);
        Button btnPre = findViewById(R.id.btnPre);
        Button btnSkip = findViewById(R.id.btnSkip);

        btnNext.setOnClickListener(v -> {
            Intent intent = getIntent();
            intent.setClass(Question2_Activity.this, Question3_Activity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
        });

        btnPre.setOnClickListener(v -> {
            Intent intent = getIntent();
            intent.setClass(Question2_Activity.this, Question1_Activity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
        });

        btnSkip.setOnClickListener(v -> {
            Intent intent = getIntent();
            intent.setClass(Question2_Activity.this, Result_Activity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
        });

        RadioButton rad1 = findViewById(R.id.RBChooseA);
        RadioButton rad2 = findViewById(R.id.RBChooseB);
        RadioButton rad3 = findViewById(R.id.RBChooseC);
        RadioButton rad4 = findViewById(R.id.RBChooseD);
        RadioButtonGroup radioButtonGroup = new RadioButtonGroup(rad1,rad2,rad3,rad4);

        TextView description = findViewById(R.id.description);
        List<RadioButton> cbs = Arrays.asList(rad1, rad2, rad3, rad4);
        Question.init();
        MultiQuestionOneChoice question1 = (MultiQuestionOneChoice)Question.questions.get(1);
        description.setText(question1.getQuestionDescription());
        for (int i = 0; i < cbs.size(); i++) {
            List<String> questionChoice = question1.getQuestionChoice();
            cbs.get(i).setText(questionChoice.get(i));
        }
    }
}
