package com.tehmou.book.androidnewsreaderexample.network;

import android.util.Log;

import com.tehmou.book.androidnewsreaderexample.network.Entry;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class FeedObservable {
   private final static String TAG = "kkk";

   private FeedObservable() {

   }

   public static Observable<List<Entry>> getFeed(String url) {
      return RawNetworkObservable.create(url)
         .map(response -> {
            FeedParser parser = new FeedParser();
            try {
               List<Entry> entries = parser.parse(response.body().byteStream());
               Log.v(TAG, "Number of entries from url " + url + ": " + entries.size());
               return entries;
            } catch (IOException e) {
               Log.e(TAG, "Error parsing feed", e);
            }
            return new ArrayList<>();
         });
   }
}
