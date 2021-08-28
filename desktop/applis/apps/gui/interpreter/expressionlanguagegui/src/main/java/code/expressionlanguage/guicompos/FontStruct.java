package code.expressionlanguage.guicompos;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.*;
import code.util.core.StringUtil;

import java.awt.Font;

public final class FontStruct extends WithoutParentStruct implements Struct {
    private final String family;
    private final boolean bold;
    private final boolean italic;
    private final int size;
    public FontStruct() {
        family = getFontFamily(NullStruct.NULL_VALUE);
        size = 12;
        bold = false;
        italic = false;
    }
    public FontStruct(int _size) {
        family = getFontFamily(NullStruct.NULL_VALUE);
        size = _size;
        bold = false;
        italic = false;
    }
    public FontStruct(Struct _family, boolean _bold,boolean _italic, int _size) {
        family = getFontFamily(_family);
        bold = _bold;
        italic = _italic;
        size = _size;
    }
    public FontStruct(Font _action) {
        family = _action.getName();
        bold = _action.isBold();
        italic = _action.isItalic();
        size = _action.getSize();
    }

    private Font newFont() {
        if (bold) {
            if (italic) {
                return new Font(family,Font.BOLD+Font.ITALIC,size);
            }
            return new Font(family,Font.BOLD,size);
        }
        if (italic) {
            return new Font(family,Font.ITALIC,size);
        }
        return new Font(family,Font.PLAIN,size);
    }

    private static String getFontFamily(Struct _family) {
        String fontFamily_;
        if (!(_family instanceof StringStruct)) {
            fontFamily_ = "Default";
        } else {
            fontFamily_ = ((StringStruct)_family).getInstance();
        }
        return fontFamily_;
    }

    public StringStruct getName() {
        return new StringStruct(family);
    }

    public IntStruct getSize() {
        return new IntStruct(size);
    }

    public BooleanStruct isItalic() {
        return BooleanStruct.of(italic);
    }

    public BooleanStruct isBold() {
        return BooleanStruct.of(bold);
    }
    @Override
    public String getClassName(ContextEl _contextEl) {
        return ((LgNamesGui) _contextEl.getStandards()).getGuiAliases().getAliasFont();
    }

    public Font getFont() {
        return newFont();
    }

    @Override
    public boolean sameReference(Struct _other) {
        if (!(_other instanceof FontStruct)) {
            return false;
        }
        if (size != ((FontStruct)_other).size) {
            return false;
        }
        if (bold != ((FontStruct)_other).bold) {
            return false;
        }
        if (italic != ((FontStruct)_other).italic) {
            return false;
        }
        return StringUtil.quickEq(family,((FontStruct)_other).family);
    }
    @Override
    public long randCode() {
        long r_ = NumParsers.mergeRandCode(1,NumParsers.randCode(size));
        r_ = NumParsers.mergeRandCode(r_,NumParsers.randCode(BooleanStruct.of(bold)));
        r_ = NumParsers.mergeRandCode(r_,NumParsers.randCode(BooleanStruct.of(italic)));
        r_ = NumParsers.mergeRandCode(r_,NumParsers.randCode(family));
        return r_;

    }
}