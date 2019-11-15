package code.expressionlanguage.opers.util;

import code.util.StringList;

public final class MethodAccessId {
    private MethodAccessKind kind;
    private int index;

    public MethodAccessId(int _index) {
        kind = MethodAccessKind.INSTANCE;
        index = _index;
    }
    public void setupInfos(int _index,StringList _args, String _kwSt, String _kwStCall) {
        if (_args.size() > _index) {
            if (StringList.quickEq(_args.get(_index).trim(), _kwSt)) {
                index++;
                kind = MethodAccessKind.STATIC;
            } else if (StringList.quickEq(_args.get(_index).trim(), _kwStCall)) {
                index++;
                kind = MethodAccessKind.STATIC_CALL;
            }
        }
    }

    public int getIndex() {
        return index;
    }

    public MethodAccessKind getKind() {
        return kind;
    }
}
