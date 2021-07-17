package tdc.edu.vn.tracnghiemkhachquan.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Arrays;
import java.util.List;

import tdc.edu.vn.tracnghiemkhachquan.R;
import tdc.edu.vn.tracnghiemkhachquan.data_models.AbstractQuestion;
import tdc.edu.vn.tracnghiemkhachquan.data_models.MatchingQuestion;
import tdc.edu.vn.tracnghiemkhachquan.data_models.MultiQuestionOneChoice;
import tdc.edu.vn.tracnghiemkhachquan.data_models.Question;

public class MatchingQuestionFragment extends AbstractFragment{
    private MatchingQuestion question;
    private Spinner sp1,sp2,sp3;

    @Override
    public void setQuestion(AbstractQuestion question) {
        this.question = (MatchingQuestion) question;
    }

    @Override
    public void updateUserInteraction(int questionID) {
        Question.questions.get(questionID).getQuestionAnswers().clear();
        List<Spinner> sps = Arrays.asList(sp1,sp2,sp3);
        for (Spinner sp :sps) {
            Question.questions.get(questionID).getQuestionAnswers().add(sp.getSelectedItemPosition());
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.matchingquestion_fragment, container, false);

        TextView description = view.findViewById(R.id.description);
        sp1 = view.findViewById(R.id.spinner1);
        sp2 = view.findViewById(R.id.spinner2);
        sp3 = view.findViewById(R.id.spinner3);
        TextView txt1 = view.findViewById(R.id.textView1);
        TextView txt2 = view.findViewById(R.id.textView2);
        TextView txt3 = view.findViewById(R.id.textView3);

        List<TextView> cbs =Arrays.asList(txt1,txt2,txt3);
        List<Spinner> rbs = Arrays.asList(sp1,sp2,sp3);

        description.setText(question.getQuestionDescription());

        List<String> questionChoiceA = question.getQuestionChoiceA();
        for (int i = 0; i < rbs.size(); i++) {
            cbs.get(i).setText(questionChoiceA.get(i));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(inflater.getContext(), android.R.layout.simple_spinner_item,question.getQuestionChoiceB());
        for (int i = 0; i < rbs.size(); i++) {
            rbs.get(i).setAdapter(adapter);
        }

        return view;
    }
}
