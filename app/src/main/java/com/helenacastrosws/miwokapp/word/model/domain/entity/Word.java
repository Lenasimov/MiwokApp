package com.helenacastrosws.miwokapp.word.model.domain.entity;

public class Word {

    private static final int NO_IMAGE_PROVIDED = -1;
    private String miwokTranslation;
    private String defaultTranslation;
    private int audioResourceId;
    private int mImageResourceId = NO_IMAGE_PROVIDED;

    public Word() {}

    public Word(String miwokTranslation, String defaultTranslation, int audioResourceId) {
        this.miwokTranslation = miwokTranslation;
        this.defaultTranslation = defaultTranslation;
        this.audioResourceId = audioResourceId;
    }

    public Word(String miwokTranslation, String defaultTranslation, int imageResourceId, int audioResourceId) {
        this.miwokTranslation = miwokTranslation;
        this.defaultTranslation = defaultTranslation;
        this.audioResourceId = audioResourceId;
        mImageResourceId = imageResourceId;
    }

    public String getMiwokTranslation() {
        return miwokTranslation;
    }

    public String getDefaultTranslation() {
        return defaultTranslation;
    }

    public int getImageResourceId() {
        return mImageResourceId;
    }

    public int getAudioResourceId() {
        return audioResourceId;
    }

    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }

    @Override
    public String toString() {
        return "Word { " +
                "miwokTranslation='" + miwokTranslation + '\'' +
                ", defaultTranslation='" + defaultTranslation + '\'' +
                ", audioResourceId=" + audioResourceId +
                ", mImageResourceId=" + mImageResourceId +
                " }";
    }

}