package kr.ac.duksung.dusthome;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class MainActivity extends AppCompatActivity {
    EditText idText;
    EditText passwordText;
    static RequestQueue requestQueue;
    static boolean loginMatch=false;

    public void makeRequest() {
        //idText.getText().toString()
        String url = "http://203.252.213.36:8080/FinalProject/loginPro.jsp?id=" + idText.getText().toString() + "&passwd=" + passwordText.getText().toString();
        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        login(response);
                        android.util.Log.d("testloginMatchRequest: ", Boolean.toString(loginMatch));

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "network error",
                                Toast.LENGTH_LONG).show();
                    }
                }
        );

        request.setShouldCache(false);
        requestQueue.add(request);
    }



    public void login(String url) {
        Document doc = Jsoup.parse(url);
        android.util.Log.d("testurl: ", doc.text());

        if(doc.text().equals("1")) {
            Toast.makeText(getBaseContext(), "로그인 성공", Toast.LENGTH_LONG).show();
            Intent intent1 = new Intent(getApplicationContext(), DustActivity.class);
            startActivity(intent1);
        }
        else {
            AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
            dlg.setTitle("Login Failed!");
            dlg.setMessage("Please try Again.");
            dlg.setPositiveButton("확인", null);
            dlg.show();

            loginMatch = false;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("로그인");

        Button button = (Button) findViewById(R.id.loginButton);
        idText = (EditText) findViewById(R.id.id);
        passwordText = (EditText) findViewById(R.id.password);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestQueue = Volley.newRequestQueue(getApplicationContext());
                makeRequest();
                android.util.Log.d("testloginMatchonClick: ", Boolean.toString(loginMatch));
            }
        });


    }


}