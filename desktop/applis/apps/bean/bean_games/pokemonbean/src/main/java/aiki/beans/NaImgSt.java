package aiki.beans;

import code.bean.nat.*;

public final class NaImgSt extends NaNuSt {
    private final int[][] img;

    public NaImgSt(int[][] _i) {
        this.img = _i;
    }
    public static int[][] tryGet(NaSt _n) {
        int[][] o_;
        if (_n instanceof NaImgSt) {
            o_ = ((NaImgSt)_n).getImg();
        } else {
            o_ = null;
        }
        if (o_ != null) {
            return o_;
        }
        return new int[0][0];
    }

    public int[][] getImg() {
        return img;
    }
}
