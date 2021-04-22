package com.example.tiviconassignment.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tiviconassignment.R;
import com.example.tiviconassignment.adapter.RecyclerAdapter;
import com.example.tiviconassignment.model.Movies;
import com.example.tiviconassignment.viewModel.HomeViewModel;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    RecyclerAdapter adapter;
    RecyclerView recyclerView;
    Button add, remove, refresh;
    private List<Movies> movieList;
    private HomeViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupRecycler();
        addRemoveRefreshAction();
        viewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        viewModel.getMovieListObserver().observe(this, new Observer<List<Movies>>() {
            @Override
            public void onChanged(List<Movies> movies) {
                if (movies != null) {
                    movieList = movies;
                    adapter.setMovieList(movies);

                }
            }

        });
        viewModel.makeApiCall();

    }

    public void setupRecycler() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerAdapter(this, movieList);
        remove = (Button) findViewById(R.id.remove);
        add = (Button) findViewById(R.id.add);
        refresh = (Button) findViewById(R.id.refresh);
        recyclerView.setAdapter(adapter);


    }

    public void rearRangeItems() {
        Collections.shuffle(movieList, new Random(System.currentTimeMillis()));
        adapter = new RecyclerAdapter(this, movieList);
        recyclerView.setAdapter(adapter);
    }

    public void addRemoveRefreshAction() {
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (movieList.size() > 0) {
                    movieList.remove(movieList.size() - 1);
                    adapter.notifyDataSetChanged();
                }
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (movieList.size() >= 0) {
                    Movies data = new Movies("https://im.haberturk.com/2020/07/31/ver1596219930/2760925_810x458.jpg",
                            "Breaking Bad",
                            "Breaking Bad, Vince Gilligan tarafından tasarlanmış ABD drama televizyon dizisidir.");
                    movieList.add(movieList.size(), data);
                    adapter.notifyDataSetChanged();

                }
            }
        });

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movieList.clear();
                recyclerView.setAdapter(adapter);


            }
        });




    }


}