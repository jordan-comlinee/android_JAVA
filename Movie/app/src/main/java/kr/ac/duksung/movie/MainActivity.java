package kr.ac.duksung.movie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String [] movies = {"Chasing Amy","Mallrats", "Dogma", "Clerks",
            "Jay & Silent Bod Strike Back", "Red States", "Cop Out", "Jersey Girl"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView movieList = (ListView) findViewById(R.id.listView);
        // 제네릭 클래스(일반화 클래스), 템플릿 클래스... 메소드에서 사용할 데이터 타입을 확정되지 않은 상태로 정의함.
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, movies);

        movieList.setAdapter(adapter);

        movieList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), movies[i], Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                intent.putExtra("image", i);
                startActivity(intent);
            }
        });
    }
}
