package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.files.CommentDelimiters;
import code.expressionlanguage.common.ParseLinesArgUtil;
import code.expressionlanguage.options.CommentsUtil;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.utilcompo.CustAliases;
import code.expressionlanguage.utilcompo.ExecutingOptions;
import code.expressionlanguage.utilcompo.FileInfos;
import code.expressionlanguage.utilfiles.DefaultFileSystem;
import code.expressionlanguage.utilimpl.ManageOptions;
import code.gui.*;
import code.gui.events.AbsEnabledAction;
import code.gui.events.QuittingEvent;
import code.gui.initialize.AbstractProgramInfos;
import code.scripts.messages.gui.MessGuiGr;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.sml.Element;
import code.sml.ElementList;
import code.sml.util.Translations;
import code.sml.util.TranslationsAppli;
import code.sml.util.TranslationsFile;
import code.sml.util.TranslationsLg;
import code.stream.*;
import code.stream.comparators.FileNameComparator;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.DefaultUniformingString;
import code.util.core.StringUtil;

public final class WindowCdmEditor implements AbsGroupFrame,WindowWithTree {
    public static final String TEMP_FOLDER = "10";
    public static final String NODE_FILES = "0";
    public static final String NODE_EXP_FILES = "1";
    public static final String NODE_PATH = "1";
    public static final String NODE_EXP_PATH = "2";
    public static final String NODE_DIRECT_MATCH_KEY_VALUE = "3";
    public static final String DEF_CONF = "0";
    public static final String ROOT_CONF = "_";
    public static final String CDM_EDITOR = "cdm_editor";
    private final CdmFactory factory;
    private final ConfirmDialogTextAbs confirmDialogText;
    private final FileSaveDialogAbs fileSaveDialogInt;
    private final AbsMenuItem languageMenu;
    private final AbsMenuItem tabulationsMenu;
    private final ConfirmDialogAnsAbs confirmDialogAns;
    private AbsTreeGui folderSystem;
    private final CustList<OutputDialogComments> commentsFrames = new CustList<OutputDialogComments>();
    private final CustList<OutputDialogTab> tabulationsFrames = new CustList<OutputDialogTab>();
    private final CustList<OutputDialogLanguage> languageFrames = new CustList<OutputDialogLanguage>();
    private final CustList<OutputDialogAliases> aliasesFrames = new CustList<OutputDialogAliases>();
    private final AbsDialog dialogSoft;
    private final AbsDialog dialogFolderExpression;
    private final AbsMenuItem commentsMenu;
    private final AbsMenuItem aliasesMenu;
    private final AbsMenuItem folderExpressionMenu;
    private final AbsMenuItem softParamsMenu;
    private final FileOpenDialogAbs fileOpenDialogInt;
    private final FolderOpenDialogAbs folderOpenDialogInt;
    private StringMap<String> messages;
    private final AbsCommonFrame commonFrame;
    private final AbsPanel panel;
    private final AbsMenuItem chooseFile;
    private final AbsMenuItem create;
    private final AbsMenuItem delete;
    private final AbsPlainButton chooseFolder;
    private final AbsPlainButton createFile;
    private final AbsPlainLabel chosenFolder;
    private final AbsTextField srcFolder;
    private final GraphicComboGrInt chosenLanguage;
    private CdmParameterSoftModel softParams;
//    private String usedLg;
//    private CustList<CommentDelimiters> comments = new CustList<CommentDelimiters>();
//    private StringMap<String> lgMessages = new StringMap<String>();
//    private StringMap<String> lgAliases = new StringMap<String>();
//    private StringMap<String> lgKeyWords = new StringMap<String>();
//    private String currentFolder = "";
//    private int tabWidth = 4;
    private final CustList<TabEditor> tabs = new CustList<TabEditor>();
    private AbsTabbedPane editors;
    private AbsEnabledAction refreshNode;
    private AbsEnabledAction renameNode;
    private AbsEnabledAction removeNode;
    private AbsEnabledAction createSystem;
    private ManageOptions manageOptions;
    private final CustList<WindowExpressionEditor> expressionEditors = new CustList<WindowExpressionEditor>();
    private final CustList<WithFrame> subssubs = new CustList<WithFrame>();

    public WindowCdmEditor(String _lg, AbstractProgramInfos _list, CdmFactory _fact) {
        factory = _fact;
        softParams = new CdmParameterSoftModel();
        confirmDialogAns = _list.getConfirmDialogAns();
        fileOpenDialogInt = _list.getFileOpenDialogInt();
        fileSaveDialogInt = _list.getFileSaveDialogInt();
        folderOpenDialogInt = _list.getFolderOpenDialogInt();
        confirmDialogText = _list.getConfirmDialogText();
        commonFrame = _list.getFrameFactory().newCommonFrame(_lg, _list, null);
        dialogSoft = _list.getFrameFactory().newDialog();
        dialogFolderExpression = _list.getFrameFactory().newDialog();
        GuiBaseUtil.choose(_lg, _list, this, MessGuiGr.ms());
        AbsMenuBar bar_ = _list.getCompoFactory().newMenuBar();
        AbsMenu file_ = _list.getCompoFactory().newMenu("file");
        bar_.add(file_);
        chooseFile = commonFrame.getFrames().getCompoFactory().newMenuItem("open");
        chooseFile.addActionListener(new ChooseInitialFile(this));
        file_.addMenuItem(chooseFile);
        create = commonFrame.getFrames().getCompoFactory().newMenuItem("new");
        create.addActionListener(new AddNewTreeFileNode(this));
        create.setAccelerator(GuiConstants.VK_N,GuiConstants.CTRL_DOWN_MASK);
        create.setEnabled(false);
        file_.addMenuItem(create);
        delete = commonFrame.getFrames().getCompoFactory().newMenuItem("delete");
        delete.addActionListener(new RemoveTreeAction(this));
        delete.setAccelerator(GuiConstants.VK_DELETE,0);
        delete.setEnabled(false);
        file_.addMenuItem(delete);
        AbsMenu menu_ = _list.getCompoFactory().newMenu("boss");
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
        folderExpressionMenu = _list.getCompoFactory().newMenuItem("folder exp");
        folderExpressionMenu.addActionListener(new FolderForExpression(this));
        menu_.addMenuItem(folderExpressionMenu);
        softParamsMenu = _list.getCompoFactory().newMenuItem("soft conf");
        softParamsMenu.addActionListener(new CdmParameterSoftEvent(this));
        menu_.addMenuItem(softParamsMenu);
        chgManagement(false);
        panel = _list.getCompoFactory().newPageBox();
        chooseFolder = commonFrame.getFrames().getCompoFactory().newPlainButton("folder");
        chooseFolder.addActionListener(new ChooseInitialFolder(this));
        chosenFolder = commonFrame.getFrames().getCompoFactory().newPlainLabel(":");
        srcFolder = commonFrame.getFrames().getCompoFactory().newTextField(32);
        StringList lgs_ = new StringList(_list.getTranslations().getMapping().getKeys());
        lgs_.add("");
        chosenLanguage = commonFrame.getFrames().getGeneComboBox().createCombo(commonFrame.getFrames().getImageFactory(), lgs_, -1, commonFrame.getFrames().getCompoFactory());
        createFile = commonFrame.getFrames().getCompoFactory().newPlainButton("create");
        createFile.addActionListener(new CreateInitialFile(this));
        editors = commonFrame.getFrames().getCompoFactory().newAbsTabbedPane();
        commonFrame.setContentPane(panel);
        commonFrame.setJMenuBar(bar_);
        commonFrame.pack();
        commonFrame.setVisible(true);
        commonFrame.addWindowListener(new QuittingEvent(this));
    }

    private void chgManagement(boolean _en) {
        tabulationsMenu.setEnabled(_en);
        commentsMenu.setEnabled(_en);
        aliasesMenu.setEnabled(_en);
        folderExpressionMenu.setEnabled(_en);
    }

    public static String getTempFolder(AbstractProgramInfos _tmpUserFolderSl) {
        return StreamFolderFile.getTempFolder(_tmpUserFolderSl,TEMP_FOLDER);
    }
    public void updateCommentsInit(StringList _files) {
        if (!_files.isEmpty()) {
            updateEnv(_files.first());
            return;
        }
        updateEnv(getTempDefConf(commonFrame.getFrames()));
    }
    public void updateEnv(String _fileConf) {
        AbstractProgramInfos frs_ = commonFrame.getFrames();
        String contentConf_ = StringUtil.nullToEmpty(StreamTextFile.contentsOfFile(_fileConf, frs_.getFileCoreStream(), frs_.getStreams()));
        Document doc_ = DocumentBuilder.parseSax(contentConf_);
        CdmParameterSoftModel params_ = allParams(doc_, commonFrame.getLanguageKey());
        if (params_ != null) {
            softParams = params_;
        } else {
            softParams = new CdmParameterSoftModel();
        }
        commonFrame.setTitle(softParams.getExecConf());
        String flatConf_ = StreamTextFile.contentsOfFile(softParams.getExecConf(), frs_.getFileCoreStream(), frs_.getStreams());
        StringList linesFiles_ = ExecutingOptions.lines(StringUtil.nullToEmpty(flatConf_));
        if (linesFiles_.size() < 2) {
            chgManagement(false);
            panel.removeAll();
            panel.add(chooseFolder);
            panel.add(chosenFolder);
            panel.add(srcFolder);
            StringList lgs_ = new StringList(commonFrame.getFrames().getTranslations().getMapping().getKeys());
            lgs_.add("");
            chosenLanguage.selectItem(StringUtil.indexOf(lgs_,commonFrame.getLanguageKey()));
            panel.add(chosenLanguage.self());
            panel.add(createFile);
            createFile.setEnabled(false);
            commonFrame.setContentPane(panel);
            commonFrame.pack();
            StringList def_ = new StringList();
            def_.add("");
            def_.add(commonFrame.getLanguageKey());
            manageOptions = manage(def_);
            return;
        }
        chgManagement(true);
        manageOptions = manage(linesFiles_);
        initEnv();
    }


    public void folder(String _folderName) {
        manageOptions.getEx().setAccess(_folderName);
        chosenFolder.setText(_folderName);
        createFile.setEnabled(true);
    }

    public void folderExp(String _folderName) {
        softParams.setFolderExpression(_folderName);
        updateDoc();
        boolean add_ = false;
        if (expressionEditors.isEmpty()) {
            expressionEditors.add(new WindowExpressionEditor(this,folderExpressionMenu));
            add_ = true;
        }
        expressionEditors.last().updateEnv(add_);
        subs().add(expressionEditors.last());
    }
    public void saveConf(String _fileName) {
        softParams = new CdmParameterSoftModel();
        softParams.setExecConf(_fileName);
        manageOptions.getEx().setSrcFolder(srcFolder.getText());
//        usedLg = StringUtil.nullToEmpty(chosenLanguage.getSelectedItem());
        manageOptions.getEx().setLg(StringUtil.nullToEmpty(chosenLanguage.getSelectedItem()));
        updateDoc();
        updateComments(manageOptions.getOptions().getComments());
        saveConf();
        initEnv();
    }

    private void initEnv() {
        String acc_ = manageOptions.getEx().getAccess();
        panel.removeAll();
        AbstractProgramInfos frs_ = commonFrame.getFrames();
        AbstractMutableTreeNode default_ = frs_.getCompoFactory().newMutableTreeNode(acc_+"/");
        folderSystem = frs_.getCompoFactory().newTreeGui(default_);
        folderSystem.select(folderSystem.getRoot());
        refreshList(folderSystem.selectEvt(),acc_, commonFrame.getFrames());
        folderSystem.addTreeSelectionListener(new ShowSrcTreeEvent(this));
        refreshNode = frs_.getCompoFactory().wrap(new RefreshTreeAction(this));
        folderSystem.registerKeyboardAction(refreshNode, GuiConstants.VK_F5, GuiConstants.CTRL_DOWN_MASK);
        renameNode = frs_.getCompoFactory().wrap(new RenameTreeAction(this));
        folderSystem.registerKeyboardAction(renameNode, GuiConstants.VK_F6, GuiConstants.CTRL_DOWN_MASK);
        removeNode = frs_.getCompoFactory().wrap(new RemoveTreeAction(this));
        folderSystem.registerKeyboardAction(removeNode, GuiConstants.VK_DELETE, 0);
        createSystem = frs_.getCompoFactory().wrap(new AddNewTreeFileNode(this));
        folderSystem.registerKeyboardAction(createSystem,GuiConstants.VK_N,GuiConstants.CTRL_DOWN_MASK);
        tabs.clear();
        editors = frs_.getCompoFactory().newAbsTabbedPane();
        editors.addChangeListener(new TabValueChanged(this));
        StringList src_ = softParams.getOpenedFiles();
        int len_ = src_.size();
        StringList existing_ = new StringList();
        for (int i = 0; i < len_; i++) {
            String fullPath_ = pathToSrc()+src_.get(i);
            BytesInfo content_ = StreamBinaryFile.loadFile(fullPath_, commonFrame.getFrames().getStreams());
            if (content_.isNul()) {
                continue;
            }
            existing_.add(src_.get(i));
            addTab(this,fullPath_,content_);
        }
        softParams.getOpenedFiles().clear();
        softParams.getOpenedFiles().addAllElts(existing_);
        panel.add(frs_.getCompoFactory().newHorizontalSplitPane(frs_.getCompoFactory().newAbsScrollPane(folderSystem), editors));
        commonFrame.setContentPane(panel);
        commonFrame.pack();
//        currentFolder = acc_;
//        lgMessages = manageOptions.getEx().getMessages();
//        lgAliases = manageOptions.getEx().getAliases();
//        lgKeyWords = manageOptions.getEx().getKeyWords();
//        usedLg = manageOptions.getEx().getLg();
        Options opt_ = manageOptions.getOptions();
//        tabWidth = opt_.getTabWidth();
        CustList<CommentDelimiters> comments_ = opt_.getComments();
        CommentsUtil.checkAndUpdateComments(comments_, CustAliases.defComments(manageOptions.getEx().getLg(), frs_.getTranslations(), frs_.getLanguage()));
//        comments = comments_;
    }

    public static boolean applyTreeChangeSelected(WindowWithTree _instance,boolean _treeEvent) {
        AbstractProgramInfos frs_ = _instance.getMainFrame().getCommonFrame().getFrames();
        AbstractMutableTreeNode sel_ = _instance.getTree().selectEvt();
        if (sel_ == null) {
            _instance.changeEnable(false);
            return false;
        }
        _instance.changeEnable(true);
        String str_ = buildPath(sel_);
        if (_treeEvent) {
            BytesInfo content_ = StreamBinaryFile.loadFile(str_, frs_.getStreams());
            if (!content_.isNul()) {
                int opened_ = indexOpened(_instance,str_);
                if (opened_ > -1) {
                    _instance.getEditors().selectIndex(opened_);
                    return false;
                }
                notifyDoc(_instance,str_);
                addTab(_instance,str_,content_);
                _instance.getEditors().selectIndex(_instance.getTabs().getLastIndex());
                return false;
            }
            if (!frs_.getFileCoreStream().newFile(str_).exists()) {
                closeIfOpened(_instance,str_);
            }
        }
        refresh(_instance,sel_,str_);
        return true;
    }

    public void notifyDoc(String _path) {
        notifyDoc(this,_path);
    }

    public static void notifyDoc(WindowWithTree _instance,String _path) {
        String rel_ = _path.substring(_instance.pathToSrc().length());
        _instance.openedFiles().add(rel_);
        _instance.getMainFrame().updateDoc();
    }

    static void addTab(WindowWithTree _tr,String _path, BytesInfo _content) {
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

    @Override
    public AbsTreeGui getTree() {
        return getFolderSystem();
    }

    public void changeEnable(boolean _en) {
        renameNode.setEnabled(_en);
        refreshNode.setEnabled(_en);
        removeNode.setEnabled(_en);
        createSystem.setEnabled(_en);
        create.setEnabled(_en);
        delete.setEnabled(_en);
    }

    static int indexOpened(WindowWithTree _tr,String _str) {
        int opened_ = -1;
        int s_ = _tr.getTabs().size();
        for (int i = 0; i < s_; i++) {
            if (StringUtil.quickEq(_tr.getTabs().get(i).getFullPath(), _str)) {
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

    public void fileOrFolder(TextAnswerValue _ans) {
        if (_ans.getAnswer() != GuiConstants.YES_OPTION) {
            return;
        }
        AbstractMutableTreeNode sel_ = folderSystem.selectEvt();
        if (sel_ == null) {
            return;
        }
        String str_ = buildPath(sel_);
        AbstractProgramInfos frs_ = commonFrame.getFrames();
        AbstractFile currentFolder_ = frs_.getFileCoreStream().newFile(str_);
        if (!currentFolder_.isDirectory()) {
            return;
        }
        String elt_ = str_+_ans.getTypedText();
        if (frs_.getFileCoreStream().newFile(elt_).exists()) {
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
        applyTreeChangeSelected(this,false);
    }

    static void refresh(WindowWithTree _tr,AbstractMutableTreeNode _sel,String _str) {
        AbstractProgramInfos frs_ = _tr.getMainFrame().getCommonFrame().getFrames();
        AbstractMutableTreeNode r_ = _tr.getTree().getRoot();
        AbstractMutableTreeNode adj_ = _sel;
        String adjPath_ = _str;
        while (adj_ != r_) {
            String candidate_ = buildPath(adj_);
            AbstractFile file_ = frs_.getFileCoreStream().newFile(candidate_);
            if (file_.exists()) {
                adjPath_ = candidate_;
                if (adj_ != _sel) {
                    _tr.getTree().select(adj_);
                    return;
                }
                break;
            }
            closeIfOpened(_tr,candidate_);
            adj_ = (AbstractMutableTreeNode) adj_.getParent();
        }
        refParent(_tr,adj_, adjPath_);
    }

    void rename(TextAnswerValue _ans, AbstractMutableTreeNode _sel,String _str) {
        if (_ans.getAnswer() != GuiConstants.YES_OPTION) {
            return;
        }
        AbstractProgramInfos frs_ = commonFrame.getFrames();
        AbstractMutableTreeNode par_ = (AbstractMutableTreeNode) _sel.getParent();
        String dest_ = buildPath(par_)+_ans.getTypedText();
        if (!frs_.getFileCoreStream().newFile(_str).renameTo(frs_.getFileCoreStream().newFile(dest_))){
            return;
        }
        int opened_ = indexOpened(this,_str);
        String parentPath_ = dest_.substring(0,dest_.lastIndexOf('/')+1);
        String name_ = dest_.substring(dest_.lastIndexOf('/')+1);
        if (opened_ > -1) {
            getEditors().setTitle(opened_,name_);
            getEditors().setToolTipAt(opened_,dest_);
            getTabs().get(opened_).setFullPath(dest_);
        }
        par_.remove(_sel);
        refParent(this,par_,parentPath_);
        for (AbstractMutableTreeNodeCore c: MutableTreeNodeCoreUtil.children(par_)) {
            if (StringUtil.quickEq(name_,((AbstractMutableTreeNode)c).getUserObject())) {
                folderSystem.select(c);
            }
        }
    }

    void remove(int _ans, AbstractMutableTreeNode _sel,String _str) {
        if (_ans != GuiConstants.YES_OPTION) {
            return;
        }
        AbstractProgramInfos frs_ = commonFrame.getFrames();
        AbstractMutableTreeNode par_ = (AbstractMutableTreeNode) _sel.getParent();
        if (!frs_.getFileCoreStream().newFile(_str).delete()){
            return;
        }
        int opened_ = indexOpened(this,_str);
        if (opened_ > -1) {
            getEditors().remove(opened_);
            getTabs().remove(opened_);
        }
        par_.remove(_sel);
        folderSystem.select(par_);
    }

    static void refParent(WindowWithTree _tr,AbstractMutableTreeNode _parent, String _parentPath) {
        _parent.removeAllChildren();
        refreshList(_parent, _parentPath, _tr.getMainFrame().getCommonFrame().getFrames());
        MutableTreeNodeUtil.reload(_tr.getTree());
    }

    static void closeIfOpened(WindowWithTree _tr,String _path) {
        int opened_ = indexOpened(_tr,_path);
        if (opened_ > -1) {
            _tr.getEditors().selectIndex(opened_);
            new CloseTabEditorEvent(_tr.getTabs().get(opened_)).action();
        }
    }

    public void updateDoc() {
        AbstractProgramInfos frs_ = commonFrame.getFrames();
        String contentConf_ = buildDefConfFile();
        StreamTextFile.saveTextFile(getTempDefConf(frs_), contentConf_, frs_.getStreams());
    }

    private ManageOptions manage(StringList _linesFiles) {
        return new ManageOptions(commonFrame.getFrames().getLanguages(), _linesFiles, factory, commonFrame.getFrames().getThreadFactory());
    }

    public static void updateComments(Translations _trs) {
        for (EntryCust<String, TranslationsLg> e: _trs.getMapping().entryList()) {
            TranslationsAppli foundAppli_ = foundAppi(e);
            TranslationsFile foundFile_ = null;
            for (EntryCust<String, TranslationsFile> f: foundAppli_.getMapping().entryList()) {
                if (StringUtil.quickEq(f.getKey(), FileInfos.COMMENTS)) {
                    foundFile_ = f.getValue();
                }
            }
            if (foundFile_ == null) {
                foundFile_ = new TranslationsFile();
                foundAppli_.getMapping().addEntry(FileInfos.COMMENTS,foundFile_);
            }
            boolean foundValue_ = false;
            for (EntryCust<String, String> f: foundFile_.getMapping().entryList()) {
                if (StringUtil.quickEq(f.getKey(), FileInfos.COMM_BEGIN)) {
                    f.setValue(StringUtil.removeAllSpaces(f.getValue()));
                    foundValue_ = true;
                }
            }
            if (!foundValue_) {
                foundFile_.getMapping().addEntry(FileInfos.COMM_BEGIN,"");
            }
        }
    }

    private static TranslationsAppli foundAppi(EntryCust<String, TranslationsLg> _e) {
        TranslationsAppli foundAppli_ = null;
        for (EntryCust<String, TranslationsAppli> f: _e.getValue().getMapping().entryList()) {
            if (StringUtil.quickEq(f.getKey(), FileInfos.CDM)) {
                foundAppli_ = f.getValue();
            }
        }
        if (foundAppli_ == null) {
            foundAppli_ = new TranslationsAppli();
            _e.getValue().getMapping().addEntry(FileInfos.CDM,foundAppli_);
        }
        return foundAppli_;
    }

    public static String getTempDefConf(AbstractProgramInfos _tmpUserFolderSl) {
        return getTempFolder(_tmpUserFolderSl)+"/"+DEF_CONF;
    }
    private static CdmParameterSoftModel allParams(Document _doc, String _lgKey) {
        if (_doc == null || !StringUtil.quickEq(ROOT_CONF, _doc.getDocumentElement().getTagName())) {
            return null;
        }
        CdmParameterSoftModel cdm_ = new CdmParameterSoftModel();
        cdm_.setExecConf(_doc.getDocumentElement().getAttribute(NODE_PATH));
        String accExp_ = _doc.getDocumentElement().getAttribute(NODE_EXP_PATH);
        StringList lines_ = new StringList();
        lines_.add(accExp_);
        cdm_.setFolderExpression(accExp_);
        cdm_.setDirectMatchKeyValue(_doc.getDocumentElement().hasAttribute(NODE_DIRECT_MATCH_KEY_VALUE));
        feedList(_doc, NODE_FILES, cdm_.getOpenedFiles());
        feedList(_doc, NODE_EXP_FILES, cdm_.getOpenedFilesToInit());
        feedList(_doc, NODE_EXP_PATH, lines_);
        if (lines_.size() == 1) {
            lines_.add(_lgKey);
        }
        cdm_.setLines(lines_);
        return cdm_;
    }

    private static void feedList(Document _doc, String _name, StringList _dest) {
        ElementList chsExp_ = _doc.getDocumentElement().getChildElements();
        int lenExp_ = chsExp_.getLength();
        for (int i = 0; i < lenExp_; i++) {
            Element c_ = chsExp_.item(i);
            String relPath_ = c_.getAttribute(_name);
            if (!relPath_.isEmpty()) {
                _dest.add(relPath_);
            }
        }
    }

    public FileOpenDialogAbs getFileOpenDialogInt() {
        return fileOpenDialogInt;
    }

    public FileSaveDialogAbs getFileSaveDialogInt() {
        return fileSaveDialogInt;
    }

    public FolderOpenDialogAbs getFolderOpenDialogInt() {
        return folderOpenDialogInt;
    }

    public ConfirmDialogAnsAbs getConfirmDialogAns() {
        return confirmDialogAns;
    }

    public ConfirmDialogTextAbs getConfirmDialogText() {
        return confirmDialogText;
    }

    public AbsTextField getSrcFolder() {
        return srcFolder;
    }

    public String getExecConf() {
        return softParams.getExecConf();
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

    public void saveConf() {
        String lines_ = fileConf(manageOptions);
        String currentFolder_ = manageOptions.getEx().getAccess();
        AbstractProgramInfos frs_ = commonFrame.getFrames();
        if (!manageOptions.getEx().getSrcFolder().isEmpty()) {
            commonFrame.getFrames().getFileCoreStream().newFile(currentFolder_+"/"+manageOptions.getEx().getSrcFolder()).mkdirs();
        } else {
            commonFrame.getFrames().getFileCoreStream().newFile(currentFolder_+"/src").mkdirs();
        }
        StreamFolderFile.makeParent(softParams.getExecConf(),commonFrame.getFrames().getFileCoreStream());
        StreamTextFile.saveTextFile(softParams.getExecConf(), lines_, frs_.getStreams());
    }

    static String fileConf(ManageOptions _manage) {
        return StringUtil.join(linesConf(_manage),'\n');
    }
    static StringList linesConf(ManageOptions _manage) {
        StringList lines_ = new StringList();
        lines_.add(_manage.getEx().getAccess());
        lines_.add(StringUtil.nullToEmpty(_manage.getEx().getLg()));
        CustList<CommentDelimiters> comments_ = _manage.getOptions().getComments();
        if (!comments_.isEmpty()) {
            lines_.add("comments="+ParseLinesArgUtil.buildCommentsLine(comments_));
        }
        if (!_manage.getEx().getSrcFolder().isEmpty()) {
            lines_.add("src="+ _manage.getEx().getSrcFolder());
        }
        lines_.add("tabWidth="+ _manage.getOptions().getTabWidth());
        if (!_manage.getEx().getMessages().isEmpty()) {
            lines_.add("messages="+ParseLinesArgUtil.buildMapLine(_manage.getEx().getMessages()));
        }
        if (!_manage.getEx().getKeyWords().isEmpty()) {
            lines_.add("keyWords="+ParseLinesArgUtil.buildMapLine(_manage.getEx().getKeyWords()));
        }
        if (!_manage.getEx().getAliases().isEmpty()) {
            lines_.add("aliases="+ParseLinesArgUtil.buildMapLine(_manage.getEx().getAliases()));
        }
        return lines_;
    }

    private String buildDefConfFile() {
        return buildDefConfFile(softParams);
    }

    public static String buildDefConfFile(String _conf, StringList _opened) {
        CdmParameterSoftModel c_ = new CdmParameterSoftModel();
        c_.setExecConf(_conf);
        c_.getOpenedFiles().addAllElts(_opened);
        return buildDefConfFile(c_);
    }
    public static String buildDefConfFile(CdmParameterSoftModel _params) {
        Document doc_ = DocumentBuilder.newXmlDocument();
        Element elt_ = doc_.createElement(ROOT_CONF);
        elt_.setAttribute(NODE_PATH,_params.getExecConf());
        elt_.setAttribute(NODE_EXP_PATH,_params.getFolderExpression());
        if (_params.isDirectMatchKeyValue()) {
            elt_.setAttribute(NODE_DIRECT_MATCH_KEY_VALUE,"");
        }
        append(doc_, elt_, NODE_FILES, _params.getOpenedFiles());
        append(doc_, elt_, NODE_EXP_FILES, _params.getOpenedFilesToInit());
        append(doc_, elt_, NODE_EXP_PATH, _params.getLines().mid(1));
//        Element eltSub_ = doc_.createElement(NODE_PATH);
//        eltSub_.appendChild(doc_.createTextNode(_conf));
//        elt_.appendChild(eltSub_);
        doc_.appendChild(elt_);
        return doc_.export();
    }

    public static void append(Document _doc, Element _elt, String _name, CustList<String> _list) {
        for (String o: _list) {
            Element e_ = _doc.createElement(ROOT_CONF);
            e_.setAttribute(_name,o);
            _elt.appendChild(e_);
        }
    }

    public CdmParameterSoftModel getSoftParams() {
        return softParams;
    }

    public String getFolderExpression() {
        return softParams.getFolderExpression();
    }

    public void setUsedLg(String _u) {
        this.manageOptions.getEx().setLg(_u);
    }

    public String getUsedLg() {
        return manageOptions.getEx().getLg();
    }

    public AbsMenuItem getChooseFile() {
        return chooseFile;
    }

    public AbsMenuItem getCreate() {
        return create;
    }

    public AbsTreeGui getFolderSystem() {
        return folderSystem;
    }

    public AbsPlainButton getChooseFolder() {
        return chooseFolder;
    }

    public AbsPlainButton getCreateFile() {
        return createFile;
    }

    public String getCurrentFolder() {
        return manageOptions.getEx().getAccess();
    }

    public ManageOptions getManageOptions() {
        return manageOptions;
    }

    public String pathToSrc() {
        ManageOptions m_ = getManageOptions();
        String acc_ = m_.getEx().getAccess();
        return acc_+StreamTextFile.SEPARATEUR;
    }

    public StringList getOpenedFiles() {
        return softParams.getOpenedFiles();
    }

    @Override
    public StringList openedFiles() {
        return getOpenedFiles();
    }

    public StringList getOpenedFilesToInit() {
        return softParams.getOpenedFilesToInit();
    }

    public AbsMenuItem getCommentsMenu() {
        return commentsMenu;
    }

    public AbsMenuItem getAliasesMenu() {
        return aliasesMenu;
    }

    public AbsMenuItem getTabulationsMenu() {
        return tabulationsMenu;
    }

    public AbsMenuItem getLanguageMenu() {
        return languageMenu;
    }

    public AbsMenuItem getSoftParamsMenu() {
        return softParamsMenu;
    }

    public AbsMenuItem getFolderExpressionMenu() {
        return folderExpressionMenu;
    }

    public CustList<OutputDialogComments> getCommentsFrames() {
        return commentsFrames;
    }

    public CustList<OutputDialogTab> getTabulationsFrames() {
        return tabulationsFrames;
    }

    public CustList<OutputDialogLanguage> getLanguageFrames() {
        return languageFrames;
    }

    public CustList<WithFrame> subs() {
        return subssubs;
    }

    public CustList<OutputDialogAliases> getAliasesFrames() {
        return aliasesFrames;
    }

    public AbsDialog getDialogSoft() {
        return dialogSoft;
    }

    public AbsDialog getDialogFolderExpression() {
        return dialogFolderExpression;
    }

    public CustList<TabEditor> getTabs() {
        return tabs;
    }

    public AbsTabbedPane getEditors() {
        return editors;
    }

    @Override
    public WindowCdmEditor getMainFrame() {
        return this;
    }

    @Override
    public AbsCommonFrame getCommonFrame() {
        return commonFrame;
    }

    public AbsPanel getPanel() {
        return panel;
    }

    @Override
    public String getApplicationName() {
        return CDM_EDITOR;
    }

    public StringMap<String> getMessages() {
        return messages;
    }

    public void setMessages(StringMap<String> _messages) {
        this.messages = _messages;
    }
    @Override
    public void changeLanguage(String _language) {
        commonFrame.setLanguageKey(_language);
        setMessages(getMessages());
    }

    @Override
    public void dispatchExit() {
        commonFrame.dispatchExit();
    }

    @Override
    public void quit() {
        closeAll();
        GuiBaseUtil.tryExit(commonFrame);
        commonFrame.getFrames().getCounts().getVal(getApplicationName()).decrementAndGet();
    }

    public void closeAll() {
        commonFrame.setVisible(false);
        for (WithFrame w: subs()) {
            w.getFrame().setVisible(false);
            w.getMenu().setEnabled(true);
        }
    }

    public CustList<WindowExpressionEditor> getExpressionEditors() {
        return expressionEditors;
    }

//    @Override
//    public boolean canChangeLanguage() {
//        return false;
//    }
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

    public int getTabWidth() {
        return manageOptions.getOptions().getTabWidth();
    }

    public void setTabWidth(int _t) {
        this.manageOptions.getOptions().setTabWidth(_t);
    }

    public CdmFactory getFactory() {
        return factory;
    }
}
