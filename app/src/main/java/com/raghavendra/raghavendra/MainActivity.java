package com.raghavendra.raghavendra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = ".MainActivity";
    EditText mmno, mqty, mvno;
    TextView mdate;
    Button mbtnsave;
    DatabaseReference databaseEntry;
    Spinner msname, mswt;
    DatePickerDialog.OnDateSetListener mDateSetListener;
    ListView listViewEntries;
    List<Entry> entryList;

    @Override
    protected void onStart() {
        super.onStart();

            databaseEntry.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot)  {
                    entryList.clear();

                        for (DataSnapshot entrysnapshot : dataSnapshot.getChildren()) {


                            Entry entry = entrysnapshot.getValue(Entry.class);

                            entryList.add(entry);
                        }

                        EntryList adapter = new EntryList(MainActivity.this, entryList);
                        listViewEntries.setAdapter(adapter);
                    }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_main);

        databaseEntry = FirebaseDatabase.getInstance().getReference("Entry");

        mmno = (EditText) findViewById(R.id.mno);
        mbtnsave = (Button) findViewById(R.id.btnsave);
        mswt = (Spinner) findViewById(R.id.swt);
        msname = (Spinner) findViewById(R.id.sname);
        mqty = (EditText) findViewById(R.id.qty);
        mvno = (EditText) findViewById(R.id.vno);
        mdate = (TextView) findViewById(R.id.date);
        listViewEntries = (ListView) findViewById(R.id.listViewEntries);


        entryList = new ArrayList<>();


        mdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(MainActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth, mDateSetListener, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;

                Log.d(TAG, "onDateSet: mm/dd/yy: " + month + "/" + day + "/" + year);
                String date = day + "/" + month + "/" + year;
                mdate.setText(date);


            }
        };

        mbtnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addentry();
                mdate.setText("");
                mmno.setText("");
                mqty.setText("");
                mvno.setText("");
            }
        });
    }


    private void addentry() {
        String mmdate = mdate.getText().toString().trim();
        String mmmno = mmno.getText().toString().trim();
        String mmsname = msname.getSelectedItem().toString();
        String mmswt = mswt.getSelectedItem().toString();
        String mmqty = mqty.getText().toString().trim();
        if (!TextUtils.isEmpty(mmdate)) {
            String id = databaseEntry.push().getKey();
            Entry entry = new Entry(mmdate, mmmno, mmsname, mmswt, mmqty);
            databaseEntry.child(id).setValue(entry);
            Toast.makeText(this, " Entry Added!", Toast.LENGTH_SHORT).show();


        } else {
            Toast.makeText(this, "You should enter Manifestation Number", Toast.LENGTH_SHORT).show();

        }

    }
    }
