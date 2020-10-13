package aiki.gui.dialogs;
import java.awt.Dimension;

import javax.swing.WindowConstants;

import aiki.beans.PokemonStandards;
import aiki.gui.threads.PreparedRenderedPages;
import aiki.sml.Resources;
import aiki.gui.MainWindow;
import code.gui.*;
import code.gui.document.RenderedPage;
import code.gui.events.ClosingChildFrameEvent;
import code.sml.stream.ExtractFromFiles;
import code.util.StringMap;

public final class FrameHtmlData extends ChildFrame {
    private static final String DIALOG_ACCESS = "aiki.gui.dialogs.framehtmldata";

    private static final String TEXT = "0";

    private static final String SEARCH_LABEL = "searchLabel";

    private RenderedPage session;

    private StringMap<String> messages;

    private LabelButton search;

    private ProgressingWebDialog dialog;

    public FrameHtmlData(MainWindow _parent, String _title, RenderedPage _session) {
        super(_parent.getLanguageKey());
        setAccessFile(DIALOG_ACCESS);
        messages = getMessages(_parent,Resources.MESSAGES_FOLDER);
        setDialogIcon(_parent);
        setLocationRelativeTo(_parent);
        setTitle(_title);
        dialog = new ProgressingWebDialog();
        setFocusableWindowState(true);
        session = _session;
        session.setFrame(this);
        session.setDialog(dialog);
        Panel panel_ = Panel.newPageBox();
        TextLabel area_ = new TextLabel(TEXT);
        TextField field_;
        search = new LabelButton(messages.getVal(SEARCH_LABEL));
        field_ = new TextField(20);
        session.setSearchText(search);
        session.setField(field_);
        session.addFinder();
        ScrollPane scrollSession_ = session.getScroll();
        scrollSession_.setPreferredSize(new Dimension(400, 400));
        panel_.add(scrollSession_);
        panel_.add(area_);
        panel_.add(field_);
        panel_.add(search);
        setContentPane(panel_);
        addWindowListener(new ClosingChildFrameEvent(this));
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        pack();
    }

    @Override
    public void closeWindow() {
        setVisible(false);
    }

    public void initSessionLg(Object _dataBase, PreparedRenderedPages _pre, String _lg) {
        setVisible(true);
        session.initializeOnlyConf(_dataBase,_pre, _lg);
    }

    public ProgressingWebDialog getDialog() {
        return dialog;
    }

    public void refresh(MainWindow _window) {
        String key_ = _window.getLanguageKey();
        setLanguageKey(key_);
        messages = ExtractFromFiles.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, key_, DIALOG_ACCESS);
        search.setTextAndSize(messages.getVal(SEARCH_LABEL));
        session.setLanguage(key_);
        session.refresh();
    }

    public void reset() {
        session.reset(new PokemonStandards());
    }

    public RenderedPage getSession() {
        return session;
    }

//    public void setBattle(Battle _battle) {
//        battle = _battle;
//    }

//    @Override
//    public void dispose() {
//        session.finish();
//        super.dispose();
////        session.setDataBase(null);
////        session.setNullFiles();
////        session = null;
//        if (parent != null) {
//            parent.toFront();
//            parent.requestFocus();
//            parent.clearHtmlDialogs();
//        }
//        if (battle != null) {
//            battle.clearHtmlDialogs();
//        }
//    }
}
