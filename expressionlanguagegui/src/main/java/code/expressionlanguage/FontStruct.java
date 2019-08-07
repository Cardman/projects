package code.expressionlanguage;

import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;

import java.awt.*;

public final class FontStruct implements Struct {
    private Font font;
    public FontStruct(Font _action) {
        font = _action;
    }
    @Override
    public Struct getParent() {
        return NullStruct.NULL_VALUE;
    }

    @Override
    public String getClassName(ExecutableCode _contextEl) {
        //GuiContextEl
        return "";
    }

    public Font getFont() {
        return font;
    }

    @Override
    public boolean sameReference(Struct _other) {
        if (!(_other instanceof FontStruct)) {
            return false;
        }
        return font == ((FontStruct)_other).font;
    }
}