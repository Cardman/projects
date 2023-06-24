package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.analyze.syntax.ResultExpressionOperationNode;
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
import code.expressionlanguage.utilcompo.AbsResultContextNext;
import code.expressionlanguage.utilimpl.ManageOptions;
import code.gui.*;
import code.gui.images.MetaDimension;
import code.gui.initialize.AbstractProgramInfos;
import code.stream.BytesInfo;
import code.stream.StreamTextFile;
import code.threads.AbstractAtomicBoolean;
import code.threads.AbstractBaseExecutorService;
import code.util.CustList;
import code.util.StringMap;
import code.util.comparators.NaturalComparator;
import code.util.core.DefaultUniformingString;
import code.util.core.StringUtil;

public abstract class AbsDebuggerGui extends AbsEditorTabList {
    private final CdmFactory factory;
    private final AbsCommonFrame commonFrame;
    private BreakPointBlockPair selectedPb;
    private AbsCustCheckBox instanceType;
    private AbsTextArea conditionalInstance;
    private AbsSpinner countInstance;
    private AbsCustCheckBox staticType;
    private AbsTextArea conditionalStatic;
    private AbsSpinner countStatic;
    private AbsCustCheckBox enabledBp;
    private AbsTextArea conditionalStd;
    private AbsSpinner countStd;
    private AbsPlainButton cancel;
    private AbsPlainButton ok;
    private AbsPanel bpForm;
    private AbsTreeGui folderSystem;
    private AbsTabbedPane tabbedPane;
    private final CustList<ReadOnlyTabEditor> tabs = new CustList<ReadOnlyTabEditor>();
    private CallingState selected = new CustomFoundExc(null);
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
    private StackCallReturnValue stackCallView;
    private ResultContext currentResult;
    private final AbstractBaseExecutorService manageAnalyze;
    private final AbstractBaseExecutorService manageGui;
    private DbgRootStruct root;
    private AbsTreeGui treeDetail;
    private final CustList<AbsTreeGui> trees = new CustList<AbsTreeGui>();
    private final CustList<DbgRootStruct> treesRoot = new CustList<DbgRootStruct>();
    private final CustList<AbsPlainButton> callButtons = new CustList<AbsPlainButton>();
    private AbsPanel callStack;
    private AbstractAtomicBoolean stopDbg;
    private AbsOpenFrameInteract dbgMenu;
    private StringMap<String> viewable = new StringMap<String>();
    private ManageOptions manageOptions;

    protected AbsDebuggerGui(AbsResultContextNext _a, String _lg, AbstractProgramInfos _list, CdmFactory _fact) {
        super(_a);
        factory = _fact;
        commonFrame = _list.getFrameFactory().newCommonFrame(_lg, _list, null);
        debugActions = _list.getThreadFactory().newExecutorService();
        manageAnalyze = _list.getThreadFactory().newExecutorService();
        manageGui = _list.getThreadFactory().newExecutorService();
        stopDbg = _list.getThreadFactory().newAtomicBoolean();
    }

    public void build(AbsOpenFrameInteract _a,ResultContext _b,ManageOptions _man, StringMap<String> _src) {
        dbgMenu = _a;
        getDbgMenu().open();
        guiBuild(_man);
        manageAnalyze.submit(new AnalyzeDebugTask(_b,this,_src));
    }

    public AbsOpenFrameInteract getDbgMenu() {
        return dbgMenu;
    }

    public void guiBuild(ManageOptions _man) {
        manageOptions = _man;
        instanceType = getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("instance");
        conditionalInstance = getCommonFrame().getFrames().getCompoFactory().newTextArea();
        countInstance = getCommonFrame().getFrames().getCompoFactory().newSpinner(0, 0, Integer.MAX_VALUE, 1);
        staticType = getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("static");
        conditionalStatic = getCommonFrame().getFrames().getCompoFactory().newTextArea();
        countStatic = getCommonFrame().getFrames().getCompoFactory().newSpinner(0, 0, Integer.MAX_VALUE, 1);
        enabledBp = getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("enabled");
        conditionalStd = getCommonFrame().getFrames().getCompoFactory().newTextArea();
        countStd = getCommonFrame().getFrames().getCompoFactory().newSpinner(0, 0, Integer.MAX_VALUE, 1);
        cancel = getCommonFrame().getFrames().getCompoFactory().newPlainButton("cancel");
        ok = getCommonFrame().getFrames().getCompoFactory().newPlainButton("ok");
        bpForm = getCommonFrame().getFrames().getCompoFactory().newPageBox();
        bpForm.add(enabledBp);
        bpForm.add(conditionalStd);
        bpForm.add(countStd);
        bpForm.add(instanceType);
        bpForm.add(conditionalInstance);
        bpForm.add(countInstance);
        bpForm.add(staticType);
        bpForm.add(conditionalStatic);
        bpForm.add(countStatic);
        ok.addActionListener(new OkBpFormEvent(this));
        bpForm.add(ok);
        cancel.addActionListener(new CancelBpFormEvent(this));
        bpForm.add(cancel);
        bpForm.setVisible(false);
        AbsPanel page_ = commonFrame.getFrames().getCompoFactory().newPageBox();
        AbstractMutableTreeNode default_ = commonFrame.getFrames().getCompoFactory().newMutableTreeNode("");
        folderSystem = commonFrame.getFrames().getCompoFactory().newTreeGui(default_);
        folderSystem.select(folderSystem.getRoot());
        folderSystem.addTreeSelectionListener(new ShowSrcReadOnlyTreeEvent(this));
        tabbedPane = commonFrame.getFrames().getCompoFactory().newAbsTabbedPane();
        tabbedPane.setPreferredSize(new MetaDimension(512,512));
        page_.add(commonFrame.getFrames().getCompoFactory().newHorizontalSplitPane(commonFrame.getFrames().getCompoFactory().newAbsScrollPane(folderSystem),tabbedPane));
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

    public void setViewable(StringMap<String> _v) {
        this.viewable = _v;
    }

    public boolean applyTreeChangeSelected() {
        if (getCurrentResult() == null) {
            return false;
        }
        AbstractMutableTreeNode sel_ = getTree().selectEvt();
        if (sel_ == null) {
            return false;
        }
        String str_ = buildPath(sel_);
        if (openFile(str_)) {
            return false;
        }
        refresh(sel_,str_);
        return true;
    }

    boolean openFile(String _str) {
        String res_ = viewable.getVal(_str);
        if (res_ != null) {
            int opened_ = indexOpened(_str);
            if (opened_ > -1) {
                getTabbedPane().selectIndex(opened_);
                return true;
            }
            String fullPath_ = pathToSrc(manageOptions)+ _str;
            BytesInfo content_ = new BytesInfo(StringUtil.encode(res_),false);
            addTab(manageOptions,fullPath_,content_,manageOptions.getOptions());
            getTabbedPane().selectIndex(getTabs().getLastIndex());
            return true;
        }
        return false;
    }
    void refresh(AbstractMutableTreeNode _sel, String _str) {
        refParent(_sel, _str);
    }

    void refParent(AbstractMutableTreeNode _parent, String _parentPath) {
        _parent.removeAllChildren();
        refreshList(_parent, viewable, _parentPath);
        MutableTreeNodeUtil.reload(getTree());
    }
    static void refreshList(AbstractMutableTreeNode _sel, StringMap<String> _files, String _folderToVisit) {
        CustList<String> currentFolders_ = new CustList<String>();
        CustList<String> currentFiles_ = new CustList<String>();
        for (String f: _files.getKeys()) {
            if (f.startsWith(_folderToVisit)) {
                String rel_ = f.substring(_folderToVisit.length());
                int sl_ = rel_.indexOf('/');
                if (sl_ > -1) {
                    String part_ = rel_.substring(0, sl_);
                    if (!StringUtil.contains(currentFolders_,part_)) {
                        currentFolders_.add(part_);
                    }
                } else {
                    currentFiles_.add(rel_);
                }
            }
        }
        currentFolders_.sortElts(new NaturalComparator());
        currentFiles_.sortElts(new NaturalComparator());
        for (String f : currentFolders_) {
            _sel.add(f+"/");
        }
        for (String f : currentFiles_) {
            _sel.add(f);
        }
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
        setStackCallView(view_);
        stackCall = getStackCallView().getStack();
        if (!stackCall.isStoppedBreakPoint()) {
            callStack.removeAll();
            callButtons.clear();
            root = new DbgRootStruct(currentResult);
            treeDetail = root.buildReturn(commonFrame.getFrames().getCompoFactory(), view_.getRetValue());
            detail.setViewportView(treeDetail);
            detailAll.setVisible(true);
            selectEnter.setEnabled(true);
            int opened_ = tabbedPane.getSelectedIndex();
            focus(opened_);
            endCall();
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
            int caret_ = tabs.get(_s).getCenter().getCaretPosition();
            currentResult.getContext().getClasses().getDebugMapping().getBreakPointsBlock().getListTmp().add(new BreakPointBlockPair(e_, ResultExpressionOperationNode.beginPart(caret_,f_), new BreakPoint()));
        }
    }

    public void updateGui(int _index) {
        AbstractPageEl last_ = getStackCall().getCall(_index);
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
    public void update(ResultContext _res, StringMap<String> _src) {
        if (_res.getPageEl().notAllEmptyErrors()) {
            selectEnter.setVisible(false);
        } else {
            selectEnter.setEnabled(true);
        }
        currentResult = _res;
        refreshList(folderSystem.selectEvt(),viewable, "");
        int len_ = _src.size();
        for (int i = 0; i < len_; i++) {
            String fullPath_ = pathToSrc(manageOptions)+ _src.getKey(i);
            BytesInfo content_ = new BytesInfo(StringUtil.encode(_src.getValue(i)),false);
            addTab(manageOptions,fullPath_,content_, manageOptions.getOptions());
        }
        PackingWindowAfter.pack(commonFrame);
    }
    public void closeAll() {
        tabbedPane.removeAll();
        tabs.clear();
        PackingWindowAfter.pack(commonFrame);
    }
    public void launchDebug() {
        CallingState l_ = look();
        if (l_ != null) {
            selected = l_;
            selectEnter.setEnabled(false);
            debugActions.submit(new DbgLaunchTask(this, StepDbgActionEnum.DEBUG));
        } else {
            selected = new CustomFoundExc(null);
        }
    }
    public AbsTreeGui getTree() {
        return getFolderSystem();
    }
    public AbsTreeGui getFolderSystem() {
        return folderSystem;
    }
    protected void endCall(){
        PackingWindowAfter.pack(commonFrame);
    }

    public StackCall getStackCall() {
        return stackCall;
    }

    public void setStackCall(StackCall _s) {
        this.stackCall = _s;
    }

    public StackCallReturnValue getStackCallView() {
        return stackCallView;
    }

    public void setStackCallView(StackCallReturnValue _s) {
        this.stackCallView = _s;
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

    public AbsSpinner getCountInstance() {
        return countInstance;
    }

    public AbsTextArea getConditionalStatic() {
        return conditionalStatic;
    }

    public AbsSpinner getCountStatic() {
        return countStatic;
    }

    public AbsTextArea getConditionalStd() {
        return conditionalStd;
    }

    public AbsSpinner getCountStd() {
        return countStd;
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

    public AbstractBaseExecutorService getManageGui() {
        return manageGui;
    }

    public AbstractAtomicBoolean getStopDbg() {
        return stopDbg;
    }

    public void setStopDbg(AbstractAtomicBoolean _s) {
        this.stopDbg = _s;
    }
}
