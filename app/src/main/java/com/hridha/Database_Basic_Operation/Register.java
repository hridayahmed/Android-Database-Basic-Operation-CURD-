package com.hridha.Database_Basic_Operation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Register extends AppCompatActivity {

    EditText sname,susername,sphone,spassword;
    String name,username,phone,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        sname = (EditText) findViewById(R.id.editText3);
        susername = (EditText) findViewById(R.id.editText4);
        sphone = (EditText) findViewById(R.id.editText5);
        spassword = (EditText) findViewById(R.id.editText6);
    }

    public void Onreg(View view){
        name = sname.getText().toString();
        username = susername.getText().toString();
        phone = sphone.getText().toString();
        password = spassword.getText().toString();
        String type = "Register";

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type,name,username,phone,password);
    }
}
