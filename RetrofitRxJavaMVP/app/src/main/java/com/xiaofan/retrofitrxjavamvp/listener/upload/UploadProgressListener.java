package com.xiaofan.retrofitrxjavamvp.listener.upload;


/**
 * @author: 范建海
 * @createTime: 2017/3/28 11:06
 * @className:  UploadProgressListener
 * @description: 上传进度回调类
 * @changed by:
 */
public interface UploadProgressListener {
    /**
     * 上传进度
     * @param currentBytesCount
     * @param totalBytesCount
     */
    void onProgress(long currentBytesCount, long totalBytesCount);
}