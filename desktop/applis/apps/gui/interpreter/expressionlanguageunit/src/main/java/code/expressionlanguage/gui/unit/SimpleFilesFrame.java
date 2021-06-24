package code.expressionlanguage.gui.unit;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.filenames.AbstractNameValidating;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.utilcompo.*;
import code.gui.*;
import code.gui.Menu;
import code.gui.MenuBar;
import code.gui.MenuItem;
import code.gui.Panel;
import code.gui.ScrollPane;
import code.gui.TextArea;
import code.gui.TextField;
import code.gui.events.ClosingChildFrameEvent;
import code.scripts.messages.gui.MessCdmUnitGr;
import code.sml.util.ResourcesMessagesUtil;
import code.stream.StreamBinaryFile;
import code.stream.StreamFolderFile;
import code.threads.AbstractThread;
import code.util.StringMap;
import code.util.core.StringUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public final class SimpleFilesFrame extends ChildFrame implements TestableFrame {
    private static final String DIALOG_ACCESS = "unit.simplefilesframe";
    private final Menu menu;
    private final MenuItem open;
    private final MenuItem stop;

    private final Panel contentPane;
    private final Panel form;
    private final Panel subForm;
    private final TextField folderField;
    private final TextField srcField;
    private final TextField filesField;
    private final PlainLabel content;
    private final TextArea conf;
    private final PlainButton launch;
    private final PlainButton launchByFile;
    private final Panel progressing;
    private final PlainLabel doneTests;
    private final PlainLabel doneTestsCount;

    private final PlainLabel method;
    private final PlainLabel currentMethod;
    private final TableGui resultsTable;
    private final TextArea results;
    private final ProgressBar progressBar;
    private final StringMap<String> messages;
    private final MainWindow parent;
    private byte[] confFile;
    private byte[] src;
    private byte[] files;
    private final TextArea errors;
    private final UnitIssuer unitIssuer;
    private final CommonExecution commonExecution;
    private String filePath = "";

    public SimpleFilesFrame(MainWindow _parent, String _title) {
        super(_parent.getLanguageKey(),_parent);
        parent =_parent;
        setAccessFile(DIALOG_ACCESS);
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath("resources_unit/gui/messages", getLanguageKey(), getAccessFile());
        String loadedResourcesMessages_ = MessCdmUnitGr.ms().getVal(fileName_);
        messages = ResourcesMessagesUtil.getMessagesFromContent(loadedResourcesMessages_);
        setDialogIcon(_parent);
        setLocationRelativeTo(_parent);
        setTitle(_title);
        setFocusableWindowState(true);
        setJMenuBar(new MenuBar());
        menu = new Menu(messages.getVal("file"));
        open = new MenuItem(messages.getVal("open"));
        open.addActionListener(new FileSelectEvent(_parent, this));
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
        menu.addMenuItem(open);
        menu.addSeparator();
        stop = new MenuItem(messages.getVal("stop"));
        stop.addActionListener(new StopRunEvent(_parent));
        menu.addMenuItem(stop);
        getJMenuBar().add(menu);
        contentPane = Panel.newPageBox();
        form = Panel.newGrid(0,2);
        content = new PlainLabel(messages.getVal("configuration"));
        form.add(content);
        conf = new TextArea(64,64);
        ScrollPane scr_ = new ScrollPane(conf);
        scr_.setPreferredSize(new Dimension(256,96));
        form.add(scr_);
        Panel formButtons_ = Panel.newGrid(0,2);
        launch = new PlainButton(messages.getVal("launch"));
        launch.addActionListener(new ListenerLaunchTests(_parent, this));
        formButtons_.add(launch);
        launchByFile = new PlainButton(messages.getVal("launchByFile"));
        launchByFile.addActionListener(new ListenerLaunchFileTests(_parent, this));
        formButtons_.add(launchByFile);
        form.add(formButtons_);
        subForm = Panel.newGrid(0,2);
        folderField = new TextField();
        subForm.add(new TextLabel(messages.getVal("folder")));
        subForm.add(folderField);
        srcField = new TextField();
        PlainButton srcButton_ = new PlainButton(messages.getVal("src"));
        srcButton_.addActionListener(new LoadSrcEvent(this));
        subForm.add(srcButton_);
        subForm.add(srcField);
        filesField = new TextField();
        PlainButton filesButton_ = new PlainButton(messages.getVal("files"));
        filesButton_.addActionListener(new LoadFilesEvent(this));
        subForm.add(filesButton_);
        subForm.add(filesField);
        form.add(subForm);
        contentPane.add(form);
        progressing = Panel.newPageBox();
        doneTests = new PlainLabel(messages.getVal("tests"));
        progressing.add(doneTests);
        doneTestsCount = new PlainLabel("");
        progressing.add(doneTestsCount);
        method = new PlainLabel(messages.getVal("method"));
        progressing.add(method);
        currentMethod = new PlainLabel("");
        progressing.add(currentMethod);
        progressBar = new ProgressBar();
        progressing.add(progressBar);
        String[] cols_ = new String[4];
        cols_[0] =messages.getVal("number");
        cols_[1] =messages.getVal("method");
        cols_[2] =messages.getVal("params");
        cols_[3] =messages.getVal("success");
        resultsTable = new TableGui(cols_);
        results = new TextArea(1024,1024);
        ScrollPane scrTable_ = new ScrollPane(resultsTable);
        scrTable_.setPreferredSize(new Dimension(256,96));
        ScrollPane scrRes_ = new ScrollPane(results);
        scrRes_.setPreferredSize(new Dimension(256,96));
        SplitPane splitPane_ = new SplitPane(JSplitPane.HORIZONTAL_SPLIT,scrTable_,scrRes_);
        splitPane_.setOneTouchExpandable(true);
        progressing.add(splitPane_);
        contentPane.add(progressing);
        errors = new TextArea(128,128);
        ScrollPane scrErrs_ = new ScrollPane(errors);
        scrErrs_.setPreferredSize(new Dimension(512,128));
        unitIssuer = new UnitIssuer(errors);
        contentPane.add(scrErrs_);
        setContentPane(contentPane);
        addWindowListener(new ClosingChildFrameEvent(this));
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        pack();
        commonExecution = new CommonExecution(messages,doneTestsCount,currentMethod,resultsTable,results,progressBar);
    }

    @Override
    public AbstractInterceptor getIntercept() {
        return parent.getInterceptor();
    }

    @Override
    public void closeWindow() {
        setVisible(false);
    }

    @Override
    public boolean ok(String _file) {
        AbstractThread th_ = parent.getTh();
        return th_ == null || !th_.isAlive();
    }

    public void src() {
        byte[] read_ = read(srcField);
        if (read_ != null) {
            src = read_;
        }
    }

    public void files() {
        byte[] read_ = read(filesField);
        if (read_ != null) {
            files = read_;
        }
    }
    public byte[] read(TextField _fileField) {
        AbstractThread th_ = parent.getTh();
        if (th_ != null && th_.isAlive()) {
            errors.append(StringUtil.simpleStringsFormat(messages.getVal("failLoadThread"),_fileField.getText()));
            errors.append("\n");
            return null;
        }
        if (StreamFolderFile.isAbsolute(_fileField.getText(), parent.getFileCoreStream())) {
            byte[] files_ = StreamBinaryFile.loadFile(_fileField.getText(), parent.getFileCoreStream(), parent.getStreams());
            if (files_ == null) {
                errors.append(StringUtil.simpleStringsFormat(messages.getVal("failLoadContent"),_fileField.getText()));
                errors.append("\n");
                return null;
            }
            errors.append(StringUtil.simpleStringsFormat(messages.getVal("successLoad"),_fileField.getText()));
            errors.append("\n");
            return files_;
        }
        if (!StreamFolderFile.isAbsolute(folderField.getText(), parent.getFileCoreStream())) {
            errors.append(StringUtil.simpleStringsFormat(messages.getVal("failLoadPath"),_fileField.getText(),folderField.getText()));
            errors.append("\n");
            return null;
        }
        byte[] files_ = StreamBinaryFile.loadFile(StringUtil.replaceBackSlashDot(folderField.getText())+_fileField.getText(), parent.getFileCoreStream(), parent.getStreams());
        if (files_ == null) {
            errors.append(StringUtil.simpleStringsFormat(messages.getVal("failLoadContent"),_fileField.getText()));
            errors.append("\n");
            return null;
        }
        errors.append(StringUtil.simpleStringsFormat(messages.getVal("successLoad"),StringUtil.replaceBackSlashDot(folderField.getText())+_fileField.getText()));
        errors.append("\n");
        return files_;

    }
    @Override
    public String getTxtConf() {

        String trim_ = conf.getText().trim();
        confFile = StringUtil.encode(trim_);
        return trim_;
    }

    public FileInfos getInfos() {
        AbstractNameValidating validator_ = parent.getValidator();
        return FileInfos.buildMemoryFromFile(parent.getGenerator(),
                validator_,unitIssuer, new TechInfos(parent.getThreadFactory(),parent.getStreams()), new MemInputFiles(confFile, src, files));
    }


    public void showProgress(ContextEl _ctx, Struct _infos, Struct _doneTests, Struct _method, Struct _count, LgNamesWithNewAliases _evolved) {
        commonExecution.showProgress(_ctx, _infos, _doneTests, _method, _count, _evolved);
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
        AbstractThread th_ = parent.getTh();
        if (th_ != null && th_.isAlive()) {
            errors.append(StringUtil.simpleStringsFormat(messages.getVal("failLoadThread"),_filePath));
            errors.append("\n");
            return;
        }
        byte[] confFile_ = StreamBinaryFile.loadFile(_filePath, parent.getFileCoreStream(), parent.getStreams());
        if (confFile_ == null) {
            errors.append(StringUtil.simpleStringsFormat(messages.getVal("failLoadContent"),_filePath));
            errors.append("\n");
            return;
        }
        this.filePath = _filePath;
        confFile = confFile_;
        errors.append(StringUtil.simpleStringsFormat(messages.getVal("successLoad"),_filePath));
        errors.append("\n");
    }

}
