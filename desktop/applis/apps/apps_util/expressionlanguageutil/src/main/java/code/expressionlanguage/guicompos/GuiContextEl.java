package code.expressionlanguage.guicompos;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.guicompos.stds.FctWindowCloseAll;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.RunnableContextEl;
import code.gui.AbsButton;
import code.threads.AbstractAtomicBoolean;
import code.util.StringList;

public final class GuiContextEl extends RunnableContextEl {


    public GuiContextEl(AbstractAtomicBoolean _i, Struct _state, CommonExecutionInfos _executionInfos, StringList _args) {
        super(_i,_state, _executionInfos, _args);
    }

    @Override
    public ContextEl copy(Struct _state) {
        GuiContextEl c_ = new GuiContextEl(getInterrupt(), _state, getExecutionInfos(), getArgs());
        EventStruct.setupThread(c_);
        return c_;
    }
    @Override
    public void forwardAndClear() {
        super.forwardAndClear();
        LgNamesGui standards_ = (LgNamesGui) getStandards();
        standards_.forwardAndClear(standards_.getGuiAliases(),standards_.getContent(), this, getClasses());
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
        AbsButton s_ = standards_.getGuiExecutingBlocks().getStop();
        if (s_ != null) {
            s_.setEnabled(true);
        }
    }

}
