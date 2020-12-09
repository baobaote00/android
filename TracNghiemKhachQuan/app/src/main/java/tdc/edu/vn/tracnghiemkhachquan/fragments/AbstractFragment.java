package tdc.edu.vn.tracnghiemkhachquan.fragments;

import androidx.fragment.app.Fragment;

import tdc.edu.vn.tracnghiemkhachquan.data_models.AbstractQuestion;

public abstract class AbstractFragment extends Fragment {
    public abstract void setQuestion(AbstractQuestion question);
    public abstract void updateUserInteraction(int questionID);
}
