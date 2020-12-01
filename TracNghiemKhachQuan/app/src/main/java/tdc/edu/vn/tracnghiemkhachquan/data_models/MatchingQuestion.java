package tdc.edu.vn.tracnghiemkhachquan.data_models;

import java.util.ArrayList;
import java.util.Collections;

public class MatchingQuestion extends AbstractQuestion {
    protected ArrayList<String> questionChoiceA;
    protected ArrayList<String> questionChoiceB;

    public MatchingQuestion() {
        this.questionChoiceA = new ArrayList<>();
        this.questionChoiceB = new ArrayList<>();
        this.questionAnswers = new ArrayList<>();
        this.questionCorrect = new ArrayList<>();
    }

    public ArrayList<String> getQuestionChoiceA() {
        return questionChoiceA;
    }

    public void setQuestionChoiceA(String... questionChoiceA) {
        Collections.addAll(this.questionChoiceA,questionChoiceA);
    }

    public ArrayList<String> getQuestionChoiceB() {
        return questionChoiceB;
    }

    public void setQuestionChoiceB(String... questionChoiceB) {
        Collections.addAll(this.questionChoiceB,questionChoiceB);
    }

    @Override
    public int getPoint() {
        return 0;
    }
}
