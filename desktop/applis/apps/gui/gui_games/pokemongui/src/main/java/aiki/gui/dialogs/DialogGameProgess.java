package aiki.gui.dialogs;
import java.awt.Dimension;

import javax.swing.WindowConstants;

import aiki.beans.PokemonStandards;
import aiki.gui.threads.PreparedRenderedPages;
import aiki.sml.Resources;
import aiki.facade.FacadeGame;
import aiki.gui.MainWindow;
import code.gui.*;
import code.gui.document.RenderedPage;
import code.util.StringMap;

public final class DialogGameProgess extends Dialog {
    private static final String DIALOG_ACCESS = "aiki.gui.dialogs.dialoggameprogess";

    private static final String TEXT = "0";

    private static final String SEARCH_LABEL = "searchLabel";

    private RenderedPage session;

    private StringMap<String> messages;

    public DialogGameProgess() {
        setAccessFile(DIALOG_ACCESS);
    }

    public static void setGameProgress(MainWindow _window, String _title, FacadeGame _facade,PreparedRenderedPages _pre) {
        _window.getDialogGameProgess().init(_window, _title, _facade,_pre);
    }

    private void init(MainWindow _window, String _title, FacadeGame _facade, PreparedRenderedPages _pre) {
        //super(_window, true);
        setDialogIcon(_window);
        messages = MainWindow.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, _window.getLanguageKey(), getAccessFile());
        setModal(true);
        setTitle(_title);
        setLocationRelativeTo(_window);
        ScrollPane scrollSession_ = new ScrollPane();
        session = new RenderedPage(scrollSession_, _window.getFrames());
        session.setFrame(this);
        ((PokemonStandards)_pre.getBeanNatLgNames()).setDataBase(_facade);
        session.initializeOnlyConf(_pre, _facade.getLanguage());
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
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        pack();
        setVisible(true);
    }

}
