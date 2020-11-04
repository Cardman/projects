package code.expressionlanguage.guicompos;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.RunnableContextEl;
import code.gui.CustComponent;
import code.threads.ThreadUtil;

public final class GuiContextEl extends RunnableContextEl {


    GuiContextEl(InitPhase _state, CommonExecutionInfos _executionInfos) {
        super(_state, _executionInfos);
    }

    @Override
    public void forwardAndClear(AnalyzedPageEl _ana, Forwards _forwards) {
        super.forwardAndClear(_ana, _forwards);
        LgNamesGui standards_ = (LgNamesGui) getStandards();
        standards_.getGuiExecutingBlocks().forwardAndClear(standards_.getGuiAliases(),standards_.getContent(), this, getClasses());
    }

    public void disposeAll(GuiExecutingBlocks _guiExecutingBlocks) {
        for (Struct s: getGuiInit().getWindows().toSnapshotArray(this).list()) {
            if (!(s instanceof WindowStruct)) {
                continue;
            }
            ((WindowStruct)s).setVisible(false);
            ((WindowStruct)s).dispose();
        }
        _guiExecutingBlocks.getFrame().setVisible(false);
        _guiExecutingBlocks.getFrame().dispose();
        interrupt();
        getGuiInit().launchHooks(this);
        _guiExecutingBlocks.getWindow().setNullCurrent();
        Thread th_ = CustComponent.newThread(new CoveringCodeTask(this, getExecutingOptions()));
        th_.start();
        ThreadUtil.join(th_);

    }
    public GuiInitializer getGuiInit() {
        return (GuiInitializer)getInit();
    }


}
