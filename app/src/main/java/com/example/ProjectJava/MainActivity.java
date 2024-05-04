package com.example.ProjectJava;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {
    sqlhelper sqlHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sqlHelper =new sqlhelper(MainActivity.this);
        setContentView(R.layout.signup);

    }

    public void enregistrer(View view) {
        EditText username = findViewById(R.id.username);
        String Vusername= username.getText().toString();
        EditText email = findViewById(R.id.email);
        String vemail= email.getText().toString();
        EditText password = findViewById(R.id.password);
        String vpassword= password.getText().toString();
        sqlHelper.insert(Vusername,vemail,vpassword);
        Toast.makeText(this,"your account has been created",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(MainActivity.this, signinactivity.class);
        startActivity(intent);
    }
}