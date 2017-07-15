package code.formathtml.util;
import code.util.Numbers;

public class ValueChangeEvent {

    private final Numbers<Long> indexes;

    private final Object oldValue;

    private final Object newValue;

    public ValueChangeEvent(Numbers<Long> _indexes,Object _oldValue, Object _newValue) {
        indexes = _indexes;
        oldValue = _oldValue;
        newValue = _newValue;
    }

    public Numbers<Long> getIndexes() {
        return indexes;
    }

    public Object getOldValue() {
        return oldValue;
    }

    public Object getNewValue() {
        return newValue;
    }
}
