package code.formathtml.util;
import code.expressionlanguage.structs.Struct;
import code.util.Numbers;

public class ValueChangeEvent {

    private final Numbers<Long> indexes;

    private final Struct oldValue;

    private final Struct newValue;

    public ValueChangeEvent(Numbers<Long> _indexes,Struct _oldValue, Struct _newValue) {
        indexes = _indexes;
        oldValue = _oldValue;
        newValue = _newValue;
    }

    public Numbers<Long> getIndexes() {
        return indexes;
    }

    public Struct getOldValue() {
        return oldValue;
    }

    public Struct getNewValue() {
        return newValue;
    }
}
