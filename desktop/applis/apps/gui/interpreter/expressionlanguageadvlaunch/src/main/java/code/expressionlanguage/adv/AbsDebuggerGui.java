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
import code.expressionlanguage.fwd.AbsLightContextGenerator;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.stds.AbstractInterceptorStdCaller;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.utilcompo.AbsResultContextNext;
import code.expressionlanguage.utilimpl.ManageOptions;
import code.gui.*;
import code.gui.images.MetaDimension;
import code.gui.initialize.AbsCompoFactory;
import code.gui.initialize.AbstractProgramInfos;
import code.stream.BytesInfo;
import code.stream.StreamTextFile;
import code.threads.AbstractAtomicBoolean;
import code.util.CustList;
import code.util.IdList;
import code.util.IdMap;
import code.util.StringMap;
import code.util.comparators.NaturalComparator;
import code.util.core.DefaultUniformingString;
import code.util.core.StringUtil;

public abstract class AbsDebuggerGui extends AbsEditorTabList {
    private final CdmFactory factory;
    private EnabledMenu analyzeMenu;
    private EnabledMenu openPoints;
    private final FramePoints framePoints;
    private AbsTreeGui folderSystem;
    private AbsTabbedPane tabbedPane;
    private final CustList<ReadOnlyTabEditor> tabs = new CustList<ReadOnlyTabEditor>();
    private CallingState selected;
    private AbsButton selectEnter;
    private AbsButton nextAction;
    private AbsButton nextInstruction;
    private AbsButton nextBlock;
    private AbsButton nextGoUp;
    private AbsButton nextInMethod;
    private AbsButton nextCursorInstruction;
    private AbsButton nextCursorExpression;
    private AbsScrollPane detail;
    private AbsTabbedPane sessionTab;
    private StackCall stackCall;
    private StackCallReturnValue stackCallView;
    private DbgRootStruct root;
    private AbsTreeGui treeDetail;
    private final CustList<AbsButton> callButtons = new CustList<AbsButton>();
    private final CustList<AbsButton> callButtonsRender = new CustList<AbsButton>();
    private AbsPanel callStack;
    private AbsPanel callStackRender;
    private AbstractAtomicBoolean stopDbg;
    private final AbstractAtomicBoolean stoppedClick;
    private AbsOpenFrameInteract dbgMenu;
    private StringMap<String> viewable = new StringMap<String>();
    private ManageOptions manageOptions;
    private AbsAnalyzingDebugEvent event;
    private AbsCustCheckBox mute;
    private AbsButton pauseStack;
    private AbsButton stopStack;
    private AbsTextArea statusAnalyzeArea;
    private AbsPanel navigation;
    private final AbsOpenFrameInteract menuManage;
    private AbsScrollPane statusDbgAreaScroll;
    private AbsScrollPane statusDbgAreaScrollRender;
    private CustList<RenderPointPair> renderList = new CustList<RenderPointPair>();
    private AbstractInterceptorStdCaller caller;
    private AbstractPageEl currentPage;
    private AbsTextArea dynamicEval;
    private AbsButton evalPage;
    private AbsButton evalNoPage;
    private DbgRootStruct rootStructStr;
    private CustList<AbsTreeGui> dynTrees = new CustList<AbsTreeGui>();
    private IdList<AbsButton> buttons = new IdList<AbsButton>();
    private IdList<AbsButton> buttonsDynRef = new IdList<AbsButton>();
    private AbsTabbedPane watches;
//    private AbsPanel cancelDynWatch;
    private AbsButton refreshRender;
    private IdMap<FileBlock, IdMap<SyntaxRefTokenEnum,CustList<SegmentReadOnlyTokenPart>>> syntax = new IdMap<FileBlock, IdMap<SyntaxRefTokenEnum, CustList<SegmentReadOnlyTokenPart>>>();

    protected AbsDebuggerGui(AbsOpenFrameInteract _m, AbsResultContextNext _a, AbstractProgramInfos _list, CdmFactory _fact) {
        super(_a, _list);
        menuManage = _m;
        factory = _fact;
        framePoints = new FramePoints(this, _list);
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
        AbsTabbedPane superTab_ = getCommonFrame().getFrames().getCompoFactory().newAbsTabbedPane();
        AbsPanel pagePrep_ = getCommonFrame().getFrames().getCompoFactory().newPageBox();
        folderSystem = getCommonFrame().getFrames().getCompoFactory().newTreeGui(getCommonFrame().getFrames().getCompoFactory().newMutableTreeNode(""));
        folderSystem.select(folderSystem.getRoot());
        tabbedPane = getCommonFrame().getFrames().getCompoFactory().newAbsTabbedPane();
        tabbedPane.setPreferredSize(new MetaDimension(512,512));
        AbsPanel spec_ = buildPart();
        spec_.add(pagePrep_);
        superTab_.addIntTab("prepare",getCommonFrame().getFrames().getCompoFactory().newVerticalSplitPane(getCommonFrame().getFrames().getCompoFactory().newHorizontalSplitPane(getCommonFrame().getFrames().getCompoFactory().newAbsScrollPane(folderSystem),tabbedPane), spec_));
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
        nextCursorInstruction = getCommonFrame().getFrames().getCompoFactory().newPlainButton("_");
        nextCursorInstruction.setEnabled(false);
        nextCursorExpression = getCommonFrame().getFrames().getCompoFactory().newPlainButton("__");
        nextCursorExpression.setEnabled(false);
        pauseStack = getCommonFrame().getFrames().getCompoFactory().newPlainButton("||");
        pauseStack.setEnabled(false);
        stopStack = getCommonFrame().getFrames().getCompoFactory().newPlainButton("stop");
        stopStack.setEnabled(false);
        detail = getCommonFrame().getFrames().getCompoFactory().newAbsScrollPane();
        callStack = getCommonFrame().getFrames().getCompoFactory().newPageBox();
        callStackRender = getCommonFrame().getFrames().getCompoFactory().newPageBox();
        AbsSplitPane calls_ = getCommonFrame().getFrames().getCompoFactory().newHorizontalSplitPane(
                getCommonFrame().getFrames().getCompoFactory().newAbsScrollPane(callStack),
                getCommonFrame().getFrames().getCompoFactory().newAbsScrollPane(callStackRender));
        AbsPanel dynPanel_ = getCommonFrame().getFrames().getCompoFactory().newPageBox();
        dynamicEval = getCommonFrame().getFrames().getCompoFactory().newTextArea();
        evalPage = getCommonFrame().getFrames().getCompoFactory().newPlainButton("eval page");
        evalNoPage = getCommonFrame().getFrames().getCompoFactory().newPlainButton("eval no page");
        watches = getCommonFrame().getFrames().getCompoFactory().newAbsTabbedPane();
        dynTrees = new CustList<AbsTreeGui>();
        buttons = new IdList<AbsButton>();
        buttonsDynRef = new IdList<AbsButton>();
//        cancelDynWatch = getCommonFrame().getFrames().getCompoFactory().newPageBox();
        dynPanel_.add(getCommonFrame().getFrames().getCompoFactory().newAbsScrollPane(dynamicEval));
        dynPanel_.add(evalPage);
        dynPanel_.add(evalNoPage);
        dynPanel_.add(watches);
//        dynPanel_.add(cancelDynWatch);
        refreshRender = getCompoFactory().newPlainButton("refresh render");
        AbsSplitPane detRender_ = getCommonFrame().getFrames().getCompoFactory().newVerticalSplitPane(refreshRender,detail);
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
        nav_.add(nextCursorInstruction);
        nav_.add(nextCursorExpression);
        nav_.add(pauseStack);
        nav_.add(stopStack);
        pagePrep_.add(nav_);
        sessionTab = getCommonFrame().getFrames().getCompoFactory().newAbsTabbedPane();
        sessionTab.setVisible(false);
        sessionTab.addIntTab("calls",calls_);
        sessionTab.addIntTab("render calculation",detRender_);
        sessionTab.addIntTab("dynamic",getCommonFrame().getFrames().getCompoFactory().newAbsScrollPane(dynPanel_));
        statusAnalyzeArea = getCommonFrame().getFrames().getCompoFactory().newTextArea();
        statusAnalyzeArea.setEditable(false);
        superTab_.addIntTab("status",getCommonFrame().getFrames().getCompoFactory().newAbsScrollPane(statusAnalyzeArea));
        statusDbgAreaScroll = getCommonFrame().getFrames().getCompoFactory().newAbsScrollPane();
        statusDbgAreaScrollRender = getCommonFrame().getFrames().getCompoFactory().newAbsScrollPane();
        sessionTab.addIntTab("logs",statusDbgAreaScroll);
        sessionTab.addIntTab("render",statusDbgAreaScrollRender);
        pagePrep_.add(sessionTab);
        AbsPanel pane_ = getCommonFrame().getFrames().getCompoFactory().newPageBox();
        pane_.add(superTab_);
        getCommonFrame().setContentPane(pane_);
        getCommonFrame().setVisible(true);
        AbsMenuBar bar_ = getCommonFrame().getFrames().getCompoFactory().newMenuBar();
        EnabledMenu session_ = getCommonFrame().getFrames().getCompoFactory().newMenu("session");
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
        AbstractMutableTreeNodeCore<String> sel_ = _t.selectEvt();
        if (sel_ == null) {
            return;
        }
        String str_ = GuiBaseUtil.buildPath(sel_);
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
    void refresh(AbstractMutableTreeNodeCore<String> _sel, String _str, AbsTreeGui _t) {
        refParent(_sel, _str, _t);
    }

    void refParent(AbstractMutableTreeNodeCore<String> _parent, String _parentPath, AbsTreeGui _t) {
        refParent(_parent, viewable, _parentPath, _t);
    }

    void refParent(AbstractMutableTreeNodeCore<String> _parent, StringMap<String> _files, String _parentPath, AbsTreeGui _t) {
        _parent.removeAllChildren();
        refreshList(_parent, _files, _parentPath, getCommonFrame().getFrames().getCompoFactory());
        MutableTreeNodeUtil.reload(_t);
    }
    static void refreshList(AbstractMutableTreeNodeCore<String> _sel, StringMap<String> _files, String _folderToVisit, AbsCompoFactory _compoFactory) {
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
            _sel.add(_compoFactory.newMutableTreeNode(f+"/"));
        }
        for (String f : currentFiles_) {
            _sel.add(_compoFactory.newMutableTreeNode(f));
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
            callStackRender.removeAll();
            callButtons.clear();
            callButtonsRender.clear();
            root = new DbgRootStruct(ctx_, null);
            treeDetail = root.buildReturn(this,renderList,getCommonFrame().getFrames().getCompoFactory(), getCommonFrame().getFrames().getThreadFactory(), view_.getStack().aw());
            AbsButton shRend_ = getCommonFrame().getFrames().getCompoFactory().newPlainButton("show render");
            shRend_.addActionListener(new DbgSelectNodeLogEvent(root,treeDetail,statusDbgAreaScrollRender));
            callStackRender.add(shRend_);
            callButtonsRender.add(shRend_);
            detail.setViewportView(treeDetail);
            sessionTab.setVisible(true);
            selectEnter.setEnabled(true);
            int opened_ = tabbedPane.getSelectedIndex();
            focus(opened_);
            endCall();
            getAnalyzeMenu().setEnabled(true);
            return;
        }
        callButtons.clear();
        callButtonsRender.clear();
        callStack.removeAll();
        callStackRender.removeAll();
        int nbPages_ = view_.getVariables().size();
        for (int i = 0; i< nbPages_; i++) {
            ViewPage p_ = view_.getVariables().get(i);
            String dis_ = p_.getStackElt().getDisplayedString(ctx_).getInstance();
            DbgRootStruct r_ = new DbgRootStruct(ctx_, null);
            root = r_;
            AbsTreeGui b_ = r_.build(this,renderList,getCommonFrame().getFrames().getCompoFactory(), getCommonFrame().getFrames().getThreadFactory(), p_, stackCall.getBreakPointInfo().getBreakPointOutputInfo());
            treeDetail = b_;
            AbsButton but_ = getCommonFrame().getFrames().getCompoFactory().newPlainButton(dis_);
            callButtons.add(but_);
            but_.addActionListener(new SelectCallStackEvent(this,p_,b_,r_));
            callStack.add(but_);
            AbsButton shRend_ = getCommonFrame().getFrames().getCompoFactory().newPlainButton("show render");
            shRend_.addActionListener(new DbgSelectNodeLogEvent(r_,b_,statusDbgAreaScrollRender));
            callStackRender.add(shRend_);
            callButtonsRender.add(shRend_);
        }
        AbstractPageEl last_ = getStackCall().getLastPage();
        int opened_ = indexOpened(ExecFileBlock.name(last_.getFile()));
        selectFocus(opened_, last_.getTraceIndex());
        currentPage = last_;
        detail.setViewportView(treeDetail);
        GuiBaseUtil.removeActionListeners(refreshRender);
        refreshRender.addActionListener(new RefreshRenderEvent(this));
        rootStructStr = new DbgRootStruct(root.getResult(), null);
        GuiBaseUtil.removeActionListeners(evalPage);
        evalPage.addActionListener(new EvalPageEvent(this,_res));
        GuiBaseUtil.removeActionListeners(evalNoPage);
        evalNoPage.addActionListener(new EvalNoPageEvent(this,_res));
        sessionTab.setVisible(true);
        PackingWindowAfter.pack(getCommonFrame());
        nextAction.setEnabled(true);
        nextInstruction.setEnabled(true);
        nextBlock.setEnabled(true);
        nextGoUp.setEnabled(true);
        nextInMethod.setEnabled(true);
        nextCursorInstruction.setEnabled(true);
        nextCursorExpression.setEnabled(true);
        getStopStack().setEnabled(true);
        getAnalyzeMenu().setEnabled(true);
    }
    public void hideVars() {
        sessionTab.setVisible(false);
    }
    public void dynamicAnalyzeSelectedPage(ResultContext _res) {
        DbgRootStruct root_ = new DbgRootStruct(root.getResult(), null);
        this.rootStructStr = root_;
        AbsTreeGui d_ = root_.buildDynamic(this,renderList, getCommonFrame().getFrames().getCompoFactory(), getCommonFrame().getFrames().getThreadFactory());
        getThreadFactory().newStartedThread(build(_res, root_, d_, currentPage));
    }
    public void dynamicAnalyzeNoSelectedPage(ResultContext _res) {
        DbgRootStruct root_ = new DbgRootStruct(root.getResult(), null);
        this.rootStructStr = root_;
        AbsTreeGui d_ = root_.buildDynamic(this,renderList, getCommonFrame().getFrames().getCompoFactory(), getCommonFrame().getFrames().getThreadFactory());
        getThreadFactory().newStartedThread(build(_res, root_, d_, null));
    }

    private DynamicAnalysisTask build(ResultContext _res, DbgRootStruct _root, AbsTreeGui _tree, AbstractPageEl _call) {
        DynamicAnalysisTask d_ = new DynamicAnalysisTask(this, _call, _res, _tree, _root);
        buttons.add(d_.getStButton());
        dynTrees.add(d_.getTree());
        buttonsDynRef.add(d_.getRefreshButton());
        return d_;
    }

    public void refreshDynamic(DynamicAnalysisTask _d, WatchResults _wr) {
        AbsTreeGui tr_ = _d.getTree();
        _d.getRoot().addWatches(getCommonFrame().getFrames().getCompoFactory(), tr_.getRoot(), _wr);
        _d.getScroll().setViewportView(tr_);
        getCommonFrame().pack();
    }

    public AbsTabbedPane getWatches() {
        return watches;
    }

    public IdList<AbsButton> getButtonsDynRef() {
        return buttonsDynRef;
    }

    public static WatchResults dynamicAnalyze(String _dyn, StackCall _stack, AbstractPageEl _page, ResultContext _res, AbsLightContextGenerator _gene, AdvLogDbg _a) {
        if (_page == null) {
            return WatchResults.dynamicAnalyze(_dyn,_stack,_res,_gene,new DefStackStopper(_a));
        }
        return WatchResults.dynamicAnalyze(_dyn,_res,_gene,_page,new DefStackStopper(_a));
    }

    public void refreshRender() {
        ref(treeDetail, root);
    }

    public void refreshRenderDyn(AbsTreeGui _tree, DbgRootStruct _root) {
        ref(_tree, _root);
    }

    private void ref(AbsTreeGui _tree, DbgRootStruct _root) {
        AbstractMutableTreeNodeCore<String> sel_ = _tree.selectEvt();
        AbstractMutableTreeNodeCore<DbgAbsNodeStruct> e_ = _root.getNode().simular(sel_);
        if (e_ == null) {
            getThreadFactory().newStartedThread(null);
            return;
        }
        DbgAbsNodeStruct i_ = e_.info();
        i_.removeChildren();
        DbgSelectNodeEvent.process(this,i_, _tree,getCompoFactory(),getThreadFactory(),renderList);
    }
    public void possibleSelectInstruction(int _s, ResultContext _res) {
        if (_s > -1) {
            FileBlock f_ = _res.getPageEl().getPreviousFilesBodies().getVal(tabs.get(_s).getFullPath());
            ExecFileBlock e_ = _res.getFiles().getVal(f_);
            int caret_ = tabs.get(_s).getCenter().getCaretPosition();
            _res.getContext().tmpList().add(new BreakPointBlockKey(e_, FileBlock.number(f_), ResultExpressionOperationNode.beginPart(caret_,f_)));
        }
    }

    public void possibleSelectExpression(int _s, ResultContext _res) {
        if (_s > -1) {
            FileBlock f_ = _res.getPageEl().getPreviousFilesBodies().getVal(tabs.get(_s).getFullPath());
            ExecFileBlock e_ = _res.getFiles().getVal(f_);
            int caret_ = tabs.get(_s).getCenter().getCaretPosition();
            _res.getContext().tmpList().add(new BreakPointBlockKey(e_, FileBlock.number(f_), ResultExpressionOperationNode.beginPartExp(caret_,f_)));
        }
    }

    public void updateGui(ViewPage _view, AbsTreeGui _treeDetailEvt, DbgRootStruct _treeRoot) {
        int opened_ = indexOpened(_view.getFileName());
        selectFocus(opened_, _view.getTrace());
        currentPage = _view.getPage();
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
            tabs.get(_open).getCenter().requestFocusInWindow();
            tabs.get(_open).getCenter().visibleCaret();
        }
    }

    public void focus(int _opened) {
        if (_opened > -1) {
            tabs.get(_opened).getCenter().requestFocusInWindow();
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
        syntax = DbgSyntaxColoring.partsTokens(_res);
        getCompoFactory().invokeNow(new AnalysisDebugLater(this, _res, _src));
    }
    public void coreUpdate(ResultContext _res, StringMap<String> _src) {
        if (_res.getPageEl().notAllEmptyErrors()) {
            statusAnalyzeArea.append(_res.getReportedMessages().displayErrors());
            selectEnter.setEnabled(false);
            navigation.setVisible(false);
        } else {
            selectEnter.setEnabled(true);
            navigation.setVisible(true);
        }
        caller = _res.getContext().getCaller();
        renderList = new CustList<RenderPointPair>();
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
        GuiBaseUtil.removeActionListeners(nextCursorInstruction);
        nextCursorInstruction.addActionListener(new DbgNextBpEvent(this, StepDbgActionEnum.CURSOR_INSTRUCTION, _res));
        GuiBaseUtil.removeActionListeners(nextCursorExpression);
        nextCursorExpression.addActionListener(new DbgNextBpEvent(this, StepDbgActionEnum.CURSOR_EXPRESSION, _res));
        GuiBaseUtil.removeActionListeners(stopStack);
        stopStack.addActionListener(new StopStackEvent(this, _res));
        GuiBaseUtil.removeActionListeners(pauseStack);
        pauseStack.addActionListener(new PauseStackEvent(this, _res));
        GuiBaseUtil.removeActionListeners(openPoints);
        openPoints.addActionListener(new OpenFramePointsEvent(this,framePoints, _res));
        GuiBaseUtil.removeTreeSelectionListeners(folderSystem);
        folderSystem.addTreeSelectionListener(new ShowSrcReadOnlyTreeEvent(this,_res,folderSystem,new TabOpeningReadOnlyFile()));
        refParent(folderSystem.getRoot(),"",folderSystem);
        framePoints.refresh(viewable,this, _res);
        closeCompos();
        int len_ = _src.size();
        for (int i = 0; i < len_; i++) {
            String fullPath_ = pathToSrc(manageOptions)+ _src.getKey(i);
            BytesInfo content_ = new BytesInfo(StringUtil.encode(_src.getValue(i)),false);
            addTab(_res,manageOptions,fullPath_,content_, manageOptions.getOptions());
        }
        getCommonFrame().pack();
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
        getNextCursorInstruction().setEnabled(false);
        getNextCursorExpression().setEnabled(false);
    }
    public void currentThreadActions(Runnable _t) {
        getCommonFrame().getFrames().getThreadFactory().newStartedThread(_t);
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

    public IdMap<FileBlock, IdMap<SyntaxRefTokenEnum, CustList<SegmentReadOnlyTokenPart>>> getSyntax() {
        return syntax;
    }

    public AbstractInterceptorStdCaller getCaller() {
        return caller;
    }

    public CustList<RenderPointPair> getRenderList() {
        return renderList;
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

    public AbsButton getSelectEnter() {
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

    public AbsButton getNextAction() {
        return nextAction;
    }

    public AbsButton getNextInstruction() {
        return nextInstruction;
    }

    public AbsButton getNextBlock() {
        return nextBlock;
    }

    public AbsButton getNextGoUp() {
        return nextGoUp;
    }

    public AbsButton getNextInMethod() {
        return nextInMethod;
    }

    public AbsButton getNextCursorInstruction() {
        return nextCursorInstruction;
    }

    public AbsButton getNextCursorExpression() {
        return nextCursorExpression;
    }

    public AbsButton getPauseStack() {
        return pauseStack;
    }

    public AbsPanel getCallStack() {
        return callStack;
    }

    public AbsPanel getCallStackRender() {
        return callStackRender;
    }

    public CustList<AbsButton> getCallButtons() {
        return callButtons;
    }

    public CustList<AbsButton> getCallButtonsRender() {
        return callButtonsRender;
    }

    public EnabledMenu getAnalyzeMenu() {
        return analyzeMenu;
    }

    public EnabledMenu getOpenPoints() {
        return openPoints;
    }

    public AbstractAtomicBoolean getStopDbg() {
        return stopDbg;
    }

    public AbsButton getStopStack() {
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

    public AbsScrollPane getStatusDbgAreaScroll() {
        return statusDbgAreaScroll;
    }

    public AbsTextArea getDynamicEval() {
        return dynamicEval;
    }

    public AbsButton getEvalPage() {
        return evalPage;
    }

    public AbsButton getEvalNoPage() {
        return evalNoPage;
    }

    public DbgRootStruct getRootStructStr() {
        return rootStructStr;
    }

    public CustList<AbsTreeGui> getDynTrees() {
        return dynTrees;
    }

//    public AbsPanel getCancelDynWatch() {
//        return cancelDynWatch;
//    }

    public IdList<AbsButton> getButtons() {
        return buttons;
    }

    public AbsButton getRefreshRender() {
        return refreshRender;
    }
}
