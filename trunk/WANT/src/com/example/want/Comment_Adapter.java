package com.example.want;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Comment_Adapter extends BaseAdapter {
	private Context context;
	private ArrayList<Comment_List_Data> arrData;
	private LayoutInflater inflater;

	public Comment_Adapter(Context c, ArrayList<Comment_List_Data> arr) {
		context = c;
		arrData = arr;
		inflater = (LayoutInflater) c
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return arrData.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return arrData.get(position).getContext();
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			convertView = inflater
					.inflate(R.layout.comment_list, parent, false);
		}

		TextView context = (TextView) convertView
				.findViewById(R.id.comment_list_Context);
		context.setText(arrData.get(position).getContext());

		TextView writer = (TextView) convertView
				.findViewById(R.id.comment_list_Writer);
		writer.setText(arrData.get(position).getWriter());

		TextView date = (TextView) convertView
				.findViewById(R.id.comment_list_Date);
		date.setText(arrData.get(position).getDate());

		return convertView;
	}
}
