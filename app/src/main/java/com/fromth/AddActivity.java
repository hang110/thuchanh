package com.fromth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.Spinner;

import com.fromth.db.SQLiteHelped;
import com.fromth.model.Item;


public class AddActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText name;
    private Spinner tacgia;
    private RadioButton ra1, ra2;
    private CheckBox cntt, vt, dt;
    private RatingBar ratingBar;
    private Button btA, btC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initView();
        btA.setOnClickListener(this);
        btC.setOnClickListener(this);
    }

    private void initView() {
        name = findViewById(R.id.ename);
        tacgia= findViewById(R.id.spAlbum);
        ra1 = findViewById(R.id.radio1);
        ra2= findViewById(R.id.radio2);
        cntt= findViewById(R.id.checkbox1);
        vt =findViewById(R.id.checkbox2);
        dt=findViewById(R.id.checkbox3);
        ratingBar = findViewById(R.id.rating);
        btA = findViewById(R.id.btAdd);
        btC = findViewById(R.id.btCancel);
        tacgia.setAdapter(new ArrayAdapter<String>(this,R.layout.itemspinner,getResources().getStringArray(R.array.album)));
    }

    @Override
    public void onClick(View view) {
        if(view==btC)
            finish();
        if (view == btA)
        {
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
            if( !(names.isEmpty()))
            {
                Item i = new Item(names, item1, item2, item3, item4);
                SQLiteHelped db = new SQLiteHelped(this);
                db.addItem(i);
                finish();
            }
        }
    }
}
