package code.expressionlanguage.gui.unit;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.filenames.AbstractNameValidating;
import code.expressionlanguage.utilcompo.*;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.utilfiles.DefaultFileSystem;
import code.expressionlanguage.utilfiles.DefaultLogger;
import code.expressionlanguage.utilfiles.DefaultReporter;
import code.expressionlanguage.utilimpl.RunningTest;
import code.gui.*;


import code.gui.AbsMenuItem;


import code.gui.events.QuittingEvent;
import code.gui.initialize.AbstractProgramInfos;
import code.scripts.messages.gui.MessCdmUnitGr;
import code.sml.util.ResourcesMessagesUtil;
import code.threads.AbstractThread;
import code.util.StringMap;
import code.util.core.DefaultUniformingString;
import code.util.ints.UniformingString;

import javax.swing.WindowConstants;
import java.awt.Dimension;

public final class WindowUnit extends GroupFrame implements TestableFrame {
    private final AbsMenu menu;
    private final AbsMenuItem open;
    private final AbsCheckBoxMenuItem logErr;
    private final AbsMenuItem simpleFrame;
    private final AbsMenuItem stop;
    private final AbsCheckBoxMenuItem memory;

    private final AbsPanel contentPane;
    private final AbsPanel form;
    private final AbsPlainLabel content;
    private final AbsTextArea conf;
    private final AbsPlainButton launch;
    private final AbsPanel progressing;
    private final AbsPlainLabel doneTests;
    private final AbsPlainLabel doneTestsCount;

    private final AbsPlainLabel method;
    private final AbsPlainLabel currentMethod;
    private final AbsTableGui resultsTable;
    private final AbsTextArea results;
    private final AbsProgressBar progressBar;

    private RunningTest running;
    private AbstractThread th;
    private final StringMap<String> unitMessages;
    private final UniformingString uniformingString = new DefaultUniformingString();
    private final AbsTextArea errors = getCompoFactory().newTextArea();
    private final AbsScrollPane errorsScroll = getCompoFactory().newAbsScrollPane(errors);
    private final UnitIssuer unitIssuer = new UnitIssuer(errors);
    private final SimpleFilesFrame filesFrame;
    private final CommonExecution commonExecution;

    protected WindowUnit(String _lg, AbstractProgramInfos _list) {
        super(_lg, _list);
        setAccessFile("unit.mainwindow");
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath("resources_unit/gui/messages", getLanguageKey(), getAccessFile());
        String loadedResourcesMessages_ = MessCdmUnitGr.ms().getVal(fileName_);
        unitMessages = ResourcesMessagesUtil.getMessagesFromContent(loadedResourcesMessages_);
        setTitle(unitMessages.getVal("title"));
        setJMenuBar(getCompoFactory().newMenuBar());
        menu = getCompoFactory().newMenu(unitMessages.getVal("file"));
        open = getCompoFactory().newMenuItem(unitMessages.getVal("open"));
        open.addActionListener(new FileOpenEventUnit(this, this));
        open.setAccelerator(GuiConstants.VK_O, GuiConstants.CTRL_DOWN_MASK);
        menu.addMenuItem(open);
        logErr = getCompoFactory().newCheckBoxMenuItem(unitMessages.getVal("status"));
        logErr.addActionListener(new LogErrEvent(this));
        menu.addMenuItem(logErr);
        memory = getCompoFactory().newCheckBoxMenuItem(unitMessages.getVal("memory"));
        menu.addMenuItem(memory);
        menu.addSeparator();
        simpleFrame = getCompoFactory().newMenuItem(unitMessages.getVal("archive"));
        simpleFrame.addActionListener(new SimpleFilesFrameOpen(this));
        menu.addMenuItem(simpleFrame);
        menu.addSeparator();
        stop = getCompoFactory().newMenuItem(unitMessages.getVal("stop"));
        stop.addActionListener(new StopRunEvent(this));
        menu.addMenuItem(stop);
        getJMenuBar().add(menu);
        contentPane = getCompoFactory().newPageBox();
        form = getCompoFactory().newGrid(0,2);
        content = getCompoFactory().newPlainLabel(unitMessages.getVal("configuration"));
        form.add(content);
        conf = getCompoFactory().newTextArea(64,64);
        AbsScrollPane scr_ = getCompoFactory().newAbsScrollPane(conf);
        scr_.setPreferredSize(new Dimension(256,96));
        form.add(scr_);
        launch = getCompoFactory().newPlainButton(unitMessages.getVal("launch"));
        launch.addActionListener(new ListenerLaunchTests(this, this));
        form.add(launch);
        form.add(getCompoFactory().newPlainLabel(""));
        contentPane.add(form);
        progressing = getCompoFactory().newPageBox();
        doneTests = getCompoFactory().newPlainLabel(unitMessages.getVal("tests"));
        progressing.add(doneTests);
        doneTestsCount = getCompoFactory().newPlainLabel("");
        progressing.add(doneTestsCount);
        method = getCompoFactory().newPlainLabel(unitMessages.getVal("method"));
        progressing.add(method);
        currentMethod = getCompoFactory().newPlainLabel("");
        progressing.add(currentMethod);
        progressBar = getCompoFactory().newAbsProgressBar();
        progressing.add(progressBar);
        String[] cols_ = new String[4];
        cols_[0] = unitMessages.getVal("number");
        cols_[1] = unitMessages.getVal("method");
        cols_[2] = unitMessages.getVal("params");
        cols_[3] = unitMessages.getVal("success");
        resultsTable = getCompoFactory().newTableGui(cols_);
        results = getCompoFactory().newTextArea(1024,1024);
        AbsScrollPane scrTable_ = getCompoFactory().newAbsScrollPane(resultsTable);
        scrTable_.setPreferredSize(new Dimension(256,96));
        AbsScrollPane scrRes_ = getCompoFactory().newAbsScrollPane(results);
        scrRes_.setPreferredSize(new Dimension(256,96));
        AbsSplitPane splitPane_ = getCompoFactory().newAbsSplitPane(GuiConstants.HORIZONTAL_SPLIT,scrTable_,scrRes_);
        splitPane_.setOneTouchExpandable(true);
        progressing.add(splitPane_);
        contentPane.add(progressing);
        errorsScroll.setPreferredSize(new Dimension(512,128));
        errorsScroll.setVisible(false);
        contentPane.add(errorsScroll);
        setContentPane(contentPane);
        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new QuittingEvent(this));
        filesFrame = new SimpleFilesFrame(this,getTitle());
        commonExecution = new CommonExecution(unitMessages,doneTestsCount,currentMethod,resultsTable,results,progressBar);
    }

    @Override
    public AbstractInterceptor getIntercept() {
        return getInterceptor();
    }

    @Override
    public void dispose() {
        basicDispose();
    }

    public void stop() {
        if (running != null) {
            ProgressingTests progressingTests_ = running.getProgressingTests();
            if (progressingTests_ != null) {
                ExecutingOptions exec_ = progressingTests_.getExec();
                if (exec_ != null) {
                    exec_.getInterrupt().set(true);
                }
            }
        }
    }
    @Override
    public void quit() {
        if (th != null) {
            stop();
            th.join();
        }
        basicDispose();
    }

    @Override
    public String getApplicationName() {
        return LaunchingAppUnitTests.getMainWindowClass();
    }

    @Override
    public boolean canChangeLanguage() {
        return false;
    }

    @Override
    public void changeLanguage(String _language) {
        //
    }

    public void process(TestableFrame _mainWindow) {
        if (!_mainWindow.ok("")) {
            return;
        }
        String txt_ = _mainWindow.getTxtConf();
        RunningTest r_ = RunningTest.newFromContent(txt_, new ProgressingTestsImpl(_mainWindow,getStreams(),getFileCoreStream()),
                _mainWindow.getInfos());
        running = r_;
        AbstractThread th_ = getThreadFactory().newThread(r_);
        th = th_;
        th_.start();
    }
    public void open() {
        if (!filesFrame.isVisible()) {
            filesFrame.setVisible(true);
        }
    }
    public void selectFile(TestableFrame _mainWindow) {
        String fichier_ = selectedFile();
        if (fichier_.isEmpty()) {
            return;
        }
        launchFileConf(fichier_, _mainWindow);
    }

    public String selectedFile() {
        FileOpenDialog.setFileOpenDialog(this,getLanguageKey(),true, "", getFrames().getHomePath(),"jre");
        String fichier_=FileOpenDialog.getStaticSelectedPath(getFileOpenDialog());
        if (fichier_ == null) {
            fichier_ = "";
        }
        return fichier_;
    }

    public void launchFileConf(String _fichier, TestableFrame _mainWindow) {
        if (!_mainWindow.ok(_fichier)) {
            return;
        }
        RunningTest r_ = RunningTest.newFromFile(_fichier, new ProgressingTestsImpl(_mainWindow,getStreams(),getFileCoreStream()),
                _mainWindow.getInfos());
        running = r_;
        AbstractThread th_ = getThreadFactory().newThread(r_);
        th = th_;
        th_.start();
    }

    @Override
    public String getTxtConf() {
        return conf.getText().trim();
    }

    public FileInfos getInfos() {
        AbstractNameValidating validator_ = getValidator();
        return new FileInfos(buildLogger(validator_),
                buildSystem(validator_), new DefaultReporter(validator_, uniformingString, memory.isSelected(),new TechInfos(getThreadFactory(),getStreams()),getFileCoreStream()), getGenerator(), getStreams().getZipFact(), getThreadFactory());
    }

    @Override
    public boolean ok(String _file) {
        return th == null || !th.isAlive();
    }

    public AbstractThread getTh() {
        return th;
    }

    public void logErr() {
        errorsScroll.setVisible(!errorsScroll.isVisible());
        contentPane.repaintChildren(getImageFactory());
    }
    private AbstractLogger buildLogger(AbstractNameValidating _validator) {
        if (memory.isSelected()) {
            return new MemoryLogger(_validator, unitIssuer,getThreadFactory());
        }
        return new DefaultLogger(_validator, unitIssuer,getFileCoreStream(),getStreams());
    }

    private AbstractFileSystem buildSystem(AbstractNameValidating _validator) {
        if (memory.isSelected()) {
            return new MemoryFileSystem(uniformingString,_validator,getThreadFactory());
        }
        return new DefaultFileSystem(uniformingString, _validator,getFileCoreStream(),getStreams());
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
