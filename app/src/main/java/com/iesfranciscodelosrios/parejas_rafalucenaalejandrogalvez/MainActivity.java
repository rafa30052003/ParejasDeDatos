package com.iesfranciscodelosrios.parejas_rafalucenaalejandrogalvez;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.OrientationEventListener;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private EditText keyEditText, valueEditText;
    private TextView resultTextView;

    private OrientationEventListener orientationEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        getWindow().setSoftInputMode(android.view.WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        keyEditText = findViewById(R.id.keyEditText);
        valueEditText = findViewById(R.id.valueEditText);
        resultTextView = findViewById(R.id.resultTextView);
    }



    public void saveData(View view) {
        String key = keyEditText.getText().toString().trim();
        String value = valueEditText.getText().toString().trim();

        if (!key.isEmpty() && !value.isEmpty()) {
            if (sharedPreferences.contains(key)) {
                Toast.makeText(this, "Key already exists. Choose a different key or update the value.", Toast.LENGTH_SHORT).show();
            } else {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(key, value);
                editor.apply();
                Toast.makeText(this, "Data saved successfully", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Key and value cannot be empty", Toast.LENGTH_SHORT).show();
        }
    }

    public void searchData(View view) {
        String key = keyEditText.getText().toString().trim();

        if (!key.isEmpty()) {

            String value = sharedPreferences.getString(key, "Key not found");
            resultTextView.setText(value);
        } else {
            Toast.makeText(this, "Enter a key to search", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteData(View view) {
        String key = keyEditText.getText().toString().trim();

        if (!key.isEmpty()) {
            if (sharedPreferences.contains(key)) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(key);
                editor.apply();
                Toast.makeText(this, "Data deleted successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Key not found", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Enter a key to delete", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateData(View view) {
        String key = keyEditText.getText().toString().trim();
        String value = valueEditText.getText().toString().trim();

        if (!key.isEmpty() && !value.isEmpty()) {
            if (sharedPreferences.contains(key)) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(key, value);
                editor.apply();
                Toast.makeText(this, "Data updated successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Key not found", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Key and value cannot be empty", Toast.LENGTH_SHORT).show();
        }
    }

    public void listData(View view) {
        Map<String, ?> allEntries = sharedPreferences.getAll();
        StringBuilder list = new StringBuilder();

        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            list.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }

        resultTextView.setText(list.toString());
    }
}
