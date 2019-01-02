package code.expressionlanguage.structs;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ExecutableCode;

public final class BooleanStruct implements DisplayableStruct, ExportableStringStruct,RealInstanceStruct {

    private final boolean value;

    public BooleanStruct(boolean _value) {
        value = _value;
    }

    @Override
    public Struct getParent() {
        return NullStruct.NULL_VALUE;
    }

    @Override
    public String getClassName(ExecutableCode _context) {
        return _context.getStandards().getAliasBoolean();
    }

    @Override
    public StringStruct getDisplayedString(Analyzable _an) {
        if (value) {
            return new StringStruct(_an.getStandards().getTrueString());
        }
        return new StringStruct(_an.getStandards().getFalseString());
    }
    @Override
    public StringStruct exportValue() {
        if (value) {
            return new StringStruct("1");
        }
        return new StringStruct("0");
    }
    @Override
    public Boolean getInstance() {
        return value;
    }

    @Override
    public boolean sameReference(Struct _other) {
        if (!(_other instanceof BooleanStruct)) {
            return false;
        }
        BooleanStruct other_ = (BooleanStruct) _other;
        return getInstance().booleanValue() == other_.getInstance().booleanValue();
    }

}
