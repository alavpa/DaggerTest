package com.alavpa.dagger.presentation.ui.main;

import com.alavpa.dagger.presentation.base.BaseView;
import com.alavpa.dagger.domain.model.User;

import java.util.List;

/**
 * Created by alavpa on 7/01/17.
 */

public interface MainView extends BaseView {

    void inflateUsers(List<User> users);
    void goToDetails(long id);
}
