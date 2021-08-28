package code.expressionlanguage.guicompos;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.*;
import code.gui.GuiConstants;

import java.awt.Color;

public final class ColorStruct extends WithoutParentStruct implements Struct {

    private static final int THREE_BYTES = 256 * 256 * 256;
    private final int color;
    public ColorStruct(int _r,int _g, int _b, int _a) {
        color = GuiConstants.newColor(range(_r),range(_g),range(_b),range(_a));
    }
    public ColorStruct(int _r,int _g, int _b) {
        color = GuiConstants.newColor(range(_r),range(_g),range(_b));
    }
    public ColorStruct(int _rgb) {
        color = GuiConstants.newColor(_rgb);
    }
    public ColorStruct(int _rgba, boolean _hasAlpha) {
        color = GuiConstants.newColor(_rgba,_hasAlpha);
    }

    private static int range(int _value) {
        return Math.min(Math.max(0, _value),255);
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return ((LgNamesGui) _contextEl.getStandards()).getGuiAliases().getAliasColor();
    }

    @Override
    public boolean sameReference(Struct _other) {
        if (!(_other instanceof ColorStruct)) {
            return false;
        }
        return color == ((ColorStruct)_other).color;
    }

    @Override
    public long randCode() {
        return NumParsers.randCode(color);
    }

    public int getColor() {
        return color;
    }

    public IntStruct getRed() {
        return new IntStruct(GuiConstants.red(color));
    }

    public IntStruct getGreen() {
        return new IntStruct(GuiConstants.green(color));
    }

    public IntStruct getBlue() {
        return new IntStruct(GuiConstants.blue(color));
    }
    public IntStruct getAlpha() {
        return new IntStruct(GuiConstants.alpha(color));
    }

    public BooleanStruct isTransparent() {
        return BooleanStruct.of(color / THREE_BYTES == 0);
    }
}
