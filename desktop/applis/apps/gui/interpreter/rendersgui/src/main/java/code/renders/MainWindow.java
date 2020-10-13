package code.renders;

import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.utilcompo.ExecutingOptions;
import code.expressionlanguage.utilcompo.FileInfos;
import code.expressionlanguage.utilfiles.DefaultFileSystem;
import code.expressionlanguage.utilfiles.DefaultLogger;
import code.expressionlanguage.utilfiles.DefaultReporter;
import code.expressionlanguage.utilfiles.DefaultResourcesReader;
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
import code.gui.initialize.AbstractProgramInfos;
import code.renders.utilcompo.LgNamesRenderUtils;
import code.stream.StreamFolderFile;
import code.stream.StreamTextFile;
import code.util.StringList;
import code.util.StringMap;
import code.util.consts.Constants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public final class MainWindow extends GroupFrame {
    private Menu menu;
    private MenuItem open;
    private TextField lgCode;
    private RenderedPage session;
    protected MainWindow(String _lg, AbstractProgramInfos _list) {
        super(_lg, _list);
        setJMenuBar(new MenuBar());
        menu = new Menu("file");
        open = new MenuItem("open");
        open.addActionListener(new OpenArchive(this));
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
        menu.addMenuItem(open);
        getJMenuBar().add(menu);
        setTitle("Local sites");
        Panel pane_ = Panel.newPageBox();
        lgCode = new TextField(20);
        pane_.add(lgCode);
        session = new RenderedPage(new ScrollPane());
        session.initNav();
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

    public static CustThreadActions inst(String _conf, String _lgCode, BeanCustLgNames _stds, StringMap<String> _files, String _clName, String _methodName, RenderedPage _page) {
        CustThreadActions actions_ = CustThreadActions.inst(_page, _stds, _lgCode, _conf, _files);
        actions_.setClassDbName(_clName);
        actions_.setMethodName(_methodName);
        return actions_;
    }

    @Override
    public void dispose() {
        basicDispose();
    }

    public void load() {
        FileOpenDialog.setFileOpenDialog(this,getLanguageKey(),true, "", getFrames().getHomePath());
        String fichier_=FileOpenDialog.getStaticSelectedPath(getFileOpenDialog());
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
        StringList lines_ = StringUtil.splitStrings(content_, "\n", "\r\n");
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
        ExecutingOptions exec_ = new ExecutingOptions();
        String lg_ = getLanguageKey();
        StringList lgs_ = Constants.getAvailableLanguages();
        if (linesFiles_.size() > 2) {
            String line_ = StringExpUtil.removeDottedSpaces(linesFiles_.get(2));
            if (line_.startsWith("initDb=")) {
                String subLine_ = line_.substring("initDb=".length());
                int last_ = subLine_.lastIndexOf('.');
                if (last_ > -1) {
                    clName_ = subLine_.substring(0,last_);
                    mName_ = subLine_.substring(last_+1);
                }
                if (linesFiles_.size() > 3) {
                    lg_ = linesFiles_.get(3);
                    StringList curr_ = new StringList();
                    if (!StringUtil.contains(Constants.getAvailableLanguages(),lg_)){
                        lg_ = getLanguageKey();
                        setupOptionals(3, exec_,linesFiles_,curr_);
                    } else {
                        setupOptionals(4, exec_, linesFiles_,curr_);
                    }
                    if (!curr_.isEmpty()) {
                        lgs_ = curr_;
                    }
                }
            }
        }
        BeanCustLgNames lgNames_ = new LgNamesRenderUtils(new FileInfos(new DefaultResourcesReader(),new DefaultLogger(),
                new DefaultFileSystem(), new DefaultReporter(), getGenerator()));
        session.initNav();
        session.setLanguage(lg_,lgs_);
        session.setFiles(zipFiles_);
        session.initializeOnlyConf(new CustContextCreator(),lgNames_, inst(confRel_, lgCode.getText(), lgNames_, zipFiles_, clName_, mName_, session));
    }
    public static void setupOptionals(int _from, ExecutingOptions _exec, StringList _lines, StringList _lgs) {
        for (String l: _lines.mid(_from)) {
            if (l.startsWith("log=")) {
                String output_ = l.substring("log=".length());
                int lastSep_ = output_.lastIndexOf('>');
                if (lastSep_ > -1) {
                    _exec.setLogFolder(output_.substring(0,lastSep_));
                    _exec.setMainThread(output_.substring(lastSep_+1));
                }
            }
            if (l.startsWith("tabWidth=")) {
                String output_ = l.substring("tabWidth=".length());
                int t_ = NumberUtil.parseInt(output_);
                if (t_ > 0) {
                    _exec.setTabWidth(t_);
                }
            }
            if (l.startsWith("lgs=")) {
                String output_ = l.substring("lgs=".length());
                for (String s: StringUtil.splitChars(output_,',')) {
                    String tr_ = s.trim();
                    if (tr_.isEmpty()) {
                        continue;
                    }
                    _lgs.add(tr_);
                }
            }
        }
    }

    @Override
    public void quit() {
        basicDispose();
    }

    @Override
    public String getApplicationName() {
        return LaunchingRenders.getMainWindowClass();
    }

    @Override
    public boolean canChangeLanguage() {
        return false;
    }

    @Override
    public void changeLanguage(String _language) {

    }
}
