package code.expressionlanguage.utilcompo;

import code.expressionlanguage.AbsPrepareCustomEvents;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecCallMethodState;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.dbg.AbsLogDbg;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.structs.WithoutParentIdStruct;
import code.expressionlanguage.utilimpl.LgNamesUtils;

public abstract class AbsDbgFutureStruct extends WithoutParentIdStruct implements IntFutureStruct{
    private final Struct called;
    private Struct found;
    private boolean cancelled;

    protected AbsDbgFutureStruct(Struct _c) {
        this.called = _c;
    }

    public Struct getCalled() {
        return called;
    }

    public Struct getFound() {
        return found;
    }

    public void setFound(Struct _f) {
        this.found = _f;
    }

    public Struct attendre(StackCall _stack, ContextEl _ctx, String _intro) {
        AbsLogDbg log_ = _stack.getStopper().getLogger();
        Struct called_ = getCalled();
        log_.log(_intro+":"+ called_.getClassName(_ctx));
        Struct f_ = getFound();
        if (f_ != null) {
            return f_;
        }
        if (cancelled) {
            return NullStruct.NULL_VALUE;
        }
        _stack.setCallingState(new ExecCallMethodState(this,called_));
        return NullStruct.NULL_VALUE;
    }

    public boolean cancel() {
        boolean was_ = cancelled;
        cancelled = true;
        return !was_ && getFound() != null;
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return ((LgNamesUtils)_contextEl.getStandards()).getExecContent().getCustAliases().getAliasFuture();
    }

    public abstract AbsPrepareCustomEvents build();
}
