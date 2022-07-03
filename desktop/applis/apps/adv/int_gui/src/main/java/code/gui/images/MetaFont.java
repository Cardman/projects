package code.gui.images;

public final class MetaFont {
    private final String fontFamily;
    private final int font;
    private final int realSize;
    public MetaFont(String _fontFamily, int _font, int _realSize) {
        fontFamily =  _fontFamily;
        font = _font;
        realSize = _realSize;
    }

    public int getFont() {
        return font;
    }

    public int getRealSize() {
        return realSize;
    }

    public String getFontFamily() {
        return fontFamily;
    }
}
