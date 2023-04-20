package ls2.efrei.projet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    Context context;
    private ArrayList<String> id, matchFormat,latitude, longitude, geocoding,  streetName, score1, score2, player1, player2;
    private ArrayList<byte[]> imagePath;
    CustomAdapter(Context context, ArrayList<String> id, ArrayList<String> matchFormat, ArrayList<byte[]> imagePath, ArrayList<String> latitude, ArrayList<String> longitude, ArrayList<String> geocoding, ArrayList<String> streetName, ArrayList<String> score1, ArrayList<String> score2, ArrayList<String> player1, ArrayList<String> player2){
        this.context = context;
        this.id = id;
        this.matchFormat = matchFormat;
        this.imagePath = imagePath;
        this.latitude = latitude;
        this.longitude = longitude;
        this.geocoding = geocoding;
        this.streetName = streetName;
        this.score1 = score1;
        this.score2 = score2;
        this.player1 = player1;
        this.player2 = player2;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.match_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.id.setText(String.valueOf(id.get(position)));
        holder.score1.setText(String.valueOf(score1.get(position)));
        holder.score2.setText(String.valueOf(score2.get(position)));
        holder.player1.setText(String.valueOf(player1.get(position)));
        holder.player2.setText(String.valueOf(player2.get(position)));
    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    /**
     * This class is used to create a view holder for the recycler view
     * Creates a Row to display a saved match
     */
    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView id, score1, score2, player1, player2;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.match_id);
            score1 = itemView.findViewById(R.id.match_score1);
            score2 = itemView.findViewById(R.id.match_score2);
            player1 = itemView.findViewById(R.id.match_player1);
            player2 = itemView.findViewById(R.id.match_player2);
        }
    }
}
