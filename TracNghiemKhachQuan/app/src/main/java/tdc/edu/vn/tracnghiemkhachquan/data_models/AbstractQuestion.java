package tdc.edu.vn.tracnghiemkhachquan.data_models;

import java.util.ArrayList;
import java.util.Collections;

public abstract class AbstractQuestion {
    protected String questionDescription;
    protected ArrayList<Integer> questionCorrect;
    protected ArrayList<Integer> questionAnswers;

    public abstract int getPoint();

    public void setQuestionAnswers(Integer... questionAnswers) {
        Collections.addAll(this.questionAnswers, questionAnswers);
    }

    public void setQuestionDescription(String questionDescription) {
        this.questionDescription = questionDescription;
    }

    public void setQuestionCorrect(Integer... questionCorrect) {
        Collections.addAll(this.questionCorrect, questionCorrect);
    }

    public String getQuestionDescription() {
        return questionDescription;
    }

    public ArrayList<Integer> getQuestionCorrect() {
        return questionCorrect;
    }

    public ArrayList<Integer> getQuestionAnswers() {
        return questionAnswers;
    }
}
