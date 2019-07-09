package com.tehmou.book.androidflickrclientexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class MainActivity extends AppCompatActivity {

    private static final String apiKey = "2ad038ee6178c302d7f9db7b7f68edfb";

    private RecyclerView recyclerView;
    private Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.main_list);
        searchButton = (Button) findViewById(R.id.search_button);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        
        searchPhoto(apiKey, "flower", 3)
           .observeOn(AndroidSchedulers.mainThread())
           .subscribe(this::updateList);
    }

    private void updateList(List<SimplePhoto> photos) {
        PhotoAdapter adapter = new PhotoAdapter(this, photos);
        recyclerView.setAdapter(adapter);
    }

    private Observable<List<SimplePhoto>> searchPhoto(String apiKey,
                                                       String search,
                                                       int limit) {
        ArrayList<SimplePhoto> photos = new ArrayList<>();
        photos.add(new SimplePhoto("1", "username", "DSC03826"));
        photos.add(new SimplePhoto("2", "username", "DSC03830"));
        photos.add(new SimplePhoto("3", "username", "DSC03831"));
        return Observable.just(photos);
    }
}
