package tdc.edu.vn.tracnghiemkhachquan.data_models;

import java.util.ArrayList;
import java.util.Collections;

public class TrueFalseQuestion extends AbstractQuestion {
    protected ArrayList<String> questionChoice;

    public TrueFalseQuestion() {
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
        int point = 0;
        if (questionAnswers.size() == questionCorrect.size()) {
            int i;
            for (i = 0; i < questionCorrect.size(); i++) {
                if (!questionCorrect.get(i).equals(questionAnswers.get(i))) {
                    break;
                }
            }
            if (i == questionCorrect.size()) {
                point = 1;
            }
        }
        return point;
    }

    @Override
    public String toString() {
        return "Câu " + (Question.questions.indexOf(this) + 1) + ": " + getPoint();
    }
}
