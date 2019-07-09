package com.tehmou.book.androidflickrclientexample;

public class SimplePhoto {
   private String id;
   private String owner;
   private String title;

   public SimplePhoto(String id, String owner, String title) {
      this.id = id;
      this.owner = owner;
      this.title = title;
   }

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getOwner() {
      return owner;
   }

   public void setOwner(String owner) {
      this.owner = owner;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }
}
