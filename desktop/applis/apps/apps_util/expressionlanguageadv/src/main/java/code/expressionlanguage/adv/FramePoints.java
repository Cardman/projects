package code.expressionlanguage.adv;

import code.expressionlanguage.exec.blocks.ExecFileBlock;
import code.expressionlanguage.exec.dbg.*;
import code.expressionlanguage.options.ResultContext;
import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;
import code.util.StringMap;

public final class FramePoints {
    private final AbsCommonFrame commonFrame;
    private final FrameExcFormContent frameExcFormContent;
    private final FrameStdMpForm frameStdFormContent;
    private final FrameMpForm frameFormContent;
    private final FrameWpFormContent frameWpFormContent;
    private final FrameBpFormContent frameBpFormContent;
    private AbsPanel excFrom;
    private AbsPanel stdForm;
    private AbsPanel wpForm;
    private AbsPanel metForm;
    private AbsPanel bpForm;
    private AbsScrollPane view;
    private AbsPlainButton addExc;
    private AbsPlainButton addStd;
    private AbsPlainButton addWp;
    private AbsPlainButton addMet;
    private AbsPlainButton addBp;

    public FramePoints(AbsDebuggerGui _d, String _lg, AbstractProgramInfos _list) {
        commonFrame = _list.getFrameFactory().newCommonFrame(_lg, _list, null);
        commonFrame.addWindowListener(new CancelFramePointsEvent(_d));
        frameExcFormContent = new FrameExcFormContent(_list);
        frameStdFormContent = new FrameStdMpForm(_list);
        frameFormContent = new FrameMpForm(_list);
        frameWpFormContent = new FrameWpFormContent(_list);
        frameBpFormContent = new FrameBpFormContent(_list);
    }
    public void guiBuild(AbsDebuggerGui _d) {
        view = _d.getCommonFrame().getFrames().getCompoFactory().newAbsScrollPane();
        AbsPanel all_ = _d.getCommonFrame().getFrames().getCompoFactory().newLineBox();
        excFrom = _d.getCommonFrame().getFrames().getCompoFactory().newPageBox();
        stdForm = _d.getCommonFrame().getFrames().getCompoFactory().newPageBox();
        wpForm = _d.getCommonFrame().getFrames().getCompoFactory().newPageBox();
        metForm = _d.getCommonFrame().getFrames().getCompoFactory().newPageBox();
        bpForm = _d.getCommonFrame().getFrames().getCompoFactory().newPageBox();
        addExc = _d.getCommonFrame().getFrames().getCompoFactory().newPlainButton("add exc");
        addStd = _d.getCommonFrame().getFrames().getCompoFactory().newPlainButton("add std");
        addWp = _d.getCommonFrame().getFrames().getCompoFactory().newPlainButton("add watch");
        addMet = _d.getCommonFrame().getFrames().getCompoFactory().newPlainButton("add method");
        addBp = _d.getCommonFrame().getFrames().getCompoFactory().newPlainButton("add method");
        all_.add(bpForm);
        all_.add(wpForm);
        all_.add(excFrom);
        all_.add(metForm);
        all_.add(stdForm);
        all_.add(view);
        commonFrame.setContentPane(all_);
        frameExcFormContent.guiBuild(_d);
        frameStdFormContent.guiBuild(_d);
        frameFormContent.guiBuild(_d);
        frameWpFormContent.guiBuild(_d);
        frameBpFormContent.guiBuild(_d);
    }
    public void refresh(StringMap<String> _v, AbsDebuggerGui _d, ResultContext _r) {
        frameExcFormContent.refresh(_v, _r, _d, this);
        GuiBaseUtil.removeActionListeners(addExc);
        addExc.addActionListener(new ExcPointBlockPairEvent(this,null,_r));
        GuiBaseUtil.removeActionListeners(addStd);
        addStd.addActionListener(new StdPointBlockPairEvent(this,null,_r));
        GuiBaseUtil.removeActionListeners(addWp);
        addWp.addActionListener(new WpPointBlockPairEvent(this,null,_r));
        GuiBaseUtil.removeActionListeners(addMet);
        addMet.addActionListener(new PointBlockPairEvent(this,null,_r));
        GuiBaseUtil.removeActionListeners(addBp);
        addBp.addActionListener(new BreakPointBlockPairEvent(this,null,_r));
        GuiBaseUtil.removeActionListeners(frameStdFormContent.getOk());
        GuiBaseUtil.removeActionListeners(frameStdFormContent.getRemove());
        frameStdFormContent.getOk().addActionListener(new OkStdMpFormEvent(_d,frameStdFormContent, this, _r));
        frameStdFormContent.getRemove().addActionListener(new OkRemoveStdFormEvent(_d, frameStdFormContent, this, _r));
        GuiBaseUtil.removeActionListeners(frameFormContent.getOk());
        GuiBaseUtil.removeActionListeners(frameFormContent.getRemove());
        frameFormContent.getOk().addActionListener(new OkMpFormEvent(_d, _r));
        frameFormContent.getRemove().addActionListener(new OkRemoveMpFormEvent(_d, frameFormContent, this, _r));
        frameStdFormContent.refresh(_v, _r, _d);
        frameFormContent.refresh(_v, _r, _d);
        frameWpFormContent.refresh(_v, _r, _d, this);
        frameBpFormContent.refresh(_v, _r, _d, this);
    }
    public void init(AbsDebuggerGui _d, ResultContext _res) {
        if (commonFrame.isVisible()) {
            return;
        }
        view.setNullViewportView();
        refreshExc(_d, _res);
        frameStdFormContent.tree(_d,this, _res);
        refreshStdMethod(_d, _res);
        refreshWatch(_d, _res);
        refreshMethod(_d, _res);
        refreshBp(_d, _res);
        commonFrame.setVisible(true);
        commonFrame.pack();
    }
    public void refreshBp(AbsDebuggerGui _d, ResultContext _res) {
        bpForm.removeAll();
        for (BreakPointBlockPair p: _res.bpList().elts()) {
            AbsPlainButton but_ = _d.getCommonFrame().getFrames().getCompoFactory().newPlainButton(ExecFileBlock.name(p.getBp().getFile())+":"+p.getBp().getOffset());
            but_.addActionListener(new BreakPointBlockPairEvent(this,p,_res));
            bpForm.add(but_);
        }
        bpForm.add(addBp);
    }
    public void refreshExc(AbsDebuggerGui _d, ResultContext _res) {
        excFrom.removeAll();
        for (ExcPointBlockPair p: _res.getContext().excList().elts()) {
            AbsPlainButton but_ = _d.getCommonFrame().getFrames().getCompoFactory().newPlainButton();
            if (p.getEp().isExact()) {
                but_.setText("exact "+p.getEp().getClName());
            } else {
                but_.setText("inherit "+p.getEp().getClName());
            }
            but_.addActionListener(new ExcPointBlockPairEvent(this,p,_res));
            excFrom.add(but_);
        }
        excFrom.add(addExc);
    }

    public void refreshStdMethod(AbsDebuggerGui _d, ResultContext _res) {
        stdForm.removeAll();
        for (StdMethodPointBlockPair p: _res.getContext().stdList().elts()) {
            AbsPlainButton but_ = _d.getCommonFrame().getFrames().getCompoFactory().newPlainButton();
            but_.setText(p.getSm().keyStr());
            but_.addActionListener(new StdPointBlockPairEvent(this,p,_res));
            stdForm.add(but_);
        }
        stdForm.add(addStd);
    }

    public void refreshMethod(AbsDebuggerGui _d, ResultContext _res) {
        metForm.removeAll();
        for (MethodPointBlockPair p: _res.getContext().metList().elts()) {
            AbsPlainButton but_ = _d.getCommonFrame().getFrames().getCompoFactory().newPlainButton();
            but_.setText(p.getSgn());
            but_.addActionListener(new PointBlockPairEvent(this,p,_res));
            metForm.add(but_);
        }
        metForm.add(addMet);
    }
    public void refreshWatch(AbsDebuggerGui _d, ResultContext _res) {
        wpForm.removeAll();
        for (WatchPointBlockPair p: _res.getContext().watchList().elts()) {
            AbsPlainButton but_ = _d.getCommonFrame().getFrames().getCompoFactory().newPlainButton(displayWatch(p));
            but_.addActionListener(new WpPointBlockPairEvent(this,p,_res));
            wpForm.add(but_);
        }
        wpForm.add(addWp);
    }
    public static String displayWatch(WatchPointBlockPair _p) {
        if (_p.getWp().isTrueField()) {
            return "exact "+_p.getRoot().getFullName()+":"+_p.getWp().fieldName();
        }
        return "annot "+_p.getRoot().getFullName()+":"+_p.getWp().fieldName();
    }
    public AbsPanel getExcFrom() {
        return excFrom;
    }

    public AbsPanel getStdForm() {
        return stdForm;
    }

    public AbsPanel getMetForm() {
        return metForm;
    }

    public AbsPanel getBpForm() {
        return bpForm;
    }

    public AbsPanel getWpForm() {
        return wpForm;
    }

    public AbsPlainButton getAddExc() {
        return addExc;
    }

    public AbsPlainButton getAddBp() {
        return addBp;
    }

    public AbsPlainButton getAddMet() {
        return addMet;
    }

    public AbsPlainButton getAddWp() {
        return addWp;
    }

    public AbsPlainButton getAddStd() {
        return addStd;
    }

    public void guiContentBuild(ExcPointBlockPair _exc, ResultContext _r) {
        frameExcFormContent.initForm(_exc,commonFrame,_r);
        view.setViewportView(frameExcFormContent.getContentPane());
        view.recalculateViewport();
    }

    public void guiContentBuild(StdMethodPointBlockPair _exc, ResultContext _r) {
        frameStdFormContent.initForm(_exc,commonFrame,_r);
        view.setViewportView(frameStdFormContent.getContentPane());
        view.recalculateViewport();
    }

    public void guiContentBuild(WatchPointBlockPair _exc, ResultContext _r) {
        frameWpFormContent.initForm(_exc,commonFrame,_r);
        view.setViewportView(frameWpFormContent.getContentPane());
        view.recalculateViewport();
    }

    public void guiContentBuild(MethodPointBlockPair _exc, ResultContext _r) {
        frameFormContent.initForm(_exc,commonFrame,_r);
        view.setViewportView(frameFormContent.getContentPane());
        view.recalculateViewport();
    }

    public void guiContentBuild(BreakPointBlockPair _exc, ResultContext _r) {
        frameBpFormContent.initForm(_exc,commonFrame,_r);
        view.setViewportView(frameBpFormContent.getContentPane());
        view.recalculateViewport();
    }

    public void guiContentBuildClear() {
        view.setNullViewportView();
    }

    public FrameExcFormContent getFrameExcFormContent() {
        return frameExcFormContent;
    }

    public FrameStdMpForm getFrameStdFormContent() {
        return frameStdFormContent;
    }

    public AbsScrollPane getView() {
        return view;
    }

    public FrameWpFormContent getFrameWpFormContent() {
        return frameWpFormContent;
    }

    public FrameMpForm getFrameFormContent() {
        return frameFormContent;
    }

    public FrameBpFormContent getFrameBpFormContent() {
        return frameBpFormContent;
    }

    public AbsCommonFrame getCommonFrame() {
        return commonFrame;
    }
}
