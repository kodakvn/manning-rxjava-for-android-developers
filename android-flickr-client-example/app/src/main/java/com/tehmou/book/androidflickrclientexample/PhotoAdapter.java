package com.tehmou.book.androidflickrclientexample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {

   private Context context;
   private List<SimplePhoto> photos;

   public PhotoAdapter(Context context, List<SimplePhoto> photos) {
      this.context = context;
      this.photos = photos;
   }

   @Override
   public int getItemCount() {
      return photos.size();
   }

   @NonNull @Override
   public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
      View view = LayoutInflater.from(context).inflate(R.layout.photo_list_card_view, viewGroup, false);
      return new PhotoViewHolder(view);
   }

   @Override
   public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
      final SimplePhoto photo = photos.get(position);
      holder.personName.setText(photo.getTitle());
      holder.personAge.setText(photo.getOwner());
   }

   @Override public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
      super.onAttachedToRecyclerView(recyclerView);
   }

   static class PhotoViewHolder extends RecyclerView.ViewHolder {

      CardView cv;
      TextView personName;
      TextView personAge;
      ImageView personPhoto;

      public PhotoViewHolder(@NonNull View itemView) {
         super(itemView);
         cv = (CardView) itemView.findViewById(R.id.cv);
         personName = (TextView) itemView.findViewById(R.id.person_name);
         personAge = (TextView) itemView.findViewById(R.id.person_age);
         personPhoto = (ImageView) itemView.findViewById(R.id.person_photo);
      }
   }

}
