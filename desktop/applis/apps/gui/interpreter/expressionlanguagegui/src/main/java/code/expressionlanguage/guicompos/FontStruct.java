package code.expressionlanguage.guicompos;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.*;

import java.awt.*;

public final class FontStruct extends WithoutParentStruct implements Struct {
    private Font font;
    public FontStruct() {
        font = newFont(NullStruct.NULL_VALUE,false,false,12);
    }
    public FontStruct(int _size) {
        font = newFont(NullStruct.NULL_VALUE,false,false,_size);
    }
    public FontStruct(Struct _family, boolean _bold,boolean _italic, int _size) {
        font = newFont(_family,_bold,_italic,_size);
    }
    public FontStruct(Font _action) {
        font = _action;
    }
    private static Font newFont(Struct _family, boolean _bold,boolean _italic, int _size) {
        String fontFamily_;
        if (!(_family instanceof StringStruct)) {
            fontFamily_ = "Default";
        } else {
            fontFamily_ = ((StringStruct)_family).getInstance();
        }
        if (_bold) {
            if (_italic) {
                return new Font(fontFamily_,Font.BOLD+Font.ITALIC,_size);
            }
            return new Font(fontFamily_,Font.BOLD,_size);
        }
        if (_italic) {
            return new Font(fontFamily_,Font.ITALIC,_size);
        }
        return new Font(fontFamily_,Font.PLAIN,_size);
    }

    public StringStruct getName() {
        return new StringStruct(font.getName());
    }

    public IntStruct getSize() {
        return new IntStruct(font.getSize());
    }

    public BooleanStruct isItalic() {
        return BooleanStruct.of(font.isItalic());
    }

    public BooleanStruct isBold() {
        return BooleanStruct.of(font.isBold());
    }
    @Override
    public String getClassName(ContextEl _contextEl) {
        return ((LgNamesGui) _contextEl.getStandards()).getAliasFont();
    }

    public Font getFont() {
        return font;
    }

    @Override
    public boolean sameReference(Struct _other) {
        if (!(_other instanceof FontStruct)) {
            return false;
        }
        return font.equals(((FontStruct)_other).font);
    }
}