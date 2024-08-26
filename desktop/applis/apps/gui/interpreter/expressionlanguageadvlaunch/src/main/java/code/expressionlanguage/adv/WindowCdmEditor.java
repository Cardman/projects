package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.files.CommentDelimiters;
import code.expressionlanguage.common.MessagesCdmBase;
import code.expressionlanguage.common.ParseLinesArgUtil;
import code.expressionlanguage.options.CommentsUtil;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.utilcompo.*;
import code.expressionlanguage.utilimpl.ManageOptions;
import code.gui.*;
import code.gui.events.AlwaysActionListenerAct;
import code.gui.events.QuittingEvent;
import code.gui.files.FileOpenFrame;
import code.gui.files.FileSaveFrame;
import code.gui.files.FolderOpenFrame;
import code.gui.initialize.AbstractProgramInfos;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.sml.Element;
import code.sml.ElementList;
import code.sml.util.Translations;
import code.sml.util.TranslationsAppli;
import code.sml.util.TranslationsFile;
import code.sml.util.TranslationsLg;
import code.stream.BytesInfo;
import code.stream.StreamBinaryFile;
import code.stream.StreamFolderFile;
import code.stream.StreamTextFile;
import code.threads.AbstractAtomicBoolean;
import code.threads.AbstractBaseExecutorService;
import code.threads.AbstractFuture;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class WindowCdmEditor extends WindowWithTreeImpl implements AbsGroupFrame,AbsOpenQuit,SetupableFolder {
    public static final String TEMP_FOLDER = "10";
    public static final String NODE_FILES = "0";
    public static final String NODE_EXP_FILES = "1";
    public static final String NODE_PATH = "1";
    public static final String NODE_EXP_PATH = "2";
    public static final String NODE_DIRECT_MATCH_KEY_VALUE = "3";
    public static final String DEF_CONF = "0";
    public static final String ROOT_CONF = "_";
    private final AbsCommonFrame dialogSoft;
    private final AbsCommonFrame dialogFolderExpression;
    private final EnabledMenu folderExpressionMenu;
    private final EnabledMenu softParamsMenu;
    private final EnabledMenu analyzeMenu;
    private final EnabledMenu analyzeMenuSt;
    private AbsResultContextNext mainResultNext;
    private final LanguageDialogButtons languageDialogButtons;
//    private final StringMap<String> coreMessages;
    private final EnabledMenu chooseFile;
    private final AbsButton chooseFolder;
    private final AbsButton createFile;
    private final AbsPlainLabel chosenFolder;
    private final AbsTextField srcFolder;
    private final ScrollCustomCombo chosenLanguage;
    private CdmParameterSoftModel softParams;
    private final CustList<WindowExpressionEditor> expressionEditors = new CustList<WindowExpressionEditor>();
    private String confGlobal=EMPTY_STRING;
    private final AbstractBaseExecutorService service;
    private final AbstractBaseExecutorService serviceDbg;
    private final AbsCommonFrame statusAnalyze;
    private final AbsTextArea statusAnalyzeArea;
    private ResultContext baseResult;
    private ResultContext baseResultDbg;
    private ResultContext baseResultDbgInit;
    private ResultContext userResultGene;
    private ResultContext userResult;
    private AbstractFuture future;
    private AbstractFuture futureDbg;
    private AbstractFuture futureDbgInit;
    private final AbstractAtomicBoolean modal;
    private final ReportingFrame resultFile = ReportingFrame.newInstance(getFrames());
    private final FileSaveFrame fileSaveFrame;
    private final FileOpenFrame fileOpenFrame;
    private final FolderOpenFrame folderOpenFrame;
    private final AbsButton mainButton;

    public WindowCdmEditor(AbstractProgramInfos _list, CdmFactory _fact, LanguagesButtonsPair _pair) {
        super(null, _list, _fact);
        mainButton = _pair.getMainButton();
        languageDialogButtons = new LanguageDialogButtons(_list,null, new AlwaysActionListenerAct());
        modal = _list.getThreadFactory().newAtomicBoolean();
        fileSaveFrame = new FileSaveFrame(_list,modal);
        fileOpenFrame = new FileOpenFrame(_list,modal);
        folderOpenFrame = new FolderOpenFrame(_list,modal);
        service = _list.getThreadFactory().newExecutorService();
        serviceDbg = _list.getThreadFactory().newExecutorService();
        statusAnalyze = _list.getFrameFactory().newCommonFrame();
        statusAnalyzeArea = _list.getCompoFactory().newTextArea();
        statusAnalyzeArea.setEditable(false);
        statusAnalyze.setContentPane(_list.getCompoFactory().newAbsScrollPane(statusAnalyzeArea));
        statusAnalyze.pack();
        softParams = new CdmParameterSoftModel();
        dialogSoft = _list.getFrameFactory().newCommonFrame();
        dialogFolderExpression = _list.getFrameFactory().newCommonFrame();
//        coreMessages = _list.getCommon();
        GuiBaseUtil.choose(this, _list);
        AbsMenuBar bar_ = _list.getCompoFactory().newMenuBar();
        StringMap<String> mes_ = MessagesIde.valFrames(_list.currentLg());
        EnabledMenu file_ = _list.getCompoFactory().newMenu(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_FRAMES_FILE)));
        bar_.add(file_);
        chooseFile = getFrames().getCompoFactory().newMenuItem(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_FRAMES_OPEN)));
        chooseFile.addActionListener(new ChooseInitialFile(this));
        file_.addMenuItem(chooseFile);
        file_.addMenuItem(getSrcMenu());
        file_.addMenuItem(getCreate());
        file_.addMenuItem(getDelete());
        EnabledMenu menu_ = getParameters();
        bar_.add(menu_);
        folderExpressionMenu = _list.getCompoFactory().newMenuItem(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_FRAMES_FOLDER_EXP)));
        folderExpressionMenu.addActionListener(new FolderForExpression(this));
        menu_.addMenuItem(folderExpressionMenu);
        softParamsMenu = _list.getCompoFactory().newMenuItem(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_FRAMES_CONF)));
        softParamsMenu.addActionListener(new CdmParameterSoftEvent(this));
        menu_.addMenuItem(softParamsMenu);
        EnabledMenu run_ = _list.getCompoFactory().newMenu(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_FRAMES_RUN)));
        analyzeMenu = _list.getCompoFactory().newMenuItem(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_FRAMES_ANALYZE)));
        analyzeMenu.addActionListener(new AnalyzeExpressionEvent(this));
        analyzeMenu.setEnabled(false);
        run_.addMenuItem(analyzeMenu);
        analyzeMenuSt = _list.getCompoFactory().newMenuItem(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_FRAMES_STATUS)));
        analyzeMenuSt.addActionListener(new ShowAnalyzeStatusEvent(this));
        run_.addMenuItem(analyzeMenuSt);
        bar_.add(run_);
        chgManagement(false);
        chooseFolder = getFrames().getCompoFactory().newPlainButton(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_FRAMES_CHOOSE_FOLDER)));
        chooseFolder.addActionListener(new ChooseInitialFolder(this));
        chosenFolder = getFrames().getCompoFactory().newPlainLabel(":");
        srcFolder = getFrames().getCompoFactory().newTextField(32);
        StringList lgs_ = new StringList(_list.getTranslations().getMapping().getKeys());
        lgs_.add(EMPTY_STRING);
        chosenLanguage = GuiBaseUtil.combo(getFrames().getImageFactory(), lgs_, -1, getFrames().getCompoFactory());
        createFile = getFrames().getCompoFactory().newPlainButton(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_FRAMES_CREATE_FILE)));
        createFile.addActionListener(new CreateInitialFile(this));
        setEditors(getFrames().getCompoFactory().newAbsTabbedPane());
        TabValueChanged tvc_ = new TabValueChanged(this);
        getEditors().addChangeListener(tvc_);
        getEditors().addMouseListener(tvc_);
        getEditors().addKeyListener(tvc_);
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
        updateEnv(getTempDefConf(getFrames()));
    }
    public void updateEnv(String _fileConf) {
        confGlobal = _fileConf;
        closeAllSubs();
        AbstractProgramInfos frs_ = getFrames();
        String contentConf_ = StringUtil.nullToEmpty(StreamTextFile.contentsOfFile(_fileConf, frs_.getFileCoreStream(), frs_.getStreams()));
        Document doc_ = DocumentBuilder.parseNoTextDocument(contentConf_);
        CdmParameterSoftModel params_ = allParams(doc_, getFrames().getLanguage());
        if (params_ != null) {
            softParams = params_;
        } else {
            softParams = new CdmParameterSoftModel();
        }
        trySubmit();
        getCommonFrame().setTitle(softParams.getExecConf());
        String flatConf_ = StreamTextFile.contentsOfFile(softParams.getExecConf(), frs_.getFileCoreStream(), frs_.getStreams());
        StringList linesFiles_ = ExecutingOptions.lines(StringUtil.nullToEmpty(flatConf_));
        if (linesFiles_.size() < 2) {
            chgManagement(false);
            getPanel().removeAll();
            getPanel().add(chooseFolder);
            getPanel().add(chosenFolder);
            getPanel().add(srcFolder);
            StringList lgs_ = new StringList(getFrames().getTranslations().getMapping().getKeys());
            lgs_.add(EMPTY_STRING);
            chosenLanguage.select(StringUtil.indexOf(lgs_, getFrames().getLanguage()));
            getPanel().add(chosenLanguage.getGlobal());
            getPanel().add(createFile);
            createFile.setEnabled(false);
            getCommonFrame().setContentPane(getPanel());
            getCommonFrame().pack();
            StringList def_ = new StringList();
            def_.add(EMPTY_STRING);
            def_.add(getFrames().getLanguage());
            setManageOptions(manage(def_));
            return;
        }
        chgManagement(true);
        setManageOptions(manage(linesFiles_));
        initEnv();
    }

    public void trySubmit() {
        if (softParams.getLines().size() > 1) {
            setFuture(getService().submitLater(new PreAnalyzeExpressionSource(this, 0)));
            setFutureDbg(getServiceDbg().submitLater(new PreAnalyzeExpressionSource(this, 1)));
            setFutureDbgInit(getServiceDbg().submitLater(new PreAnalyzeExpressionSource(this, 2)));
        }
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
    public void dateSave(String _key) {
        resultFile.display(StringUtil.nullToEmpty(MessagesIde.valInitChoose(getFrames().currentLg()).getVal(_key)),Clock.getDateTimeText(getThreadFactory()));
    }
    public void saveConf(String _fileName) {
        softParams = new CdmParameterSoftModel();
        softParams.setExecConf(_fileName);
        getManageOptions().getEx().setSrcFolder(srcFolder.getText());
//        usedLg = StringUtil.nullToEmpty(chosenLanguage.getSelectedItem());
        getManageOptions().getEx().setLg(StringUtil.nullToEmpty(GuiBaseUtil.getSelectedItem(chosenLanguage)));
        updateDoc();
        updateComments(getManageOptions().getOptions().getComments());
        saveConf();
        initEnv();
    }

    private void initEnv() {
        String acc_ = getManageOptions().getEx().getAccess();
        getPanel().removeAll();
        initTree(acc_);
        AbstractProgramInfos frs_ = getFrames();
        getTabs().clear();
        setEditors(frs_.getCompoFactory().newAbsTabbedPane());
        TabValueChanged tvc_ = new TabValueChanged(this);
        getEditors().addChangeListener(tvc_);
        getEditors().addMouseListener(tvc_);
        getEditors().addKeyListener(tvc_);
        StringList src_ = softParams.getOpenedFiles();
        int len_ = src_.size();
        StringList existing_ = new StringList();
        for (int i = 0; i < len_; i++) {
            String fullPath_ = pathToSrc()+src_.get(i);
            BytesInfo content_ = StreamBinaryFile.loadFile(fullPath_, getFrames().getStreams());
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

    @Override
    public AbsTreeGui getTree() {
        return getFolderSystem();
    }

    @Override
    public void changeEnable(AbstractMutableTreeNodeCore<String> _en) {
        changeEnable(_en != null);
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
                foundFile_.getMapping().addEntry(FileInfos.COMM_BEGIN,EMPTY_STRING);
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
        return getTempFolder(_tmpUserFolderSl)+AbsEditorTabList.SLASH+DEF_CONF;
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

    public AbsTextField getSrcFolder() {
        return srcFolder;
    }

    public String getExecConf() {
        return softParams.getExecConf();
    }

    public void saveConf() {
        String lines_ = fileConf(getManageOptions());
        String currentFolder_ = getManageOptions().getEx().getAccess();
        AbstractProgramInfos frs_ = getFrames();
        if (!getManageOptions().getEx().getSrcFolder().isEmpty()) {
            getFrames().getFileCoreStream().newFile(currentFolder_+AbsEditorTabList.SLASH+ getManageOptions().getEx().getSrcFolder()).mkdirs();
        } else {
            StringMap<String> mesKeys_ = MessagesExecutingOptions.valExecOptionsKeys(getFrames().currentLg());
            String src_ = mesKeys_.getVal(MessagesExecutingOptions.EXEC_OPTIONS_KEY_SRC);
            getFrames().getFileCoreStream().newFile(currentFolder_+AbsEditorTabList.SLASH+src_).mkdirs();
        }
        StreamFolderFile.makeParent(softParams.getExecConf(), getFrames().getFileCoreStream());
        StreamTextFile.saveTextFile(softParams.getExecConf(), lines_, frs_.getStreams());
    }

    static String fileConf(ManageOptions _manage) {
        return StringUtil.join(linesConf(_manage),LINE_RETURN_CH);
    }
    static StringList linesConf(ManageOptions _manage) {
        StringMap<String> mesKeys_ = MessagesExecutingOptions.valExecOptionsKeys(_manage.getEx().getLightProgramInfos().currentLg());
        String src_ = mesKeys_.getVal(MessagesExecutingOptions.EXEC_OPTIONS_KEY_SRC);
        String tabWidth_ = mesKeys_.getVal(MessagesExecutingOptions.EXEC_OPTIONS_KEY_TABWIDTH);
        String als_ = mesKeys_.getVal(MessagesExecutingOptions.EXEC_OPTIONS_KEY_ALIASES);
        String mess_ = mesKeys_.getVal(MessagesExecutingOptions.EXEC_OPTIONS_KEY_MESSAGES);
        String kw_ = mesKeys_.getVal(MessagesExecutingOptions.EXEC_OPTIONS_KEY_KEYWORDS);
        String com_ = mesKeys_.getVal(MessagesExecutingOptions.EXEC_OPTIONS_KEY_COMMENTS);
        StringList lines_ = new StringList();
        lines_.add(_manage.getEx().getAccess());
        lines_.add(StringUtil.nullToEmpty(_manage.getEx().getLg()));
        CustList<CommentDelimiters> comments_ = _manage.getOptions().getComments();
        StringMap<String> parse_ = MessagesCdmBase.valMessages(FileInfos.getAppliTr(_manage.getEx().getLightProgramInfos().currentLg()));
        if (!comments_.isEmpty()) {
            lines_.add(com_+ExecutingOptions.EXEC_OPTIONS_SEP+ParseLinesArgUtil.buildCommentsLine(parse_,comments_));
        }
        if (!_manage.getEx().getSrcFolder().isEmpty()) {
            lines_.add(src_+ExecutingOptions.EXEC_OPTIONS_SEP+ _manage.getEx().getSrcFolder());
        }
        lines_.add(tabWidth_+ExecutingOptions.EXEC_OPTIONS_SEP+ _manage.getOptions().getTabWidth());
        if (!_manage.getEx().getMessages().isEmpty()) {
            lines_.add(mess_+ExecutingOptions.EXEC_OPTIONS_SEP+ParseLinesArgUtil.buildMapLine(parse_,_manage.getEx().getMessages()));
        }
        if (!_manage.getEx().getKeyWords().isEmpty()) {
            lines_.add(kw_+ExecutingOptions.EXEC_OPTIONS_SEP+ParseLinesArgUtil.buildMapLine(parse_,_manage.getEx().getKeyWords()));
        }
        if (!_manage.getEx().getAliases().isEmpty()) {
            lines_.add(als_+ExecutingOptions.EXEC_OPTIONS_SEP+ParseLinesArgUtil.buildMapLine(parse_,_manage.getEx().getAliases()));
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
            elt_.setAttribute(NODE_DIRECT_MATCH_KEY_VALUE,EMPTY_STRING);
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


    public EnabledMenu getChooseFile() {
        return chooseFile;
    }

    public AbsButton getChooseFolder() {
        return chooseFolder;
    }

    public AbsButton getCreateFile() {
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
    public EnabledMenu getSoftParamsMenu() {
        return softParamsMenu;
    }

    public EnabledMenu getFolderExpressionMenu() {
        return folderExpressionMenu;
    }
    public AbsCommonFrame getDialogSoft() {
        return dialogSoft;
    }

    public AbsCommonFrame getDialogFolderExpression() {
        return dialogFolderExpression;
    }

    @Override
    public WindowCdmEditor getMainFrame() {
        return this;
    }

    @Override
    public String getApplicationName() {
        return MessagesIde.CDM_EDITOR;
    }

    @Override
    public void changeLanguage(String _language) {
        getFrames().setLanguage(_language);
        getFactory().getProgramInfos().setLanguage(_language);
    }

    @Override
    public void dispatchExit() {
        getCommonFrame().dispatchExit();
    }

    @Override
    public void quit() {
        closeAll();
        LanguageDialogButtons.enable(mainButton,true);
        GuiBaseUtil.trEx(this, getFrames());
    }

    public void closeAll() {
        getCommonFrame().setVisible(false);
        statusAnalyze.setVisible(false);
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

//    public StringMap<String> getCoreMessages() {
//        return coreMessages;
//    }

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

    public EnabledMenu getAnalyzeMenu() {
        return analyzeMenu;
    }

    public EnabledMenu getAnalyzeMenuSt() {
        return analyzeMenuSt;
    }

    public AbsCommonFrame getStatusAnalyze() {
        return statusAnalyze;
    }

    public AbsTextArea getStatusAnalyzeArea() {
        return statusAnalyzeArea;
    }

    public AbstractBaseExecutorService getService() {
        return service;
    }

    public AbstractBaseExecutorService getServiceDbg() {
        return serviceDbg;
    }

    public ResultContext getBaseResult() {
        return baseResult;
    }

    public void setBaseResult(ResultContext _b) {
        this.baseResult = _b;
    }

    public ResultContext getBaseResultDbg() {
        return baseResultDbg;
    }

    public void setBaseResultDbg(ResultContext _b) {
        this.baseResultDbg = _b;
    }

    public ResultContext getBaseResultDbgInit() {
        return baseResultDbgInit;
    }

    public void setBaseResultDbgInit(ResultContext _b) {
        this.baseResultDbgInit = _b;
    }

    public ResultContext getUserResultGene() {
        return userResultGene;
    }

    public void setUserResultGene(ResultContext _u) {
        this.userResultGene = _u;
    }

    public ResultContext getUserResult() {
        return userResult;
    }

    public void setUserResult(ResultContext _u) {
        this.userResult = _u;
    }

    public AbstractFuture getFuture() {
        return future;
    }

    public void setFuture(AbstractFuture _f) {
        this.future = _f;
    }

    public AbstractFuture getFutureDbg() {
        return futureDbg;
    }

    public void setFutureDbg(AbstractFuture _f) {
        this.futureDbg = _f;
    }

    public AbstractFuture getFutureDbgInit() {
        return futureDbgInit;
    }

    public void setFutureDbgInit(AbstractFuture _f) {
        this.futureDbgInit = _f;
    }

    public AbsResultContextNext getMainResultNext() {
        return mainResultNext;
    }

    public void setMainResultNext(AbsResultContextNext _r) {
        this.mainResultNext = _r;
    }

    public FileSaveFrame getFileSaveFrame() {
        return fileSaveFrame;
    }

    public FileOpenFrame getFileOpenFrame() {
        return fileOpenFrame;
    }

    public FolderOpenFrame getFolderOpenFrame() {
        return folderOpenFrame;
    }

    public AbstractAtomicBoolean getModal() {
        return modal;
    }

    public LanguageDialogButtons getLanguageDialogButtons() {
        return languageDialogButtons;
    }

}
