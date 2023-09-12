package code.expressionlanguage.adv;

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
    private final FrameArrFormContent frameArrFormContent;
    private final FramePointsTree framePointsTree;
    private AbsScrollPane view;

    public FramePoints(AbsDebuggerGui _d, String _lg, AbstractProgramInfos _list) {
        framePointsTree = new FramePointsTree(_d.getCompoFactory());
        commonFrame = _list.getFrameFactory().newCommonFrame(_lg, _list, null);
        commonFrame.addWindowListener(new CancelFramePointsEvent(_d));
        frameExcFormContent = new FrameExcFormContent(_list);
        frameStdFormContent = new FrameStdMpForm(_list);
        frameFormContent = new FrameMpForm(_list);
        frameWpFormContent = new FrameWpFormContent(_list);
        frameBpFormContent = new FrameBpFormContent(_list);
        frameArrFormContent = new FrameArrFormContent(_list);
    }
    public void guiBuild(AbsDebuggerGui _d) {
        view = _d.getCommonFrame().getFrames().getCompoFactory().newAbsScrollPane();
        AbsPanel all_ = _d.getCommonFrame().getFrames().getCompoFactory().newLineBox();
        framePointsTree.guiBuild();
        all_.add(commonFrame.getFrames().getCompoFactory().newAbsScrollPane(framePointsTree.getTree()));
        all_.add(framePointsTree.getCreate());
        all_.add(view);
        commonFrame.setContentPane(all_);
        frameExcFormContent.guiBuild(_d);
        frameStdFormContent.guiBuild(_d);
        frameFormContent.guiBuild(_d);
        frameWpFormContent.guiBuild(_d);
        frameBpFormContent.guiBuild(_d);
        frameArrFormContent.guiBuild(_d);
    }
    public void refresh(StringMap<String> _v, AbsDebuggerGui _d, ResultContext _r) {
        frameExcFormContent.refresh(_v, _r, _d, this);
        GuiBaseUtil.removeActionListeners(frameStdFormContent.getOk());
        GuiBaseUtil.removeActionListeners(frameStdFormContent.getRemove());
        frameStdFormContent.getOk().addActionListener(new OkStdMpFormEvent(_d,frameStdFormContent, this, _r));
        frameStdFormContent.getRemove().addActionListener(new OkRemoveStdFormEvent(frameStdFormContent, this, _r));
        GuiBaseUtil.removeActionListeners(frameFormContent.getOk());
        GuiBaseUtil.removeActionListeners(frameFormContent.getRemove());
        frameFormContent.getOk().addActionListener(new OkMpFormEvent(_d, _r));
        frameFormContent.getRemove().addActionListener(new OkRemoveMpFormEvent(_d, frameFormContent, this, _r));
        frameStdFormContent.refresh(_v, _r, _d);
        frameFormContent.refresh(_v, _r, _d);
        frameWpFormContent.refresh(_v, _r, _d, this);
        frameBpFormContent.refresh(_v, _r, _d, this);
        frameArrFormContent.refresh(_v, _r, _d, this);
    }
    public void init(AbsDebuggerGui _d, ResultContext _res) {
        if (commonFrame.isVisible()) {
            return;
        }
        view.setNullViewportView();
        framePointsTree.init(this,_res);
        frameStdFormContent.tree(_d,this, _res);
        commonFrame.setVisible(true);
        commonFrame.pack();
    }
    public void refreshBp(ResultContext _res) {
        framePointsTree.refreshBp(_res);
    }
    public void refreshArr(ResultContext _res) {
        framePointsTree.refreshArray(_res);
    }
    public void refreshExc(ResultContext _res) {
        framePointsTree.refreshException(_res);
    }

    public void refreshStdMethod(ResultContext _res) {
        framePointsTree.refreshStdMethod(_res);
    }

    public void refreshMethod(ResultContext _res) {
        framePointsTree.refreshMethod(_res);
    }
    public void refreshWatch(ResultContext _res) {
        framePointsTree.refreshWp(_res);
        framePointsTree.refreshWpAnnot(_res);
    }
    public static String displayWatch(WatchPointBlockPair _p) {
        if (_p.getWp().isTrueField()) {
            return "exact "+_p.getRoot().getFullName()+":"+_p.getWp().fieldName();
        }
        return "annot "+_p.getRoot().getFullName()+":"+_p.getWp().fieldName();
    }
    public FramePointsTree getFramePointsTree() {
        return framePointsTree;
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

    public void guiContentBuild(ArrPointBlockPair _exc, ResultContext _r) {
        frameArrFormContent.initForm(_exc,commonFrame,_r);
        view.setViewportView(frameArrFormContent.getContentPane());
        view.recalculateViewport();
    }

    public void guiContentBuildClear() {
        view.setNullViewportView();
    }

    public FrameExcFormContent getFrameExcFormContent() {
        return frameExcFormContent;
    }

    public FrameArrFormContent getFrameArrFormContent() {
        return frameArrFormContent;
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
