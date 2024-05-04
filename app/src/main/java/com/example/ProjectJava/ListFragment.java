package com.example.ProjectJava;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private sqlhelper dbHelper;
    private ListView listView;
    ArrayList listItem;
    ArrayAdapter adapter;

    Cursor cursor ;


    public ListFragment() {
    }

    public static ListFragment newInstance(String param1, String param2) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new sqlhelper(getContext());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.liste, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Correctly initialize the listView from the fragment's view
        listView = view.findViewById(R.id.rdv);  // Ensure this ID is correct and exists in your layout

        listItem = new ArrayList<>();
        cursor = dbHelper.getAllURdv();
        if (cursor.getCount() == 0) {
            Toast.makeText(getContext(), "Liste vide", Toast.LENGTH_LONG).show();
        } else {
            while (cursor.moveToNext()) {
                // Concatenate Date, Time, and Description with appropriate formatting
                @SuppressLint("Range") String listItemText = cursor.getString(cursor.getColumnIndex("Date")) + " " +
                        cursor.getString(cursor.getColumnIndex("Time")) + " - " +
                        cursor.getString(cursor.getColumnIndex("Description"));
                listItem.add(listItemText);
            }
            adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, listItem);
            listView.setAdapter(adapter);
        }
    }



}