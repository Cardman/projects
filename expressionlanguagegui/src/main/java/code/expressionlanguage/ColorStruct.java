package code.expressionlanguage;

import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;

import java.awt.*;

public final class ColorStruct implements Struct {

    private static final int THREE_BYTES = 256 * 256 * 256;
    private Color image;
    public ColorStruct(int _r,int _g, int _b, int _a) {
        image= new Color(_r,_g,_b,_a);
    }
    public ColorStruct(int _r,int _g, int _b) {
        image= new Color(_r,_g,_b);
    }
    public ColorStruct(int _rgb) {
        image= new Color(_rgb);
    }
    public ColorStruct(int _rgba, boolean _hasAlpha) {
        image= new Color(_rgba,_hasAlpha);
    }

    @Override
    public Struct getParent() {
        return NullStruct.NULL_VALUE;
    }

    @Override
    public String getClassName(ExecutableCode _contextEl) {
        return ((LgNamesGui) _contextEl.getStandards()).getAliasColor();
    }

    @Override
    public boolean sameReference(Struct _other) {
        if (!(_other instanceof ColorStruct)) {
            return false;
        }
        return image.getRGB() == ((ColorStruct)_other).image.getRGB();
    }

    public IntStruct getRed() {
        return new IntStruct(image.getRed());
    }

    public IntStruct getGreen() {
        return new IntStruct(image.getGreen());
    }

    public IntStruct getBlue() {
        return new IntStruct(image.getBlue());
    }
    public IntStruct getAlpha() {
        return new IntStruct(image.getAlpha());
    }

    public BooleanStruct isTransparent() {
        return new BooleanStruct(image.getRGB() / THREE_BYTES == 0);
    }
}
