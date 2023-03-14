package com.example.projectfikli;

import androidx.appcompat.app.AppCompatActivity;
import org.mariuszgromada.math.mxparser.*;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.input);
        display.setShowSoftInputOnFocus(false);
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getString(R.string.display).equals(display.getText().toString())){
                    display.setText("");
                }
            }
        });
    }
    private void updateText(String strToAdd){
        String oldStr = display.getText().toString();
        int cursorPos = display.getSelectionStart();
        String leftStr = oldStr.substring(0, cursorPos);
        String rightStr = oldStr.substring(cursorPos);
        if (getString(R.string.display).equals(display.getText().toString())){
            display.setText(strToAdd);
            display.setSelection(cursorPos + 1);
        }
        else {
            display.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr));
            display.setSelection(cursorPos + 1);
        }
    }

    public void void_zeroBTN(View view){
        updateText("0");
    }
    public void void_oneBTN(View view){
        updateText("1");
    }
    public void void_twoBTN(View view){
        updateText("2");
    }
    public void void_threeBTN(View view){
        updateText("3");
    }
    public void void_fourBTN(View view){
        updateText("4");
    }
    public void void_fiveBTN(View view){
        updateText("5");
    }
    public void void_sixBTN(View view){
        updateText("6");
    }
    public void void_sevenBTN(View view){
        updateText("7");
    }
    public void void_eightBTN(View view){
        updateText("8");
    }
    public void void_nineBTN(View view){
        updateText("9");
    }
    public void void_clearBTN(View view){
        display.setText("");
    }
    public void void_pangkatBTN(View view){
        updateText("^");
    }
    public void void_kurungBTN(View view){
        int cursorPos = display.getSelectionStart();
        int openPar = 0;
        int closedPar = 0;
        int textLen = display.getText().length();
        for (int i = 0; i < cursorPos; i++){
            if (display.getText().toString().substring(i, i+1).equals("(")){
                openPar += 1;
            }
            if (display.getText().toString().substring(i, i+1).equals(")")){
                closedPar += 1;
            }
        }
        if(openPar == closedPar || display.getText().toString().substring(textLen-1, textLen).equals("(")){
            updateText("(");
        }
        else if(closedPar < openPar && !display.getText().toString().substring(textLen-1, textLen).equals("(")){
            updateText(")");
        }
        display.setSelection(cursorPos + 1);
    }
    public void void_bagiBTN(View view){
        updateText("÷");
    }
    public void void_kaliBTN(View view){
        updateText("×");
    }
    public void void_kurangBTN(View view){
        updateText("-");
    }
    public void void_tambahBTN(View view){
        updateText("+");
    }
    public void void_plusMinusBTN(View view){
        updateText("-");
    }
    public void void_pointBTN(View view){
        updateText(".");
    }
    public void void_hasilBTN(View view){
        String userExp = display.getText().toString();
        userExp = userExp.replaceAll("÷", "/");
        userExp = userExp.replaceAll("×", "*");

        Expression exp = new Expression(userExp);

        String result = String.valueOf(exp.calculate());

        display.setText(result);
        display.setSelection(result.length());

    }
    public void void_backspaceBTN(View view){
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();
        if (cursorPos != 0 && textLen !=0){
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.replace(cursorPos - 1, cursorPos, "");
            display.setText(selection);
            display.setSelection(cursorPos - 1);
        }
    }
}