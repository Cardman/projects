package code.formathtml.render;


public final class MetaStyle {

    private int bgColor = 255 * 256 * 256 + 255 * 256 + 255;

    private int fgColor;

    private int size;

    private int italic;

    private int bold;

    private int borderSize;

    private BorderEnum border = BorderEnum.NONE;

    private int borderColor;

    private String fontFamily = "Default";

    private int em = 1;
    private int emToPixels = 16;

    private int delta;

    public int getBgColor() {
        return bgColor;
    }

    public void setBgColor(int _bgColor) {
        bgColor = _bgColor;
    }

    public int getFgColor() {
        return fgColor;
    }

    public void setFgColor(int _fgColor) {
        fgColor = _fgColor;
    }

    public int getRealSize() {
        if (size == 0) {
            if (delta == 6) {
                return em*emToPixels * 2;
            }
            if (delta == 5) {
                return em*emToPixels * 3 / 2;
            }
            if (delta == 4) {
                return em*emToPixels * 7 / 6;
            }
            if (delta == 3) {
                return em*emToPixels;
            }
            if (delta == 2) {
                return em*emToPixels * 5 / 6;
            }
            if (delta == 1) {
                return em*emToPixels * 2 / 3;
            }
            return em*emToPixels;
        }
        return size;
    }

    public void setSize(int _size) {
        size = _size;
    }

    public int getItalic() {
        return italic;
    }

    public void setItalic(int _italic) {
        italic = _italic;
    }

    public int getBold() {
        return bold;
    }

    public void setBold(int _bold) {
        bold = _bold;
    }

    public int getBorderSize() {
        return borderSize;
    }

    public void setBorderSize(int _borderSize) {
        borderSize = _borderSize;
    }

    public BorderEnum getBorder() {
        return border;
    }

    public void setBorder(BorderEnum _border) {
        border = _border;
    }

    public int getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(int _borderColor) {
        borderColor = _borderColor;
    }

    public String getFontFamily() {
        return fontFamily;
    }

    public void setFontFamily(String _fontFamily) {
        fontFamily = _fontFamily;
    }

    public int getEmToPixels() {
        return emToPixels;
    }

    public void setEm(int _em) {
        em = _em;
    }

    public int getDelta() {
        return delta;
    }

    public void setDelta(int _delta) {
        delta = _delta;
    }
}
