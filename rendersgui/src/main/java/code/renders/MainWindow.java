package code.renders;

import code.expressionlanguage.ContextEl;
import code.formathtml.DefaultInitialization;
import code.formathtml.util.BeanCustLgNames;
import code.gui.*;
import code.gui.Menu;
import code.gui.MenuBar;
import code.gui.MenuItem;
import code.gui.Panel;
import code.gui.ScrollPane;
import code.gui.TextArea;
import code.gui.TextField;
import code.gui.document.RenderedPage;
import code.gui.events.QuittingEvent;
import code.stream.StreamFolderFile;
import code.stream.StreamTextFile;
import code.stream.StreamZipFile;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;

public final class MainWindow extends GroupFrame {
    private Menu menu;
    private MenuItem open;
    private RenderedPage session;
    protected MainWindow(String _lg) {
        super(_lg);
        setJMenuBar(new MenuBar());
        menu = new Menu("file");
        open = new MenuItem("open");
        open.addActionListener(new OpenArchive(this));
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
        menu.addMenuItem(open);
        getJMenuBar().add(menu);
        setTitle("Local sites");
        Panel pane_ = Panel.newPageBox();
        session = new RenderedPage(new ScrollPane());
        session.setLanguage(_lg);
        session.setFrame(this);
        TextField field_;
        LabelButton search = new LabelButton("search");
        field_ = new TextField(20);
        session.setSearchText(search);
        session.setField(field_);
        TextArea t_ = new TextArea(8,32);
        session.setArea(t_);
        session.addFinder();
        ScrollPane scrollSession_ = session.getScroll();
        scrollSession_.setPreferredSize(new Dimension(400, 400));
        pane_.add(scrollSession_);
        pane_.add(field_);
        pane_.add(search);
        pane_.add(t_);
        setContentPane(pane_);
        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new QuittingEvent(this));
    }

    @Override
    public void dispose() {
        LaunchingRenders.decrement();
        super.dispose();
    }

    public void load() {
        FileOpenDialog.setFileOpenDialog(this,getLanguageKey(),true, "", ConstFiles.getHomePath());
        String fichier_=FileOpenDialog.getStaticSelectedPath();
        if (fichier_ == null) {
            fichier_ = "";
        }
        if (fichier_.isEmpty()) {
            return;
        }
        loadRenderConf(fichier_);
    }

    public void loadRenderConf(String _fichier) {
        String content_ = StreamTextFile.contentsOfFile(_fichier);
        if (content_ == null) {
            return;
        }
        StringList lines_ = StringList.splitStrings(content_, "\n", "\r\n");
        StringList linesFiles_ = new StringList();
        for (String s: lines_) {
            if (s.trim().isEmpty()) {
                continue;
            }
            linesFiles_.add(s.trim());
        }
        if (linesFiles_.size() < 2) {
            return;
        }
        String archive_ = linesFiles_.first();
        String confRel_ = linesFiles_.get(1);
        StringMap<String> zipFiles_ = StreamFolderFile.getFiles(archive_);
        String clName_ = "";
        String mName_ = "";
        if (linesFiles_.size() > 2) {
            String line_ = ContextEl.removeDottedSpaces(linesFiles_.get(2));
            if (line_.startsWith("initDb=")) {
                String subLine_ = line_.substring("initDb=".length());
                int last_ = subLine_.lastIndexOf('.');
                if (last_ > -1) {
                    clName_ = subLine_.substring(0,last_);
                    mName_ = subLine_.substring(last_+1);
                }
            }
        }
        BeanCustLgNames lgNames_ = new BeanCustLgNames();
        DefaultInitialization.basicStandards(lgNames_);
        basicCustStandards(lgNames_);
        session.initNav();
        session.setLanguage(getLanguageKey());
        session.setFiles(zipFiles_);
        session.initializeOnlyConf(confRel_,lgNames_,zipFiles_,clName_,mName_);
    }

    private static void basicCustStandards(BeanCustLgNames _lgNames) {
        _lgNames.setAliasMapKeys("keys");
        _lgNames.setAliasMapValues("values");
        _lgNames.setAliasMapIndexOfEntry("indexOfEntry");
        _lgNames.setAliasMapAddEntry("addEntry");
        _lgNames.setAliasMapGetValue("getValue");
        _lgNames.setAliasMapFirstValue("firstValue");
        _lgNames.setAliasMapLastValue("lastValue");
        _lgNames.setAliasMapSetValue("setValue");
        _lgNames.setAliasMapPut("put");
        _lgNames.setAliasMapPutAll("putAll");
        _lgNames.setAliasMapContains("contains");
        _lgNames.setAliasMapGetVal("getVal");
        _lgNames.setAliasMapRemoveKey("removeKey");
        _lgNames.setAliasMapGetKey("getKey");
        _lgNames.setAliasMapFirstKey("firstKey");
        _lgNames.setAliasMapLastKey("lastKey");
        _lgNames.setAliasMapSetKey("setKey");
        _lgNames.setAliasMapSize("size");
        _lgNames.setAliasMapIsEmpty("isEmpty");
        _lgNames.setAliasMapClear("clear");
        _lgNames.setAliasValidator("code.bean.Validator");
        _lgNames.setAliasValidate("validate");
    }
    @Override
    public void quit() {
        dispose();
    }

    @Override
    public String getApplicationName() {
        return "renders_sites";
    }

    @Override
    public boolean canChangeLanguage() {
        return false;
    }

    @Override
    public void changeLanguage(String _language) {

    }
}
