package com.example.spot;

//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//
//public class custom_view_bus extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.custom_view_bus);
//    }
//}


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class custom_view_bus extends BaseAdapter {
    String[] pid, bus_no, bus_name,start_time,end_time,bid,fr,er;
    private Context context;
    Button bt;

    public custom_view_bus(Context applicationContext, String[] pid, String[] bus_no, String[] bus_name , String[] start_time, String[] end_time,String[] bid,String[] fr,String[] er) {
        this.context = applicationContext;
        this.pid = pid;
        this.bid = bid;
        this.bus_no = bus_no;
        this.bus_name = bus_name;
        this.start_time = start_time;
        this.end_time = end_time;
        this.fr = fr;
        this.er = er;


    }

    @Override
    public int getCount() {
        return bus_no.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if (view == null) {
            gridView = new View(context);
            //gridView=inflator.inflate(R.layout.customview, null);
            gridView = inflator.inflate(R.layout.custom_view_bus, null);//same class name

        } else {
            gridView = (View) view;

        }
        TextView tv1 = (TextView) gridView.findViewById(R.id.textView7);
        TextView tv2 = (TextView) gridView.findViewById(R.id.textView8);
        TextView tv3 = (TextView) gridView.findViewById(R.id.textView9);
        TextView tv4 = (TextView) gridView.findViewById(R.id.textView10);
        TextView tv5 = (TextView) gridView.findViewById(R.id.textView14);
        TextView tv6 = (TextView) gridView.findViewById(R.id.textView15);
//        Button bt = (Button) gridView.findViewById(R.id.button7);
        Button bt=(Button)gridView.findViewById(R.id.button7);
        bt.setTag(bid[i]);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "bid", Toast.LENGTH_SHORT).show();
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor ed = sh.edit();
                ed.putString("bid",view.getTag().toString());
                ed.commit();
                Intent i = new Intent(context,Passenger_send_complaints.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });


        tv1.setTextColor(Color.BLACK);//color setting
        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);
        tv4.setTextColor(Color.BLACK);
        tv5.setTextColor(Color.BLACK);
        tv6.setTextColor(Color.BLACK);


        tv1.setText(bus_no[i]);
        tv2.setText(start_time[i]);
        tv3.setText(end_time[i]);
        tv4.setText(bus_name[i]);
        tv5.setText(fr[i]);
        tv6.setText(er[i]);






        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);

        return gridView;
    }
    private void handleButtonClick(String busId) {

        Intent intent = new Intent(context, Passenger_send_complaints.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
