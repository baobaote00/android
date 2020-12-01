package tdc.edu.vn.tracnghiemkhachquan.data_models;

import java.util.ArrayList;
import java.util.Collections;

public class MultiQuestionsMultiChoices extends AbstractQuestion {
    protected ArrayList<String> questionChoices;

    public MultiQuestionsMultiChoices() {
        this.questionChoices = new ArrayList<>();
        this.questionCorrect= new ArrayList<>();
        this.questionAnswers=new ArrayList<>();
    }

    public void setQuestionChoices(String... questionChoices) {
        Collections.addAll(this.questionChoices, questionChoices);
    }

    public ArrayList<String> getQuestionChoices() {
        return questionChoices;
    }

    @Override
    public int getPoint() {
        int point =0;

        if(questionAnswers.size()==questionCorrect.size()){
            int i;
            for (i=0;i<questionCorrect.size();i++){
                if (!questionCorrect.get(i).equals(questionAnswers.get(i))){
                    break;
                }
            }
            if (i==questionCorrect.size()){
                point=1;
            }
        }
        return point;
    }
}
