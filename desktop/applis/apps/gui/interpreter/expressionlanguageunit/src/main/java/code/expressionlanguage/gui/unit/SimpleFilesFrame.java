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
    private TextField srcField;
    private TextField filesField;
    private PlainLabel content;
    private TextArea conf;
    private PlainButton launch;
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
        open.addActionListener(new FileOpenEvent(_parent, this));
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
        launch = new PlainButton(messages.getVal("launch"));
        launch.addActionListener(new ListenerLaunchTests(_parent, this));
        form.add(launch);
        subForm = Panel.newGrid(0,2);
        srcField = new TextField();
        subForm.add(new TextLabel(messages.getVal("src")));
        subForm.add(srcField);
        filesField = new TextField();
        subForm.add(new TextLabel(messages.getVal("files")));
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
        if (th_ != null && th_.isAlive()) {
            return false;
        }
        byte[] src_ = StreamBinaryFile.loadFile(srcField.getText());
        byte[] files_ = StreamBinaryFile.loadFile(filesField.getText());
        byte[] conf_;
        if (!_file.isEmpty()) {
            conf_ = StreamBinaryFile.loadFile(_file);
        } else {
            String txt_ = conf.getText().trim();
            conf_ = StringUtil.encode(txt_);
        }
        if (src_ == null) {
            return false;
        }
        if (files_ == null) {
            return false;
        }
        if (conf_ == null) {
            return false;
        }
        confFile = conf_;
        src = src_;
        files = files_;
        return true;
    }

    @Override
    public String getTxtConf() {
        return conf.getText().trim();
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
}
