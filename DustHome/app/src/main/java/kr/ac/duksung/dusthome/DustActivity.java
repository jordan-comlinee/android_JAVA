package kr.ac.duksung.dusthome;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DustActivity extends AppCompatActivity {

    TextView textView;
    static RequestQueue requestQueue;
    String currentTime;
    String currentDateTime;
    String currentDate;

    public String currentDateTime(){
        LocalDateTime now = LocalDateTime.now();
        String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmm"));
        return formatedNow;
    }
    public String currentTime() {
        LocalDateTime now = LocalDateTime.now();
        String formatedNow = now.format(DateTimeFormatter.ofPattern("HHmm"));
        return formatedNow;
    }
    public String currentDate() {
        LocalDateTime now = LocalDateTime.now();
        String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        return formatedNow;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dust);
        setTitle("일기예보");

        textView = findViewById(R.id.textView);
        Button button = findViewById(R.id.button);
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        //리퀘스트 전송
        makeRequest();

        //버튼을 누르면 다음 페이지로 넘어감
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SeoulDustActivity.class);
                startActivity(intent);
            }
        });
    }

    public void makeRequest() {
        currentDate = currentDate();
        currentDateTime = currentDateTime();
        currentTime = currentTime();
        if( 600 <= Integer.parseInt(currentTime) && Integer.parseInt(currentTime) < 2400) {
            String url = "http://apis.data.go.kr/1360000/MidFcstInfoService/getMidFcst?serviceKey=5IvhfeF3YQjNafNzytYkOK3vdFXpbBkvimq%2BdVc6RMQ%2B0hwOGJUA1SO%2BaI5vfVlFOlnC7A%2B9j8FJWa6lfOunJw%3D%3D&pageNo=1&numOfRows=10&dataType=JSON&stnId=109&tmFc="
                    + currentDate + "0600";
            StringRequest request = new StringRequest(
                    Request.Method.GET,
                    url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(getApplicationContext(), url.toString(), Toast.LENGTH_LONG).show();
                            android.util.Log.d("testEnv: ", currentTime);
                            parseJson(response);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            textView.setText("에러");
                        }
                    }
            );

            request.setShouldCache(false);
            requestQueue.add(request);

        }
        else {
            textView.setText("not available until 06:00");

        }

    }

    public void println(String data) {
        textView.setText(data);
    }

    public void parseJson(String json) {
        try {
            android.util.Log.d("wfSv: ", "processing");
            JSONObject object1 = new JSONObject(json);
            JSONObject object2 = object1.getJSONObject("response");
            android.util.Log.d("wfSv2: ", object2.toString());
            JSONObject object3 = object2.getJSONObject("body");
            android.util.Log.d("wfSv3: ", object3.toString());
            JSONObject object4 = object3.getJSONObject("items");
            android.util.Log.d("wfSv4: ", object4.toString());
            JSONArray array = object4.getJSONArray("item");
            for(int i=0; i<array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                String wfSv = obj.getString("wfSv");
                println(wfSv);
            }
            android.util.Log.d("testEnv: ", object1.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}