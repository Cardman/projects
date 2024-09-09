package code.expressionlanguage;

import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.guicompos.LgNamesGui;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class EvtTreePrepareCustomEvents extends AbsPrepareCustomEvents {
    private final Struct node;

    public EvtTreePrepareCustomEvents(Struct _s) {
        this.node = _s;
    }

    @Override
    protected ExecTypeFunction pair(ContextEl _ctx) {
        return new ExecTypeFunction(((LgNamesGui) _ctx.getStandards()).getGuiExecutingBlocks().getTreeListener(),((LgNamesGui) _ctx.getStandards()).getGuiExecutingBlocks().getTreeListenerValueChanged());
    }

    @Override
    protected CustList<ArgumentWrapper> args() {
        return new CustList<ArgumentWrapper>(new ArgumentWrapper(node));
    }

    @Override
    protected boolean excluded() {
        return false;
    }
}
