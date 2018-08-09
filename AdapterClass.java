package com.example.hp.grid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by hp on 7/27/2018.
 */
public class AdapterClass extends BaseAdapter {
    int image[]={R.drawable.payyambalam,R.drawable.kannur_fort,R.drawable.kanjirakkolli,R.drawable.beach,R.drawable.parassinikkadavu,R.drawable.aralam};
    String place[]={"Payyambalam beach","Kannur fort","Kanjirakolli","Muzhappilangad Driving Beach","Parassinikkadavu Snake Park","Aralam WildLife Sanctuary"};
    Context context;
    public AdapterClass(Context c) {
        this.context=c;

    }

    @Override
    public int getCount() {
        return place.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(context).inflate(R.layout.custom_layout,parent,false);
        ImageView imageView=(ImageView)convertView.findViewById(R.id.imageView);
        TextView textView=(TextView)convertView.findViewById(R.id.textView3);
        TextView textView1=(TextView)convertView.findViewById(R.id.textView4);
        imageView.setImageResource(image[position]);
        textView.setText(place[position]);
        return convertView;
    }
}
