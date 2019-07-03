package com.tehmou.book.androidnewsreaderexample;

import java.util.Date;

public class Entry {
   public final String id;
   public final String title;
   public final String link;
   public final long updated;

   public Entry(String id, String title, String link, long updated) {
      this.id = id;
      this.title = title;
      this.link = link;
      this.updated = updated;
   }

   @Override public String toString() {
      return new Date(updated).toString() + "\n"+ title;
   }
}
