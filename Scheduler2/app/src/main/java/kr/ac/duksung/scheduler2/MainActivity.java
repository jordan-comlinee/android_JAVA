package kr.ac.duksung.scheduler2;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    ActivityResultLauncher<Intent> launcher;                            // launcher 객체 참조변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("일정관리");

        Button button = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddActivity.class);
                //        startActivityForResult(intent, 0);            // deprecated
                launcher.launch(intent);                // launcher 객체의 launch 메서드로 인텐트 호출
            }
        });
/*
        launcher = registerForActivityResult(                           // launcher 객체 생성
                new ActivityResultContracts.StartActivityForResult(),   // Contract 객체 파라미터
                new ActivityResultCallback<ActivityResult>() {          // Callback 객체 파라미터
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            Intent intent = result.getData();
                            String data = intent.getStringExtra("appointment");
                            textView.append(data + "\n");
                        }
                    }
                });
 */
        // Callback 람다 사용
        launcher = registerForActivityResult(                           // launcher 객체 생성
                new ActivityResultContracts.StartActivityForResult(),   // Contract 객체 파라미터
                result -> {                                             // Callback 람다 파라미터
                    if (result.getResultCode() == RESULT_OK) {
                        Intent intent = result.getData();
                        String data = intent.getStringExtra("appointment");
                        textView.append(data + "\n");
                    }
                });
    }

    /*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            String result = data.getStringExtra("appointment");
            textView.append(result + "\n");
        }
    }
     */
}
