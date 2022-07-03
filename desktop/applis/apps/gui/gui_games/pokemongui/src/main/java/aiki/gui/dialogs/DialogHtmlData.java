package aiki.gui.dialogs;




import aiki.beans.PokemonStandards;
import aiki.facade.FacadeGame;
import aiki.gui.threads.PreparedRenderedPages;
import aiki.sml.Resources;
import aiki.gui.WindowAiki;
import code.gui.*;
import code.gui.document.RenderedPage;
import code.gui.images.MetaDimension;
import code.gui.initialize.AbsFrameFactory;
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
    public static void setDialogHtmlData(WindowAiki _window, AbsDialog _parent, String _title, RenderedPage _session, FacadeGame _dataBase, PreparedRenderedPages _pre, String _lg) {
        //super(_parent, true);
        _window.getDialogHtmlData().absDialog.setDialogIcon(_window.getImageFactory(),_parent);
        _window.getDialogHtmlData().absDialog.setTitle(_title);
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
    public static void setDialogHtmlData(WindowAiki _parent, String _title, RenderedPage _session, FacadeGame _dataBase, PreparedRenderedPages _pre, String _lg) {
        //super(_parent, true);
        _parent.getDialogHtmlData().absDialog.setDialogIcon(_parent.getImageFactory(),_parent);
        _parent.getDialogHtmlData().absDialog.setTitle(_title);
        _parent.getDialogHtmlData().init(_parent, _parent, _session);
        _parent.getDialogHtmlData().initSession(_dataBase,_pre,_lg);
    }

    private void init(WindowAiki _window, AbsDialog _parent, RenderedPage _session) {
        messages = WindowAiki.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, _window.getLanguageKey(), absDialog.getAccessFile());
        absDialog.setLocationRelativeTo(_parent);
        initSession(_session);
    }

    private void init(WindowAiki _window, WindowAiki _parent, RenderedPage _session) {
        messages = WindowAiki.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, _window.getLanguageKey(), absDialog.getAccessFile());
        absDialog.setLocationRelativeTo(_parent.getCommonFrame());
        initSession(_session);
    }

    private void initSession(RenderedPage _session) {
        session = _session;
        _session.setFrame(absDialog);
        AbsPanel panel_ = _session.getCompoFactory().newPageBox();
        AbsPlainLabel area_ = _session.getCompoFactory().newPlainLabel(TEXT);
        AbsTextField field_;
//        LabelButton search_ = new LabelButton(MainWindow.OK);
        AbsPlainButton search_ = _session.getCompoFactory().newPlainButton(messages.getVal(SEARCH_LABEL));
        field_ = _session.getCompoFactory().newTextField(20);
//        _session.setLabel(area_);
        _session.setSearchText(search_);
        _session.setField(field_);
        _session.addFinder();
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
        absDialog.setDefaultCloseOperation(GuiConstants.DO_NOTHING_ON_CLOSE);
        absDialog.pack();
    }

    public void initSession(FacadeGame _dataBase, PreparedRenderedPages _pre, String _lg) {
        session.setFrame(absDialog);
        ((PokemonStandards)_pre.getBeanNatLgNames()).setDataBase(_dataBase);
        session.initializeOnlyConf(_pre, _lg);

        absDialog.setVisible(true);
    }
}
