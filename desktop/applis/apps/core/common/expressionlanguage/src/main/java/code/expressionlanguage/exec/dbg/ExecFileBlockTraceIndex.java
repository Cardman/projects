package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecFileBlock;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.util.CustList;

public final class ExecFileBlockTraceIndex implements AbsCallContraints {
    private final ExecFileBlock file;
    private final int numberFile;
    private final int index;

    public ExecFileBlockTraceIndex(ExecFileBlock _f, int _nf, int _i) {
        this.file = _f;
        this.numberFile = _nf;
        this.index = _i;
    }

    public static void setAll(AbsCollection<AbsCallContraints> _collection, CustList<AbsCallContraints> _elts) {
        AbsCollection<AbsCallContraints> conv_ = _collection.intercept().newExecFileBlockTraceIndexCollection();
        int s_ = _elts.size();
        for (int i = 0; i < s_; i++) {
            conv_.add(_elts.get(i));
        }
        _collection.setAll(conv_);
    }

    @Override
    public boolean match(AbsCallContraints _call) {
        return _call instanceof ExecFileBlockTraceIndex && match(((ExecFileBlockTraceIndex) _call).file, ((ExecFileBlockTraceIndex) _call).index);
    }

    @Override
    public boolean match(ContextEl _context, AbstractPageEl _call) {
        return match(_call.getFile(), _call.getTraceIndex());
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
        return numberFile+"/"+index;
    }

    @Override
    public String valueStr() {
        return ExecFileBlock.name(file)+" : "+index;
    }
}
