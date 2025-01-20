package aiki.beans.game;

import code.bean.nat.*;

public final class ImgPkPlayerStruct extends NaNuSt {
    private final ImgPkPlayer ent;

    public ImgPkPlayerStruct(ImgPkPlayer _e) {
        this.ent = _e;
    }

    public String key() {
        return ent.getKey().getKey();
    }
    public String translation() {
        return ent.getKey().getTranslation();
    }
    public int[][] image() {
        return ent.getImage();
    }

}
