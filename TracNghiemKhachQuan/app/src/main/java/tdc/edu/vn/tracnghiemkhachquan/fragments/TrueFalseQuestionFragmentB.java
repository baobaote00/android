package tdc.edu.vn.tracnghiemkhachquan.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.Arrays;
import java.util.List;

import tdc.edu.vn.tracnghiemkhachquan.R;
import tdc.edu.vn.tracnghiemkhachquan.data_models.AbstractQuestion;
import tdc.edu.vn.tracnghiemkhachquan.data_models.Question;
import tdc.edu.vn.tracnghiemkhachquan.data_models.TrueFalseQuestion;

public class TrueFalseQuestionFragmentB extends AbstractFragment{
    private TrueFalseQuestion question;
    private SwitchMaterial sw1,sw2,sw3;
    @Override
    public void setQuestion(AbstractQuestion question) {
        this.question = (TrueFalseQuestion) question;
    }

    @Override
    public void updateUserInteraction(int questionID) {
        Question.questions.get(questionID).getQuestionAnswers().clear();
        List<SwitchMaterial> sws = Arrays.asList(sw1,sw2,sw3);
        int chk = 0;
        for (SwitchMaterial sw :sws) {
            chk = sw.isChecked()?1:0;
            Question.questions.get(questionID).getQuestionAnswers().add(chk);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.truefalsequestion_switch_fragment, container, false);
        TextView txt1 = view.findViewById(R.id.textView7);
        TextView txt2 = view.findViewById(R.id.textView8);
        TextView txt3 = view.findViewById(R.id.textView9);

        sw1 = view.findViewById(R.id.switch1);
        sw2 = view.findViewById(R.id.switch2);
        sw3 = view.findViewById(R.id.switch3);
        
        TextView description = view.findViewById(R.id.description);
        List<TextView> cbs = Arrays.asList(txt1,txt2,txt3);
        TrueFalseQuestion question1 = (TrueFalseQuestion)Question.questions.get(4);
        description.setText(question1.getQuestionDescription());

        List<String> questionChoice = question1.getQuestionChoice();
        for (int i = 0; i < cbs.size(); i++) {
            cbs.get(i).setText(questionChoice.get(i));
        }
        return view;
    }
}
