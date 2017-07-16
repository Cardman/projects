package aiki.gui.dialogs;
import java.awt.Dimension;
import java.awt.Window;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ToolTipManager;
import javax.swing.WindowConstants;

import aiki.Resources;
import code.gui.Dialog;
import code.gui.GroupFrame;
import code.gui.LabelButton;
import code.gui.SessionEditorPane;
import code.util.StringMap;

public final class DialogHtmlData extends Dialog {
    private static final String DIALOG_ACCESS = "dbpokemon.gui.dialogs.DialogHtmlData";

    private static final String TEXT = "0";

    private static final String SEARCH_LABEL = "searchLabel";

    private static final DialogHtmlData DIALOG = new DialogHtmlData();

//    private Timer timer;

    private SessionEditorPane session;

    private StringMap<String> messages;

    private DialogHtmlData() {
        setAccessFile(DIALOG_ACCESS);
    }

    @Override
    public void closeWindow() {
        session.finish(true);
        super.closeWindow();
//        session.clearSession();
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
    public static void setDialogHtmlData(Dialog _parent, String _title, SessionEditorPane _session) {
        //super(_parent, true);
        DIALOG.setDialogIcon(_parent);
        DIALOG.setTitle(_title);
        DIALOG.init(_parent, _session);
        DIALOG.initSession();
    }

//    public static void setDialogHtmlData(GroupFrame _parent, String _title, SessionEditorPane _session, boolean _successCompile) {
//        //super(_parent, true);
//        DIALOG.setDialogIcon(_parent);
//        DIALOG.setTitle(_title);
//        DIALOG.init(_parent, _session);
//        DIALOG.initSession(_successCompile);
//    }
    public static void setDialogHtmlData(GroupFrame _parent, String _title, SessionEditorPane _session) {
        //super(_parent, true);
        DIALOG.setDialogIcon(_parent);
        DIALOG.setTitle(_title);
        DIALOG.init(_parent, _session);
        DIALOG.initSession();
    }

    private void init(Window _parent, SessionEditorPane _session) {
        messages = getMessages(Resources.MESSAGES_FOLDER);
        setLocationRelativeTo(_parent);
        session = _session;
        _session.setFrame(this);
        _session.setEditable(false);
        ToolTipManager.sharedInstance().registerComponent(_session);
        JPanel panel_ = new JPanel();
        JLabel area_ = new JLabel(TEXT);
        JTextField field_;
//        LabelButton search_ = new LabelButton(MainWindow.OK);
        LabelButton search_ = new LabelButton(messages.getVal(SEARCH_LABEL));
        field_ = new JTextField(20);
        _session.setLabel(area_);
        _session.setSearchText(search_);
        _session.setField(field_);
        _session.addFinder();
        panel_.setLayout(new BoxLayout(panel_, BoxLayout.PAGE_AXIS));
//        JPanel group_ = new JPanel();
//        group_.setLayout(new BoxLayout(group_, BoxLayout.PAGE_AXIS));
        JScrollPane scrollSession_ = new JScrollPane(_session);
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

//    public void initSession(boolean _successfulCompile) {
//        session.setFrame(this);
//        try {
//            if (_successfulCompile) {
//                session.initialize(Resources.CONFIG_PK);
//            } else {
//                session.initialize(Resources.ACCESS_TO_DEFAULT_PK);
//            }
//        } catch (Throwable _0) {
//            _0.printStackTrace();
//        }
//        setVisible(true);
//    }
    public void initSession() {
        session.setFrame(this);
        session.initializeOnlyConf(Resources.ACCESS_TO_DEFAULT_PK);
//        try {
//            session.initializeOnlyConf(Resources.ACCESS_TO_DEFAULT_PK);
//        } catch (Throwable _0) {
//            _0.printStackTrace();
//        }
        setVisible(true);
    }
}
