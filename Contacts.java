package com.example.hp.grid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Contacts extends AppCompatActivity {

    Button keralaPolice,ambulance,fireForce,districtAdministration,blockPanchayat,mla,kseb,ksrtc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        keralaPolice=(Button)findViewById(R.id.police);
        ambulance=(Button)findViewById(R.id.ambulance);
        fireForce=(Button)findViewById(R.id.fire);
        districtAdministration=(Button)findViewById(R.id.district);
        blockPanchayat=(Button)findViewById(R.id.block);
        mla=(Button)findViewById(R.id.mla);
        kseb=(Button)findViewById(R.id.kseb);
        ksrtc=(Button)findViewById(R.id.ksrtc);

        keralaPolice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Contacts.this,KeralaPolice.class);
                startActivity(intent);
            }
        });
        ambulance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Contacts.this,Ambulance.class);
                startActivity(intent);
            }
        });

        fireForce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Contacts.this,FireForce.class);
                startActivity(intent);
            }
        });
        districtAdministration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Contacts.this,DistrictAdministration.class);
                startActivity(intent);
            }
        });

        blockPanchayat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Contacts.this,BlockPanchayat.class);
                startActivity(intent);
            }
        });

        mla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Contacts.this,Mla.class);
                startActivity(intent);
            }
        });

        kseb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Contacts.this,Kseb.class);
                startActivity(intent);
            }
        });

        ksrtc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Contacts.this,KsrtcPhone.class);
                startActivity(intent);
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
