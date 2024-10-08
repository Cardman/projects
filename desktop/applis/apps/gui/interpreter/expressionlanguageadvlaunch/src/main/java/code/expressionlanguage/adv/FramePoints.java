package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.*;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.stds.AbstractInterceptorStdCaller;
import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;
import code.sml.util.TranslationsLg;
import code.util.CustList;
import code.util.EntryCust;
import code.util.NatStringTreeMap;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class FramePoints {
    private final AbsCommonFrame commonFrame;
    private final FrameExcFormContent frameExcFormContent;
    private final FrameStdMpForm frameStdFormContent;
    private final FrameMpForm frameFormContent;
    private final FrameWpFormContent frameWpFormContent;
    private final FrameBpFormContent frameBpFormContent;
    private final FrameTpFormContent frameTpFormContent;
    private final FrameArrFormContent frameArrFormContent;
    private final FrameParFormContent frameParFormContent;
    private final FrameOperNatFormContent frameOperNatFormContent;
    private final FrameOperNatCompoFormContent frameOperNatCompoFormContent;
    private final FramePointsTree framePointsTree;
    private final FrameRenderFormContent frameRenderFormContent;
    private AbsScrollPane view;
    private AbsScrollPane viewRp;
    private final StackConstraintsForm stackConstraintsForm;
    private AbsButton validStack;
    private AbsTreeGui tree;
    private AbsButton create;
    private final NatStringTreeMap<CustList<RenderPointPair>> renderList = new NatStringTreeMap<CustList<RenderPointPair>>();
    private AbstractInterceptorStdCaller caller;
    private final AbstractProgramInfos frames;

    public FramePoints(AbsDebuggerGui _d, AbstractProgramInfos _list) {
        frames = _list;
        framePointsTree = new FramePointsTree(_list);
        commonFrame = _list.getFrameFactory().newCommonFrame();
        commonFrame.addWindowListener(new CancelFramePointsEvent(_d));
        frameExcFormContent = new FrameExcFormContent(_list);
        frameStdFormContent = new FrameStdMpForm(_list);
        frameFormContent = new FrameMpForm(_list);
        frameWpFormContent = new FrameWpFormContent(_list);
        frameBpFormContent = new FrameBpFormContent(_list);
        frameTpFormContent = new FrameTpFormContent(_list);
        frameArrFormContent = new FrameArrFormContent(_list);
        frameParFormContent = new FrameParFormContent(_list);
        frameOperNatFormContent = new FrameOperNatFormContent(_list);
        frameOperNatCompoFormContent = new FrameOperNatCompoFormContent(_list);
        frameRenderFormContent = new FrameRenderFormContent(_list);
        stackConstraintsForm = new StackConstraintsForm(_list);
    }
    public void guiBuild(AbsDebuggerGui _d) {
        StringMap<String> mes_ = MessagesIde.valGlForm(_d.getFrames().currentLg());
        view = _d.getFrames().getCompoFactory().newAbsScrollPane();
        viewRp = _d.getFrames().getCompoFactory().newAbsScrollPane();
        AbsTabbedPane tab_ = _d.getFrames().getCompoFactory().newAbsTabbedPane();
        AbsPanel pointsKeys_ = _d.getFrames().getCompoFactory().newPageBox();
        framePointsTree.guiBuild(_d.getFrames());
        pointsKeys_.add(_d.getFrames().getCompoFactory().newAbsScrollPane(framePointsTree.getTree()));
        pointsKeys_.add(framePointsTree.getCreate());
        validStack = _d.getFrames().getCompoFactory().newPlainButton(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_GL_FORM_STACK)));
        AbsSplitPane pageStack_ = stackConstraintsForm.guiBuild(_d,validStack);
        AbstractMutableTreeNodeCore<String> root_ = _d.getFrames().getCompoFactory().newMutableTreeNode(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_GL_FORM_REND)));
        tree = _d.getFrames().getCompoFactory().newTreeGui(root_);
        create = _d.getFrames().getCompoFactory().newPlainButton("+");
        AbsPanel renderPts_ = _d.getFrames().getCompoFactory().newPageBox();
        renderPts_.add(_d.getFrames().getCompoFactory().newAbsScrollPane(tree));
        renderPts_.add(create);
        frameRenderFormContent.guiBuild(_d);
        AbsPanel all_ = _d.getFrames().getCompoFactory().newPageBox();
        tab_.addIntTab(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_GL_FORM_POINTS)),_d.getFrames().getCompoFactory().newHorizontalSplitPane(pointsKeys_,view));
        tab_.addIntTab(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_GL_FORM_CONST)),_d.getFrames().getCompoFactory().newAbsScrollPane(pageStack_));
        tab_.addIntTab(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_GL_FORM_REND)),_d.getFrames().getCompoFactory().newHorizontalSplitPane(_d.getFrames().getCompoFactory().newAbsScrollPane(renderPts_),viewRp));
        all_.add(tab_);
        commonFrame.setContentPane(all_);
        frameExcFormContent.guiBuild(_d);
        frameStdFormContent.guiBuild(_d);
        frameFormContent.guiBuild(_d);
        frameWpFormContent.guiBuild(_d);
        frameBpFormContent.guiBuild(_d);
        frameTpFormContent.guiBuild(_d);
        frameArrFormContent.guiBuild(_d);
        frameParFormContent.guiBuild(_d);
        frameOperNatFormContent.guiBuild(_d);
        frameOperNatCompoFormContent.guiBuild(_d);
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
        frameTpFormContent.refresh(_v, _r, _d, this);
        frameArrFormContent.refresh(_v, _r, _d, this);
        frameParFormContent.refresh(_v, _r, _d, this);
        frameOperNatFormContent.refresh(_v, _r, _d, this);
        frameOperNatCompoFormContent.refresh(_v, _r, _d, this);
        frameRenderFormContent.refresh(_r, _d, this);
        stackConstraintsForm.refresh(_v,AbsEditorTabList.EMPTY_STRING,_r,_d);
        GuiBaseUtil.removeActionListeners(validStack);
        validStack.addActionListener(new ValidateStepStackConstraintsEvent(_r,this));
        BreakPointFormEvent.feed(stackConstraintsForm.getMustBe(), _r.getBreakPointsBlock().getInclude());
        BreakPointFormEvent.feed(stackConstraintsForm.getMustNotBe(), _r.getBreakPointsBlock().getExclude());
    }
    public void init(AbsDebuggerGui _d, ResultContext _res) {
        if (commonFrame.isVisible()) {
            return;
        }
        caller = _d.getCaller();
        refreshRender(_d.getRenderList());
        GuiBaseUtil.removeActionListeners(create);
        create.addActionListener(new TreeRenderPointBlockPairAddEvent(_d.getRenderList(),_res,this));
        listenerSelect(_d.getRenderList(), _res);
        refreshPoints(_d, _res);
    }
    public void refreshPoints(AbsDebuggerGui _d, ResultContext _res) {
        view.setNullViewportView();
        framePointsTree.init(this,_res);
        frameStdFormContent.tree(_d,this, _res);
        commonFrame.setVisible(true);
        commonFrame.pack();
    }
    public void refreshRender(CustList<RenderPointPair> _res) {
        guiContentBuildClearRp();
        AbstractMutableTreeNodeCore<String> root_ = tree.getRoot();
        root_.removeAllChildren();
        AbsCollection<ExcPointBlockPair> p_ = caller.newExcPointKeyStringCollection();
        int s_ = _res.size();
        for (int i = 0; i < s_; i++) {
            p_.add(_res.get(i).getExcPointBlockPair());
        }
        for (EntryCust<String, CustList<ExcPointBlockKey>> p: FramePointsTree.sortedRend(renderList, _res).entryList()) {
            AbstractMutableTreeNodeCore<String> file_ = FramePointsTree.node(p.getKey(), p.getValue(),frames.getCompoFactory(), frames);
            root_.add(file_);
        }
        tree.reload(root_);
    }

    public void listenerSelect(CustList<RenderPointPair> _bpc,ResultContext _res) {
        GuiBaseUtil.removeTreeSelectionListeners(tree);
        tree.addTreeSelectionListener(new TreeRenderPointBlockPairEvent(_bpc,this, _res));
    }
    public void refreshBp(ResultContext _res) {
        framePointsTree.refreshBp(_res);
    }
    public void refreshTp(ResultContext _res) {
        framePointsTree.refreshTp(_res);
    }
    public void refreshArr(ResultContext _res) {
        framePointsTree.refreshArray(_res);
    }
    public void refreshExc(ResultContext _res) {
        framePointsTree.refreshException(_res);
    }
    public void refreshParent(ResultContext _res) {
        framePointsTree.refreshParent(_res);
    }
    public void refreshOperNat(ResultContext _res) {
        framePointsTree.refreshOperNat(_res);
    }
    public void refreshOperNatCompo(ResultContext _res) {
        framePointsTree.refreshOperNatCompo(_res);
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
    public static String displayWatch(WatchPointBlockPair _p, TranslationsLg _lg) {
        StringMap<String> mes_ = MessagesIde.valGlForm(_lg);
        if (_p.getWp().isTrueField()) {
            return mes_.getVal(MessagesIde.IDE_POINTS_GL_FORM_EXACT)+" "+_p.getRoot().getFullName()+":"+_p.getWp().fieldName();
        }
        return mes_.getVal(MessagesIde.IDE_POINTS_GL_FORM_ANNOT)+" "+_p.getRoot().getFullName()+":"+_p.getWp().fieldName();
    }
    public FramePointsTree getFramePointsTree() {
        return framePointsTree;
    }

    public void guiContentBuild(CustList<RenderPointPair> _bpc,RenderPointPair _exc, ResultContext _r) {
        frameRenderFormContent.initForm(_bpc,_exc,commonFrame,_r);
        viewRp.setViewportView(frameRenderFormContent.getContentPane());
        viewRp.recalculateViewport();
    }
    public void guiContentBuild(ExcPointBlockPair _exc, ResultContext _r) {
        frameExcFormContent.initForm(_exc,commonFrame,_r);
        view.setViewportView(frameExcFormContent.getContentPane());
        view.recalculateViewport();
    }

    public void guiContentBuild(ParPointBlockPair _exc, ResultContext _r) {
        frameParFormContent.initForm(_exc,commonFrame,_r);
        view.setViewportView(frameParFormContent.getContentPane());
        view.recalculateViewport();
    }

    public void guiContentBuildNotCompo(OperNatPointBlockPair _exc, ResultContext _r) {
        frameOperNatFormContent.initForm(_exc, commonFrame, _r);
        view.setViewportView(frameOperNatFormContent.getContentPane());
        view.recalculateViewport();
    }

    public void guiContentBuild(OperNatPointBlockPair _exc, ResultContext _r) {
        frameOperNatCompoFormContent.initForm(_exc,commonFrame,_r);
        view.setViewportView(frameOperNatCompoFormContent.getContentPane());
        view.recalculateViewport();
    }

    public void guiContentBuildNotCompo(ResultContext _r) {
        frameOperNatFormContent.initForm(null,commonFrame,_r);
        view.setViewportView(frameOperNatFormContent.getContentPane());
        view.recalculateViewport();
    }

    public void guiContentBuildCompo(ResultContext _r) {
        frameOperNatCompoFormContent.initForm(null,commonFrame,_r);
        view.setViewportView(frameOperNatCompoFormContent.getContentPane());
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

    public void guiContentBuild(TypePointBlockPair _exc, ResultContext _r) {
        frameTpFormContent.initForm(_exc,commonFrame,_r);
        view.setViewportView(frameTpFormContent.getContentPane());
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

    public void guiContentBuildClearRp() {
        viewRp.setNullViewportView();
    }

    public AbsTreeGui getTree() {
        return tree;
    }

    public AbsButton getCreate() {
        return create;
    }

    public FrameRenderFormContent getFrameRenderFormContent() {
        return frameRenderFormContent;
    }

    public NatStringTreeMap<CustList<RenderPointPair>> getRenderList() {
        return renderList;
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

    public FrameTpFormContent getFrameTpFormContent() {
        return frameTpFormContent;
    }

    public FrameOperNatFormContent getFrameOperNatFormContent() {
        return frameOperNatFormContent;
    }

    public FrameOperNatCompoFormContent getFrameOperNatCompoFormContent() {
        return frameOperNatCompoFormContent;
    }

    public FrameParFormContent getFrameParFormContent() {
        return frameParFormContent;
    }

    public StackConstraintsForm getStackConstraintsForm() {
        return stackConstraintsForm;
    }

    public AbsButton getValidStack() {
        return validStack;
    }

    public AbsCommonFrame getCommonFrame() {
        return commonFrame;
    }
}
