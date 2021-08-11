package code.formathtml.sample;

import code.bean.nat.CommNatStruct;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.*;
import code.util.StringList;
import code.util.ints.Displayable;

public final class StdStruct extends CommNatStruct {

    private final Object instance;
    
    private StdStruct(Object _instance, String _className) {
        super(_className);
        instance = _instance;
    }

    public StdStruct(StringList _instance, String _className) {
        super(_className);
        instance = _instance;
    }

    public StdStruct(Rate _instance, String _className) {
        super(_className);
        instance = _instance;
    }

    public StdStruct(LgInt _instance, String _className) {
        super(_className);
        instance = _instance;
    }
    public StdStruct(Displayable _instance, String _className) {
        super(_className);
        instance = _instance;
    }

    public static StdStruct newInstance(Object _instance, String _className) {
        return new StdStruct(_instance, _className);
    }
    public static StdStruct newListLong(Longs _instance, String _className) {
        return new StdStruct(_instance, _className);
    }
    public static StdStruct newListInt(Ints _instance, String _className) {
        return new StdStruct(_instance, _className);
    }
    public static StdStruct newListByte(Bytes _instance, String _className) {
        return new StdStruct(_instance, _className);
    }

    public Object getInstance() {
        return instance;
    }

}
