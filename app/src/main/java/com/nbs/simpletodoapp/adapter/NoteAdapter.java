package com.nbs.simpletodoapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nbs.simpletodoapp.R;
import com.nbs.simpletodoapp.db.Note;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sidiq on 26/02/2016.
 */
public class NoteAdapter extends BaseAdapter{

    private List<Note> notes;
    private Activity activity;

    public NoteAdapter(Activity activity, List<Note> notes){
        this.activity = activity;
        this.notes = notes;
    }

    @Override
    public int getCount() {
        return notes.size();
    }

    @Override
    public Object getItem(int position) {
        return notes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_note, null);
            holder = new ViewHolder();
            holder.txtTitle = (TextView)convertView.findViewById(R.id.txt_item_title);
            holder.txtDate = (TextView)convertView.findViewById(R.id.txt_item_date);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        Note item = (Note)getItem(position);
        holder.txtTitle.setText(item.getTitle());
        holder.txtDate.setText(item.getDate());

        return convertView;
    }

    static class ViewHolder{
        TextView txtTitle;
        TextView txtDate;
    }
}
