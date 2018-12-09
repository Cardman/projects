package aiki.gui.dialogs;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import aiki.Resources;
import aiki.beans.PokemonStandards;
import aiki.facade.FacadeGame;
import aiki.gui.MainWindow;
import code.gui.Dialog;
import code.gui.LabelButton;
import code.gui.Panel;
import code.gui.document.RenderedPage;
import code.util.StringMap;

public final class DialogGameProgess extends Dialog {
    private static final String DIALOG_ACCESS = "aiki.gui.dialogs.dialoggameprogess";

    private static final String TEXT = "0";

    private static final DialogGameProgess DIALOG = new DialogGameProgess();

    private static final String SEARCH_LABEL = "searchLabel";

    private RenderedPage session;

    private StringMap<String> messages;

    private DialogGameProgess() {
        setAccessFile(DIALOG_ACCESS);
    }

    public static void setGameProgress(MainWindow _window, String _title, FacadeGame _facade) {
        DIALOG.init(_window, _title, _facade);
    }

    private void init(MainWindow _window, String _title, FacadeGame _facade) {
        //super(_window, true);
        setDialogIcon(_window);
        messages = getMessages(_window,Resources.MESSAGES_FOLDER);
        setModal(true);
        setTitle(_title);
        setLocationRelativeTo(_window);
        JScrollPane scrollSession_ = new JScrollPane();
        session = new RenderedPage(scrollSession_);
        //session.setProcess(VideoLoading.getVideo());
        session.setLanguage(_facade.getLanguage());
        session.setDataBase(_facade);
        //dialog.setLocationRelativeTo(this);
        //session.setDialog(dialog);
        session.setFrame(this);
        session.setFiles(Resources.ACCESS_TO_DEFAULT_FILES);
        session.initializeOnlyConf(Resources.ACCESS_TO_DEFAULT_PROG, new PokemonStandards());
//        try {
////            session.setFiles(_facade.getData().getWebProg(), Resources.ACCESS_TO_DEFAULT_FILES);
//            session.setFiles(Resources.ACCESS_TO_DEFAULT_FILES);
////            if (_window.isSuccessfulCompile()) {
//////                session_.initializeOnlyConf(Resources.CONFIG_PROG);
////                session.initialize(Resources.CONFIG_PROG);
////            } else {
//////                session_.initializeOnlyConf(Resources.ACCESS_TO_DEFAULT_PROG);
////                session.initialize(Resources.ACCESS_TO_DEFAULT_PROG);
////            }
//            session.initializeOnlyConf(Resources.ACCESS_TO_DEFAULT_PROG);
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
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        pack();
        setVisible(true);
    }

    @Override
    public void closeWindow() {
        session.interrupt();
        super.closeWindow();
//        session.clearSession();
    }
}
