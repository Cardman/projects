package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.files.CommentDelimiters;
import code.expressionlanguage.common.ParseLinesArgUtil;
import code.expressionlanguage.options.CommentsUtil;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.utilcompo.CustAliases;
import code.expressionlanguage.utilcompo.ExecutingOptions;
import code.expressionlanguage.utilcompo.FileInfos;
import code.expressionlanguage.utilimpl.ManageOptions;
import code.gui.*;
import code.gui.events.QuittingEvent;
import code.gui.initialize.AbstractProgramInfos;
import code.scripts.messages.gui.MessGuiGr;
import code.sml.*;
import code.sml.util.Translations;
import code.sml.util.TranslationsAppli;
import code.sml.util.TranslationsFile;
import code.sml.util.TranslationsLg;
import code.stream.StreamFolderFile;
import code.stream.StreamTextFile;
import code.util.*;
import code.util.core.StringUtil;

public final class WindowCdmEditor implements AbsGroupFrame {
    public static final String TEMP_FOLDER = "10";
    public static final String NODE_FILES = "0";
    public static final String NODE_PATH = "1";
    public static final String DEF_CONF = "0";
    public static final String ROOT_CONF = "_";
    private final CdmFactory factory;
    private TabEditor tabEditor;
    private final AbsDialog dialogComments;
    private final AbsDialog dialogNavigLine;
    private final AbsMenuItem commentsMenu;
    private final FileOpenDialogAbs fileOpenDialogInt;
    private final FolderOpenDialogAbs folderOpenDialogInt;
    private StringMap<String> messages;
    private final AbsCommonFrame commonFrame;
    private final AbsSpinner spinner;
    private final TabValueChanged spinnerEvent;
    private final AbsPanel panel;
    private final AbsMenuItem chooseFile;
    private final AbsPlainButton chooseFolder;
    private final AbsPlainButton createFile;
    private final AbsTextField srcFolder;
    private final IdList<WindowCdmEditor> ides;
    private String usedLg = "";
    private String execConf = "";
    private CustList<CommentDelimiters> comments = new CustList<CommentDelimiters>();
    private String currentFolder = "";

    public WindowCdmEditor(String _lg, AbstractProgramInfos _list, CdmFactory _fact, IdList<WindowCdmEditor> _opened) {
        factory = _fact;
        fileOpenDialogInt = _list.getFileOpenDialogInt();
        folderOpenDialogInt = _list.getFolderOpenDialogInt();
        commonFrame = _list.getFrameFactory().newCommonFrame(_lg, _list, null);
        dialogComments = _list.getFrameFactory().newDialog();
        dialogNavigLine = _list.getFrameFactory().newDialog();
        GuiBaseUtil.choose(_lg, _list, this, MessGuiGr.ms());
        AbsMenuBar bar_ = _list.getCompoFactory().newMenuBar();
        AbsMenu file_ = _list.getCompoFactory().newMenu("file");
        bar_.add(file_);
        chooseFile = commonFrame.getFrames().getCompoFactory().newMenuItem("open");
        chooseFile.addActionListener(new ChooseInitialFile(this));
        file_.addMenuItem(chooseFile);
        AbsMenu menu_ = _list.getCompoFactory().newMenu("boss");
        bar_.add(menu_);
        commentsMenu = _list.getCompoFactory().newMenuItem("comments");
        commentsMenu.addActionListener(new ChangeCommentsEvent(this));
        menu_.addMenuItem(commentsMenu);
        spinner = _list.getCompoFactory().newSpinner(4,1,64,1);
        TabValueChanged l_ = new TabValueChanged(this);
        spinner.addChangeListener(l_);
        spinnerEvent = l_;
        panel = _list.getCompoFactory().newPageBox();
        chooseFolder = commonFrame.getFrames().getCompoFactory().newPlainButton("folder");
        chooseFolder.addActionListener(new ChooseInitialFolder(this));
        srcFolder = commonFrame.getFrames().getCompoFactory().newTextField(32);
        createFile = commonFrame.getFrames().getCompoFactory().newPlainButton("create");
        createFile.addActionListener(new CreateInitialFile(this));
        commonFrame.setContentPane(panel);
        commonFrame.setJMenuBar(bar_);
        commonFrame.pack();
        commonFrame.setVisible(true);
        commonFrame.addWindowListener(new QuittingEvent(this));
        ides = _opened;
        _opened.add(this);
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
        Document doc_ = DocumentBuilder.parseSax(StringUtil.nullToEmpty(StreamTextFile.contentsOfFile(_fileConf, frs_.getFileCoreStream(), frs_.getStreams())));
        execConf = retrievePath(doc_);
        String flatConf_ = StreamTextFile.contentsOfFile(execConf, frs_.getFileCoreStream(), frs_.getStreams());
        StringList linesFiles_ = ExecutingOptions.lines(StringUtil.nullToEmpty(flatConf_));
        if (linesFiles_.size() < 2) {
            currentFolder = "";
            panel.removeAll();
            panel.add(chooseFolder);
            panel.add(srcFolder);
            panel.add(createFile);
            createFile.setEnabled(false);
            commonFrame.setContentPane(panel);
            commonFrame.pack();
            return;
        }
        ManageOptions manage_ = manage(linesFiles_, linesFiles_.get(1));
        initEnv(linesFiles_.get(0),manage_);
    }


    public void folder(String _folderName) {
        currentFolder = _folderName;
        createFile.setEnabled(true);
    }
    public void saveConf(String _fileName) {
        execConf = _fileName;
        AbstractProgramInfos frs_ = commonFrame.getFrames();
        StreamTextFile.saveTextFile(getTempDefConf(frs_),buildDefConfFile(),frs_.getStreams());
        ManageOptions opts_ = saveComments(comments);
        initEnv(currentFolder,opts_);
    }

    private void initEnv(String _curFolder,ManageOptions _linesFiles) {
        panel.removeAll();
        panel.add(spinner);
        tabEditor = new TabEditor(this);
        panel.add(tabEditor.getPanel());
        commonFrame.setContentPane(panel);
        commonFrame.pack();
        currentFolder = _curFolder;
        AbstractProgramInfos frs_ = commonFrame.getFrames();
        usedLg = _linesFiles.getLanguage();
        Options opt_ = _linesFiles.getOptions();
        spinner.setValue(opt_.getTabWidth());
        spinnerEvent.stateChanged();
        CustList<CommentDelimiters> comments_ = opt_.getComments();
        CommentsUtil.checkAndUpdateComments(comments_, CustAliases.defComments("", frs_.getTranslations(), frs_.getLanguage()));
        comments = comments_;
    }

    private ManageOptions manage(StringList _linesFiles, String _lg) {
        return new ManageOptions(commonFrame.getFrames().getLanguages(), _lg, _linesFiles, factory, commonFrame.getFrames().getThreadFactory());
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
//    private static StringList retrieveRelativeFiles(Document _doc) {
//        StringList files_ = new StringList();
//        ElementList chs_ = _doc.getDocumentElement().getChildElements();
//        int len_ = chs_.getLength();
//        for (int i = 0; i < len_; i++) {
//            Element c_ = chs_.item(i);
//            files_.add(c_.getTextContent());
//        }
//        return files_;
//    }

    public FileOpenDialogAbs getFileOpenDialogInt() {
        return fileOpenDialogInt;
    }

    public FolderOpenDialogAbs getFolderOpenDialogInt() {
        return folderOpenDialogInt;
    }

    public AbsTextField getSrcFolder() {
        return srcFolder;
    }

    public String getExecConf() {
        return execConf;
    }

    public void afterChangingComments(OutputDialogCommentsResult _res) {
        if (!_res.getValid().get()) {
            return;
        }
        saveComments(_res.getComments());
        DocumentTextChange.updateEditorText(tabEditor);
    }

    public ManageOptions saveComments(CustList<CommentDelimiters> _comm) {
        AbstractProgramInfos frs_ = commonFrame.getFrames();
        CommentsUtil.checkAndUpdateComments(_comm, comments);
        comments = _comm;
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
        StreamFolderFile.makeParent(execConf,commonFrame.getFrames().getFileCoreStream());
        StreamTextFile.saveTextFile(execConf, StringUtil.join(lines_,'\n'),frs_.getStreams());
        return new ManageOptions(commonFrame.getFrames().getLanguages(),commonFrame.getLanguageKey(), lines_,factory,commonFrame.getFrames().getThreadFactory());
    }

    private String buildDefConfFile() {
        return buildDefConfFile(execConf);
    }

    public static String buildDefConfFile(String _conf) {
        Document doc_ = DocumentBuilder.newXmlDocument();
        Element elt_ = doc_.createElement(ROOT_CONF);
        elt_.setAttribute(NODE_PATH,_conf);
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

    public AbsPlainButton getChooseFolder() {
        return chooseFolder;
    }

    public AbsPlainButton getCreateFile() {
        return createFile;
    }

    public String getCurrentFolder() {
        return currentFolder;
    }

    public AbsMenuItem getCommentsMenu() {
        return commentsMenu;
    }

    public AbsDialog getDialogComments() {
        return dialogComments;
    }

    public AbsDialog getDialogNavigLine() {
        return dialogNavigLine;
    }

    public TabEditor getTabEditor() {
        return tabEditor;
    }

    @Override
    public AbsCommonFrame getCommonFrame() {
        return commonFrame;
    }

    public AbsSpinner getSpinner() {
        return spinner;
    }

    public AbsPanel getPanel() {
        return panel;
    }

    @Override
    public String getApplicationName() {
        return "";
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
        ides.removeObj(this);
        GuiBaseUtil.tryExit(commonFrame);
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


    public IdList<WindowCdmEditor> getIdes() {
        return ides;
    }
}
