package com.example.banksystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroupOverlay;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListPopupWindow;
import android.widget.TextView;

public class UserData extends AppCompatActivity {

    TextView editTextCard,editTextPin;
    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);

        editTextCard = findViewById(R.id.editTextCardNumber);
        editTextPin = findViewById(R.id.editTextPinNumber);
        btn = findViewById(R.id.btnSignInUserActivity);

        CardView layoutGet = findViewById(R.id.CardNumberCard);
        ViewGroup.LayoutParams layoutParams = layoutGet.getLayoutParams();
        int width = layoutParams.width;

        getWindow().setLayout(width, ListPopupWindow.WRAP_CONTENT);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        getWindow().setAttributes(params);

        ViewGroup root = (ViewGroup) getWindow().getDecorView().getRootView();
        applydim(root);



        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("cardNumber") && intent.hasExtra("pinKey")) {
            String cardNumber = intent.getStringExtra("cardNumber");
            String pinNumber = intent.getStringExtra("pinKey");

            editTextCard.setText(cardNumber);
            editTextPin.setText(pinNumber);
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(UserData.this,SignIn.class);
                startActivity(intent1);
                finish();
            }
        });

    }

    private static void applydim(ViewGroup parent) {
        Drawable dim = new ColorDrawable(Color.TRANSPARENT);
        dim.setBounds(0, 0, parent.getWidth(), parent.getHeight());
        dim.setAlpha(200);
        ViewGroupOverlay overlay = parent.getOverlay();
        overlay.add(dim);
    }
}
