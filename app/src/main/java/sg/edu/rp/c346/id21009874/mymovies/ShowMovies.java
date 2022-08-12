package sg.edu.rp.c346.id21009874.mymovies;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ShowMovies extends AppCompatActivity {
    ListView lvShow;
    ArrayList<Movie> alMovieList;
    CustomAdapter caMovie;
    Button butShowList;
    @Override
    protected void onResume() {
        super.onResume();
        DBHelper dbh = new DBHelper(this);
        alMovieList.clear();
        alMovieList.addAll(dbh.getAllMovies());
        caMovie.notifyDataSetChanged();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_movies);

        lvShow =findViewById(R.id.lvShow);
        butShowList = findViewById(R.id.butShowList);
        alMovieList = new ArrayList<>();

        caMovie = new CustomAdapter(this,R.layout.row,alMovieList);
        lvShow.setAdapter(caMovie);

        DBHelper dbh = new DBHelper(ShowMovies.this);
        alMovieList.clear();
        alMovieList.addAll(dbh.getAllMovies());
        caMovie.notifyDataSetChanged();

        lvShow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent i = new Intent (ShowMovies.this, ModifyMovie.class);
                i.putExtra("movie", alMovieList.get(position));
                startActivity(i);
            }
        });
        butShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ShowMovies.this);
                alMovieList.clear();
                alMovieList.addAll(dbh.getAllMovies());
                caMovie.notifyDataSetChanged();

            }
        });

    }

}
