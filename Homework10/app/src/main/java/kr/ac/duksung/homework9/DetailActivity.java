package kr.ac.duksung.homework9;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        Button moreButton = (Button) findViewById(R.id.moreButton);
        TextView textView = (TextView) findViewById(R.id.textView);
        Intent intent = getIntent();
        Integer i = intent.getIntExtra("num", 0);
        ArrayList<String> meals = intent.getStringArrayListExtra("meals");
        textView.setText(meals.get(i)+" 선택!\n추가 정보를 원하시면 more 버튼 클릭!");

        moreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                //intent.putStringArrayListExtra(SearchManager.QUERY, meals.get(i));
                intent.putExtra(SearchManager.QUERY, meals.get(i));
                startActivity(intent);
            }
        });
    }
}