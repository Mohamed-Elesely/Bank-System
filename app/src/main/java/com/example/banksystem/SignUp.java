// SignUp.java

package com.example.banksystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class SignUp extends AppCompatActivity {

    private EditText nameEdit, emailEdit, passEdit;
    private Random random;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Button btnSignUp = findViewById(R.id.btnSignUp);
        TextView textViewSignIn = findViewById(R.id.textViewSignIn);
        nameEdit = findViewById(R.id.editTextName);
        emailEdit = findViewById(R.id.editTextEmail);
        passEdit = findViewById(R.id.editTextPassword);
        random = new Random();
        databaseHelper = new DatabaseHelper(this);

        // Navigate to the SignInActivity
        textViewSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this, SignIn.class);
                startActivity(intent);
            }
        });

        // Handle sign up button click
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });
    }

    private void signUp() {
        String name = nameEdit.getText().toString().trim();
        String email = emailEdit.getText().toString().trim();
        String password = passEdit.getText().toString().trim();

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check if email is in a valid format
        if (!isValidEmail(email)) {
            Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter a password", Toast.LENGTH_SHORT).show();
            return;
        }

        // Generate a random card number
        String cardNumber = generateCardNumber();

        // Generate a random pin key
        String pinKey = generatePinKey();

        // Create a new user with the provided information
        UserModel user = new UserModel(cardNumber, name, email, password, pinKey, 0);

        // Insert the user into the database
        databaseHelper.insertUser(user);

        // Show success message and navigate to the UserDataActivity
        Toast.makeText(this, "User registered successfully", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(SignUp.this, UserData.class);
        intent.putExtra("cardNumber", cardNumber);
        intent.putExtra("pinKey", pinKey);
        startActivity(intent);
        finish();
    }

    private boolean isValidEmail(String email) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        return email.matches(emailPattern);
    }


    private String generateCardNumber() {
        StringBuilder cardNumberBuilder = new StringBuilder();
        for (int i = 0; i < 12; i++) {
            int digit = random.nextInt(10);
            cardNumberBuilder.append(digit);
        }
        return cardNumberBuilder.toString();
    }

    private String generatePinKey() {
        StringBuilder pinKeyBuilder = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            int digit = random.nextInt(10);
            pinKeyBuilder.append(digit);
        }
        return pinKeyBuilder.toString();
    }
}
