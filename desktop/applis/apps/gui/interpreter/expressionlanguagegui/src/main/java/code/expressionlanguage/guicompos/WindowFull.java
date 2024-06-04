package code.expressionlanguage.guicompos;

import code.gui.*;


import code.gui.EnabledMenu;


import code.gui.events.*;
import code.gui.files.DefButtonsOpenPanelAct;
import code.gui.files.FileOpenFrame;
import code.gui.images.MetaDimension;
import code.gui.initialize.*;
import code.scripts.messages.gui.MessCdmGuiGr;
import code.sml.util.ResourcesMessagesUtil;
import code.stream.StreamTextFile;
import code.threads.AbstractAtomicBoolean;
import code.util.StringMap;


public final class WindowFull extends GroupFrame implements AbsOpenQuit{
    public static final String APPS_LAUNCHER = "launcher";
    private final EnabledMenu menu;
    private final EnabledMenu open;

    private final AbsPanel contentPane;
    private final AbsPanel form;
    private final AbsPlainLabel content;
    private final AbsTextArea conf;
    private final AbsButton launch;
    private final AbsButton coverage;
    private final AbsButton stop;

    private final StringMap<String> messages;
    private final CdmFactory cdmFactory;
//    private final GuiInterpreterElements currentElements;
    private AbstractLightProgramInfos light;
    private GuiContextEl context;
    private final AbstractAtomicBoolean atomicBoolean;
    private final FileOpenFrame fileOpenFrame;

    public WindowFull(String _lg, CdmFactory _list, AbstractProgramInfos _programInfos) {
        super(_programInfos);
        atomicBoolean = _programInfos.getThreadFactory().newAtomicBoolean();
        fileOpenFrame = new FileOpenFrame(_programInfos,atomicBoolean);
        GuiBaseUtil.choose(_lg, this, _programInfos.getCommon());
        cdmFactory = _list;
//        currentElements = new GuiInterpreterElements(getFrames());
        setAccessFile("launcher.mainwindow");
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath("resources_lg_gui/gui/messages", _programInfos.getLanguage(), getAccessFile());
        String loadedResourcesMessages_ = MessCdmGuiGr.ms().getVal(fileName_);
        messages = ResourcesMessagesUtil.getMessagesFromContent(loadedResourcesMessages_);
        setTitle(messages.getVal("title"));
        setJMenuBar(getCompoFactory().newMenuBar());
        menu = getCompoFactory().newMenu(messages.getVal("file"));
        open = getCompoFactory().newMenuItem(messages.getVal("open"));
        open.addActionListener(new FileOpenEventFull(this));
        open.setAccelerator(GuiConstants.VK_O, GuiConstants.CTRL_DOWN_MASK);
        menu.addMenuItem(open);
        getJMenuBar().add(menu);
        contentPane = getCompoFactory().newPageBox();
        form = getCompoFactory().newGrid(0,2);
        content = getCompoFactory().newPlainLabel(messages.getVal("configuration"));
        form.add(content);
        conf = getCompoFactory().newTextArea(64,64);
        AbsScrollPane scr_ = getCompoFactory().newAbsScrollPane(conf);
        scr_.setPreferredSize(new MetaDimension(256,96));
        form.add(scr_);
        launch = getCompoFactory().newPlainButton(messages.getVal("launch"));
        launch.addActionListener(new ListenerLaunchApp(this));
        form.add(launch);
        coverage = getCompoFactory().newPlainButton("coverage");
        coverage.addActionListener(new CoverageAction(this));
        coverage.setEnabled(false);
        form.add(coverage);
        stop = getCompoFactory().newPlainButton("stop");
        stop.addActionListener(new StopAction(this));
        stop.setEnabled(false);
        form.add(stop);
        form.add(getCompoFactory().newPlainLabel(""));
        contentPane.add(form);
        setContentPane(contentPane);
        pack();
        setVisible(true);
//        exitMode(_list);
//        setDefaultCloseOperation(GuiConstants.EXIT_ON_CLOSE);
        addWindowListener(new QuittingEvent(this));
    }
    public void selectFile() {
        getAtomicBoolean().set(true);
        FileOpenFrame.setFileSaveDialogByFrame(true,getFrames().getHomePath(),getFileOpenFrame(),new DefButtonsOpenPanelAct(new FullAppContinueFile(this)));
//        String fichier_= StringUtil.nullToEmpty(getFileOpenDialogInt().input(getCommonFrame(), true, "", getFrames().getHomePath()));
//        if (fichier_.isEmpty()) {
//            return;
//        }
//        launchFileConf(fichier_,true);
    }

    public AbstractAtomicBoolean getAtomicBoolean() {
        return atomicBoolean;
    }

    public FileOpenFrame getFileOpenFrame() {
        return fileOpenFrame;
    }

//    @Override
    public void dispose() {
        GuiBaseUtil.trEx(this);
    }

    public void process() {
        if (light != null) {
            return;
        }
        String txt_ = conf.getText().trim();
        GuiRunnable current_ = GuiProcess.build("", txt_, cdmFactory, getFrames());
//        currentElements.setGuiRunnable(current_);
        if (current_ == null) {
            stop.setEnabled(true);
            return;
        }
        light = getFrames();
        context = current_.getContext();
        ((LgNamesGui) current_.getContext().getStandards()).getGuiExecutingBlocks().setStop(stop);
        current_.run();
        coverage.setEnabled(true);
    }
    public void launchFileConf(String _fichier, boolean _direct) {
        if (light != null) {
            return;
        }
        String content_ = StreamTextFile.contentsOfFile(_fichier,getFileCoreStream(),getStreams());
        if (content_ == null) {
            return;
        }
        GuiRunnable current_ = GuiProcess.build(_fichier, content_, cdmFactory, getFrames());
//        currentElements.setGuiRunnable(current_);
        if (current_ == null) {
            stop.setEnabled(true);
            return;
        }
        light = getFrames();
        context = current_.getContext();
        ((LgNamesGui) current_.getContext().getStandards()).getGuiExecutingBlocks().setStop(stop);
        if (_direct) {
            current_.run();
        } else {
            getFrames().getCompoFactory().invokeNow(current_);
        }
    }

    @Override
    public void quit() {
        if (context != null) {
            context.interrupt();
        }
        coverage.setEnabled(false);
        stop.setEnabled(false);
        light = null;
        GuiBaseUtil.trEx(this);
    }

    @Override
    public String getApplicationName() {
        return APPS_LAUNCHER;
    }

//    @Override
//    public boolean canChangeLanguage() {
//        return false;
//    }

    @Override
    public void changeLanguage(String _language) {
        setLanguageKey(_language);
    }

//    public GuiInterpreterElements getCurrentElements() {
//        return currentElements;
//    }

    public void coverage() {
        new CoveringCodeTask(context, context.getExecutingOptions()).run();
    }

    public void stopAction() {
        coverage.setEnabled(false);
        stop.setEnabled(false);
        light = null;
//        ((LgNamesGui) context.getStandards()).getGuiExecutingBlocks().getGuiInterpreterElements().setGuiRunnable(null);
    }
}
