package aiki.gui.dialogs;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ToolTipManager;
import javax.swing.WindowConstants;

import code.gui.ChildFrame;
import code.gui.LabelButton;
import code.gui.ProgressingWebDialog;
import code.gui.SessionEditorPane;
import code.gui.events.ClosingChildFrameEvent;
import code.util.StringMap;
import code.util.consts.Constants;
import code.xml.util.ExtractFromFiles;
import aiki.Resources;
import aiki.gui.MainWindow;

public final class FrameHtmlData extends ChildFrame {
    private static final String DIALOG_ACCESS = "aiki.gui.dialogs.FrameHtmlData";

    private static final String TEXT = "0";

    private static final String SEARCH_LABEL = "searchLabel";

//    private Timer timer;
//    private MainWindow parent;
//    private Battle battle;

    private SessionEditorPane session;

    private StringMap<String> messages;

    private LabelButton search;

    private ProgressingWebDialog dialog;

    public FrameHtmlData(MainWindow _parent, String _title, SessionEditorPane _session) {
        setAccessFile(DIALOG_ACCESS);
        messages = getMessages(Resources.MESSAGES_FOLDER);
        setDialogIcon(_parent);
        setLocationRelativeTo(_parent);
        setTitle(_title);
        dialog = new ProgressingWebDialog();
        setFocusableWindowState(true);
//        if (_relative) {
//            parent = _parent;
//        }
        session = _session;
        session.setFrame(this);
        session.setDialog(dialog);
        session.setEditable(false);
        ToolTipManager.sharedInstance().registerComponent(session);
        JPanel panel_ = new JPanel();
        JLabel area_ = new JLabel(TEXT);
        JTextField field_;
//        LabelButton search_ = new LabelButton(MainWindow.OK);
        search = new LabelButton(messages.getVal(SEARCH_LABEL));
        field_ = new JTextField(20);
        session.setLabel(area_);
        session.setSearchText(search);
        session.setField(field_);
        session.addFinder();
        panel_.setLayout(new BoxLayout(panel_, BoxLayout.PAGE_AXIS));
//        JPanel group_ = new JPanel();
//        group_.setLayout(new BoxLayout(group_, BoxLayout.PAGE_AXIS));
        JScrollPane scrollSession_ = new JScrollPane(session);
        scrollSession_.setPreferredSize(new Dimension(400, 400));
//        group_.add(scrollSession_);
        panel_.add(scrollSession_);
        panel_.add(area_);
        panel_.add(field_);
        panel_.add(search);
        setContentPane(panel_);
//        timer = new Timer(200, new Chronometer(area_, session, 0));
//        timer.start();
        addWindowListener(new ClosingChildFrameEvent(this));
//        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        pack();
//        setVisible(true);
    }

    @Override
    public void closeWindow() {
//        timer.stop();
        session.finish(true);
        setVisible(false);
//        session.clearSession();
    }

//    public void initSession(Map<String,String> _webFiles,
//            boolean _succesfulCompile, String _fileConf, String _fileResConf) {
//        session.setFiles(_webFiles, Resources.ACCESS_TO_DEFAULT_FILES);
//        try {
//            if (_succesfulCompile) {
//                session.initialize(_fileConf);
//            } else {
//                session.initialize(_fileResConf);
//            }
//        } catch (Throwable _0) {
//            _0.printStackTrace();
//        }
//    }
    public void initSession(String _fileResConf) {
        session.setFiles( Resources.ACCESS_TO_DEFAULT_FILES);
        setVisible(true);
        session.initializeOnlyConf(_fileResConf);
//        try {
//            session.initializeOnlyConf(_fileResConf);
//        } catch (Throwable _0) {
//            _0.printStackTrace();
//        }
    }
//    public void reInitAllSession(String _conf, String _language, Object _dataBase, Map<String,String> _files) {
//        session.setLanguage(_language);
//        session.setDataBase(_dataBase);
//        session.setFiles(_files, new Map<String,String>());
//        try {
//            session.reInitSession(_conf);
//        } catch (Exception e_) {
//            try {
//                CompilingBeans.loadDefaultClassesAndClear();
//                session.setRelativeFiles(Resources.ACCESS_TO_DEFAULT_FILES);
//                session.initialize(Resources.ACCESS_TO_DEFAULT_DATA);
//            } catch (Exception e2_) {
//                e2_.printStackTrace();
//            }
//        }
//    }

    public ProgressingWebDialog getDialog() {
        return dialog;
    }

    public void refresh() {
        messages = ExtractFromFiles.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, Constants.getLanguage(), DIALOG_ACCESS);
        search.setText(messages.getVal(SEARCH_LABEL));
        session.setLanguage(Constants.getLanguage());
        session.refresh();
    }

    public void reset() {
        session.reset();
    }

    public SessionEditorPane getSession() {
        return session;
    }

//    public void setBattle(Battle _battle) {
//        battle = _battle;
//    }

//    @Override
//    public void dispose() {
//        session.finish();
//        super.dispose();
////        session.setDataBase(null);
////        session.setNullFiles();
////        session = null;
//        if (parent != null) {
//            parent.toFront();
//            parent.requestFocus();
//            parent.clearHtmlDialogs();
//        }
//        if (battle != null) {
//            battle.clearHtmlDialogs();
//        }
//    }
}
