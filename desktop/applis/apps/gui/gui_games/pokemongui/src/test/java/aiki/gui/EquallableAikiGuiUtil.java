package aiki.gui;

import aiki.db.DataBase;
import aiki.game.Game;
import aiki.gui.components.*;
import aiki.gui.listeners.MouseTask;
import aiki.main.AikiFactory;
import aiki.main.AikiNatLgNamesNavigation;
import aiki.sml.*;
import code.bean.nat.NatNavigation;
import code.bean.nat.analyze.NatConfigurationCore;
import code.gui.*;
import code.maths.LgInt;
import code.maths.Rate;
import code.maths.montecarlo.CustomSeedGene;
import code.mock.*;
import code.sml.util.TranslationsAppli;
import code.sml.util.TranslationsLg;
import code.threads.AbstractThread;
import code.util.IdList;
import code.util.StringList;
import org.junit.Assert;

public abstract class EquallableAikiGuiUtil {
    public static final String FR = "fr";
    public static final String EN = "en";
    public static final String LANGUAGE = EN;


    public static WindowAiki newGame() {
        MockProgramInfos pr_ = buildListLgs();
        AikiFactory fact_ = pkFact(pr_);
        gameTr(pr_);
        return window(pr_, fact_);
    }

    public static WindowAiki newProg() {
        MockProgramInfos pr_ = buildListLgs();
        AikiFactory fact_ = pkFact(pr_);
        progTr(pr_);
        return window(pr_, fact_);
    }

    public static WindowAiki newSelEgg() {
        MockProgramInfos pr_ = buildListLgs();
        AikiFactory fact_ = pkFact(pr_);
        pagEgg(commonSel(pr_));
        return window(pr_, fact_);
    }

    public static WindowAiki newSelPk() {
        MockProgramInfos pr_ = buildListLgs();
        AikiFactory fact_ = pkFact(pr_);
        pagPk(commonSel(pr_));
        return window(pr_, fact_);
    }

    public static WindowAiki newSelMv() {
        MockProgramInfos pr_ = buildListLgs();
        AikiFactory fact_ = pkFact(pr_);
        pagMv(commonSel(pr_));
        return window(pr_, fact_);
    }

    public static WindowAiki newSelIt() {
        MockProgramInfos pr_ = buildListLgs();
        AikiFactory fact_ = pkFact(pr_);
        pagIt(commonSel(pr_));
        return window(pr_, fact_);
    }

    public static WindowAiki newSelHealIt() {
        MockProgramInfos pr_ = buildListLgs();
        AikiFactory fact_ = pkFact(pr_);
        pagHealIt(commonSel(pr_));
        return window(pr_, fact_);
    }

    public static WindowAiki newSelPkCons() {
        MockProgramInfos pr_ = buildListLgs();
        AikiFactory fact_ = pkFact(pr_);
        pkGameDetail(pagPk(commonSel(pr_)));
        return window(pr_, fact_);
    }

    public static WindowAiki newHostCons() {
        MockProgramInfos pr_ = buildListLgs();
        AikiFactory fact_ = pkFact(pr_);
        consHostTr(pr_);
        return window(pr_, fact_);
    }

    public static WindowAiki newFight() {
        MockProgramInfos pr_ = buildListLgs();
        AikiFactory fact_ = pkFact(pr_);
        fightTr(pr_);
        return window(pr_, fact_);
    }
    public static void preparePkTask(WindowAiki _window) {
        _window.getCore().getAikiFactory().submitNavPkTask(new MockCallable<AikiNatLgNamesNavigation>(new AikiNatLgNamesNavigation(new PokemonStandardsSample(),nav())));
        _window.setPreparedPkTask(_window.getCore().getAikiFactory().getTaskNavPkTask());
    }

    public static void prepareFightTask(WindowAiki _window) {
//        _window.getCore().getAikiFactory().setPreparedFightTask(new AikiNatLgNamesNavigation(new PokemonStandardsSample(),nav()));
//        _window.setPreparedFightTask(_window.getCore().getAikiFactory().getPreparedFightTask());
        _window.getCore().getAikiFactory().submitNavFight(new MockCallable<AikiNatLgNamesNavigation>(new AikiNatLgNamesNavigation(new PokemonStandardsSample(),nav())));
        _window.setPreparedFightTask(_window.getCore().getAikiFactory().getTaskNavFight());
    }

    public static void prepareDiffTask(WindowAiki _window) {
        _window.getCore().getAikiFactory().submitNavDiffTask(new MockCallable<AikiNatLgNamesNavigation>(new AikiNatLgNamesNavigation(new PokemonStandardsSample(),nav())));
        _window.setPreparedDiffTask(_window.getCore().getAikiFactory().getTaskNavDiff());
    }

    public static void prepareWebTask(WindowAiki _window) {
        _window.getCore().getAikiFactory().submitNavData(new MockCallable<AikiNatLgNamesNavigation>(new AikiNatLgNamesNavigation(new PokemonStandardsSample(),nav())));
        _window.setPreparedDataWebTask(_window.getCore().getAikiFactory().getTaskNavData());
    }
    public static void gameTr(MockProgramInfos _pr) {
        TranslationsLg en_ = _pr.lg(EN);
        TranslationsAppli app_ = GamesPk.initAppliTr(en_);
        GamesPk.appendHerosContent(app_, MessagesRenderHeros.en());
        windowPk(app_);
    }

    public static void progTr(MockProgramInfos _pr) {
        TranslationsLg en_ = _pr.lg(EN);
        pkGameDetail(GamesPk.initAppliTr(en_));
    }

    public static void fightTr(MockProgramInfos _pr) {
        TranslationsLg en_ = _pr.lg(EN);
        TranslationsAppli appli_ = GamesPk.initAppliTr(en_);
        windowPk(appli_);
        fightAction(appli_);
        searchMode(appli_);
        pagHealIt(appli_);
    }
    private static void pkGameDetail(TranslationsAppli _appli) {
        windowPk(_appli);
        GamesPk.appendPkGameDetailContent(_appli, MessagesRenderPkGameDetail.en());
    }
    public static void windowPk(TranslationsAppli _appli) {
        GamesPk.appendWindowPkContent(_appli, MessagesRenderWindowPk.en());
        GamesPk.appendScenePanelContent(_appli, MessagesRenderScenePanel.en());
        GamesPk.appendBattleContent(_appli, MessagesRenderBattle.en());
    }
    private static void fightAction(TranslationsAppli _appli) {
        GamesPk.appendFightActionContent(_appli, MessagesRenderActionType.en());
    }

    public static TranslationsAppli commonSel(MockProgramInfos _pr) {
        TranslationsAppli app_ = GamesPk.initAppliTr(_pr.lg(EN));
        searchMode(app_);
        windowPk(app_);
        return app_;
    }

    public static void searchMode(TranslationsAppli _appli) {
        GamesPk.appendPaginatorContent(_appli, MessagesRenderPaginatorSearchMode.en());
        GamesPk.appendPaginatorButtonsContent(_appli, MessagesRenderPaginatorButtons.en());
        GamesPk.appendSelectDialogContent(_appli, MessagesRenderPaginatorButtons.enSel());
    }

    public static TranslationsAppli pagEgg(TranslationsAppli _appli) {
        GamesPk.appendPaginatorEggContent(_appli, MessagesRenderPaginatorEgg.en());
        GamesPk.appendPaginatorSelEggContent(_appli, MessagesRenderPaginatorEgg.enTitle());
        return _appli;
    }

    public static TranslationsAppli pagPk(TranslationsAppli _appli) {
        GamesPk.appendPaginatorPkContent(_appli, MessagesRenderPaginatorPk.en());
        GamesPk.appendPaginatorSelPkContent(_appli, MessagesRenderPaginatorPk.enTitle());
        return _appli;
    }

    public static TranslationsAppli pagMv(TranslationsAppli _appli) {
        GamesPk.appendPaginatorMvContent(_appli, MessagesRenderPaginatorMove.en());
        GamesPk.appendPaginatorSelMvContent(_appli, MessagesRenderPaginatorMove.enTitle());
        return _appli;
    }

    public static TranslationsAppli pagIt(TranslationsAppli _appli) {
        GamesPk.appendPaginatorItContent(_appli, MessagesRenderPaginatorItem.en());
        GamesPk.appendPaginatorSelItContent(_appli, MessagesRenderPaginatorItem.enTitle());
        return _appli;
    }

    public static TranslationsAppli pagHealIt(TranslationsAppli _appli) {
        GamesPk.appendPaginatorHealItContent(_appli, MessagesRenderPaginatorHealingItem.en());
        GamesPk.appendPaginatorSelHealItContent(_appli, MessagesRenderPaginatorHealingItem.enTitle());
        return _appli;
    }

    public static void consHostTr(MockProgramInfos _pr) {
        TranslationsAppli app_ = GamesPk.initAppliTr(_pr.lg(EN));
        pkGameDetail(app_);
        windowPk(app_);
        GamesPk.appendConsultHostContent(app_,MessagesRenderConsultHost.en());
    }
    public static void loadGame(WindowAiki _window, Game _game) {
        _window.getFacade().load(_game);
        _window.getFacade().changeCamera();
        _window.afterLoadGame();
    }

    public static MockProgramInfos buildListLgs() {
        MockProgramInfos pr_ = build("/__/", "/_/", dbs(0.75));
        pr_.setLanguages(new StringList(EN));
        return pr_;
    }

    public static WindowAiki window(MockProgramInfos _pr, AikiFactory _fact) {
        WindowAiki wa_ = new WindowAiki(_pr, _fact, new LanguagesButtonsPair(_pr.getCompoFactory().newMenuItem(),null,null));
        wa_.setTaskEnabled(new MockTaskEnabled());
        wa_.pack();
        wa_.setVisible(true);
        wa_.getFacade().setSexList(new MockLSexList());
        wa_.getFacade().setLanguage(LANGUAGE);
        return wa_;
    }

    public static AikiFactory pkFact(MockProgramInfos _pr) {
        AikiFactory fact_ = new AikiFactory(_pr, new MockBaseExecutorServiceParam<AikiNatLgNamesNavigation>(), new MockBaseExecutorServiceParam<DataBase>());
        fact_.setConfPkStream(new MockConfPkStream());
        fact_.setGamePkStream(new MockGamePkStream());
        return fact_;
    }

    public static MockProgramInfos build() {
        return build("", "",dbs(0.75));
    }
    public static MockProgramInfos build(String _h, String _t, double[] _dbs) {
        MockProgramInfos pr_ = MockProgramInfos.inst(_h, _t, new CustomSeedGene(_dbs), new MockFileSet(0, new long[1], new String[]{"/"}));
        pr_.setLanguage(EN);
        return pr_;
    }

    public static AbstractThread tryAn(MockThreadFactory _g) {
        assertEq(1, _g.getAllThreads().size());
        AbstractThread th_ = _g.getAllThreads().get(0);
        _g.getAllThreads().remove(0);
        th_.join();
        checkNoAnim(_g);
        return th_;
    }
    public static AbstractThread tryAnNoCheck(MockThreadFactory _g) {
        assertEq(1, _g.getAllThreads().size());
        AbstractThread th_ = _g.getAllThreads().get(0);
        _g.getAllThreads().remove(0);
        th_.join();
        return th_;
    }

    public static void checkNoAnim(MockThreadFactory _thFact) {
        assertEq(0, _thFact.getAllThreads().size());
    }
    public static void tryClick(AbsButton _m) {
        assertTrue(_m.isVisible());
        assertTrue(_m.isEnabled());
        _m.getActionListeners().get(0).action();
    }
    public static void tryClick(AbsMetaLabelPk _m) {
        assertTrue(_m.getPaintableLabel().isVisible());
        assertTrue(_m.getPaintableLabel().isEnabled());
        _m.getPaintableLabel().getMouseListenersRel().get(0).mouseReleased(null, null, null);
    }
    public static void tryPress(AbsMetaLabelPk _m) {
        assertTrue(_m.getPaintableLabel().isVisible());
        assertTrue(_m.getPaintableLabel().isEnabled());
        MouseTask mt_ = (MouseTask) ((MockCustComponent) _m.getPaintableLabel()).getMouseWithoutClickListeners().get(0);
        mt_.mousePressed(null, null, null);
        ((MockBaseExecutorService)mt_.getTimer()).getTasks().lastValue().attendre();
        mt_.mouseReleased(null, null, null);
    }
    public static void tryPress(AbsMetaLabelPk _m, int _dir) {
        assertTrue(_m.getPaintableLabel().isVisible());
        assertTrue(_m.getPaintableLabel().isEnabled());
        ((MockAbstractAction) GuiBaseUtil.getAction(_m.getPaintableLabel(),_dir,0)).action();
    }
    public static void tryCheck(AbsCustCheckBox _ch, boolean _v) {
        assertTrue(((MockCustComponent) _ch).isDeepAccessible());
        _ch.setSelected(_v);
    }

    public static void tryToggle(AbsCustCheckBox _ch) {
        assertTrue(((MockCustComponent) _ch).isDeepAccessible());
        ((MockCustCheckBox)_ch).toggle();
    }

    public static double[] dbs(double... _args) {
        return _args;
    }

    public static void eventsCombo(ScrollCustomCombo _combo, int _i) {
        _combo.select(_i);
        _combo.events(null);
    }
//    public static TranslationsLg lg(MockProgramInfos _pr, String _key) {
//        return _pr.lg(_key);
//    }

    public static void checkCommon12(PaginatorEgg _pag, IdList<AbsCustComponent> _tr) {
        assertTrue(_tr.containsObj(_pag.getDelta()));
        assertTrue(_tr.containsObj(_pag.getNbResults()));
        assertTrue(_tr.containsObj(_pag.getMinSteps()));
        assertTrue(_tr.containsObj(_pag.getMaxSteps()));
        assertTrue(_tr.containsObj(_pag.getCmpStepsPrio().self()));
        assertTrue(_tr.containsObj(_pag.getCmpStepsSorting().self()));
        assertTrue(_tr.containsObj(_pag.getName()));
        assertTrue(_tr.containsObj(_pag.getModeName().self()));
        assertTrue(_tr.containsObj(_pag.getCmpNamePrio().self()));
        assertTrue(_tr.containsObj(_pag.getCmpNameSorting().self()));
        assertTrue(_tr.containsObj(_pag.getSearchButton()));
        assertTrue(_tr.containsObj(_pag.getNewSearchButton()));
    }

    public static void checkCommon30(PaginatorPokemon _pag, IdList<AbsCustComponent> _tr) {
        assertTrue(_tr.containsObj(_pag.getDelta()));
        assertTrue(_tr.containsObj(_pag.getNbResults()));
        assertTrue(_tr.containsObj(_pag.getCmpPossEvosPrio().self()));
        assertTrue(_tr.containsObj(_pag.getCmpPossEvosSorting().self()));
        assertTrue(_tr.containsObj(_pag.getCmpItemPrio().self()));
        assertTrue(_tr.containsObj(_pag.getCmpItemSorting().self()));
        assertTrue(_tr.containsObj(_pag.getCmpGenderPrio().self()));
        assertTrue(_tr.containsObj(_pag.getCmpGenderSorting().self()));
        assertTrue(_tr.containsObj(_pag.getCmpLevelPrio().self()));
        assertTrue(_tr.containsObj(_pag.getCmpLevelSorting().self()));
        assertTrue(_tr.containsObj(_pag.getCmpAbilityPrio().self()));
        assertTrue(_tr.containsObj(_pag.getCmpAbilitySorting().self()));
        assertTrue(_tr.containsObj(_pag.getCmpNamePrio().self()));
        assertTrue(_tr.containsObj(_pag.getCmpNameSorting().self()));
        assertTrue(_tr.containsObj(_pag.getName()));
        assertTrue(_tr.containsObj(_pag.getAbility()));
        assertTrue(_tr.containsObj(_pag.getMoves()));
        assertTrue(_tr.containsObj(_pag.getItem()));
        assertTrue(_tr.containsObj(_pag.getGender().self()));
        assertTrue(_tr.containsObj(_pag.getWithItem().self()));
        assertTrue(_tr.containsObj(_pag.getModeName().self()));
        assertTrue(_tr.containsObj(_pag.getModeAbility().self()));
        assertTrue(_tr.containsObj(_pag.getModeMoves().self()));
        assertTrue(_tr.containsObj(_pag.getModeItem().self()));
        assertTrue(_tr.containsObj(_pag.getMaxLevel()));
        assertTrue(_tr.containsObj(_pag.getMinLevel()));
        assertTrue(_tr.containsObj(_pag.getMaxPossEvos()));
        assertTrue(_tr.containsObj(_pag.getMinPossEvos()));
        assertTrue(_tr.containsObj(_pag.getSearchButton()));
        assertTrue(_tr.containsObj(_pag.getNewSearchButton()));
    }

    public static void checkCommon28(PaginatorMove _pag, IdList<AbsCustComponent> _tr) {
        assertTrue(_tr.containsObj(_pag.getDelta()));
        assertTrue(_tr.containsObj(_pag.getNbResults()));
        assertTrue(_tr.containsObj(_pag.getCmpDamagingPrio().self()));
        assertTrue(_tr.containsObj(_pag.getCmpDamagingSorting().self()));
        assertTrue(_tr.containsObj(_pag.getCmpPpPrio().self()));
        assertTrue(_tr.containsObj(_pag.getCmpPpSorting().self()));
        assertTrue(_tr.containsObj(_pag.getCmpPricePrio().self()));
        assertTrue(_tr.containsObj(_pag.getCmpPriceSorting().self()));
        assertTrue(_tr.containsObj(_pag.getCmpPrioPrio().self()));
        assertTrue(_tr.containsObj(_pag.getCmpPrioSorting().self()));
        assertTrue(_tr.containsObj(_pag.getCmpTargetsPrio().self()));
        assertTrue(_tr.containsObj(_pag.getCmpTargetsSorting().self()));
        assertTrue(_tr.containsObj(_pag.getCmpNamePrio().self()));
        assertTrue(_tr.containsObj(_pag.getCmpNameSorting().self()));
        assertTrue(_tr.containsObj(_pag.getName()));
        assertTrue(_tr.containsObj(_pag.getTypes()));
        assertTrue(_tr.containsObj(_pag.getTargets().self()));
        assertTrue(_tr.containsObj(_pag.getDamaging().self()));
        assertTrue(_tr.containsObj(_pag.getModeName().self()));
        assertTrue(_tr.containsObj(_pag.getModeTypes().self()));
        assertTrue(_tr.containsObj(_pag.getMaxPp()));
        assertTrue(_tr.containsObj(_pag.getMinPp()));
        assertTrue(_tr.containsObj(_pag.getMaxPriority()));
        assertTrue(_tr.containsObj(_pag.getMinPriority()));
        assertTrue(_tr.containsObj(_pag.getMaxPrice()));
        assertTrue(_tr.containsObj(_pag.getMinPrice()));
        assertTrue(_tr.containsObj(_pag.getSearchButton()));
        assertTrue(_tr.containsObj(_pag.getNewSearchButton()));
    }

    public static void checkCommon20(PaginatorItem _pag, IdList<AbsCustComponent> _tr) {
        assertTrue(_tr.containsObj(_pag.getDelta()));
        assertTrue(_tr.containsObj(_pag.getNbResults()));
        assertTrue(_tr.containsObj(_pag.getCmpDescriptionPrio().self()));
        assertTrue(_tr.containsObj(_pag.getCmpDescriptionSorting().self()));
        assertTrue(_tr.containsObj(_pag.getCmpNumberPrio().self()));
        assertTrue(_tr.containsObj(_pag.getCmpNumberSorting().self()));
        assertTrue(_tr.containsObj(_pag.getCmpPricePrio().self()));
        assertTrue(_tr.containsObj(_pag.getCmpPriceSorting().self()));
        assertTrue(_tr.containsObj(_pag.getCmpNamePrio().self()));
        assertTrue(_tr.containsObj(_pag.getCmpNameSorting().self()));
        assertTrue(_tr.containsObj(_pag.getName()));
        assertTrue(_tr.containsObj(_pag.getDescription()));
        assertTrue(_tr.containsObj(_pag.getModeName().self()));
        assertTrue(_tr.containsObj(_pag.getModeDescription().self()));
        assertTrue(_tr.containsObj(_pag.getMaxNumber()));
        assertTrue(_tr.containsObj(_pag.getMinNumber()));
        assertTrue(_tr.containsObj(_pag.getMaxPrice()));
        assertTrue(_tr.containsObj(_pag.getMinPrice()));
        assertTrue(_tr.containsObj(_pag.getSearchButton()));
        assertTrue(_tr.containsObj(_pag.getNewSearchButton()));
    }

    public static void checkCommon45(PaginatorHealingItem _pag, IdList<AbsCustComponent> _tr) {
        assertTrue(_tr.containsObj(_pag.getDelta()));
        assertTrue(_tr.containsObj(_pag.getNbResults()));
        assertTrue(_tr.containsObj(_pag.getCmpDescriptionPrio().self()));
        assertTrue(_tr.containsObj(_pag.getCmpDescriptionSorting().self()));
        assertTrue(_tr.containsObj(_pag.getCmpNumberPrio().self()));
        assertTrue(_tr.containsObj(_pag.getCmpNumberSorting().self()));
        assertTrue(_tr.containsObj(_pag.getCmpPricePrio().self()));
        assertTrue(_tr.containsObj(_pag.getCmpPriceSorting().self()));
        assertTrue(_tr.containsObj(_pag.getCmpNamePrio().self()));
        assertTrue(_tr.containsObj(_pag.getCmpNameSorting().self()));
        assertTrue(_tr.containsObj(_pag.getCmpNbStatisticsPrio().self()));
        assertTrue(_tr.containsObj(_pag.getCmpNbStatisticsSorting().self()));
        assertTrue(_tr.containsObj(_pag.getCmpNbStatusPrio().self()));
        assertTrue(_tr.containsObj(_pag.getCmpNbStatusSorting().self()));
        assertTrue(_tr.containsObj(_pag.getCmpHpPrio().self()));
        assertTrue(_tr.containsObj(_pag.getCmpHpSorting().self()));
        assertTrue(_tr.containsObj(_pag.getCmpRelativeHpPrio().self()));
        assertTrue(_tr.containsObj(_pag.getCmpRelativeHpSorting().self()));
        assertTrue(_tr.containsObj(_pag.getCmpRelativePpPrio().self()));
        assertTrue(_tr.containsObj(_pag.getCmpRelativePpSorting().self()));
        assertTrue(_tr.containsObj(_pag.getCmpPpPrio().self()));
        assertTrue(_tr.containsObj(_pag.getCmpPpSorting().self()));
        assertTrue(_tr.containsObj(_pag.getName()));
        assertTrue(_tr.containsObj(_pag.getStatus()));
        assertTrue(_tr.containsObj(_pag.getStatis().self()));
        assertTrue(_tr.containsObj(_pag.getDescription()));
        assertTrue(_tr.containsObj(_pag.getModeName().self()));
        assertTrue(_tr.containsObj(_pag.getModeDescription().self()));
        assertTrue(_tr.containsObj(_pag.getModeStatus().self()));
        assertTrue(_tr.containsObj(_pag.getMaxNumber()));
        assertTrue(_tr.containsObj(_pag.getMinNumber()));
        assertTrue(_tr.containsObj(_pag.getMaxPrice()));
        assertTrue(_tr.containsObj(_pag.getMinPrice()));
        assertTrue(_tr.containsObj(_pag.getMaxHp()));
        assertTrue(_tr.containsObj(_pag.getMinHp()));
        assertTrue(_tr.containsObj(_pag.getMaxHpRate()));
        assertTrue(_tr.containsObj(_pag.getMinHpRate()));
        assertTrue(_tr.containsObj(_pag.getMaxPp()));
        assertTrue(_tr.containsObj(_pag.getMinPp()));
        assertTrue(_tr.containsObj(_pag.getRelativeHpCheck()));
        assertTrue(_tr.containsObj(_pag.getRelativePp().self()));
        assertTrue(_tr.containsObj(_pag.getHealFromKo().self()));
        assertTrue(_tr.containsObj(_pag.getHealMove().self()));
        assertTrue(_tr.containsObj(_pag.getSearchButton()));
        assertTrue(_tr.containsObj(_pag.getNewSearchButton()));
    }

    public static void assertNull(AbsCustComponent _compo) {
        Assert.assertNull(_compo);
    }
    public static void assertNotNull(AbsCustComponent _compo) {
        Assert.assertNotNull(_compo);
    }
    public static void assertTrue(boolean _value) {
        Assert.assertTrue(_value);
    }

    public static void assertFalse(boolean _value) {
        Assert.assertFalse(_value);
    }
    public static void assertEq(long _expected, long _result) {
        Assert.assertEquals(_expected, _result);
    }

    public static void assertEq(String _expected, String _result) {
        Assert.assertEquals(_expected, _result);
    }
    public static void assertEq(Rate _expected, Rate _result) {
        Assert.assertEquals(_expected.toNumberString(), _result.toNumberString());
    }
    public static void assertEq(LgInt _expected, LgInt _result) {
        Assert.assertEquals(_expected.toNumberString(), _result.toNumberString());
    }

    public static NatNavigation nav() {
        NatNavigation nav_ = new NatNavigation();
        nav_.setSession(new NatConfigurationCore());
        return nav_;
    }
}
