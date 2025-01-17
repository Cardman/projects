package code.mock;

import code.gui.AbsPreparedLabel;
import code.gui.images.AbstractImage;
import code.gui.images.MetaFont;
import code.images.*;
import code.util.*;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class MockImage implements AbstractImage {
    private final int[][] pixels;
    private final StringList strings = new StringList();
    private MetaFont metaFont = new MetaFont("",0,0);
    private final StringList trans = new StringList();
    private int color;
    private final int width;

    public MockImage(int[][] _p) {
        width = _p[0].length;
        this.pixels = _p;
        color = getRGB(0,0);
    }

    @Override
    public AbsPreparedLabel newAbsPreparedLabel() {
        return new MockPreparedLabel();
    }

    @Override
    public void translate(int _x, int _y) {
        trans.add("");
    }

    @Override
    public byte[] toBytes() {
        return exportBytes();
    }

    @Override
    public int getHeight() {
        return pixels.length;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getRGB(int _i, int _j) {
        return pixels[_j][_i];
    }

    @Override
    public void setRGB(int _i, int _j, int _c) {
        pixels[NumberUtil.max(0,NumberUtil.min(_j,pixels.length-1))]
                [NumberUtil.max(0,NumberUtil.min(_i,pixels[0].length-1))] = _c;
    }

    @Override
    public void drawImage(AbstractImage _img, int _x, int _y) {
        int l_ = NumberUtil.max(0,-_x);
        int t_ = NumberUtil.max(0,-_y);
        int r_ = NumberUtil.max(l_,NumberUtil.min(getWidth(),_img.getWidth())-NumberUtil.max(0,_x));
        int b_ = NumberUtil.max(t_,NumberUtil.min(getHeight(),_img.getHeight())-NumberUtil.max(0,_y));
        for(int i = l_; i < r_; i++) {
            for (int j = t_; j < b_; j++) {
                setRGB(i+_x,j+_y,_img.getRGB(i,j));
            }
        }
    }

    @Override
    public void setColor(int _i) {
        color = _i;
    }

    @Override
    public void fillRect(int _a, int _b, int _width, int _height) {
        trans.add("");
    }

    @Override
    public void drawString(String _str, int _x, int _y) {
        getStrings().add(_str);
    }

    @Override
    public void drawRect(int _a, int _b, int _c, int _d) {
        trans.add("");
    }

    @Override
    public void fillPolygon(int[] _x, int[] _y, int _nb) {
        trans.add("");
    }

    @Override
    public void fillOval(int _x, int _y, int _a, int _b) {
        trans.add("");
    }

    @Override
    public void drawLine(int _x, int _y, int _a, int _b) {
        trans.add("");
    }

    @Override
    public int getColorValue() {
        return color;
    }

    @Override
    public MetaFont getMetaFont() {
        return metaFont;
    }

    @Override
    public void setFont(MetaFont _font) {
        setMetaFont(_font);
    }

    public void setMetaFont(MetaFont _f) {
        this.metaFont = _f;
    }

    @Override
    public void drawPolygon(int[] _w, int[] _y, int _n) {
        trans.add("");
    }

    @Override
    public void drawOval(int _x, int _y, int _width, int _height) {
        trans.add("");
    }

    @Override
    public void dispose() {
        getStrings().clear();
    }

    @Override
    public byte[] writeImg(String _s) {
        return exportBytes();
    }
    private byte[] exportBytes() {
        CustList<String> rows_ = new CustList<String>();
        for (int[] r: pixels) {
            CustList<String> row_ = new CustList<String>();
            for (int p:r) {
                row_.add(Long.toString(p));
            }
            rows_.add(StringUtil.join(row_,ImageCsv.SEPARATOR_CHAR));
        }
        return StringUtil.encode(""+width+ImageCsv.SEPARATOR_CHAR+StringUtil.join(rows_,ImageCsv.SEPARATOR_CHAR));
    }

    public StringList getStrings() {
        return strings;
    }
}
