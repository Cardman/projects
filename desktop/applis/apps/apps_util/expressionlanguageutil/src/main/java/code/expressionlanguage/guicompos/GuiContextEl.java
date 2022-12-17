package code.expressionlanguage.guicompos;

import code.expressionlanguage.exec.*;
import code.expressionlanguage.guicompos.stds.FctWindowCloseAll;
import code.expressionlanguage.utilcompo.RunnableContextEl;
import code.gui.AbsPlainButton;
import code.util.StringList;

public final class GuiContextEl extends RunnableContextEl {


    GuiContextEl(InitPhase _state, CommonExecutionInfos _executionInfos, StringList _args) {
        super(_state, _executionInfos, _args);
    }

    @Override
    public void forwardAndClear() {
        super.forwardAndClear();
        LgNamesGui standards_ = (LgNamesGui) getStandards();
        standards_.getGuiExecutingBlocks().forwardAndClear(standards_.getGuiAliases(),standards_.getContent(), this, getClasses());
    }

//    public void disposeAll(StackCall _stackCall) {
////        for (Struct s: getGuiInit().getWindows().toSnapshotArray(this, _stackCall).list()) {
////            if (!(s instanceof WindowStruct)) {
////                continue;
////            }
////            ((WindowStruct)s).setVisible(false);
////            ((WindowStruct)s).dispose();
////        }
////        _guiExecutingBlocks.getFrame().setVisible(false);
////        _guiExecutingBlocks.getFrame().dispose();
//        interrupt();
////        getGuiInit().launchHooks(this, _stackCall);
////        _guiExecutingBlocks.getGuiInterpreterElements().setGuiRunnable(null);
////        new CoveringCodeTask(this, getExecutingOptions()).run();
//    }
    public GuiInitializer getGuiInit() {
        return (GuiInitializer)getInit();
    }

    @Override
    public void interrupt() {
        FctWindowCloseAll.closeAll(this,StackCall.newInstance(InitPhase.NOTHING,this));
        super.interrupt();
        LgNamesGui standards_ = (LgNamesGui) getStandards();
        AbsPlainButton s_ = standards_.getGuiExecutingBlocks().getStop();
        if (s_ != null) {
            s_.setEnabled(true);
        }
    }

}
