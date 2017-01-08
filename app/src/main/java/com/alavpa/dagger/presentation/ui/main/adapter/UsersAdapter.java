package com.alavpa.dagger.presentation.ui.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alavpa.dagger.R;
import com.alavpa.dagger.domain.model.User;

import java.util.Collections;
import java.util.List;

/**
 * Created by alavpa on 7/01/17.
 */

public class UsersAdapter extends RecyclerView.Adapter<UserViewHolder>{

    List<User> users;
    OnClickListener onClickListener;

    LayoutInflater layoutInflater;

    public interface OnClickListener{
        void onClick(User user);
    }

    public UsersAdapter(Context context){
        layoutInflater = LayoutInflater.from(context);
        users = Collections.emptyList();
    }

    public void setUsers(List<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater
                .inflate(R.layout.item_user,parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        holder.bind(users.get(position), onClickListener);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}
