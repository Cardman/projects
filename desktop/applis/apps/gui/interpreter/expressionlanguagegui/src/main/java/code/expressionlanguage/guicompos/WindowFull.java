package code.expressionlanguage.guicompos;

import code.gui.*;


import code.gui.AbsMenuItem;


import code.gui.events.*;
import code.gui.images.MetaDimension;
import code.gui.initialize.AbstractProgramInfos;
import code.scripts.messages.gui.MessCdmGuiGr;
import code.sml.util.ResourcesMessagesUtil;
import code.stream.StreamTextFile;
import code.util.StringMap;




public final class WindowFull extends GroupFrame {
    private final AbsMenu menu;
    private final AbsMenuItem open;

    private final AbsPanel contentPane;
    private final AbsPanel form;
    private final AbsPlainLabel content;
    private final AbsTextArea conf;
    private final AbsPlainButton launch;
    private final AbsPlainButton coverage;
    private final AbsPlainButton stop;

    private final StringMap<String> messages;
    private final GuiInterpreterElements currentElements;

    public WindowFull(String _lg, AbstractProgramInfos _list) {
        super(_lg, _list);
        currentElements = new GuiInterpreterElements(getFrames());
        setAccessFile("launcher.mainwindow");
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath("resources_lg_gui/gui/messages", getLanguageKey(), getAccessFile());
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
        String fichier_=getFileOpenDialogInt().input(getCommonFrame(),getLanguageKey(),true, "", getFrames().getHomePath());
        if (fichier_ == null) {
            fichier_ = "";
        }
        if (fichier_.isEmpty()) {
            return;
        }
        launchFileConf(fichier_,true);
    }

    @Override
    public void dispose() {
        basicDispose();
    }

    public void process() {
        if (currentElements.getGuiRunnable() != null) {
            return;
        }
        String txt_ = conf.getText().trim();
        GuiRunnable current_ = GuiProcess.build("", txt_, getCurrentElements(),getFrames());
        currentElements.setGuiRunnable(current_);
        if (current_ == null) {
            stop.setEnabled(true);
            return;
        }
        ((LgNamesGui) current_.getContext().getStandards()).getGuiExecutingBlocks().setStop(stop);
        current_.run();
        coverage.setEnabled(true);
    }
    public void launchFileConf(String _fichier, boolean _direct) {
        if (currentElements.getGuiRunnable() != null) {
            return;
        }
        String content_ = StreamTextFile.contentsOfFile(_fichier,getFileCoreStream(),getStreams());
        if (content_ == null) {
            return;
        }
        GuiRunnable current_ = GuiProcess.build(_fichier, content_, getCurrentElements(),getFrames());
        currentElements.setGuiRunnable(current_);
        if (current_ == null) {
            return;
        }
        if (_direct) {
            current_.run();
        } else {
            FrameUtil.invokeLater(current_, getFrames());
        }
    }

    @Override
    public void quit() {
        GuiRunnable current_ = currentElements.getGuiRunnable();
        if (current_ != null) {
            return;
        }
        coverage.setEnabled(false);
        stop.setEnabled(false);
        basicDispose();
    }

    @Override
    public String getApplicationName() {
        return LaunchingFull.getMainWindowClass();
    }

    @Override
    public boolean canChangeLanguage() {
        return false;
    }

    @Override
    public void changeLanguage(String _language) {
        //
    }

    public GuiInterpreterElements getCurrentElements() {
        return currentElements;
    }

    public void coverage() {
        new CoveringCodeTask(currentElements.getGuiRunnable().getContext(), currentElements.getGuiRunnable().getContext().getExecutingOptions()).run();
    }

    public void stopAction() {
        coverage.setEnabled(false);
        stop.setEnabled(false);
        ((LgNamesGui) currentElements.getGuiRunnable().getContext().getStandards()).getGuiExecutingBlocks().getGuiInterpreterElements().setGuiRunnable(null);
    }
}
