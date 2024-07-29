package code.expressionlanguage.gui.unit;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.filenames.AbstractNameValidating;
import code.expressionlanguage.options.DefBuildLightResultContextNext;
import code.expressionlanguage.utilcompo.*;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.utilfiles.DefaultFileSystem;
import code.expressionlanguage.utilfiles.DefaultLogger;
import code.expressionlanguage.utilfiles.DefaultReporter;
import code.expressionlanguage.utilimpl.DefFileBuilderListGene;
import code.expressionlanguage.utilimpl.RunningTest;
import code.gui.*;


import code.gui.EnabledMenu;


import code.gui.events.*;
import code.gui.files.DefButtonsOpenPanelAct;
import code.gui.files.FileOpenFrame;
import code.gui.images.MetaDimension;
import code.gui.initialize.*;
import code.scripts.messages.gui.MessCdmUnitGr;
import code.sml.util.ResourcesMessagesUtil;
import code.threads.AbstractAtomicBoolean;
import code.threads.AbstractBaseExecutorService;
import code.threads.AbstractFuture;
import code.util.StringMap;
import code.util.core.DefaultUniformingString;
import code.util.ints.UniformingString;




public final class WindowUnit extends GroupFrame implements TestableFrame,AbsOpenQuit {
    public static final String APPS_UNIT = "ug";
    private final EnabledMenu menu;
    private final EnabledMenu open;
    private final EnabledMenu logErr;
    private final EnabledMenu simpleFrame;
    private final EnabledMenu stop;
    private final EnabledMenu memory;

    private final AbsPanel contentPane;
    private final AbsPanel form;
    private final AbsPlainLabel content;
    private final AbsTextArea conf;
    private final AbsButton launch;
    private final AbsPanel progressing;
    private final AbsPlainLabel doneTests;
    private final AbsPlainLabel doneTestsCount;
    private final AbsPlainLabel doneTestsCalls;

    private final AbsPlainLabel method;
    private final AbsPlainLabel currentMethod;
    private final AbsTableGui resultsTable;
    private final AbsTextArea results;
    private final AbsProgressBar progressBar;
    private final CdmFactory interceptor;
    private final AbstractBaseExecutorService exec;

    private RunningTest running;
    private AbstractFuture th;
    private final StringMap<String> unitMessages;
    private final UniformingString uniformingString = new DefaultUniformingString();
    private final AbsTextArea errors = getCompoFactory().newTextArea();
    private final AbsScrollPane errorsScroll = getCompoFactory().newAbsScrollPane(errors);
    private final UnitIssuer unitIssuer = new UnitIssuer(errors);
    private final SimpleFilesFrame filesFrame;
    private final CommonExecution commonExecution;
    private final AbstractAtomicBoolean atomicBoolean;
    private final FileOpenFrame fileOpenFrame;
    private final AbsButton mainButton;

    public WindowUnit(String _lg, CdmFactory _list, AbstractProgramInfos _programInfos, LanguagesButtonsPair _pair) {
        super(_programInfos);
        mainButton = _pair.getMainButton();
        atomicBoolean = _programInfos.getThreadFactory().newAtomicBoolean();
        fileOpenFrame = new FileOpenFrame(_programInfos,atomicBoolean);
        GuiBaseUtil.choose(_lg, this, _programInfos.getCommon());
        exec = _programInfos.getThreadFactory().newExecutorService();
        interceptor = _list;
        setAccessFile("unit.mainwindow");
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath("resources_unit/gui/messages", _programInfos.getLanguage(), getAccessFile());
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
        menu.addMenuItem(getCompoFactory().newSep());
        simpleFrame = getCompoFactory().newMenuItem(unitMessages.getVal("archive"));
        simpleFrame.addActionListener(new SimpleFilesFrameOpen(this));
        menu.addMenuItem(simpleFrame);
        menu.addMenuItem(getCompoFactory().newSep());
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
        scr_.setPreferredSize(new MetaDimension(256,96));
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
        doneTestsCalls = getCompoFactory().newPlainLabel("");
        progressing.add(doneTestsCalls);
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
        scrTable_.setPreferredSize(new MetaDimension(256,96));
        AbsScrollPane scrRes_ = getCompoFactory().newAbsScrollPane(results);
        scrRes_.setPreferredSize(new MetaDimension(256,96));
        AbsSplitPane splitPane_ = getCompoFactory().newHorizontalSplitPane(scrTable_,scrRes_);
        splitPane_.setOneTouchExpandable(true);
        progressing.add(splitPane_);
        contentPane.add(progressing);
        errorsScroll.setPreferredSize(new MetaDimension(512,128));
        errorsScroll.setVisible(false);
        contentPane.add(errorsScroll);
        setContentPane(contentPane);
        pack();
        setVisible(true);
//        exitMode(_list);
//        setDefaultCloseOperation(GuiConstants.EXIT_ON_CLOSE);
        addWindowListener(new QuittingEvent(this));
        filesFrame = new SimpleFilesFrame(this,getTitle());
        commonExecution = new CommonExecution(new ProgTestBar(unitMessages,doneTestsCalls,doneTestsCount,currentMethod,resultsTable,results,progressBar));
    }

    @Override
    public CdmFactory getFactory() {
        return interceptor;
    }

//    @Override
//    public void dispose() {
//        GuiBaseUtil.trEx(this);
//    }

    public void stop() {
        if (running != null) {
            ProgressingTests progressingTests_ = running.getProgressingTests();
//            if (progressingTests_ != null) {
                AbstractAtomicBoolean exec_ = progressingTests_.getStop();
                if (exec_ != null) {
                    exec_.set(true);
                }
                InterruptibleContextEl c_ = progressingTests_.ctx();
                if (c_ != null) {
                    c_.getInterrupt().set(true);
                    c_.stopJoinSleep();
                }
//            }
        }
    }
    @Override
    public void quit() {
        getCommonFrame().setVisible(false);
        if (th != null) {
            stop();
        }
        filesFrame.setVisible(false);
        simpleFrame.setEnabled(true);
        LanguageDialogButtons.enable(mainButton,true);
        GuiBaseUtil.trEx(this);
    }

    @Override
    public String getApplicationName() {
        return APPS_UNIT;
    }

//    @Override
//    public boolean canChangeLanguage() {
//        return false;
//    }

    @Override
    public void changeLanguage(String _language) {
        getFrames().setLanguage(_language);
    }

    public void process(TestableFrame _mainWindow) {
//        if (!_mainWindow.ok("")) {
//            return;
//        }
        String txt_ = _mainWindow.getTxtConf();
        RunningTest r_ = RunningTest.newFromContent(getFrames().getLanguages(),txt_, new ProgressingTestsImpl(_mainWindow,getStreams(),getFileCoreStream()),
                _mainWindow.getInfos(), new DefBuildLightResultContextNext(),new DefFileBuilderListGene());
        running = r_;
        th = exec.submitLater(r_);
    }
    public void open() {
        filesFrame .setVisible(true);
        simpleFrame.setEnabled(false);
    }
    public void selectFile(TestableFrame _mainWindow) {
        getAtomicBoolean().set(true);
        FileOpenFrame.setFileSaveDialogByFrame(true,getFrames().getHomePath(),getFileOpenFrame(),new DefButtonsOpenPanelAct(new UnitContinueOpenFile(this,_mainWindow)));

//        String fichier_ = selectedFile();
//        if (fichier_.isEmpty()) {
//            return;
//        }
//        launchFileConf(fichier_, _mainWindow);
    }

//    public String selectedFile() {
//        return StringUtil.nullToEmpty(getFileOpenDialogInt().input(getCommonFrame(), true, "", getFrames().getHomePath()));
//    }

    public void launchFileConf(String _fichier, TestableFrame _mainWindow) {
//        if (!_mainWindow.ok(_fichier)) {
//            return;
//        }
        RunningTest r_ = RunningTest.newFromFile(getFrames().getLanguages(),_fichier, new ProgressingTestsImpl(_mainWindow,getStreams(),getFileCoreStream()),
                _mainWindow.getInfos(), new DefBuildLightResultContextNext(),new DefFileBuilderListGene());
        running = r_;
        th = exec.submitLater(r_);
    }

    @Override
    public String getTxtConf() {
        return conf.getText().trim();
    }

    public FileInfos getInfos() {
        AbstractNameValidating validator_ = getValidator();
        return new FileInfos(buildLogger(),
                buildSystem(validator_), new DefaultReporter(getFactory().getProgramInfos(),validator_, uniformingString, memory.isSelected(),new TechInfos(getThreadFactory(),getStreams()),getFileCoreStream()), getGenerator(), getStreams().getZipFact(), getThreadFactory());
    }

//    @Override
//    public boolean ok(String _file) {
//        return th == null || !th.isAlive();
//    }

//    public AbstractThread getTh() {
//        return th;
//    }

    public void logErr() {
        errorsScroll.setVisible(!errorsScroll.isVisible());
//        contentPane.repaintChildren(getImageFactory());
    }
    private AbstractLogger buildLogger() {
        if (memory.isSelected()) {
            return new MemoryLogger(unitIssuer,getThreadFactory());
        }
        return new DefaultLogger(unitIssuer,getFileCoreStream(),getStreams());
    }

    private AbstractFileSystem buildSystem(AbstractNameValidating _validator) {
        if (memory.isSelected()) {
            return new MemoryFileSystem(_validator,getThreadFactory());
        }
        return new DefaultFileSystem(uniformingString, _validator,getFileCoreStream(),getStreams());
    }

    public void showProgress(ContextEl _ctx, Struct _infos, LgNamesWithNewAliases _evolved) {
        commonExecution.showProgress(_ctx, _infos, _evolved);
    }
    public void finish(Struct _infos, LgNamesWithNewAliases _evolved) {
        commonExecution.finish(_infos, _evolved);
    }

    public void setResults(ContextEl _ctx, Argument _res, LgNamesWithNewAliases _evolved) {
        commonExecution.setResults(_ctx, _res, _evolved);
    }

    public AbstractAtomicBoolean getAtomicBoolean() {
        return atomicBoolean;
    }

    public FileOpenFrame getFileOpenFrame() {
        return fileOpenFrame;
    }

    public SimpleFilesFrame getFilesFrame() {
        return filesFrame;
    }

    public EnabledMenu getSimpleFrame() {
        return simpleFrame;
    }

    public EnabledMenu getOpen() {
        return open;
    }

    public EnabledMenu getLogErr() {
        return logErr;
    }

    public EnabledMenu getStop() {
        return stop;
    }

    public EnabledMenu getMemory() {
        return memory;
    }

    public AbsTextArea getConf() {
        return conf;
    }

    public AbsButton getLaunch() {
        return launch;
    }

    public AbsTableGui getResultsTable() {
        return resultsTable;
    }

//    public CommonExecution getCommonExecution() {
//        return commonExecution;
//    }

    public AbstractBaseExecutorService getExec() {
        return exec;
    }
}
