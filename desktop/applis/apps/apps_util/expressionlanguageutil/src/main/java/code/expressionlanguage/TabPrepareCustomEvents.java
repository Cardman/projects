package code.expressionlanguage;

import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.guicompos.LgNamesGui;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class TabPrepareCustomEvents extends AbsPrepareCustomEvents {
    private final Struct anchor;
    private final Struct lead;

    public TabPrepareCustomEvents(Struct _a, Struct _l) {
        anchor = _a;
        lead = _l;
    }

    @Override
    protected ExecTypeFunction pair(ContextEl _ctx) {
        return new ExecTypeFunction(((LgNamesGui) _ctx.getStandards()).getGuiExecutingBlocks().getTableListener(), ((LgNamesGui) _ctx.getStandards()).getGuiExecutingBlocks().getTableValueTableChanged());
    }

    @Override
    protected CustList<ArgumentWrapper> args() {
        return new CustList<ArgumentWrapper>(new ArgumentWrapper(anchor),new ArgumentWrapper(lead));
    }

    @Override
    protected boolean excluded() {
        return false;
    }
}
