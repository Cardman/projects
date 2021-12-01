package code.expressionlanguage.guicompos;

import code.expressionlanguage.exec.*;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.RunnableContextEl;

public final class GuiContextEl extends RunnableContextEl {


    GuiContextEl(InitPhase _state, CommonExecutionInfos _executionInfos) {
        super(_state, _executionInfos);
    }

    @Override
    public void forwardAndClear() {
        super.forwardAndClear();
        LgNamesGui standards_ = (LgNamesGui) getStandards();
        standards_.getGuiExecutingBlocks().forwardAndClear(getGuiInit(),standards_.getGuiAliases(),standards_.getContent(), this, getClasses());
    }

    public void disposeAll(GuiExecutingBlocks _guiExecutingBlocks, StackCall _stackCall) {
        for (Struct s: getGuiInit().getWindows().toSnapshotArray(this, _stackCall).list()) {
            if (!(s instanceof WindowStruct)) {
                continue;
            }
            ((WindowStruct)s).setVisible(false);
            ((WindowStruct)s).dispose();
        }
        _guiExecutingBlocks.getFrame().setVisible(false);
        _guiExecutingBlocks.getFrame().dispose();
        interrupt();
        getGuiInit().launchHooks(this, _stackCall);
        _guiExecutingBlocks.getGuiInterpreterElements().setGuiRunnable(null);
        new CoveringCodeTask(this, getExecutingOptions()).run();
    }
    public GuiInitializer getGuiInit() {
        return (GuiInitializer)getInit();
    }


}
