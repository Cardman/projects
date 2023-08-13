package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.common.ClassField;
import code.util.core.StringUtil;

public final class WatchPointBlockPair {
    private final RootBlock root;
    private final int nbType;
    private final ClassField field;
    private final WatchPoint value;

    public WatchPointBlockPair(RootBlock _rBlock, int _nb, ClassField _file, WatchPoint _v) {
        this.root = _rBlock;
        this.nbType = _nb;
        this.field = _file;
        this.value = _v;
    }
    public boolean match(WatchPointBlockPair _b) {
        return match(_b.nbType,_b.field.getFieldName());
    }
    public boolean match(int _root, String _field) {
        return nbType == _root && StringUtil.quickEq(field.getFieldName(),_field);
    }

    public String keyStr() {
        return nbType+"."+ field.getFieldName();
    }

    public RootBlock getRoot() {
        return root;
    }

    public WatchPoint getValue() {
        return value;
    }
}
