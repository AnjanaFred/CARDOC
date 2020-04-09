package com.example.cardoc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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


public class Insurance extends AppCompatActivity implements View.OnClickListener, TimePickerDialog.OnTimeSetListener {

    EditText Vehicleno;
    EditText Licenceno;
    EditText Ownername;
    TextView ptime, pdate;
    Button dget,tget;
    Calendar StartD;
    int notificationId=2;
    ImageButton btnb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insurance);
        StartD = Calendar.getInstance();

        Vehicleno= (EditText)findViewById(R.id.veno);
        Licenceno= (EditText)findViewById(R.id.lnno);
        Ownername=(EditText)findViewById(R.id.Owrname);
        ptime=(TextView)findViewById(R.id.ipicktime);
        pdate=(TextView)findViewById(R.id.ipickdate);
        dget=(Button)findViewById(R.id.idatebtn);
        tget=(Button)findViewById(R.id.itimebtn);
        btnb=(ImageButton)findViewById(R.id.backp);
        findViewById(R.id.iokbtn).setOnClickListener(this);





        btnb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a= new Intent(Insurance.this,Main2Activity.class);
                startActivity(a);
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
            public void onDateSet(DatePicker dview, int year, int month, int dayOfMonth) {
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


    public void onTimeSet(TimePicker itp, int HourOfDay, int minute){

        StartD.set(Calendar.HOUR_OF_DAY,HourOfDay);
        StartD.set(Calendar.MINUTE,minute);
        StartD.set(Calendar.SECOND,0);


        String time= HourOfDay+":"+minute;
        ptime.setText(time);
    }

    @Override
    public void onClick(View v) {

        Intent intenta = new Intent(Insurance.this, AlarmReciverI.class);
        intenta.putExtra("notificationId", notificationId);
        intenta.putExtra("atodo","Hi,"+Ownername.getText().toString()+"Your Insurance:"+Licenceno.getText().toString()+"Expired."+"Vehicle No:"+Vehicleno.getText().toString());
        intenta.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        // getBroadcast(context, requestCode, intent, flags)
        PendingIntent alarmIntent = PendingIntent.getBroadcast(Insurance.this, 1, intenta, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarm = (AlarmManager) getSystemService(ALARM_SERVICE);





        switch (v.getId()) {
            case R.id.iokbtn:
                long alarmStartTime = StartD.getTimeInMillis();
                alarm.set(AlarmManager.RTC_WAKEUP, alarmStartTime, alarmIntent);

                Toast.makeText(this, "Insurance Add to Remind", Toast.LENGTH_SHORT).show();
                System.out.println("HOURRRRR"+alarmStartTime);
                Intent intent1 = new Intent(Insurance.this,Main3Activity.class);
                startActivity(intent1);

                break;


        }
        finish();

    }




}

