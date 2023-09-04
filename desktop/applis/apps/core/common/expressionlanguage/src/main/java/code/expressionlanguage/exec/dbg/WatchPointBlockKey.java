package code.expressionlanguage.exec.dbg;

import code.util.core.StringUtil;

public final class WatchPointBlockKey implements AbsKeyPoint{
    private final boolean trueField;
    private final int nbType;
    private final String field;

    public WatchPointBlockKey(boolean _trField, int _nb, String _field) {
        this.trueField = _trField;
        this.nbType = _nb;
        this.field = _field;
    }
    public boolean match(WatchPointBlockKey _b) {
        return match(_b.trueField,_b.nbType,_b.field);
    }
    public boolean match(boolean _t,int _root, String _field) {
        return trueField==_t&&nbType == _root && StringUtil.quickEq(field,_field);
    }

    @Override
    public String keyStr() {
        if (isTrueField()) {
            return "1"+getNbType()+"."+fieldName();
        }
        return "0"+getNbType()+"."+fieldName();
    }

    public int getNbType() {
        return nbType;
    }

    public boolean isTrueField() {
        return trueField;
    }

    public String fieldName() {
        return field;
    }

}
