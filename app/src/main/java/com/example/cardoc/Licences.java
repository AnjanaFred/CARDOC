package com.example.cardoc;

import androidx.appcompat.app.AppCompatActivity;


import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class Licences extends AppCompatActivity implements View.OnClickListener, TimePickerDialog.OnTimeSetListener {

    EditText Vehicleno;
    EditText Licenceno;
    EditText Ownername;
    TextView ptime, pdate;
    Button  dget,tget;
    Calendar StartD;
    int notificationId=1;
    ImageButton btnb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_licences);
        StartD = Calendar.getInstance();

        Vehicleno= (EditText)findViewById(R.id.vno);
        Licenceno= (EditText)findViewById(R.id.lno);
        Ownername=(EditText)findViewById(R.id.Owname);
        ptime=(TextView)findViewById(R.id.picktime);
        pdate=(TextView)findViewById(R.id.pickdate);
        dget=(Button)findViewById(R.id.datebtn);
        tget=(Button)findViewById(R.id.timebtn);
        btnb=(ImageButton)findViewById(R.id.backp);

       findViewById(R.id.okbtn).setOnClickListener(this);


       btnb.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent b= new Intent(Licences.this,Main2Activity.class);
               startActivity(b);
           }
       });


       dget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getDate();
            }
        });

        tget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTime();

            }
        });

    }

    public void getDate(){
        Calendar now= Calendar.getInstance();
        int Year = now.get(Calendar.YEAR);
        int Mont = now.get(Calendar.MONTH);
        int day  = now.get(Calendar.DATE);


        DatePickerDialog datePickerDialog =new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener(){

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                StartD.set(Calendar.YEAR,year);
                StartD.set(Calendar.MONTH,month);
                StartD.set(Calendar.DATE,dayOfMonth);

                String ndate=  dayOfMonth+"/"+month+"/"+year;
                pdate.setText(ndate);
            }

        } , Year,Mont,day);

        datePickerDialog.show();
    }



    public void getTime(){
        Calendar ntime= Calendar.getInstance();
        int hrs=ntime.get(Calendar.HOUR);
        int min=ntime.get(Calendar.MINUTE);


            TimePickerDialog timePickerDialog=new TimePickerDialog(this,(TimePickerDialog.OnTimeSetListener) this,hrs,min, DateFormat.is24HourFormat(this));


            timePickerDialog.show();


    }


  public void onTimeSet(TimePicker tp, int HourOfDay, int minute){

        StartD.set(Calendar.HOUR_OF_DAY,HourOfDay);
        StartD.set(Calendar.MINUTE,minute);
        StartD.set(Calendar.SECOND,0);


        String time= HourOfDay+":"+minute;
        ptime.setText(time);
                        }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(Licences.this, AlarmReciver.class);
        intent.putExtra("notificationId", notificationId);
        intent.putExtra("todo","Hi,"+Ownername.getText().toString()+"Your Licence :"+Licenceno.getText().toString()+"Expired."+"Vehicle No:"+Vehicleno.getText().toString());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        // getBroadcast(context, requestCode, intent, flags)
        PendingIntent alarmIntent = PendingIntent.getBroadcast(Licences.this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);





        switch (v.getId()) {
            case R.id.okbtn:
                long alarmStartTime = StartD.getTimeInMillis();
                manager.set(AlarmManager.RTC_WAKEUP, alarmStartTime, alarmIntent);

                Toast.makeText(this, "Licence Add to Remind", Toast.LENGTH_SHORT).show();
                System.out.println("HOURRRRR"+alarmStartTime);
                Intent intent1 = new Intent(Licences.this,Main3Activity.class);
                startActivity(intent1);

                break;


        }
        finish();

    }





}



