package aiki.gui.dialogs;




import aiki.beans.BeanNatCommonLgNamesForm;
import aiki.beans.PokemonStandards;
import aiki.facade.FacadeGame;
import aiki.gui.threads.PreparedRenderedPages;
import aiki.sml.Resources;
import aiki.gui.WindowAiki;
import code.bean.nat.FixCharacterCaseConverter;
import code.bean.nat.NatNavigation;
import code.gui.*;
import code.gui.document.NatRenderAction;
import code.gui.document.PreparedAnalyzed;
import code.gui.document.RenderedPage;
import code.gui.events.ClosingChildFrameEvent;
import code.gui.images.MetaDimension;
import code.gui.initialize.AbstractProgramInfos;
import code.util.StringMap;

public final class FrameHtmlData extends GroupFrame implements AbsChildFrame {
    private static final String DIALOG_ACCESS = "aiki.gui.dialogs.framehtmldata";

    private static final String TEXT = "0";

    private static final String SEARCH_LABEL = "searchLabel";

    private final RenderedPage session;

    private StringMap<String> messages;

    private final AbsButton search;

    private final ProgressingWebDialog dialog;
    private final EnabledMenu menuItem;

    public FrameHtmlData(WindowAiki _parent, String _title, RenderedPage _session, EnabledMenu _m) {
        super(_parent.getLanguageKey(),_parent.getFrames());
        setAccessFile(DIALOG_ACCESS);
        messages = WindowAiki.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, _parent.getLanguageKey(), getAccessFile());
        setDialogIcon(_parent.getCommonFrame());
        getCommonFrame().setLocationRelativeTo(_parent.getCommonFrame());
        setTitle(_title);
        dialog = new ProgressingWebDialog(_parent.getFrames());
        setFocusableWindowState(true);
        session = _session;
        session.setFrame(getCommonFrame());
        session.setDialog(dialog);
        AbsPanel panel_ = _parent.getCompoFactory().newPageBox();
        AbsPlainLabel area_ = _parent.getCompoFactory().newPlainLabel(TEXT);
        AbsTextField field_;
        search = _parent.getCompoFactory().newPlainButton(messages.getVal(SEARCH_LABEL));
        field_ = _parent.getCompoFactory().newTextField(20);
        session.addFinder(field_,search);
        AbsScrollPane scrollSession_ = session.getScroll();
        scrollSession_.setPreferredSize(new MetaDimension(400, 400));
        panel_.add(scrollSession_);
        panel_.add(area_);
        panel_.add(field_);
        panel_.add(search);
        setContentPane(panel_);
        addWindowListener(new ClosingChildFrameEvent(this));
//        setDefaultCloseOperation(GuiConstants.DO_NOTHING_ON_CLOSE);
        pack();
        menuItem = _m;
        menuItem.setEnabled(false);
    }
    public void setDialogIcon(AbsCommonFrame _group) {
        setIconImage(_group.getImageIconFrame());
        setImageIconFrame(_group.getImageIconFrame());
    }
    public static void initializeOnlyConf(PreparedAnalyzed _prepared, String _lg, BeanNatCommonLgNamesForm _stds, RenderedPage _cur) {
        NatNavigation n_ = _prepared.getNavigation();
        n_.setLanguage(_lg);
        coreInfos(_cur, n_);
        _cur.getNavCore().setLanguage(_lg);
        _cur.setStandards(_stds);
        _cur.setRenderAction(new NatRenderAction(_stds,n_));
        _stds.initializeRendSessionDoc(n_);
        _cur.setupText();
    }

    public static RenderedPage initializeOnlyConf(PreparedAnalyzed _prepared, String _lg, BeanNatCommonLgNamesForm _stds, AbstractProgramInfos _pr) {
        AbsScrollPane ascenseur_=_pr.getCompoFactory().newAbsScrollPane();
        RenderedPage r_ = new RenderedPage(ascenseur_, _pr,new FixCharacterCaseConverter());
        NatNavigation n_ = _prepared.getNavigation();
        n_.setLanguage(_lg);
        coreInfos(r_, n_);
        r_.getNavCore().setLanguage(_lg);
        r_.setStandards(_stds);
        r_.setRenderAction(new NatRenderAction(_stds,n_));
        _stds.initializeRendSessionDoc(n_);
        r_.setupText();
        return r_;
    }

    public static void coreInfos(RenderedPage _cur, NatNavigation _n) {
        _cur.setNavCore(_n.getBean());
        _cur.setKeys(_n.getSession().getRendKeyWords());
    }
    @Override
    public void closeWindow() {
        setVisible(false);
        menuItem.setEnabled(true);
    }

    public void initSessionLg(FacadeGame _dataBase, PreparedRenderedPages _pre, String _lg) {
        setVisible(true);
        menuItem.setEnabled(false);
        ((PokemonStandards)_pre.getBeanNatLgNames()).setDataBase(_dataBase);
        initializeOnlyConf(_pre, _lg,((PokemonStandards)_pre.getBeanNatLgNames()), session);
    }

    public ProgressingWebDialog getDialog() {
        return dialog;
    }

    public void refresh(WindowAiki _window) {
        String key_ = _window.getLanguageKey();
        setLanguageKey(key_);
        messages = WindowAiki.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, key_, DIALOG_ACCESS);
        search.setText(messages.getVal(SEARCH_LABEL));
        session.setLanguage(key_,getFrames().getLanguages());
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
