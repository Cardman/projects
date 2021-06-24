package aiki.gui.dialogs;
import java.awt.Dimension;

import javax.swing.WindowConstants;

import aiki.beans.PokemonStandards;
import aiki.facade.FacadeGame;
import aiki.gui.threads.PreparedRenderedPages;
import aiki.sml.Resources;
import aiki.gui.MainWindow;
import code.gui.*;
import code.gui.document.RenderedPage;
import code.util.StringMap;

public final class DialogHtmlData extends Dialog {
    private static final String DIALOG_ACCESS = "aiki.gui.dialogs.dialoghtmldata";

    private static final String TEXT = "0";

    private static final String SEARCH_LABEL = "searchLabel";

//    private Timer timer;

    private RenderedPage session;

    private StringMap<String> messages;

    public DialogHtmlData() {
        setAccessFile(DIALOG_ACCESS);
    }

//    public static void setDialogHtmlData(JDialog _parent, String _title, SessionEditorPane _session) {

//    }
//    public static void setDialogHtmlData(Dialog _parent, String _title, SessionEditorPane _session, boolean _successCompile) {
//        //super(_parent, true);
//        DIALOG.setDialogIcon(_parent);
//        DIALOG.setTitle(_title);
//        DIALOG.init(_parent, _session);
//        DIALOG.initSession(_successCompile);
//    }
    public static void setDialogHtmlData(MainWindow _window, Dialog _parent, String _title, RenderedPage _session, FacadeGame _dataBase, PreparedRenderedPages _pre, String _lg) {
        //super(_parent, true);
        _window.getDialogHtmlData().setDialogIcon(_window.getImageFactory(),_parent);
        _window.getDialogHtmlData().setTitle(_title);
        _window.getDialogHtmlData().init(_window, _parent, _session);
        _window.getDialogHtmlData().initSession(_dataBase,_pre,_lg);
    }

//    public static void setDialogHtmlData(GroupFrame _parent, String _title, SessionEditorPane _session, boolean _successCompile) {
//        //super(_parent, true);
//        DIALOG.setDialogIcon(_parent);
//        DIALOG.setTitle(_title);
//        DIALOG.init(_parent, _session);
//        DIALOG.initSession(_successCompile);
//    }
    public static void setDialogHtmlData(MainWindow _parent, String _title, RenderedPage _session,FacadeGame _dataBase,PreparedRenderedPages _pre, String _lg) {
        //super(_parent, true);
        _parent.getDialogHtmlData().setDialogIcon(_parent.getImageFactory(),_parent);
        _parent.getDialogHtmlData().setTitle(_title);
        _parent.getDialogHtmlData().init(_parent, _parent, _session);
        _parent.getDialogHtmlData().initSession(_dataBase,_pre,_lg);
    }

    private void init(MainWindow _window,Dialog _parent, RenderedPage _session) {
        messages = MainWindow.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, _window.getLanguageKey(), getAccessFile());
        setLocationRelativeTo(_parent);
        initSession(_session);
    }

    private void init(MainWindow _window,MainWindow _parent, RenderedPage _session) {
        messages = MainWindow.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, _window.getLanguageKey(), getAccessFile());
        setLocationRelativeTo(_parent);
        initSession(_session);
    }

    private void initSession(RenderedPage _session) {
        session = _session;
        _session.setFrame(this);
        Panel panel_ = Panel.newPageBox();
        TextLabel area_ = new TextLabel(TEXT);
        TextField field_;
//        LabelButton search_ = new LabelButton(MainWindow.OK);
        LabelButton search_ = new LabelButton(messages.getVal(SEARCH_LABEL));
        field_ = new TextField(20);
//        _session.setLabel(area_);
        _session.setSearchText(search_);
        _session.setField(field_);
        _session.addFinder();
//        JPanel group_ = new JPanel();
//        group_.setLayout(new BoxLayout(group_, BoxLayout.PAGE_AXIS));
        ScrollPane scrollSession_ = _session.getScroll();
        scrollSession_.setPreferredSize(new Dimension(400, 400));
//        group_.add(scrollSession_);
        panel_.add(scrollSession_);
        panel_.add(area_);
        panel_.add(field_);
        panel_.add(search_);
        setContentPane(panel_);
//        timer = new Timer(200, new Chronometer(area_, _session, 0));
//        timer.start();
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        pack();
    }

    public void initSession(FacadeGame _dataBase, PreparedRenderedPages _pre, String _lg) {
        session.setFrame(this);
        ((PokemonStandards)_pre.getBeanNatLgNames()).setDataBase(_dataBase);
        session.initializeOnlyConf(_pre, _lg);

        setVisible(true);
    }
}
