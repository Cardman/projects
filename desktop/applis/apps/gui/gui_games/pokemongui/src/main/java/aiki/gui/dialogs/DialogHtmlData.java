package aiki.gui.dialogs;


import aiki.facade.FacadeGame;
import aiki.gui.WindowAiki;
import aiki.main.AikiNatLgNamesNavigation;
import aiki.sml.Resources;
import code.gui.*;
import code.gui.document.RenderedPage;
import code.gui.images.MetaDimension;
import code.gui.initialize.AbstractProgramInfos;
import code.util.StringMap;

public final class DialogHtmlData {
    private static final String DIALOG_ACCESS = "aiki.gui.dialogs.dialoghtmldata";

    private static final String TEXT = "0";

    private static final String SEARCH_LABEL = "searchLabel";
    private final AbsDialog absDialog;

//    private Timer timer;

    private RenderedPage session;

    private StringMap<String> messages;

    public DialogHtmlData(AbstractProgramInfos _frameFactory) {
        absDialog = _frameFactory.getFrameFactory().newDialog();
        absDialog.setAccessFile(DIALOG_ACCESS);
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
    public static void setDialogHtmlData(WindowAiki _window, AbsDialog _parent, String _title, FacadeGame _dataBase, AikiNatLgNamesNavigation _pre, String _lg) {
        //super(_parent, true);
        _window.getDialogHtmlData().absDialog.setDialogIcon(_window.getImageFactory(),_parent);
        _window.getDialogHtmlData().absDialog.setTitle(_title);
        _window.getDialogHtmlData().absDialog.setLocationRelativeTo(_parent);
        group(_window, _dataBase, _pre, _lg);
    }

//    public static void setDialogHtmlData(GroupFrame _parent, String _title, SessionEditorPane _session, boolean _successCompile) {
//        //super(_parent, true);
//        DIALOG.setDialogIcon(_parent);
//        DIALOG.setTitle(_title);
//        DIALOG.init(_parent, _session);
//        DIALOG.initSession(_successCompile);
//    }
    public static void setDialogHtmlData(WindowAiki _parent, String _title, FacadeGame _dataBase, AikiNatLgNamesNavigation _pre, String _lg) {
        //super(_parent, true);
        _parent.getDialogHtmlData().absDialog.setDialogIcon(_parent.getImageFactory(),_parent.getCommonFrame());
        _parent.getDialogHtmlData().absDialog.setTitle(_title);
        _parent.getDialogHtmlData().absDialog.setLocationRelativeTo(_parent.getCommonFrame());
        group(_parent, _dataBase, _pre, _lg);
    }

    private static void group(WindowAiki _parent, FacadeGame _dataBase, AikiNatLgNamesNavigation _pre, String _lg) {
        DialogHtmlData d_ = _parent.getDialogHtmlData();
        _pre.getBeanNatLgNames().setDataBase(_dataBase);
        RenderedPage session_ = FrameHtmlData.initializeOnlyConf(_pre, _lg, _pre.getBeanNatLgNames(), _parent.getFrames());
        d_.messages = WindowAiki.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, _parent.getLanguageKey(), d_.absDialog.getAccessFile());
        d_.initSession(session_);
        d_.session.setFrame(d_.absDialog);
        d_.absDialog.setVisible(true);
    }

    private void initSession(RenderedPage _session) {
        session = _session;
        _session.setFrame(absDialog);
        AbsPanel panel_ = _session.getCompoFactory().newPageBox();
        AbsPlainLabel area_ = _session.getCompoFactory().newPlainLabel(TEXT);
        AbsTextField field_;
//        LabelButton search_ = new LabelButton(MainWindow.OK);
        AbsButton search_ = _session.getCompoFactory().newPlainButton(messages.getVal(SEARCH_LABEL));
        field_ = _session.getCompoFactory().newTextField(20);
//        _session.setLabel(area_);
        _session.addFinder(field_,search_);
//        JPanel group_ = new JPanel();
//        group_.setLayout(new BoxLayout(group_, BoxLayout.PAGE_AXIS));
        AbsScrollPane scrollSession_ = _session.getScroll();
        scrollSession_.setPreferredSize(new MetaDimension(400, 400));
//        group_.add(scrollSession_);
        panel_.add(scrollSession_);
        panel_.add(area_);
        panel_.add(field_);
        panel_.add(search_);
        absDialog.setContentPane(panel_);
//        timer = new Timer(200, new Chronometer(area_, _session, 0));
//        timer.start();
//        absDialog.setDefaultCloseOperation(GuiConstants.DO_NOTHING_ON_CLOSE);
        absDialog.pack();
    }

}
