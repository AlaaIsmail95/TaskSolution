package com.example.alaaismail.tasksolution.view;


import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.text.InputType;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.domain.Event;

import com.example.alaaismail.tasksolution.R;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class calendarActivity extends AppCompatActivity {
    CalendarView mCalendarView;
    CompactCalendarView compactCalendarView;

    int display_msg = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendara);
        compactCalendarView = findViewById(R.id.compactcalendar_view);


        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(final Date dateClicked) {


                if (display_msg == 0) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(calendarActivity.this);
                    builder.setTitle("Add Event");
                    final EditText event_title = new EditText(calendarActivity.this);
                    event_title.setInputType(InputType.TYPE_CLASS_TEXT);
                    event_title.setHint("Event Title");

                    builder.setView(event_title);
                    builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Event event = new Event(Color.RED, dateClicked.getTime(), event_title.getText().toString());
                            compactCalendarView.addEvent(event);

                        }
                    }).show();
                    display_msg = 1;
                }
                else {
                    List<Event> events = compactCalendarView.getEvents(dateClicked);
                    if (!events.isEmpty()) {
                        Toast.makeText(calendarActivity.this, events.get(0).getData().toString(), Toast.LENGTH_LONG).show();
                        compactCalendarView.removeAllEvents();
                    }
                    else {
                        Toast.makeText(calendarActivity.this,"No Events in this day",Toast.LENGTH_LONG).show();
                    }
                    display_msg = 0;
                }
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {

            }
        });

        /*mCalendarView = findViewById(R.id.calendarView);


        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                AlertDialog.Builder builder = new AlertDialog.Builder(calendarActivity.this);
                builder.setTitle("Add Event");
                final EditText event_title = new EditText(calendarActivity.this);
                event_title.setInputType(InputType.TYPE_CLASS_TEXT);
                event_title.setHint("Event Title");
                final EditText event_Description = new EditText(calendarActivity.this);
                event_Description.setInputType(InputType.TYPE_CLASS_TEXT);
                event_Description.setHint("Event Description");
                builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (ContextCompat.checkSelfPermission(calendarActivity.this,
                                Manifest.permission.READ_CALENDAR) == PackageManager.PERMISSION_GRANTED) {
                            ContentResolver cr = calendarActivity.this.getContentResolver();
                            ContentValues cv = new ContentValues();
                            cv.put(CalendarContract.Events.TITLE, event_title.getText().toString());
                            cv.put(CalendarContract.Events.DESCRIPTION, event_Description.getText().toString());
//                        cv.put(CalendarContract.Events.,event_Description.getText().toString());
                            Uri uri = cr.insert(CalendarContract.Events.CONTENT_URI, cv);
                        }
                        else {
                            if (ActivityCompat.shouldShowRequestPermissionRationale(calendarActivity.this, Manifest.permission.READ_CALENDAR)) {
                                new AlertDialog.Builder(calendarActivity.this).setTitle("Permission needed")
                                        .setMessage("We need to access your Calendar to display it in the application")
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                ActivityCompat.requestPermissions(calendarActivity.this, new String[]{Manifest.permission.READ_CALENDAR}, 1);
                                            }
                                        })
                                        .setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.dismiss();
                                            }
                                        }).create().show();

                            }
                            else {
                                ActivityCompat.requestPermissions(calendarActivity.this, new String[]{Manifest.permission.READ_CALENDAR}, 1);
                            }

                        }
                            ContentResolver cr = calendarActivity.this.getContentResolver();
                            ContentValues cv = new ContentValues();
                            cv.put(CalendarContract.Events.TITLE, event_title.getText().toString());
                            cv.put(CalendarContract.Events.DESCRIPTION, event_Description.getText().toString());
//                        cv.put(CalendarContract.Events.,event_Description.getText().toString());
                            Uri uri = cr.insert(CalendarContract.Events.CONTENT_URI, cv);

                    }
                });

            }
        });*/
    }


}
