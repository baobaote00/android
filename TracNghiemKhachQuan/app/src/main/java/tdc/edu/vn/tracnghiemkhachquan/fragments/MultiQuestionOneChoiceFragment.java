package tdc.edu.vn.tracnghiemkhachquan.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Arrays;
import java.util.List;

import tdc.edu.vn.tracnghiemkhachquan.R;
import tdc.edu.vn.tracnghiemkhachquan.data_models.AbstractQuestion;
import tdc.edu.vn.tracnghiemkhachquan.data_models.MultiQuestionOneChoice;
import tdc.edu.vn.tracnghiemkhachquan.data_models.MultiQuestionsMultiChoices;
import tdc.edu.vn.tracnghiemkhachquan.data_models.Question;
import tdc.edu.vn.tracnghiemkhachquan.view_model.RadioButtonGroup;

public class MultiQuestionOneChoiceFragment extends AbstractFragment{
    private MultiQuestionOneChoice question;
    private RadioButton rb1, rb2, rb3, rb4;
    @Override
    public void setQuestion(AbstractQuestion question) {
        this.question = (MultiQuestionOneChoice) question;
    }

    @Override
    public void updateUserInteraction(int questionID) {
        Question.questions.get(questionID).getQuestionAnswers().clear();
        List<RadioButton> rbs = Arrays.asList(rb1, rb2, rb3, rb4);
        for (RadioButton rb : rbs) {
            if (rb.isChecked()) {
                Question.questions.get(questionID).getQuestionAnswers().add(rbs.indexOf(rb));
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.multiquestion_onechoice_fragment, container, false);

        TextView description = view.findViewById(R.id.description);
        rb1 = view.findViewById(R.id.RBChooseA);
        rb2 = view.findViewById(R.id.RBChooseB);
        rb3 = view.findViewById(R.id.RBChooseC);
        rb4 = view.findViewById(R.id.RBChooseD);

        List<RadioButton> rbs = Arrays.asList(rb1, rb2, rb3, rb4);
        RadioButtonGroup g = new RadioButtonGroup(rb1,rb2,rb3,rb4);
        description.setText(question.getQuestionDescription());
        for (int i = 0; i < rbs.size(); i++) {
            List<String> questionChoice = question.getQuestionChoice();
            rbs.get(i).setText(questionChoice.get(i));
        }

        return view;
    }
}
