package com.fromth;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.Spinner;

import com.fromth.db.SQLiteHelped;
import com.fromth.model.Item;

import java.util.Calendar;

public class UpdateDeleteActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText name;
    private Spinner tacgia;
    private RadioButton ra1, ra2;
    private CheckBox cntt, vt, dt;
    private RatingBar ratingBar;
    private Button btUpdate, btRemove, btBack;
    private Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);
        initView();
        btUpdate.setOnClickListener(this);
        btRemove.setOnClickListener(this);
        btBack.setOnClickListener(this);
        Intent intent = getIntent();
        item = (Item) intent.getSerializableExtra("item");
        name.setText(item.getName());
        int p = 0;
        for (int i = 0; i < tacgia.getCount(); i++) {
            if (tacgia.getItemAtPosition(i).toString().equalsIgnoreCase(item.getItem1())) {
                p = i;
                break;
            }
            tacgia.setSelection(p);
        }
        if(item.getItem2()=="hoc")
        {
            ra1.setChecked(true);
        }else{
            ra2.setChecked(true);
        }
        String s = item.getItem3();
        if(s.contains("1")){
            cntt.setChecked(true);
        }
        if(s.contains("2")){
            vt.setChecked(true);
        }
        if(s.contains("3")){
            dt.setChecked(true);
        }
        ratingBar.setRating(Float.parseFloat(item.getItem4()));

    }

    private void initView() {
        name = findViewById(R.id.enames);
        tacgia= findViewById(R.id.spAlbums);
        ra1 = findViewById(R.id.radio1s);
        ra2= findViewById(R.id.radio2s);
        cntt= findViewById(R.id.checkbox1s);
        vt =findViewById(R.id.checkbox2s);
        dt=findViewById(R.id.checkbox3s);
        ratingBar = findViewById(R.id.ratings);
        btUpdate = findViewById(R.id.btUpdate);
        btBack = findViewById(R.id.btBack);
        btRemove = findViewById(R.id.btRemove);
        tacgia.setAdapter(new ArrayAdapter<String>(this,R.layout.itemspinner,getResources().getStringArray(R.array.album)));
    }

    @Override
    public void onClick(View view) {
        if (view == btBack)
            finish();
        if (view == btUpdate) {
            String names = name.getText().toString();
            String item1 = tacgia.getSelectedItem().toString();
            String item2 = "";
            if(ra1.isChecked())
            {
                item2="hoc";
            }
            if(ra2.isChecked())
            {
                item2="tracuu";
            }
            String item3="";
            if(cntt.isChecked())
            {
                item3="1";
            }
            if(vt.isChecked())
                item3+="2";
            if(dt.isChecked())
                item3+="3";
            String item4 = ""+ratingBar.getRating();
            if( !(names.isEmpty() && item1.matches("\\d+")))
            {
                int id = item.getId();
                Item i = new Item(id, names, item1, item2, item3, item4);
                SQLiteHelped db = new SQLiteHelped(this);
                db.updateItem(i);
                finish();
            }
        }
        if(view==btRemove)
        {
            int id = item.getId();
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setTitle("Xac nhan xoa");
            builder.setMessage("Ban chac chan muon xoa "+ item.getName()+" khong?");
            builder.setPositiveButton("Co", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    SQLiteHelped db = new SQLiteHelped(getApplicationContext());
                    db.delete(id);
                    finish();
                }
            });
            builder.setNegativeButton("Khong", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }

    }

}