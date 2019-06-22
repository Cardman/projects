package aiki.beans;

import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.structs.NullStruct;
import code.formathtml.util.RealInstanceStruct;
import code.expressionlanguage.structs.Struct;
import aiki.facade.enums.SelectedBoolean;

public final class SelectedBooleanStruct implements RealInstanceStruct {

    private final SelectedBoolean instance;

    private final String className;

    public SelectedBooleanStruct(SelectedBoolean _instance, String _className) {
        instance = _instance;
        className = _className;
    }
    @Override
    public Object getInstance() {
        return instance;
    }

    @Override
    public Struct getParent() {
        return NullStruct.NULL_VALUE;
    }

    @Override
    public String getClassName(ExecutableCode _contextEl) {
        return className;
    }

    @Override
    public boolean sameReference(Struct _other) {
        if (!(_other instanceof SelectedBooleanStruct)) {
            return false;
        }
        SelectedBooleanStruct other_ = (SelectedBooleanStruct) _other;
        return getInstance() == other_.getInstance();
    }
}
