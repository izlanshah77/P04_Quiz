package sg.edu.rp.c346.anything;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView tvResult;
    Button btnInsert, btnRetrieve;
    EditText etName, etGPA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult = findViewById(R.id.result);
        btnInsert = findViewById(R.id.btnInsert);
        btnRetrieve = findViewById(R.id.btnRetrieve);
        etName = findViewById(R.id.etName);
        etGPA = findViewById(R.id.etGPA);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db = new DBHelper(MainActivity.this);

                db.insertStudent(etName.getText().toString(), Double.parseDouble(etGPA.getText().toString()));
                db.close();
            }
        });

        btnRetrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db = new DBHelper(MainActivity.this);

                ArrayList<Student> data = db.getStudents();
                db.close();

                String txt = "";
                for (int i=0;i < data.size();i++){
                    txt += i + ". " + data.get(i).getName() + " " +  data.get(i).getGpa() + "\n";
                }
                tvResult.setText(txt);

            }
        });
    }
}
