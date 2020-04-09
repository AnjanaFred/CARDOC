package com.example.cardoc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Main2Activity extends AppCompatActivity {

    private CardView carda;
    private CardView cardb;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        carda= (CardView)findViewById(R.id.card1);
        cardb=(CardView)findViewById(R.id.card2);

        getButon();


    }

    public void getButon(){
        carda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a =new Intent(Main2Activity.this,Licences.class);
                startActivity(a);
                finish();

            }


        });


        cardb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent b= new Intent(Main2Activity.this,Insurance.class);
                startActivity(b);
                finish();

            }
        });


    }

}
