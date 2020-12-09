package tdc.edu.vn.tracnghiemkhachquan.data_models;

import java.util.ArrayList;
import java.util.Collections;

public class MultiQuestionOneChoice extends AbstractQuestion {
    protected ArrayList<String> questionChoice;

    public MultiQuestionOneChoice() {
        this.questionChoice = new ArrayList<>();
        this.questionAnswers = new ArrayList<>();
        this.questionCorrect = new ArrayList<>();
    }

    public ArrayList<String> getQuestionChoice() {
        return questionChoice;
    }

    public void setQuestionChoice(String... questionChoice) {
        Collections.addAll(this.questionChoice, questionChoice);
    }

    @Override
    public int getPoint() {
        return questionChoice.equals(questionCorrect)?1:0;
    }

    @Override
    public String toString() {
        return "Câu " + (Question.questions.indexOf(this) + 1) + ": " + getPoint();
    }
}
