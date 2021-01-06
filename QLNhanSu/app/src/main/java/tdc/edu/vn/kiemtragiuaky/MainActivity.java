package tdc.edu.vn.kiemtragiuaky;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import tdc.edu.vn.kiemtragiuaky.adapters.PersonAdapter;

public class MainActivity extends AppCompatActivity {
    public static String DAIHOC = "University";
    public static String CAODANG = "College";
    public static String TRUNGCAP = "Training";
    public static String KHONGBANGCAP = "None";

    private ArrayList<Person> listPerson = new ArrayList<>();

    private PersonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//Lay id button
        Button btnAdd = findViewById(R.id.btnAdd);
        Button btnCancel = findViewById(R.id.btnCancel);
//Lay id radio
        final EditText edtName = findViewById(R.id.name);
        final RadioGroup radioGroup = findViewById(R.id.grRadio);

        final CheckBox chkRead = findViewById(R.id.read);
        final CheckBox chkTravel = findViewById(R.id.travel);
        final EditText edtOther = findViewById(R.id.other);

        final ListView listView = findViewById(R.id.results);
        adapter = new PersonAdapter(this, R.layout.listview_item_layout, listPerson);
        listView.setAdapter(adapter);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Person person = new Person();
                person.setName(edtName.getText().toString());
                if (!edtName.getText().toString().isEmpty()) {
                    int radioID = radioGroup.getCheckedRadioButtonId();
                    switch (radioID) {
                        case R.id.university:
                            person.setDegree(DAIHOC);
                            break;
                        case R.id.college:
                            person.setDegree(CAODANG);
                            break;
                        case R.id.training:
                            person.setDegree(TRUNGCAP);
                            break;
                        default:
                            person.setDegree(KHONGBANGCAP);
                            break;
                    }

                    ArrayList<String> hobbies = new ArrayList<>();
                    if (chkRead.isChecked()) {
                        hobbies.add(chkRead.getText().toString());
                    }
                    if (chkTravel.isChecked()) {
                        hobbies.add(chkTravel.getText().toString());
                    }
                    hobbies.add(edtOther.getText().toString());
                    person.setHobbies(hobbies);
                    listPerson.add(person);
                    adapter.notifyDataSetChanged();

                    edtName.setText("");
                    radioGroup.clearCheck();
                    chkRead.setChecked(false);
                    chkTravel.setChecked(false);
                    edtOther.setText("");
                    edtName.requestFocus();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.saveMN:
                save();
                break;
            case R.id.removeMN:
                remove();
                break;
            case R.id.updateMN:
                update();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public void save(){
        Toast.makeText(this,"save()",Toast.LENGTH_SHORT).show();
    }
    public void remove(){
        Toast.makeText(this,"remove()",Toast.LENGTH_SHORT).show();
    }
    public void update(){
        Toast.makeText(this,"update()",Toast.LENGTH_SHORT).show();
    }

}
