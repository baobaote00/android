package tdc.edu.vn.tracnghiemkhachquan.view_model;

import android.view.View;
import android.widget.RadioButton;

import java.util.ArrayList;

public class RadioButtonGroup {
    public ArrayList<RadioButton> radioButtons;

    public RadioButtonGroup(RadioButton... radioButtons) {
        this.radioButtons = new ArrayList<>();
        for (RadioButton rad: radioButtons) {
            rad.setOnClickListener(onClick);
            this.radioButtons.add(rad);
        }

    }
    View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            for (RadioButton rad: radioButtons) {
                rad.setChecked(false);
            }
            ((RadioButton) v).setChecked(true);
        }
    };
}
