package code.expressionlanguage.guicompos;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.*;
import code.gui.GuiConstants;
import code.gui.images.MetaFont;
import code.util.core.StringUtil;

public final class FontStruct extends WithoutParentStruct implements Struct {
    private final String family;
    private final boolean bold;
    private final boolean italic;
    private final int size;
    public FontStruct() {
        family = "";
        size = 12;
        bold = false;
        italic = false;
    }
    public FontStruct(int _size) {
        family = "";
        size = _size;
        bold = false;
        italic = false;
    }
    public FontStruct(Struct _family, boolean _bold,boolean _italic, int _size) {
        family = StringUtil.nullToEmpty(NumParsers.getStringValue(_family));
        bold = _bold;
        italic = _italic;
        size = _size;
    }
    public FontStruct(MetaFont _action) {
        family = StringUtil.nullToEmpty(_action.getFontFamily());
        bold = GuiConstants.boldFlag(_action.getFont());
        italic = GuiConstants.italicFlag(_action.getFont());
        size = _action.getRealSize();
    }

    private MetaFont newFont() {
        int font_ = GuiConstants.fontStyle(bold, italic);
        return meta(font_);
    }

    private MetaFont meta(int _font) {
        return new MetaFont(family, _font, size);
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

    public MetaFont getFont() {
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
        long r_ = NumParsers.mergeRandCode(1,size);
        r_ = NumParsers.mergeRandCode(r_,NumParsers.randCode(BooleanStruct.of(bold)));
        r_ = NumParsers.mergeRandCode(r_,NumParsers.randCode(BooleanStruct.of(italic)));
        r_ = NumParsers.mergeRandCode(r_,NumParsers.randCode(family));
        return r_;

    }
}