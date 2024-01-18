package com.iesfranciscodelosrios.parejas_rafalucenaalejandrogalvez;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        keyEditText = (EditText) findViewById(R.id.keyEditText);
        valueEditText = (EditText) findViewById(R.id.valueEditText);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
    }

    public void saveData(View view) {
        String key = keyEditText.getText().toString().trim();
        String value = valueEditText.getText().toString().trim();

        if (!key.isEmpty() && !value.isEmpty()) {
            if (sharedPreferences.contains(key)) {
                // Key already exists, show a warning message
                Toast.makeText(this, "Key already exists. Choose a different key or update the value.", Toast.LENGTH_SHORT).show();
            } else {
                // Save the data
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(key, value);
                editor.apply();
                Toast.makeText(this, "Data saved successfully", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Empty key or value, show a warning message
            Toast.makeText(this, "Key and value cannot be empty", Toast.LENGTH_SHORT).show();
        }
    }

    public void searchData(View view) {
        String key = keyEditText.getText().toString().trim();

        if (!key.isEmpty()) {
            // Retrieve the value associated with the key
            String value = sharedPreferences.getString(key, "Key not found");
            resultTextView.setText(value);
        } else {
            // Empty key, show a warning message
            Toast.makeText(this, "Enter a key to search", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteData(View view) {
        String key = keyEditText.getText().toString().trim();

        if (!key.isEmpty()) {
            if (sharedPreferences.contains(key)) {
                // Key exists, delete the data
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(key);
                editor.apply();
                Toast.makeText(this, "Data deleted successfully", Toast.LENGTH_SHORT).show();
            } else {
                // Key does not exist, show a warning message
                Toast.makeText(this, "Key not found", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Empty key, show a warning message
            Toast.makeText(this, "Enter a key to delete", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateData(View view) {
        String key = keyEditText.getText().toString().trim();
        String value = valueEditText.getText().toString().trim();

        if (!key.isEmpty() && !value.isEmpty()) {
            if (sharedPreferences.contains(key)) {
                // Key exists, update the value
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(key, value);
                editor.apply();
                Toast.makeText(this, "Data updated successfully", Toast.LENGTH_SHORT).show();
            } else {
                // Key does not exist, show a warning message
                Toast.makeText(this, "Key not found", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Empty key or value, show a warning message
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
