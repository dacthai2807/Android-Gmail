package com.example.gmail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MenuAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<ItemMenu> list;

    public MenuAdapter(Context context, int layout, List<ItemMenu> list) {
        this.context    = context;
        this.layout     = layout;
        this.list       = list;
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

    private class ViewHolder {
        TextView text;
        ImageView image;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);

            viewHolder = new ViewHolder();
            viewHolder.text  = (TextView) convertView.findViewById(R.id.textItem);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.iconItem);

            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.text.setText(list.get(position).nameItem);
        viewHolder.image.setImageResource(list.get(position).iconItem);
        return convertView;
    }
}
