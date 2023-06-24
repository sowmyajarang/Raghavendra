package com.raghavendra.raghavendra;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

class EntryList extends ArrayAdapter<Entry> {
    private Activity context;
    private List<Entry> entryList;
        public EntryList(Activity context, List<Entry> entryList) {
        super(context, R.layout.list_layout, entryList);
        this.context = context;
        this.entryList = entryList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_layout, null, true);
        TextView textViewqty = (TextView) listViewItem.findViewById(R.id.searchresultqty);
        TextView textViewdate = (TextView) listViewItem.findViewById(R.id.listviewdate);
        TextView textViewmno = (TextView) listViewItem.findViewById(R.id.searchresultmno);
        TextView textViewsupplier = (TextView) listViewItem.findViewById(R.id.searchresultsupplier);

        Entry entry = entryList.get(position);

        textViewdate.setText(entry.getDate());
        textViewmno.setText(entry.getEntryMno());
        textViewsupplier.setText(entry.getEntrysname());
        textViewqty.setText(entry.getEntryqty());
            return listViewItem;
        }
    }




