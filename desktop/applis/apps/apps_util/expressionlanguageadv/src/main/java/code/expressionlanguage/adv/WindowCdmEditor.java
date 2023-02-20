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
import code.util.core.StringUtil;

public final class WindowCdmEditor implements AbsGroupFrame {
    public static final String TEMP_FOLDER = "10";
    public static final String NODE_FILES = "0";
    public static final String NODE_PATH = "1";
    public static final String DEF_CONF = "0";
    public static final String ROOT_CONF = "_";
    public static final String CDM_EDITOR = "cdm_editor";
    private final CdmFactory factory;
    private final ConfirmDialogTextAbs confirmDialogText;
    private final FileSaveDialogAbs fileSaveDialogInt;
    private final AbsMenuItem tabulationsMenu;
    private AbsTreeGui folderSystem;
    private final AbsDialog dialogComments;
    private final AbsDialog dialogNavigLine;
    private final AbsDialog dialogTabulations;
    private final AbsMenuItem commentsMenu;
    private final FileOpenDialogAbs fileOpenDialogInt;
    private final FolderOpenDialogAbs folderOpenDialogInt;
    private StringMap<String> messages;
    private final AbsCommonFrame commonFrame;
    private final AbsPanel panel;
    private final AbsMenuItem chooseFile;
    private final AbsMenuItem create;
    private final AbsPlainButton chooseFolder;
    private final AbsPlainButton createFile;
    private final AbsPlainLabel chosenFolder;
    private final AbsTextField srcFolder;
    private String document;
    private String usedLg = "";
    private String execConf = "";
    private CustList<CommentDelimiters> comments = new CustList<CommentDelimiters>();
    private String currentFolder = "";
    private String currentFolderSrc = "";
    private int tabWidth = 4;
    private final StringList openedFiles = new StringList();
    private final CustList<TabEditor> tabs = new CustList<TabEditor>();
    private AbsTabbedPane editors;

    public WindowCdmEditor(String _lg, AbstractProgramInfos _list, CdmFactory _fact) {
        factory = _fact;
        fileOpenDialogInt = _list.getFileOpenDialogInt();
        fileSaveDialogInt = _list.getFileSaveDialogInt();
        folderOpenDialogInt = _list.getFolderOpenDialogInt();
        confirmDialogText = _list.getConfirmDialogText();
        commonFrame = _list.getFrameFactory().newCommonFrame(_lg, _list, null);
        dialogComments = _list.getFrameFactory().newDialog();
        dialogNavigLine = _list.getFrameFactory().newDialog();
        dialogTabulations = _list.getFrameFactory().newDialog();
        GuiBaseUtil.choose(_lg, _list, this, MessGuiGr.ms());
        AbsMenuBar bar_ = _list.getCompoFactory().newMenuBar();
        AbsMenu file_ = _list.getCompoFactory().newMenu("file");
        bar_.add(file_);
        chooseFile = commonFrame.getFrames().getCompoFactory().newMenuItem("open");
        chooseFile.addActionListener(new ChooseInitialFile(this));
        file_.addMenuItem(chooseFile);
        create = commonFrame.getFrames().getCompoFactory().newMenuItem("new");
        create.addActionListener(new AddNewTreeFileNode(this));
        create.setEnabled(false);
        file_.addMenuItem(create);
        AbsMenu menu_ = _list.getCompoFactory().newMenu("boss");
        bar_.add(menu_);
        tabulationsMenu = _list.getCompoFactory().newMenuItem("tabulations");
        tabulationsMenu.addActionListener(new ChangeTabulationsEvent(this));
        menu_.addMenuItem(tabulationsMenu);
        commentsMenu = _list.getCompoFactory().newMenuItem("comments");
        commentsMenu.addActionListener(new ChangeCommentsEvent(this));
        menu_.addMenuItem(commentsMenu);
        panel = _list.getCompoFactory().newPageBox();
        chooseFolder = commonFrame.getFrames().getCompoFactory().newPlainButton("folder");
        chooseFolder.addActionListener(new ChooseInitialFolder(this));
        chosenFolder = commonFrame.getFrames().getCompoFactory().newPlainLabel(":");
        srcFolder = commonFrame.getFrames().getCompoFactory().newTextField(32);
        createFile = commonFrame.getFrames().getCompoFactory().newPlainButton("create");
        createFile.addActionListener(new CreateInitialFile(this));
        editors = commonFrame.getFrames().getCompoFactory().newAbsTabbedPane();
        commonFrame.setContentPane(panel);
        commonFrame.setJMenuBar(bar_);
        commonFrame.pack();
        commonFrame.setVisible(true);
        commonFrame.addWindowListener(new QuittingEvent(this));
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
        document = contentConf_;
        execConf = retrievePath(doc_);
        commonFrame.setTitle(execConf);
        String flatConf_ = StreamTextFile.contentsOfFile(execConf, frs_.getFileCoreStream(), frs_.getStreams());
        StringList linesFiles_ = ExecutingOptions.lines(StringUtil.nullToEmpty(flatConf_));
        if (linesFiles_.size() < 2) {
            currentFolder = "";
            panel.removeAll();
            panel.add(chooseFolder);
            panel.add(chosenFolder);
            panel.add(srcFolder);
            panel.add(createFile);
            createFile.setEnabled(false);
            commonFrame.setContentPane(panel);
            commonFrame.pack();
            return;
        }
        ManageOptions manage_ = manage(linesFiles_);
        initEnv(manage_);
    }


    public void folder(String _folderName) {
        currentFolder = _folderName;
        chosenFolder.setText(_folderName);
        createFile.setEnabled(true);
    }
    public void saveConf(String _fileName) {
        execConf = _fileName;
        updateDoc();
        updateComments(comments);
        ManageOptions opts_ = saveConf();
        initEnv(opts_);
    }

    private void initEnv(ManageOptions _linesFiles) {
        String acc_ = _linesFiles.getEx().getAccess();
        String srcFolderRel_ = _linesFiles.getEx().getSrcFolder();
        currentFolderSrc =srcFolderRel_;
        panel.removeAll();
        AbstractProgramInfos frs_ = commonFrame.getFrames();
        AbstractMutableTreeNode default_ = frs_.getCompoFactory().newMutableTreeNode(acc_+"/");
        folderSystem = frs_.getCompoFactory().newTreeGui(default_);
        folderSystem.select(folderSystem.getRoot());
        refreshList(folderSystem.selectEvt(),acc_);
        folderSystem.addTreeSelectionListener(new ShowSrcTreeEvent(this));
        folderSystem.getTree().registerKeyboardAction(frs_.getCompoFactory().wrap(new RefreshTreeAction(this)), GuiConstants.VK_F5, GuiConstants.CTRL_DOWN_MASK);
        tabs.clear();
        openedFiles.clear();
        editors = frs_.getCompoFactory().newAbsTabbedPane();
        editors.addChangeListener(new TabValueChanged(this));
        StringList src_ = retrieveRelativeFiles(DocumentBuilder.parseSax(document));
        int len_ = src_.size();
        for (int i = 0; i < len_; i++) {
            String fullPath_ = acc_+StreamTextFile.SEPARATEUR+srcFolderRel_+StreamTextFile.SEPARATEUR+src_.get(i);
            BytesInfo content_ = StreamBinaryFile.loadFile(fullPath_, commonFrame.getFrames().getStreams());
            if (content_.isNul()) {
                continue;
            }
            String dec_ = StringUtil.nullToEmpty(StringUtil.decode(content_.getBytes()));
            openedFiles.add(src_.get(i));
            String name_ = fullPath_.substring(fullPath_.lastIndexOf('/')+1);
            TabEditor te_ = new TabEditor(this,fullPath_);
            te_.getCenter().setText(StringUtil.replace(dec_,"\r",""));
            tabs.add(te_);
            editors.addIntTab(name_, te_.getPanel(),fullPath_);
        }
        create.setEnabled(true);
        panel.add(frs_.getCompoFactory().newHorizontalSplitPane(frs_.getCompoFactory().newAbsScrollPane(folderSystem.getTree()), editors));
        commonFrame.setContentPane(panel);
        commonFrame.pack();
        currentFolder = acc_;
        usedLg = _linesFiles.getLanguage();
        Options opt_ = _linesFiles.getOptions();
        tabWidth = opt_.getTabWidth();
        CustList<CommentDelimiters> comments_ = opt_.getComments();
        CommentsUtil.checkAndUpdateComments(comments_, CustAliases.defComments("", frs_.getTranslations(), frs_.getLanguage()));
        comments = comments_;
    }


    public boolean applyTreeChangeSelected(boolean _treeEvent) {
        AbstractMutableTreeNode sel_ = folderSystem.selectEvt();
        if (sel_ == null) {
            return false;
        }
        String str_ = buildPath(sel_);
        if (_treeEvent && str_.startsWith(currentFolder+StreamTextFile.SEPARATEUR+currentFolderSrc+StreamTextFile.SEPARATEUR)) {
            BytesInfo content_ = StreamBinaryFile.loadFile(str_, commonFrame.getFrames().getStreams());
            if (!content_.isNul()) {
                int opened_ = -1;
                int s_ = getTabs().size();
                for (int i = 0; i < s_; i++) {
                    if (StringUtil.quickEq(getTabs().get(i).getFullPath(),str_)) {
                        opened_ = i;
                        break;
                    }
                }
                if (opened_ > -1) {
                    getEditors().selectIndex(opened_);
                    return false;
                }
                String rel_ = str_.substring(currentFolder.length() + currentFolderSrc.length() + 2);
                openedFiles.add(rel_);
                updateDoc();
                String name_ = str_.substring(str_.lastIndexOf('/')+1);
                TabEditor te_ = new TabEditor(this,str_);
                String dec_ = StringUtil.nullToEmpty(StringUtil.decode(content_.getBytes()));
                te_.getCenter().setText(StringUtil.replace(dec_,"\r",""));
                tabs.add(te_);
                editors.addIntTab(name_, te_.getPanel(), str_);
                getEditors().selectIndex(tabs.getLastIndex());
                return false;
            }
        }
        refresh(sel_,str_);
        return true;
    }

    private void refreshList(AbstractMutableTreeNode _sel,String _folderToVisit) {
        AbstractProgramInfos frs_ = commonFrame.getFrames();
        FileListInfo files_ = PathsUtil.abs(frs_.getFileCoreStream().newFile(_folderToVisit),frs_.getFileCoreStream());
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
        if (frs_.getFileCoreStream().newFile(elt_).exists() || !elt_.startsWith(currentFolder+StreamTextFile.SEPARATEUR+currentFolderSrc+StreamTextFile.SEPARATEUR)) {
            return;
        }
        if (elt_.endsWith("/")) {
            frs_.getFileCoreStream().newFile(elt_).mkdirs();
        } else {
            StreamFolderFile.makeParent(elt_,frs_.getFileCoreStream());
            StreamTextFile.saveTextFile(elt_,"",frs_.getStreams());
            String rel_ = elt_.substring(currentFolder.length() + currentFolderSrc.length() + 2);
            openedFiles.add(rel_);
            updateDoc();
            String name_ = elt_.substring(elt_.lastIndexOf('/')+1);
            TabEditor te_ = new TabEditor(this,elt_);
            tabs.add(te_);
            editors.addIntTab(name_, te_.getPanel(), elt_);
            editors.selectIndex(tabs.getLastIndex());
        }
        applyTreeChangeSelected(false);
    }

    void refresh(AbstractMutableTreeNode _sel,String _str) {
        _sel.removeAllChildren();
        refreshList(_sel,_str);
        MutableTreeNodeUtil.reload(folderSystem);
    }

    public void updateDoc() {
        AbstractProgramInfos frs_ = commonFrame.getFrames();
        String contentConf_ = buildDefConfFile();
        document = contentConf_;
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
    private static String retrievePath(Document _doc) {
        if (_doc == null || !StringUtil.quickEq(ROOT_CONF, _doc.getDocumentElement().getTagName())) {
            return "";
        }
        return _doc.getDocumentElement().getAttribute(NODE_PATH);
    }
    private static StringList retrieveRelativeFiles(Document _doc) {
        StringList files_ = new StringList();
        ElementList chs_ = _doc.getDocumentElement().getChildElements();
        int len_ = chs_.getLength();
        for (int i = 0; i < len_; i++) {
            Element c_ = chs_.item(i);
            String relPath_ = c_.getAttribute(NODE_FILES);
            if (!relPath_.isEmpty()) {
                files_.add(relPath_);
            }
        }
        return files_;
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

    public ConfirmDialogTextAbs getConfirmDialogText() {
        return confirmDialogText;
    }

    public AbsTextField getSrcFolder() {
        return srcFolder;
    }

    public String getExecConf() {
        return execConf;
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
        CommentsUtil.checkAndUpdateComments(_comm, comments);
        for (CommentDelimiters c: _comm) {
            if (c.getEnd().get(0).trim().isEmpty()) {
                c.getEnd().clear();
                c.getEnd().add("\n");
            }
        }
        comments = _comm;
    }

    public ManageOptions saveConf() {
        AbstractProgramInfos frs_ = commonFrame.getFrames();
        StringList lines_ = new StringList();
        lines_.add(currentFolder);
        lines_.add(commonFrame.getLanguageKey());
        if (!comments.isEmpty()) {
            lines_.add("comments="+ParseLinesArgUtil.buildCommentsLine(comments));
        }
        if (!srcFolder.getText().isEmpty()) {
            lines_.add("src="+srcFolder.getText());
            commonFrame.getFrames().getFileCoreStream().newFile(currentFolder+"/"+srcFolder.getText()).mkdirs();
        } else {
            commonFrame.getFrames().getFileCoreStream().newFile(currentFolder+"/src").mkdirs();
        }
        lines_.add("tabWidth="+tabWidth);
        StreamFolderFile.makeParent(execConf,commonFrame.getFrames().getFileCoreStream());
        StreamTextFile.saveTextFile(execConf, StringUtil.join(lines_,'\n'), frs_.getStreams());
        return new ManageOptions(commonFrame.getFrames().getLanguages(), lines_, factory, commonFrame.getFrames().getThreadFactory());
    }

    private String buildDefConfFile() {
        return buildDefConfFile(execConf,openedFiles);
    }

    public static String buildDefConfFile(String _conf, StringList _opened) {
        Document doc_ = DocumentBuilder.newXmlDocument();
        Element elt_ = doc_.createElement(ROOT_CONF);
        elt_.setAttribute(NODE_PATH,_conf);
        for (String o: _opened) {
            Element e_ = doc_.createElement(ROOT_CONF);
            e_.setAttribute(NODE_FILES,o);
            elt_.appendChild(e_);
        }
//        Element eltSub_ = doc_.createElement(NODE_PATH);
//        eltSub_.appendChild(doc_.createTextNode(_conf));
//        elt_.appendChild(eltSub_);
        doc_.appendChild(elt_);
        return doc_.export();
    }

    public String getUsedLg() {
        return usedLg;
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
        return currentFolder;
    }

    public String getCurrentFolderSrc() {
        return currentFolderSrc;
    }

    public StringList getOpenedFiles() {
        return openedFiles;
    }

    public AbsMenuItem getCommentsMenu() {
        return commentsMenu;
    }

    public AbsMenuItem getTabulationsMenu() {
        return tabulationsMenu;
    }

    public AbsDialog getDialogComments() {
        return dialogComments;
    }

    public AbsDialog getDialogNavigLine() {
        return dialogNavigLine;
    }

    public AbsDialog getDialogTabulations() {
        return dialogTabulations;
    }
    //    public TabEditor getTabEditor() {
//        return tabEditor;
//    }

    public CustList<TabEditor> getTabs() {
        return tabs;
    }

    public AbsTabbedPane getEditors() {
        return editors;
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
        setMessages(getMessages());
    }

    @Override
    public void dispatchExit() {
        commonFrame.dispatchExit();
    }

    @Override
    public void quit() {
        commonFrame.setVisible(false);
        GuiBaseUtil.tryExit(commonFrame);
        commonFrame.getFrames().getCounts().getVal(getApplicationName()).decrementAndGet();
    }

    @Override
    public boolean canChangeLanguage() {
        return false;
    }
    public CustList<CommentDelimiters> getComments() {
        return comments;
    }

    public void setComments(CustList<CommentDelimiters> _c) {
        this.comments = _c;
    }

    public int getTabWidth() {
        return tabWidth;
    }

    public void setTabWidth(int _t) {
        this.tabWidth = _t;
    }

}
