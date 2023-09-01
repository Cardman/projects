package code.expressionlanguage.adv;

import code.expressionlanguage.exec.blocks.ExecFileBlock;
import code.expressionlanguage.exec.dbg.*;
import code.gui.AbsCommonFrame;
import code.gui.AbsPanel;
import code.gui.AbsPlainButton;
import code.gui.AbsScrollPane;
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
        frameExcFormContent = new FrameExcFormContent();
        frameStdFormContent = new FrameStdMpForm();
        frameFormContent = new FrameMpForm();
        frameWpFormContent = new FrameWpFormContent();
        frameBpFormContent = new FrameBpFormContent();
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
        addExc.addActionListener(new ExcPointBlockPairEvent(this,null));
        addStd = _d.getCommonFrame().getFrames().getCompoFactory().newPlainButton("add std");
        addStd.addActionListener(new StdPointBlockPairEvent(this,null));
        addWp = _d.getCommonFrame().getFrames().getCompoFactory().newPlainButton("add watch");
        addWp.addActionListener(new WpPointBlockPairEvent(this,null));
        addMet = _d.getCommonFrame().getFrames().getCompoFactory().newPlainButton("add method");
        addMet.addActionListener(new PointBlockPairEvent(this,null));
        addBp = _d.getCommonFrame().getFrames().getCompoFactory().newPlainButton("add method");
        addBp.addActionListener(new BreakPointBlockPairEvent(this,null));
        all_.add(bpForm);
        all_.add(wpForm);
        all_.add(excFrom);
        all_.add(metForm);
        all_.add(stdForm);
        all_.add(view);
        commonFrame.setContentPane(all_);
        frameExcFormContent.guiBuild(_d,this);
        frameStdFormContent.guiBuild(_d,this);
        frameFormContent.guiBuild(_d,this);
        frameWpFormContent.guiBuild(_d,this);
        frameBpFormContent.guiBuild(_d,this);
    }
    public void refresh(StringMap<String> _v) {
        frameExcFormContent.refresh(_v);
        frameStdFormContent.refresh(_v);
        frameFormContent.refresh(_v);
        frameWpFormContent.refresh(_v);
        frameBpFormContent.refresh(_v);
    }
    public void init(AbsDebuggerGui _d) {
        if (commonFrame.isVisible()) {
            return;
        }
        view.setNullViewportView();
        refreshExc(_d);
        frameStdFormContent.tree(_d,this);
        refreshStdMethod(_d);
        refreshWatch(_d);
        refreshMethod(_d);
        refreshBp(_d);
        commonFrame.setVisible(true);
        commonFrame.pack();
    }
    public void refreshBp(AbsDebuggerGui _d) {
        bpForm.removeAll();
        for (BreakPointBlockPair p: _d.getCurrentResult().bpList().elts()) {
            AbsPlainButton but_ = _d.getCommonFrame().getFrames().getCompoFactory().newPlainButton(ExecFileBlock.name(p.getBp().getFile())+":"+p.getBp().getOffset());
            but_.addActionListener(new BreakPointBlockPairEvent(this,p));
            bpForm.add(but_);
        }
        bpForm.add(addBp);
    }
    public void refreshExc(AbsDebuggerGui _d) {
        excFrom.removeAll();
        for (ExcPointBlockPair p: _d.getCurrentResult().getContext().excList().elts()) {
            AbsPlainButton but_ = _d.getCommonFrame().getFrames().getCompoFactory().newPlainButton();
            if (p.getEp().isExact()) {
                but_.setText("exact "+p.getEp().getClName());
            } else {
                but_.setText("inherit "+p.getEp().getClName());
            }
            but_.addActionListener(new ExcPointBlockPairEvent(this,p));
            excFrom.add(but_);
        }
        excFrom.add(addExc);
    }

    public void refreshStdMethod(AbsDebuggerGui _d) {
        stdForm.removeAll();
        for (StdMethodPointBlockPair p: _d.getCurrentResult().getContext().stdList().elts()) {
            AbsPlainButton but_ = _d.getCommonFrame().getFrames().getCompoFactory().newPlainButton();
            but_.setText(p.getSm().keyStr());
            but_.addActionListener(new StdPointBlockPairEvent(this,p));
            stdForm.add(but_);
        }
        stdForm.add(addStd);
    }

    public void refreshMethod(AbsDebuggerGui _d) {
        metForm.removeAll();
        for (MethodPointBlockPair p: _d.getCurrentResult().getContext().metList().elts()) {
            AbsPlainButton but_ = _d.getCommonFrame().getFrames().getCompoFactory().newPlainButton();
            but_.setText(p.getSgn());
            but_.addActionListener(new PointBlockPairEvent(this,p));
            metForm.add(but_);
        }
        metForm.add(addMet);
    }
    public void refreshWatch(AbsDebuggerGui _d) {
        wpForm.removeAll();
        for (WatchPointBlockPair p: _d.getCurrentResult().getContext().watchList().elts()) {
            AbsPlainButton but_ = _d.getCommonFrame().getFrames().getCompoFactory().newPlainButton(displayWatch(p));
            but_.addActionListener(new WpPointBlockPairEvent(this,p));
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

    public void guiContentBuild(ExcPointBlockPair _exc) {
        frameExcFormContent.initForm(_exc,commonFrame);
        view.setViewportView(frameExcFormContent.getContentPane());
        view.recalculateViewport();
    }

    public void guiContentBuild(StdMethodPointBlockPair _exc) {
        frameStdFormContent.initForm(_exc,commonFrame);
        view.setViewportView(frameStdFormContent.getContentPane());
        view.recalculateViewport();
    }

    public void guiContentBuild(WatchPointBlockPair _exc) {
        frameWpFormContent.initForm(_exc,commonFrame);
        view.setViewportView(frameWpFormContent.getContentPane());
        view.recalculateViewport();
    }

    public void guiContentBuild(MethodPointBlockPair _exc) {
        frameFormContent.initForm(_exc,commonFrame);
        view.setViewportView(frameFormContent.getContentPane());
        view.recalculateViewport();
    }

    public void guiContentBuild(BreakPointBlockPair _exc) {
        frameBpFormContent.initForm(_exc,commonFrame);
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
