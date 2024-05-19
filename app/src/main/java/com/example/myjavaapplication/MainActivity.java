package com.example.myjavaapplication;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private Button button_Convert;
    private EditText textEdit_inputNumber;
    private Spinner s_fromBase;
    private Spinner s_toBase;
    private TextView textView_rezult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        View view = this.getCurrentFocus();

        textView_rezult = findViewById(R.id.TV_Result);
        s_fromBase = findViewById(R.id.S_fromBase);
        s_toBase = findViewById(R.id.S_toBase);
        textEdit_inputNumber = findViewById(R.id.ET_number);
        button_Convert = findViewById(R.id.B_convert);


        s_fromBase.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String number = textEdit_inputNumber.getText().toString();
                int fromBase = Integer.parseInt(s_fromBase.getSelectedItem().toString());
                int toBase = Integer.parseInt(s_toBase.getSelectedItem().toString());

                try {
                    String convertedNumber = convertBase(number, fromBase, toBase);
                    textView_rezult.setText(convertedNumber);
                } catch (Exception e) {
                    textView_rezult.setText("Ошибка: " + e.getMessage());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        s_toBase.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String number = textEdit_inputNumber.getText().toString();
                int fromBase = Integer.parseInt(s_fromBase.getSelectedItem().toString());
                int toBase = Integer.parseInt(s_toBase.getSelectedItem().toString());

                try {
                    String convertedNumber = convertBase(number, fromBase, toBase);
                    textView_rezult.setText(convertedNumber);
                } catch (Exception e) {
                    textView_rezult.setText("Ошибка: " + e.getMessage());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        textEdit_inputNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String number = textEdit_inputNumber.getText().toString();
                int fromBase = Integer.parseInt(s_fromBase.getSelectedItem().toString());
                int toBase = Integer.parseInt(s_toBase.getSelectedItem().toString());

                try {
                    String convertedNumber = convertBase(number, fromBase, toBase);
                    textView_rezult.setText(convertedNumber);
                } catch (Exception e) {
                    textView_rezult.setText("Ошибка: " + e.getMessage());
                }
            }
        });
        button_Convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = textEdit_inputNumber.getText().toString();
                int fromBase = Integer.parseInt(s_fromBase.getSelectedItem().toString());
                int toBase = Integer.parseInt(s_toBase.getSelectedItem().toString());

                try {
                    String convertedNumber = convertBase(number, fromBase, toBase);
                    textView_rezult.setText(convertedNumber);
                } catch (Exception e) {
                    textView_rezult.setText("Ошибка: " + e.getMessage());
                }
            }
        });
    }
    public String convertBase(String number, int fromBase, int toBase) {
        String rez = "";

        int a_number = 0;
        if (fromBase != 10) {
            for (int i = 0; i < number.length(); i++) {
                char digit = number.charAt(i);
                int digitValue;
                if (Character.isDigit(digit)) {
                    digitValue = Character.digit(digit, fromBase);
                } else {
                    // Для букв от A до F
                    digitValue = digit - 'A' + 10;
                }
                a_number += digitValue * Math.pow(fromBase, (number.length() - 1 - i));
            }
        } else {
            a_number = Integer.parseInt(number, fromBase);
        }
        // lol
        while (a_number > toBase - 1) {
            int remainder = a_number % toBase;
            if (remainder < 10) {
                rez += Integer.toString(remainder);
            } else {
                // Для чисел от 10 до 15 (A-F)
                rez += (char) ('A' + remainder - 10);
            }
            a_number /= toBase;
        }
        int remainder = a_number % toBase;
        if (remainder < 10) {
            rez += Integer.toString(remainder);
        } else {
            // Для чисел от 10 до 15 (A-F)
            rez += (char) ('A' + remainder - 10);
        }

        return m_reverse(rez);
    }
    public String m_reverse(String s) {
        int n = s.length();

        String rez = "";

        for (int i = n-1; i >= 0; i--)
        {
            rez += s.charAt(i);
        }

        return rez;
    }
}