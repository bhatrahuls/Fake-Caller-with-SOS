package com.example.app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CallerInfo extends AppCompatActivity {

    Button b3,b6;
    EditText b4;
    EditText b5;
    DatabaseHelper2 DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caller_info);

        b3=(Button)findViewById(R.id.save);
        b6=(Button)findViewById(R.id.show);
        b4=(EditText)findViewById(R.id.cname);
        b5=(EditText)findViewById(R.id.phno);
        DB = new DatabaseHelper2(this);


        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cn = b4.getText().toString();
                String ccontact = b5.getText().toString();

                if (!b5.getText().toString().isEmpty()) {
                    Boolean check = DB.insertcdata(cn, ccontact);
                    if(check ==true)
                        Toast.makeText(CallerInfo.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(CallerInfo.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(CallerInfo.this, "Please fill the number field atleast", Toast.LENGTH_SHORT).show();
                }
            }
        });

        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.contactgetdata();
                if(res.getCount()==0){
                    Toast.makeText(CallerInfo.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Name :"+res.getString(0)+"\n");
                    buffer.append("PhNo :"+res.getString(1)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(CallerInfo.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }        });
    }
}