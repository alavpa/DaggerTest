package com.alavpa.dagger.presentation.base;

/**
 * Created by alavpa on 7/01/17.
 */

public interface BaseView {

    void showError(String message);

    void showLoading();

    void hideLoading();

    void showMessage(String message);

}
