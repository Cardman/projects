package code.expressionlanguage.files;

public final class OffsetStringInfo {

    private final int offset;
    private final String info;
    public OffsetStringInfo(int _offset, String _info) {
        offset = _offset;
        info = _info;
    }
    public int getOffset() {
        return offset;
    }
    public String getInfo() {
        return info;
    }

}
