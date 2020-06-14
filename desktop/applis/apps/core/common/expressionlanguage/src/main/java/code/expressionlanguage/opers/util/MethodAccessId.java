package code.expressionlanguage.opers.util;

import code.expressionlanguage.common.LongInfo;
import code.expressionlanguage.common.NumParsers;
import code.util.StringList;

public final class MethodAccessId {
    private MethodAccessKind kind;
    private int index;
    private int ancestor;

    public MethodAccessId(int _index) {
        kind = MethodAccessKind.INSTANCE;
        index = _index;
    }
    public void setupInfos(int _index,StringList _args, String _kwSt, String _kwStCall) {
        int i_ = _index;
        if (_args.size() > i_) {
            if (StringList.quickEq(_args.get(i_).trim(), _kwSt)) {
                index++;
                kind = MethodAccessKind.STATIC;
                i_++;
            } else if (StringList.quickEq(_args.get(i_).trim(), _kwStCall)) {
                index++;
                kind = MethodAccessKind.STATIC_CALL;
                i_++;
            }
        }
        setupAncestor(_args, i_);
    }

    public void setupAncestor(StringList _args, int _i) {
        if (_args.size() > _i) {
            String trim_ = _args.get(_i).trim();
            LongInfo longValue_ = NumParsers.parseLong(trim_, 10);
            if (!longValue_.outOfRange(0,Integer.MAX_VALUE)) {
                ancestor = (int) longValue_.getValue();
                index++;
            }
        }
    }

    public int getIndex() {
        return index;
    }

    public int getAncestor() {
        return ancestor;
    }

    public MethodAccessKind getKind() {
        return kind;
    }
}
