package aiki.gui.dialogs;




import aiki.beans.PokemonStandards;
import aiki.facade.FacadeGame;
import aiki.gui.threads.PreparedRenderedPages;
import aiki.sml.Resources;
import aiki.gui.WindowAiki;
import code.gui.*;
import code.gui.document.RenderedPage;
import code.gui.events.ClosingChildFrameEvent;
import code.gui.images.MetaDimension;
import code.util.StringMap;

public final class FrameHtmlData extends ChildFrame {
    private static final String DIALOG_ACCESS = "aiki.gui.dialogs.framehtmldata";

    private static final String TEXT = "0";

    private static final String SEARCH_LABEL = "searchLabel";

    private final RenderedPage session;

    private StringMap<String> messages;

    private final AbsPlainButton search;

    private final ProgressingWebDialog dialog;

    public FrameHtmlData(WindowAiki _parent, String _title, RenderedPage _session) {
        super(_parent.getLanguageKey(),_parent);
        setAccessFile(DIALOG_ACCESS);
        messages = WindowAiki.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, _parent.getLanguageKey(), getAccessFile());
        setDialogIcon(_parent.getCommonFrame());
        setLocationRelativeTo(_parent);
        setTitle(_title);
        dialog = new ProgressingWebDialog(_parent.getFrames());
        setFocusableWindowState(true);
        session = _session;
        session.setFrame(this);
        session.setDialog(dialog);
        AbsPanel panel_ = _parent.getCompoFactory().newPageBox();
        AbsPlainLabel area_ = _parent.getCompoFactory().newPlainLabel(TEXT);
        AbsTextField field_;
        search = _parent.getCompoFactory().newPlainButton(messages.getVal(SEARCH_LABEL));
        field_ = _parent.getCompoFactory().newTextField(20);
        session.setSearchText(search);
        session.setField(field_);
        session.addFinder();
        AbsScrollPane scrollSession_ = session.getScroll();
        scrollSession_.setPreferredSize(new MetaDimension(400, 400));
        panel_.add(scrollSession_);
        panel_.add(area_);
        panel_.add(field_);
        panel_.add(search);
        setContentPane(panel_);
        addWindowListener(new ClosingChildFrameEvent(this));
        setDefaultCloseOperation(GuiConstants.DO_NOTHING_ON_CLOSE);
        pack();
    }

    @Override
    public void closeWindow() {
        setVisible(false);
    }

    public void initSessionLg(FacadeGame _dataBase, PreparedRenderedPages _pre, String _lg) {
        setVisible(true);
        ((PokemonStandards)_pre.getBeanNatLgNames()).setDataBase(_dataBase);
        session.initializeOnlyConf(_pre, _lg);
    }

    public ProgressingWebDialog getDialog() {
        return dialog;
    }

    public void refresh(WindowAiki _window) {
        String key_ = _window.getLanguageKey();
        setLanguageKey(key_);
        messages = WindowAiki.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, key_, DIALOG_ACCESS);
        search.setText(messages.getVal(SEARCH_LABEL));
        session.setLanguage(key_);
//        session.refresh();
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
