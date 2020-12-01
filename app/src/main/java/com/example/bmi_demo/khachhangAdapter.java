package com.example.bmi_demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class khachhangAdapter extends BaseAdapter {

    Context context;
    ArrayList<Guest>list;

    public khachhangAdapter(Context context, ArrayList<Guest> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
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
    public View getView(int position, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row= inflater.inflate(R.layout.khachhangitem,null);
        TextView mTVid= (TextView) row.findViewById(R.id.tv_idGuest);
        TextView mTVname= (TextView) row.findViewById(R.id.tv_nameGuest);
        TextView mTVbirth=(TextView) row.findViewById(R.id.tv_birthGuest);
        TextView mTVgender=(TextView)row.findViewById(R.id.tv_gender);
        TextView mTVMobile=(TextView)row.findViewById(R.id.tv_mobleGuest);



        Guest Guest = list.get(position);
        mTVid.setText(Guest.getGuestId()+"");
        mTVname.setText(Guest.getGuestName()+"");
        mTVbirth.setText(Guest.getGuestBirth()+"");
        mTVgender.setText(Guest.getGuestGender()+"");
        mTVMobile.setText(Guest.getGuestMoblie()+"");
        return  row;

    }
}
