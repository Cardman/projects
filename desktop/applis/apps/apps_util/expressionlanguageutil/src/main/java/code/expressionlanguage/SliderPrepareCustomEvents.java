package code.expressionlanguage;

import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.guicompos.LgNamesGui;
import code.util.CustList;

public final class SliderPrepareCustomEvents extends AbsPrepareCustomEvents {

    @Override
    protected ExecTypeFunction pair(ContextEl _ctx) {
        return new ExecTypeFunction(((LgNamesGui) _ctx.getStandards()).getGuiExecutingBlocks().getChangeListener(), ((LgNamesGui) _ctx.getStandards()).getGuiExecutingBlocks().getStateChanged());
    }

    @Override
    protected CustList<Argument> args() {
        return new CustList<Argument>();
    }

    @Override
    protected boolean excluded() {
        return false;
    }
}
