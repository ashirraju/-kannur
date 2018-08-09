package com.example.hp.grid;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;




public class KannurInfo extends Activity {


    ImageView imageView;
ProgressDialog pd;
    TextView dis_c,dis_p;
    String dc,dp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kannur_info);


        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);

        imageView=(ImageView)findViewById(R.id.im1);
        dis_c=(TextView)findViewById(R.id.collector_name);
        dis_p=(TextView)findViewById(R.id.police_name);

        async();

         final int[] imageArray = { R.drawable.kannur1,R.drawable.kannur2,R.drawable.kannur3
               };

        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            int i = 0;

            public void run() {
                imageView.setImageResource(imageArray[i]);
                i++;
                if (i > imageArray.length - 1) {
                    i = 0;
                }
                handler.postDelayed(this, 5000);
            }
        };
        handler.postDelayed(runnable, 0000);

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    private void async(){

        AsyncTask<Void,Void,Void> task=new AsyncTask<Void, Void, Void>() {
            @Override
            protected void onPreExecute() {
                pd=new ProgressDialog(KannurInfo.this);
                pd.setMessage("Loading");
                pd.setIndeterminate(true);
                pd.setCancelable(false);
                pd.show();
            }



            @Override
            protected Void doInBackground(Void... params) {

                try {
                    URL url = new URL("http://192.168.43.252:85/Kannur/show.php");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.connect();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String text;
                    while ((text = reader.readLine()) != null) {
                        JSONArray array = new JSONArray(text);
                        for (int i = 0; i < array.length(); i++) {
                           // HashMap map = new HashMap();
                            JSONObject object = (JSONObject) array.get(i);
                            dc = object.getString("dc");
                             dp = object.getString("dp");


                        }

                    }

                }catch (Exception e){
                    Toast.makeText(KannurInfo.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                return null;
            }
            @Override
            protected void onPostExecute(Void aVoid) {
                if(pd.isShowing())
                    pd.dismiss();

                dis_c.setText(dc);
                dis_p.setText(dp);
            }
    };
        task.execute();
}
}
