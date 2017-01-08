package com.alavpa.dagger.presentation.ui.details;

import com.alavpa.dagger.domain.model.User;
import com.alavpa.dagger.presentation.base.BaseView;

/**
 * Created by alavpa on 7/01/17.
 */

public interface DetailsView extends BaseView {

    void inflateUser(User user);
}
