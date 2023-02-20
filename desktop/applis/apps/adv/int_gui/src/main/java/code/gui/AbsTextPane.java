package code.gui;

public interface AbsTextPane extends AbsTxtComponent {

    void setFontSize(int _size);
    void clearCharacterAttributes(int _begin, int _length);
    void setCharacterAttributes(int _begin, int _length, AbsAttrSet _attrs, boolean _replace);
    void setParagraphAttributes(AbsAttrSet _attrs);
}
