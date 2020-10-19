package code.expressionlanguage.structs;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.stds.DisplayedStrings;

public final class DoubleStruct extends AbsRealNumberStruct {

    private final double value;

    public DoubleStruct(double _value) {
        value = _value;
    }

    @Override
    public String getClassName(ContextEl _context) {
        return _context.getStandards().getContent().getNbAlias().getAliasDouble();
    }

    @Override
    public double doubleStruct() {
        return value;
    }

    @Override
    public float floatStruct() {
        return (float) value;
    }

    @Override
    protected StringStruct getStringValue(DisplayedStrings _dis) {
        return NumParsers.getDoubleString(this,_dis.getInfinity(),
                _dis.getNan(),
                _dis.getExponent());
    }
}
