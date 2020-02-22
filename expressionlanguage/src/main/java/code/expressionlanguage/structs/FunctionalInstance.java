package code.expressionlanguage.structs;

import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.opers.util.ClassField;
import code.util.EntryCust;
import code.util.ObjectMap;

public final class FunctionalInstance implements Struct {

    private final String className;

    private Struct functional;

    public FunctionalInstance(String _className) {
        className = _className;
    }

    @Override
    public Struct getParent() {
        return NullStruct.NULL_VALUE;
    }

    @Override
    public boolean sameReference(Struct _other) {
        return this == _other;
    }

    @Override
    public String getClassName(ExecutableCode _contextEl) {
        return className;
    }

    public Struct getFunctional() {
        return functional;
    }

    public void setFunctional(Struct _functional) {
        functional = _functional;
    }
}
