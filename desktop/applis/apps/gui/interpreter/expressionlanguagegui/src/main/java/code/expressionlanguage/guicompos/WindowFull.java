package code.expressionlanguage.guicompos;

import code.gui.*;
import code.gui.Menu;
import code.gui.MenuBar;
import code.gui.MenuItem;


import code.gui.events.QuittingEvent;
import code.gui.initialize.AbstractProgramInfos;
import code.scripts.messages.gui.MessCdmGuiGr;
import code.sml.util.ResourcesMessagesUtil;
import code.stream.StreamTextFile;
import code.util.StringMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public final class WindowFull extends GroupFrame {
    private final Menu menu;
    private final MenuItem open;

    private final AbsPanel contentPane;
    private final AbsPanel form;
    private final PlainLabel content;
    private final AbsTextArea conf;
    private final PlainButton launch;

    private final StringMap<String> messages;
    private GuiProcess current;
    private final GuiFactroy fact;
    protected WindowFull(String _lg, AbstractProgramInfos _list, GuiFactroy _guiFactroy) {
        super(_lg, _list);
        fact = _guiFactroy;
        setAccessFile("launcher.mainwindow");
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath("resources_lg_gui/gui/messages", getLanguageKey(), getAccessFile());
        String loadedResourcesMessages_ = MessCdmGuiGr.ms().getVal(fileName_);
        messages = ResourcesMessagesUtil.getMessagesFromContent(loadedResourcesMessages_);
        setTitle(messages.getVal("title"));
        setJMenuBar(new MenuBar());
        menu = new Menu(messages.getVal("file"));
        open = new MenuItem(messages.getVal("open"));
        open.addActionListener(new FileOpenEventFull(this));
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
        menu.addMenuItem(open);
        getJMenuBar().add(menu);
        contentPane = getCompoFactory().newPageBox();
        form = getCompoFactory().newGrid(0,2);
        content = new PlainLabel(messages.getVal("configuration"));
        form.add(content);
        conf = getCompoFactory().newTextArea(64,64);
        AbsScrollPane scr_ = getCompoFactory().newAbsScrollPane(conf);
        scr_.setPreferredSize(new Dimension(256,96));
        form.add(scr_);
        launch = new PlainButton(messages.getVal("launch"));
        launch.addActionListener(new ListenerLaunchApp(this));
        form.add(launch);
        form.add(new TextLabel(""));
        contentPane.add(form);
        setContentPane(contentPane);
        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new QuittingEvent(this));
    }
    public void selectFile() {
        FileOpenDialog.setFileOpenDialog(this,getLanguageKey(),true, "", getFrames().getHomePath(),"jre");
        String fichier_=FileOpenDialog.getStaticSelectedPath(getFileOpenDialog());
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
        if (current != null) {
            return;
        }
        String txt_ = conf.getText().trim();
        current = GuiProcess.build("",this, txt_);
        if (current == null) {
            return;
        }
        current.run();
    }
    public void launchFileConf(String _fichier, boolean _direct) {
        if (current != null) {
            return;
        }
        String content_ = StreamTextFile.contentsOfFile(_fichier,getFileCoreStream(),getStreams());
        if (content_ == null) {
            return;
        }
        current = GuiProcess.build(_fichier, this,content_);
        if (current == null) {
            return;
        }
        if (_direct) {
            current.run();
        } else {
            FrameUtil.invokeLater(current, getFrames());
        }
    }

    public void setNullCurrent() {
        current = null;
    }

    @Override
    public void quit() {
        if (current != null&&current.isVisible()) {
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

    public GuiFactroy getFact() {
        return fact;
    }
}
