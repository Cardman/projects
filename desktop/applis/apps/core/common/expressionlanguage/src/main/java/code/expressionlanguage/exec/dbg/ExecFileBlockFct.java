package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecBlock;
import code.expressionlanguage.exec.blocks.ExecReturnableWithSignature;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.util.core.StringUtil;

public final class ExecFileBlockFct implements AbsCallContraints {
    private final String index;

    public ExecFileBlockFct(String _i) {
        this.index = _i;
    }

    @Override
    public boolean match(AbsCallContraints _call) {
        return _call instanceof ExecFileBlockFct && match(((ExecFileBlockFct) _call).index);
    }

    @Override
    public boolean match(ContextEl _context, AbstractPageEl _call) {
        ExecBlock b_ = _call.getBlockRoot();
        return b_ instanceof ExecReturnableWithSignature && match(((ExecReturnableWithSignature) b_).id());
    }

    public boolean match(String _i) {
        return StringUtil.quickEq(getIndex(),_i);
    }

    public String getIndex() {
        return index;
    }

    public String keyStr() {
        return index;
    }
}
