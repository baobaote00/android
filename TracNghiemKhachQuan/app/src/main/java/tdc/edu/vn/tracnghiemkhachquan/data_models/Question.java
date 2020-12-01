package tdc.edu.vn.tracnghiemkhachquan.data_models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Question {
    public static List<AbstractQuestion> questions = new ArrayList<>();
    public static void init(){
        MultiQuestionsMultiChoices question1 = new MultiQuestionsMultiChoices();
        question1.setQuestionDescription("Trong các mô hình mạng dưới đây, mô hình nào được dùng phổ biến hiện nay:");
        question1.setQuestionChoices("Peer - to – Peer" , "Remote Access" , "Terminal Mainframe" , "Client – Server");
        question1.setQuestionCorrect(0,3);

        MultiQuestionOneChoice question2 = new MultiQuestionOneChoice();
        question2.setQuestionDescription("Dịch vụ mạng DNS dùng để làm gì?");
        question2.setQuestionChoice("Cấp địa chỉ cho các máy trạm","Phân giải tên miền và địa chỉ IP","Truyền file và dữ liệu","Gửi thư điện tử");
        question2.setQuestionCorrect(1);

        MatchingQuestion question3 = new MatchingQuestion();
        question3.setQuestionDescription("Hãy tìm những đáp án đúng cho các mệnh đề dưới đây bằng cách nhấp chọn ô xổ xuống bên tay phải và lựa chọn câu trả lời:");
        question3.setQuestionChoiceA("Giao thức DHCP có thể cấp được...","Mô hình mạng dùng nhiều nhất...","Dịch vụ DNS dùng để ...");
        question3.setQuestionChoiceB("Địa chỉ Mac" , "Địa chỉ IP" , "Subnet Mask" , "Client – Server" , "Phân giải tên và địa chỉ");
        question3.setQuestionCorrect(1,3,4);

        TrueFalseQuestion question4 = new TrueFalseQuestion();
        question4.setQuestionDescription("Hãy lựa chọn ĐÚNG hay SAI cho những mệnh đề dưới đây bằng cách nhấp chuột vào nút bên tay phải:");
        question4.setQuestionChoice("Giao thức DHCP có thể cấp được địa chỉ IP" , "Mô hình mạng phổ biến: Terminal – Mainframe" , "Dịch vụ DNS dùng để phân giải tên và địa chỉ");
        question4.setQuestionCorrect(1,0,1);

        TrueFalseQuestion question5 = new TrueFalseQuestion();
        question5.setQuestionDescription("Hãy lựa chọn ĐÚNG hay SAI cho những mệnh đề dưới đây bằng cách kéo thanh trượt bên tay phải");
        question5.setQuestionChoice("Mô hình mạng phổ biến: Terminal – Mainframe" , "Giao thức DHCP có thể cấp được địa chỉ IP" , "Dịch vụ DNS dùng để phân giải tên và địa chỉ");
        question5.setQuestionCorrect(0,1,1);

        questions = Arrays.asList(question1,question2,question3,question4,question5);
    }
}
