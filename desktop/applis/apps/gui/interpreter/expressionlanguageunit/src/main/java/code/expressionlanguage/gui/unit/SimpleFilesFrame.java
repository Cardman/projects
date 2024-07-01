package code.expressionlanguage.gui.unit;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.filenames.AbstractNameValidating;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.utilcompo.*;
import code.gui.*;


import code.gui.events.ClosingChildFrameEvent;
import code.gui.images.MetaDimension;
import code.scripts.messages.gui.MessCdmUnitGr;
import code.sml.util.ResourcesMessagesUtil;
import code.stream.BytesInfo;
import code.stream.PathsUtil;
import code.stream.StreamBinaryFile;
import code.util.StringMap;
import code.util.core.StringUtil;




public final class SimpleFilesFrame extends GroupFrame implements TestableFrame,AbsChildFrame {
    private static final String DIALOG_ACCESS = "unit.simplefilesframe";
    private final EnabledMenu menu;
    private final EnabledMenu open;
    private final EnabledMenu stop;

    private final AbsPanel contentPane;
    private final AbsPanel form;
    private final AbsPanel subForm;
    private final AbsTextField folderField;
    private final AbsTextField srcField;
    private final AbsTextField filesField;
    private final AbsPlainLabel content;
    private final AbsTextArea conf;
    private final AbsButton launch;
    private final AbsButton launchByFile;
    private final AbsPanel progressing;
    private final AbsPlainLabel doneTests;
    private final AbsPlainLabel doneTestsCount;
    private final AbsPlainLabel doneTestsCalls;

    private final AbsPlainLabel method;
    private final AbsPlainLabel currentMethod;
    private final AbsTableGui resultsTable;
    private final AbsTextArea results;
    private final AbsProgressBar progressBar;
    private final StringMap<String> messages;
    private final WindowUnit parent;
    private final AbsButton srcButton;
    private final AbsButton filesButton;
    private byte[] confFile;
    private BytesInfo src = new BytesInfo(new byte[0],true);
    private BytesInfo files = new BytesInfo(new byte[0],true);
    private final AbsTextArea errors;
    private final UnitIssuer unitIssuer;
    private final CommonExecution commonExecution;
    private String filePath = "";

    public SimpleFilesFrame(WindowUnit _parent, String _title) {
        super(_parent.getFrames());
        parent =_parent;
        setAccessFile(DIALOG_ACCESS);
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath("resources_unit/gui/messages", _parent.getFrames().getLanguage(), getAccessFile());
        String loadedResourcesMessages_ = MessCdmUnitGr.ms().getVal(fileName_);
        messages = ResourcesMessagesUtil.getMessagesFromContent(loadedResourcesMessages_);
        setDialogIcon(_parent.getCommonFrame());
        getCommonFrame().setLocationRelativeTo(_parent.getCommonFrame());
        setTitle(_title);
        setFocusableWindowState(true);
        setJMenuBar(parent.getCompoFactory().newMenuBar());
        menu = parent.getCompoFactory().newMenu(messages.getVal("file"));
        open = parent.getCompoFactory().newMenuItem(messages.getVal("open"));
        open.addActionListener(new FileSelectEvent(_parent, this));
        open.setAccelerator(GuiConstants.VK_O, GuiConstants.CTRL_DOWN_MASK);
        menu.addMenuItem(open);
        menu.addMenuItem(getCompoFactory().newSep());
        stop = parent.getCompoFactory().newMenuItem(messages.getVal("stop"));
        stop.addActionListener(new StopRunEvent(_parent));
        menu.addMenuItem(stop);
        getJMenuBar().add(menu);
        contentPane = parent.getCompoFactory().newPageBox();
        form = parent.getCompoFactory().newGrid(0,2);
        content = parent.getCompoFactory().newPlainLabel(messages.getVal("configuration"));
        form.add(content);
        conf = parent.getCompoFactory().newTextArea(64,64);
        AbsScrollPane scr_ = parent.getCompoFactory().newAbsScrollPane(conf);
        scr_.setPreferredSize(new MetaDimension(256,96));
        form.add(scr_);
        AbsPanel formButtons_ = parent.getCompoFactory().newGrid(0,2);
        launch = parent.getCompoFactory().newPlainButton(messages.getVal("launch"));
        launch.addActionListener(new ListenerLaunchTests(_parent, this));
        formButtons_.add(launch);
        launchByFile = parent.getCompoFactory().newPlainButton(messages.getVal("launchByFile"));
        launchByFile.addActionListener(new ListenerLaunchFileTests(_parent, this));
        formButtons_.add(launchByFile);
        form.add(formButtons_);
        subForm = parent.getCompoFactory().newGrid(0,2);
        folderField = parent.getCompoFactory().newTextField();
        subForm.add(parent.getCompoFactory().newPlainLabel(messages.getVal("folder")));
        subForm.add(folderField);
        srcField = parent.getCompoFactory().newTextField();
        srcButton = parent.getCompoFactory().newPlainButton(messages.getVal("src"));
        AbsButton srcButton_ = srcButton;
        srcButton_.addActionListener(new LoadSrcEvent(this));
        subForm.add(srcButton_);
        subForm.add(srcField);
        filesField = parent.getCompoFactory().newTextField();
        filesButton = parent.getCompoFactory().newPlainButton(messages.getVal("files"));
        AbsButton filesButton_ = filesButton;
        filesButton_.addActionListener(new LoadFilesEvent(this));
        subForm.add(filesButton_);
        subForm.add(filesField);
        form.add(subForm);
        contentPane.add(form);
        progressing = parent.getCompoFactory().newPageBox();
        doneTests = parent.getCompoFactory().newPlainLabel(messages.getVal("tests"));
        progressing.add(doneTests);
        doneTestsCount = parent.getCompoFactory().newPlainLabel("");
        progressing.add(doneTestsCount);
        doneTestsCalls = parent.getCompoFactory().newPlainLabel("");
        progressing.add(doneTestsCalls);
        method = parent.getCompoFactory().newPlainLabel(messages.getVal("method"));
        progressing.add(method);
        currentMethod = parent.getCompoFactory().newPlainLabel("");
        progressing.add(currentMethod);
        progressBar = parent.getCompoFactory().newAbsProgressBar();
        progressing.add(progressBar);
        String[] cols_ = new String[4];
        cols_[0] =messages.getVal("number");
        cols_[1] =messages.getVal("method");
        cols_[2] =messages.getVal("params");
        cols_[3] =messages.getVal("success");
        resultsTable = parent.getCompoFactory().newTableGui(cols_);
        results = parent.getCompoFactory().newTextArea(1024,1024);
        AbsScrollPane scrTable_ = parent.getCompoFactory().newAbsScrollPane(resultsTable);
        scrTable_.setPreferredSize(new MetaDimension(256,96));
        AbsScrollPane scrRes_ = parent.getCompoFactory().newAbsScrollPane(results);
        scrRes_.setPreferredSize(new MetaDimension(256,96));
        AbsSplitPane splitPane_ = parent.getCompoFactory().newHorizontalSplitPane(scrTable_,scrRes_);
        splitPane_.setOneTouchExpandable(true);
        progressing.add(splitPane_);
        contentPane.add(progressing);
        errors = parent.getCompoFactory().newTextArea(128,128);
        AbsScrollPane scrErrs_ = parent.getCompoFactory().newAbsScrollPane(errors);
        scrErrs_.setPreferredSize(new MetaDimension(512,128));
        unitIssuer = new UnitIssuer(errors);
        contentPane.add(scrErrs_);
        setContentPane(contentPane);
        addWindowListener(new ClosingChildFrameEvent(this));
//        setDefaultCloseOperation(GuiConstants.DO_NOTHING_ON_CLOSE);
        pack();
        commonExecution = new CommonExecution(new ProgTestBar(messages,doneTestsCalls,doneTestsCount,currentMethod,resultsTable,results,progressBar));
    }

    @Override
    public CdmFactory getFactory() {
        return parent.getFactory();
    }

    @Override
    public void closeWindow() {
        setVisible(false);
        parent.getSimpleFrame().setEnabled(true);
    }
    public void setDialogIcon(AbsCommonFrame _group) {
        setIconImage(_group.getImageIconFrame());
        setImageIconFrame(_group.getImageIconFrame());
    }
//    @Override
//    public boolean ok(String _file) {
//        AbstractThread th_ = parent.getTh();
//        return th_ == null || !th_.isAlive();
//    }

    public void src() {
        BytesInfo read_ = read(srcField);
        if (!read_.isNul()) {
            src = read_;
        }
    }

    public void files() {
        BytesInfo read_ = read(filesField);
        if (!read_.isNul()) {
            files = read_;
        }
    }
    public BytesInfo read(AbsTextField _fileField) {
//        AbstractThread th_ = parent.getTh();
//        if (th_ != null && th_.isAlive()) {
//            errors.append(StringUtil.simpleStringsFormat(messages.getVal("failLoadThread"),_fileField.getText()));
//            errors.append("\n");
//            return new BytesInfo(new byte[0],true);
//        }
        if (PathsUtil.isAbsolute(_fileField.getText(), parent.getFileCoreStream())) {
            return readFile(_fileField.getText());
//            BytesInfo files_ = StreamBinaryFile.loadFile(_fileField.getText(), parent.getStreams());
//            if (files_.isNul()) {
//                errors.append(StringUtil.simpleStringsFormat(messages.getVal("failLoadContent"),_fileField.getText()));
//                errors.append("\n");
//                return new BytesInfo(new byte[0],true);
//            }
//            errors.append(StringUtil.simpleStringsFormat(messages.getVal("successLoad"),_fileField.getText()));
//            errors.append("\n");
//            return files_;
        }
        if (!PathsUtil.isAbsolute(folderField.getText(), parent.getFileCoreStream())) {
            errors.append(StringUtil.simpleStringsFormat(messages.getVal("failLoadPath"),_fileField.getText(),folderField.getText()));
            errors.append("\n");
            return new BytesInfo(new byte[0],true);
        }
        return readFile(StringUtil.replaceBackSlashDot(folderField.getText())+_fileField.getText());
//        BytesInfo files_ = StreamBinaryFile.loadFile(StringUtil.replaceBackSlashDot(folderField.getText())+_fileField.getText(), parent.getStreams());
//        if (files_.isNul()) {
//            errors.append(StringUtil.simpleStringsFormat(messages.getVal("failLoadContent"),StringUtil.replaceBackSlashDot(folderField.getText())+_fileField.getText()));
//            errors.append("\n");
//            return new BytesInfo(new byte[0],true);
//        }
//        errors.append(StringUtil.simpleStringsFormat(messages.getVal("successLoad"),StringUtil.replaceBackSlashDot(folderField.getText())+_fileField.getText()));
//        errors.append("\n");
//        return files_;

    }
    @Override
    public String getTxtConf() {

        String trim_ = conf.getText().trim();
        confFile = StringUtil.encode(trim_);
        return trim_;
    }

    public FileInfos getInfos() {
        AbstractNameValidating validator_ = parent.getValidator();
        return FileInfos.buildMemoryFromFile(getFactory().getProgramInfos(),parent.getGenerator(),
                validator_,unitIssuer, new MemInputFiles(confFile, src, files), parent.getFrames().getZipFact(), parent.getThreadFactory());
    }

//    @Override
//    public AbstractThreadFactory getThreadFactory() {
//        return parent.getThreadFactory();
//    }

    public void showProgress(ContextEl _ctx, Struct _infos, LgNamesWithNewAliases _evolved) {
        commonExecution.showProgress(_ctx, _infos, _evolved);
    }
    public void finish(Struct _infos, LgNamesWithNewAliases _evolved) {
        commonExecution.finish(_infos, _evolved);
    }

    public void setResults(ContextEl _ctx, Argument _res, LgNamesWithNewAliases _evolved) {
        commonExecution.setResults(_ctx, _res, _evolved);
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String _filePath) {
//        AbstractThread th_ = parent.getTh();
//        if (th_ != null && th_.isAlive()) {
//            errors.append(StringUtil.simpleStringsFormat(messages.getVal("failLoadThread"),_filePath));
//            errors.append("\n");
//            return;
//        }
        BytesInfo read_ = readFile(_filePath);
        if (!read_.isNul()) {
            this.filePath = _filePath;
            confFile = read_.getBytes();
        }
//        BytesInfo confFile_ = StreamBinaryFile.loadFile(_filePath, parent.getStreams());
//        if (confFile_.isNul()) {
//            errors.append(StringUtil.simpleStringsFormat(messages.getVal("failLoadContent"),_filePath));
//            errors.append("\n");
//            return;
//        }
//        this.filePath = _filePath;
//        confFile = confFile_.getBytes();
//        errors.append(StringUtil.simpleStringsFormat(messages.getVal("successLoad"),_filePath));
//        errors.append("\n");
    }

    public BytesInfo readFile(String _filePath) {
        BytesInfo confFile_ = StreamBinaryFile.loadFile(_filePath, parent.getStreams());
        if (confFile_.isNul()) {
            errors.append(StringUtil.simpleStringsFormat(messages.getVal("failLoadContent"),_filePath));
            errors.append("\n");
            return new BytesInfo(new byte[0],true);
        }
        errors.append(StringUtil.simpleStringsFormat(messages.getVal("successLoad"),_filePath));
        errors.append("\n");
        return confFile_;
    }

    public EnabledMenu getOpen() {
        return open;
    }

    public EnabledMenu getStop() {
        return stop;
    }

    public AbsTextField getFolderField() {
        return folderField;
    }

    public AbsTextField getSrcField() {
        return srcField;
    }

    public AbsTextField getFilesField() {
        return filesField;
    }

    public AbsTextArea getConf() {
        return conf;
    }

    public AbsButton getLaunch() {
        return launch;
    }

    public AbsButton getSrcButton() {
        return srcButton;
    }

    public AbsButton getFilesButton() {
        return filesButton;
    }

    public AbsButton getLaunchByFile() {
        return launchByFile;
    }

    public AbsTableGui getResultsTable() {
        return resultsTable;
    }
}
