package code.expressionlanguage;

import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.guicompos.LgNamesGui;
import code.util.CustList;

public final class ExecCallPrepareCustomEvents extends AbsPrepareCustomEvents {

    @Override
    protected ExecTypeFunction pair(ContextEl _ctx) {
        return new ExecTypeFunction(((LgNamesGui) _ctx.getStandards()).getExecContent().getExecutingBlocks().getCallableType(), ((LgNamesGui) _ctx.getStandards()).getExecContent().getExecutingBlocks().getCallMethod());
    }

    @Override
    protected CustList<ArgumentWrapper> args() {
        return new CustList<ArgumentWrapper>();
    }

    @Override
    protected boolean excluded() {
        return false;
    }
}
