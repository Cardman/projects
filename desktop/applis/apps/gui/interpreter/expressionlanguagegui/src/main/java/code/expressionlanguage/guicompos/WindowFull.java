package code.expressionlanguage.guicompos;

import code.expressionlanguage.gui.unit.MessagesCdmFullGui;
import code.gui.*;


import code.gui.EnabledMenu;


import code.gui.events.*;
import code.gui.files.DefButtonsOpenPanelAct;
import code.gui.files.FileOpenFrame;
import code.gui.images.MetaDimension;
import code.gui.initialize.*;
import code.stream.StreamTextFile;
import code.threads.AbstractAtomicBoolean;
import code.util.StringMap;
import code.util.core.StringUtil;


public final class WindowFull extends GroupFrame implements AbsOpenQuit{
    private final EnabledMenu menu;
    private final EnabledMenu open;

    private final AbsPanel contentPane;
    private final AbsPanel form;
    private final AbsPlainLabel content;
    private final AbsTextArea conf;
    private final AbsButton launch;
    private final AbsButton coverage;
    private final AbsButton stop;

    private final CdmFactory cdmFactory;
//    private final GuiInterpreterElements currentElements;
    private AbstractLightProgramInfos light;
    private GuiContextEl context;
    private final AbstractAtomicBoolean atomicBoolean;
    private final FileOpenFrame fileOpenFrame;
    private final AbsButton mainButton;

    public WindowFull(CdmFactory _list, AbstractProgramInfos _programInfos, LanguagesButtonsPair _pair) {
        super(_programInfos);
        mainButton = _pair.getMainButton();
        atomicBoolean = _programInfos.getThreadFactory().newAtomicBoolean();
        fileOpenFrame = new FileOpenFrame(_programInfos,atomicBoolean);
        GuiBaseUtil.choose(this, _programInfos);
        cdmFactory = _list;
//        currentElements = new GuiInterpreterElements(getFrames());
//        setAccessFile("launcher.mainwindow");
//        String fileName_ = ResourcesMessagesUtil.getPropertiesPath("resources_lg_gui/gui/messages", _programInfos.getLanguage(), getAccessFile());
//        String loadedResourcesMessages_ = MessCdmGuiGr.ms().getVal(fileName_);
        StringMap<String> messages_ = MessagesCdmFullGui.valMessages(_programInfos.currentLg());
        setTitle(messages_.getVal(MessagesCdmFullGui.TITLE));
        setJMenuBar(getCompoFactory().newMenuBar());
        menu = getCompoFactory().newMenu(messages_.getVal(MessagesCdmFullGui.FILE));
        open = getCompoFactory().newMenuItem(messages_.getVal(MessagesCdmFullGui.OPEN));
        open.addActionListener(new FileOpenEventFull(this));
        open.setAccelerator(GuiConstants.VK_O, GuiConstants.CTRL_DOWN_MASK);
        menu.addMenuItem(open);
        getJMenuBar().add(menu);
        contentPane = getCompoFactory().newPageBox();
        form = getCompoFactory().newGrid(0,2);
        content = getCompoFactory().newPlainLabel(messages_.getVal(MessagesCdmFullGui.CONFIGURATION));
        form.add(content);
        conf = getCompoFactory().newTextArea(64,64);
        AbsScrollPane scr_ = getCompoFactory().newAbsScrollPane(conf);
        scr_.setPreferredSize(new MetaDimension(256,96));
        form.add(scr_);
        launch = getCompoFactory().newPlainButton(messages_.getVal(MessagesCdmFullGui.LAUNCH));
        launch.addActionListener(new ListenerLaunchApp(this));
        form.add(launch);
        coverage = getCompoFactory().newPlainButton(messages_.getVal(MessagesCdmFullGui.COVERAGE));
        coverage.addActionListener(new CoverageAction(this));
        coverage.setEnabled(false);
        form.add(coverage);
        stop = getCompoFactory().newPlainButton(messages_.getVal(MessagesCdmFullGui.STOP));
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
//    public void dispose() {
//        GuiBaseUtil.trEx(this);
//    }

    public void process() {
//        if (light != null) {
//            return;
//        }
        String txt_ = conf.getText().trim();
        GuiRunnable current_ = GuiProcess.build("", txt_, cdmFactory, getFrames());
//        currentElements.setGuiRunnable(current_);
        if (current_ == null) {
            stop.setEnabled(true);
            return;
        }
        light = getFrames();
        launch.setEnabled(false);
        context = current_.getContext();
        ((LgNamesGui) current_.getContext().getStandards()).getGuiExecutingBlocks().setStop(stop);
        current_.run();
        coverage.setEnabled(true);
    }
    public void launchFileConf(String _fichier, boolean _direct) {
        if (light != null) {
            return;
        }
        String content_ = StringUtil.nullToEmpty(StreamTextFile.contentsOfFile(_fichier, getStreams()));
//        if (content_ == null) {
//            return;
//        }
        GuiRunnable current_ = GuiProcess.build(_fichier, content_, cdmFactory, getFrames());
//        currentElements.setGuiRunnable(current_);
        if (current_ == null) {
            stop.setEnabled(true);
            return;
        }
        light = getFrames();
        launch.setEnabled(false);
        context = current_.getContext();
        ((LgNamesGui) current_.getContext().getStandards()).getGuiExecutingBlocks().setStop(stop);
        if (_direct) {
            current_.run();
        } else {
            getFrames().getCompoFactory().invokeNow(current_);
        }
        coverage.setEnabled(true);
    }

    @Override
    public void quit() {
        if (context != null) {
            context.interrupt();
        }
        getCommonFrame().setVisible(false);
        coverage.setEnabled(false);
        stop.setEnabled(false);
        launch.setEnabled(true);
        light = null;
        LanguageDialogButtons.enable(mainButton,true);
        GuiBaseUtil.trEx(this, getFrames());
    }

    @Override
    public String getApplicationName() {
        return MessagesCdmFullGui.APPS_LAUNCHER;
    }

//    @Override
//    public boolean canChangeLanguage() {
//        return false;
//    }

    @Override
    public void changeLanguage(String _language) {
        getFrames().setLanguage(_language);
        cdmFactory.getProgramInfos().setLanguage(_language);
    }

//    public GuiInterpreterElements getCurrentElements() {
//        return currentElements;
//    }

    public void coverage() {
        new CoveringCodeTask(context, context.getExecutingOptions()).run();
    }

    public GuiContextEl getContext() {
        return context;
    }

    public AbsTextArea getConf() {
        return conf;
    }

    public AbsButton getLaunch() {
        return launch;
    }

    public AbsButton getStop() {
        return stop;
    }

    public AbsButton getCoverage() {
        return coverage;
    }

    public EnabledMenu getOpen() {
        return open;
    }

    public void stopAction() {
        coverage.setEnabled(false);
        stop.setEnabled(false);
        light = null;
        launch.setEnabled(true);
//        ((LgNamesGui) context.getStandards()).getGuiExecutingBlocks().getGuiInterpreterElements().setGuiRunnable(null);
    }
}
