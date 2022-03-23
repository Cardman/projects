package code.bean.nat;

import code.util.StringList;

public final class CompositeStruct extends CommNatStruct {

    private int integer;

    private final StringList strings = new StringList();
    public CompositeStruct(String _v) {
        super(_v);
    }

    public int getInteger() {
        return integer;
    }

    public void setInteger(int _v) {
        integer = _v;
    }

    public StringList getStrings() {
        return strings;
    }

}
