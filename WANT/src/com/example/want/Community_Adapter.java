package com.example.want;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Community_Adapter extends BaseAdapter {

	private Context context;
	private ArrayList<Community_List_Data> arrData;
	private LayoutInflater inflater;
	
	public Community_Adapter(Context c, ArrayList<Community_List_Data> arr)
	{
		this.context = c;
		this.arrData = arr;
		inflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return arrData.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return arrData.get(position).getTitle();
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(convertView == null)
		{
			convertView = inflater.inflate(R.layout.community_list, parent, false);
		}
		
		TextView title = (TextView)convertView.findViewById(R.id.community_list_title);
		title.setText(arrData.get(position).getTitle());
		
		TextView writer = (TextView)convertView.findViewById(R.id.community_list_writer);
		writer.setText(arrData.get(position).getWriter());
		
		TextView date = (TextView)convertView.findViewById(R.id.community_list_date);
		date.setText(arrData.get(position).getDate());
		
		return convertView;
	}

}
