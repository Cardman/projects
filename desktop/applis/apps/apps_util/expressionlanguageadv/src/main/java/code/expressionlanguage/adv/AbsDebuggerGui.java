package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.ExecFileBlock;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.CallingState;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.dbg.BreakPoint;
import code.expressionlanguage.exec.dbg.BreakPointBlockPair;
import code.expressionlanguage.exec.variables.ViewPage;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.utilimpl.ManageOptions;
import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;
import code.stream.BytesInfo;
import code.stream.StreamTextFile;
import code.threads.AbstractAtomicBoolean;
import code.threads.AbstractBaseExecutorService;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.DefaultUniformingString;
import code.util.core.StringUtil;

public abstract class AbsDebuggerGui extends AbsEditorTabList {
    private final CdmFactory factory;
    private final AbsCommonFrame commonFrame;
    private BreakPointBlockPair selectedPb;
    private AbsCustCheckBox instanceType;
    private AbsTextArea conditionalInstance;
    private AbsCustCheckBox staticType;
    private AbsTextArea conditionalStatic;
    private AbsCustCheckBox enabledBp;
    private AbsTextArea conditionalStd;
    private AbsPlainButton cancel;
    private AbsPlainButton ok;
    private AbsPanel bpForm;
    private AbsTabbedPane tabbedPane;
    private final CustList<ReadOnlyTabEditor> tabs = new CustList<ReadOnlyTabEditor>();
    private CallingState selected = new CustomFoundExc(NullStruct.NULL_VALUE);
    private final AbstractBaseExecutorService debugActions;
    private AbsPlainButton selectEnter;
    private AbsPlainButton nextAction;
    private AbsPlainButton nextInstruction;
    private AbsPlainButton nextGoUp;
    private AbsPlainButton nextInMethod;
    private AbsPlainButton nextCursor;
    private AbsScrollPane detail;
    private AbsSplitPane detailAll;
    private StackCall stackCall;
    private ResultContext currentResult;
    private final AbstractBaseExecutorService manageAnalyze;
    private DbgRootStruct root;
    private AbsTreeGui treeDetail;
    private final CustList<AbsTreeGui> trees = new CustList<AbsTreeGui>();
    private final CustList<DbgRootStruct> treesRoot = new CustList<DbgRootStruct>();
    private final CustList<AbsPlainButton> callButtons = new CustList<AbsPlainButton>();
    private AbsPanel callStack;
    private AbstractAtomicBoolean stopDbg;

    protected AbsDebuggerGui(String _lg, AbstractProgramInfos _list, CdmFactory _fact) {
        factory = _fact;
        commonFrame = _list.getFrameFactory().newCommonFrame(_lg, _list, null);
        debugActions = _list.getThreadFactory().newExecutorService();
        manageAnalyze = _list.getThreadFactory().newExecutorService();
        stopDbg = _list.getThreadFactory().newAtomicBoolean();
    }

    public void build(ResultContext _b,ManageOptions _man, StringMap<String> _src) {
        guiBuild(_man, _src);
        manageAnalyze.submit(new AnalyzeDebugTask(_b,this,_src));
    }

    public void guiBuild(ManageOptions _man, StringMap<String> _src) {
        instanceType = getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("instance");
        conditionalInstance = getCommonFrame().getFrames().getCompoFactory().newTextArea();
        staticType = getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("static");
        conditionalStatic = getCommonFrame().getFrames().getCompoFactory().newTextArea();
        enabledBp = getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("enabled");
        conditionalStd = getCommonFrame().getFrames().getCompoFactory().newTextArea();
        cancel = getCommonFrame().getFrames().getCompoFactory().newPlainButton("cancel");
        ok = getCommonFrame().getFrames().getCompoFactory().newPlainButton("ok");
        bpForm = getCommonFrame().getFrames().getCompoFactory().newPageBox();
        bpForm.add(enabledBp);
        bpForm.add(conditionalStd);
        bpForm.add(instanceType);
        bpForm.add(conditionalInstance);
        bpForm.add(staticType);
        bpForm.add(conditionalStatic);
        ok.addActionListener(new OkBpFormEvent(this));
        bpForm.add(ok);
        cancel.addActionListener(new CancelBpFormEvent(this));
        bpForm.add(cancel);
        bpForm.setVisible(false);
        AbsPanel page_ = commonFrame.getFrames().getCompoFactory().newPageBox();
        tabbedPane = commonFrame.getFrames().getCompoFactory().newAbsTabbedPane();
        int len_ = _src.size();
        for (int i = 0; i < len_; i++) {
            String fullPath_ = pathToSrc(_man)+ _src.getKey(i);
            BytesInfo content_ = new BytesInfo(StringUtil.encode(_src.getValue(i)),false);
            addTab(_man,fullPath_,content_, _man.getOptions());
        }
        page_.add(tabbedPane);
        page_.add(bpForm);
        page_.add(buildPart());
        selectEnter = getCommonFrame().getFrames().getCompoFactory().newPlainButton("|>");
        selectEnter.addActionListener(new DbgLaunchEvent(this));
        selectEnter.setEnabled(false);
        nextAction = getCommonFrame().getFrames().getCompoFactory().newPlainButton(">>");
        nextAction.setEnabled(false);
        nextAction.addActionListener(new DbgNextBpEvent(this, StepDbgActionEnum.KEEP));
        nextInstruction = getCommonFrame().getFrames().getCompoFactory().newPlainButton(">");
        nextInstruction.setEnabled(false);
        nextInstruction.addActionListener(new DbgNextBpEvent(this, StepDbgActionEnum.NEXT_INSTRUCTION));
        nextGoUp = getCommonFrame().getFrames().getCompoFactory().newPlainButton("^");
        nextGoUp.setEnabled(false);
        nextGoUp.addActionListener(new DbgNextBpEvent(this, StepDbgActionEnum.RETURN_METHOD));
        nextInMethod = getCommonFrame().getFrames().getCompoFactory().newPlainButton("=");
        nextInMethod.setEnabled(false);
        nextInMethod.addActionListener(new DbgNextBpEvent(this, StepDbgActionEnum.NEXT_IN_METHOD));
        nextCursor = getCommonFrame().getFrames().getCompoFactory().newPlainButton("_");
        nextCursor.setEnabled(false);
        nextCursor.addActionListener(new DbgNextBpEvent(this, StepDbgActionEnum.CURSOR));
        detail = getCommonFrame().getFrames().getCompoFactory().newAbsScrollPane();
        callStack = getCommonFrame().getFrames().getCompoFactory().newPageBox();
        detailAll = getCommonFrame().getFrames().getCompoFactory().newHorizontalSplitPane(callStack,detail);
        detailAll.setVisible(false);
        page_.add(selectEnter);
        page_.add(nextAction);
        page_.add(nextInstruction);
        page_.add(nextGoUp);
        page_.add(nextInMethod);
        page_.add(nextCursor);
        page_.add(detailAll);
        commonFrame.setContentPane(page_);
        commonFrame.setVisible(true);
        PackingWindowAfter.pack(commonFrame);
    }

    protected abstract AbsPanel buildPart();
    void addTab(ManageOptions _man,String _path, BytesInfo _content, Options _opt) {
        String dec_ = StringUtil.nullToEmpty(StringUtil.decode(_content.getBytes()));
        String name_ = _path.substring(_path.lastIndexOf('/')+1);
        ReadOnlyTabEditor te_ = new ReadOnlyTabEditor(this,commonFrame.getFrames(), _path.substring(pathToSrc(_man).length()), WindowWithTreeImpl.lineSeparator(dec_),_opt);
        te_.getCenter().setText(new DefaultUniformingString().apply(dec_));
        tabs.add(te_);
        tabbedPane.addIntTab(name_, te_.getPanel(), _path);
    }
    void next(StepDbgActionEnum _step){
        if (_step == StepDbgActionEnum.CURSOR) {
            int s_ = tabbedPane.getSelectedIndex();
            possibleSelect(s_);
        }
        StackCallReturnValue view_ = ExecClassesUtil.tryInitStaticlyTypes(currentResult.getContext(), currentResult.getForwards().getOptions(), stackCall, selected,_step);
        stackCall = view_.getStack();
        if (stackCall.getInitializingTypeInfos().getInitEnums() == InitPhase.NOTHING && !stackCall.isStoppedBreakPoint()) {
            callStack.removeAll();
            callButtons.clear();
            root = new DbgRootStruct(currentResult);
            treeDetail = root.buildReturn(commonFrame.getFrames().getCompoFactory(), view_.getRetValue());
            detail.setViewportView(treeDetail);
            detailAll.setVisible(true);
            PackingWindowAfter.pack(commonFrame);
            selectEnter.setEnabled(true);
            int opened_ = tabbedPane.getSelectedIndex();
            focus(opened_);
            stackCall = null;
            return;
        }
        trees.clear();
        treesRoot.clear();
        callButtons.clear();
        callStack.removeAll();
        int nbPages_ = view_.getVariables().size();
        for (int i = 0; i< nbPages_; i++) {
            ViewPage p_ = view_.getVariables().get(i);
            String dis_ = MetaInfoUtil.newStackTraceElement(currentResult.getContext(), i, stackCall).getDisplayedString(currentResult.getContext()).getInstance();
            DbgRootStruct r_ = new DbgRootStruct(currentResult);
            root = r_;
            AbsTreeGui b_ = r_.build(commonFrame.getFrames().getCompoFactory(), p_);
            treeDetail = b_;
            trees.add(b_);
            treesRoot.add(r_);
            AbsPlainButton but_ = commonFrame.getFrames().getCompoFactory().newPlainButton(dis_);
            callButtons.add(but_);
            but_.addActionListener(new SelectCallStackEvent(this,i));
            callStack.add(but_);
        }
        AbstractPageEl last_ = stackCall.getLastPage();
        int opened_ = indexOpened(last_.getFile().getFileName());
        selectFocus(opened_, last_.getTraceIndex());
        detail.setViewportView(treeDetail);
        detailAll.setVisible(true);
        PackingWindowAfter.pack(commonFrame);
        nextAction.setEnabled(true);
        nextInstruction.setEnabled(true);
        nextGoUp.setEnabled(true);
        nextInMethod.setEnabled(true);
        nextCursor.setEnabled(true);
    }

    public void possibleSelect(int _s) {
        if (_s > -1) {
            FileBlock f_ = currentResult.getPageEl().getPreviousFilesBodies().getVal(tabs.get(_s).getFullPath());
            ExecFileBlock e_ = currentResult.getContext().getClasses().getDebugMapping().getFiles().getVal(f_);
            currentResult.getContext().getClasses().getDebugMapping().getBreakPointsBlock().getListTmp().add(new BreakPointBlockPair(e_, tabs.get(_s).getCenter().getCaretPosition(), new BreakPoint()));
        }
    }

    public void updateGui(int _index) {
        AbstractPageEl last_ = stackCall.getCall(_index);
        int opened_ = indexOpened(last_.getFile().getFileName());
        selectFocus(opened_, last_.getTraceIndex());
        detail.setNullViewportView();
        treeDetail = trees.get(_index);
        root = treesRoot.get(_index);
        detail.setViewportView(treeDetail);
        commonFrame.pack();
    }
    public void selectFocus(int _open, int _trace) {
        if (_open > -1) {
            tabbedPane.selectIndex(_open);
            tabs.get(_open).getCenter().select(_trace, _trace);
            tabs.get(_open).getCenter().requestFocus();
            tabs.get(_open).getCenter().visibleCaret();
        }
    }

    public void focus(int _opened) {
        if (_opened > -1) {
            tabs.get(_opened).getCenter().requestFocus();
            tabs.get(_opened).getCenter().visibleCaret();
        }
    }

    @Override
    public int tabCount() {
        return getTabs().size();
    }

    @Override
    public AbsTabEditor tab(int _i) {
        return getTabs().get(_i);
    }

    String pathToSrc(ManageOptions _man) {
        String acc_ = _man.getEx().getAccess();
        return acc_+ StreamTextFile.SEPARATEUR;
    }
    public void update(ResultContext _res) {
        if (_res.getPageEl().notAllEmptyErrors()) {
            selectEnter.setVisible(false);
            PackingWindowAfter.pack(commonFrame);
        } else {
            currentResult = _res;
            selectEnter.setEnabled(true);
            for (ReadOnlyTabEditor r: getTabs()) {
                r.update(_res);
            }
        }
    }
    public void launchDebug() {
        CallingState l_ = look();
        if (l_ != null) {
            selected = l_;
            selectEnter.setEnabled(false);
            debugActions.submit(new DbgLaunchTask(this, StepDbgActionEnum.DEBUG));
        } else {
            selected = new CustomFoundExc(NullStruct.NULL_VALUE);
        }
    }

    public DbgRootStruct getRoot() {
        return root;
    }

    public AbsTreeGui getTreeDetail() {
        return treeDetail;
    }

    public AbsPlainButton getSelectEnter() {
        return selectEnter;
    }

    public AbstractBaseExecutorService getDebugActions() {
        return debugActions;
    }

    protected abstract CallingState look();
    public CustList<ReadOnlyTabEditor> getTabs() {
        return tabs;
    }

    public AbsTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    public CdmFactory getFactory() {
        return factory;
    }

    public BreakPointBlockPair getSelectedPb() {
        return selectedPb;
    }

    public void setSelectedPb(BreakPointBlockPair _s) {
        this.selectedPb = _s;
    }

    public AbsCustCheckBox getInstanceType() {
        return instanceType;
    }

    public AbsTextArea getConditionalInstance() {
        return conditionalInstance;
    }

    public AbsTextArea getConditionalStatic() {
        return conditionalStatic;
    }

    public AbsTextArea getConditionalStd() {
        return conditionalStd;
    }

    public AbsCustCheckBox getStaticType() {
        return staticType;
    }

    public AbsCustCheckBox getEnabledBp() {
        return enabledBp;
    }

    public AbsPlainButton getCancel() {
        return cancel;
    }

    public AbsPlainButton getOk() {
        return ok;
    }

    public AbsPanel getBpForm() {
        return bpForm;
    }

    public AbsCommonFrame getCommonFrame() {
        return commonFrame;
    }

    public ResultContext getCurrentResult() {
        return currentResult;
    }

    public AbsPlainButton getNextAction() {
        return nextAction;
    }

    public AbsPlainButton getNextInstruction() {
        return nextInstruction;
    }

    public AbsPlainButton getNextGoUp() {
        return nextGoUp;
    }

    public AbsPlainButton getNextInMethod() {
        return nextInMethod;
    }

    public AbsPlainButton getNextCursor() {
        return nextCursor;
    }

    public AbsSplitPane getDetailAll() {
        return detailAll;
    }

    public CustList<AbsPlainButton> getCallButtons() {
        return callButtons;
    }

    public AbstractAtomicBoolean getStopDbg() {
        return stopDbg;
    }

    public void setStopDbg(AbstractAtomicBoolean _s) {
        this.stopDbg = _s;
    }
}
