package me.sheepyang.qlady.entity;

/**
 * Created by Administrator on 2017/4/19.
 */

public class ModelEntity {
    private boolean isLock;
    private String avatarPath;
    private String imgPath;

    public ModelEntity() {

    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public boolean isLock() {
        return isLock;
    }

    public void setLock(boolean lock) {
        isLock = lock;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}
