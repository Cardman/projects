package aiki.gui.dialogs;
import java.awt.Dimension;

import javax.swing.WindowConstants;

import aiki.beans.PokemonStandards;
import aiki.gui.threads.PreparedRenderedPages;
import aiki.sml.Resources;
import aiki.facade.FacadeGame;
import aiki.gui.WindowAiki;
import aiki.gui.threads.AfterSettingDifficutyThread;
import code.gui.*;
import code.gui.document.RenderedPage;
import code.gui.initialize.AbsFrameFactory;
import code.util.StringMap;

public final class DialogDifficulty implements AbsCloseableDialog {
    private static final String DIALOG_ACCESS = "aiki.gui.dialogs.dialogdifficulty";

    private static final String TEXT = "0";

    private static final String SEARCH_LABEL = "searchLabel";
    private final AbsDialog absDialog;

    private WindowAiki window;

    private StringMap<String> messages;

    private RenderedPage session;

    private FacadeGame facade;

    public DialogDifficulty(AbsFrameFactory _frameFactory) {
        absDialog = _frameFactory.newDialog(this);
        absDialog.setAccessFile(DIALOG_ACCESS);
    }

    public static void setDialogDifficulty(WindowAiki _window, String _title, FacadeGame _facade, PreparedRenderedPages _pre) {
        _window.getDialogDifficulty().init(_window, _title, _facade,_pre);
    }

    private void init(WindowAiki _window, String _title, FacadeGame _facade, PreparedRenderedPages _pre) {
        absDialog.setDialogIcon(_window.getImageFactory(),_window);
        facade = _facade;
        window = _window;
        //super(_window, true);
        messages = WindowAiki.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, _window.getLanguageKey(), absDialog.getAccessFile());
        absDialog.setModal(true);
        absDialog.setTitle(_title);
        absDialog.setLocationRelativeTo(_window);
        ScrollPane scrollSession_ = new ScrollPane();
        session = new RenderedPage(scrollSession_, window.getFrames());
        session.setFrame(absDialog);
        ((PokemonStandards)_pre.getBeanNatLgNames()).setDataBase(facade);
        session.initializeOnlyConf(_pre, _facade.getLanguage());
        AbsPanel panel_ = window.getCompoFactory().newPageBox();
        TextLabel area_ = new TextLabel(TEXT);
        TextField field_;
//        LabelButton search_ = new LabelButton(MainWindow.OK);
        LabelButton search_ = new LabelButton(messages.getVal(SEARCH_LABEL));
        field_ = new TextField(20);
//        session.setLabel(area_);
        session.setSearchText(search_);
        session.setField(field_);
        session.addFinder();
//        JPanel group_ = new JPanel();
//        group_.setLayout(new BoxLayout(group_, BoxLayout.PAGE_AXIS));
        scrollSession_.setPreferredSize(new Dimension(400, 400));
//        group_.add(scrollSession_);
//        JScrollPane scrollTextArea_ = new JScrollPane(area_);
//        group_.add(scrollTextArea_);
        panel_.add(scrollSession_);
        panel_.add(area_);
        panel_.add(field_);
        panel_.add(search_);
        absDialog.setContentPane(panel_);
//        timer = new Timer(200, new Chronometer(area_, session_, 0));
//        timer.start();
        absDialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        absDialog.pack();
        absDialog.setVisible(true);
    }

    public void closeWindow() {
        absDialog.closeWindow();
//        session.clearSession();
        facade.initIv();
        FrameUtil.invokeLater(new AfterSettingDifficutyThread(window, facade), window.getFrames());
    }
}
