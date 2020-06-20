package code.expressionlanguage.files;

import code.expressionlanguage.common.AccessEnum;

public final class OffsetAccessInfo {

    private final int offset;
    private final AccessEnum info;
    public OffsetAccessInfo(int _offset, AccessEnum _info) {
        offset = _offset;
        info = _info;
    }
    public int getOffset() {
        return offset;
    }
    public AccessEnum getInfo() {
        return info;
    }

}
