package tdc.edu.vn.kiemtragiuaky.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import tdc.edu.vn.kiemtragiuaky.MainActivity;
import tdc.edu.vn.kiemtragiuaky.Person;
import tdc.edu.vn.kiemtragiuaky.R;

public class PersonAdapter extends ArrayAdapter<Person> {
    private Activity context;
    private int layoutID;
    private ArrayList<Person> objects;

    public PersonAdapter(Activity context, int layoutID, ArrayList<Person> objects) {
        super(context, layoutID, objects);
        this.context = context;
        this.layoutID = layoutID;
        this.objects = objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        @SuppressLint("ViewHolder") View view = context.getLayoutInflater().inflate(R.layout.listview_item_layout, parent, false);
        ImageView degreeImage = view.findViewById(R.id.degreeImage);
        TextView lblName = view.findViewById(R.id.lblName);
        TextView lblHobbies = view.findViewById(R.id.lblHobbies);
        CheckBox chkPerson = view.findViewById(R.id.chkUpdate);

        chkPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()){
                    objects.get(position).setCheck(true);
                }else {
                    objects.get(position).setCheck(false);
                }
            }
        });

        Person person = objects.get(position);

        if (person.getDegree().equalsIgnoreCase(MainActivity.CAODANG)) {
            degreeImage.setBackground(context.getResources().getDrawable(R.mipmap.college));
        } else if (person.getDegree().equalsIgnoreCase(MainActivity.DAIHOC)) {
            degreeImage.setBackground(context.getResources().getDrawable(R.mipmap.university));
        } else if (person.getDegree().equalsIgnoreCase(MainActivity.TRUNGCAP)) {
            degreeImage.setBackground(context.getResources().getDrawable(R.mipmap.midium));
        } else {
            degreeImage.setBackground(context.getResources().getDrawable(R.mipmap.none));
        }

        lblName.setText(person.getName());
        String str;
        str=person.getHobbies().toString().replaceAll(",",";");
        str= str.substring(1,str.length()-1);
        lblHobbies.setText(str);
        return view;
    }
}
