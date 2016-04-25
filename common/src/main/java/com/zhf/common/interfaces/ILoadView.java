package com.zhf.common.interfaces;

/**
 */
public interface ILoadView {

    void showLoading();

    void hideLoading();
    /**加载更多时出错*/
    void showError(String error);
}
