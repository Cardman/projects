package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.ExcPointBlockPair;
import code.gui.AbsCommonFrame;
import code.gui.AbsPanel;
import code.gui.AbsPlainButton;
import code.gui.AbsScrollPane;
import code.gui.initialize.AbstractProgramInfos;
import code.util.StringMap;

public final class FramePoints {
    private final AbsCommonFrame commonFrame;
    private final FrameExcFormContent frameExcFormContent;
    private AbsPanel excFrom;
    private AbsScrollPane view;
    private AbsPlainButton addExc;

    public FramePoints(AbsDebuggerGui _d, String _lg, AbstractProgramInfos _list) {
        commonFrame = _list.getFrameFactory().newCommonFrame(_lg, _list, null);
        commonFrame.addWindowListener(new CancelFramePointsEvent(_d));
        frameExcFormContent = new FrameExcFormContent();
    }
    public void guiBuild(AbsDebuggerGui _d) {
        view = _d.getCommonFrame().getFrames().getCompoFactory().newAbsScrollPane();
        AbsPanel all_ = _d.getCommonFrame().getFrames().getCompoFactory().newLineBox();
        excFrom = _d.getCommonFrame().getFrames().getCompoFactory().newPageBox();
        addExc = _d.getCommonFrame().getFrames().getCompoFactory().newPlainButton("add exc");
        addExc.addActionListener(new ExcPointBlockPairEvent(this,null));
        all_.add(excFrom);
        all_.add(view);
        commonFrame.setContentPane(all_);
        frameExcFormContent.guiBuild(_d,this);
    }
    public void refresh(StringMap<String> _v) {
        frameExcFormContent.refresh(_v);
    }
    public void init(AbsDebuggerGui _d) {
        if (commonFrame.isVisible()) {
            return;
        }
        view.setNullViewportView();
        refreshExc(_d);
        commonFrame.setVisible(true);
        commonFrame.pack();
    }

    public void refreshExc(AbsDebuggerGui _d) {
        excFrom.removeAll();
        for (ExcPointBlockPair p: _d.getCurrentResult().getContext().getClasses().getDebugMapping().getBreakPointsBlock().getExcPointList().elts()) {
            AbsPlainButton but_ = _d.getCommonFrame().getFrames().getCompoFactory().newPlainButton();
            if (p.isExact()) {
                but_.setText("exact "+p.getClName());
            } else {
                but_.setText("inherit "+p.getClName());
            }
            but_.addActionListener(new ExcPointBlockPairEvent(this,p));
            excFrom.add(but_);
        }
        excFrom.add(addExc);
    }

    public AbsPanel getExcFrom() {
        return excFrom;
    }

    public AbsPlainButton getAddExc() {
        return addExc;
    }

    public void guiContentBuild(ExcPointBlockPair _exc) {
        frameExcFormContent.initForm(_exc);
        view.setViewportView(frameExcFormContent.getContentPane());
    }

    public void guiContentBuildClear() {
        view.setNullViewportView();
    }

    public FrameExcFormContent getFrameExcFormContent() {
        return frameExcFormContent;
    }

    public AbsCommonFrame getCommonFrame() {
        return commonFrame;
    }
}
