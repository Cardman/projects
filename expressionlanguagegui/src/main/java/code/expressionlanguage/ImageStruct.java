package code.expressionlanguage;

import code.expressionlanguage.structs.*;
import code.util.CustList;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public final class ImageStruct implements Struct {
    private BufferedImage image;
    private boolean withAlpha;
    private Graphics graphics;
    public ImageStruct(int _w, int _h, boolean _rgba) {
        int w_ = Math.max(1,_w);
        int h_ = Math.max(1,_h);
        withAlpha = _rgba;
        if (_rgba) {
            image = new BufferedImage(w_,h_,BufferedImage.TYPE_INT_ARGB);
        } else {
            image = new BufferedImage(w_,h_,BufferedImage.TYPE_INT_RGB);
        }
        graphics = image.getGraphics();
    }
    public IntStruct getWidth() {
        return new IntStruct(image.getWidth());
    }
    public IntStruct getHeight() {
        return new IntStruct(image.getHeight());
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

    BufferedImage getImage() {
        return image;
    }

    public BooleanStruct isWithAlpha() {
        return new BooleanStruct(withAlpha);
    }

    public Struct getColor() {
        Color color_ = graphics.getColor();
        if (color_ == null) {
            return NullStruct.NULL_VALUE;
        }
        return new ColorStruct(color_);
    }

    public void setColor(Struct _c) {
        if (!(_c instanceof ColorStruct)) {
            return;
        }
        graphics.setColor(((ColorStruct)_c).getColor());
    }

    public Struct getFont() {
        Font font_ = graphics.getFont();
        if (font_ == null) {
            return NullStruct.NULL_VALUE;
        }
        return new FontStruct(font_);
    }

    public void setFont(Struct _font) {
        if (!(_font instanceof FontStruct)) {
            return;
        }
        graphics.setFont(((FontStruct)_font).getFont());
    }

    public void dispose() {
        graphics.dispose();
    }

    public void drawLine(int x1, int y1, int x2, int y2) {
        graphics.drawLine(x1, y1, x2, y2);
    }

    public void fillRect(int x, int y, int width, int height) {
        graphics.fillRect(x, y, width, height);
    }

    public void drawRect(int x, int y, int width, int height) {
        graphics.drawRect(x, y, width, height);
    }

    public void drawOval(int x, int y, int width, int height) {
        graphics.drawOval(x, y, width, height);
    }

    public void fillOval(int x, int y, int width, int height) {
        graphics.fillOval(x, y, width, height);
    }

    public void drawPolygon(Struct xPoints, Struct yPoints) {
        if (!(xPoints instanceof ArrayStruct)) {
            return;
        }
        if (!(yPoints instanceof ArrayStruct)) {
            return;
        }
        ArrayStruct x_ = (ArrayStruct) xPoints;
        ArrayStruct y_ = (ArrayStruct) yPoints;
        if (x_.getInstance().length != y_.getInstance().length) {
            return;
        }
        int len_ = x_.getInstance().length;
        int[] xs_ = new int[len_];
        int[] ys_ = new int[len_];
        for (int i = 0; i < len_; i++) {
            xs_[i] = ((NumberStruct)x_.getInstance()[i]).intStruct();
            ys_[i] = ((NumberStruct)y_.getInstance()[i]).intStruct();
        }
        graphics.drawPolygon(xs_, ys_, len_);
    }

    public void fillPolygon(Struct xPoints, Struct yPoints) {
        if (!(xPoints instanceof ArrayStruct)) {
            return;
        }
        if (!(yPoints instanceof ArrayStruct)) {
            return;
        }
        ArrayStruct x_ = (ArrayStruct) xPoints;
        ArrayStruct y_ = (ArrayStruct) yPoints;
        if (x_.getInstance().length != y_.getInstance().length) {
            return;
        }
        int len_ = x_.getInstance().length;
        int[] xs_ = new int[len_];
        int[] ys_ = new int[len_];
        for (int i = 0; i < len_; i++) {
            xs_[i] = ((NumberStruct)x_.getInstance()[i]).intStruct();
            ys_[i] = ((NumberStruct)y_.getInstance()[i]).intStruct();
        }
        graphics.fillPolygon(xs_, ys_, len_);
    }

    public void drawString(Struct _str, int _x, int _y) {
        if (!(_str instanceof StringStruct)) {
            return;
        }
        graphics.drawString(((StringStruct)_str).getInstance(), _x, _y);
    }

    public void drawImage(Struct _img, int _x, int _y) {
        if (!(_img instanceof ImageStruct)) {
            return;
        }
        graphics.drawImage(((ImageStruct)_img).image, _x, _y, null);
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
