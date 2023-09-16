package code.expressionlanguage.exec.calls.util;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.Struct;

public abstract class AbsLambdaParentRetriever implements IntParentRetriever {
    private Struct parent;
    private boolean checkedParent;

    protected AbsLambdaParentRetriever() {
    }

    @Override
    public boolean retrieve(ContextEl _conf, StackCall _stackCall) {
        if (parent != null) {
            return true;
        }
        if (!checkedParent) {
            checkedParent = true;
            if (_stackCall.getStopper().isStopAtExcMethod()) {
                return false;
            }
        }
        Struct par_ = getValue(_stackCall);
        if (par_ == null) {
            String npe_ = _conf.getStandards().getContent().getCoreNames().getAliasNullPe();
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, npe_, _stackCall)));
            return false;
        }
        parent = par_;
        return afterSetParent();
    }

    protected abstract Struct getValue(StackCall _stackCall);

    public abstract int getAncestor();
    protected abstract boolean afterSetParent();

    public Struct getParent() {
        return parent;
    }

}
