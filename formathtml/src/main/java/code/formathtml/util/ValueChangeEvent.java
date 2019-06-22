package code.formathtml.util;
import code.expressionlanguage.structs.Struct;
import code.util.Longs;

public class ValueChangeEvent {

    private final Longs indexes;

    private final Struct oldValue;

    private final Struct newValue;

    public ValueChangeEvent(Longs _indexes,Struct _oldValue, Struct _newValue) {
        indexes = _indexes;
        oldValue = _oldValue;
        newValue = _newValue;
    }

    public Longs getIndexes() {
        return indexes;
    }

    public Struct getOldValue() {
        return oldValue;
    }

    public Struct getNewValue() {
        return newValue;
    }
}
