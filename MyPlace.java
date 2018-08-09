package com.example.hp.grid;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;



public class MyPlace extends Activity {


ListView listView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_place);



        listView=(ListView)findViewById(R.id.myplace);
        AdapterClass adapter=new AdapterClass(MyPlace.this);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                if(position==0)
                {
                    Intent intent=new Intent(MyPlace.this,Payyambalam.class);
                    startActivity(intent);
                }
                else if(position==1){
                    Intent intent=new Intent(MyPlace.this,KannurFort.class);
                    startActivity(intent);
                }
                else if(position==2){
                    Intent intent=new Intent(MyPlace.this,Kanjirakkolli.class);
                    startActivity(intent);
                }
                else if(position==3){
                    Intent intent=new Intent(MyPlace.this,Muzhappilangad.class);
                    startActivity(intent);
                }



            }
        });

    }
}
