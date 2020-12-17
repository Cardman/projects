package code.expressionlanguage.gui.unit;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.filenames.AbstractNameValidating;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.utilcompo.FileInfos;
import code.expressionlanguage.utilcompo.LgNamesWithNewAliases;
import code.expressionlanguage.utilfiles.DefaultResourcesReader;
import code.gui.*;
import code.gui.Menu;
import code.gui.MenuBar;
import code.gui.MenuItem;
import code.gui.Panel;
import code.gui.ScrollPane;
import code.gui.TextArea;
import code.gui.TextField;
import code.gui.events.ClosingChildFrameEvent;
import code.stream.StreamBinaryFile;
import code.stream.StreamFolderFile;
import code.util.StringMap;
import code.util.core.StringUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public final class SimpleFilesFrame extends ChildFrame implements TestableFrame {
    private static final String DIALOG_ACCESS = "unit.simplefilesframe";
    private Menu menu;
    private MenuItem open;
    private MenuItem stop;

    private Panel contentPane;
    private Panel form;
    private Panel subForm;
    private TextField folderField;
    private TextField srcField;
    private TextField filesField;
    private PlainLabel content;
    private TextArea conf;
    private PlainButton launch;
    private PlainButton launchByFile;
    private Panel progressing;
    private PlainLabel doneTests;
    private PlainLabel doneTestsCount;

    private PlainLabel method;
    private PlainLabel currentMethod;
    private TableGui resultsTable;
    private TextArea results;
    private ProgressBar progressBar;
    private final StringMap<String> messages;
    private final MainWindow parent;
    private byte[] confFile;
    private byte[] src;
    private byte[] files;
    private TextArea errors;
    private UnitIssuer unitIssuer;
    private CommonExecution commonExecution;
    private String filePath = "";

    public SimpleFilesFrame(MainWindow _parent, String _title) {
        super(_parent.getLanguageKey());
        parent =_parent;
        setAccessFile(DIALOG_ACCESS);
        messages = getMessages(this, "resources_unit/gui/messages");
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
    public void closeWindow() {
        setVisible(false);
    }

    @Override
    public boolean ok(String _file) {
        Thread th_ = parent.getTh();
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
        Thread th_ = parent.getTh();
        if (th_ != null && th_.isAlive()) {
            errors.append(StringUtil.simpleStringsFormat(messages.getVal("failLoadThread"),_fileField.getText()));
            errors.append("\n");
            return null;
        }
        if (StreamFolderFile.isAbsolute(_fileField.getText())) {
            byte[] files_ = StreamBinaryFile.loadFile(_fileField.getText());
            if (files_ == null) {
                errors.append(StringUtil.simpleStringsFormat(messages.getVal("failLoadContent"),_fileField.getText()));
                errors.append("\n");
                return null;
            }
            errors.append(StringUtil.simpleStringsFormat(messages.getVal("successLoad"),_fileField.getText()));
            errors.append("\n");
            return files_;
        }
        if (!StreamFolderFile.isAbsolute(folderField.getText())) {
            errors.append(StringUtil.simpleStringsFormat(messages.getVal("failLoadPath"),_fileField.getText(),folderField.getText()));
            errors.append("\n");
            return null;
        }
        byte[] files_ = StreamBinaryFile.loadFile(StringUtil.replaceBackSlashDot(folderField.getText())+_fileField.getText());
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
        return FileInfos.buildMemoryFromFile(new DefaultResourcesReader(),parent.getGenerator(),
                confFile, src,files,
                validator_,unitIssuer);
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
        Thread th_ = parent.getTh();
        if (th_ != null && th_.isAlive()) {
            errors.append(StringUtil.simpleStringsFormat(messages.getVal("failLoadThread"),_filePath));
            errors.append("\n");
            return;
        }
        this.filePath = _filePath;
        byte[] confFile_ = StreamBinaryFile.loadFile(_filePath);
        if (confFile_ == null) {
            errors.append(StringUtil.simpleStringsFormat(messages.getVal("failLoadContent"),_filePath));
            errors.append("\n");
            return;
        }
        confFile = confFile_;
        errors.append(StringUtil.simpleStringsFormat(messages.getVal("successLoad"),_filePath));
        errors.append("\n");
    }

}
