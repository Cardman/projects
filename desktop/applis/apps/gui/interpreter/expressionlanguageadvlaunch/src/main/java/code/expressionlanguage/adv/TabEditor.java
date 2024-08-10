package code.expressionlanguage.adv;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.exec.ExecClassesUtil;
import code.expressionlanguage.fwd.blocks.ForwardInfos;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.CustAliases;
import code.expressionlanguage.utilcompo.InterruptibleContextEl;
import code.gui.*;
import code.gui.events.AbsEnabledAction;
import code.gui.images.MetaDimension;
import code.gui.images.MetaFont;
import code.gui.initialize.AbstractProgramInfos;
import code.threads.AbstractAtomicBoolean;
import code.threads.AbstractBaseExecutorService;
import code.threads.AbstractFuture;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class TabEditor implements AbsTabEditor {
    private final WindowWithTreeImpl windowSecEditor;
    private final AbstractProgramInfos factories;
    private final AbsScrollPane scCenter;
    private final AbsTextPane center;
    private final AbsTextField finder;
    private final AbsCustCheckBox caseSens;
    private final AbsCustCheckBox wholeWord;
    private final AbsTextField replacer;
    private final AbsButton prevOcc;
    private final AbsButton nextOcc;
    private final AbsButton closeFinder;
    private final AbsButton replaceOne;
    private final AbsButton replaceAll;
    private final AbsButton replacePrevious;
    private final AbsButton replaceNext;
    private final AbsButton replaceOneExp;
    private final AbsButton replaceAllExp;
    private final AbsButton replacePreviousExp;
    private final AbsButton replaceNextExp;
    private final AbsButton applyExp;
    private final AbsTextPane preview;
    private final AbsPanel navModifPanel;
    private final AbsPanel finderPanel;
    private final AbsPanel replacerPanel;
    private final AbsButton refreshExpression;
    private final AbsPlainLabel lastBuild;
    private final AbsButton selectExpressionClass;
    private final AbsButton findingExpression;
    private final AbsButton findingExpressionCancel;
    private final AbsButton closeExpression;
    private final AbsButton prevOccExp;
    private final AbsButton nextOccExp;
    private final AbsPanel panel;
    private final CustList<SegmentFindPart> parts = new CustList<SegmentFindPart>();
    private final CustList<SegmentFindPart> partsExp = new CustList<SegmentFindPart>();
    private final AbsPlainLabel label;
    private final AbsPlainLabel labelOcc;
    private final AbsPlainLabel labelOccExp;
    private final StringList texts = new StringList();
    private final AbsEnabledAction undo;
    private final AbsEnabledAction redo;
    private final AbstractBaseExecutorService taskManager;
    private final AbstractBaseExecutorService taskManagerExp;
    private final AbsPanel expSpli;
    private final AbsSplitPane mainSplitter;
    private boolean enabledSyntax = true;
    private int currentPart = -1;
    private int currentPartExp = -1;
    private int currentText = -1;
    private String fullPath;
    private String relPath;
    private final String useFeed;
    private int index=-1;
    private int dest;
    private final AbsSpinner row;
    private final AbsSpinner col;
    private final AbsButton val;
    private ContextEl action;
    private AbstractFuture task;
    private Struct instance = NullStruct.NULL_VALUE;
    private final FormFindReplaceExpression findReplaceExpression;

    public TabEditor(WindowWithTreeImpl _editor, String _fullPath, String _rel, String _lr) {
        useFeed = _lr;
        fullPath = _fullPath;
        relPath = _rel;
        windowSecEditor = _editor;
        AbstractProgramInfos frames_ = _editor.getCommonFrame().getFrames();
        findReplaceExpression = new FormFindReplaceExpression(frames_);
        taskManager = frames_.getThreadFactory().newExecutorService();
        taskManagerExp = frames_.getThreadFactory().newExecutorService();
        factories = frames_;
        label = frames_.getCompoFactory().newPlainLabel(":");
        labelOcc = frames_.getCompoFactory().newPlainLabel(AbsEditorTabList.SLASH);
        labelOccExp = frames_.getCompoFactory().newPlainLabel(AbsEditorTabList.SLASH);
        center = frames_.getCompoFactory().newTextPane();
        center.setFocusable(true);
        center.addMouseListener(new ClickTextPane(center));
        center.setFont(new MetaFont(GuiConstants.MONOSPACED,GuiConstants.fontStyle(false,false),12));
        center.setBackground(GuiConstants.BLACK);
        center.setForeground(GuiConstants.WHITE);
        center.setCaretColor(GuiConstants.WHITE);
        center.addCaretListener(new EditorCaretListener(this));
        center.addAutoComplete(new DocumentTextChange(this));
        finder = frames_.getCompoFactory().newTextField();
        replacer = frames_.getCompoFactory().newTextField();
        prevOcc = frames_.getCompoFactory().newPlainButton("<-");
        nextOcc = frames_.getCompoFactory().newPlainButton("->");
        closeFinder = frames_.getCompoFactory().newPlainButton("x");
        refreshExpression = frames_.getCompoFactory().newPlainButton("refresh");
        lastBuild = frames_.getCompoFactory().newPlainLabel(CustAliases.YYYY_MM_DD_HH_MM_SS_SSS_DASH);
        selectExpressionClass = frames_.getCompoFactory().newPlainButton("reset");
        findingExpression = frames_.getCompoFactory().newPlainButton("find");
        findingExpressionCancel = frames_.getCompoFactory().newPlainButton("cancel");
        prevOccExp = frames_.getCompoFactory().newPlainButton("<-");
        nextOccExp = frames_.getCompoFactory().newPlainButton("->");
        closeExpression = frames_.getCompoFactory().newPlainButton("x");
        replaceOne = frames_.getCompoFactory().newPlainButton("1");
        replaceOne.addActionListener(new ReplaceAction(this,false,false));
        replaceAll = frames_.getCompoFactory().newPlainButton("*");
        replaceAll.addActionListener(new ReplaceAction(this,true,true));
        replacePrevious = frames_.getCompoFactory().newPlainButton("<-");
        replacePrevious.addActionListener(new ReplaceAction(this,true,false));
        replaceNext = frames_.getCompoFactory().newPlainButton("->");
        replaceNext.addActionListener(new ReplaceAction(this,false,true));
        navModifPanel = frames_.getCompoFactory().newPageBox();
        navModifPanel.setVisible(false);
        finderPanel = frames_.getCompoFactory().newLineBox();
        AbsPanel colRowPanel_ = frames_.getCompoFactory().newLineBox();
        finder.addAutoComplete(new FinderTextChange(this));
        finderPanel.add(finder);
        caseSens = frames_.getCompoFactory().newCustCheckBox("Aa");
        caseSens.addActionListener(new ToggleFindOptionEvent(this));
        caseSens.setSelected(true);
        finderPanel.add(caseSens);
        wholeWord = frames_.getCompoFactory().newCustCheckBox("_");
        wholeWord.addActionListener(new ToggleFindOptionEvent(this));
        wholeWord.setSelected(true);
        finderPanel.add(wholeWord);
        row = frames_.getCompoFactory().newSpinner(1, 1, Integer.MAX_VALUE, 1);
        col = frames_.getCompoFactory().newSpinner(1, 1, Integer.MAX_VALUE, 1);
        row.addChangeListener(new RowColStateChangedEvent(this,row,col));
        colRowPanel_.add(row);
        col.addChangeListener(new RowColStateChangedEvent(this,row,col));
        colRowPanel_.add(col);
        val = frames_.getCompoFactory().newPlainButton("GO");
        val.addActionListener(new ValidateNavLine(this));
        colRowPanel_.add(val);
        closeFinder.addActionListener(new ClosePanelAction(this));
        finderPanel.add(labelOcc);
        prevOcc.addActionListener(new ChgSegmentPartEvent(this,-1));
        finderPanel.add(prevOcc);
        nextOcc.addActionListener(new ChgSegmentPartEvent(this,1));
        finderPanel.add(nextOcc);
        finderPanel.add(closeFinder);
        navModifPanel.add(finderPanel);
        navModifPanel.add(colRowPanel_);
        replacerPanel = frames_.getCompoFactory().newLineBox();
        replacerPanel.add(replacer);
        replacerPanel.add(replaceOne);
        replacerPanel.add(replaceAll);
        replacerPanel.add(replacePrevious);
        replacerPanel.add(replaceNext);
        navModifPanel.add(replacerPanel);
        AbsPanel expressionPanel_ = frames_.getCompoFactory().newPageBox();
        refreshExpression.addActionListener(new RefreshExpressionEvent(this));
        AbsPanel finderClass_ = frames_.getCompoFactory().newLineBox();
        finderClass_.add(refreshExpression);
        finderClass_.add(lastBuild);
        finderClass_.add(findReplaceExpression.getFinderExpClasses());
        selectExpressionClass.addActionListener(new SelectClassEvent(this));
        selectExpressionClass.setEnabled(false);
        finderClass_.add(selectExpressionClass);
        expressionPanel_.add(finderClass_);
        AbsPanel expressionFind_ = frames_.getCompoFactory().newLineBox();
        findingExpression.setEnabled(false);
        findingExpression.addActionListener(new FindExpressionEvent(this));
        expressionFind_.add(findingExpression);
        findingExpressionCancel.setEnabled(false);
        findingExpressionCancel.addActionListener(new FindExpressionStop(this));
        expressionFind_.add(findingExpressionCancel);
        expressionPanel_.add(expressionFind_);
        AbsPanel expressionNav_ = frames_.getCompoFactory().newLineBox();
        expressionNav_.add(labelOccExp);
        prevOccExp.addActionListener(new ChgSegmentPartExpEvent(this,-1));
        prevOccExp.setEnabled(false);
        expressionNav_.add(prevOccExp);
        nextOccExp.addActionListener(new ChgSegmentPartExpEvent(this,1));
        nextOccExp.setEnabled(false);
        expressionNav_.add(nextOccExp);
        expressionPanel_.add(expressionNav_);
        AbsPanel expressionRepl_ = frames_.getCompoFactory().newLineBox();
        replaceOneExp = frames_.getCompoFactory().newPlainButton("1");
        replaceOneExp.addActionListener(new ReplaceExpressionAction(this,false,false));
        replaceOneExp.setEnabled(false);
        expressionRepl_.add(replaceOneExp);
        replaceAllExp = frames_.getCompoFactory().newPlainButton("*");
        replaceAllExp.addActionListener(new ReplaceExpressionAction(this,true,true));
        replaceAllExp.setEnabled(false);
        expressionRepl_.add(replaceAllExp);
        replacePreviousExp = frames_.getCompoFactory().newPlainButton("<-");
        replacePreviousExp.addActionListener(new ReplaceExpressionAction(this,true,false));
        replacePreviousExp.setEnabled(false);
        expressionRepl_.add(replacePreviousExp);
        replaceNextExp = frames_.getCompoFactory().newPlainButton("->");
        replaceNextExp.addActionListener(new ReplaceExpressionAction(this,false,true));
        replaceNextExp.setEnabled(false);
        expressionRepl_.add(replaceNextExp);
        expressionPanel_.add(expressionRepl_);
        AbsPanel applRepl_ = frames_.getCompoFactory().newLineBox();
        applyExp = frames_.getCompoFactory().newPlainButton("apply");
        applyExp.addActionListener(new ReplaceExpressionApply(this));
        applyExp.setEnabled(false);
        applRepl_.add(applyExp);
        preview = frames_.getCompoFactory().newTextPane();
        preview.setEditable(false);
        preview.setFont(new MetaFont(GuiConstants.MONOSPACED,GuiConstants.fontStyle(false,false),12));
        preview.setBackground(GuiConstants.BLACK);
        preview.setForeground(GuiConstants.WHITE);
        preview.setCaretColor(GuiConstants.WHITE);
        closeExpression.addActionListener(new ClosePanelExpressionAction(this));
        applRepl_.add(closeExpression);
        expressionPanel_.add(applRepl_);
        expSpli = frames_.getCompoFactory().newPageBox();
        expSpli.add(expressionPanel_);
        expSpli.add(frames_.getCompoFactory().newAbsScrollPane(preview));
        expSpli.setVisible(false);
        undo = frames_.getCompoFactory().wrap(new UndoRedoAction(this, -1));
        redo = frames_.getCompoFactory().wrap(new UndoRedoAction(this, 1));
        undo.setEnabled(false);
        redo.setEnabled(false);
        center.registerKeyboardAction(frames_.getCompoFactory().wrap(new ExpressionAction(this)),GuiConstants.VK_G,GuiConstants.CTRL_DOWN_MASK);
        center.registerKeyboardAction(frames_.getCompoFactory().wrap(new FindAction(this, true)),GuiConstants.VK_F,GuiConstants.CTRL_DOWN_MASK);
        center.registerKeyboardAction(frames_.getCompoFactory().wrap(new FindAction(this, false)),GuiConstants.VK_R,GuiConstants.CTRL_DOWN_MASK);
        center.registerKeyboardAction(frames_.getCompoFactory().wrap(new StoreUndoRedoAction(this)),GuiConstants.VK_Y,GuiConstants.CTRL_DOWN_MASK+GuiConstants.SHIFT_DOWN_MASK);
        center.registerKeyboardAction(undo,GuiConstants.VK_Z,GuiConstants.CTRL_DOWN_MASK);
        center.registerKeyboardAction(redo,GuiConstants.VK_Y,GuiConstants.CTRL_DOWN_MASK);
        center.registerKeyboardAction(frames_.getCompoFactory().wrap(new ClearUndoRedoAction(this)),GuiConstants.VK_Z,GuiConstants.CTRL_DOWN_MASK+GuiConstants.SHIFT_DOWN_MASK);
        center.registerKeyboardAction(frames_.getCompoFactory().wrap(new SaveTextFileNode(this)),GuiConstants.VK_S,GuiConstants.CTRL_DOWN_MASK);
        center.registerKeyboardAction(frames_.getCompoFactory().wrap(new CloseTabEditorEvent(this)),GuiConstants.VK_K,GuiConstants.CTRL_DOWN_MASK);
        center.registerKeyboardAction(frames_.getCompoFactory().wrap(new LookForDefinitionFullCustAnalysisEvent(this)),GuiConstants.VK_T,GuiConstants.CTRL_DOWN_MASK);
        panel = frames_.getCompoFactory().newPageBox();
        AbsPanel upp_ = frames_.getCompoFactory().newPageBox();
        scCenter = frames_.getCompoFactory().newAbsScrollPane(center);
        scCenter.setPreferredSize(new MetaDimension(512,512));
        upp_.add(scCenter);
        upp_.add(label);
        upp_.add(navModifPanel);
        mainSplitter = frames_.getCompoFactory().newVerticalSplitPane(upp_, expSpli);
        panel.add(mainSplitter);
//        caseSens.setFocusable(false);
//        wholeWord.setFocusable(false);
//        finder.setFocusable(false);
//        prevOcc.setFocusable(false);
//        nextOcc.setFocusable(false);
//        closeFinder.setFocusable(false);
//        row.setFocusable(false);
//        col.setFocusable(false);
//        val.setFocusable(false);
    }
    public void afterValidate(int _dest) {
        index = _dest;
//        center.setEditable(true);
        center.requestFocusInWindow();
        centerSelect(dest, dest);
        center.visibleCaret();
//        center.setEditable(false);
        dest = 0;
    }
    public AbsSpinner getRow() {
        return row;
    }

    public AbsSpinner getCol() {
        return col;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int _i) {
        this.index = _i;
    }

    public AbsButton getVal() {
        return val;
    }

    public boolean enabled(int _row, int _col) {
        return index(_row, _col) > -1;
    }
    public int index(int _row, int _col) {
        int adjRow_ = _row - 1;
        int adjCol_ = _col - 1;
        int tw_ = windowSecEditor.getTabWidth();
        String txt_ = centerText();
        int index_ = 0;
        int row_ = 0;
        while (index_ >= 0) {
            int next_ = txt_.indexOf(AbsEditorTabList.LINE_RETURN_CH,index_);
            if (row_ == adjRow_) {
                int j_ = tab(tw_, txt_, index_, adjCol_);
                if (j_ > -1) {
                    dest = index_ + j_;
                    return dest;
                }
                dest(next_,txt_);
                return -1;
            }
            if (next_ < 0) {
                index_=-1;
            } else {
                index_ = next_ + 1;
                row_++;
            }
        }
        dest(-1,txt_);
        return -1;
    }

    private void dest(int _index,String _txt) {
        if (_index > -1) {
            dest = _index;
            return;
        }
        dest = _txt.length();
    }

    private int tab(int _tw, String _txt, int _index, int _delta) {
        int j_ = 0;
        int d_ = 0;
        while (d_ < _delta) {
            int curIndex_ = j_ + _index;
            if (curIndex_ >= _txt.length()) {
                return -1;
            }
            char ch_ = _txt.charAt(curIndex_);
            if (ch_ == AbsEditorTabList.LINE_RETURN_CH) {
                return -1;
            }
            if (ch_ == AbsEditorTabList.TAB_CH) {
                d_ += _tw;
                d_ -= d_ % _tw;
            } else {
                d_++;
            }
            j_++;
        }
        return j_;
    }

    public void updateNavSelect() {
        if (getParts().isValidIndex(getCurrentPart())) {
            SegmentFindPart s_ = getParts().get(getCurrentPart());
            centerSelect(s_.getBegin(),s_.getEnd());
        }
        updateNav();
    }

    public void updateNavSelectExp() {
        if (getPartsExp().isValidIndex(getCurrentPartExp())) {
            SegmentFindPart s_ = getPartsExp().get(getCurrentPartExp());
            previewSelect(s_.getBegin(),s_.getEnd());
        }
        updateNavExp();
    }

    public void updateNav() {
        int n_ = getCurrentPart();
        getLabelOcc().setText((n_+1)+AbsEditorTabList.SLASH+getParts().size());
        prevOcc.setEnabled(!getParts().isEmpty());
        nextOcc.setEnabled(!getParts().isEmpty());
    }
    public void updateNavExp() {
        int n_ = getCurrentPartExp();
        labelOccExp.setText((n_+1)+AbsEditorTabList.SLASH+getPartsExp().size());
        prevOccExp.setEnabled(!getPartsExp().isEmpty());
        nextOccExp.setEnabled(!getPartsExp().isEmpty());
    }
    public void enableExp(boolean _en) {
        findingExpression.setEnabled(_en);
        enableExpRepl(_en);
    }

    public void enableExpRepl(boolean _en) {
        replaceOneExp.setEnabled(_en);
        replaceAllExp.setEnabled(_en);
        replaceNextExp.setEnabled(_en);
        replacePreviousExp.setEnabled(_en);
    }

    public void refresh() {
        enableExp(false);
        findReplaceExpression.getFinderExpClasses().setEnabled(false);
        findingExpressionCancel.setEnabled(false);
        applyExp.setEnabled(false);
        prevOccExp.setEnabled(false);
        nextOccExp.setEnabled(false);
        ResultContext userResult_ = windowSecEditor.getMainFrame().getUserResult();
        if (userResult_ == null) {
            lastBuild.setText(CustAliases.YYYY_MM_DD_HH_MM_SS_SSS_DASH);
            selectExpressionClass.setEnabled(false);
            findReplaceExpression.getCompleteClasses().setDictionary(new StringList());
            findReplaceExpression.getDico().clear();
            findReplaceExpression.getDicoRepl().clear();
            return;
        }
        ResultContext base_ = windowSecEditor.getMainFrame().getBaseResult();
        ResultContext copy_ = windowSecEditor.getResultContextNext().next(base_,userResult_);
        ForwardInfos.generalForward(copy_);
        AbstractAtomicBoolean inter_ = windowSecEditor.getMainFrame().getCommonFrame().getFrames().getThreadFactory().newAtomicBoolean();
        action = windowSecEditor.getResultContextNext().generateAdv(inter_).geneWith(copy_.getForwards());
        ContextEl ctx_ = action;
        getFindingExpressionCancel().setEnabled(true);
        Classes.forwardAndClear(ctx_);
        Options options_ = copy_.getForwards().getOptions();
        ExecClassesUtil.tryInitStaticlyTypes(ctx_, options_);
        AbstractProgramInfos frames_ = windowSecEditor.getMainFrame().getCommonFrame().getFrames();
        lastBuild.setText(CustAliases.getDateTimeText(frames_.getThreadFactory()));
        selectExpressionClass.setEnabled(true);
        findReplaceExpression.refresh(base_,ctx_);
    }

    public void tryInterrupt() {
        if (action instanceof InterruptibleContextEl) {
            ((InterruptibleContextEl)action).stopJoinSleep();
        }
    }
    public void waitAndSubmit(Runnable _r) {
        AbstractFuture t_ = task;
        if (t_ != null) {
            t_.attendre();
        }
        task = getTaskManager().submitLater(_r);
    }
    public void shutdownTasks() {
        getTaskManager().shutdown();
        task = null;
    }
    public void usedType(String _u) {
        findReplaceExpression.usedType(_u);
    }

    public StringMap<ExecConstructorOverrideInfo> getDico() {
        return findReplaceExpression.getDico();
    }

    public StringMap<ExecConstructorOverrideInfo> getDicoRepl() {
        return findReplaceExpression.getDicoRepl();
    }
    public void centerSelect(int _selectionStart, int _selectionEnd) {
        getCenter().select(_selectionStart, _selectionEnd);
    }
    public void previewSelect(int _selectionStart, int _selectionEnd) {
        getPreview().select(_selectionStart, _selectionEnd);
    }
    public String previewText() {
        return getPreview().getText();
    }

    public void previewText(String _t) {
        getPreview().setText(_t);
    }

    public String centerText() {
        return getCenter().getText();
    }
    public void centerText(String _t) {
        getCenter().setText(_t);
    }
    @Override
    public int getTabWidth() {
        return getWindowSecEditor().getTabWidth();
    }

    public String getUseFeed() {
        return useFeed;
    }

    public AbsPlainLabel getLabelOcc() {
        return labelOcc;
    }

    public AbsPlainLabel getLabel() {
        return label;
    }

    public AbstractProgramInfos getFactories() {
        return factories;
    }

    public AbsButton getCloseFinder() {
        return closeFinder;
    }

    public AbsPanel getFinderPanel() {
        return finderPanel;
    }

    public AbsPanel getNavModifPanel() {
        return navModifPanel;
    }

    public AbsPanel getReplacerPanel() {
        return replacerPanel;
    }

    public AbsTextField getFinder() {
        return finder;
    }

    public AbsCustCheckBox getCaseSens() {
        return caseSens;
    }

    public AbsCustCheckBox getWholeWord() {
        return wholeWord;
    }

    public AbsButton getReplaceOne() {
        return replaceOne;
    }

    public AbsButton getReplaceAll() {
        return replaceAll;
    }

    public AbsButton getReplacePrevious() {
        return replacePrevious;
    }

    public AbsButton getReplaceNext() {
        return replaceNext;
    }

    public AbsButton getReplaceOneExp() {
        return replaceOneExp;
    }

    public AbsButton getReplaceAllExp() {
        return replaceAllExp;
    }

    public AbsButton getReplacePreviousExp() {
        return replacePreviousExp;
    }

    public AbsButton getReplaceNextExp() {
        return replaceNextExp;
    }

    public AbsTextField getReplacer() {
        return replacer;
    }

    public AbsScrollPane getScCenter() {
        return scCenter;
    }

    public AbsTextPane getCenter() {
        return center;
    }

    public AbsPanel getPanel() {
        return panel;
    }

    public AbsButton getPrevOcc() {
        return prevOcc;
    }

    public AbsButton getNextOcc() {
        return nextOcc;
    }

    public AbsButton getRefreshExpression() {
        return refreshExpression;
    }

    public AbsTextField getFinderExpClasses() {
        return findReplaceExpression.getFinderExpClasses();
    }

    public AbsButton getFindingExpression() {
        return findingExpression;
    }

    public AbsButton getFindingExpressionCancel() {
        return findingExpressionCancel;
    }

    public AbsButton getCloseExpression() {
        return closeExpression;
    }

    public AbsSplitPane getMainSplitter() {
        return mainSplitter;
    }

    public AbstractBaseExecutorService getTaskManager() {
        return taskManager;
    }

    public AbstractBaseExecutorService getTaskManagerExp() {
        return taskManagerExp;
    }

    public CustList<SegmentFindPart> getParts() {
        return parts;
    }

    public CustList<SegmentFindPart> getPartsExp() {
        return partsExp;
    }

    public StringList getTexts() {
        return texts;
    }

    public int getCurrentPart() {
        return currentPart;
    }

    public void setCurrentPart(int _c) {
        this.currentPart = _c;
    }

    public int getCurrentPartExp() {
        return currentPartExp;
    }

    public void setCurrentPartExp(int _c) {
        this.currentPartExp = _c;
    }

    public int getCurrentText() {
        return currentText;
    }

    public void setCurrentText(int _c) {
        this.currentText = _c;
    }

    public boolean isEnabledSyntax() {
        return enabledSyntax;
    }

    public void setEnabledSyntax(boolean _e) {
        this.enabledSyntax = _e;
    }

    public AbsEnabledAction getRedo() {
        return redo;
    }

    public AbsEnabledAction getUndo() {
        return undo;
    }

    public String getRelPath() {
        return relPath;
    }

    public void setRelPath(String _r) {
        this.relPath = _r;
    }

    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String _f) {
        this.fullPath = _f;
    }

    public WindowWithTreeImpl getWindowSecEditor() {
        return windowSecEditor;
    }

    public ExecConstructorOverrideInfo getTargetMethodView() {
        return findReplaceExpression.getTargetMethodView();
    }

    public ExecConstructorOverrideInfo getTargetMethodReplace() {
        return findReplaceExpression.getTargetMethodReplace();
    }

    public ResultContextViewReplacer getResultContext() {
        return findReplaceExpression.getResultContext();
    }

    public AbsButton getSelectExpressionClass() {
        return selectExpressionClass;
    }

    public AutoCompleteDocument getCompleteClasses() {
        return findReplaceExpression.getCompleteClasses();
    }

    public AbsPanel getExpSpli() {
        return expSpli;
    }

    public Struct getInstance() {
        return instance;
    }

    public void setInstance(Struct _i) {
        this.instance = _i;
    }

    public AbsTextPane getPreview() {
        return preview;
    }

    public AbsButton getApplyExp() {
        return applyExp;
    }

    public AbsButton getPrevOccExp() {
        return prevOccExp;
    }

    public AbsButton getNextOccExp() {
        return nextOccExp;
    }

    public ContextEl getAction() {
        return action;
    }

    public void setAction(ContextEl _act) {
        this.action = _act;
    }
}
