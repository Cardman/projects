package code.expressionlanguage.guicompos;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.*;
import code.util.core.IndexConstants;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public final class ImageStruct extends WithoutParentIdStruct implements Struct {
    private final BufferedImage image;
    private final boolean withAlpha;
    private final Graphics graphics;
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
        return BooleanStruct.of(withAlpha);
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

    public void drawLine(int _x1, int _y1, int _x2, int _y2) {
        graphics.drawLine(_x1, _y1, _x2, _y2);
    }

    public void fillRect(int _x, int _y, int _width, int _height) {
        graphics.fillRect(_x, _y, _width, _height);
    }

    public void drawRect(int _x, int _y, int _width, int _height) {
        graphics.drawRect(_x, _y, _width, _height);
    }

    public void drawOval(int _x, int _y, int _width, int _height) {
        graphics.drawOval(_x, _y, _width, _height);
    }

    public void fillOval(int _x, int _y, int _width, int _height) {
        graphics.fillOval(_x, _y, _width, _height);
    }

    public void drawPolygon(Struct _xPoints, Struct _yPoints) {
        if (!(_xPoints instanceof ArrayStruct)) {
            return;
        }
        if (!(_yPoints instanceof ArrayStruct)) {
            return;
        }
        ArrayStruct x_ = (ArrayStruct) _xPoints;
        ArrayStruct y_ = (ArrayStruct) _yPoints;
        if (x_.getLength() != y_.getLength()) {
            return;
        }
        int len_ = x_.getLength();
        int[] xs_ = new int[len_];
        int[] ys_ = new int[len_];
        for (int i = 0; i < len_; i++) {
            xs_[i] = ((NumberStruct)x_.get(i)).intStruct();
            ys_[i] = ((NumberStruct)y_.get(i)).intStruct();
        }
        graphics.drawPolygon(xs_, ys_, len_);
    }

    public void fillPolygon(Struct _xPoints, Struct _yPoints) {
        if (!(_xPoints instanceof ArrayStruct)) {
            return;
        }
        if (!(_yPoints instanceof ArrayStruct)) {
            return;
        }
        ArrayStruct x_ = (ArrayStruct) _xPoints;
        ArrayStruct y_ = (ArrayStruct) _yPoints;
        if (x_.getLength() != y_.getLength()) {
            return;
        }
        int len_ = x_.getLength();
        int[] xs_ = new int[len_];
        int[] ys_ = new int[len_];
        for (int i = 0; i < len_; i++) {
            xs_[i] = ((NumberStruct)x_.get(i)).intStruct();
            ys_[i] = ((NumberStruct)y_.get(i)).intStruct();
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
    public String getClassName(ContextEl _contextEl) {
        return ((LgNamesGui) _contextEl.getStandards()).getGuiAliases().getAliasImage();
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
        for (int i = IndexConstants.FIRST_INDEX; i < w_; i++) {
            for (int j = IndexConstants.FIRST_INDEX; j < h_; j++) {
                if (_imgOne.getRGB(i, j) != _imgTwo.getRGB(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }
}
