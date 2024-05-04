package com.example.ProjectJava;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.app.DatePickerDialog;
import android.widget.DatePicker;

import java.util.Calendar;

import androidx.fragment.app.Fragment;

public class homeFragment extends Fragment {

    public homeFragment() {
        // Required empty public constructor
    }

    public static homeFragment newInstance(String param1, String param2) {
        homeFragment fragment = new homeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        final EditText editTextDate = rootView.findViewById(R.id.editTextDate);
        final EditText editTextTime = rootView.findViewById(R.id.editTextTime);
        final EditText editTextDescription = rootView.findViewById(R.id.editTextDescription);
        Button buttonSave = rootView.findViewById(R.id.buttonSave);

        editTextDate.setOnClickListener(v -> showDatePicker(rootView));
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = editTextDate.getText().toString();
                String time = editTextTime.getText().toString();
                String description = editTextDescription.getText().toString();

                sqlhelper db = new sqlhelper(getContext());
                db.addrendezvous(date, time, description);

                editTextDate.setText("");
                editTextTime.setText("");
                editTextDescription.setText("");
            }
        });

        return rootView;
    }


    public void showDatePicker(View rootView) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        EditText editTextDate = rootView.findViewById(R.id.editTextDate);
                        editTextDate.setText(String.format("%02d-%02d-%d", dayOfMonth, monthOfYear + 1, year));
                    }
                }, year, month, day);
        datePickerDialog.show();
    }


}
