package com.zft.navbarlib;

/**
 * author:zft
 * date:2017/11/1 0001.
 */

public interface IFtTab {

    /***
     * 开始动画
     */
    void anim();

    /**
     * 结束动画
     */
    void endanim();

    /***
     *  选中状态
     */
    void selected();

    /***
     * 非选中状态
     */
    void unselected();
}
