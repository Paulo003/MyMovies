package sg.edu.rp.c346.id21009874.mymovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    Context parent_context;
    int layout_id;
    ArrayList<Movie> movieList;

    public CustomAdapter(Context context,int resource,ArrayList<Movie> objects){
        super(context, resource,objects);
        parent_context = context;
        layout_id = resource;
        movieList = objects;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(layout_id, parent, false);

        TextView tvTitle = rowView.findViewById(R.id.tvTitle2);
        TextView tvGenre = rowView.findViewById(R.id.tvGenere2);
        TextView tvYear = rowView.findViewById(R.id.tvYear2);
        ImageView ivRating = rowView.findViewById(R.id.imageView);
        Movie movieItem = movieList.get(position);
        tvTitle.setText(movieItem.getTitle());
        tvGenre.setText(movieItem.getGenre());
        tvYear.setText(movieItem.getYear() + "");
        if (movieItem.getRating().equals("G")) {
            ivRating.setImageResource(R.drawable.rating_g);
        } else if (movieItem.getRating().equals("M18")) {
            ivRating.setImageResource(R.drawable.rating_m18);
        } else if (movieItem.getRating().equals("NC16")) {
            ivRating.setImageResource(R.drawable.rating_nc16);
        } else if (movieItem.getRating().equals("PG")) {
            ivRating.setImageResource(R.drawable.rating_pg);
        } else if (movieItem.getRating().equals("PG13")) {
            ivRating.setImageResource(R.drawable.rating_pg13);
        }
        else {
            ivRating.setImageResource(R.drawable.rating_r21);
        }

    return rowView;
    }
}



