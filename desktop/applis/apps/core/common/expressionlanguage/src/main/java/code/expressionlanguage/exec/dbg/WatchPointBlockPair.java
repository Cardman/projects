package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.analyze.blocks.RootBlock;
import code.util.core.StringUtil;

public final class WatchPointBlockPair {
    private final boolean trueField;
    private final RootBlock root;
    private final int nbType;
    private final String field;
    private final WatchPoint value;

    public WatchPointBlockPair(boolean _trField,RootBlock _rBlock, int _nb, String _field, WatchPoint _v) {
        this.trueField = _trField;
        this.root = _rBlock;
        this.nbType = _nb;
        this.field = _field;
        this.value = _v;
    }
    public boolean match(WatchPointBlockPair _b) {
        return match(_b.trueField,_b.nbType,_b.field);
    }
    public boolean match(boolean _t,int _root, String _field) {
        return trueField==_t&&nbType == _root && StringUtil.quickEq(field,_field);
    }

    public String keyStr() {
        if (isTrueField()) {
            return "1"+nbType+"."+fieldName();
        }
        return "0"+nbType+"."+fieldName();
    }

    public boolean isTrueField() {
        return trueField;
    }

    public String fieldName() {
        return field;
    }

    public RootBlock getRoot() {
        return root;
    }

    public WatchPoint getValue() {
        return value;
    }
}
