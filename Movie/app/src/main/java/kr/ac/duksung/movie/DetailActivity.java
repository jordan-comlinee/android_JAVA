package kr.ac.duksung.movie;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    Integer[] images = {R.drawable.movie0, R.drawable.movie1, R.drawable.movie2,
            R.drawable.movie3, R.drawable.movie4, R.drawable.movie5,
            R.drawable.movie6, R.drawable.movie7};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        Intent intent = getIntent();
        Integer position = intent.getIntExtra("image", 0);
        imageView.setImageResource(images[position]);
    }
}
