package kr.ac.dukdung.homework1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button buttonText;
    Button buttonHomepage;
    RadioButton radioButtonOreo;
    RadioButton radioButtonPie;
    ImageView imageAndroid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editTextView);
        buttonText = (Button) findViewById(R.id.buttonText);
        buttonHomepage = (Button) findViewById(R.id.buttonHomepage);
        radioButtonOreo = (RadioButton) findViewById(R.id.radioButtonOreo);
        radioButtonPie = (RadioButton) findViewById(R.id.radioButtonPie);
        imageAndroid = (ImageView) findViewById(R.id.imageAndroid);


        buttonText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String toastStr = editText.getText().toString();
                toastStr = toastStr.trim();
                Toast.makeText(getApplicationContext(), toastStr, Toast.LENGTH_LONG).show();
            }
        });

        buttonHomepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uriStr = editText.getText().toString();
                Uri uri = Uri.parse(uriStr);
                uriStr = uriStr.trim();
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        radioButtonOreo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageAndroid.setVisibility(View.VISIBLE);
                imageAndroid.setImageResource(R.drawable.oreo);
            }
        });

        radioButtonPie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageAndroid.setVisibility(View.VISIBLE);
                imageAndroid.setImageResource(R.drawable.pie);
            }
        });



    }
}