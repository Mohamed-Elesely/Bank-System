package com.example.banksystem;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager{ private static SessionManager instance;
        private SharedPreferences sharedPreferences;
        private static final String PREF_NAME = "session";
        private static final String KEY_CARD_NUMBER = "card_number";

        private SessionManager(Context context) {
            sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        }

        public static synchronized SessionManager getInstance(Context context) {
            if (instance == null) {
                instance = new SessionManager(context);
            }
            return instance;
        }

        public void setCardNumber(String cardNumber) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(KEY_CARD_NUMBER, cardNumber);
            editor.apply();
        }

        public String getCardNumber() {
            return sharedPreferences.getString(KEY_CARD_NUMBER, "");
        }

    public void clearSession() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}