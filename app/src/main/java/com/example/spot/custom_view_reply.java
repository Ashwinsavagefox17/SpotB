package com.example.spot;

//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//
//public class custom_view_reply extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_custom_view_reply);
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

public class custom_view_reply extends BaseAdapter {
    String[] pid, complaint, reply,date;
    private Context context;
    Button bt;

    public custom_view_reply(Context applicationContext, String[] pid, String[] complaint, String[] reply , String[] date) {
        this.context = applicationContext;
        this.pid = pid;
        this.complaint = complaint;
        this.reply = reply;
        this.date = date;


    }

    @Override
    public int getCount() {
        return complaint.length;
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
            gridView = inflator.inflate(R.layout.activity_custom_view_reply, null);//same class name

        } else {
            gridView = (View) view;

        }
        TextView tv1 = (TextView) gridView.findViewById(R.id.textView12);
        TextView tv2 = (TextView) gridView.findViewById(R.id.textView13);
        TextView tv3 = (TextView) gridView.findViewById(R.id.textView11);

//        Button bt = (Button) gridView.findViewById(R.id.button7);
//        Button bt=(Button)gridView.findViewById(R.id.button7);
//        bt.setTag(bid[i]);
//        bt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(context, "bid", Toast.LENGTH_SHORT).show();
//                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
//                SharedPreferences.Editor ed = sh.edit();
//                ed.putString("bid",view.getTag().toString());
//                ed.commit();
//                Intent i = new Intent(context,Passenger_send_complaints.class);
//                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(i);
//            }
//        });


        tv1.setTextColor(Color.BLACK);//color setting
        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);



        tv1.setText(complaint[i]);
        tv2.setText(reply[i]);
        tv3.setText(date[i]);






        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);

        return gridView;
    }
    private void handleButtonClick(String busId) {

        Intent intent = new Intent(context, Passenger_home.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
