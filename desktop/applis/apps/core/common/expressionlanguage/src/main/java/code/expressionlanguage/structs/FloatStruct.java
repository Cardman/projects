package code.expressionlanguage.structs;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.common.DisplayedStrings;

public final class FloatStruct extends AbsRealNumberStruct {

    private final float value;

    public FloatStruct(float _value) {
        value = _value;
    }

    @Override
    public String getClassName(ContextEl _context) {
        return _context.getStandards().getContent().getNbAlias().getAliasFloat();
    }

    @Override
    public double doubleStruct() {
        return value;
    }

    @Override
    protected StringStruct getStringValue(DisplayedStrings _dis) {
        return NumParsers.getFloatString(this,_dis.getInfinity(),
                _dis.getNan(),
                _dis.getExponent());
    }
}
