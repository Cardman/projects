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

public final class WindowCdmEditor extends WindowWithTreeImpl implements AbsGroupFrame {
    public static final String TEMP_FOLDER = "10";
    public static final String NODE_FILES = "0";
    public static final String NODE_EXP_FILES = "1";
    public static final String NODE_PATH = "1";
    public static final String NODE_EXP_PATH = "2";
    public static final String NODE_DIRECT_MATCH_KEY_VALUE = "3";
    public static final String DEF_CONF = "0";
    public static final String ROOT_CONF = "_";
    public static final String CDM_EDITOR = "cdm_editor";
    private final FileSaveDialogAbs fileSaveDialogInt;
    private final AbsDialog dialogSoft;
    private final AbsDialog dialogFolderExpression;
    private final AbsMenuItem folderExpressionMenu;
    private final AbsMenuItem softParamsMenu;
    private final FileOpenDialogAbs fileOpenDialogInt;
    private final FolderOpenDialogAbs folderOpenDialogInt;
    private StringMap<String> messages;
    private final AbsMenuItem chooseFile;
    private final AbsPlainButton chooseFolder;
    private final AbsPlainButton createFile;
    private final AbsPlainLabel chosenFolder;
    private final AbsTextField srcFolder;
    private final GraphicComboGrInt chosenLanguage;
    private CdmParameterSoftModel softParams;
    private final CustList<WindowExpressionEditor> expressionEditors = new CustList<WindowExpressionEditor>();
    private String confGlobal="";

    public WindowCdmEditor(String _lg, AbstractProgramInfos _list, CdmFactory _fact) {
        super(_lg, _list, _fact);
        softParams = new CdmParameterSoftModel();
        fileOpenDialogInt = _list.getFileOpenDialogInt();
        fileSaveDialogInt = _list.getFileSaveDialogInt();
        folderOpenDialogInt = _list.getFolderOpenDialogInt();
        dialogSoft = _list.getFrameFactory().newDialog();
        dialogFolderExpression = _list.getFrameFactory().newDialog();
        GuiBaseUtil.choose(_lg, _list, this, MessGuiGr.ms());
        AbsMenuBar bar_ = _list.getCompoFactory().newMenuBar();
        AbsMenu file_ = _list.getCompoFactory().newMenu("file");
        bar_.add(file_);
        chooseFile = getCommonFrame().getFrames().getCompoFactory().newMenuItem("open");
        chooseFile.addActionListener(new ChooseInitialFile(this));
        file_.addMenuItem(chooseFile);
        file_.addMenuItem(getCreate());
        file_.addMenuItem(getDelete());
        AbsMenu menu_ = getParameters();
        bar_.add(menu_);
        folderExpressionMenu = _list.getCompoFactory().newMenuItem("folder exp");
        folderExpressionMenu.addActionListener(new FolderForExpression(this));
        menu_.addMenuItem(folderExpressionMenu);
        softParamsMenu = _list.getCompoFactory().newMenuItem("soft conf");
        softParamsMenu.addActionListener(new CdmParameterSoftEvent(this));
        menu_.addMenuItem(softParamsMenu);
        chgManagement(false);
        chooseFolder = getCommonFrame().getFrames().getCompoFactory().newPlainButton("folder");
        chooseFolder.addActionListener(new ChooseInitialFolder(this));
        chosenFolder = getCommonFrame().getFrames().getCompoFactory().newPlainLabel(":");
        srcFolder = getCommonFrame().getFrames().getCompoFactory().newTextField(32);
        StringList lgs_ = new StringList(_list.getTranslations().getMapping().getKeys());
        lgs_.add("");
        chosenLanguage = getCommonFrame().getFrames().getGeneComboBox().createCombo(getCommonFrame().getFrames().getImageFactory(), lgs_, -1, getCommonFrame().getFrames().getCompoFactory());
        createFile = getCommonFrame().getFrames().getCompoFactory().newPlainButton("create");
        createFile.addActionListener(new CreateInitialFile(this));
        setEditors(getCommonFrame().getFrames().getCompoFactory().newAbsTabbedPane());
        getCommonFrame().setContentPane(getPanel());
        getCommonFrame().setJMenuBar(bar_);
        getCommonFrame().pack();
        getCommonFrame().setVisible(true);
        getCommonFrame().addWindowListener(new QuittingEvent(this));
    }

    @Override
    public void chgManagement(boolean _en) {
        super.chgManagement(_en);
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
        updateEnv(getTempDefConf(getCommonFrame().getFrames()));
    }
    public void updateEnv(String _fileConf) {
        confGlobal = _fileConf;
        closeAllSubs();
        AbstractProgramInfos frs_ = getCommonFrame().getFrames();
        String contentConf_ = StringUtil.nullToEmpty(StreamTextFile.contentsOfFile(_fileConf, frs_.getFileCoreStream(), frs_.getStreams()));
        Document doc_ = DocumentBuilder.parseSax(contentConf_);
        CdmParameterSoftModel params_ = allParams(doc_, getCommonFrame().getLanguageKey());
        if (params_ != null) {
            softParams = params_;
        } else {
            softParams = new CdmParameterSoftModel();
        }
        getCommonFrame().setTitle(softParams.getExecConf());
        String flatConf_ = StreamTextFile.contentsOfFile(softParams.getExecConf(), frs_.getFileCoreStream(), frs_.getStreams());
        StringList linesFiles_ = ExecutingOptions.lines(StringUtil.nullToEmpty(flatConf_));
        if (linesFiles_.size() < 2) {
            chgManagement(false);
            getPanel().removeAll();
            getPanel().add(chooseFolder);
            getPanel().add(chosenFolder);
            getPanel().add(srcFolder);
            StringList lgs_ = new StringList(getCommonFrame().getFrames().getTranslations().getMapping().getKeys());
            lgs_.add("");
            chosenLanguage.selectItem(StringUtil.indexOf(lgs_, getCommonFrame().getLanguageKey()));
            getPanel().add(chosenLanguage.self());
            getPanel().add(createFile);
            createFile.setEnabled(false);
            getCommonFrame().setContentPane(getPanel());
            getCommonFrame().pack();
            StringList def_ = new StringList();
            def_.add("");
            def_.add(getCommonFrame().getLanguageKey());
            setManageOptions(manage(def_));
            return;
        }
        chgManagement(true);
        setManageOptions(manage(linesFiles_));
        initEnv();
    }


    public void folder(String _folderName) {
        getManageOptions().getEx().setAccess(_folderName);
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
    }
    public void saveConf(String _fileName) {
        softParams = new CdmParameterSoftModel();
        softParams.setExecConf(_fileName);
        getManageOptions().getEx().setSrcFolder(srcFolder.getText());
//        usedLg = StringUtil.nullToEmpty(chosenLanguage.getSelectedItem());
        getManageOptions().getEx().setLg(StringUtil.nullToEmpty(chosenLanguage.getSelectedItem()));
        updateDoc();
        updateComments(getManageOptions().getOptions().getComments());
        saveConf();
        initEnv();
    }

    private void initEnv() {
        String acc_ = getManageOptions().getEx().getAccess();
        getPanel().removeAll();
        initTree(acc_);
        AbstractProgramInfos frs_ = getCommonFrame().getFrames();
        getTabs().clear();
        setEditors(frs_.getCompoFactory().newAbsTabbedPane());
        getEditors().addChangeListener(new TabValueChanged(this));
        StringList src_ = softParams.getOpenedFiles();
        int len_ = src_.size();
        StringList existing_ = new StringList();
        for (int i = 0; i < len_; i++) {
            String fullPath_ = pathToSrc()+src_.get(i);
            BytesInfo content_ = StreamBinaryFile.loadFile(fullPath_, getCommonFrame().getFrames().getStreams());
            if (content_.isNul()) {
                continue;
            }
            existing_.add(src_.get(i));
            addTab(this,fullPath_,content_);
        }
        softParams.getOpenedFiles().clear();
        softParams.getOpenedFiles().addAllElts(existing_);
        endTree();
        getCommonFrame().setContentPane(getPanel());
        getCommonFrame().pack();
//        currentFolder = acc_;
//        lgMessages = manageOptions.getEx().getMessages();
//        lgAliases = manageOptions.getEx().getAliases();
//        lgKeyWords = manageOptions.getEx().getKeyWords();
//        usedLg = manageOptions.getEx().getLg();
        Options opt_ = getManageOptions().getOptions();
//        tabWidth = opt_.getTabWidth();
        CustList<CommentDelimiters> comments_ = opt_.getComments();
        CommentsUtil.checkAndUpdateComments(comments_, CustAliases.defComments(getManageOptions().getEx().getLg(), frs_.getTranslations(), frs_.getLanguage()));
//        comments = comments_;
    }

    public static boolean applyTreeChangeSelected(WindowWithTreeImpl _instance,boolean _treeEvent) {
        AbstractProgramInfos frs_ = _instance.getMainFrame().getCommonFrame().getFrames();
        AbstractMutableTreeNode sel_ = _instance.getTree().selectEvt();
        _instance.changeEnable(sel_);
        if (sel_ == null) {
            return false;
        }
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

    public static void notifyDoc(WindowWithTree _instance,String _path) {
        String rel_ = _path.substring(_instance.pathToSrc().length());
        _instance.openedFiles().add(rel_);
        _instance.getMainFrame().updateDoc();
    }

    static void addTab(WindowWithTreeImpl _tr,String _path, BytesInfo _content) {
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

    @Override
    public void changeEnable(AbstractMutableTreeNode _en) {
        changeEnable(_en != null);
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

    public AbsTextField getSrcFolder() {
        return srcFolder;
    }

    public String getExecConf() {
        return softParams.getExecConf();
    }

    public void saveConf() {
        String lines_ = fileConf(getManageOptions());
        String currentFolder_ = getManageOptions().getEx().getAccess();
        AbstractProgramInfos frs_ = getCommonFrame().getFrames();
        if (!getManageOptions().getEx().getSrcFolder().isEmpty()) {
            getCommonFrame().getFrames().getFileCoreStream().newFile(currentFolder_+"/"+ getManageOptions().getEx().getSrcFolder()).mkdirs();
        } else {
            getCommonFrame().getFrames().getFileCoreStream().newFile(currentFolder_+"/src").mkdirs();
        }
        StreamFolderFile.makeParent(softParams.getExecConf(), getCommonFrame().getFrames().getFileCoreStream());
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

    public String buildDefConfFile() {
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


    public AbsMenuItem getChooseFile() {
        return chooseFile;
    }

    public AbsPlainButton getChooseFolder() {
        return chooseFolder;
    }

    public AbsPlainButton getCreateFile() {
        return createFile;
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
    public AbsMenuItem getSoftParamsMenu() {
        return softParamsMenu;
    }

    public AbsMenuItem getFolderExpressionMenu() {
        return folderExpressionMenu;
    }
    public AbsDialog getDialogSoft() {
        return dialogSoft;
    }

    public AbsDialog getDialogFolderExpression() {
        return dialogFolderExpression;
    }

    @Override
    public WindowCdmEditor getMainFrame() {
        return this;
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
        getCommonFrame().setLanguageKey(_language);
        setMessages(getMessages());
    }

    @Override
    public void dispatchExit() {
        getCommonFrame().dispatchExit();
    }

    @Override
    public void quit() {
        closeAll();
        GuiBaseUtil.tryExit(getCommonFrame());
        getCommonFrame().getFrames().getCounts().getVal(getApplicationName()).decrementAndGet();
    }

    public void closeAll() {
        getCommonFrame().setVisible(false);
        closeAllSubs();
    }

    private void closeAllSubs() {
        closeSecs();
        for (WindowExpressionEditor w: getExpressionEditors()) {
            w.closeWindows();
            w.getMenu().setEnabled(isEditing());
        }
        expressionEditors.clear();
    }

    public CustList<WindowExpressionEditor> getExpressionEditors() {
        return expressionEditors;
    }

    public String getConfGlobal() {
        return confGlobal;
    }
    //    @Override
//    public boolean canChangeLanguage() {
//        return false;
//    }


}
