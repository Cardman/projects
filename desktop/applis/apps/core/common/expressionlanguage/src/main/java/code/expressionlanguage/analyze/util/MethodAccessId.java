package code.expressionlanguage.analyze.util;

import code.expressionlanguage.common.LongInfo;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.util.StringList;
import code.util.core.StringUtil;

public final class MethodAccessId {
    private MethodAccessKind kind;
    private boolean retRef;
    private int index;
    private int ancestor;

    public MethodAccessId(int _index) {
        kind = MethodAccessKind.INSTANCE;
        index = _index;
    }
    public void setupInfosId(int _index,StringList _args, String _kwSt, String _kwStCall) {
        int i_ = retRefLookup(_index, _args);
        setupInfos(i_,_args,_kwSt,_kwStCall);
    }

    public void setupInfos(int _index,StringList _args, String _kwSt, String _kwStCall) {
        int i_ = _index;
        if (_args.size() > i_) {
            if (StringUtil.quickEq(_args.get(i_).trim(), _kwSt)) {
                index++;
                kind = MethodAccessKind.STATIC;
                i_++;
            } else if (StringUtil.quickEq(_args.get(i_).trim(), _kwStCall)) {
                index++;
                kind = MethodAccessKind.STATIC_CALL;
                i_++;
            }
        }
        setupAncestor(_args, i_);
    }

    public void setupAncestorId(StringList _args, int _index) {
        int i_ = retRefLookup(_index, _args);
        setupAncestor(_args, i_);
    }

    private int retRefLookup(int _index, StringList _args) {
        int i_ = _index;
        if (_args.size() > i_ && StringUtil.quickEq(_args.get(i_).trim(), "~")) {
            index++;
            retRef = true;
            i_++;
        }
        return i_;
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

    public boolean isRetRef() {
        return retRef;
    }

    public MethodAccessKind getKind() {
        return kind;
    }
}
