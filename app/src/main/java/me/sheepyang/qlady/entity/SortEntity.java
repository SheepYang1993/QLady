package me.sheepyang.qlady.entity;

/**
 * Created by SheepYang on 2017/4/21.
 */

public class SortEntity {
    private String imaPath;
    private boolean isLock;
    private String name;
    private int photoNum;

    public String getImaPath() {
        return imaPath;
    }

    public void setImaPath(String imaPath) {
        this.imaPath = imaPath;
    }

    public boolean isLock() {
        return isLock;
    }

    public void setLock(boolean lock) {
        isLock = lock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhotoNum() {
        return photoNum;
    }

    public void setPhotoNum(int photoNum) {
        this.photoNum = photoNum;
    }
}
