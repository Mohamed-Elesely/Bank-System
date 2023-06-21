package com.example.banksystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignIn extends AppCompatActivity {

    private EditText numbersEditText;
    private EditText pinEditText;
    private Button signInButton;
    private TextView textViewSignUp;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        numbersEditText = findViewById(R.id.editTextName);
        pinEditText = findViewById(R.id.editTextEmail);
        signInButton = findViewById(R.id.btnSignUp);
        textViewSignUp = findViewById(R.id.textViewSignUp);

        sessionManager = SessionManager.getInstance(this);

        // Handle Sign Up TextView click
        textViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the SignUp activity
                Intent intent = new Intent(SignIn.this, SignUp.class);
                startActivity(intent);
            }
        });

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numbers = numbersEditText.getText().toString().trim();
                String pin = pinEditText.getText().toString().trim();

                // Perform validation by querying the database
                if (!isValidKey(numbers, pin)) {
                    Toast.makeText(SignIn.this, "Invalid key combination", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Store the card number in the session
                sessionManager.setCardNumber(numbers);

                // Show the AccountManagement activity
                Intent accountManagementIntent = new Intent(SignIn.this, AccountMangment.class);
                startActivity(accountManagementIntent);
                finish();
            }
        });
    }

    private boolean isValidKey(String numbers, String pin) {
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        return databaseHelper.isValidKeyCombination(numbers, pin);
    }
}
