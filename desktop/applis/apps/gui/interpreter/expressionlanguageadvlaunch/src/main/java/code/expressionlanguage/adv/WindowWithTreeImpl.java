package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.files.CommentDelimiters;
import code.expressionlanguage.analyze.syntax.RowSrcLocation;
import code.expressionlanguage.analyze.syntax.SrcFileLocation;
import code.expressionlanguage.options.CommentsUtil;
import code.expressionlanguage.utilcompo.AbsResultContextNext;
import code.expressionlanguage.utilfiles.DefaultFileSystem;
import code.expressionlanguage.utilimpl.ManageOptions;
import code.gui.*;
import code.gui.events.AbsEnabledAction;
import code.gui.initialize.AbsCompoFactory;
import code.gui.initialize.AbstractProgramInfos;
import code.stream.*;
import code.stream.comparators.FileNameComparator;
import code.threads.AbstractBaseExecutorService;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.DefaultUniformingString;
import code.util.core.StringUtil;

public abstract class WindowWithTreeImpl extends AbsEditorTabList {
    private final CdmFactory factory;
    private final EnabledMenu languageMenu;
    private final EnabledMenu tabulationsMenu;
    private final EnabledMenu parameters;
    private final AbsTabbedPane events;
    private AbsTreeGui folderSystem;
    private AbsScrollPane scrollDialog;
    private AbstractMutableTreeNodeCore<String> selectedNode;
    private final CustList<OutputDialogComments> commentsFrames = new CustList<OutputDialogComments>();
    private final CustList<OutputDialogTab> tabulationsFrames = new CustList<OutputDialogTab>();
    private final CustList<OutputDialogLanguage> languageFrames = new CustList<OutputDialogLanguage>();
    private final CustList<OutputDialogAliases> aliasesFrames = new CustList<OutputDialogAliases>();
    private final CustList<OutputDialogSrc> srcFrames = new CustList<OutputDialogSrc>();
    private final EnabledMenu commentsMenu;
    private final EnabledMenu aliasesMenu;
    private final AbsPanel panel;
    private final AbsPanel panelSymbols;
    private final AbsTextArea analyzeState;
    private AbsPlainLabel lastCount;
//    private final AbsScrollPane panelSymbolsScroll;
    private final AbsScrollPane panelSymbolsDetailScroll;
    private final AbsScrollPane panelSymbolsLocationScroll;
    private final EnabledMenu srcMenu;
    private final EnabledMenu create;
    private final EnabledMenu delete;
    private final CustList<TabEditor> tabs = new CustList<TabEditor>();
    private AbsTabbedPane editors;
    private AbsEnabledAction refreshNode;
    private AbsEnabledAction renameNode;
    private AbsEnabledAction removeNode;
    private AbsEnabledAction createSystem;
    private ManageOptions manageOptions;
    private boolean editing;
    private AbsTextField targetName;
    private AbsButton validateDialog;
    private AbsButton cancelDialog;
    private int limitSymbol;
    private final AbstractBaseExecutorService finderSymbol;
    private final CustList<ResultRowSrcLocationList> symbols = new CustList<ResultRowSrcLocationList>();
    private AbsSplitPane projectPart;

    protected WindowWithTreeImpl(AbsResultContextNext _a, AbstractProgramInfos _list, CdmFactory _fact) {
        super(_a, _list);
        factory = _fact;
        StringMap<String> mes_ = MessagesIde.valFrames(_list.currentLg());
        finderSymbol = _list.getThreadFactory().newExecutorService();
        AbsMenuBar bar_ = _list.getCompoFactory().newMenuBar();
        EnabledMenu file_ = _list.getCompoFactory().newMenu(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_FRAMES_FILE)));
        bar_.add(file_);
        srcMenu = getFrames().getCompoFactory().newMenuItem(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_FRAMES_SRC)));
        srcMenu.addActionListener(new ChangeSrcEvent(this, srcMenu));
        create = getFrames().getCompoFactory().newMenuItem(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_FRAMES_CREATE)));
        create.addActionListener(new AddNewTreeFileNode(this));
        create.setAccelerator(GuiConstants.VK_N,GuiConstants.CTRL_DOWN_MASK);
        create.setEnabled(false);
        delete = getFrames().getCompoFactory().newMenuItem(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_FRAMES_DELETE)));
        delete.addActionListener(new RemoveTreeAction(this));
        delete.setAccelerator(GuiConstants.VK_DELETE,0);
        delete.setEnabled(false);
        parameters = _list.getCompoFactory().newMenu(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_FRAMES_PARAMETERS)));
        EnabledMenu menu_ = parameters;
        bar_.add(menu_);
        languageMenu = _list.getCompoFactory().newMenuItem(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_FRAMES_LANGUAGE)));
        languageMenu.addActionListener(new ChangeLanguageEvent(this,languageMenu));
        menu_.addMenuItem(languageMenu);
        tabulationsMenu = _list.getCompoFactory().newMenuItem(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_FRAMES_TABULATIONS)));
        tabulationsMenu.addActionListener(new ChangeTabulationsEvent(this, tabulationsMenu));
        menu_.addMenuItem(tabulationsMenu);
        commentsMenu = _list.getCompoFactory().newMenuItem(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_FRAMES_COMMENTS)));
        commentsMenu.addActionListener(new ChangeCommentsEvent(this, commentsMenu));
        menu_.addMenuItem(commentsMenu);
        aliasesMenu = _list.getCompoFactory().newMenuItem(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_FRAMES_ALIASES)));
        aliasesMenu.addActionListener(new ChangeAliasesEvent(this,aliasesMenu));
        menu_.addMenuItem(aliasesMenu);
        panel = _list.getCompoFactory().newPageBox();
        analyzeState = _list.getCompoFactory().newTextArea();
        lastCount = _list.getCompoFactory().newPlainLabel("0");
        panelSymbols = _list.getCompoFactory().newPageBox();
//        panelSymbolsScroll = _list.getCompoFactory().newAbsScrollPane(panelSymbols);
        panelSymbolsDetailScroll = _list.getCompoFactory().newAbsScrollPane();
        panelSymbolsLocationScroll = _list.getCompoFactory().newAbsScrollPane();
        events = _list.getCompoFactory().newAbsTabbedPane();
        events.add(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_FRAMES_FIND)), _list.getCompoFactory().newHorizontalSplitPane(_list.getCompoFactory().newAbsScrollPane(panelSymbols), _list.getCompoFactory().newHorizontalSplitPane(panelSymbolsDetailScroll, panelSymbolsLocationScroll)));
//        events.add(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_FRAMES_FIND)), _list.getCompoFactory().newHorizontalSplitPane(panelSymbolsScroll, _list.getCompoFactory().newHorizontalSplitPane(panelSymbolsDetailScroll, panelSymbolsLocationScroll)));
        events.add(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_FRAMES_EVENT)), _list.getCompoFactory().newAbsScrollPane(analyzeState));
        editors = getFrames().getCompoFactory().newAbsTabbedPane();
        getCommonFrame().setContentPane(panel);
        getCommonFrame().setJMenuBar(bar_);
        getCommonFrame().pack();
        getCommonFrame().setVisible(true);
        setLimitSymbol(16);
    }

    public boolean applyTreeChangeSelected(boolean _treeEvent) {
        AbstractProgramInfos frs_ = getFrames();
        AbstractMutableTreeNodeCore<String> sel_ = getTree().selectEvt();
        changeEnable(sel_);
        if (sel_ == null) {
            return false;
        }
        String str_ = GuiBaseUtil.buildPath(sel_);
        if (_treeEvent) {
            if (openFile(str_)) {
                return false;
            }
            if (!frs_.getFileCoreStream().newFile(str_).exists()) {
                closeIfOpened(str_);
            }
        }
        refresh(sel_,str_);
        return true;
    }

    boolean openFile(String _str) {
        AbstractProgramInfos frs_ = getFrames();
        BytesInfo content_ = StreamBinaryFile.loadFile(_str, frs_.getStreams());
        if (!content_.isNul()) {
            int opened_ = indexOpened(_str);
            if (opened_ > -1) {
                getEditors().selectIndex(opened_);
                return true;
            }
            notifyDoc(_str);
            addTab(this, _str,content_);
            getEditors().selectIndex(getTabs().getLastIndex());
            return true;
        }
        return false;
    }

    public void notifyDoc(String _path) {
        String rel_ = _path.substring(pathToSrc().length());
        openedFiles().add(rel_);
        updateDoc();
    }

    static void addTab(WindowWithTreeImpl _tr, String _path, BytesInfo _content) {
        String dec_ = StringUtil.nullToEmpty(StringUtil.decode(_content.getBytes()));
        String name_ = _path.substring(_path.lastIndexOf(SLASH_CH)+1);
        TabEditor te_ = new TabEditor(_tr,_path,_path.substring(_tr.pathToSrc().length()),lineSeparator(dec_));
        te_.centerText(new DefaultUniformingString().apply(dec_));
        _tr.getTabs().add(te_);
        _tr.getEditors().addIntTab(name_, te_.getPanel(), _path);
    }

    static String lineSeparator(String _content) {
        int len_ = _content.length();
        for (int i = 0; i < len_; i++) {
            char cur_ = _content.charAt(i);
            if (cur_ == AbsEditorTabList.CR_CHAR) {
                if (i + 1 < _content.length() && _content.charAt(i+1) == LINE_RETURN_CH) {
                    return AbsEditorTabList.CR_LF;
                }
                return AbsEditorTabList.CR;
            }
        }
        return AbsEditorTabList.LINE_RETURN;
    }

    @Override
    public int tabCount() {
        return getTabs().size();
    }

    @Override
    public AbsTabEditor tab(int _i) {
        return getTabs().get(_i);
    }

    static void refreshList(AbstractMutableTreeNodeCore<String> _sel, String _folderToVisit, AbstractProgramInfos _factories) {
        FileListInfo files_ = PathsUtil.abs(_factories.getFileCoreStream().newFile(_folderToVisit), _factories.getFileCoreStream());
        CustList<AbstractFile> currentFolders_ = new CustList<AbstractFile>();
        CustList<AbstractFile> currentFiles_ = new CustList<AbstractFile>();
        for (AbstractFile f : files_.getNames()) {
            if (DefaultFileSystem.dir(f)) {
                currentFolders_.add(f);
            }
        }
        currentFolders_.sortElts(new FileNameComparator());
        for (AbstractFile f : files_.getNames()) {
            if (DefaultFileSystem.file(f)) {
                currentFiles_.add(f);
            }
        }
        currentFiles_.sortElts(new FileNameComparator());
        for (AbstractFile f : currentFolders_) {
            _sel.add(_factories.getCompoFactory().newMutableTreeNode(f.getName()+SLASH));
        }
        for (AbstractFile f : currentFiles_) {
            _sel.add(_factories.getCompoFactory().newMutableTreeNode(f.getName()));
        }
    }

    void refresh(AbstractMutableTreeNodeCore<String> _sel, String _str) {
        AbstractProgramInfos frs_ = getFrames();
        AbstractMutableTreeNodeCore<String> r_ = getTree().getRoot();
        AbstractMutableTreeNodeCore<String> adj_ = _sel;
        String adjPath_ = _str;
        while (adj_ != r_) {
            String candidate_ = GuiBaseUtil.buildPath(adj_);
            AbstractFile file_ = frs_.getFileCoreStream().newFile(candidate_);
            if (file_.exists()) {
                adjPath_ = candidate_;
                if (adj_ != _sel) {
                    getTree().select(adj_);
                    return;
                }
                break;
            }
            closeIfOpened(candidate_);
            adj_ = adj_.getParent();
        }
        refParent(adj_, adjPath_);
    }

    void refParent(AbstractMutableTreeNodeCore<String> _parent, String _parentPath) {
        _parent.removeAllChildren();
        refreshList(_parent, _parentPath, getFrames());
        MutableTreeNodeUtil.reload(getTree());
    }

    void closeIfOpened(String _path) {
        int opened_ = indexOpened(_path);
        if (opened_ > -1) {
            getEditors().selectIndex(opened_);
            new CloseTabEditorEvent(getTabs().get(opened_)).action();
        }
    }

    public void initTree(String _acc) {
        AbstractProgramInfos frs_ = getFrames();
        AbstractMutableTreeNodeCore<String> default_ = frs_.getCompoFactory().newMutableTreeNode(_acc+SLASH);
        folderSystem = frs_.getCompoFactory().newTreeGui(default_);
        folderSystem.select(folderSystem.getRoot());
        refreshList(folderSystem.selectEvt(),_acc, getFrames());
        folderSystem.addTreeSelectionListener(new ShowSrcTreeEvent(this));
        refreshNode = frs_.getCompoFactory().wrap(new RefreshTreeAction(this));
        folderSystem.registerKeyboardAction(refreshNode, GuiConstants.VK_F5, GuiConstants.CTRL_DOWN_MASK);
        renameNode = frs_.getCompoFactory().wrap(new RenameTreeAction(this));
        folderSystem.registerKeyboardAction(renameNode, GuiConstants.VK_F6, GuiConstants.CTRL_DOWN_MASK);
        removeNode = frs_.getCompoFactory().wrap(new RemoveTreeAction(this));
        folderSystem.registerKeyboardAction(removeNode, GuiConstants.VK_DELETE, 0);
        createSystem = frs_.getCompoFactory().wrap(new AddNewTreeFileNode(this));
        folderSystem.registerKeyboardAction(createSystem,GuiConstants.VK_N,GuiConstants.CTRL_DOWN_MASK);
    }
    public void endTree() {
        AbstractProgramInfos frs_ = getFrames();
        setScrollDialog(frs_.getCompoFactory().newAbsScrollPane());
        getScrollDialog().setVisible(false);
        panelSymbols.removeAll();
        lastCount = frs_.getCompoFactory().newPlainLabel("0");
        panelSymbols.add(lastCount);
        projectPart = frs_.getCompoFactory().newVerticalSplitPane(frs_.getCompoFactory().newAbsScrollPane(folderSystem), getScrollDialog());
        AbsSplitPane elt_ = frs_.getCompoFactory().newVerticalSplitPane(frs_.getCompoFactory().newHorizontalSplitPane(projectPart, editors),
                events);
        panel.add(elt_);
    }

    public void changeEnable(boolean _en) {
        renameNode.setEnabled(_en);
        refreshNode.setEnabled(_en);
        removeNode.setEnabled(_en);
        createSystem.setEnabled(_en);
        create.setEnabled(_en);
        delete.setEnabled(_en);
    }
    void showFileOrFolder() {
        changeEnable(false);
        scrollDialog.setVisible(true);
        AbstractProgramInfos frs_ = getFrames();
        AbsCompoFactory c_ = frs_.getCompoFactory();
        AbsPanel panel_ = c_.newPageBox();
        targetName = c_.newTextField();
        panel_.add(targetName);
        StringMap<String> map_ = MessagesIde.valFiles(frs_.currentLg());
        validateDialog = c_.newPlainButton(StringUtil.nullToEmpty(map_.getVal(MessagesIde.IDE_FILES_CREATE_VALIDATE)));
        validateDialog.addActionListener(new ValidateFileFolderTree(this));
        panel_.add(validateDialog);
        cancelDialog = c_.newPlainButton(StringUtil.nullToEmpty(map_.getVal(MessagesIde.IDE_FILES_CREATE_CANCEL)));
        cancelDialog.addActionListener(new CloseTreeDialog(this));
        panel_.add(cancelDialog);
        scrollDialog.setViewportView(panel_);
//        GuiBaseUtil.recalculate(scrollDialog);
        pack();
        projectPart.setDividerLocation(projectPart.getHeight()-scrollDialog.getHeight());
    }

    public void fileOrFolder() {
        String str_ = GuiBaseUtil.buildPath(selectedNode);
        AbstractProgramInfos frs_ = getFrames();
        AbstractFile currentFolder_ = frs_.getFileCoreStream().newFile(str_);
        if (!currentFolder_.isDirectory()) {
            clearTreeDialog();
            return;
        }
        String elt_ = str_+targetName.getText();
        if (frs_.getFileCoreStream().newFile(elt_).exists()) {
            clearTreeDialog();
            return;
        }
        if (elt_.endsWith(SLASH)) {
            frs_.getFileCoreStream().newFile(elt_).mkdirs();
        } else {
            StreamFolderFile.makeParent(elt_,frs_.getFileCoreStream());
            StreamTextFile.saveTextFile(elt_,EMPTY_STRING,frs_.getStreams());
            notifyDoc(elt_);
            addTab(this,elt_,new BytesInfo(new byte[0],false));
            editors.selectIndex(tabs.getLastIndex());
        }
        applyTreeChangeSelected(false);
        clearTreeDialog();
    }

    public CdmParameterSoftModel softParams() {
        return getMainFrame().getSoftParams();
    }
    public void updateDoc() {
        updateDoc(getMainFrame());
    }

    static void updateDoc(WindowCdmEditor _m) {
        AbstractProgramInfos frs_ = _m.getFrames();
        String contentConf_ = _m.buildDefConfFile();
        StreamTextFile.saveTextFile(_m.getConfGlobal(), contentConf_, frs_.getStreams());
    }

    void showRenaming() {
        changeEnable(false);
        String str_ = GuiBaseUtil.buildPath(selectedNode);
        scrollDialog.setVisible(true);
        AbstractProgramInfos frs_ = getFrames();
        AbsCompoFactory c_ = frs_.getCompoFactory();
        AbsPanel panel_ = c_.newPageBox();
        targetName = c_.newTextField();
        panel_.add(c_.newPlainLabel(str_));
        panel_.add(targetName);
        StringMap<String> map_ = MessagesIde.valFiles(frs_.currentLg());
        validateDialog = c_.newPlainButton(StringUtil.nullToEmpty(map_.getVal(MessagesIde.IDE_FILES_RENAMING_VALIDATE)));
        validateDialog.addActionListener(new ValidateRenameTree(this));
        panel_.add(validateDialog);
        cancelDialog = c_.newPlainButton(StringUtil.nullToEmpty(map_.getVal(MessagesIde.IDE_FILES_RENAMING_CANCEL)));
        cancelDialog.addActionListener(new CloseTreeDialog(this));
        panel_.add(cancelDialog);
        scrollDialog.setViewportView(panel_);
//        GuiBaseUtil.recalculateWindow(getCommonFrame());
        getCommonFrame().pack();
    }

    void renameValidate() {
        AbstractProgramInfos frs_ = getFrames();
        String str_ = GuiBaseUtil.buildPath(selectedNode);
        AbstractMutableTreeNodeCore<String> par_ = selectedNode.getParent();
        String dest_ = GuiBaseUtil.buildPath(par_)+targetName.getText();
        if (!frs_.getFileCoreStream().newFile(str_).renameTo(frs_.getFileCoreStream().newFile(dest_))){
            clearTreeDialog();
            return;
        }
        int opened_ = indexOpened(str_);
        String parentPath_ = dest_.substring(0,dest_.lastIndexOf(SLASH_CH)+1);
        String name_ = dest_.substring(dest_.lastIndexOf(SLASH_CH)+1);
        if (opened_ > -1) {
            getEditors().setTitle(opened_,name_);
            getEditors().setToolTipAt(opened_,dest_);
            getTabs().get(opened_).setFullPath(dest_);
            getTabs().get(opened_).setRelPath(dest_.substring(pathToSrc().length()));
        }
        par_.remove(selectedNode);
        refParent(par_,parentPath_);
        for (AbstractMutableTreeNodeCore<String> c: par_.children()) {
            if (StringUtil.quickEq(name_,c.info())) {
                folderSystem.select(c);
            }
        }
        clearTreeDialog();
    }

    void showRemoving() {
        changeEnable(false);
        String str_ = GuiBaseUtil.buildPath(selectedNode);
        scrollDialog.setVisible(true);
        AbstractProgramInfos frs_ = getFrames();
        AbsCompoFactory c_ = frs_.getCompoFactory();
        AbsPanel panel_ = c_.newPageBox();
        panel_.add(c_.newPlainLabel(str_));
        StringMap<String> map_ = MessagesIde.valFiles(frs_.currentLg());
        validateDialog = c_.newPlainButton(StringUtil.nullToEmpty(map_.getVal(MessagesIde.IDE_FILES_REMOVING_VALIDATE)));
        validateDialog.addActionListener(new ValidateRemoveTree(this));
        panel_.add(validateDialog);
        cancelDialog = c_.newPlainButton(StringUtil.nullToEmpty(map_.getVal(MessagesIde.IDE_FILES_REMOVING_CANCEL)));
        cancelDialog.addActionListener(new CloseTreeDialog(this));
        panel_.add(cancelDialog);
        scrollDialog.setViewportView(panel_);
//        GuiBaseUtil.recalculateWindow(getCommonFrame());
        getCommonFrame().pack();
    }
    void removeValidate() {
        AbstractProgramInfos frs_ = getFrames();
        String str_ = GuiBaseUtil.buildPath(selectedNode);
        AbstractMutableTreeNodeCore<String> par_ = selectedNode.getParent();
        if (!frs_.getFileCoreStream().newFile(str_).delete()){
            clearTreeDialog();
            return;
        }
        int opened_ = indexOpened(str_);
        if (opened_ > -1) {
            getEditors().remove(opened_);
            getTabs().remove(opened_);
        }
        par_.remove(selectedNode);
        folderSystem.select(par_);
        clearTreeDialog();
    }

    void clearTreeDialog() {
        setSelectedNode(null);
        scrollDialog.setVisible(false);
//        GuiBaseUtil.recalculateWindow(getCommonFrame());
        getCommonFrame().pack();
        changeEnable(getTree().selectEvt());
    }

    public void afterChangingSyntaxPreferences() {
        saveConf();
        updateCurrentTab();
    }

    public void updateCurrentTab() {
        int ind_ = editors.getSelectedIndex();
        if (!tabs.isValidIndex(ind_)) {
            return;
        }
        stateChangeTab();
        DocumentTextChange.updateEditorText(tabs.get(ind_));
        FindExpressionTask.updatedSegColorsBase(tabs.get(ind_));
    }

    private void stateChangeTab() {
        new TabValueChanged(this).stateChanged();
    }

    public void updateComments(CustList<CommentDelimiters> _comm) {
        CommentsUtil.checkAndUpdateComments(_comm, manageOptions.getOptions().getComments());
        for (CommentDelimiters c: _comm) {
            if (c.getEnd().get(0).trim().isEmpty()) {
                c.getEnd().clear();
                c.getEnd().add(AbsEditorTabList.LINE_RETURN);
            }
        }
        manageOptions.getOptions().getComments().clear();
        manageOptions.getOptions().getComments().addAllElts(_comm);
    }

    protected void closeSecs() {
        for (OutputDialogLanguage w: getLanguageFrames()) {
            w.getFrame().setVisible(false);
            w.getMenu().setEnabled(true);
        }
        for (OutputDialogTab w: getTabulationsFrames()) {
            w.getFrame().setVisible(false);
            w.getMenu().setEnabled(isEditing());
        }
        for (OutputDialogAliases w: getAliasesFrames()) {
            w.getFrame().setVisible(false);
            w.getMenu().setEnabled(isEditing());
        }
        for (OutputDialogSrc w: getSrcFrames()) {
            w.getFrame().setVisible(false);
            w.getMenu().setEnabled(isEditing());
        }
        for (OutputDialogComments w: getCommentsFrames()) {
            w.getFrame().setVisible(false);
            w.getMenu().setEnabled(isEditing());
        }
    }
    public void chgManagement(boolean _en) {
        editing = _en;
        srcMenu.setEnabled(_en);
        tabulationsMenu.setEnabled(_en);
        commentsMenu.setEnabled(_en);
        aliasesMenu.setEnabled(_en);
    }

    public void afterSearchSymbol(AnalyzedPageEl _page, String _relPath, int _caret, CustList<SrcFileLocation> _us, CustList<RowSrcLocation> _ls) {
        if (_ls.isEmpty()) {
            lastCount.setText(Long.toString(_ls.size()));
            return;
        }
        ResultRowSrcLocationList r_ = new ResultRowSrcLocationList(_page, _relPath, _caret, _us, _ls);
        if (symbols.size() < getLimitSymbol()) {
            symbols.add(r_);
        } else {
            symbols.remove(0);
            symbols.add(r_);
        }
        panelSymbols.removeAll();
        lastCount.setText(Long.toString(_ls.size()));
        panelSymbols.add(lastCount);
        AbsCompoFactory fr_ = getFrames().getCompoFactory();
        for (ResultRowSrcLocationList f: symbols) {
            AbsPanel c_ = fr_.newLineBox();
            updatePanel(c_,f);
            panelSymbols.add(c_);
        }
        update(r_);
    }
    public void updatePanel(AbsPanel _p,ResultRowSrcLocationList _r) {
        AbsCompoFactory fr_ = getFrames().getCompoFactory();
        _p.left();
        for (RowSrcLocation l: _r.getSymbols()) {
            AbsButton b_ = fr_.newPlainButton(l.getKind() + ":" + l.getDisplay());
            b_.addActionListener(new GoToDefinitionEvent(_r.getPage(),l,this));
            b_.left();
            _p.add(b_);
        }
        StringMap<String> mes_ = MessagesIde.valFindRef(getFrames().currentLg());
        AbsButton b_ = fr_.newPlainButton(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_FIND_REF_CALLERS)));
        b_.addActionListener(new CallersHierarchyEvent(_r,this));
        b_.left();
        _p.add(b_);
        AbsButton usagesRef_ = fr_.newPlainButton(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_FIND_REF_USAGE_ONLY)));
        usagesRef_.addActionListener(new RefreshLocationEvent(_p, this, _r));
        usagesRef_.left();
        _p.add(usagesRef_);
        AbsButton usagesDefRef_ = fr_.newPlainButton(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_FIND_REF_USAGE_AND_DEF)));
        usagesDefRef_.addActionListener(new RefreshLocationTabEvent(_p, this, _r));
        usagesDefRef_.left();
        _p.add(usagesDefRef_);
    }
    public void update(ResultRowSrcLocationList _r) {
//        GuiBaseUtil.recalculate(panelSymbolsScroll);
        pack();
        getEvents().selectIndex(0);
        _r.buildTree(this);
        LookForCallersTask.updateCallersView(this,_r);
    }

    protected ManageOptions manage(StringList _linesFiles) {
        return new ManageOptions(getFrames().getLanguages(), _linesFiles, factory);
    }

    public AbsTreeGui getFolderSystem() {
        return folderSystem;
    }
    public EnabledMenu getCreate() {
        return create;
    }

    public EnabledMenu getDelete() {
        return delete;
    }

    public EnabledMenu getLanguageMenu() {
        return languageMenu;
    }

    public EnabledMenu getTabulationsMenu() {
        return tabulationsMenu;
    }

    public EnabledMenu getCommentsMenu() {
        return commentsMenu;
    }

    public EnabledMenu getAliasesMenu() {
        return aliasesMenu;
    }

    public CustList<OutputDialogLanguage> getLanguageFrames() {
        return languageFrames;
    }

    public CustList<OutputDialogTab> getTabulationsFrames() {
        return tabulationsFrames;
    }

    public CustList<OutputDialogComments> getCommentsFrames() {
        return commentsFrames;
    }

    public CustList<OutputDialogAliases> getAliasesFrames() {
        return aliasesFrames;
    }

    public CustList<OutputDialogSrc> getSrcFrames() {
        return srcFrames;
    }

    public CdmFactory getFactory() {
        return factory;
    }

    public AbstractMutableTreeNodeCore<String> getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(AbstractMutableTreeNodeCore<String> _s) {
        this.selectedNode = _s;
    }

    public AbsButton getValidateDialog() {
        return validateDialog;
    }

    public AbsButton getCancelDialog() {
        return cancelDialog;
    }

    public AbsTextField getTargetName() {
        return targetName;
    }

    public AbsScrollPane getScrollDialog() {
        return scrollDialog;
    }

    public void setScrollDialog(AbsScrollPane _s) {
        this.scrollDialog = _s;
    }

    public boolean isEditing() {
        return editing;
    }

    public EnabledMenu getParameters() {
        return parameters;
    }

    public CustList<TabEditor> getTabs() {
        return tabs;
    }

    public AbsTabbedPane getEditors() {
        return editors;
    }

    public void setEditors(AbsTabbedPane _e) {
        this.editors = _e;
    }

    public ManageOptions getManageOptions() {
        return manageOptions;
    }

    public void setManageOptions(ManageOptions _m) {
        this.manageOptions = _m;
    }
    public CustList<CommentDelimiters> getComments() {
        return manageOptions.getOptions().getComments();
    }

    public StringMap<String> getLgMessages() {
        return manageOptions.getEx().getMessages();
    }

    public void setLgMessages(StringMap<String> _l) {
        this.manageOptions.getEx().setMessages(_l);
    }

    public StringMap<String> getLgAliases() {
        return manageOptions.getEx().getAliases();
    }

    public void setLgAliases(StringMap<String> _l) {
        this.manageOptions.getEx().setAliases(_l);
    }

    public StringMap<String> getLgKeyWords() {
        return manageOptions.getEx().getKeyWords();
    }

    public void setLgKeyWords(StringMap<String> _l) {
        this.manageOptions.getEx().setKeyWords(_l);
    }

    public void setComments(CustList<CommentDelimiters> _c) {
        manageOptions.getOptions().getComments().clear();
        manageOptions.getOptions().getComments().addAllElts(_c);
    }

    public EnabledMenu getSrcMenu() {
        return srcMenu;
    }

    public int getTabWidth() {
        return manageOptions.getOptions().getTabWidth();
    }

    public void setTabWidth(int _t) {
        this.manageOptions.getOptions().setTabWidth(_t);
    }

    public void setUsedLg(String _u) {
        this.manageOptions.getEx().setLg(_u);
    }

    public String getUsedLg() {
        return manageOptions.getEx().getLg();
    }

    public String getCurrentFolder() {
        return manageOptions.getEx().getAccess();
    }
    public AbsPanel getPanel() {
        return panel;
    }

    public AbsTabbedPane getEvents() {
        return events;
    }

    public AbsTextArea getAnalyzeState() {
        return analyzeState;
    }

    public AbsPanel getPanelSymbols() {
        return panelSymbols;
    }

    public AbsScrollPane getPanelSymbolsDetailScroll() {
        return panelSymbolsDetailScroll;
    }

    public AbsScrollPane getPanelSymbolsLocationScroll() {
        return panelSymbolsLocationScroll;
    }

    public int getLimitSymbol() {
        return limitSymbol;
    }

    public void setLimitSymbol(int _l) {
        this.limitSymbol = _l;
    }

    public CustList<ResultRowSrcLocationList> getSymbols() {
        return symbols;
    }

    public AbstractBaseExecutorService getFinderSymbol() {
        return finderSymbol;
    }

    public abstract AbsTreeGui getTree();
    public abstract void changeEnable(AbstractMutableTreeNodeCore<String> _en);
    public abstract String pathToSrc();

    public abstract WindowCdmEditor getMainFrame();

    public abstract StringList openedFiles();
    public abstract void saveConf();
}
