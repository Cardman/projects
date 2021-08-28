package code.expressionlanguage.guicompos;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.*;

import java.awt.Color;

public final class ColorStruct extends WithoutParentStruct implements Struct {

    private static final int THREE_BYTES = 256 * 256 * 256;
    private Color color;
    public ColorStruct(int _r,int _g, int _b, int _a) {
        color = new Color(range(_r),range(_g),range(_b),range(_a));
    }
    public ColorStruct(int _r,int _g, int _b) {
        color = new Color(range(_r),range(_g),range(_b));
    }
    public ColorStruct(int _rgb) {
        color = new Color(_rgb);
    }
    public ColorStruct(int _rgba, boolean _hasAlpha) {
        color = new Color(_rgba,_hasAlpha);
    }
    ColorStruct(Color _color) {
        color = _color;
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
        return color.getRGB() == ((ColorStruct)_other).color.getRGB();
    }

    @Override
    public long randCode() {
        return NumParsers.randCode(color.getRGB());
    }

    public Color getColor() {
        return color;
    }

    public IntStruct getRed() {
        return new IntStruct(color.getRed());
    }

    public IntStruct getGreen() {
        return new IntStruct(color.getGreen());
    }

    public IntStruct getBlue() {
        return new IntStruct(color.getBlue());
    }
    public IntStruct getAlpha() {
        return new IntStruct(color.getAlpha());
    }

    public BooleanStruct isTransparent() {
        return BooleanStruct.of(color.getRGB() / THREE_BYTES == 0);
    }
}
