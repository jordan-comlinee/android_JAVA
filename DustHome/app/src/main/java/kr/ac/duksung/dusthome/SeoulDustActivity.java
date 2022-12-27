package kr.ac.duksung.dusthome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class SeoulDustActivity extends AppCompatActivity {

    TextView currentTime;
    TextView information;
    ArrayList<String> seoulArea;
    ArrayList<String> AreaDustInformation;
    ArrayAdapter<String> adapter;
    static RequestQueue requestQueue;
    String currentDateTime;

    public String currentDateTime(){
        LocalDateTime now = LocalDateTime.now();
        String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 기준"));
        return formatedNow;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seoul_dust);
        setTitle("자치구별 대기 환경 오염 정보");


        currentTime = (TextView) findViewById(R.id.currentTime);
        information = (TextView) findViewById(R.id.information);


        //서울시 내 지역 불러오기
        ListView seoulAreaList = (ListView) findViewById(R.id.listView);
        seoulArea = new ArrayList<String>();
        AreaDustInformation = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, seoulArea);
        seoulAreaList.setAdapter(adapter);


        seoulAreaList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                information.setText(AreaDustInformation.get(i));
            }
        });

        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        // 현재 시각을 알려준다
        currentDateTime = currentDateTime();
        currentTime.setText(currentDateTime);

        // 공공데이터로부터 가져온다
        makeRequest();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem refresh) {
        switch (refresh.getItemId()) {
            case R.id.refresh:
                seoulArea.clear();
                AreaDustInformation.clear();
                adapter.clear();
                makeRequest();
                Toast.makeText(getApplicationContext(), "새로고침 완료", Toast.LENGTH_LONG).show();
        }
        return false;
    }

    public void makeRequest() {
        String url = "http://openapi.seoul.go.kr:8088/476347726b646c743131354e6c454468/json/" +
                "ListAirQualityByDistrictService/1/25/";
        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        parseJson(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        AreaDustInformation.add("에러");
                    }
                }
        );

        request.setShouldCache(false);
        requestQueue.add(request);
    }

    public void parseJson(String json) {
        try {
            JSONObject object1 = new JSONObject(json);
            JSONObject object2 = object1.getJSONObject("ListAirQualityByDistrictService");
            JSONArray array = object2.getJSONArray("row");
            for(int i=0; i<array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                android.util.Log.d("getJSON: ", obj.toString());
                String ku = obj.getString("MSRSTENAME");
                seoulArea.add(ku);
                String ozone = obj.getString("OZONE");
                String nitrogen = obj.getString("NITROGEN");
                String carbon = obj.getString("CARBON");
                String sulfurous = obj.getString("SULFUROUS");
                String pm10 = obj.getString("PM10");
                String pm25 = obj.getString("PM25");
                String grade = obj.getString("GRADE");
                AreaDustInformation.add("오존: " + ozone+ "\n이산화질소: " + nitrogen + "\n이산화탄소: " + carbon + "\n아황산가스: " +
                        sulfurous + "\n미세먼지: " + pm10 + "\n초미세먼지: " + pm25 + "\n통합대기환경지수 등급: " + grade);
            }
            adapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}