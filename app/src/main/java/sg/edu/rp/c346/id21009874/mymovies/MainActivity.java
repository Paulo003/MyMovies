package sg.edu.rp.c346.id21009874.mymovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    TextView tvMovie;
    TextView tvgenre;
    TextView tvYear;
    TextView tvRating;
    Spinner spinner;
    EditText etMovie, etYear, etGenre;
    Button btnInsert;
    Button btnShow;
    ArrayList <String> al;
    ArrayList <String> aa;
    Movie data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvMovie = findViewById(R.id.textViewMovieTitle);
        tvgenre = findViewById(R.id.textViewGenre);
        tvYear = findViewById(R.id.textViewYear);
        tvRating = findViewById(R.id.textViewRating);
        btnInsert = findViewById(R.id.buttonInsert);
        btnShow = findViewById(R.id.buttonShow);
        etMovie = findViewById(R.id.editMovieName);
        etYear = findViewById(R.id.editTextYear);
        etGenre = findViewById(R.id.editTextGenre);
        spinner = findViewById(R.id.spinner);

        al = new ArrayList<>(Arrays.asList("Select movie rating","G", "PG", "PG13", "NC16", "M18", "R21"));
        aa = new ArrayList<String>(this, android.R.layout.simple_spinner_item, al);
        spinner.setAdapter(aa);

        Intent i = getIntent();
        data = (Movie) i.getSerializableExtra("data");

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbh = new DBHelper(MainActivity.this);
                String title = etMovie.getText().toString();
                String genre = etGenre.getText().toString();
                int year = Integer.parseInt(etYear.getText().toString());
                String rating =spinner.getSelectedItem().toString();
                if (title.length() == 0 || genre.length() == 0){
                    Toast.makeText(MainActivity.this, "Incomplete data", Toast.LENGTH_SHORT).show();
                    return;
                }
                long inserted_id = dbh.insertMovie(title,genre,year,rating);
                if (inserted_id != -1){
                    Toast.makeText(MainActivity.this, "Insert successful",
                            Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Insert unsuccessful",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ShowMovies.class);
                startActivity(i);
            }
        });
    }
    }
}