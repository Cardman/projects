package code.expressionlanguage.guicompos;

import code.gui.*;


import code.gui.AbsMenuItem;


import code.gui.events.QuittingEvent;
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

    private final StringMap<String> messages;
    private final GuiInterpreterElements currentElements;

    protected WindowFull(String _lg, AbstractProgramInfos _list) {
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
        form.add(getCompoFactory().newPlainLabel(""));
        contentPane.add(form);
        setContentPane(contentPane);
        pack();
        setVisible(true);
        setDefaultCloseOperation(GuiConstants.DO_NOTHING_ON_CLOSE);
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
            return;
        }
        current_.run();
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
        if (current_ != null&&current_.isVisible()) {
            return;
        }
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

}
