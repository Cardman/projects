package code.expressionlanguage;

import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.guicompos.LgNamesGui;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.IntStruct;
import code.gui.SelectionInfo;
import code.util.CustList;

public final class EvtPrepareCustomEvents extends AbsPrepareCustomEvents {
    private final SelectionInfo selectionInfo;

    public EvtPrepareCustomEvents(SelectionInfo _s) {
        this.selectionInfo = _s;
    }

    @Override
    protected ExecTypeFunction pair(ContextEl _ctx) {
        return new ExecTypeFunction(((LgNamesGui) _ctx.getStandards()).getGuiExecutingBlocks().getListSelection(),((LgNamesGui) _ctx.getStandards()).getGuiExecutingBlocks().getValueChanged());
    }

    @Override
    protected CustList<Argument> args() {
        return new CustList<Argument>(new Argument(new IntStruct(selectionInfo.getFirstIndex())),new Argument(new IntStruct(selectionInfo.getLastIndex())),new Argument(BooleanStruct.of(selectionInfo.isMethodAction())));
    }

    @Override
    protected boolean excluded() {
        return false;
    }
}
