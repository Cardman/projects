package code.formathtml.render;


public final class MetaStyle {

    private int bgColor;

    private int fgColor;

    private int size;

    private int italic;

    private int bold;

    private int border;

    private String fontFamily = "Default";

    private int emToPixels = 16;

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

    public int getSize() {
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

    public int getBorder() {
        return border;
    }

    public void setBorder(int _border) {
        border = _border;
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

    public void setEmToPixels(int _emToPixels) {
        emToPixels = _emToPixels;
    }
}
