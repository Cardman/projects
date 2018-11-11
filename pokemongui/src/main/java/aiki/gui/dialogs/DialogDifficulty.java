package aiki.gui.dialogs;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import aiki.Resources;
import aiki.beans.PokemonStandards;
import aiki.facade.FacadeGame;
import aiki.gui.MainWindow;
import aiki.gui.threads.AfterSettingDifficutyThread;
import code.gui.Dialog;
import code.gui.LabelButton;
import code.gui.Panel;
import code.gui.document.RenderedPage;
import code.util.StringMap;

public final class DialogDifficulty extends Dialog {
    private static final String DIALOG_ACCESS = "aiki.gui.dialogs.DialogDifficulty";

    private static final String TEXT = "0";

    private static final DialogDifficulty DIALOG = new DialogDifficulty();

    private static final String SEARCH_LABEL = "searchLabel";

//    private Timer timer;

    private MainWindow window;

    private StringMap<String> messages;

    private RenderedPage session;

    private FacadeGame facade;

    private DialogDifficulty() {
        setAccessFile(DIALOG_ACCESS);
    }

    public static void setDialogDifficulty(MainWindow _window, String _title, FacadeGame _facade) {
        DIALOG.init(_window, _title, _facade);
    }

    private void init(MainWindow _window, String _title, FacadeGame _facade) {
        setDialogIcon(_window);
        facade = _facade;
        window = _window;
        //super(_window, true);
        messages = getMessages(_window, Resources.MESSAGES_FOLDER);
        setModal(true);
        setTitle(_title);
        setLocationRelativeTo(_window);
        JScrollPane scrollSession_ = new JScrollPane();
        session = new RenderedPage(scrollSession_);
        session.setLanguage(_facade.getLanguage());
        session.setDataBase(_facade);
        session.setFrame(this);
        session.setFiles(Resources.ACCESS_TO_DEFAULT_FILES);
        session.initializeOnlyConf(Resources.ACCESS_TO_DEFAULT_DIFF, new PokemonStandards());
//        try {
////            session.setFiles(_facade.getData().getWebGame(), Resources.ACCESS_TO_DEFAULT_FILES);
//            session.setFiles(Resources.ACCESS_TO_DEFAULT_FILES);
////            if (_window.isSuccessfulCompile()) {
////                session.initialize(Resources.CONFIG_DIFF);
////            } else {
////                session.initialize(Resources.ACCESS_TO_DEFAULT_DIFF);
////            }
//            session.initializeOnlyConf(Resources.ACCESS_TO_DEFAULT_DIFF);
//        } catch (Throwable _0) {
//            _0.printStackTrace();
//        }

//        try {
//            session_.setFiles(_facade.getData().getWebGame(), new Map<String,String>());
//            session_.initialize(Resources.CONFIG_DIFF);
//        } catch (Exception e_) {
//            CompilingBeans.loadDefaultClassesAndClear();
//            session_.setRelativeFiles(Resources.ACCESS_TO_DEFAULT_FILES);
//            session_.initialize(Resources.ACCESS_TO_DEFAULT_DIFF);
//        }
        Panel panel_ = new Panel();
        JLabel area_ = new JLabel(TEXT);
        JTextField field_;
//        LabelButton search_ = new LabelButton(MainWindow.OK);
        LabelButton search_ = new LabelButton(messages.getVal(SEARCH_LABEL));
        field_ = new JTextField(20);
//        session.setLabel(area_);
        session.setSearchText(search_);
        session.setField(field_);
        session.addFinder();
        panel_.setLayout(new BoxLayout(panel_.getComponent(), BoxLayout.PAGE_AXIS));
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
        setContentPane(panel_);
//        timer = new Timer(200, new Chronometer(area_, session_, 0));
//        timer.start();
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        pack();
        setVisible(true);
    }

    @Override
    public void closeWindow() {
        super.closeWindow();
//        session.clearSession();
        facade.initIv();
        SwingUtilities.invokeLater(new AfterSettingDifficutyThread(window, facade));
    }
}
