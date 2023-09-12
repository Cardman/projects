package code.expressionlanguage.adv;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.analyze.syntax.ResultExpressionOperationNode;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.ExecFileBlock;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.CallingState;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.dbg.BreakPointBlockKey;
import code.expressionlanguage.exec.variables.ViewPage;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.utilcompo.AbsResultContextNext;
import code.expressionlanguage.utilimpl.ManageOptions;
import code.gui.*;
import code.gui.images.MetaDimension;
import code.gui.initialize.AbstractProgramInfos;
import code.stream.BytesInfo;
import code.stream.StreamTextFile;
import code.threads.AbstractAtomicBoolean;
import code.threads.AbstractThread;
import code.util.CustList;
import code.util.StringMap;
import code.util.comparators.NaturalComparator;
import code.util.core.DefaultUniformingString;
import code.util.core.StringUtil;

public abstract class AbsDebuggerGui extends AbsEditorTabList {
    private final CdmFactory factory;
    private AbsMenuItem analyzeMenu;
    private AbsMenuItem openPoints;
    private final FramePoints framePoints;
    private AbsTreeGui folderSystem;
    private AbsTabbedPane tabbedPane;
    private final CustList<ReadOnlyTabEditor> tabs = new CustList<ReadOnlyTabEditor>();
    private CallingState selected;
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
    private DbgRootStruct root;
    private AbsTreeGui treeDetail;
    private final CustList<AbsTreeGui> trees = new CustList<AbsTreeGui>();
    private final CustList<DbgRootStruct> treesRoot = new CustList<DbgRootStruct>();
    private final CustList<AbsPlainButton> callButtons = new CustList<AbsPlainButton>();
    private AbsPanel callStack;
    private AbstractAtomicBoolean stopDbg;
    private final AbstractAtomicBoolean stoppedClick;
    private AbsOpenFrameInteract dbgMenu;
    private StringMap<String> viewable = new StringMap<String>();
    private ManageOptions manageOptions;
    private AbsAnalyzingDebugEvent event;
    private AbsCustCheckBox mute;
    private AbsPlainButton pauseStack;
    private AbsPlainButton stopStack;
    private AbsTextArea statusAnalyzeArea;
    private AbsPanel navigation;
    private AbstractThread currentThreadActions;
    private final AbsOpenFrameInteract menuManage;
    private AbsScrollPane statusDbgAreaScroll;

    protected AbsDebuggerGui(AbsOpenFrameInteract _m, AbsResultContextNext _a, String _lg, AbstractProgramInfos _list, CdmFactory _fact) {
        super(_a,_lg,_list);
        menuManage = _m;
        factory = _fact;
        framePoints = new FramePoints(this,_lg, _list);
        stopDbg = _list.getThreadFactory().newAtomicBoolean();
        stoppedClick = _list.getThreadFactory().newAtomicBoolean();
    }

    public void build(AbsAnalyzingDebugEvent _ev) {
        setEvent(_ev);
        guiBuild();
    }
    public void analyze() {
        getAnalyzeMenu().setEnabled(false);
        selectEnter.setEnabled(false);
        disableNext();
        StringMap<String> s_ = getEvent().src();
        currentThreadActions(new AnalyzeDebugTask(getEvent().base(),this,s_));
    }

    public AbsOpenFrameInteract getDbgMenu() {
        return dbgMenu;
    }

    public void guiBuild() {
        dbgMenu = getEvent().act();
        getDbgMenu().open();
        manageOptions = getEvent().manageOpt();
        framePoints.guiBuild(this);
        AbsPanel page_ = getCommonFrame().getFrames().getCompoFactory().newPageBox();
        folderSystem = getCommonFrame().getFrames().getCompoFactory().newTreeGui(getCommonFrame().getFrames().getCompoFactory().newMutableTreeNode(""));
        folderSystem.select(folderSystem.getRoot());
        tabbedPane = getCommonFrame().getFrames().getCompoFactory().newAbsTabbedPane();
        tabbedPane.setPreferredSize(new MetaDimension(512,512));
        page_.add(getCommonFrame().getFrames().getCompoFactory().newHorizontalSplitPane(getCommonFrame().getFrames().getCompoFactory().newAbsScrollPane(folderSystem),tabbedPane));
        page_.add(buildPart());
        mute = getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("mute");
        selectEnter = getCommonFrame().getFrames().getCompoFactory().newPlainButton("|>");
        selectEnter.setEnabled(false);
        nextAction = getCommonFrame().getFrames().getCompoFactory().newPlainButton(">>");
        nextAction.setEnabled(false);
        nextInstruction = getCommonFrame().getFrames().getCompoFactory().newPlainButton(">");
        nextInstruction.setEnabled(false);
        nextBlock = getCommonFrame().getFrames().getCompoFactory().newPlainButton(".");
        nextBlock.setEnabled(false);
        nextGoUp = getCommonFrame().getFrames().getCompoFactory().newPlainButton("^");
        nextGoUp.setEnabled(false);
        nextInMethod = getCommonFrame().getFrames().getCompoFactory().newPlainButton("=");
        nextInMethod.setEnabled(false);
        nextCursor = getCommonFrame().getFrames().getCompoFactory().newPlainButton("_");
        nextCursor.setEnabled(false);
        pauseStack = getCommonFrame().getFrames().getCompoFactory().newPlainButton("||");
        pauseStack.setEnabled(false);
        stopStack = getCommonFrame().getFrames().getCompoFactory().newPlainButton("stop");
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
        statusDbgAreaScroll = getCommonFrame().getFrames().getCompoFactory().newAbsScrollPane();
        page_.add(statusDbgAreaScroll);
        getCommonFrame().setContentPane(page_);
        getCommonFrame().setVisible(true);
        AbsMenuBar bar_ = getCommonFrame().getFrames().getCompoFactory().newMenuBar();
        AbsMenu session_ = getCommonFrame().getFrames().getCompoFactory().newMenu("session");
        analyzeMenu = getCommonFrame().getFrames().getCompoFactory().newMenuItem("analyze");
        analyzeMenu.addActionListener(getEvent());
        session_.addMenuItem(analyzeMenu);
        openPoints = getCommonFrame().getFrames().getCompoFactory().newMenuItem("open points");
        openPoints.setEnabled(false);
        openPoints.setAccelerator(GuiConstants.VK_F6,GuiConstants.CTRL_DOWN_MASK+GuiConstants.SHIFT_DOWN_MASK);
        session_.addMenuItem(openPoints);
        bar_.add(session_);
        getCommonFrame().setJMenuBar(bar_);
        PackingWindowAfter.pack(getCommonFrame());
    }

    public void setViewable(StringMap<String> _v) {
        this.viewable = _v;
    }

    public void applyTreeChangeSelected(AbsOpeningReadOnlyFile _a, ResultContext _res, AbsTreeGui _t) {
        AbstractMutableTreeNode sel_ = _t.selectEvt();
        if (sel_ == null) {
            return;
        }
        String str_ = buildPath(sel_);
        if (openFile(_res,_a,str_)) {
            return;
        }
        refresh(sel_,str_, _t);
    }

    boolean openFile(ResultContext _res, AbsOpeningReadOnlyFile _a, String _str) {
        String res_ = viewable.getVal(_str);
        if (res_ != null) {
            _a.openFile(this,_res,_str,res_);
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
    void addTab(ResultContext _res, ManageOptions _man, String _path, BytesInfo _content, Options _opt) {
        String dec_ = StringUtil.nullToEmpty(StringUtil.decode(_content.getBytes()));
        String name_ = _path.substring(_path.lastIndexOf('/')+1);
        ReadOnlyTabEditor te_ = new ReadOnlyTabEditor(this,getCommonFrame().getFrames(), _path.substring(pathToSrc(_man).length()), WindowWithTreeImpl.lineSeparator(dec_),_opt,_res);
        te_.centerText(new DefaultUniformingString().apply(dec_));
        ToggleBreakPointEvent.afterToggle(_res,te_);
        tabs.add(te_);
        tabbedPane.addIntTab(name_, te_.getPanel(), _path);
    }
    void next(StepDbgActionEnum _step, ResultContext _res){
        getPauseStack().setEnabled(true);
        getStopStack().setEnabled(true);
        getAnalyzeMenu().setEnabled(false);
        ContextEl ctx_ = _res.getContext();
        StackCallReturnValue view_ = ExecClassesUtil.tryInitStaticlyTypes(ctx_, _res.getForwards().getOptions(), stackCall, state(selected),_step, mute.isSelected());
        if (getStoppedClick().getAndSet(false)) {
            return;
        }
        getStopStack().setEnabled(false);
        getPauseStack().setEnabled(false);
        setStackCallView(view_);
        stackCall = getStackCallView().getStack();
        if (!stackCall.getBreakPointInfo().getBreakPointOutputInfo().isStoppedBreakPoint()) {
            callStack.removeAll();
            callButtons.clear();
            root = new DbgRootStruct(_res);
            treeDetail = root.buildReturn(getCommonFrame().getFrames().getCompoFactory(), view_.getStack().aw());
            detail.setViewportView(treeDetail);
            detailAll.setVisible(true);
            selectEnter.setEnabled(true);
            int opened_ = tabbedPane.getSelectedIndex();
            focus(opened_);
            endCall();
            getAnalyzeMenu().setEnabled(true);
            return;
        }
        trees.clear();
        treesRoot.clear();
        callButtons.clear();
        callStack.removeAll();
        int nbPages_ = view_.getVariables().size();
        for (int i = 0; i< nbPages_; i++) {
            ViewPage p_ = view_.getVariables().get(i);
            String dis_ = p_.getStackElt().getDisplayedString(ctx_).getInstance();
            DbgRootStruct r_ = new DbgRootStruct(_res);
            root = r_;
            AbsTreeGui b_ = r_.build(getCommonFrame().getFrames().getCompoFactory(), p_);
            treeDetail = b_;
            trees.add(b_);
            treesRoot.add(r_);
            AbsPlainButton but_ = getCommonFrame().getFrames().getCompoFactory().newPlainButton(dis_);
            callButtons.add(but_);
            but_.addActionListener(new SelectCallStackEvent(this,p_,b_,r_));
            callStack.add(but_);
        }
        AbstractPageEl last_ = getStackCall().getLastPage();
        int opened_ = indexOpened(ExecFileBlock.name(last_.getFile()));
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
        getStopStack().setEnabled(true);
        getAnalyzeMenu().setEnabled(true);
    }

    public void possibleSelect(int _s, ResultContext _res) {
        if (_s > -1) {
            FileBlock f_ = _res.getPageEl().getPreviousFilesBodies().getVal(tabs.get(_s).getFullPath());
            ExecFileBlock e_ = _res.getFiles().getVal(f_);
            int caret_ = tabs.get(_s).getCenter().getCaretPosition();
            _res.getContext().tmpList().add(new BreakPointBlockKey(e_, FileBlock.number(f_), ResultExpressionOperationNode.beginPart(caret_,f_)));
        }
    }

    public void updateGui(ViewPage _view, AbsTreeGui _treeDetailEvt, DbgRootStruct _treeRoot) {
        int opened_ = indexOpened(_view.getFileName());
        selectFocus(opened_, _view.getTrace());
        detail.setNullViewportView();
        treeDetail = _treeDetailEvt;
        root = _treeRoot;
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
        stopDbg = _res.getContext().getInterrupt();
        openPoints.setEnabled(true);
        GuiBaseUtil.removeActionListeners(selectEnter);
        selectEnter.addActionListener(new DbgLaunchEvent(this, _res));
        GuiBaseUtil.removeActionListeners(nextAction);
        nextAction.addActionListener(new DbgNextBpEvent(this, StepDbgActionEnum.KEEP, _res));
        GuiBaseUtil.removeActionListeners(nextInstruction);
        nextInstruction.addActionListener(new DbgNextBpEvent(this, StepDbgActionEnum.NEXT_INSTRUCTION, _res));
        GuiBaseUtil.removeActionListeners(nextBlock);
        nextBlock.addActionListener(new DbgNextBpEvent(this, StepDbgActionEnum.NEXT_BLOCK, _res));
        GuiBaseUtil.removeActionListeners(nextGoUp);
        nextGoUp.addActionListener(new DbgNextBpEvent(this, StepDbgActionEnum.RETURN_METHOD, _res));
        GuiBaseUtil.removeActionListeners(nextInMethod);
        nextInMethod.addActionListener(new DbgNextBpEvent(this, StepDbgActionEnum.NEXT_IN_METHOD, _res));
        GuiBaseUtil.removeActionListeners(nextCursor);
        nextCursor.addActionListener(new DbgNextBpEvent(this, StepDbgActionEnum.CURSOR, _res));
        GuiBaseUtil.removeActionListeners(stopStack);
        stopStack.addActionListener(new StopStackEvent(this, _res));
        GuiBaseUtil.removeActionListeners(pauseStack);
        pauseStack.addActionListener(new PauseStackEvent(this, _res));
        GuiBaseUtil.removeActionListeners(openPoints);
        openPoints.addActionListener(new OpenFramePointsEvent(this,framePoints, _res));
        GuiBaseUtil.removeTreeSelectionListeners(folderSystem);
        folderSystem.addTreeSelectionListener(new ShowSrcReadOnlyTreeEvent(this,_res,folderSystem,new TabOpeningReadOnlyFile()));
        refreshList(folderSystem.selectEvt(),viewable, "");
        framePoints.refresh(viewable,this, _res);
        closeCompos();
        int len_ = _src.size();
        for (int i = 0; i < len_; i++) {
            String fullPath_ = pathToSrc(manageOptions)+ _src.getKey(i);
            BytesInfo content_ = new BytesInfo(StringUtil.encode(_src.getValue(i)),false);
            addTab(_res,manageOptions,fullPath_,content_, manageOptions.getOptions());
        }
        PackingWindowAfter.pack(getCommonFrame());
    }

    public void closeAll() {
        closeCompos();
    }

    public void closeCompos() {
        tabbedPane.removeAll();
        tabs.clear();
    }

    public void launchDebug(ResultContext _curr) {
        CallingState l_ = look(_curr);
        selected = state(l_);
        if (l_ != null) {
            selectEnter.setEnabled(false);
            currentThreadActions(new DbgLaunchTask(this, StepDbgActionEnum.DEBUG, _curr));
        }
    }

    public void disableNext() {
        getNextAction().setEnabled(false);
        getNextInstruction().setEnabled(false);
        getNextBlock().setEnabled(false);
        getNextGoUp().setEnabled(false);
        getNextInMethod().setEnabled(false);
        getNextCursor().setEnabled(false);
    }
    public void currentThreadActions(Runnable _t) {
        setCurrentThreadActions(getCommonFrame().getFrames().getThreadFactory().newStartedThread(_t));
    }

    public AbstractAtomicBoolean getStoppedClick() {
        return stoppedClick;
    }

    private static CallingState state(CallingState _f) {
        if (_f != null) {
            return _f;
        }
        return new CustomFoundExc(NullStruct.NULL_VALUE);
    }
    public ReadOnlyTabEditor selectedTab() {
        int ind_ = tabbedPane.getSelectedIndex();
        if (!getTabs().isValidIndex(ind_)) {
            return null;
        }
        return getTabs().get(ind_);
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

    protected abstract CallingState look(ResultContext _res);
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

    public FramePoints getFramePoints() {
        return framePoints;
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

    public AbsPanel getCallStack() {
        return callStack;
    }

    public CustList<AbsPlainButton> getCallButtons() {
        return callButtons;
    }

    public AbsMenuItem getAnalyzeMenu() {
        return analyzeMenu;
    }

    public AbsMenuItem getOpenPoints() {
        return openPoints;
    }

    public AbstractAtomicBoolean getStopDbg() {
        return stopDbg;
    }

    public AbsPlainButton getStopStack() {
        return stopStack;
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

    public AbstractThread getCurrentThreadActions() {
        return currentThreadActions;
    }

    public void setCurrentThreadActions(AbstractThread _t) {
        this.currentThreadActions = _t;
    }

    public AbsScrollPane getStatusDbgAreaScroll() {
        return statusDbgAreaScroll;
    }
}
