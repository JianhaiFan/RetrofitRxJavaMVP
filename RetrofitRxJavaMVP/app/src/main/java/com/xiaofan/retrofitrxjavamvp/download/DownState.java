package com.xiaofan.retrofitrxjavamvp.download;

/**
 * @author: 范建海
 * @createTime: 2017/3/28 10:12
 * @className:  DownState
 * @description: 下载状态
 * @changed by:
 */
public enum  DownState {
    START(0),
    DOWN(1),
    PAUSE(2),
    STOP(3),
    ERROR(4),
    FINISH(5);
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    DownState(int state) {
        this.state = state;
    }
}
