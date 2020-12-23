package tdc.edu.vn.tracnghiemkhachquan.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Arrays;
import java.util.List;

import tdc.edu.vn.tracnghiemkhachquan.R;
import tdc.edu.vn.tracnghiemkhachquan.data_models.AbstractQuestion;
import tdc.edu.vn.tracnghiemkhachquan.data_models.MatchingQuestion;
import tdc.edu.vn.tracnghiemkhachquan.data_models.Question;
import tdc.edu.vn.tracnghiemkhachquan.data_models.TrueFalseQuestion;

public class TrueFalseQuestionFragmentA extends AbstractFragment{
    private TrueFalseQuestion question;
    private ToggleButton tg1,tg2,tg3;
    @Override
    public void setQuestion(AbstractQuestion question) {
        this.question = (TrueFalseQuestion) question;
    }

    @Override
    public void updateUserInteraction(int questionID) {
        Question.questions.get(questionID).getQuestionAnswers().clear();
        List<ToggleButton> tgs = Arrays.asList(tg1,tg2,tg3);
        int chk = 0;
        for (ToggleButton tg :tgs) {
            chk = tg.isChecked()?1:0;
            Question.questions.get(questionID).getQuestionAnswers().add(chk);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.truefalsequestion_togglebutton_fragment, container, false);
        TextView txt1 = view.findViewById(R.id.textView4);
        TextView txt2 = view.findViewById(R.id.textView5);
        TextView txt3 = view.findViewById(R.id.textView6);

        tg1 = view.findViewById(R.id.toggleButton1);
        tg2 = view.findViewById(R.id.toggleButton2);
        tg3 = view.findViewById(R.id.toggleButton3);

        TextView description = view.findViewById(R.id.description);
        List<TextView> cbs = Arrays.asList(txt1,txt2,txt3);
        TrueFalseQuestion question1 = (TrueFalseQuestion)Question.questions.get(3);
        description.setText(question1.getQuestionDescription());

        List<String> questionChoice = question1.getQuestionChoice();
        for (int i = 0; i < cbs.size(); i++) {
            cbs.get(i).setText(questionChoice.get(i));
        }
        return view;
    }
}
