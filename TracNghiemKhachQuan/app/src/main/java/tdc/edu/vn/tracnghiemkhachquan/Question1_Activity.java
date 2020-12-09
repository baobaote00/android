package tdc.edu.vn.tracnghiemkhachquan;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
//import android.view.View;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.Button;

import tdc.edu.vn.tracnghiemkhachquan.data_models.MultiQuestionOneChoice;
import tdc.edu.vn.tracnghiemkhachquan.data_models.MultiQuestionsMultiChoices;
import tdc.edu.vn.tracnghiemkhachquan.data_models.Question;
import tdc.edu.vn.tracnghiemkhachquan.fragments.AbstractFragment;
import tdc.edu.vn.tracnghiemkhachquan.fragments.MultiQuestionMultiChoiceFragment;

public class Question1_Activity extends AppCompatActivity {
    private int questionID = 0;
    private AbstractFragment fragment;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question1_layout);

        Button btnNext = findViewById(R.id.btnNext);
        Button btnPre = findViewById(R.id.btnPre);
        Button btnSkip = findViewById(R.id.btnSkip);

        if (savedInstanceState == null) {
            Question.init();
        }
        updateUI();

//        TextView description = findViewById(R.id.description);
//        CheckBox cb1 = findViewById(R.id.CBChooseA);
//        CheckBox cb2 = findViewById(R.id.CBChooseB);
//        CheckBox cb3 = findViewById(R.id.CBChooseC);
//        CheckBox cb4 = findViewById(R.id.CBChooseD);

        btnNext.setOnClickListener(v -> {
            Intent intent = getIntent();
            intent.setClass(Question1_Activity.this, Question2_Activity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
//            processUserActivities(cb1,cb2,cb3,cb4);
            startActivity(intent);
        });

        btnPre.setOnClickListener(v -> {
            Intent intent = getIntent();
            intent.setClass(Question1_Activity.this, Result_Activity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
//            processUserActivities(cb1,cb2,cb3,cb4);
            startActivity(intent);
        });

        btnSkip.setOnClickListener(v -> {
            Intent intent = getIntent();
            intent.setClass(Question1_Activity.this, Result_Activity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
//            processUserActivities(cb1,cb2,cb3,cb4);
            startActivity(intent);
        });

//        List<CheckBox> cbs = Arrays.asList(cb1, cb2, cb3, cb4);


//        MultiQuestionsMultiChoices question1 = (MultiQuestionsMultiChoices) Question.questions.get(0);
//        description.setText(question1.getQuestionDescription());
//        for (int i = 0; i < cbs.size(); i++) {
//            List<String> questionChoice = question1.getQuestionChoices();
//            cbs.get(i).setText(questionChoice.get(i));
//        }
    }

    //    private void processUserActivities(CheckBox cb1, CheckBox cb2, CheckBox cb3, CheckBox cb4) {
//        Question.questions.get(0).getQuestionAnswers().clear();
//        List<CheckBox> cbs = Arrays.asList(cb1, cb2, cb3, cb4);
//        for (CheckBox cb : cbs) {
//            if (cb.isChecked()){
//                Question.questions.get(0).getQuestionAnswers().add(cbs.indexOf(cb));
//            }
//        }
//    }
    private void updateUI() {
        Log.d("test", "updateUI: "+"test");
        this.setTitle("cau hoi so: " + (questionID + 1));
        if (Question.questions.get(questionID) instanceof MultiQuestionsMultiChoices){
            fragment = new MultiQuestionMultiChoiceFragment();
        }else if (Question.questions.get(questionID) instanceof MultiQuestionOneChoice){

        }
        fragment.setQuestion(Question.questions.get(questionID));
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentLayout1,fragment);
        transaction.commit();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }
}
