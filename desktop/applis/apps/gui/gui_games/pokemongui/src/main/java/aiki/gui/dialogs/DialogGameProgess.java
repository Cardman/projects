package aiki.gui.dialogs;


import aiki.beans.game.*;
import aiki.facade.FacadeGame;
import aiki.gui.WindowAiki;
import code.gui.*;
import code.gui.document.*;
import code.gui.initialize.AbstractProgramInfos;

public final class DialogGameProgess {
//    private static final String DIALOG_ACCESS = "aiki.gui.dialogs.dialoggameprogess";

//    private static final String TEXT = "0";

//    private static final String SEARCH_LABEL = "searchLabel";
    private final AbsCommonFrame absDialog;
    private WrapBeanRender wrapBeanRender;

    public DialogGameProgess(AbstractProgramInfos _frameFactory) {
        absDialog = _frameFactory.getFrameFactory().newCommonFrame();
//        absDialog.setAccessFile(DIALOG_ACCESS);
    }

    public static void setGameProgress(WindowAiki _window, String _title, FacadeGame _facade) {
        _window.getDialogGameProgess().init(_window, _title, _facade);
    }

    private void init(WindowAiki _window, String _title, FacadeGame _facade) {
        //super(_window, true);
        absDialog.setIconImage(_window.getCommonFrame().getImageIconFrame());
//        StringMap<String> messages_ = file(_window.getFrames().currentLg());
//        messages = WindowAiki.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, _window.getLanguageKey(), absDialog.getAccessFile());
//        absDialog.setModal(true);
        absDialog.setTitle(_title);
        absDialog.setLocationRelativeTo(_window.getCommonFrame());
//        _pre.getBeanNatLgNames().setDataBase(_facade);
//        _pre.getBeanNatLgNames().setBaseEncode(GamesPk.baseEncode(_window.getFrames().getTranslations()));
//        RenderedPage session_ = FrameHtmlData.initializeOnlyConf(_pre, _facade.getLanguage(), _pre.getBeanNatLgNames(), _window.getFrames(), _window.getGuardRender());
//        session_.setFrame(absDialog);
        AbsPanel panel_ = _window.getCompoFactory().newPageBox();
        wrapBeanRender = new WrapBeanRender(panel_);
        wrapBeanRender.getRenders().addEntry("",new GameProgressionBean());
        wrapBeanRender.display(getWrapBeanRender().getRenders().firstValue(), _window.getFrames(), _facade,absDialog);
//        AbsPlainLabel area_ = _window.getCompoFactory().newPlainLabel(TEXT);
//        AbsTextField field_;
////        LabelButton search_ = _window.getCompoFactory().newPlainButton(MainWindow.OK);
//        AbsButton search_ = _window.getCompoFactory().newPlainButton(messages_.getVal(MessagesRenderPkGameDetail.SEARCH_LABEL));
//        field_ = _window.getCompoFactory().newTextField(20);
////        session.setLabel(area_);
//        session_.addFinder(field_,search_);
////        JPanel group_ = new JPanel();
////        group_.setLayout(new BoxLayout(group_, BoxLayout.PAGE_AXIS));
//        session_.getScroll().setPreferredSize(new MetaDimension(400, 400));
////        group_.add(scrollSession_);
////        JScrollPane scrollTextArea_ = new JScrollPane(area_);
////        group_.add(scrollTextArea_);
//        panel_.add(session_.getScroll());
//        panel_.add(area_);
//        panel_.add(field_);
//        panel_.add(search_);
        absDialog.setContentPane(panel_);
//        absDialog.setDefaultCloseOperation(GuiConstants.DO_NOTHING_ON_CLOSE);
        absDialog.pack();
        absDialog.setVisible(true);
    }

    public WrapBeanRender getWrapBeanRender() {
        return wrapBeanRender;
    }

    public AbsCommonFrame getAbsDialog() {
        return absDialog;
    }

//    public static StringMap<String> file(TranslationsLg _lg) {
//        return MessagesPkGame.getPkGameDetailContentTr(MessagesPkGame.getAppliTr(_lg)).getMapping();
//    }
}
