package code.expressionlanguage;

import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

import java.awt.image.BufferedImage;

public final class ImageStruct implements Struct {
    private BufferedImage image;
    private boolean withAlpha;
    public ImageStruct(int _w, int _h, boolean _rgba) {
        int w_ = Math.max(1,_w);
        int h_ = Math.max(1,_h);
        withAlpha = _rgba;
        if (_rgba) {
            image = new BufferedImage(w_,h_,BufferedImage.TYPE_INT_ARGB);
        } else {
            image = new BufferedImage(w_,h_,BufferedImage.TYPE_INT_RGB);
        }
    }
    public IntStruct getWidth() {
        return new IntStruct(image.getWidth());
    }
    public IntStruct getHeight() {
        return new  IntStruct(image.getHeight());
    }
    public void setPixel(int _x, int _y, int _rgb) {
        if (_x < 0) {
            return;
        }
        if (_x >= image.getWidth()) {
            return;
        }
        if (_y < 0) {
            return;
        }
        if (_y >= image.getHeight()) {
            return;
        }
        image.setRGB(_x,_y,_rgb);
    }
    public IntStruct getPixel(int _x, int _y) {
        if (_x < 0) {
            return new IntStruct(-1);
        }
        if (_x >= image.getWidth()) {
            return new IntStruct(-1);
        }
        if (_y < 0) {
            return new IntStruct(-1);
        }
        if (_y >= image.getHeight()) {
            return new IntStruct(-1);
        }
        return new IntStruct(image.getRGB(_x,_y));
    }

    public BufferedImage getImage() {
        return image;
    }

    public BooleanStruct isWithAlpha() {
        return new BooleanStruct(withAlpha);
    }

    @Override
    public Struct getParent() {
        return NullStruct.NULL_VALUE;
    }

    @Override
    public String getClassName(ExecutableCode _contextEl) {
        return ((LgNamesGui) _contextEl.getStandards()).getAliasImage();
    }

    @Override
    public boolean sameReference(Struct _other) {
        if (!(_other instanceof ImageStruct)) {
            return false;
        }
        return eq(image, ((ImageStruct)_other).image);
    }

    public static boolean eq(BufferedImage _imgOne, BufferedImage _imgTwo) {
        if (_imgOne.getWidth() != _imgTwo.getWidth()) {
            return false;
        }
        if (_imgOne.getHeight() != _imgTwo.getHeight()) {
            return false;
        }
        int w_ = _imgOne.getWidth();
        int h_ = _imgOne.getHeight();
        for (int i = CustList.FIRST_INDEX; i < w_; i++) {
            for (int j = CustList.FIRST_INDEX; j < h_; j++) {
                if (_imgOne.getRGB(i, j) != _imgTwo.getRGB(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }
}
