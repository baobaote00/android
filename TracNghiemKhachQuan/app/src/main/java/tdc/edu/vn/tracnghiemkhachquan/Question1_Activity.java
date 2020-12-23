package tdc.edu.vn.tracnghiemkhachquan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
//import android.view.View;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.Button;

import tdc.edu.vn.tracnghiemkhachquan.data_models.MatchingQuestion;
import tdc.edu.vn.tracnghiemkhachquan.data_models.MultiQuestionOneChoice;
import tdc.edu.vn.tracnghiemkhachquan.data_models.MultiQuestionsMultiChoices;
import tdc.edu.vn.tracnghiemkhachquan.data_models.Question;
import tdc.edu.vn.tracnghiemkhachquan.data_models.TrueFalseQuestion;
import tdc.edu.vn.tracnghiemkhachquan.fragments.AbstractFragment;
import tdc.edu.vn.tracnghiemkhachquan.fragments.MatchingQuestionFragment;
import tdc.edu.vn.tracnghiemkhachquan.fragments.MultiQuestionMultiChoiceFragment;
import tdc.edu.vn.tracnghiemkhachquan.fragments.MultiQuestionOneChoiceFragment;
import tdc.edu.vn.tracnghiemkhachquan.fragments.TrueFalseQuestionFragmentA;
import tdc.edu.vn.tracnghiemkhachquan.fragments.TrueFalseQuestionFragmentB;

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

        btnNext.setOnClickListener(v -> {
            fragment.updateUserInteraction(questionID);
            if (questionID == Question.questions.size() - 1) {
                questionID = 0;
            } else {
                questionID++;
            }
            updateUI();
        });

        btnPre.setOnClickListener(v -> {
            fragment.updateUserInteraction(questionID);
            if (questionID == 0) {
                questionID = Question.questions.size() - 1;
            } else {
                questionID--;
            }
            updateUI();
        });

        btnSkip.setOnClickListener(v -> {
            fragment.updateUserInteraction(questionID);
            Intent intent = getIntent();
            intent.setClass(Question1_Activity.this, Result_Activity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
        });
    }

    private void updateUI() {
        this.setTitle("cau hoi so: " + (questionID + 1));
        Fragment f = getSupportFragmentManager().findFragmentByTag(questionID + "");
        if (Question.questions.get(questionID) instanceof MultiQuestionsMultiChoices) {
            fragment = f!=null?(MultiQuestionMultiChoiceFragment)f:new MultiQuestionMultiChoiceFragment();
        }else if(Question.questions.get(questionID) instanceof  MultiQuestionOneChoice){
            fragment = f!=null?(MultiQuestionOneChoiceFragment)f:new MultiQuestionOneChoiceFragment();
        } else if (Question.questions.get(questionID) instanceof MatchingQuestion) {
            fragment = f!=null?(MatchingQuestionFragment)f:new MatchingQuestionFragment();
        } else if (Question.questions.get(questionID) instanceof TrueFalseQuestion) {
            if (questionID % 2 != 0) {
                fragment = f!=null?(TrueFalseQuestionFragmentA)f:new TrueFalseQuestionFragmentA();
            } else {
                fragment = f!=null?(TrueFalseQuestionFragmentB)f:new TrueFalseQuestionFragmentB();
            }
        }
        fragment.setQuestion(Question.questions.get(questionID));
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentLayout1, fragment, questionID + "");
        if (getSupportFragmentManager().findFragmentByTag(questionID + "") == null) {
            transaction.addToBackStack(questionID + "");
        }
        transaction.commit();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }
}
