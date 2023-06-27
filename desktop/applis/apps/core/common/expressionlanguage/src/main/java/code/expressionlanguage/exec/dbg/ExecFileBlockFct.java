package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecBlock;
import code.expressionlanguage.exec.blocks.ExecReturnableWithSignature;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.util.core.StringUtil;

public final class ExecFileBlockFct implements AbsCallContraints {
    private final String key;
    private final String index;

    public ExecFileBlockFct(String _k,String _i) {
        this.key = _k;
        this.index = _i;
    }

    @Override
    public boolean match(AbsCallContraints _call) {
        return _call instanceof ExecFileBlockFct && match(((ExecFileBlockFct) _call).key);
    }

    @Override
    public boolean match(ContextEl _context, AbstractPageEl _call) {
        ExecBlock b_ = _call.getBlockRoot();
        return b_ instanceof ExecReturnableWithSignature && match(((ExecReturnableWithSignature) b_).id());
    }

    public boolean match(String _i) {
        return StringUtil.quickEq(keyStr(),_i);
    }

    public String keyStr() {
        return key;
    }

    @Override
    public String valueStr() {
        return index;
    }
}
