package aiki.beans;

public final class TranslatedKeyImgs {
    private final int[][] img;
    private final int[][] color;

    public TranslatedKeyImgs(int[][] _i, int[][] _c) {
        this.img = _i;
        this.color = _c;
    }

    public int[][] getImg() {
        return img;
    }

    public int[][] getColor() {
        return color;
    }
}
