package aiki.db;

public final class ImageArrayBaseSixtyFour {
    private int[][] image;
    private String base;

    public static ImageArrayBaseSixtyFour instance() {
        return instance(new int[0][0],"");
    }

    public static ImageArrayBaseSixtyFour instance(int[][] _img, String _base) {
        ImageArrayBaseSixtyFour img_ = new ImageArrayBaseSixtyFour();
        img_.setImage(_img);
        img_.setBase(_base);
        return img_;
    }
    public int[][] getImage() {
        return image;
    }

    public void setImage(int[][] _p) {
        this.image = _p;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String _p) {
        this.base = _p;
    }
}
