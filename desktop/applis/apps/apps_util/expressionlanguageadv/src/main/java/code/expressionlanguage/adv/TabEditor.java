package code.expressionlanguage.adv;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.exec.ExecClassesUtil;
import code.expressionlanguage.exec.blocks.ExecClassBlock;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.util.ExecOverrideInfo;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.fwd.blocks.ForwardInfos;
import code.expressionlanguage.guicompos.LgNamesGui;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.structs.ClassMetaInfo;
import code.expressionlanguage.structs.ConstructorMetaInfo;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.*;
import code.expressionlanguage.utilimpl.CustContextFactory;
import code.gui.*;
import code.gui.events.AbsEnabledAction;
import code.gui.images.MetaDimension;
import code.gui.images.MetaFont;
import code.gui.initialize.AbstractProgramInfos;
import code.threads.AbstractBaseExecutorService;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;

public final class TabEditor {
    private final WindowWithTreeImpl windowSecEditor;
    private final AbstractProgramInfos factories;
    private final AbsTextPane center;
    private final AbsTextField finder;
    private final AbsCustCheckBox caseSens;
    private final AbsCustCheckBox wholeWord;
    private final AbsTextField replacer;
    private final AbsPlainButton prevOcc;
    private final AbsPlainButton nextOcc;
    private final AbsPlainButton closeFinder;
    private final AbsPlainButton replaceOne;
    private final AbsPlainButton replaceAll;
    private final AbsPlainButton replacePrevious;
    private final AbsPlainButton replaceNext;
    private final AbsPlainButton replaceOneExp;
    private final AbsPlainButton replaceAllExp;
    private final AbsPlainButton replacePreviousExp;
    private final AbsPlainButton replaceNextExp;
    private final AbsPlainButton applyExp;
    private final AbsTextPane preview;
    private final AbsPanel navModifPanel;
    private final AbsPanel finderPanel;
    private final AbsPanel replacerPanel;
    private final AbsPlainButton refreshExpression;
    private final AbsPlainLabel lastBuild;
    private final AbsTextField finderExpClasses;
    private final AbsPlainButton selectExpressionClass;
    private final AbsPlainButton findingExpression;
    private final AbsPlainButton findingExpressionCancel;
    private final AbsPlainButton closeExpression;
    private final AbsPlainButton prevOccExp;
    private final AbsPlainButton nextOccExp;
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
    private final AutoCompleteDocument completeClasses;
    private final AbsPanel expSpli;
    private final AbsSplitPane mainSplitter;
    private boolean enabledSyntax = true;
    private int currentPart = -1;
    private int currentPartExp = -1;
    private int currentText = -1;
    private String fullPath;
    private final String useFeed;
    private int index=-1;
    private int dest;
    private final AbsSpinner row;
    private final AbsSpinner col;
    private final AbsPlainButton val;
    private final ResultContextViewReplacer resultContext = new ResultContextViewReplacer();
    private ExecConstructorOverrideInfo targetMethodView;
    private ExecConstructorOverrideInfo targetMethodReplace;
    private RunnableContextEl action;
    private Struct instance = NullStruct.NULL_VALUE;
    private final StringMap<ExecConstructorOverrideInfo> dico = new StringMap<ExecConstructorOverrideInfo>();
    private final StringMap<ExecConstructorOverrideInfo> dicoRepl = new StringMap<ExecConstructorOverrideInfo>();

    public TabEditor(WindowWithTreeImpl _editor, String _fullPath, String _lr) {
        useFeed = _lr;
        fullPath = _fullPath;
        windowSecEditor = _editor;
        AbstractProgramInfos frames_ = _editor.getCommonFrame().getFrames();
        taskManager = frames_.getThreadFactory().newExecutorService();
        taskManagerExp = frames_.getThreadFactory().newExecutorService();
        factories = frames_;
        label = frames_.getCompoFactory().newPlainLabel(":");
        labelOcc = frames_.getCompoFactory().newPlainLabel("/");
        labelOccExp = frames_.getCompoFactory().newPlainLabel("/");
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
        finderExpClasses = frames_.getCompoFactory().newTextField();
        completeClasses = new AutoCompleteDocument(finderExpClasses, new StringList(), frames_,new FeedExpressionClassValue());
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
        finderClass_.add(finderExpClasses);
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
        panel = frames_.getCompoFactory().newPageBox();
        AbsPanel upp_ = frames_.getCompoFactory().newPageBox();
        AbsScrollPane scCenter_ = frames_.getCompoFactory().newAbsScrollPane(center);
        scCenter_.setPreferredSize(new MetaDimension(512,512));
        upp_.add(scCenter_);
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
        center.requestFocus();
        center.select(dest, dest);
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

    public AbsPlainButton getVal() {
        return val;
    }

    public boolean enabled(int _row, int _col) {
        return index(_row, _col) > -1;
    }
    public int index(int _row, int _col) {
        int adjRow_ = _row - 1;
        int adjCol_ = _col - 1;
        int tw_ = windowSecEditor.getTabWidth();
        String txt_ = getCenter().getText();
        int index_ = 0;
        int row_ = 0;
        while (index_ >= 0) {
            int next_ = txt_.indexOf('\n',index_);
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
            if (ch_ == '\n') {
                return -1;
            }
            if (ch_ == '\t') {
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
            getCenter().select(s_.getBegin(),s_.getEnd());
        }
        updateNav();
    }

    public void updateNavSelectExp() {
        if (getPartsExp().isValidIndex(getCurrentPartExp())) {
            SegmentFindPart s_ = getPartsExp().get(getCurrentPartExp());
            getPreview().select(s_.getBegin(),s_.getEnd());
        }
        updateNavExp();
    }

    public void updateNav() {
        int n_ = getCurrentPart();
        getLabelOcc().setText((n_+1)+"/"+getParts().size());
        prevOcc.setEnabled(!getParts().isEmpty());
        nextOcc.setEnabled(!getParts().isEmpty());
    }
    public void updateNavExp() {
        int n_ = getCurrentPartExp();
        labelOccExp.setText((n_+1)+"/"+getPartsExp().size());
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
        finderExpClasses.setEnabled(false);
        findingExpressionCancel.setEnabled(false);
        applyExp.setEnabled(false);
        prevOccExp.setEnabled(false);
        nextOccExp.setEnabled(false);
        ResultContext userResult_ = windowSecEditor.getMainFrame().getUserResult();
        if (userResult_ == null) {
            lastBuild.setText(CustAliases.YYYY_MM_DD_HH_MM_SS_SSS_DASH);
            selectExpressionClass.setEnabled(false);
            completeClasses.setDictionary(new StringList());
            dico.clear();
            dicoRepl.clear();
            return;
        }
        ResultContext base_ = windowSecEditor.getMainFrame().getBaseResult();
        LgNamesGui lg_ = (LgNamesGui) base_.getForwards().getGenerator();
        FileInfos fileInfos_ = lg_.getExecContent().getInfos();
        ExecutingOptions ex_ = new ExecutingOptions(windowSecEditor.getMainFrame().getCommonFrame().getFrames().getThreadFactory().newAtomicBoolean());
        ex_.setLightProgramInfos(lg_.getExecContent().getExecutingOptions().getLightProgramInfos());
        ex_.setListGenerator(lg_.getExecContent().getExecutingOptions().getListGenerator());
        ex_.setFileSystemParameterizing(lg_.getExecContent().getExecutingOptions().getFileSystemParameterizing());
        ex_.setAliases(lg_.getExecContent().getExecutingOptions().getAliases());
        ex_.setLg(lg_.getExecContent().getExecutingOptions().getLg());
        ex_.setCovering(lg_.getExecContent().getExecutingOptions().isCovering());
        ex_.setCoverFolder(lg_.getExecContent().getExecutingOptions().getCoverFolder());
        ex_.setOutputFolder(lg_.getExecContent().getExecutingOptions().getOutputFolder());
        ex_.setOutput(lg_.getExecContent().getExecutingOptions().getOutput());
        ex_.setOutputZip(lg_.getExecContent().getExecutingOptions().getOutputZip());
        ex_.setMainThread(lg_.getExecContent().getExecutingOptions().getMainThread());
        ex_.setFiles(lg_.getExecContent().getExecutingOptions().getFiles());
        ex_.setLogFolder(lg_.getExecContent().getExecutingOptions().getLogFolder());
        ex_.setBaseFiles(lg_.getExecContent().getExecutingOptions().getBaseFiles());
        ex_.getLgs().addAllElts(lg_.getExecContent().getExecutingOptions().getLgs());
        LgNamesGui lgCopy_ = new LgNamesGui(fileInfos_,windowSecEditor.getMainFrame().getFactory().getInterceptor());
        lgCopy_.getContent().getStandards().addAllEntries(lg_.getContent().getStandards());
        lgCopy_.getContent().getPrimTypes().getPrimitiveTypes().addAllEntries(lg_.getContent().getPrimTypes().getPrimitiveTypes());
        lgCopy_.getExecContent().updateTranslations(ex_.getLightProgramInfos().getTranslations(),ex_.getLightProgramInfos().getLanguage(),ex_.getLg());
        CustContextFactory.parts(ex_,lgCopy_,new StringList());
        CustContextFactory.aliases(ex_,lgCopy_.getContent(), lgCopy_.getExecContent().getCustAliases(), lgCopy_.getGuiAliases());
        Forwards forwards_ = CustContextFactory.fwd(userResult_.getForwards().getOptions(), lgCopy_, userResult_.getForwards().getFileBuilder());
        ResultContext copy_ = new ResultContext(userResult_.getPageEl(),forwards_,userResult_.getReportedMessages());
        AnalyzedPageEl fwd_ = copy_.getPageEl();
        Forwards f_ = copy_.getForwards();
        ForwardInfos.generalForward(fwd_,f_);
        ContextEl ctx_ = f_.generate();
        RunnableContextEl res_ = (RunnableContextEl) ctx_;
        setAction(res_);
        getFindingExpressionCancel().setEnabled(true);
        Classes.forwardAndClear(ctx_);
        Options options_ = f_.getOptions();
        RunnableStruct.setupThread(res_);
        ExecClassesUtil.tryInitStaticlyTypes(ctx_, options_);
        AbstractProgramInfos frames_ = windowSecEditor.getMainFrame().getCommonFrame().getFrames();
        ResultContextViewReplacer vr_ = getResultContext();
        lastBuild.setText(vr_.update(lgCopy_.getExecContent().getCustAliases(), lgCopy_.getContent(),getAction(),frames_));
        selectExpressionClass.setEnabled(true);
        ExecRootBlock typeView_ = vr_.getViewType();
        ExecNamedFunctionBlock methodView_ = vr_.getViewMethod();
        ExecRootBlock typeRepl_ = vr_.getReplaceType();
        ExecNamedFunctionBlock methodRepl_ = vr_.getReplaceMethod();
        dico.clear();
        dicoRepl.clear();
        StringList dict_ = new StringList();
        for (EntryCust<String, ExecRootBlock> e: res_.getClasses().getClassesBodies().entryList()) {
            ExecRootBlock type_ = e.getValue();
            String name_ = e.getKey();
            ExecConstructorOverrideInfo ov_ = isValid(name_, type_, res_, typeView_, methodView_);
            if (ov_ != null) {
                dict_.add(name_);
                dico.addEntry(name_,ov_);
            }
            ExecConstructorOverrideInfo ovRep_ = isValid(name_, type_, res_, typeRepl_, methodRepl_);
            if (ovRep_ != null) {
                dicoRepl.addEntry(name_,ovRep_);
            }
        }
        completeClasses.setDictionary(dict_);
        finderExpClasses.setEnabled(true);
    }
    private static ExecConstructorOverrideInfo isValid(String _k, ExecRootBlock _type, ContextEl _ctx, ExecRootBlock _look, ExecNamedFunctionBlock _method) {
        if (!(_type instanceof ExecClassBlock) || ((ExecClassBlock)_type).isAbstractType() || !_type.withoutInstance()) {
            return null;
        }
        ExecOverrideInfo o_ = _look.getRedirections().getVal(_method, _k);
        if (o_ == null) {
            return null;
        }
        for (ConstructorMetaInfo c: new ClassMetaInfo(new ExecFormattedRootBlock(_type), _ctx).getConstructorsInfos()) {
            if (c.getFid().getParametersTypesLength() == 0) {
                return new ExecConstructorOverrideInfo(c,o_);
            }
        }
        return null;
    }

    public void usedType(String _u) {
        usedTypeReplace(_u);
        targetMethodView = dico.getVal(_u);
    }

    public StringMap<ExecConstructorOverrideInfo> getDico() {
        return dico;
    }

    public StringMap<ExecConstructorOverrideInfo> getDicoRepl() {
        return dicoRepl;
    }

    public void usedTypeReplace(String _u) {
        targetMethodReplace = dicoRepl.getVal(_u);
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

    public AbsPlainButton getCloseFinder() {
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

    public AbsPlainButton getReplaceOne() {
        return replaceOne;
    }

    public AbsPlainButton getReplaceAll() {
        return replaceAll;
    }

    public AbsPlainButton getReplacePrevious() {
        return replacePrevious;
    }

    public AbsPlainButton getReplaceNext() {
        return replaceNext;
    }

    public AbsPlainButton getReplaceOneExp() {
        return replaceOneExp;
    }

    public AbsPlainButton getReplaceAllExp() {
        return replaceAllExp;
    }

    public AbsPlainButton getReplacePreviousExp() {
        return replacePreviousExp;
    }

    public AbsPlainButton getReplaceNextExp() {
        return replaceNextExp;
    }

    public AbsTextField getReplacer() {
        return replacer;
    }

    public AbsTextPane getCenter() {
        return center;
    }

    public AbsPanel getPanel() {
        return panel;
    }

    public AbsPlainButton getPrevOcc() {
        return prevOcc;
    }

    public AbsPlainButton getNextOcc() {
        return nextOcc;
    }

    public AbsPlainButton getRefreshExpression() {
        return refreshExpression;
    }

    public AbsTextField getFinderExpClasses() {
        return finderExpClasses;
    }

    public AbsPlainButton getFindingExpression() {
        return findingExpression;
    }

    public AbsPlainButton getFindingExpressionCancel() {
        return findingExpressionCancel;
    }

    public AbsPlainButton getCloseExpression() {
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
        return targetMethodView;
    }

    public ExecConstructorOverrideInfo getTargetMethodReplace() {
        return targetMethodReplace;
    }

    public ResultContextViewReplacer getResultContext() {
        return resultContext;
    }

    public AbsPlainButton getSelectExpressionClass() {
        return selectExpressionClass;
    }

    public AutoCompleteDocument getCompleteClasses() {
        return completeClasses;
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

    public AbsPlainButton getApplyExp() {
        return applyExp;
    }

    public AbsPlainButton getPrevOccExp() {
        return prevOccExp;
    }

    public AbsPlainButton getNextOccExp() {
        return nextOccExp;
    }

    public RunnableContextEl getAction() {
        return action;
    }

    public void setAction(RunnableContextEl _act) {
        this.action = _act;
    }
}
