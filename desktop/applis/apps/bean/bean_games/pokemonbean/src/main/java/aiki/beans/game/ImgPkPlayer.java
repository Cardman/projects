package aiki.beans.game;

import aiki.beans.*;

public final class ImgPkPlayer extends AbsTranslatedKey {
    private final int[][] image;

    public ImgPkPlayer(String _k, String _t, int[][] _i) {
        super(_k, _t);
        image = _i;
    }

    public int[][] getImage() {
        return image;
    }
}
