package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.ExcPointBlockPair;
import code.expressionlanguage.exec.dbg.StdMethodPointBlockPair;
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
    private AbsPanel excFrom;
    private AbsPanel excStd;
    private AbsScrollPane view;
    private AbsPlainButton addExc;
    private AbsPlainButton addStd;

    public FramePoints(AbsDebuggerGui _d, String _lg, AbstractProgramInfos _list) {
        commonFrame = _list.getFrameFactory().newCommonFrame(_lg, _list, null);
        commonFrame.addWindowListener(new CancelFramePointsEvent(_d));
        frameExcFormContent = new FrameExcFormContent();
        frameStdFormContent = new FrameStdMpForm();
    }
    public void guiBuild(AbsDebuggerGui _d) {
        view = _d.getCommonFrame().getFrames().getCompoFactory().newAbsScrollPane();
        AbsPanel all_ = _d.getCommonFrame().getFrames().getCompoFactory().newLineBox();
        excFrom = _d.getCommonFrame().getFrames().getCompoFactory().newPageBox();
        excStd = _d.getCommonFrame().getFrames().getCompoFactory().newPageBox();
        addExc = _d.getCommonFrame().getFrames().getCompoFactory().newPlainButton("add exc");
        addExc.addActionListener(new ExcPointBlockPairEvent(this,null));
        addStd = _d.getCommonFrame().getFrames().getCompoFactory().newPlainButton("add std");
        addStd.addActionListener(new StdPointBlockPairEvent(this,null));
        all_.add(excFrom);
        all_.add(excStd);
        all_.add(view);
        commonFrame.setContentPane(all_);
        frameExcFormContent.guiBuild(_d,this);
        frameStdFormContent.guiBuild(_d,this);
    }
    public void refresh(StringMap<String> _v) {
        frameExcFormContent.refresh(_v);
        frameStdFormContent.refresh(_v);
    }
    public void init(AbsDebuggerGui _d) {
        if (commonFrame.isVisible()) {
            return;
        }
        view.setNullViewportView();
        refreshExc(_d);
        frameStdFormContent.tree(_d,this);
        refreshStdMethod(_d);
        commonFrame.setVisible(true);
        commonFrame.pack();
    }

    public void refreshExc(AbsDebuggerGui _d) {
        excFrom.removeAll();
        for (ExcPointBlockPair p: _d.getCurrentResult().getContext().getClasses().getDebugMapping().getBreakPointsBlock().getExcPointList().elts()) {
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
        excStd.removeAll();
        for (StdMethodPointBlockPair p: _d.getCurrentResult().getContext().getClasses().getDebugMapping().getBreakPointsBlock().getStdMethPointList().elts()) {
            AbsPlainButton but_ = _d.getCommonFrame().getFrames().getCompoFactory().newPlainButton();
            but_.setText(p.getSm().keyStr());
            but_.addActionListener(new StdPointBlockPairEvent(this,p));
            excStd.add(but_);
        }
        excStd.add(addStd);
    }
    public AbsPanel getExcFrom() {
        return excFrom;
    }

    public AbsPanel getExcStd() {
        return excStd;
    }

    public AbsPlainButton getAddExc() {
        return addExc;
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

    public void guiContentBuildClear() {
        view.setNullViewportView();
    }

    public FrameExcFormContent getFrameExcFormContent() {
        return frameExcFormContent;
    }

    public FrameStdMpForm getFrameStdFormContent() {
        return frameStdFormContent;
    }

    public AbsCommonFrame getCommonFrame() {
        return commonFrame;
    }
}
