package tdc.edu.vn.tracnghiemkhachquan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import tdc.edu.vn.tracnghiemkhachquan.data_models.MatchingQuestion;
import tdc.edu.vn.tracnghiemkhachquan.data_models.MultiQuestionOneChoice;
import tdc.edu.vn.tracnghiemkhachquan.data_models.Question;
import tdc.edu.vn.tracnghiemkhachquan.view_model.RadioButtonGroup;

public class Question3_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question3_layout);

        Button btnNext = findViewById(R.id.btnNext);
        Button btnPre = findViewById(R.id.btnPre);
        Button btnSkip = findViewById(R.id.btnSkip);

        btnNext.setOnClickListener(v -> {
            Intent intent = getIntent();
            intent.setClass(Question3_Activity.this, Question4_Activity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
        });

        btnPre.setOnClickListener(v -> {
            Intent intent = getIntent();
            intent.setClass(Question3_Activity.this, Question2_Activity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
        });

        btnSkip.setOnClickListener(v -> {
            Intent intent = getIntent();
            intent.setClass(Question3_Activity.this, Result_Activity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
        });

        TextView txt1 = findViewById(R.id.textView1);
        TextView txt2 = findViewById(R.id.textView2);
        TextView txt3 = findViewById(R.id.textView3);
        Spinner spn1 = findViewById(R.id.spinner1);
        Spinner spn2 = findViewById(R.id.spinner2);
        Spinner spn3 = findViewById(R.id.spinner3);
        TextView description = findViewById(R.id.description);

        List<TextView> cbs = Arrays.asList(txt1,txt2,txt3);
        List<Spinner> spns = Arrays.asList(spn1,spn2,spn3);
        Question.init();
        MatchingQuestion question1 = (MatchingQuestion)Question.questions.get(2);
        description.setText(question1.getQuestionDescription());
        for (int i = 0; i < cbs.size(); i++) {
            List<String> questionChoice = question1.getQuestionChoiceA();
            cbs.get(i).setText(questionChoice.get(i));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,question1.getQuestionChoiceB());
        for (int i = 0; i < spns.size(); i++) {
            spns.get(i).setAdapter(adapter);
        }
    }
}
