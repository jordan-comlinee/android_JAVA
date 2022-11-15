package kr.ac.duksung.advisor;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class StudentActivity extends AppCompatActivity {
    private Document doc;
    ArrayList<String> employees;
    ArrayList<String> employeeNumbers;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        employees = new ArrayList<String>();
        employeeNumbers = new ArrayList<String>();
        ListView listView = (ListView) findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, employees);
        listView.setAdapter(adapter);
        // local server
        //       String urlString = "http://10.0.2.2:8080/FinalProject/advisorPro.jsp";
        String urlString = "http://203.252.213.36:8080/FinalProject/advisorProHome.jsp";
        Intent intent = getIntent();
        String department = intent.getStringExtra("department");

        urlString = urlString + "?dept=" + department;
        JsoupAsyncTask advisorTask = new JsoupAsyncTask();
        advisorTask.execute(urlString);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "사번: " +employeeNumbers.get(i), Toast.LENGTH_LONG).show();
            }
        });

    }

    private class JsoupAsyncTask extends AsyncTask<String, Void, Document> {

        @Override
        protected Document doInBackground(String... params) {
            try {
                doc = Jsoup.connect(params[0]).get();
            } catch (Exception e) {
                Toast.makeText(getBaseContext(), "network error",
                        Toast.LENGTH_SHORT).show();
            }
            return doc;
        }

        @Override
        protected void onPostExecute(Document doc) {
            Elements elements = doc.select("h5");
            Elements employeeNumber = doc.select("h6");
            for(Element element : employeeNumber) {
                employeeNumbers.add(element.text());
            }
            for(Element element : elements) {
                employees.add(element.text());
            }
            adapter.notifyDataSetChanged();
        }

    }
}
