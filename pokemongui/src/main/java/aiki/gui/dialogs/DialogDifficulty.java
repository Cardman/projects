package aiki.gui.dialogs;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import aiki.beans.PokemonStandards;
import aiki.sml.Resources;
import aiki.facade.FacadeGame;
import aiki.gui.MainWindow;
import aiki.gui.threads.AfterSettingDifficutyThread;
import code.gui.*;
import code.gui.document.RenderedPage;
import code.util.StringMap;

public final class DialogDifficulty extends Dialog {
    private static final String DIALOG_ACCESS = "aiki.gui.dialogs.dialogdifficulty";

    private static final String TEXT = "0";

    private static final DialogDifficulty DIALOG = new DialogDifficulty();

    private static final String SEARCH_LABEL = "searchLabel";

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
        ScrollPane scrollSession_ = new ScrollPane();
        session = new RenderedPage(scrollSession_);
        session.setLanguage(_facade.getLanguage());
        session.setDataBase(_facade);
        session.setFrame(this);
        session.setFiles(Resources.ACCESS_TO_DEFAULT_FILES);
        session.initializeOnlyConf(Resources.ACCESS_TO_DEFAULT_DIFF, new PokemonStandards());
        Panel panel_ = Panel.newPageBox();
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
        CustComponent.invokeLater(new AfterSettingDifficutyThread(window, facade));
    }
}
