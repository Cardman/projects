package aiki.gui.dialogs;




import aiki.beans.*;
import aiki.beans.abilities.*;
import aiki.beans.effects.*;
import aiki.beans.endround.*;
import aiki.beans.fight.*;
import aiki.beans.help.*;
import aiki.beans.items.*;
import aiki.beans.map.*;
import aiki.beans.map.characters.*;
import aiki.beans.map.elements.*;
import aiki.beans.moves.*;
import aiki.beans.pokemon.*;
import aiki.beans.simulation.*;
import aiki.beans.solution.*;
import aiki.facade.FacadeGame;
import aiki.sml.*;
import aiki.gui.WindowAiki;
import code.bean.nat.FixCharacterCaseConverter;
import code.gui.*;
import code.gui.document.*;
import code.gui.events.ClosingChildFrameEvent;
import code.gui.images.MetaDimension;

public final class FrameHtmlData extends GroupFrame implements AbsChildFrame {
//    private static final String DIALOG_ACCESS = "aiki.gui.dialogs.framehtmldata";

    private static final String TEXT = "0";

//    private static final String SEARCH_LABEL = "searchLabel";

    private final RenderedPage session;

//    private StringMap<String> messages;

    private final AbsButton search;

    private final ProgressingWebDialog dialog;
    private final EnabledMenu menuItem;
    private final WindowAiki window;
    private final WrapBeanRender wrapBeanRender;

    public FrameHtmlData(WindowAiki _parent, EnabledMenu _m) {
        super(_parent.getFrames());
        window = _parent;
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
        wrapBeanRender.getRenders().addEntry(CommonBean.WEB_FIGHT_HTML_FIGHT_HTML,new FightBean());
        wrapBeanRender.getRenders().addEntry(CommonBean.WEB_FIGHT_HTML_FIGHTDETAIL_HTML,new FightCalculationBean());
        wrapBeanRender.getRenders().addEntry(CommonBean.WEB_FIGHT_HTML_TEAM_HTML,new TeamBean());
        wrapBeanRender.getRenders().addEntry(CommonBean.WEB_FIGHT_HTML_FIGHTER_HTML,new FighterBean());
    }
    public void initDataBeans() {
        wrapBeanRender.getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_INDEX_HTML,new WelcomeBean());
        wrapBeanRender.getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_POKEMON_POKEDEX_HTML,new PokedexBean());
        wrapBeanRender.getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_POKEMON_DATA_HTML,new PokemonBean());
        wrapBeanRender.getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_MOVES_MOVES_HTML,new MovesBean());
        wrapBeanRender.getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_MOVES_DATA_HTML,new MoveBean());
        wrapBeanRender.getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_ABILITY_ABILITIES_HTML,new AbilitiesBean());
        wrapBeanRender.getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_ABILITY_DATA_HTML,new AbilityBean());
        wrapBeanRender.getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_ENDROUND_ENDROUND_HTML,new EndRoundBean());
        wrapBeanRender.getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_COMBO_COMBOS_HTML,new CombosBean());
        wrapBeanRender.getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_ITEMS_ITEMS_HTML,new ItemsBean());
        wrapBeanRender.getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_ITEMS_ITEMFORBATTLE_HTML,new ItemForBattleBean());
        wrapBeanRender.getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_ITEMS_BALL_HTML,new BallBean());
        wrapBeanRender.getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_ITEMS_BERRY_HTML,new BerryBean());
        wrapBeanRender.getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_ITEMS_BOOST_HTML,new BoostBean());
        wrapBeanRender.getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_ITEMS_EVO_ITEM_HTML,new EvolvingItemBean());
        wrapBeanRender.getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_ITEMS_EVO_STONE_HTML,new EvolvingStoneBean());
        wrapBeanRender.getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_ITEMS_HEALINGPP_HTML,new HealingPpBean());
        wrapBeanRender.getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_ITEMS_HEALINGHP_HTML,new HealingHpBean());
        HealingStatusBean healing_ = new HealingStatusBean();
        wrapBeanRender.getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_ITEMS_HEALINGHPSTATUS_HTML, healing_);
        wrapBeanRender.getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_ITEMS_HEALINGSTATUS_HTML, healing_);
        wrapBeanRender.getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_ITEMS_HEALINGITEM_HTML,new SimpleHealingItemBean());
        wrapBeanRender.getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_ITEMS_SELLINGITEM_HTML,new SellingItemBean());
        wrapBeanRender.getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_MAP_MAP_HTML,new MapBean());
        wrapBeanRender.getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_MAP_LEVEL_HTML,new MapLevelBean());
        wrapBeanRender.getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_MAP_ELEMENTS_LEG_PK_HTML,new LegendaryPokemonBean());
        TrainerBean trainer_ = new TrainerBean();
        wrapBeanRender.getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_MAP_ELEMENTS_TRAINER_MULTI_FIGHT_HTML,trainer_);
        wrapBeanRender.getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_MAP_ELEMENTS_TRAINER_ONE_FIGHT_HTML,trainer_);
        wrapBeanRender.getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_MAP_ELEMENTS_DUAL_FIGHT_HTML,new DualFightBean());
        wrapBeanRender.getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_MAP_ELEMENTS_AREA_HTML,new AreaBean());
        wrapBeanRender.getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_MAP_ELEMENTS_DEALER_HTML,new DealerBean());
        wrapBeanRender.getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_MAP_ELEMENTS_SELLER_HTML,new SellerBean());
        wrapBeanRender.getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_GENERAL_GENERAL_HTML,new GeneralHelpBean());
        wrapBeanRender.getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_LANGS_LANGS_HTML,new LangsBean());
        wrapBeanRender.getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_SOLUTION_SOLUTION_HTML,new SolutionBean());
        wrapBeanRender.getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_ROUND_HELPROUND_HTML,new FightHelpBean());
    }
    public void initSimuBeans() {
        wrapBeanRender.getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML, new SimulationBean());
        wrapBeanRender.getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_SIMULATION_SIMULATIONLEVEL_HTML, new SimulationLevelBean());
        wrapBeanRender.getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_SIMULATION_EDITPOKEMONTRAINER_HTML, new EditTrainerPokemonBean());
        wrapBeanRender.getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_SIMULATION_EDITPOKEMON_HTML, new EditPokemonBean());
        wrapBeanRender.getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_SIMULATION_EDITPOKEMONMOVES_HTML, new EditPokemonMovesBean());
        wrapBeanRender.getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_SIMULATION_SELECTABILITY_HTML, new SelectAbilityBean());
        wrapBeanRender.getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_SIMULATION_SELECTITEM_HTML, new SelectItemBean());
        wrapBeanRender.getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_SIMULATION_SELECTPOKEMON_HTML, new SelectPokemonBean());
        wrapBeanRender.getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_SIMULATION_ADDPOKEMON_HTML, new AddPokemonBean());
    }
    public void setDialogIcon(AbsCommonFrame _group) {
        setIconImage(_group.getImageIconFrame());
        setImageIconFrame(_group.getImageIconFrame());
    }
//    public static void initializeOnlyConf(AikiNatLgNamesNavigation _prepared, String _lg, BeanNatCommonLgNamesForm _stds, RenderedPage _cur) {
//        NatNavigation n_ = _prepared.getNavigation();
//        n_.setLanguage(_lg);
//        coreInfos(_cur, n_);
//        _cur.getNavCore().setLanguage(_lg);
//        _cur.setStandards(_stds);
//        _cur.setRenderAction(new NatRenderAction(_stds,n_));
//        _stds.initializeRendSessionDoc(n_);
//        _cur.setupText();
//    }
//
//    public static RenderedPage initializeOnlyConf(AikiNatLgNamesNavigation _prepared, String _lg, BeanNatCommonLgNamesForm _stds, AbstractProgramInfos _pr, AbsActionListenerAct _guard) {
//        AbsScrollPane ascenseur_=_pr.getCompoFactory().newAbsScrollPane();
//        RenderedPage r_ = new RenderedPage(ascenseur_, _pr,new FixCharacterCaseConverter(), _guard);
////        r_.setBase(GamesPk.baseEncode(_pr.getTranslations()));
//        NatNavigation n_ = _prepared.getNavigation();
//        n_.setLanguage(_lg);
//        coreInfos(r_, n_);
//        r_.getNavCore().setLanguage(_lg);
//        r_.setStandards(_stds);
//        r_.setRenderAction(new NatRenderAction(_stds,n_));
//        _stds.initializeRendSessionDoc(n_);
//        r_.setupText();
//        return r_;
//    }

//    public static void coreInfos(RenderedPage _cur, NatNavigation _n) {
//        _cur.setNavCore(_n.getBean());
//        _cur.setKeys(_n.getSession().getRendKeyWords());
//    }
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

//    public void initSessionLg(FacadeGame _dataBase, AbstractFutureParam<AikiNatLgNamesNavigation> _pre, String _lg) {
//        AikiNatLgNamesNavigation res_ = _pre.attendreResultat();
//        initSessionLg(_dataBase, res_, _lg);
//    }

//    public void initSessionLg(FacadeGame _dataBase, AikiNatLgNamesNavigation _pr, String _lg) {
//        setVisible(true);
//        menuItem.setEnabled(false);
//        search.setText(MessagesPkGame.getPkGameDetailContentTr(MessagesPkGame.getAppliTr(window.getFrames().currentLg())).getMapping().getVal(MessagesRenderPkGameDetail.SEARCH_LABEL));
//        _pr.getBeanNatLgNames().setDataBase(_dataBase);
////        _pr.getBeanNatLgNames().setBaseEncode(GamesPk.baseEncode(window.getFrames().getTranslations()));
//        session.setProcess(videoLoading.getVideo(getGenerator(),getFileCoreStream(),getFrames(), window.getVideoBase()));
//        initializeOnlyConf(_pr, _lg, _pr.getBeanNatLgNames(), session);
//    }

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
