package tdc.edu.vn.tracnghiemkhachquan.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Arrays;
import java.util.List;

import tdc.edu.vn.tracnghiemkhachquan.R;
import tdc.edu.vn.tracnghiemkhachquan.data_models.AbstractQuestion;
import tdc.edu.vn.tracnghiemkhachquan.data_models.MultiQuestionsMultiChoices;
import tdc.edu.vn.tracnghiemkhachquan.data_models.Question;

public class MultiQuestionMultiChoiceFragment extends AbstractFragment {
    private MultiQuestionsMultiChoices question;
    private CheckBox cb1, cb2, cb3, cb4;

    @Override
    public void setQuestion(AbstractQuestion question) {
        this.question = (MultiQuestionsMultiChoices) question;
    }

    @Override
    public void updateUserInteraction(int questionID) {
        Question.questions.get(questionID).getQuestionAnswers().clear();
        List<CheckBox> cbs = Arrays.asList(cb1, cb2, cb3, cb4);
        for (CheckBox cb : cbs) {
            if (cb.isChecked()) {
                Question.questions.get(questionID).getQuestionAnswers().add(cbs.indexOf(cb));
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.multiquestion_multichoice_fragment, container, false);

        TextView description = view.findViewById(R.id.description);
        cb1 = view.findViewById(R.id.CBChooseA);
        cb2 = view.findViewById(R.id.CBChooseB);
        cb3 = view.findViewById(R.id.CBChooseC);
        cb4 = view.findViewById(R.id.CBChooseD);

        List<CheckBox> cbs = Arrays.asList(cb1, cb2, cb3, cb4);

        description.setText(question.getQuestionDescription());
        for (int i = 0; i < cbs.size(); i++) {
            List<String> questionChoice = question.getQuestionChoices();
            cbs.get(i).setText(questionChoice.get(i));
        }

        return view;
    }
}
