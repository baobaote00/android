package tdc.edu.vn.kiemtragiuaky.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import tdc.edu.vn.kiemtragiuaky.MainActivity;
import tdc.edu.vn.kiemtragiuaky.Person;
import tdc.edu.vn.kiemtragiuaky.R;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {
    private Activity context;
    private int layoutID;
    private ArrayList<Person> list;

    public MyRecyclerViewAdapter(Activity context, int layoutID, ArrayList<Person> list) {
        this.context = context;
        this.layoutID = layoutID;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = context.getLayoutInflater();
        CardView cardView = (CardView) inflater.inflate(viewType,parent,false);
        return new MyViewHolder(cardView,list);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Person person = list.get(position);
        holder.setPosition(position);
        holder.chkSelection.setChecked(false);
        if (person.getDegree().equalsIgnoreCase(MainActivity.CAODANG)) {
            holder.imageView.setImageDrawable(context.getResources().getDrawable(R.mipmap.college,context.getTheme()));
        } else if (person.getDegree().equalsIgnoreCase(MainActivity.DAIHOC)) {
            holder.imageView.setBackground(context.getResources().getDrawable(R.mipmap.university,context.getTheme()));
        } else if (person.getDegree().equalsIgnoreCase(MainActivity.TRUNGCAP)) {
            holder.imageView.setBackground(context.getResources().getDrawable(R.mipmap.midium,context.getTheme()));
        } else {
            holder.imageView.setBackground(context.getResources().getDrawable(R.mipmap.none,context.getTheme()));
        }
        holder.txtName.setText(person.getName());
        String str;
        str=person.getHobbies().toString().replaceAll(",",";");
        str= str.substring(1,str.length()-1);
        holder.txtHoppies.setText(str);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return layoutID;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView txtName, txtHoppies;
        private CheckBox chkSelection;
        private int pos;

        public MyViewHolder(@NonNull View itemView,ArrayList<Person> list) {
            super(itemView);
            imageView = itemView.findViewById(R.id.degreeImage);
            txtName = itemView.findViewById(R.id.lblName);
            txtHoppies = itemView.findViewById(R.id.lblHobbies);
            chkSelection = itemView.findViewById(R.id.chkUpdate);

            chkSelection.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (((CheckBox)v).isChecked()){
                        list.get(pos).setCheck(true);
                    }else {
                        list.get(pos).setCheck(false);
                    }
                }
            });
        }

        public void setPosition(int pos){
            this.pos = pos;
        }
    }
}
