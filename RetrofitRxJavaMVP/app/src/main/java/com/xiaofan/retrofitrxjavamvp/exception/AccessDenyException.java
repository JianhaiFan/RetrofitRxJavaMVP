package com.xiaofan.retrofitrxjavamvp.exception;

/**
 * @author: 范建海
 * @createTime: 2017/4/6 16:12
 * @className:  AccessDenyException
 * @description: Token
 * @changed by:
 */
public class AccessDenyException extends ApiException {

    public AccessDenyException(Throwable e) {
        super(e);
    }

    public AccessDenyException(Throwable cause, @CodeException.CodeEp int code, String showMsg) {
        super(cause, code, showMsg);
    }
}
