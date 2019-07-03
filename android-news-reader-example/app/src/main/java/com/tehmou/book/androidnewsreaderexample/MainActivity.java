package com.tehmou.book.androidnewsreaderexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import io.reactivex.*;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "kkk";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Observable<List<Entry>> purpleFeedObservable = FeedObservable.getFeed("https://news.google.com/?output=atom");
        Observable<List<Entry>> yellowFeedObservable = FeedObservable.getFeed("http://www.theregister.co.uk/software/headlines.atom");
        Observable<List<Entry>> combinedObservable = Observable.combineLatest(purpleFeedObservable, yellowFeedObservable,
           (purpleList, yellowList) -> {
            final List<Entry> list = new ArrayList<>();
            list.addAll(purpleList);
            list.addAll(yellowList);
            return list;
        });

        combinedObservable
           .observeOn(AndroidSchedulers.mainThread())
           .subscribe(this::drawList);
    }

    private void drawList(List<Entry> listItems) {
        final ListView listView = (ListView) findViewById(R.id.list);
        final ArrayAdapter<Entry> itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItems);
        listView.setAdapter(itemsAdapter);
    }
}
