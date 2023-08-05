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
    private AbsMenuItem analyzeMenu;
    private final FrameBpForm frameBpForm;
    private AbsTreeGui folderSystem;
    private AbsTabbedPane tabbedPane;
    private final CustList<ReadOnlyTabEditor> tabs = new CustList<ReadOnlyTabEditor>();
    private CallingState selected = new CustomFoundExc(null);
    private AbsPlainButton selectEnter;
    private AbsPlainButton nextAction;
    private AbsPlainButton nextInstruction;
    private AbsPlainButton nextBlock;
    private AbsPlainButton nextGoUp;
    private AbsPlainButton nextInMethod;
    private AbsPlainButton nextCursor;
    private AbsScrollPane detail;
    private AbsSplitPane detailAll;
    private StackCall stackCall;
    private StackCallReturnValue stackCallView;
    private ResultContext currentResult;
    private AbstractBaseExecutorService actions;
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
    private AbsAnalyzingDebugEvent event;
    private AbsCustCheckBox mute;
    private AbsPlainButton pauseStack;
    private AbsPlainButton stopStack;
    private AbsTextArea statusAnalyzeArea;
    private AbsPanel navigation;
    private final AbsOpenFrameInteract menuManage;

    protected AbsDebuggerGui(AbsOpenFrameInteract _m, AbsResultContextNext _a, String _lg, AbstractProgramInfos _list, CdmFactory _fact) {
        super(_a,_lg,_list);
        menuManage = _m;
        factory = _fact;
        frameBpForm = new FrameBpForm(this,_lg, _list);
        stopDbg = _list.getThreadFactory().newAtomicBoolean();
    }

    public void build(AbsAnalyzingDebugEvent _ev) {
        setEvent(_ev);
        guiBuild();
    }
    public void analyze() {
        StringMap<String> s_ = getEvent().src();
        actions.submit(new AnalyzeDebugTask(getEvent().base(),this,s_));
    }

    public AbsOpenFrameInteract getDbgMenu() {
        return dbgMenu;
    }

    public void guiBuild() {
        dbgMenu = getEvent().act();
        getDbgMenu().open();
        manageOptions = getEvent().manageOpt();
        frameBpForm.guiBuild(this);
        actions = getCommonFrame().getFrames().getThreadFactory().newExecutorService();
        AbsPanel page_ = getCommonFrame().getFrames().getCompoFactory().newPageBox();
        folderSystem = getCommonFrame().getFrames().getCompoFactory().newTreeGui(getCommonFrame().getFrames().getCompoFactory().newMutableTreeNode(""));
        folderSystem.select(folderSystem.getRoot());
        folderSystem.addTreeSelectionListener(new ShowSrcReadOnlyTreeEvent(this,folderSystem,new TabOpeningReadOnlyFile()));
        tabbedPane = getCommonFrame().getFrames().getCompoFactory().newAbsTabbedPane();
        tabbedPane.setPreferredSize(new MetaDimension(512,512));
        page_.add(getCommonFrame().getFrames().getCompoFactory().newHorizontalSplitPane(getCommonFrame().getFrames().getCompoFactory().newAbsScrollPane(folderSystem),tabbedPane));
        page_.add(buildPart());
        mute = getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("mute");
        selectEnter = getCommonFrame().getFrames().getCompoFactory().newPlainButton("|>");
        selectEnter.addActionListener(new DbgLaunchEvent(this));
        selectEnter.setEnabled(false);
        nextAction = getCommonFrame().getFrames().getCompoFactory().newPlainButton(">>");
        nextAction.setEnabled(false);
        nextAction.addActionListener(new DbgNextBpEvent(this, StepDbgActionEnum.KEEP));
        nextInstruction = getCommonFrame().getFrames().getCompoFactory().newPlainButton(">");
        nextInstruction.setEnabled(false);
        nextInstruction.addActionListener(new DbgNextBpEvent(this, StepDbgActionEnum.NEXT_INSTRUCTION));
        nextBlock = getCommonFrame().getFrames().getCompoFactory().newPlainButton(".");
        nextBlock.setEnabled(false);
        nextBlock.addActionListener(new DbgNextBpEvent(this, StepDbgActionEnum.NEXT_BLOCK));
        nextGoUp = getCommonFrame().getFrames().getCompoFactory().newPlainButton("^");
        nextGoUp.setEnabled(false);
        nextGoUp.addActionListener(new DbgNextBpEvent(this, StepDbgActionEnum.RETURN_METHOD));
        nextInMethod = getCommonFrame().getFrames().getCompoFactory().newPlainButton("=");
        nextInMethod.setEnabled(false);
        nextInMethod.addActionListener(new DbgNextBpEvent(this, StepDbgActionEnum.NEXT_IN_METHOD));
        nextCursor = getCommonFrame().getFrames().getCompoFactory().newPlainButton("_");
        nextCursor.setEnabled(false);
        nextCursor.addActionListener(new DbgNextBpEvent(this, StepDbgActionEnum.CURSOR));
        pauseStack = getCommonFrame().getFrames().getCompoFactory().newPlainButton("||");
        pauseStack.addActionListener(new PauseStackEvent(this));
        pauseStack.setEnabled(false);
        stopStack = getCommonFrame().getFrames().getCompoFactory().newPlainButton("stop");
        stopStack.addActionListener(new StopStackEvent(this));
        stopStack.setEnabled(false);
        detail = getCommonFrame().getFrames().getCompoFactory().newAbsScrollPane();
        callStack = getCommonFrame().getFrames().getCompoFactory().newPageBox();
        detailAll = getCommonFrame().getFrames().getCompoFactory().newHorizontalSplitPane(callStack,detail);
        detailAll.setVisible(false);
        navigation = getCommonFrame().getFrames().getCompoFactory().newLineBox();
        navigation.setVisible(false);
        AbsPanel nav_ = navigation;
        nav_.add(mute);
        nav_.add(selectEnter);
        nav_.add(nextAction);
        nav_.add(nextInstruction);
        nav_.add(nextBlock);
        nav_.add(nextGoUp);
        nav_.add(nextInMethod);
        nav_.add(nextCursor);
        nav_.add(pauseStack);
        nav_.add(stopStack);
        page_.add(nav_);
        page_.add(detailAll);
        statusAnalyzeArea = getCommonFrame().getFrames().getCompoFactory().newTextArea();
        statusAnalyzeArea.setEditable(false);
        page_.add(getCommonFrame().getFrames().getCompoFactory().newAbsScrollPane(statusAnalyzeArea));
        getCommonFrame().setContentPane(page_);
        getCommonFrame().setVisible(true);
        AbsMenuBar bar_ = getCommonFrame().getFrames().getCompoFactory().newMenuBar();
        AbsMenu session_ = getCommonFrame().getFrames().getCompoFactory().newMenu("session");
        analyzeMenu = getCommonFrame().getFrames().getCompoFactory().newMenuItem("analyze");
        analyzeMenu.addActionListener(getEvent());
        session_.addMenuItem(analyzeMenu);
        bar_.add(session_);
        getCommonFrame().setJMenuBar(bar_);
        PackingWindowAfter.pack(getCommonFrame());
    }

    public void setViewable(StringMap<String> _v) {
        this.viewable = _v;
    }

    public void applyTreeChangeSelected(AbsOpeningReadOnlyFile _a, AbsTreeGui _t) {
        if (getCurrentResult() == null) {
            return;
        }
        AbstractMutableTreeNode sel_ = _t.selectEvt();
        if (sel_ == null) {
            return;
        }
        String str_ = buildPath(sel_);
        if (openFile(_a,str_)) {
            return;
        }
        refresh(sel_,str_, _t);
    }

    boolean openFile(AbsOpeningReadOnlyFile _a, String _str) {
        String res_ = viewable.getVal(_str);
        if (res_ != null) {
            _a.openFile(this,_str,res_);
            return true;
        }
        return false;
    }
    void refresh(AbstractMutableTreeNode _sel, String _str, AbsTreeGui _t) {
        refParent(_sel, _str, _t);
    }

    void refParent(AbstractMutableTreeNode _parent, String _parentPath, AbsTreeGui _t) {
        _parent.removeAllChildren();
        refreshList(_parent, viewable, _parentPath);
        MutableTreeNodeUtil.reload(_t);
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
        ReadOnlyTabEditor te_ = new ReadOnlyTabEditor(this,getCommonFrame().getFrames(), _path.substring(pathToSrc(_man).length()), WindowWithTreeImpl.lineSeparator(dec_),_opt);
        te_.centerText(new DefaultUniformingString().apply(dec_));
        tabs.add(te_);
        tabbedPane.addIntTab(name_, te_.getPanel(), _path);
    }
    void next(StepDbgActionEnum _step){
        getPauseStack().setEnabled(true);
        getStopStack().setEnabled(true);
        StackCallReturnValue view_ = ExecClassesUtil.tryInitStaticlyTypes(currentResult.getContext(), currentResult.getForwards().getOptions(), stackCall, selected,_step, mute.isSelected());
        getPauseStack().setEnabled(false);
        if (getStopDbg().get()) {
            getStopStack().setEnabled(false);
            setStackCall(null);
            return;
        }
        setStackCallView(view_);
        stackCall = getStackCallView().getStack();
        if (!stackCall.getBreakPointInfo().getBreakPointOutputInfo().isStoppedBreakPoint()) {
            callStack.removeAll();
            callButtons.clear();
            root = new DbgRootStruct(currentResult);
            treeDetail = root.buildReturn(getCommonFrame().getFrames().getCompoFactory(), view_.getRetValue());
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
            AbsTreeGui b_ = r_.build(getCommonFrame().getFrames().getCompoFactory(), p_);
            treeDetail = b_;
            trees.add(b_);
            treesRoot.add(r_);
            AbsPlainButton but_ = getCommonFrame().getFrames().getCompoFactory().newPlainButton(dis_);
            callButtons.add(but_);
            but_.addActionListener(new SelectCallStackEvent(this,i));
            callStack.add(but_);
        }
        AbstractPageEl last_ = stackCall.getLastPage();
        int opened_ = indexOpened(last_.getFile().getFileName());
        selectFocus(opened_, last_.getTraceIndex());
        detail.setViewportView(treeDetail);
        detailAll.setVisible(true);
        PackingWindowAfter.pack(getCommonFrame());
        nextAction.setEnabled(true);
        nextInstruction.setEnabled(true);
        nextBlock.setEnabled(true);
        nextGoUp.setEnabled(true);
        nextInMethod.setEnabled(true);
        nextCursor.setEnabled(true);
    }

    public void possibleSelect(int _s) {
        if (_s > -1) {
            FileBlock f_ = currentResult.getPageEl().getPreviousFilesBodies().getVal(tabs.get(_s).getFullPath());
            ExecFileBlock e_ = currentResult.getContext().getClasses().getDebugMapping().getFiles().getVal(f_);
            int caret_ = tabs.get(_s).getCenter().getCaretPosition();
            currentResult.getContext().getClasses().getDebugMapping().getBreakPointsBlock().getListTmp().add(new BreakPointBlockPair(e_, ResultExpressionOperationNode.beginPart(caret_,f_), new BreakPoint(currentResult.getContext().getCaller())));
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
        getCommonFrame().pack();
    }
    public void selectFocus(int _open, int _trace) {
        if (_open > -1) {
            tabbedPane.selectIndex(_open);
            tabs.get(_open).centerSelect(_trace, _trace);
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
            statusAnalyzeArea.append(_res.getReportedMessages().displayErrors());
            selectEnter.setEnabled(false);
            navigation.setVisible(false);
        } else {
            selectEnter.setEnabled(true);
            navigation.setVisible(true);
        }
        currentResult = _res;
        refreshList(folderSystem.selectEvt(),viewable, "");
        frameBpForm.refresh(viewable);

        int len_ = _src.size();
        for (int i = 0; i < len_; i++) {
            String fullPath_ = pathToSrc(manageOptions)+ _src.getKey(i);
            BytesInfo content_ = new BytesInfo(StringUtil.encode(_src.getValue(i)),false);
            addTab(manageOptions,fullPath_,content_, manageOptions.getOptions());
        }
        PackingWindowAfter.pack(getCommonFrame());
    }

    public void closeAll() {
        closeCompos();
        actions.shutdown();
    }

    public void closeCompos() {
        tabbedPane.removeAll();
        tabs.clear();
    }

    public void launchDebug() {
        CallingState l_ = look();
        if (l_ != null) {
            selected = l_;
            selectEnter.setEnabled(false);
            actions.submit(new DbgLaunchTask(this, StepDbgActionEnum.DEBUG));
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
        PackingWindowAfter.pack(getCommonFrame());
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

    public AbsCustCheckBox getMute() {
        return mute;
    }

    public AbstractBaseExecutorService getActions() {
        return actions;
    }

    protected abstract CallingState look();
    public CustList<ReadOnlyTabEditor> getTabs() {
        return tabs;
    }

    public ManageOptions getManageOptions() {
        return manageOptions;
    }

    public AbsTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    public CdmFactory getFactory() {
        return factory;
    }

    public FrameBpForm getFrameBpForm() {
        return frameBpForm;
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

    public AbsPlainButton getNextBlock() {
        return nextBlock;
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

    public AbsPlainButton getPauseStack() {
        return pauseStack;
    }

    public AbsSplitPane getDetailAll() {
        return detailAll;
    }

    public CustList<AbsPlainButton> getCallButtons() {
        return callButtons;
    }

    public AbsMenuItem getAnalyzeMenu() {
        return analyzeMenu;
    }

    public AbstractAtomicBoolean getStopDbg() {
        return stopDbg;
    }

    public AbsPlainButton getStopStack() {
        return stopStack;
    }

    public void setStopDbg(AbstractAtomicBoolean _s) {
        this.stopDbg = _s;
    }

    public AbsAnalyzingDebugEvent getEvent() {
        return event;
    }

    public void setEvent(AbsAnalyzingDebugEvent _e) {
        this.event = _e;
    }

    public AbsOpenFrameInteract getMenuManage() {
        return menuManage;
    }
}
