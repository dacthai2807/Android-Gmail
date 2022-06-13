package com.example.gmail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> implements Filterable {
    private List<User> userList;
    private List<User> userListOld;

    public UserAdapter(List<User> userList) {
        this.userList = userList;
        this.userListOld = userList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = userList.get(position);
        if (user == null) return;
        holder.userImg.setImageResource(user.getImageId());
        holder.userName.setText(user.getName());
        holder.lastMsg.setText(user.getLastMessage());
        holder.time.setText(user.getLastMsgTime());
    }

    @Override
    public int getItemCount() {
        if (userList != null) return userList.size();
        return 0;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String strSearch = constraint.toString();
                if (strSearch.isEmpty()) {
                    userList = userListOld;
                }
                else {
                    List <User> list = new ArrayList<>();
                    for (User user : userListOld) {
                        if (user.getName().toLowerCase().contains(strSearch.toLowerCase())) {
                            list.add(user);
                        }
                    }
                    userList = list;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = userList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                userList = (List<User>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        private ImageView userImg;
        private TextView userName;
        private TextView lastMsg;
        private TextView time;

        public UserViewHolder(@NonNull View convertView) {
            super(convertView);
            userImg  = (ImageView) convertView.findViewById(R.id.profile_pic);
            userName   = (TextView) convertView.findViewById(R.id.personName);
            lastMsg    = (TextView) convertView.findViewById(R.id.lastMessage);
            time       = (TextView) convertView.findViewById(R.id.msgtime);
        }
    }
}
