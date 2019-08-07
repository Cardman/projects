package code.expressionlanguage;

import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;

import java.awt.*;

public final class FontMetricsStruct implements Struct {
    private FontMetrics font;
    public FontMetricsStruct(FontMetrics _action) {
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

    public FontMetrics getFont() {
        return font;
    }

    @Override
    public boolean sameReference(Struct _other) {
        if (!(_other instanceof FontMetricsStruct)) {
            return false;
        }
        return font == ((FontMetricsStruct)_other).font;
    }
}