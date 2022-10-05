package kr.ac.duksung.homework5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editNumber1, editNumber2;
    Button button;
    RadioButton btnPlus, btnMinus, btnMul, btnSplit;
    String num1, num2, operator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editNumber1 = (EditText) findViewById(R.id.editTextNumber1);
        editNumber2 = (EditText) findViewById(R.id.editTextNumber2);
        button = (Button) findViewById(R.id.button);
        btnPlus = (RadioButton) findViewById(R.id.radioPlus);
        btnMinus = (RadioButton) findViewById(R.id.radioMinus);
        btnMul = (RadioButton) findViewById(R.id.radioMultiply);
        btnSplit = (RadioButton) findViewById(R.id.radioSplit);
        
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operator = "+";
            }
        });
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operator = "-";
            }
        });
        btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operator = "*";
            }
        });
        btnSplit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operator = "/";
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1 = editNumber1.getText().toString();
                num2 = editNumber2.getText().toString();

                if(num1.equals("")) {
                    //toast
                    Toast.makeText(getApplicationContext(), "첫번째 정수 입력", Toast.LENGTH_LONG).show();
                }
                if(operator.equals("")) {
                    Toast.makeText(getApplicationContext(), "연산자 입력", Toast.LENGTH_LONG).show();
                }
                if(num2.equals("")) {
                    //toast
                    Toast.makeText(getApplicationContext(), "두번째 정수 입력", Toast.LENGTH_LONG).show();
                }


            }

        });

    }
}