package aiki.gui.dialogs;




import aiki.beans.PokemonStandards;
import aiki.gui.dialogs.events.ClosingDialogDifficulty;
import aiki.gui.threads.PreparedRenderedPages;
import aiki.sml.Resources;
import aiki.facade.FacadeGame;
import aiki.gui.WindowAiki;
import aiki.gui.threads.AfterSettingDifficutyThread;
import code.gui.*;
import code.gui.document.RenderedPage;
import code.gui.images.MetaDimension;
import code.gui.initialize.AbstractProgramInfos;
import code.util.StringMap;

public final class DialogDifficulty {
    private static final String DIALOG_ACCESS = "aiki.gui.dialogs.dialogdifficulty";

    private static final String TEXT = "0";

    private static final String SEARCH_LABEL = "searchLabel";
    private final AbsDialog absDialog;

    private WindowAiki window;

    private StringMap<String> messages;

    private RenderedPage session;

    private FacadeGame facade;

    public DialogDifficulty(AbstractProgramInfos _frameFactory) {
        absDialog = _frameFactory.getFrameFactory().newDialog(new ClosingDialogDifficulty(this));
        absDialog.setAccessFile(DIALOG_ACCESS);
    }

    public static void setDialogDifficulty(WindowAiki _window, String _title, FacadeGame _facade, PreparedRenderedPages _pre) {
        _window.getDialogDifficulty().init(_window, _title, _facade,_pre);
    }

    private void init(WindowAiki _window, String _title, FacadeGame _facade, PreparedRenderedPages _pre) {
        absDialog.setDialogIcon(_window.getImageFactory(),_window.getCommonFrame());
        facade = _facade;
        window = _window;
        //super(_window, true);
        messages = WindowAiki.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, _window.getLanguageKey(), absDialog.getAccessFile());
        absDialog.setModal(true);
        absDialog.setTitle(_title);
        absDialog.setLocationRelativeTo(_window.getCommonFrame());
        ((PokemonStandards)_pre.getBeanNatLgNames()).setDataBase(facade);
        session = FrameHtmlData.initializeOnlyConf(_pre, _facade.getLanguage(), ((PokemonStandards)_pre.getBeanNatLgNames()), window.getFrames());
        session.setFrame(absDialog);
        AbsPanel panel_ = window.getCompoFactory().newPageBox();
        AbsPlainLabel area_ = window.getCompoFactory().newPlainLabel(TEXT);
        AbsTextField field_;
//        LabelButton search_ = window.getCompoFactory().newPlainButton(MainWindow.OK);
        AbsPlainButton search_ = window.getCompoFactory().newPlainButton(messages.getVal(SEARCH_LABEL));
        field_ = window.getCompoFactory().newTextField(20);
//        session.setLabel(area_);
        session.addFinder(field_,search_);
//        JPanel group_ = new JPanel();
//        group_.setLayout(new BoxLayout(group_, BoxLayout.PAGE_AXIS));
        session.getScroll().setPreferredSize(new MetaDimension(400, 400));
//        group_.add(scrollSession_);
//        JScrollPane scrollTextArea_ = new JScrollPane(area_);
//        group_.add(scrollTextArea_);
        panel_.add(session.getScroll());
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
        facade.initIv();
        GuiBaseUtil.invokeLater(new AfterSettingDifficutyThread(window, facade), window.getFrames());
    }
}
