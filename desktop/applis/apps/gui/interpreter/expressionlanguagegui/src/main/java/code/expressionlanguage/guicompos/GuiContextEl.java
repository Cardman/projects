package code.expressionlanguage.guicompos;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.RunnableContextEl;
import code.gui.CustComponent;
import code.threads.ThreadUtil;
import code.util.StringList;

public final class GuiContextEl extends RunnableContextEl {


    public void initApplicationParts(StringList _mainArgs, MainWindow _window) {
        LgNamesGui standards_ = (LgNamesGui) getStandards();
        standards_.getGuiExecutingBlocks().initApplicationParts(getGuiInit(),_mainArgs,_window);
    }

    GuiContextEl(InitPhase _state, CommonExecutionInfos _executionInfos) {
        super(_state, _executionInfos);
    }

    @Override
    public void forwardAndClear(AnalyzedPageEl _ana, Forwards _forwards) {
        super.forwardAndClear(_ana, _forwards);
        LgNamesGui standards_ = (LgNamesGui) getStandards();
        standards_.getGuiExecutingBlocks().forwardAndClear(standards_.getGuiAliases(),standards_.getContent(),getClasses());
    }

    public void disposeAll() {
        for (Struct s: getGuiInit().getWindows().toSnapshotArray(this).getInstance()) {
            if (!(s instanceof WindowStruct)) {
                continue;
            }
            ((WindowStruct)s).setVisible(false);
            ((WindowStruct)s).dispose();
        }
        LgNamesGui standards_ = (LgNamesGui) getStandards();
        standards_.getGuiExecutingBlocks().getFrame().setVisible(false);
        standards_.getGuiExecutingBlocks().getFrame().dispose();
        interrupt();
        getGuiInit().launchHooks(this);
        standards_.getGuiExecutingBlocks().getWindow().setNullCurrent();
        Thread th_ = CustComponent.newThread(new CoveringCodeTask(this, getExecutingOptions()));
        th_.start();
        ThreadUtil.join(th_);

    }
    public GuiInitializer getGuiInit() {
        return (GuiInitializer)getInit();
    }

    public Struct showTextField(Struct _img,Struct _frame, Struct _value, Struct _message, Struct _title, Struct _ok, Struct _cancel) {
        LgNamesGui standards_ = (LgNamesGui) getStandards();
        return standards_.getGuiExecutingBlocks().showTextField(_img, _frame, _value, _message, _title, _ok, _cancel);
    }

    public Struct showTextField(Struct _frame, Struct _value, Struct _message, Struct _title, Struct _ok, Struct _cancel) {
        LgNamesGui standards_ = (LgNamesGui) getStandards();
        return standards_.getGuiExecutingBlocks().showTextField(_frame, _value, _message, _title, _ok, _cancel);
    }

    public void showMessage(Struct _img,Struct _frame, Struct _message, Struct _title, Struct _ok) {
        LgNamesGui standards_ = (LgNamesGui) getStandards();
        standards_.getGuiExecutingBlocks().showMessage(_img, _frame, _message, _title, _ok);
    }

    public void showMessage(Struct _frame, Struct _message, Struct _title, Struct _ok) {
        LgNamesGui standards_ = (LgNamesGui) getStandards();
        standards_.getGuiExecutingBlocks().showMessage(_frame, _message, _title, _ok);
    }

    public Struct getAnswerOk(Struct _img,Struct _frame, Struct _message, Struct _title, Struct _ok) {
        LgNamesGui standards_ = (LgNamesGui) getStandards();
        return standards_.getGuiExecutingBlocks().getAnswerOk(_img, _frame, _message, _title, _ok);
    }
    public Struct getAnswerOk(Struct _frame, Struct _message, Struct _title, Struct _ok) {
        LgNamesGui standards_ = (LgNamesGui) getStandards();
        return standards_.getGuiExecutingBlocks().getAnswerOk(_frame, _message, _title, _ok);
    }

    public Struct getAnswerYesNo(Struct _img,Struct _frame, Struct _message, Struct _title, Struct _yes, Struct _no) {
        LgNamesGui standards_ = (LgNamesGui) getStandards();
        return standards_.getGuiExecutingBlocks().getAnswerYesNo(_img, _frame, _message, _title, _yes, _no);
    }

    public Struct getAnswerYesNo(Struct _frame, Struct _message, Struct _title, Struct _yes, Struct _no) {
        LgNamesGui standards_ = (LgNamesGui) getStandards();
        return standards_.getGuiExecutingBlocks().getAnswerYesNo(_frame, _message, _title, _yes, _no);
    }


    public Struct getAnswer(Struct _img,Struct _frame, Struct _message, Struct _title, Struct _yes, Struct _no, Struct _cancel) {
        LgNamesGui standards_ = (LgNamesGui) getStandards();
        return standards_.getGuiExecutingBlocks().getAnswer(_img, _frame, _message, _title, _yes, _no, _cancel);
    }
    public Struct getAnswer(Struct _frame, Struct _message, Struct _title, Struct _yes, Struct _no, Struct _cancel) {
        LgNamesGui standards_ = (LgNamesGui) getStandards();
        return standards_.getGuiExecutingBlocks().getAnswer(_frame, _message, _title, _yes, _no, _cancel);
    }

    public void addWindowListener(WindowStruct _frame,Struct _event) {
        LgNamesGui standards_ = (LgNamesGui) getStandards();
        standards_.getGuiExecutingBlocks().addWindowListener(_frame, _event);
    }

    public void removeWindowListener(WindowStruct _inst, Struct _event) {
        LgNamesGui standards_ = (LgNamesGui) getStandards();
        standards_.getGuiExecutingBlocks().removeWindowListener(_inst, _event);
    }

    public ExecRootBlock getActionListener() {
        return ((LgNamesGui)getStandards()).getGuiExecutingBlocks().getActionListener();
    }

    public ExecNamedFunctionBlock getActionPerformed() {
        return ((LgNamesGui)getStandards()).getGuiExecutingBlocks().getActionPerformed();
    }

    public ExecRootBlock getMouseListener() {
        return ((LgNamesGui)getStandards()).getGuiExecutingBlocks().getMouseListener();
    }

    public ExecNamedFunctionBlock getMouseClicked() {
        return ((LgNamesGui)getStandards()).getGuiExecutingBlocks().getMouseClicked();
    }

    public ExecNamedFunctionBlock getMousePressed() {
        return ((LgNamesGui)getStandards()).getGuiExecutingBlocks().getMousePressed();
    }

    public ExecNamedFunctionBlock getMouseReleased() {
        return ((LgNamesGui)getStandards()).getGuiExecutingBlocks().getMouseReleased();
    }

    public ExecNamedFunctionBlock getMouseEntered() {
        return ((LgNamesGui)getStandards()).getGuiExecutingBlocks().getMouseEntered();
    }

    public ExecNamedFunctionBlock getMouseExited() {
        return ((LgNamesGui)getStandards()).getGuiExecutingBlocks().getMouseExited();
    }

    public ExecNamedFunctionBlock getMouseDragged() {
        return ((LgNamesGui)getStandards()).getGuiExecutingBlocks().getMouseDragged();
    }

    public ExecNamedFunctionBlock getMouseMoved() {
        return ((LgNamesGui)getStandards()).getGuiExecutingBlocks().getMouseMoved();
    }

    public ExecRootBlock getWheelListener() {
        return ((LgNamesGui)getStandards()).getGuiExecutingBlocks().getWheelListener();
    }

    public ExecNamedFunctionBlock getWheelMove() {
        return ((LgNamesGui)getStandards()).getGuiExecutingBlocks().getWheelMove();
    }

    public ExecRootBlock getWindowListener() {
        return ((LgNamesGui)getStandards()).getGuiExecutingBlocks().getWindowListener();
    }

    public ExecNamedFunctionBlock getWindowOpened() {
        return ((LgNamesGui)getStandards()).getGuiExecutingBlocks().getWindowOpened();
    }

    public ExecNamedFunctionBlock getWindowClosed() {
        return ((LgNamesGui)getStandards()).getGuiExecutingBlocks().getWindowClosed();
    }

    public ExecNamedFunctionBlock getWindowClosing() {
        return ((LgNamesGui)getStandards()).getGuiExecutingBlocks().getWindowClosing();
    }

    public ExecNamedFunctionBlock getWindowActivated() {
        return ((LgNamesGui)getStandards()).getGuiExecutingBlocks().getWindowActivated();
    }

    public ExecNamedFunctionBlock getWindowDeactivated() {
        return ((LgNamesGui)getStandards()).getGuiExecutingBlocks().getWindowDeactivated();
    }

    public ExecNamedFunctionBlock getWindowIconified() {
        return ((LgNamesGui)getStandards()).getGuiExecutingBlocks().getWindowIconified();
    }

    public ExecNamedFunctionBlock getWindowDeiconified() {
        return ((LgNamesGui)getStandards()).getGuiExecutingBlocks().getWindowDeiconified();
    }

    public ExecRootBlock getListSelection() {
        return ((LgNamesGui)getStandards()).getGuiExecutingBlocks().getListSelection();
    }

    public ExecNamedFunctionBlock getValueChanged() {
        return ((LgNamesGui)getStandards()).getGuiExecutingBlocks().getValueChanged();
    }

    public ExecRootBlock getChangeListener() {
        return ((LgNamesGui)getStandards()).getGuiExecutingBlocks().getChangeListener();
    }

    public ExecNamedFunctionBlock getStateChanged() {
        return ((LgNamesGui)getStandards()).getGuiExecutingBlocks().getStateChanged();
    }

    public ExecRootBlock getTreeListener() {
        return ((LgNamesGui)getStandards()).getGuiExecutingBlocks().getTreeListener();
    }

    public ExecNamedFunctionBlock getTreeListenerValueChanged() {
        return ((LgNamesGui)getStandards()).getGuiExecutingBlocks().getTreeListenerValueChanged();
    }

    public ExecRootBlock getTableListener() {
        return ((LgNamesGui)getStandards()).getGuiExecutingBlocks().getTableListener();
    }

    public ExecNamedFunctionBlock getTableValueTableChanged() {
        return ((LgNamesGui)getStandards()).getGuiExecutingBlocks().getTableValueTableChanged();
    }

    public ExecRootBlock getKeyListener() {
        return ((LgNamesGui)getStandards()).getGuiExecutingBlocks().getKeyListener();
    }

    public ExecNamedFunctionBlock getKeyPressed() {
        return ((LgNamesGui)getStandards()).getGuiExecutingBlocks().getKeyPressed();
    }

    public ExecNamedFunctionBlock getKeyReleased() {
        return ((LgNamesGui)getStandards()).getGuiExecutingBlocks().getKeyReleased();
    }

    public ExecNamedFunctionBlock getKeyTyped() {
        return ((LgNamesGui)getStandards()).getGuiExecutingBlocks().getKeyTyped();
    }

    public ExecRootBlock getPaint() {
        return ((LgNamesGui)getStandards()).getGuiExecutingBlocks().getPaint();
    }

    public ExecNamedFunctionBlock getPaintRefresh() {
        return ((LgNamesGui)getStandards()).getGuiExecutingBlocks().getPaintRefresh();
    }

    public ExecNamedFunctionBlock getPaintMethod() {
        return ((LgNamesGui)getStandards()).getGuiExecutingBlocks().getPaintMethod();
    }

    public ExecNamedFunctionBlock getPaintAdd() {
        return ((LgNamesGui)getStandards()).getGuiExecutingBlocks().getPaintAdd();
    }

    public FrameStruct getFrame() {
        return ((LgNamesGui)getStandards()).getGuiExecutingBlocks().getFrame();
    }

    public StringList getMainArgs() {
        return ((LgNamesGui)getStandards()).getGuiExecutingBlocks().getMainArgs();
    }
    public IntStruct stringWidth(FontStruct _font, Struct _string) {
        LgNamesGui standards_ = (LgNamesGui) getStandards();
        return standards_.getGuiExecutingBlocks().stringWidth(_font, _string);
    }

}
