package aiki.beans.game;

import aiki.beans.DiffGameInit;
import aiki.beans.DifficultyCommon;
import aiki.beans.PkDiff;
import aiki.beans.PokemonBeanStruct;
import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.game.Game;
import aiki.game.params.Difficulty;
import aiki.game.params.enums.DifficultyModelLaw;
import aiki.game.params.enums.DifficultyWinPointsFight;
import code.bean.nat.NatHtmlPage;
import code.bean.nat.NatNavigation;
import code.bean.nat.*;
import code.maths.Rate;
import code.scripts.confs.PkScriptPages;
import code.scripts.pages.aiki.CssInit;
import code.scripts.pages.aiki.MessagesInit;
import code.scripts.pages.aiki.PagesInit;
import code.sml.util.*;
import code.util.StringList;
import code.util.StringMap;
import org.junit.Test;

public final class DifficultyBeanTest extends InitDbDifficultyBean {

    @Test
    public void getAllowCatchingKo1() {
        FacadeGame fac_ = fac();
        diff(fac_).setAllowCatchingKo(true);
        assertTrue(callDifficultyBeanAllowCatchingKoGet(displaying(beanDiff(EN, fac_))));
    }

    @Test
    public void getAllowCatchingKo2() {
        FacadeGame fac_ = fac();
        diff(fac_).setAllowCatchingKo(false);
        assertFalse(callDifficultyBeanAllowCatchingKoGet(displaying(beanDiff(EN, fac_))));
    }

    @Test
    public void getEndFightIfOneTeamKo1() {
        FacadeGame fac_ = fac();
        diff(fac_).setEndFightIfOneTeamKo(true);
        assertTrue(callDifficultyBeanEndFightIfOneTeamKoGet(displaying(beanDiff(EN, fac_))));
    }

    @Test
    public void getEndFightIfOneTeamKo2() {
        FacadeGame fac_ = fac();
        diff(fac_).setEndFightIfOneTeamKo(false);
        assertFalse(callDifficultyBeanEndFightIfOneTeamKoGet(displaying(beanDiff(EN, fac_))));
    }

    @Test
    public void getAllowedSwitchPlacesEndRound1() {
        FacadeGame fac_ = fac();
        diff(fac_).setAllowedSwitchPlacesEndRound(true);
        assertTrue(callDifficultyBeanAllowedSwitchPlacesEndRoundGet(displaying(beanDiff(EN, fac_))));
    }

    @Test
    public void getAllowedSwitchPlacesEndRound2() {
        FacadeGame fac_ = fac();
        diff(fac_).setAllowedSwitchPlacesEndRound(false);
        assertFalse(callDifficultyBeanAllowedSwitchPlacesEndRoundGet(displaying(beanDiff(EN, fac_))));
    }

    @Test
    public void getRestoredMovesEndFight1() {
        FacadeGame fac_ = fac();
        diff(fac_).setRestoredMovesEndFight(true);
        assertTrue(callDifficultyBeanRestoredMovesEndFightGet(displaying(beanDiff(EN, fac_))));
    }

    @Test
    public void getRestoredMovesEndFight2() {
        FacadeGame fac_ = fac();
        diff(fac_).setRestoredMovesEndFight(false);
        assertFalse(callDifficultyBeanRestoredMovesEndFightGet(displaying(beanDiff(EN, fac_))));
    }

    @Test
    public void getEnabledClosing1() {
        FacadeGame fac_ = fac();
        diff(fac_).setEnabledClosing(true);
        assertTrue(callDifficultyBeanEnabledClosingGet(displaying(beanDiff(EN, fac_))));
    }

    @Test
    public void getEnabledClosing2() {
        FacadeGame fac_ = fac();
        diff(fac_).setEnabledClosing(false);
        assertFalse(callDifficultyBeanEnabledClosingGet(displaying(beanDiff(EN, fac_))));
    }

    @Test
    public void getRandomWildFight1() {
        FacadeGame fac_ = fac();
        diff(fac_).setRandomWildFight(true);
        assertTrue(callDifficultyBeanRandomWildFightGet(displaying(beanDiff(EN, fac_))));
    }

    @Test
    public void getRandomWildFight2() {
        FacadeGame fac_ = fac();
        diff(fac_).setRandomWildFight(false);
        assertFalse(callDifficultyBeanRandomWildFightGet(displaying(beanDiff(EN, fac_))));
    }

    @Test
    public void getStillPossibleFlee1() {
        FacadeGame fac_ = fac();
        diff(fac_).setStillPossibleFlee(true);
        assertTrue(callDifficultyBeanStillPossibleFleeGet(displaying(beanDiff(EN, fac_))));
    }

    @Test
    public void getStillPossibleFlee2() {
        FacadeGame fac_ = fac();
        diff(fac_).setStillPossibleFlee(false);
        assertFalse(callDifficultyBeanStillPossibleFleeGet(displaying(beanDiff(EN, fac_))));
    }

    @Test
    public void getSkipLearningMovesWhileNotGrowingLevel1() {
        FacadeGame fac_ = fac();
        diff(fac_).setSkipLearningMovesWhileNotGrowingLevel(true);
        assertTrue(callDifficultyBeanSkipLearningMovesWhileNotGrowingLevelGet(displaying(beanDiff(EN, fac_))));
    }

    @Test
    public void getSkipLearningMovesWhileNotGrowingLevel2() {
        FacadeGame fac_ = fac();
        diff(fac_).setSkipLearningMovesWhileNotGrowingLevel(false);
        assertFalse(callDifficultyBeanSkipLearningMovesWhileNotGrowingLevelGet(displaying(beanDiff(EN, fac_))));
    }

    @Test
    public void getRateLooseMoneyWin() {
        FacadeGame fac_ = fac();
        diff(fac_).setRateLooseMoneyWin(rt());
        assertEq(rt(),callDifficultyBeanRateLooseMoneyWinGet(displaying(beanDiff(EN, fac_))));
    }

    @Test
    public void getWinTrainerExp() {
        FacadeGame fac_ = fac();
        diff(fac_).setWinTrainerExp(rt());
        assertEq(rt(),callDifficultyBeanWinTrainerExpGet(displaying(beanDiff(EN, fac_))));
    }

    @Test
    public void getRateWinningExpPtsFight() {
        FacadeGame fac_ = fac();
        diff(fac_).setRateWinningExpPtsFight(rt());
        assertEq(rt(),callDifficultyBeanRateWinningExpPtsFightGet(displaying(beanDiff(EN, fac_))));
    }

    @Test
    public void getRateWinMoneyBase() {
        FacadeGame fac_ = fac();
        diff(fac_).setRateWinMoneyBase(rt());
        assertEq(rt(),callDifficultyBeanRateWinMoneyBaseGet(displaying(beanDiff(EN, fac_))));
    }

    @Test
    public void getIvFoe() {
        FacadeGame fac_ = fac();
        diff(fac_).setIvFoe( 1);
        assertEq(1,callDifficultyBeanIvFoeGet(displaying(beanDiff(EN, fac_))));
    }

    @Test
    public void getIvPlayer() {
        FacadeGame fac_ = fac();
        diff(fac_).setIvPlayer( 1);
        assertEq(1,callDifficultyBeanIvPlayerGet(displaying(beanDiff(EN, fac_))));
    }

    @Test
    public void getDiffWinningExpPtsFight() {
        FacadeGame fac_ = fac();
        diff(fac_).setDiffWinningExpPtsFight(DifficultyWinPointsFight.FACILE);
        assertEq(DifficultyWinPointsFight.FACILE.getWinName(),callDifficultyBeanDiffWinningExpPtsFightGet(displaying(beanDiff(EN, fac_))));
    }

    @Test
    public void getDamageRateLawFoe() {
        FacadeGame fac_ = fac();
        diff(fac_).setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MIN);
        assertEq(DifficultyModelLaw.CONSTANT_MIN.getModelName(),callDifficultyBeanDamageRateLawFoeGet(displaying(beanDiff(EN, fac_))));
    }

    @Test
    public void getDamageRatePlayer() {
        FacadeGame fac_ = fac();
        diff(fac_).setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        assertEq(DifficultyModelLaw.CONSTANT_MAX.getModelName(),callDifficultyBeanDamageRatePlayerGet(displaying(beanDiff(EN, fac_))));
    }

    @Test
    public void getWinPointsFight1() {
        FacadeGame fac_ = facTr();
        assertSizeEq(4,callDifficultyBeanWinPointsFightGet(displaying(beanDiff(EN, fac_))));
    }

    @Test
    public void getWinPointsFight2() {
        FacadeGame fac_ = facTr();
        assertEq(DifficultyWinPointsFight.TRES_FACILE.getWinName(),first(elt(callDifficultyBeanWinPointsFightGet(displaying(beanDiff(EN, fac_))),0)));
    }

    @Test
    public void getWinPointsFight3() {
        FacadeGame fac_ = facTr();
        assertEq("W1",second(elt(callDifficultyBeanWinPointsFightGet(displaying(beanDiff(EN, fac_))),0)));
    }

    @Test
    public void getWinPointsFight4() {
        FacadeGame fac_ = facTr();
        assertEq(DifficultyWinPointsFight.FACILE.getWinName(),first(elt(callDifficultyBeanWinPointsFightGet(displaying(beanDiff(EN, fac_))),1)));
    }

    @Test
    public void getWinPointsFight5() {
        FacadeGame fac_ = facTr();
        assertEq("W2",second(elt(callDifficultyBeanWinPointsFightGet(displaying(beanDiff(EN, fac_))),1)));
    }

    @Test
    public void getWinPointsFight6() {
        FacadeGame fac_ = facTr();
        assertEq(DifficultyWinPointsFight.DIFFICILE.getWinName(),first(elt(callDifficultyBeanWinPointsFightGet(displaying(beanDiff(EN, fac_))),2)));
    }

    @Test
    public void getWinPointsFight7() {
        FacadeGame fac_ = facTr();
        assertEq("W3",second(elt(callDifficultyBeanWinPointsFightGet(displaying(beanDiff(EN, fac_))),2)));
    }

    @Test
    public void getWinPointsFight8() {
        FacadeGame fac_ = facTr();
        assertEq(DifficultyWinPointsFight.TRES_DIFFICILE.getWinName(),first(elt(callDifficultyBeanWinPointsFightGet(displaying(beanDiff(EN, fac_))),3)));
    }

    @Test
    public void getWinPointsFight9() {
        FacadeGame fac_ = facTr();
        assertEq("W4",second(elt(callDifficultyBeanWinPointsFightGet(displaying(beanDiff(EN, fac_))),3)));
    }

    @Test
    public void getDamageRates1() {
        FacadeGame fac_ = facTr();
        assertSizeEq(5,callDifficultyBeanDamageRatesGet(displaying(beanDiff(EN, fac_))));
    }

    @Test
    public void getDamageRates2() {
        FacadeGame fac_ = facTr();
        assertEq(DifficultyModelLaw.CONSTANT_MIN.getModelName(),first(elt(callDifficultyBeanDamageRatesGet(displaying(beanDiff(EN, fac_))),0)));
    }

    @Test
    public void getDamageRates3() {
        FacadeGame fac_ = facTr();
        assertEq("M1",second(elt(callDifficultyBeanDamageRatesGet(displaying(beanDiff(EN, fac_))),0)));
    }

    @Test
    public void getDamageRates4() {
        FacadeGame fac_ = facTr();
        assertEq(DifficultyModelLaw.CROISSANT.getModelName(),first(elt(callDifficultyBeanDamageRatesGet(displaying(beanDiff(EN, fac_))),1)));
    }

    @Test
    public void getDamageRates5() {
        FacadeGame fac_ = facTr();
        assertEq("M2",second(elt(callDifficultyBeanDamageRatesGet(displaying(beanDiff(EN, fac_))),1)));
    }

    @Test
    public void getDamageRates6() {
        FacadeGame fac_ = facTr();
        assertEq(DifficultyModelLaw.UNIFORME.getModelName(),first(elt(callDifficultyBeanDamageRatesGet(displaying(beanDiff(EN, fac_))),2)));
    }

    @Test
    public void getDamageRates7() {
        FacadeGame fac_ = facTr();
        assertEq("M3",second(elt(callDifficultyBeanDamageRatesGet(displaying(beanDiff(EN, fac_))),2)));
    }

    @Test
    public void getDamageRates8() {
        FacadeGame fac_ = facTr();
        assertEq(DifficultyModelLaw.DECROISSANT.getModelName(),first(elt(callDifficultyBeanDamageRatesGet(displaying(beanDiff(EN, fac_))),3)));
    }

    @Test
    public void getDamageRates9() {
        FacadeGame fac_ = facTr();
        assertEq("M4",second(elt(callDifficultyBeanDamageRatesGet(displaying(beanDiff(EN, fac_))),3)));
    }

    @Test
    public void getDamageRates10() {
        FacadeGame fac_ = facTr();
        assertEq(DifficultyModelLaw.CONSTANT_MAX.getModelName(),first(elt(callDifficultyBeanDamageRatesGet(displaying(beanDiff(EN, fac_))),4)));
    }

    @Test
    public void getDamageRates11() {
        FacadeGame fac_ = facTr();
        assertEq("M5",second(elt(callDifficultyBeanDamageRatesGet(displaying(beanDiff(EN, fac_))),4)));
    }

    @Test
    public void getDamageRatePlayerTable1() {
        FacadeGame fac_ = facTr();
        diff(fac_).setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MIN);
        assertSizeEq(1,callDifficultyBeanDamageRatePlayerTableGet(displaying(beanDiff(EN, fac_))));
    }

    @Test
    public void getDamageRatePlayerTable2() {
        FacadeGame fac_ = facTr();
        diff(fac_).setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MIN);
        assertEq(fac_.getData().getLawsDamageRate().getVal(DifficultyModelLaw.CONSTANT_MIN).getLaw().getEvent(0),first(elt(callDifficultyBeanDamageRatePlayerTableGet(displaying(beanDiff(EN, fac_))),0)));
    }

    @Test
    public void getDamageRatePlayerTable3() {
        FacadeGame fac_ = facTr();
        diff(fac_).setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MIN);
        assertEq(Rate.one(),second(elt(callDifficultyBeanDamageRatePlayerTableGet(displaying(beanDiff(EN, fac_))),0)));
    }

    @Test
    public void getDamageRateLawFoe1() {
        FacadeGame fac_ = facTr();
        diff(fac_).setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MIN);
        assertSizeEq(1,callDifficultyBeanDamageRateFoeTableGet(displaying(beanDiff(EN, fac_))));
    }

    @Test
    public void getDamageRateLawFoe2() {
        FacadeGame fac_ = facTr();
        diff(fac_).setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MIN);
        assertEq(fac_.getData().getLawsDamageRate().getVal(DifficultyModelLaw.CONSTANT_MIN).getLaw().getEvent(0),first(elt(callDifficultyBeanDamageRateFoeTableGet(displaying(beanDiff(EN, fac_))),0)));
    }

    @Test
    public void getDamageRateLawFoe3() {
        FacadeGame fac_ = facTr();
        diff(fac_).setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MIN);
        assertEq(Rate.one(),second(elt(callDifficultyBeanDamageRateFoeTableGet(displaying(beanDiff(EN, fac_))),0)));
    }

    @Test
    public void setAllowCatchingKo1() {
        assertTrue(result(callDifficultyBeanAllowCatchingKoSet(displaying(beanDiff(EN, fac())),true)).getAllowCatchingKo());
    }

    @Test
    public void setAllowCatchingKo2() {
        assertFalse(result(callDifficultyBeanAllowCatchingKoSet(displaying(beanDiff(EN, fac())),false)).getAllowCatchingKo());
    }

    @Test
    public void setEndFightIfOneTeamKo1() {
        assertTrue(result(callDifficultyBeanEndFightIfOneTeamKoSet(displaying(beanDiff(EN, fac())),true)).getEndFightIfOneTeamKo());
    }

    @Test
    public void setEndFightIfOneTeamKo2() {
        assertFalse(result(callDifficultyBeanEndFightIfOneTeamKoSet(displaying(beanDiff(EN, fac())),false)).getEndFightIfOneTeamKo());
    }

    @Test
    public void setAllowedSwitchPlacesEndRound1() {
        assertTrue(result(callDifficultyBeanAllowedSwitchPlacesEndRoundSet(displaying(beanDiff(EN, fac())),true)).getAllowedSwitchPlacesEndRound());
    }

    @Test
    public void setAllowedSwitchPlacesEndRound2() {
        assertFalse(result(callDifficultyBeanAllowedSwitchPlacesEndRoundSet(displaying(beanDiff(EN, fac())),false)).getAllowedSwitchPlacesEndRound());
    }

    @Test
    public void setRestoredMovesEndFight1() {
        assertTrue(result(callDifficultyBeanRestoredMovesEndFightSet(displaying(beanDiff(EN, fac())),true)).getRestoredMovesEndFight());
    }

    @Test
    public void setRestoredMovesEndFight2() {
        assertFalse(result(callDifficultyBeanRestoredMovesEndFightSet(displaying(beanDiff(EN, fac())),false)).getRestoredMovesEndFight());
    }

    @Test
    public void setEnabledClosing1() {
        assertTrue(result(callDifficultyBeanEnabledClosingSet(displaying(beanDiff(EN, fac())),true)).getEnabledClosing());
    }

    @Test
    public void setEnabledClosing2() {
        assertFalse(result(callDifficultyBeanEnabledClosingSet(displaying(beanDiff(EN, fac())),false)).getEnabledClosing());
    }

    @Test
    public void setRandomWildFight1() {
        assertTrue(result(callDifficultyBeanRandomWildFightSet(displaying(beanDiff(EN, fac())),true)).getRandomWildFight());
    }

    @Test
    public void setRandomWildFight2() {
        assertFalse(result(callDifficultyBeanRandomWildFightSet(displaying(beanDiff(EN, fac())),false)).getRandomWildFight());
    }

    @Test
    public void setStillPossibleFlee1() {
        assertTrue(result(callDifficultyBeanStillPossibleFleeSet(displaying(beanDiff(EN, fac())),true)).getStillPossibleFlee());
    }

    @Test
    public void setStillPossibleFlee2() {
        assertFalse(result(callDifficultyBeanStillPossibleFleeSet(displaying(beanDiff(EN, fac())),false)).getStillPossibleFlee());
    }

    @Test
    public void setSkipLearningMovesWhileNotGrowingLevel1() {
        assertTrue(result(callDifficultyBeanSkipLearningMovesWhileNotGrowingLevelSet(displaying(beanDiff(EN, fac())),true)).getSkipLearningMovesWhileNotGrowingLevel());
    }

    @Test
    public void setSkipLearningMovesWhileNotGrowingLevel2() {
        assertFalse(result(callDifficultyBeanSkipLearningMovesWhileNotGrowingLevelSet(displaying(beanDiff(EN, fac())),false)).getSkipLearningMovesWhileNotGrowingLevel());
    }

    @Test
    public void setWinTrainerExp() {
        assertEq(rt(),result(callDifficultyBeanWinTrainerExpSet(displaying(beanDiff(EN, fac())),rt())).getWinTrainerExp());
    }

    @Test
    public void setRateWinningExpPtsFight() {
        assertEq(rt(),result(callDifficultyBeanRateWinningExpPtsFightSet(displaying(beanDiff(EN, fac())),rt())).getRateWinningExpPtsFight());
    }

    @Test
    public void setRateWinMoneyBase() {
        assertEq(rt(),result(callDifficultyBeanRateWinMoneyBaseSet(displaying(beanDiff(EN, fac())),rt())).getRateWinMoneyBase());
    }

    @Test
    public void setRateLooseMoneyWin() {
        assertEq(rt(),result(callDifficultyBeanRateLooseMoneyWinSet(displaying(beanDiff(EN, fac())),rt())).getRateLooseMoneyWin());
    }

    @Test
    public void setIvFoe() {
        assertEq(1,result(callDifficultyBeanIvFoeSet(displaying(beanDiff(EN, fac())),1)).getIvFoe());
    }

    @Test
    public void setIvPlayer() {
        assertEq(1,result(callDifficultyBeanIvPlayerSet(displaying(beanDiff(EN, fac())),1)).getIvPlayer());
    }
    @Test
    public void setDiffWinningExpPtsFight() {
        assertEq(DifficultyWinPointsFight.FACILE.getWinName(),result(callDifficultyBeanDiffWinningExpPtsFightSet(displaying(beanDiff(EN, fac())),DifficultyWinPointsFight.FACILE.getWinName())).getDiffWinningExpPtsFight());
    }
    @Test
    public void setDamageRateLawFoe() {
        assertEq(DifficultyModelLaw.CONSTANT_MIN.getModelName(),result(callDifficultyBeanDamageRateLawFoeSet(displaying(beanDiff(EN, fac())),DifficultyModelLaw.CONSTANT_MIN.getModelName())).getDamageRateLawFoe());
    }
    @Test
    public void setDamageRatePlayer() {
        assertEq(DifficultyModelLaw.CONSTANT_MAX.getModelName(),result(callDifficultyBeanDamageRatePlayerSet(displaying(beanDiff(EN, fac())),DifficultyModelLaw.CONSTANT_MAX.getModelName())).getDamageRatePlayer());
    }
    @Test
    public void change() {
        FacadeGame fac_ = fac();
        assertEq(PkScriptPages.WEB_GAME_HTML_DIFFICULTY_HTML,navigateDiffChange(callChange(displaying(beanDiff(EN, fac_)),DifficultyWinPointsFight.FACILE.getWinName())));
        assertEq(DifficultyWinPointsFight.FACILE.getWinName(),diff(fac_).getDiffWinningExpPtsFight().getWinName());
    }
    @Test
    public void init() {
        FacadeGame fac_ = facTr();
        diff(fac_).setDiffWinningExpPtsFight(DifficultyWinPointsFight.FACILE);
        diff(fac_).setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff(fac_).setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MIN);
        diff(fac_).setRateLooseMoneyWin(rt());
        diff(fac_).setWinTrainerExp(rt());
        diff(fac_).setRateWinningExpPtsFight(rt());
        diff(fac_).setRateWinMoneyBase(rt());
        StringMap<TranslationsAppli> builtMessages_ = new StringMap<TranslationsAppli>();
        builtMessages_.addEntry(EN,MessagesInit.en());
        builtMessages_.addEntry(FR,MessagesInit.fr());
        StringMap<String> builtOther_ = CssInit.ms();
        PkDiff pk_ = new PkDiff();
        NatNavigation nav_ = pk_.nav(new StringList(EN,FR), new DiffGameInit(), PagesInit.buildDiff(),builtOther_,builtMessages_);
        nav_.setLanguage(EN);
        pk_.setDataBase(fac_);
        pk_.initializeRendSessionDoc(nav_);
        assertEq("<html xmlns:c=\"javahtml\"><head><title>Difficulty choice</title><link href=\""+PkScriptPages.REN_ADD_WEB_GAME_CSS_DIFFICULTY_CSS+"\" rel=\"stylesheet\" type=\"text/css\"/><style>p{\n" +
                "\ttext-indent:25px;\n" +
                "}\n" +
                "body{\n" +
                "\ttext-align:justify;\n" +
                "}\n" +
                "td{\n" +
                "\tborder:1px solid black;\n" +
                "}\n" +
                "th{\n" +
                "\tbackground: yellow;\n" +
                "\tborder:1px solid black;\n" +
                "}\n" +
                "table{\n" +
                "\tborder-spacing:0;\n" +
                "}\n" +
                "h1{\n" +
                "\tcolor:red;\n" +
                "}\n" +
                "h2{\n" +
                "\tcolor:blue;\n" +
                "}\n" +
                "span.errormessage{\n" +
                "\tcolor:red;\n" +
                "}\n" +
                "</style></head><body><form c:command=\"difficulty.change\" name=\"changingDiff\" action=\"\" n-f=\"0\">Difficulty of winning points<select name=\"difficulty_common.diffWinningExpPtsFight\" n-i=\"0\"><option value=\"0\">W1</option><option value=\"1\" selected=\"selected\">W2</option><option value=\"2\">W3</option><option value=\"3\">W4</option></select><br/>Allow catching ko pokemon<input name=\"difficulty_common.allowCatchingKo\" type=\"checkbox\" n-i=\"1\" checked=\"checked\"/><br/>Allow swicthing places at the front of battle at the end of round<input name=\"difficulty_common.allowedSwitchPlacesEndRound\" type=\"checkbox\" n-i=\"2\"/><br/>Rate of winning experience points de gain de points while a fight against a foe<input id=\"winTrainerExpRate\" name=\"difficulty_common.winTrainerExp\" type=\"text\" n-i=\"3\" value=\"2\"/><br/>Rate of winning experience points<input id=\"winExpRateFight\" name=\"difficulty_common.rateWinningExpPtsFight\" type=\"text\" n-i=\"4\" value=\"2\"/><br/>End of fight if a team is ko.<input name=\"difficulty_common.endFightIfOneTeamKo\" type=\"checkbox\" n-i=\"5\" checked=\"checked\"/><br/>Iv of your pokemon<input id=\"ivPlayerId\" name=\"difficulty_common.ivPlayer\" type=\"text\" n-i=\"6\" value=\"31\"/><br/>Iv of the pokemon of your foes<input id=\"ivFoeId\" name=\"difficulty_common.ivFoe\" type=\"text\" n-i=\"7\" value=\"0\"/><br/>Rate of won money between winning money while a victory and winning base<input id=\"rateWinMoneyBaseId\" name=\"difficulty_common.rateWinMoneyBase\" type=\"text\" n-i=\"8\" value=\"2\"/><br/>Rate of lost money between loss while a defeat and winning money while a victory<input id=\"rateLooseMoneyWinId\" name=\"difficulty_common.rateLooseMoneyWin\" type=\"text\" n-i=\"9\" value=\"2\"/><br/>Healed moves of your pokemon at the end of fight<input name=\"difficulty_common.restoredMovesEndFight\" type=\"checkbox\" n-i=\"10\"/><br/>The moves wth single target can achieve any foe<input name=\"difficulty_common.enabledClosing\" type=\"checkbox\" n-i=\"11\" checked=\"checked\"/><br/>Random appearing pokemon<input name=\"difficulty_common.randomWildFight\" type=\"checkbox\" n-i=\"12\"/><br/>Flee always possible<input name=\"difficulty_common.stillPossibleFlee\" type=\"checkbox\" n-i=\"13\" checked=\"checked\"/><br/>Do not learnt the already known moves<input name=\"difficulty_common.skipLearningMovesWhileNotGrowingLevel\" type=\"checkbox\" n-i=\"14\" checked=\"checked\"/><br/>Choice of averages of damage rate for your pokemon<select name=\"difficulty_common.damageRatePlayer\" n-i=\"15\"><option value=\"0\">M1</option><option value=\"1\">M2</option><option value=\"2\">M3</option><option value=\"3\">M4</option><option value=\"4\" selected=\"selected\">M5</option></select><br/><table><thead><tr><th>Rate</th><th>Probability</th></tr></thead><tbody><tr><td>1</td><td>1</td></tr></tbody></table>Choix of averages of damage rate for the pokemon of your foes<select name=\"difficulty_common.damageRateLawFoe\" n-i=\"16\"><option value=\"0\" selected=\"selected\">M1</option><option value=\"1\">M2</option><option value=\"2\">M3</option><option value=\"3\">M4</option><option value=\"4\">M5</option></select><br/><table><thead><tr><th>Rate</th><th>Probability</th></tr></thead><tbody><tr><td>17/20</td><td>1</td></tr></tbody></table><input value=\"OK\" type=\"submit\"/></form></body></html>",nav_.getHtmlText());
    }
    @Test
    public void nav() {
        FacadeGame fac_ = facTr(16);
        StringMap<TranslationsAppli> builtMessages_ = new StringMap<TranslationsAppli>();
        builtMessages_.addEntry(EN,MessagesInit.en());
        builtMessages_.addEntry(FR,MessagesInit.fr());
        StringMap<String> builtOther_ = CssInit.ms();
        PkDiff pk_ = new PkDiff();
        NatNavigation nav_ = pk_.nav(new StringList(EN,FR), new DiffGameInit(), PagesInit.buildDiff(),builtOther_,builtMessages_);
        nav_.setLanguage(EN);
        pk_.setDataBase(fac_);
        pk_.initializeRendSessionDoc(nav_);
        NatHtmlPage htmlPage_ = pk_.getNatPage();
        choose(htmlPage_, 0, DifficultyWinPointsFight.FACILE.getWinName());
        choose(htmlPage_, 3, rt().toNumberString());
        choose(htmlPage_, 4, rt().toNumberString());
        choose(htmlPage_, 6, "12");
        choose(htmlPage_, 7, "4");
        choose(htmlPage_, 8, rt().toNumberString());
        choose(htmlPage_, 9, rt().toNumberString());
        choose(htmlPage_, 15, DifficultyModelLaw.CONSTANT_MAX.getModelName());
        choose(htmlPage_, 16, DifficultyModelLaw.CONSTANT_MIN.getModelName());
        htmlPage_.setUrl(0);

        pk_.execute(true, nav_);


        assertEq("<html xmlns:c=\"javahtml\"><head><title>Difficulty choice</title><link href=\""+PkScriptPages.REN_ADD_WEB_GAME_CSS_DIFFICULTY_CSS+"\" rel=\"stylesheet\" type=\"text/css\"/><style>p{\n" +
                "\ttext-indent:25px;\n" +
                "}\n" +
                "body{\n" +
                "\ttext-align:justify;\n" +
                "}\n" +
                "td{\n" +
                "\tborder:1px solid black;\n" +
                "}\n" +
                "th{\n" +
                "\tbackground: yellow;\n" +
                "\tborder:1px solid black;\n" +
                "}\n" +
                "table{\n" +
                "\tborder-spacing:0;\n" +
                "}\n" +
                "h1{\n" +
                "\tcolor:red;\n" +
                "}\n" +
                "h2{\n" +
                "\tcolor:blue;\n" +
                "}\n" +
                "span.errormessage{\n" +
                "\tcolor:red;\n" +
                "}\n" +
                "</style></head><body><form c:command=\"difficulty.change\" name=\"changingDiff\" action=\"\" n-f=\"0\">Difficulty of winning points<select name=\"difficulty_common.diffWinningExpPtsFight\" n-i=\"0\"><option value=\"0\">W1</option><option value=\"1\" selected=\"selected\">W2</option><option value=\"2\">W3</option><option value=\"3\">W4</option></select><br/>Allow catching ko pokemon<input name=\"difficulty_common.allowCatchingKo\" type=\"checkbox\" n-i=\"1\" checked=\"checked\"/><br/>Allow swicthing places at the front of battle at the end of round<input name=\"difficulty_common.allowedSwitchPlacesEndRound\" type=\"checkbox\" n-i=\"2\"/><br/>Rate of winning experience points de gain de points while a fight against a foe<input id=\"winTrainerExpRate\" name=\"difficulty_common.winTrainerExp\" type=\"text\" n-i=\"3\" value=\"2\"/><br/>Rate of winning experience points<input id=\"winExpRateFight\" name=\"difficulty_common.rateWinningExpPtsFight\" type=\"text\" n-i=\"4\" value=\"2\"/><br/>End of fight if a team is ko.<input name=\"difficulty_common.endFightIfOneTeamKo\" type=\"checkbox\" n-i=\"5\" checked=\"checked\"/><br/>Iv of your pokemon<input id=\"ivPlayerId\" name=\"difficulty_common.ivPlayer\" type=\"text\" n-i=\"6\" value=\"12\"/><br/>Iv of the pokemon of your foes<input id=\"ivFoeId\" name=\"difficulty_common.ivFoe\" type=\"text\" n-i=\"7\" value=\"4\"/><br/>Rate of won money between winning money while a victory and winning base<input id=\"rateWinMoneyBaseId\" name=\"difficulty_common.rateWinMoneyBase\" type=\"text\" n-i=\"8\" value=\"2\"/><br/>Rate of lost money between loss while a defeat and winning money while a victory<input id=\"rateLooseMoneyWinId\" name=\"difficulty_common.rateLooseMoneyWin\" type=\"text\" n-i=\"9\" value=\"2\"/><br/>Healed moves of your pokemon at the end of fight<input name=\"difficulty_common.restoredMovesEndFight\" type=\"checkbox\" n-i=\"10\"/><br/>The moves wth single target can achieve any foe<input name=\"difficulty_common.enabledClosing\" type=\"checkbox\" n-i=\"11\" checked=\"checked\"/><br/>Random appearing pokemon<input name=\"difficulty_common.randomWildFight\" type=\"checkbox\" n-i=\"12\"/><br/>Flee always possible<input name=\"difficulty_common.stillPossibleFlee\" type=\"checkbox\" n-i=\"13\" checked=\"checked\"/><br/>Do not learnt the already known moves<input name=\"difficulty_common.skipLearningMovesWhileNotGrowingLevel\" type=\"checkbox\" n-i=\"14\" checked=\"checked\"/><br/>Choice of averages of damage rate for your pokemon<select name=\"difficulty_common.damageRatePlayer\" n-i=\"15\"><option value=\"0\">M1</option><option value=\"1\">M2</option><option value=\"2\">M3</option><option value=\"3\">M4</option><option value=\"4\" selected=\"selected\">M5</option></select><br/><table><thead><tr><th>Rate</th><th>Probability</th></tr></thead><tbody><tr><td>1</td><td>1</td></tr></tbody></table>Choix of averages of damage rate for the pokemon of your foes<select name=\"difficulty_common.damageRateLawFoe\" n-i=\"16\"><option value=\"0\" selected=\"selected\">M1</option><option value=\"1\">M2</option><option value=\"2\">M3</option><option value=\"3\">M4</option><option value=\"4\">M5</option></select><br/><table><thead><tr><th>Rate</th><th>Probability</th></tr></thead><tbody><tr><td>17/20</td><td>1</td></tr></tbody></table><input value=\"OK\" type=\"submit\"/></form></body></html>",nav_.getHtmlText());
    }

    private void choose(NatHtmlPage _page, int _nbId, String _value) {
        _page.getContainer(0, _nbId).setEnabled(true);
        _page.getContainer(0, _nbId).setValue(new StringList(_value));
    }

    private DifficultyCommon result(NaSt _str) {
        return ((DifficultyCommonBean) ((PokemonBeanStruct) _str).getInstance()).getDifficultyCommon();
    }
    private Rate rt() {
        return Rate.newRate("2");
    }

    private Difficulty diff(FacadeGame _fac) {
        return _fac.getGame().getDifficulty();
    }

    private FacadeGame facTr() {
        return facTr(2);
    }
    private FacadeGame facTr(int _iv) {
        DataBase di_ = diff(_iv);
        di_.getTranslatedDiffWinPts().getVal(EN).clear();
        di_.getTranslatedDiffWinPts().getVal(EN).addEntry(DifficultyWinPointsFight.TRES_FACILE,"W1");
        di_.getTranslatedDiffWinPts().getVal(EN).addEntry(DifficultyWinPointsFight.FACILE,"W2");
        di_.getTranslatedDiffWinPts().getVal(EN).addEntry(DifficultyWinPointsFight.DIFFICILE,"W3");
        di_.getTranslatedDiffWinPts().getVal(EN).addEntry(DifficultyWinPointsFight.TRES_DIFFICILE,"W4");
        di_.getTranslatedDiffModelLaw().getVal(EN).clear();
        di_.getTranslatedDiffModelLaw().getVal(EN).addEntry(DifficultyModelLaw.CONSTANT_MIN,"M1");
        di_.getTranslatedDiffModelLaw().getVal(EN).addEntry(DifficultyModelLaw.CROISSANT,"M2");
        di_.getTranslatedDiffModelLaw().getVal(EN).addEntry(DifficultyModelLaw.UNIFORME,"M3");
        di_.getTranslatedDiffModelLaw().getVal(EN).addEntry(DifficultyModelLaw.DECROISSANT,"M4");
        di_.getTranslatedDiffModelLaw().getVal(EN).addEntry(DifficultyModelLaw.CONSTANT_MAX,"M5");
        return fac(di_);
    }
    private FacadeGame fac() {
        DataBase di_ = diff(2);
        return fac(di_);
    }

    private FacadeGame fac(DataBase _di) {
        FacadeGame f_ = new FacadeGame();
        f_.setData(_di);
        f_.setGame(new Game());
        return f_;
    }
}
