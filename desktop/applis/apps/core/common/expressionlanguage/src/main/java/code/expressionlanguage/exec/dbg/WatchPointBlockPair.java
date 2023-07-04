package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.common.ClassField;

public final class WatchPointBlockPair {
    private final ClassField field;
    private final WatchPoint value;

    public WatchPointBlockPair(ClassField _file, WatchPoint _v) {
        this.field = _file;
        this.value = _v;
    }
    public boolean match(WatchPointBlockPair _b) {
        return match(_b.field);
    }
    public boolean match(ClassField _file) {
        return getField().eq(_file);
    }

    public String keyStr() {
        return field.getClassName()+"."+ field.getFieldName();
    }

    public ClassField getField() {
        return field;
    }

    public WatchPoint getValue() {
        return value;
    }
}
