package sg.edu.rp.c346.id21009874.mymovies;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ModifyMovie extends AppCompatActivity {
    TextView tvTitle, tvId, tvGenere, tvYear, TvRating, tvSpinner;
    EditText etTitle, etId, etGenere, etYear, etRating;
    Spinner spinRating;
    Button btnDelete, btnCancel, btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify_movie);

        //setMovie(getMovie().toString() + " ~ "+ "Modify Movie");
        btnCancel = findViewById(R.id.btnCancel);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        etTitle = findViewById(R.id.etTitle);
        etId = findViewById(R.id.etID);
        etGenere = findViewById(R.id.etGenre);
        etYear = findViewById(R.id.etYear);
        spinRating = findViewById(R.id.spinRating2);

        Intent i = getIntent();
        final Movie currentMovie = (Movie) i.getSerializableExtra("movie");
        etId.setText(currentMovie.getId() + " ");
        etTitle.setText(currentMovie.getTitle());
        etGenere.setText(currentMovie.getGenre());
        //spinRating.setAdapter(currentMovie.getRating());
        etYear.setText(currentMovie.getYear() + " ");


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(ModifyMovie.this);
                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to discard the changes");
                myBuilder.setCancelable(false);

                myBuilder.setPositiveButton("DISCARD", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DBHelper dbh = new DBHelper(ModifyMovie.this);

                        currentMovie.setTitle(etTitle.getText().toString().trim());
                        currentMovie.setGenre(etGenere.getText().toString().trim());
                        int Year = 0;
                        try {
                            Year = Integer.parseInt(etYear.getText().toString().trim());
                        }catch (Exception e){
                            Toast.makeText(ModifyMovie.this, "Invalid year", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        currentMovie.setYear(Year);
                        int result= dbh.updateMovie(currentMovie);
                        Toast.makeText(ModifyMovie.this, "Movie discarded", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
                myBuilder.setNegativeButton("DO NOT DISCARD",null );
                    AlertDialog myDialog = myBuilder.create();
                    myDialog.show();


                }
            });


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder myBuilder = new AlertDialog.Builder(ModifyMovie.this);
                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to delete the movie " + etTitle.getText());
                myBuilder.setCancelable(false);

                myBuilder.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ModifyMovie.this, "Movie deleted", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
                myBuilder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder myBuilder = new AlertDialog.Builder(ModifyMovie.this);
                        myBuilder.setTitle("Danger");
                        myBuilder.setMessage("Are you sure you want to discard the changes");
                        myBuilder.setCancelable(false);

                        myBuilder.setPositiveButton("DISCARD", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(ModifyMovie.this, "Movie discarded", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        });
                        myBuilder.setNegativeButton("DO NOT DISCARD", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }


                        });
                    }

                });
            }
        });
    }
}

