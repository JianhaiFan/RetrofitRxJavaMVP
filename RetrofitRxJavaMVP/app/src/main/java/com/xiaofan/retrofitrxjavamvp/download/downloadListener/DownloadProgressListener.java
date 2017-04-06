package com.xiaofan.retrofitrxjavamvp.download.downloadListener;


/**
 * @author: 范建海
 * @createTime: 2017/3/28 10:49
 * @className:  DownloadProgressListener
 * @description: 成功回调处理
 * @changed by:
 */
public interface DownloadProgressListener {
    /**
     * 下载进度
     * @param read
     * @param count
     * @param done
     */
    void update(long read, long count, boolean done);
}
