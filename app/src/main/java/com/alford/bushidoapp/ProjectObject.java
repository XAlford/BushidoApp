package com.alford.bushidoapp;

/**
 * Created by WilliamAlford on 5/29/17.
 */

public class ProjectObject {


    private String mId;
    private String mTitle;
    private String mPhotoUrl;
    private String mDescription;

    public void setDiscipline(String discipline) {
        mDiscipline = discipline;
    }

    private String mDiscipline;
    private String mApprentice;
    private String mDate;
    private String mSelectedRadioBtn;
    private String mLocation;
    private String mMentor;

    public ProjectObject(String chatsKey) {
        mChatsKey = chatsKey;
    }

    private String mChatsKey;

    public String getChatsKey() {
        return mChatsKey;
    }

    public void setChatsKey(String chatsKey) {
        mChatsKey = chatsKey;
    }

    boolean mIsChecked;
    private String mCreator;
    private boolean mHasStarted;

    public String getSelectedRadioBtn() {
        return mSelectedRadioBtn;
    }

    public void setSelectedRadioBtn(String selectedRadioBtn) {
        mSelectedRadioBtn = selectedRadioBtn;
    }



    public ProjectObject() {

    }

    public String getDiscipline() {
        return mDiscipline;
    }

    //REGISTRATTION CONSTRUCTOR



//    public ProjectObject(String id, String title, String photoUrl,
//                         String description, String location, String discipline,
//                         String mentor, String apprentice, String date,
//                         boolean isChecked, String creator,
//                         boolean hasStarted, DatabaseReference chatLocation) {
//        mId = id;
//        mTitle = title;
//        mDiscipline = discipline;
//        mPhotoUrl = photoUrl;
//        mDescription = description;
//        mLocation = location;
//        mMentor = mentor;
//        mApprentice = apprentice;
//        mDate = date;
//        mIsChecked = isChecked;
//        mCreator = creator;
//        mHasStarted = hasStarted;
//        mChatsKey = chatLocation.push().getKey();
//    }

    //REGISTRATTION CONSTRUCTOR

    public ProjectObject(String id,
                         String title,
                         String description,
                         String date,
                         String discipline,
                         String selectedRadioBtn) {
        mId =id;
        mTitle = title;
        mDescription = description;
        mDate = date;
        mDiscipline = discipline;
        mSelectedRadioBtn = selectedRadioBtn;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getPhotoUrl() {
        return mPhotoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        mPhotoUrl = photoUrl;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String location) {
        mLocation = location;
    }

    public String getMentor() {
        return mMentor;
    }

    public void setMentor(String mentor) {
        mMentor = mentor;
    }

    public String getApprentice() {
        return mApprentice;
    }

    public void setApprentice(String apprentice) {
        mApprentice = apprentice;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public boolean isChecked() {
        return mIsChecked;
    }

    public void setChecked(boolean checked) {
        mIsChecked = checked;
    }

    public String getCreator() {
        return mCreator;
    }

    public void setCreator(String creator) {
        mCreator = creator;
    }

    public boolean isHasStarted() {
        return mHasStarted;
    }

    public void setHasStarted(boolean hasStarted) {
        mHasStarted = hasStarted;
    }




}
