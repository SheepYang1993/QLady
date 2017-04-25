package me.sheepyang.qlady.entity;

/**
 * Created by Administrator on 2017/4/19.
 */

public class ModelEntity {
    private boolean isLock;
    private String imgPath;

    public ModelEntity(boolean isLock, String imgPath) {
        this.isLock = isLock;
        this.imgPath = imgPath;
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
