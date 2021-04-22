package com.example.tiviconassignment.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.tiviconassignment.R;
import com.example.tiviconassignment.model.Movies;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    private List<Movies> list;
    private Context context;


    public RecyclerAdapter(Context context, List<Movies> list) {
        this.context = context;
        this.list = list;


    }

    public void setMovieList(List<Movies> list) {
        this.list = list;
        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        if (this.list != null) {
            return this.list.size();
        }
        return 0;
    }

    @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_item, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyViewHolder holder, int position) {
        final String title = this.list.get(position).getTitle();
        final String description = this.list.get(position).getDescription();
        final String photo = this.list.get(position).getImage();


        Glide.with(context)
                .load(photo)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .override(500, 500)
                .into(holder.image);

        holder.title.setText(title);
        holder.description.setText(description);


    }



    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title, description;
        ImageView image;
        CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cardview);
            image = (ImageView) itemView.findViewById(R.id.photo);
            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
            cardView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return false;
                }
            });

        }
    }

}
