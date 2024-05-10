package aiki.gui.dialogs;


import aiki.facade.FacadeGame;
import aiki.gui.WindowAiki;
import aiki.gui.dialogs.events.ClosingDialogDifficulty;
import aiki.gui.threads.AfterSettingDifficutyThread;
import aiki.main.AikiNatLgNamesNavigation;
import aiki.sml.GamesPk;
import aiki.sml.MessagesRenderPkGameDetail;
import code.gui.*;
import code.gui.document.RenderedPage;
import code.gui.images.MetaDimension;
import code.gui.initialize.AbstractProgramInfos;
import code.util.StringMap;

public final class DialogDifficulty {
    private static final String DIALOG_ACCESS = "aiki.gui.dialogs.dialogdifficulty";

    private static final String TEXT = "0";

//    private static final String SEARCH_LABEL = "searchLabel";
    private final AbsCommonFrame absDialog;

    private WindowAiki window;

    private FacadeGame facade;

    public DialogDifficulty(AbstractProgramInfos _frameFactory) {
        absDialog = _frameFactory.getFrameFactory().newCommonFrame("",_frameFactory,null);
        absDialog.addWindowListener(new ClosingDialogDifficulty(this));
        absDialog.setAccessFile(DIALOG_ACCESS);
    }

    public static void setDialogDifficulty(WindowAiki _window, String _title, FacadeGame _facade, AikiNatLgNamesNavigation _pre) {
        _window.getDialogDifficulty().init(_window, _title, _facade,_pre);
    }

    private void init(WindowAiki _window, String _title, FacadeGame _facade, AikiNatLgNamesNavigation _pre) {
        _window.getModal().set(true);
        absDialog.setIconImage(_window.getCommonFrame().getImageIconFrame());
        facade = _facade;
        window = _window;
        //super(_window, true);
        StringMap<String> messages_ = GamesPk.getPkGameDetailContentTr(GamesPk.getAppliTr(_window.getFrames().currentLg())).getMapping();
//        StringMap<String> messages_ = WindowAiki.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, _window.getLanguageKey(), absDialog.getAccessFile());
//        absDialog.setModal(true);
        absDialog.setTitle(_title);
        absDialog.setLocationRelativeTo(_window.getCommonFrame());
        _pre.getBeanNatLgNames().setDataBase(facade);
        RenderedPage session_ = FrameHtmlData.initializeOnlyConf(_pre, _facade.getLanguage(), _pre.getBeanNatLgNames(), window.getFrames());
        session_.setFrame(absDialog);
        AbsPanel panel_ = window.getCompoFactory().newPageBox();
        AbsPlainLabel area_ = window.getCompoFactory().newPlainLabel(TEXT);
        AbsTextField field_;
//        LabelButton search_ = window.getCompoFactory().newPlainButton(MainWindow.OK);
        AbsButton search_ = window.getCompoFactory().newPlainButton(messages_.getVal(MessagesRenderPkGameDetail.SEARCH_LABEL));
        field_ = window.getCompoFactory().newTextField(20);
//        session.setLabel(area_);
        session_.addFinder(field_,search_);
//        JPanel group_ = new JPanel();
//        group_.setLayout(new BoxLayout(group_, BoxLayout.PAGE_AXIS));
        session_.getScroll().setPreferredSize(new MetaDimension(400, 400));
//        group_.add(scrollSession_);
//        JScrollPane scrollTextArea_ = new JScrollPane(area_);
//        group_.add(scrollTextArea_);
        panel_.add(session_.getScroll());
        panel_.add(area_);
        panel_.add(field_);
        panel_.add(search_);
        absDialog.setContentPane(panel_);
//        timer = new Timer(200, new Chronometer(area_, session_, 0));
//        timer.start();
//        absDialog.setDefaultCloseOperation(GuiConstants.DO_NOTHING_ON_CLOSE);
        absDialog.pack();
        absDialog.setVisible(true);
    }

//    public void closeWindow() {
//        absDialog.closeWindow();
////        session.clearSession();
//        closeDial();
//    }

    public void closeDial() {
        window.getModal().set(false);
        facade.initIv();
        window.getFrames().getCompoFactory().invokeNow(new AfterSettingDifficutyThread(window, facade));
    }

    public AbsCommonFrame getAbsDialog() {
        return absDialog;
    }
}
