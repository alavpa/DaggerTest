package com.alavpa.dagger.presentation.ui.main.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.alavpa.dagger.R;
import com.alavpa.dagger.domain.model.User;

/**
 * Created by alavpa on 7/01/17.
 */

public class UserViewHolder extends RecyclerView.ViewHolder {

    TextView tv_name;

    public UserViewHolder(View itemView) {
        super(itemView);
        tv_name = (TextView)itemView.findViewById(R.id.tv_name);
    }

    public void bind(final User user,final UsersAdapter.OnClickListener onClickListener){
        tv_name.setText(user.getName());

        this.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onClick(user);
            }
        });
    }
}
