package com.example.banksystem;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
public class AccountMangment extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private TextView textViewBalance;
    private EditText editTextWithdrawAmount;
    private EditText editTextDepositAmount;
    private SessionManager sessionManager;
    private String cardNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_mangment);

        databaseHelper = new DatabaseHelper(this);
        sessionManager = SessionManager.getInstance(this);
        cardNumber = sessionManager.getCardNumber();

        textViewBalance = findViewById(R.id.textViewBalance);
        editTextWithdrawAmount = findViewById(R.id.editTextWithdrawAmount);
        editTextDepositAmount = findViewById(R.id.editTextDepositAmount);

        updateBalanceDisplay();

        Button btnWithdraw = findViewById(R.id.btnWithdraw);
        btnWithdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                withdraw();
            }
        });

        Button btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Clear the session and navigate back to the SignIn activity
                SessionManager.getInstance(getApplicationContext()).clearSession();
                Intent intent = new Intent(AccountMangment.this, SignIn.class);
                startActivity(intent);
                finish();
            }
        });

        Button btnDeposit = findViewById(R.id.btnDeposit);
        btnDeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deposit();
            }
        });
    }

    private void updateBalanceDisplay() {
        double balance = databaseHelper.getBalance(cardNumber);
        textViewBalance.setText("Current Balance: $" + balance);
    }

    private void withdraw() {
        String withdrawAmountStr = editTextWithdrawAmount.getText().toString().trim();

        if (!withdrawAmountStr.isEmpty()) {
            double withdrawAmount = Double.parseDouble(withdrawAmountStr);
            double currentBalance = databaseHelper.getBalance(cardNumber);

            if (withdrawAmount <= currentBalance) {
                double updatedBalance = currentBalance - withdrawAmount;
                databaseHelper.updateBalance(cardNumber, updatedBalance);
                updateBalanceDisplay();
                Toast.makeText(this, "Withdrawal successful", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Insufficient balance", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Please enter a withdrawal amount", Toast.LENGTH_SHORT).show();
        }
    }

    private void deposit() {
        String depositAmountStr = editTextDepositAmount.getText().toString().trim();

        if (!depositAmountStr.isEmpty()) {
            double depositAmount = Double.parseDouble(depositAmountStr);
            double currentBalance = databaseHelper.getBalance(cardNumber);

            double updatedBalance = currentBalance + depositAmount;
            databaseHelper.updateBalance(cardNumber, updatedBalance);
            updateBalanceDisplay();
            Toast.makeText(this, "Deposit successful", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Please enter a deposit amount", Toast.LENGTH_SHORT).show();
        }
    }
}

