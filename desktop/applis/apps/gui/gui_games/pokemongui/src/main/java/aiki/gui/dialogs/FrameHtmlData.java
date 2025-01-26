package aiki.gui.dialogs;




import aiki.beans.BeanNatCommonLgNamesForm;
import aiki.facade.FacadeGame;
import aiki.main.AikiNatLgNamesNavigation;
import aiki.main.VideoLoading;
import aiki.sml.*;
import aiki.gui.WindowAiki;
import code.bean.nat.FixCharacterCaseConverter;
import code.bean.nat.NatNavigation;
import code.gui.*;
import code.gui.document.*;
import code.gui.events.AbsActionListenerAct;
import code.gui.events.ClosingChildFrameEvent;
import code.gui.images.MetaDimension;
import code.gui.initialize.AbstractProgramInfos;
import code.scripts.confs.PkScriptPages;
import code.threads.AbstractFutureParam;

public final class FrameHtmlData extends GroupFrame implements AbsChildFrame {
//    private static final String DIALOG_ACCESS = "aiki.gui.dialogs.framehtmldata";

    private static final String TEXT = "0";

//    private static final String SEARCH_LABEL = "searchLabel";

    private final RenderedPage session;
    private final VideoLoading videoLoading;

//    private StringMap<String> messages;

    private final AbsButton search;

    private final ProgressingWebDialog dialog;
    private final EnabledMenu menuItem;
    private final WindowAiki window;
    private final WrapBeanRender wrapBeanRender;

    public FrameHtmlData(WindowAiki _parent, EnabledMenu _m) {
        super(_parent.getFrames());
        window = _parent;
        videoLoading = _parent.getVideoLoading();
//        setAccessFile(DIALOG_ACCESS);
//        messages = WindowAiki.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, _parent.getLanguageKey(), getAccessFile());
        setDialogIcon(_parent.getCommonFrame());
        getCommonFrame().setLocationRelativeTo(_parent.getCommonFrame());
        dialog = new ProgressingWebDialog(_parent.getFrames());
        setFocusableWindowState(true);
        RenderedPage session_;
        session_ = new RenderedPage(getFrames().getCompoFactory().newAbsScrollPane(), _parent.getFrames(),new FixCharacterCaseConverter(), _parent.getGuardRender());
//        session_.setBase(GamesPk.baseEncode(_parent.getFrames().getTranslations()));
        session = session_;
        session.setFrame(getCommonFrame());
        session.setDialog(getDialog());
        AbsPanel panel_ = _parent.getCompoFactory().newPageBox();
        wrapBeanRender = new WrapBeanRender(panel_);
        AbsPlainLabel area_ = _parent.getCompoFactory().newPlainLabel(TEXT);
        AbsTextField field_;
        search = _parent.getCompoFactory().newPlainButton();
//        search = _parent.getCompoFactory().newPlainButton(messages.getVal(SEARCH_LABEL));
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
    public void initFightBeans() {
        wrapBeanRender.getRenders().addEntry(PkScriptPages.WEB_FIGHT_HTML_FIGHT_HTML,new FightBeanRender());
        wrapBeanRender.getRenders().addEntry(PkScriptPages.WEB_FIGHT_HTML_FIGHTDETAIL_HTML,new FightCalculationBeanRender());
        wrapBeanRender.getRenders().addEntry(PkScriptPages.WEB_FIGHT_HTML_TEAM_HTML,new TeamBeanRender());
        wrapBeanRender.getRenders().addEntry(PkScriptPages.WEB_FIGHT_HTML_FIGHTER_HTML,new FighterBeanRender());
    }
    public void setDialogIcon(AbsCommonFrame _group) {
        setIconImage(_group.getImageIconFrame());
        setImageIconFrame(_group.getImageIconFrame());
    }
    public static void initializeOnlyConf(AikiNatLgNamesNavigation _prepared, String _lg, BeanNatCommonLgNamesForm _stds, RenderedPage _cur) {
        NatNavigation n_ = _prepared.getNavigation();
        n_.setLanguage(_lg);
        coreInfos(_cur, n_);
        _cur.getNavCore().setLanguage(_lg);
        _cur.setStandards(_stds);
        _cur.setRenderAction(new NatRenderAction(_stds,n_));
        _stds.initializeRendSessionDoc(n_);
        _cur.setupText();
    }

    public static RenderedPage initializeOnlyConf(AikiNatLgNamesNavigation _prepared, String _lg, BeanNatCommonLgNamesForm _stds, AbstractProgramInfos _pr, AbsActionListenerAct _guard) {
        AbsScrollPane ascenseur_=_pr.getCompoFactory().newAbsScrollPane();
        RenderedPage r_ = new RenderedPage(ascenseur_, _pr,new FixCharacterCaseConverter(), _guard);
//        r_.setBase(GamesPk.baseEncode(_pr.getTranslations()));
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

    public void initSession(FacadeGame _dataBase) {
        wrapBeanRender.display(wrapBeanRender.getRenders().firstValue(),getFrames(),_dataBase,getCommonFrame());
        setVisible(true);
        menuItem.setEnabled(false);
        search.setText(MessagesPkGame.getPkGameDetailContentTr(MessagesPkGame.getAppliTr(window.getFrames().currentLg())).getMapping().getVal(MessagesRenderPkGameDetail.SEARCH_LABEL));
    }

    public void initSessionLg(FacadeGame _dataBase, AbstractFutureParam<AikiNatLgNamesNavigation> _pre, String _lg) {
        AikiNatLgNamesNavigation res_ = _pre.attendreResultat();
        initSessionLg(_dataBase, res_, _lg);
    }

    public void initSessionLg(FacadeGame _dataBase, AikiNatLgNamesNavigation _pr, String _lg) {
        setVisible(true);
        menuItem.setEnabled(false);
        search.setText(MessagesPkGame.getPkGameDetailContentTr(MessagesPkGame.getAppliTr(window.getFrames().currentLg())).getMapping().getVal(MessagesRenderPkGameDetail.SEARCH_LABEL));
        _pr.getBeanNatLgNames().setDataBase(_dataBase);
//        _pr.getBeanNatLgNames().setBaseEncode(GamesPk.baseEncode(window.getFrames().getTranslations()));
        session.setProcess(videoLoading.getVideo(getGenerator(),getFileCoreStream(),getFrames(), window.getVideoBase()));
        initializeOnlyConf(_pr, _lg, _pr.getBeanNatLgNames(), session);
    }

    public ProgressingWebDialog getDialog() {
        return dialog;
    }

    public void refresh() {
//        String key_ = _window.getLanguageKey();
//        setLanguageKey(key_);
//        messages = WindowAiki.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, key_, DIALOG_ACCESS);
        search.setText(MessagesPkGame.getPkGameDetailContentTr(MessagesPkGame.getAppliTr(window.getFrames().currentLg())).getMapping().getVal(MessagesRenderPkGameDetail.SEARCH_LABEL));
        session.setLanguage(window.getFrames().getLanguage(),getFrames().getLanguages());
//        session.refresh();
    }

    public RenderedPage getSession() {
        return session;
    }

    public WrapBeanRender getWrapBeanRender() {
        return wrapBeanRender;
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
