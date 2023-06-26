package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.exec.blocks.ExecFileBlock;
import code.util.CustList;

public final class ExecFileBlockTraceIndex {
    private final ExecFileBlock file;
    private final int index;

    public ExecFileBlockTraceIndex(ExecFileBlock _f, int _i) {
        this.file = _f;
        this.index = _i;
    }

    public static void setAll(AbsCollection<ExecFileBlockTraceIndex> _collection, CustList<ExecFileBlockTraceIndex> _elts) {
        AbsCollection<ExecFileBlockTraceIndex> conv_ = _collection.intercept().newExecFileBlockTraceIndexCollection();
        int s_ = _elts.size();
        for (int i = 0; i < s_; i++) {
            conv_.add(_elts.get(i));
        }
        _collection.setAll(conv_);
    }
    public boolean match(ExecFileBlock _f, int _i) {
        return getFile() == _f && getIndex() == _i;
    }

    public ExecFileBlock getFile() {
        return file;
    }

    public int getIndex() {
        return index;
    }

    public String keyStr() {
        return ExecFileBlock.name(file)+"/"+index;
    }
}
