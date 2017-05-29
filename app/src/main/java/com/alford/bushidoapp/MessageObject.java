package com.alford.bushidoapp;

/**
 * Created by WilliamAlford on 5/29/17.
 */

public class MessageObject {

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public MessageObject(String message, String email, String id) {
        mMessage = message;
        mEmail = email;
        mId = id;
    }

    private String mMessage;
    private String mEmail;
    private String mId;

    public MessageObject(String themessagebeingsent)
    {
        mMessage = themessagebeingsent;
    }
    public MessageObject (){

    }
}
