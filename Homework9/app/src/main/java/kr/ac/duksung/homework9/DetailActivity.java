package kr.ac.duksung.homework9;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    Integer[] images = {R.drawable.meal0, R.drawable.meal1, R.drawable.meal2};
    String[] menus = {"Caprese Salad", "Chicken and Potatoes", "Pasta with Meatballs"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        Button moreButton = (Button) findViewById(R.id.moreButton);
        TextView textView = (TextView) findViewById(R.id.textView);
        Intent intent = getIntent();
        Integer position = intent.getIntExtra("image", 0);
        imageView.setImageResource(images[position]);
        textView.setText(menus[position]);

        moreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY, menus[position]);
                startActivity(intent);
            }
        });
    }
}