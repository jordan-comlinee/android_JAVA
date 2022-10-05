package kr.ac.dukdung.baseappcl;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    Button Button1;
    Button Button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //디자인을 한다고 객체가 만들어지는 게 아니라 setContentView... 함수를 통해 개발자가 디자인한 대로 객체가 만들어짐
        setContentView(R.layout.activity_main);

        //뷰를 아이디로 찾아서 반환함_Button2
        Button1 = (Button) findViewById(R.id.Button1);
        //Button1을 클릭한 경우 Button 클래스의 눌리는 경우를 대비한 메소드가 존재함! 객체를 생성하려면 내부 클래스를 먼저 정의해야 함!! 버튼의 모양은 xml파일을 통해, 버튼의 동작은 java를 통해
        Button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(), "첫번째 버튼을 눌렀어요",
                        Toast.LENGTH_SHORT).show();
            }
        });

        //뷰를 아이디로 찾는다_Button2
        Button2 = (Button) findViewById(R.id.Button2);
        //Button2을 클릭한 경우
        Button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "두번째 버튼을 눌렀어요",
                        Toast.LENGTH_SHORT).show();
            }
        });


    }

}
