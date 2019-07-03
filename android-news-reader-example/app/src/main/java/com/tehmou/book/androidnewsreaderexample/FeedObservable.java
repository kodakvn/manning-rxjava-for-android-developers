package com.tehmou.book.androidnewsreaderexample;

import java.util.List;

import io.reactivex.Observable;

public class FeedObservable {
   protected static Observable<List<Entry>> getFeed(String url) {
      return Observable.empty();
   }
}
