package kr.ac.duksung.homework9;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    /*
    String [] meals = {"Caprese Salad", "Chicken and Potatoes", "Pasta with Meatballs"};
    */

    ArrayList<String> meals;
    String removedMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        meals = new ArrayList<String>();
        meals.add("Caprese Salad");
        meals.add("Chicken and Potatoes");
        meals.add("Pasta with Meatballs");

        ListView mealList = (ListView) findViewById(R.id.listView);
        // 제네릭 클래스(일반화 클래스), 템플릿 클래스... 메소드에서 사용할 데이터 타입을 확정되지 않은 상태로 정의함.
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, meals);

        mealList.setAdapter(adapter);

        mealList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), meals.get(i), Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                intent.putExtra("num", i);
                intent.putExtra("meals", meals);
                //intent.putStringArrayListExtra("arrayList", meals);
                startActivity(intent);
            }
        });

        Button addBtn = (Button) findViewById(R.id.button);
        final EditText addEdit = (EditText) findViewById(R.id.editText);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                meals.add(addEdit.getText().toString());
                adapter.notifyDataSetChanged();
            }
        });

        Button returnBtn = (Button) findViewById(R.id.buttonReturn);
        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                meals.add(removedMenu);
                adapter.notifyDataSetChanged();
            }
        });


        mealList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int which = i;
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                dlg.setTitle("삭제?");
                dlg.setMessage(meals.get(i));
                dlg.setNegativeButton("취소", null);
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        removedMenu = meals.get(which);
                        meals.remove(which);
                        adapter.notifyDataSetChanged();
                    }
                });
                dlg.show();
                return true;
            }
        });

    }
}