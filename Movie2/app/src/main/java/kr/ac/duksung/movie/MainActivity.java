package kr.ac.duksung.movie;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
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

    ArrayList<String> movies;
    /*
    String [] movies = {"Chasing Amy","Mallrats", "Dogma", "Clerks",
            "Jay & Silent Bod Strike Back", "Red States", "Cop Out", "Jersey Girl"};
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movies = new ArrayList<String>();
        //가변으로 넣을 수 있도록 dd함...
        movies.add("Chasing Amy"); movies.add("Mallrats"); movies.add("Dogma");
        movies.add("Clerks"); movies.add("Jay & Silent Bod Strike Back");
        movies.add("Red States"); movies.add("Cop Out"); movies.add("Jersey Girl");

        ListView movieList = (ListView) findViewById(R.id.listView);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, movies);

        movieList.setAdapter(adapter);

        movieList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), movies.get(i), Toast.LENGTH_LONG).show();
                /* movies[i] */

                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY, movies.get(i));
                startActivity(intent);
            }
        });

        Button addBtn = (Button) findViewById(R.id.button);
        final EditText addEdit = (EditText) findViewById(R.id.editText);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movies.add(addEdit.getText().toString());
                adapter.notifyDataSetChanged();
            }
        });

        movieList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int which = i;
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                dlg.setTitle("삭제?");
                dlg.setMessage(movies.get(i));
                dlg.setNegativeButton("취소", null);
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        movies.remove(which);
                        adapter.notifyDataSetChanged();
                    }
                });
                dlg.show();
                return true;
            }
        });
    }
}
