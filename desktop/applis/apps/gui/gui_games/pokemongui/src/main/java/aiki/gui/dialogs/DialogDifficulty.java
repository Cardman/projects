package aiki.gui.dialogs;


import aiki.facade.FacadeGame;
import aiki.gui.WindowAiki;
import aiki.gui.dialogs.events.ClosingDialogDifficulty;
import aiki.gui.threads.AfterSettingDifficutyThread;
import code.gui.*;
import code.gui.document.DifficultyBeanRender;
import code.gui.document.WrapBeanRender;
import code.gui.initialize.AbstractProgramInfos;

public final class DialogDifficulty {
//    private static final String DIALOG_ACCESS = "aiki.gui.dialogs.dialogdifficulty";

//    private static final String TEXT = "0";

//    private static final String SEARCH_LABEL = "searchLabel";
    private final AbsCommonFrame absDialog;

    private WindowAiki window;

    private FacadeGame facade;
    private DifficultyBeanRender difficultyBeanRender;

    public DialogDifficulty(AbstractProgramInfos _frameFactory) {
        absDialog = _frameFactory.getFrameFactory().newCommonFrame();
        absDialog.addWindowListener(new ClosingDialogDifficulty(this));
//        absDialog.setAccessFile(DIALOG_ACCESS);
    }

    public static void setDialogDifficulty(WindowAiki _window, String _title, FacadeGame _facade) {
        _window.getDialogDifficulty().init(_window, _title, _facade);
    }

    private void init(WindowAiki _window, String _title, FacadeGame _facade) {
        _window.getModal().set(true);
        absDialog.setIconImage(_window.getCommonFrame().getImageIconFrame());
        facade = _facade;
        window = _window;
        //super(_window, true);
//        StringMap<String> messages_ = MessagesPkGame.getPkGameDetailContentTr(MessagesPkGame.getAppliTr(_window.getFrames().currentLg())).getMapping();
//        StringMap<String> messages_ = WindowAiki.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, _window.getLanguageKey(), absDialog.getAccessFile());
//        absDialog.setModal(true);
        absDialog.setTitle(_title);
        absDialog.setLocationRelativeTo(_window.getCommonFrame());
//        _pre.getBeanNatLgNames().setDataBase(facade);
//        _pre.getBeanNatLgNames().setBaseEncode(GamesPk.baseEncode(_window.getFrames().getTranslations()));
//        RenderedPage session_ = FrameHtmlData.initializeOnlyConf(_pre, _facade.getLanguage(), _pre.getBeanNatLgNames(), window.getFrames(), _window.getGuardRender());
//        session_.setFrame(absDialog);
        AbsPanel panel_ = window.getCompoFactory().newPageBox();
        WrapBeanRender wr_ = new WrapBeanRender(panel_);
        difficultyBeanRender = new DifficultyBeanRender();
        wr_.getRenders().addEntry("",difficultyBeanRender);
        wr_.display(wr_.getRenders().firstValue(), _window.getFrames(), _facade,absDialog);
//        AbsPlainLabel area_ = window.getCompoFactory().newPlainLabel(TEXT);
//        AbsTextField field_;
//        LabelButton search_ = window.getCompoFactory().newPlainButton(MainWindow.OK);
//        AbsButton search_ = window.getCompoFactory().newPlainButton(messages_.getVal(MessagesRenderPkGameDetail.SEARCH_LABEL));
//        field_ = window.getCompoFactory().newTextField(20);
//        session.setLabel(area_);
//        session_.addFinder(field_,search_);
//        JPanel group_ = new JPanel();
//        group_.setLayout(new BoxLayout(group_, BoxLayout.PAGE_AXIS));
//        session_.getScroll().setPreferredSize(new MetaDimension(400, 400));
//        group_.add(scrollSession_);
//        JScrollPane scrollTextArea_ = new JScrollPane(area_);
//        group_.add(scrollTextArea_);
//        panel_.add(session_.getScroll());
//        panel_.add(area_);
//        panel_.add(field_);
//        panel_.add(search_);
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

    public DifficultyBeanRender getDifficultyBeanRender() {
        return difficultyBeanRender;
    }
}
