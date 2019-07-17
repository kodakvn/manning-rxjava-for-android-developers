package com.tehmou.examples.androidfilebrowser;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.io.File;
import java.util.List;

public class FileListAdapter extends ArrayAdapter<File> {
    private final Context context;
    private List<File> files;

    public FileListAdapter(Context context, List<File> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
        this.context = context;
        this.files = objects;
    }

    public void refreshFileList(List<File> newFiles) {
        files.clear();
        files.addAll(newFiles);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = super.getView(position, convertView, parent);
        final File file = getItem(position);
        ((TextView) convertView).setText(file.isDirectory() ? file.getName() + "/" : file.getName());
        convertView.setTag(file);
        return convertView;
    }
}
