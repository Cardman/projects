package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.files.CommentDelimiters;
import code.expressionlanguage.options.CommentsUtil;
import code.expressionlanguage.utilfiles.DefaultFileSystem;
import code.expressionlanguage.utilimpl.ManageOptions;
import code.gui.*;
import code.gui.events.AbsEnabledAction;
import code.gui.initialize.AbsCompoFactory;
import code.gui.initialize.AbstractProgramInfos;
import code.stream.*;
import code.stream.comparators.FileNameComparator;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.DefaultUniformingString;
import code.util.core.StringUtil;

public abstract class WindowWithTreeImpl {
    private final CdmFactory factory;
    private final AbsMenuItem languageMenu;
    private final AbsMenuItem tabulationsMenu;
    private final AbsMenu parameters;
    private AbsTreeGui folderSystem;
    private AbsScrollPane scrollDialog;
    private AbstractMutableTreeNode selectedNode;
    private final CustList<OutputDialogComments> commentsFrames = new CustList<OutputDialogComments>();
    private final CustList<OutputDialogTab> tabulationsFrames = new CustList<OutputDialogTab>();
    private final CustList<OutputDialogLanguage> languageFrames = new CustList<OutputDialogLanguage>();
    private final CustList<OutputDialogAliases> aliasesFrames = new CustList<OutputDialogAliases>();
    private final CustList<OutputDialogSrc> srcFrames = new CustList<OutputDialogSrc>();
    private final AbsMenuItem commentsMenu;
    private final AbsMenuItem aliasesMenu;
    private final AbsCommonFrame commonFrame;
    private final AbsPanel panel;
    private final AbsMenuItem srcMenu;
    private final AbsMenuItem create;
    private final AbsMenuItem delete;
    private final CustList<TabEditor> tabs = new CustList<TabEditor>();
    private AbsTabbedPane editors;
    private AbsEnabledAction refreshNode;
    private AbsEnabledAction renameNode;
    private AbsEnabledAction removeNode;
    private AbsEnabledAction createSystem;
    private ManageOptions manageOptions;
    private boolean editing;
    private AbsTextField targetName;
    private AbsPlainButton validateDialog;
    private AbsPlainButton cancelDialog;
    protected WindowWithTreeImpl(String _lg, AbstractProgramInfos _list, CdmFactory _fact) {
        factory = _fact;
        commonFrame = _list.getFrameFactory().newCommonFrame(_lg, _list, null);
        AbsMenuBar bar_ = _list.getCompoFactory().newMenuBar();
        AbsMenu file_ = _list.getCompoFactory().newMenu("file");
        bar_.add(file_);
        srcMenu = commonFrame.getFrames().getCompoFactory().newMenuItem("inputs output conf");
        srcMenu.addActionListener(new ChangeSrcEvent(this, srcMenu));
        create = commonFrame.getFrames().getCompoFactory().newMenuItem("new");
        create.addActionListener(new AddNewTreeFileNode(this));
        create.setAccelerator(GuiConstants.VK_N,GuiConstants.CTRL_DOWN_MASK);
        create.setEnabled(false);
        delete = commonFrame.getFrames().getCompoFactory().newMenuItem("delete");
        delete.addActionListener(new RemoveTreeAction(this));
        delete.setAccelerator(GuiConstants.VK_DELETE,0);
        delete.setEnabled(false);
        parameters = _list.getCompoFactory().newMenu("boss");
        AbsMenu menu_ = parameters;
        bar_.add(menu_);
        languageMenu = _list.getCompoFactory().newMenuItem("language");
        languageMenu.addActionListener(new ChangeLanguageEvent(this,languageMenu));
        menu_.addMenuItem(languageMenu);
        tabulationsMenu = _list.getCompoFactory().newMenuItem("tabulations");
        tabulationsMenu.addActionListener(new ChangeTabulationsEvent(this, tabulationsMenu));
        menu_.addMenuItem(tabulationsMenu);
        commentsMenu = _list.getCompoFactory().newMenuItem("comments");
        commentsMenu.addActionListener(new ChangeCommentsEvent(this, commentsMenu));
        menu_.addMenuItem(commentsMenu);
        aliasesMenu = _list.getCompoFactory().newMenuItem("aliases");
        aliasesMenu.addActionListener(new ChangeAliasesEvent(this,aliasesMenu));
        menu_.addMenuItem(aliasesMenu);
        panel = _list.getCompoFactory().newPageBox();
        editors = commonFrame.getFrames().getCompoFactory().newAbsTabbedPane();
        commonFrame.setContentPane(panel);
        commonFrame.setJMenuBar(bar_);
        commonFrame.pack();
        commonFrame.setVisible(true);
    }

    public boolean applyTreeChangeSelected(boolean _treeEvent) {
        AbstractProgramInfos frs_ = getCommonFrame().getFrames();
        AbstractMutableTreeNode sel_ = getTree().selectEvt();
        changeEnable(sel_);
        if (sel_ == null) {
            return false;
        }
        String str_ = buildPath(sel_);
        if (_treeEvent) {
            BytesInfo content_ = StreamBinaryFile.loadFile(str_, frs_.getStreams());
            if (!content_.isNul()) {
                int opened_ = indexOpened(str_);
                if (opened_ > -1) {
                    getEditors().selectIndex(opened_);
                    return false;
                }
                notifyDoc(str_);
                addTab(this,str_,content_);
                getEditors().selectIndex(getTabs().getLastIndex());
                return false;
            }
            if (!frs_.getFileCoreStream().newFile(str_).exists()) {
                closeIfOpened(str_);
            }
        }
        refresh(sel_,str_);
        return true;
    }

    public void notifyDoc(String _path) {
        String rel_ = _path.substring(pathToSrc().length());
        openedFiles().add(rel_);
        updateDoc();
    }

    static void addTab(WindowWithTreeImpl _tr, String _path, BytesInfo _content) {
        String dec_ = StringUtil.nullToEmpty(StringUtil.decode(_content.getBytes()));
        String name_ = _path.substring(_path.lastIndexOf('/')+1);
        TabEditor te_ = new TabEditor(_tr,_path,lineSeparator(dec_));
        te_.getCenter().setText(new DefaultUniformingString().apply(dec_));
        _tr.getTabs().add(te_);
        _tr.getEditors().addIntTab(name_, te_.getPanel(), _path);
    }

    static String lineSeparator(String _content) {
        int len_ = _content.length();
        for (int i = 0; i < len_; i++) {
            char cur_ = _content.charAt(i);
            if (cur_ == '\r') {
                if (i + 1 < _content.length() && _content.charAt(i+1) == '\n') {
                    return "\r\n";
                }
                return "\r";
            }
        }
        return "\n";
    }

    int indexOpened(String _str) {
        int opened_ = -1;
        int s_ = getTabs().size();
        for (int i = 0; i < s_; i++) {
            if (StringUtil.quickEq(getTabs().get(i).getFullPath(), _str)) {
                opened_ = i;
                break;
            }
        }
        return opened_;
    }

    static void refreshList(AbstractMutableTreeNode _sel, String _folderToVisit, AbstractProgramInfos _factories) {
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
            _sel.add(f.getName()+"/");
        }
        for (AbstractFile f : currentFiles_) {
            _sel.add(f.getName());
        }
    }

    static String buildPath(AbstractMutableTreeNode _treePath) {
        StringList pathFull_ = new StringList();
        AbstractMutableTreeNode current_ = _treePath;
        while (current_ != null) {
            pathFull_.add(0,current_.getUserObject());
            current_ = (AbstractMutableTreeNode) current_.getParent();
        }
        StringUtil.removeObj(pathFull_, "");
        return StringUtil.join(pathFull_,"");
    }

    void refresh(AbstractMutableTreeNode _sel, String _str) {
        AbstractProgramInfos frs_ = getCommonFrame().getFrames();
        AbstractMutableTreeNode r_ = getTree().getRoot();
        AbstractMutableTreeNode adj_ = _sel;
        String adjPath_ = _str;
        while (adj_ != r_) {
            String candidate_ = buildPath(adj_);
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
            adj_ = (AbstractMutableTreeNode) adj_.getParent();
        }
        refParent(adj_, adjPath_);
    }

    void refParent(AbstractMutableTreeNode _parent, String _parentPath) {
        _parent.removeAllChildren();
        refreshList(_parent, _parentPath, getCommonFrame().getFrames());
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
        AbstractProgramInfos frs_ = getCommonFrame().getFrames();
        AbstractMutableTreeNode default_ = frs_.getCompoFactory().newMutableTreeNode(_acc+"/");
        folderSystem = frs_.getCompoFactory().newTreeGui(default_);
        folderSystem.select(folderSystem.getRoot());
        refreshList(folderSystem.selectEvt(),_acc, getCommonFrame().getFrames());
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
        AbstractProgramInfos frs_ = getCommonFrame().getFrames();
        setScrollDialog(frs_.getCompoFactory().newAbsScrollPane());
        getScrollDialog().setVisible(false);
        panel.add(frs_.getCompoFactory().newHorizontalSplitPane(frs_.getCompoFactory().newVerticalSplitPane(frs_.getCompoFactory().newAbsScrollPane(folderSystem), getScrollDialog()), editors));
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
        AbstractProgramInfos frs_ = getCommonFrame().getFrames();
        AbsCompoFactory c_ = frs_.getCompoFactory();
        AbsPanel panel_ = c_.newPageBox();
        targetName = c_.newTextField();
        panel_.add(targetName);
        validateDialog = c_.newPlainButton("OK");
        validateDialog.addActionListener(new ValidateFileFolderTree(this));
        panel_.add(validateDialog);
        cancelDialog = c_.newPlainButton("x");
        cancelDialog.addActionListener(new CloseTreeDialog(this));
        panel_.add(cancelDialog);
        scrollDialog.setViewportView(panel_);
        GuiBaseUtil.recalculate(commonFrame.getPane());
    }

    public void fileOrFolder() {
        String str_ = buildPath(selectedNode);
        AbstractProgramInfos frs_ = commonFrame.getFrames();
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
        if (elt_.endsWith("/")) {
            frs_.getFileCoreStream().newFile(elt_).mkdirs();
        } else {
            StreamFolderFile.makeParent(elt_,frs_.getFileCoreStream());
            StreamTextFile.saveTextFile(elt_,"",frs_.getStreams());
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
        AbstractProgramInfos frs_ = _m.getCommonFrame().getFrames();
        String contentConf_ = _m.buildDefConfFile();
        StreamTextFile.saveTextFile(_m.getConfGlobal(), contentConf_, frs_.getStreams());
    }

    void showRenaming() {
        changeEnable(false);
        String str_ = buildPath(selectedNode);
        scrollDialog.setVisible(true);
        AbstractProgramInfos frs_ = getCommonFrame().getFrames();
        AbsCompoFactory c_ = frs_.getCompoFactory();
        AbsPanel panel_ = c_.newPageBox();
        targetName = c_.newTextField();
        panel_.add(c_.newPlainLabel(str_));
        panel_.add(targetName);
        validateDialog = c_.newPlainButton("OK");
        validateDialog.addActionListener(new ValidateRenameTree(this));
        panel_.add(validateDialog);
        cancelDialog = c_.newPlainButton("x");
        cancelDialog.addActionListener(new CloseTreeDialog(this));
        panel_.add(cancelDialog);
        scrollDialog.setViewportView(panel_);
        GuiBaseUtil.recalculate(commonFrame.getPane());
    }

    void renameValidate() {
        AbstractProgramInfos frs_ = commonFrame.getFrames();
        String str_ = buildPath(selectedNode);
        AbstractMutableTreeNode par_ = (AbstractMutableTreeNode) selectedNode.getParent();
        String dest_ = buildPath(par_)+targetName.getText();
        if (!frs_.getFileCoreStream().newFile(str_).renameTo(frs_.getFileCoreStream().newFile(dest_))){
            clearTreeDialog();
            return;
        }
        int opened_ = indexOpened(str_);
        String parentPath_ = dest_.substring(0,dest_.lastIndexOf('/')+1);
        String name_ = dest_.substring(dest_.lastIndexOf('/')+1);
        if (opened_ > -1) {
            getEditors().setTitle(opened_,name_);
            getEditors().setToolTipAt(opened_,dest_);
            getTabs().get(opened_).setFullPath(dest_);
        }
        par_.remove(selectedNode);
        refParent(par_,parentPath_);
        for (AbstractMutableTreeNodeCore c: MutableTreeNodeCoreUtil.children(par_)) {
            if (StringUtil.quickEq(name_,((AbstractMutableTreeNode)c).getUserObject())) {
                folderSystem.select(c);
            }
        }
        clearTreeDialog();
    }

    void showRemoving() {
        changeEnable(false);
        String str_ = buildPath(selectedNode);
        scrollDialog.setVisible(true);
        AbstractProgramInfos frs_ = getCommonFrame().getFrames();
        AbsCompoFactory c_ = frs_.getCompoFactory();
        AbsPanel panel_ = c_.newPageBox();
        panel_.add(c_.newPlainLabel(str_));
        validateDialog = c_.newPlainButton("OK");
        validateDialog.addActionListener(new ValidateRemoveTree(this));
        panel_.add(validateDialog);
        cancelDialog = c_.newPlainButton("x");
        cancelDialog.addActionListener(new CloseTreeDialog(this));
        panel_.add(cancelDialog);
        scrollDialog.setViewportView(panel_);
        GuiBaseUtil.recalculate(commonFrame.getPane());
    }
    void removeValidate() {
        AbstractProgramInfos frs_ = commonFrame.getFrames();
        String str_ = buildPath(selectedNode);
        AbstractMutableTreeNode par_ = (AbstractMutableTreeNode) selectedNode.getParent();
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
        GuiBaseUtil.recalculate(commonFrame.getPane());
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
                c.getEnd().add("\n");
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

    protected ManageOptions manage(StringList _linesFiles) {
        return new ManageOptions(commonFrame.getFrames().getLanguages(), _linesFiles, factory, commonFrame.getFrames().getThreadFactory());
    }
    public AbsCommonFrame getCommonFrame() {
        return commonFrame;
    }

    public AbsTreeGui getFolderSystem() {
        return folderSystem;
    }
    public AbsMenuItem getCreate() {
        return create;
    }

    public AbsMenuItem getDelete() {
        return delete;
    }

    public AbsMenuItem getLanguageMenu() {
        return languageMenu;
    }

    public AbsMenuItem getTabulationsMenu() {
        return tabulationsMenu;
    }

    public AbsMenuItem getCommentsMenu() {
        return commentsMenu;
    }

    public AbsMenuItem getAliasesMenu() {
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

    public AbstractMutableTreeNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(AbstractMutableTreeNode _s) {
        this.selectedNode = _s;
    }

    public AbsPlainButton getValidateDialog() {
        return validateDialog;
    }

    public AbsPlainButton getCancelDialog() {
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

    public AbsMenu getParameters() {
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

    public AbsMenuItem getSrcMenu() {
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
    public abstract AbsTreeGui getTree();
    public abstract void changeEnable(AbstractMutableTreeNode _en);
    public abstract String pathToSrc();

    public abstract WindowCdmEditor getMainFrame();

    public abstract StringList openedFiles();
    public abstract void saveConf();
}
