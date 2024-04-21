package com.example.projectjava;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ProjectJava.R;

public class EditProfileActivity extends AppCompatActivity {
    private sqlHelper dbHelper;
    private EditText editTextUsername, editTextEmail, editTextPassword;
    private String userId; // This should be passed to this activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile);

        dbHelper = new sqlHelper(this);
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);

        // Load user data from database
        loadData();
        // Set the toolbar as the app bar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Show the up button in the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Button buttonUpdate = findViewById(R.id.buttonUpdate);
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUserData();
            }
        });





    }
    @Override
    public boolean onSupportNavigateUp() {
        // When the up button is pressed, finish the current activity
        onBackPressed();
        return true;
    }
    private void loadData() {
        // You would typically fetch this from the database
        // For now, let's simulate with placeholder values
        editTextUsername.setText("JohnDoe");
        editTextEmail.setText("johndoe@example.com");
        editTextPassword.setText("password123");
    }

    private void updateUserData() {
        String username = editTextUsername.getText().toString();
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        boolean updateSuccessful = dbHelper.updateUserInfo(userId, username, email, password);

        if(updateSuccessful) {
            Toast.makeText(this, "Profile Updated Successfully", Toast.LENGTH_SHORT).show();
            finish(); // close this activity and return to the previous activity (or wherever appropriate)
        } else {
            Toast.makeText(this, "Failed to Update Profile", Toast.LENGTH_SHORT).show();
        }
    }
}

