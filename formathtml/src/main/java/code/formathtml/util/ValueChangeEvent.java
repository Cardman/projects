package code.formathtml.util;
import code.expressionlanguage.structs.Struct;
import code.util.Longs;

public class ValueChangeEvent {

    private final Struct oldValue;

    private final Struct newValue;

    public ValueChangeEvent(Struct _oldValue, Struct _newValue) {
        oldValue = _oldValue;
        newValue = _newValue;
    }

    public Struct getOldValue() {
        return oldValue;
    }

    public Struct getNewValue() {
        return newValue;
    }
}
