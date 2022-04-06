package edu.volstate.dicegame;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class SbRecyclerViewAdapter extends RecyclerView.Adapter<SbRecyclerViewAdapter.MyViewHolder> {

    // instance variables
    Context context;
    ArrayList<Player> players;

    // constructor
    public SbRecyclerViewAdapter(Context context, ArrayList<Player> players) {
        this.context = context;
        this.players = players;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflating layout and giving look to rows
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.scoreboard_row, parent, false);
        return new SbRecyclerViewAdapter.MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // assigning values to the rows created in the layout
        // based on the position of the recycler view
        holder.textName.setText(players.get(position).getName());
        holder.textScore.setText(String.format("High Score: %s", players.get(position).getHighScore()));
        holder.textDoubles.setText(String.format("Doubles: %s", players.get(position).getDoubles()));
        holder.textTriples.setText(String.format("Triples: %s", players.get(position).getTriples()));
        holder.textRolls.setText(String.format("Roll Count: %s", players.get(position).getRollCount()));
    }

    @Override
    public int getItemCount() {
        // the view wants to know the number of items displayed
        return players.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // grabbing views from our row layout file
        TextView textName, textScore, textDoubles, textTriples, textRolls;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textName = itemView.findViewById(R.id.textName);
            textScore = itemView.findViewById(R.id.textScore);
            textDoubles = itemView.findViewById(R.id.textDoubles);
            textTriples = itemView.findViewById(R.id.textTriples);
            textRolls = itemView.findViewById(R.id.textRollCount);

        }
    }
}
