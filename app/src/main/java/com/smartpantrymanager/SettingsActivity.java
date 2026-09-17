package com.smartpantrymanager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    private Button buttonBack;
    private CheckBox checkExpiryReminders;

    private SharedPreferences sharedPreferences;

    private static final String PREFS_NAME = "SmartPantryPreferences";
    private static final String KEY_EXPIRY_REMINDERS = "expiry_reminders";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settings);

        buttonBack = findViewById(R.id.buttonBack);
        checkExpiryReminders = findViewById(R.id.checkExpiryReminders);

        sharedPreferences = getSharedPreferences(
                PREFS_NAME,
                MODE_PRIVATE
        );

        // Load the previously saved setting.
        boolean expiryRemindersEnabled = sharedPreferences.getBoolean(
                KEY_EXPIRY_REMINDERS,
                false
        );

        checkExpiryReminders.setChecked(expiryRemindersEnabled);

        // Save the setting whenever the checkbox changes.
        checkExpiryReminders.setOnCheckedChangeListener(
                (buttonView, isChecked) -> {
                    sharedPreferences.edit()
                            .putBoolean(KEY_EXPIRY_REMINDERS, isChecked)
                            .apply();
                }
        );

        buttonBack.setOnClickListener(view -> finish());
    }
}