package tdc.edu.vn.kiemtragiuaky.database_layer;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import tdc.edu.vn.kiemtragiuaky.Person;

public class PersonDatabase extends SQLiteOpenHelper {
    private static final String DB_NAME = "database";
    private static final int DB_VERSION = 1;

    private static final String TABLE_NAME = "persons";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String DEGREE = "degree";
    private static final String HOBBIES = "hobbies";

    public PersonDatabase(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME +"("+
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                NAME +" TEXT,"+
                DEGREE + " TEXT,"+
                HOBBIES + " TEXT);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void savePerson(Person person){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues value = new ContentValues();

        value.put(NAME,person.getName());
        value.put(DEGREE,person.getDegree());
        value.put(HOBBIES,person.getHobbiesString());

        db.insert(TABLE_NAME,null,value);
        db.close();
    }
    public void savePerson(ArrayList<Person> persons){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues value = new ContentValues();
        for (Person person: persons) {
            value.put(NAME,person.getName());
            value.put(DEGREE,person.getDegree());
            value.put(HOBBIES,person.getHobbiesString());

            db.insert(TABLE_NAME,null,value);
            value.clear();
        }

        db.close();
    }
}
