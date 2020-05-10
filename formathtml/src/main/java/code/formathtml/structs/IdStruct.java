package code.formathtml.structs;

import code.bean.validator.Message;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.*;
import code.util.ints.Displayable;
import code.util.ints.SimpleEntries;
import code.util.ints.SimpleEntry;
import code.util.ints.SimpleIterable;

public final class IdStruct implements RealInstanceStruct {

    private final Object instance;

    private final String className;

    private IdStruct(Object _instance, String _className) {
        instance = _instance;
        className = _className;
    }

    @Override
    public Struct getParent() {
        return NullStruct.NULL_VALUE;
    }
    public static IdStruct newInstance(SimpleEntry _instance, String _className) {
        return new IdStruct(_instance, _className);
    }
    public static IdStruct newInstance(Message _instance, String _className) {
        return new IdStruct(_instance, _className);
    }
    public static IdStruct newInstance(SimpleItr _instance, String _className) {
        return new IdStruct(_instance, _className);
    }
    public static IdStruct newInstance(Object _instance, String _className) {
        return new IdStruct(_instance, _className);
    }

    @Override
    public boolean sameReference(Struct _other) {
        return this == _other;
    }

    @Override
    public String getClassName(ExecutableCode _contextEl) {
        return className;
    }

    @Override
    public Object getInstance() {
        return instance;
    }

}
