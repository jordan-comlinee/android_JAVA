package kr.ac.duksung.homework5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editNumber1, editNumber2;
    Button button;
    String num1, num2, operator;
    RadioGroup radiogroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editNumber1 = (EditText) findViewById(R.id.editTextNumber1);
        editNumber2 = (EditText) findViewById(R.id.editTextNumber2);
        button = (Button) findViewById(R.id.button);
        radiogroup = (RadioGroup) findViewById(R.id.radioGroup);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1 = editNumber1.getText().toString();
                num2 = editNumber2.getText().toString();

                if(num1.equals("")) {
                    //toast
                    Toast.makeText(getApplicationContext(), "첫번째 정수 입력", Toast.LENGTH_LONG).show();
                }
                switch(radiogroup.getCheckedRadioButtonId()) {
                    case R.id.radioPlus:
                        operator = "+";
                        break;
                    case R.id.radioMinus:
                        operator = "-";
                        break;
                    case R.id.radioMultiply:
                        operator = "*";
                        break;
                    case R.id.radioSplit:
                        operator = "/";
                        break;
                    default:
                        Toast.makeText(getApplicationContext(), "연산자 입력", Toast.LENGTH_SHORT).show();
                }
                if(num2.equals("")) {
                    //toast
                    Toast.makeText(getApplicationContext(), "두번째 정수 입력", Toast.LENGTH_LONG).show();
                }
                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                intent.putExtra("num1", editNumber1.getText().toString());
                intent.putExtra("num2", editNumber2.getText().toString());
                intent.putExtra("operator", operator);
                startActivity(intent);
            }

        });

    }
}