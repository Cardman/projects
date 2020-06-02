package code.expressionlanguage.files;


public final class OffsetBooleanInfo {

    private final int offset;
    private final boolean info;
    public OffsetBooleanInfo(int _offset, boolean _info) {
        offset = _offset;
        info = _info;
    }
    public int getOffset() {
        return offset;
    }
    public boolean isInfo() {
        return info;
    }

}
