package aiki.beans.fight;

import aiki.beans.FightGameInit;
import aiki.beans.PkFight;
import aiki.game.fight.Fight;
import code.bean.nat.NatNavigation;
import code.bean.nat.*;
import code.maths.LgInt;
import code.maths.Rate;
import code.scripts.confs.PkScriptPages;
import code.scripts.pages.aiki.CssInit;
import code.scripts.pages.aiki.MessagesInit;
import code.scripts.pages.aiki.PagesInit;
import code.sml.util.*;
import code.util.*;
import org.junit.Test;

public final class FightBeanTest extends InitDbFight {

    @Test
    public void mult() {
        assertEq(2,callFightBeanMultGet(displaying(beanFight(EN,facade(db())))));
    }

    @Test
    public void enMoves() {
        assertSizeEq(2,callFightBeanEnabledMovesGet(displaying(beanFight(EN,facade(db())))));
    }

    @Test
    public void enMoves1() {
        assertEq(CHARGE_TR2,first(elt(callFightBeanEnabledMovesGet(displaying(beanFight(EN,facade(db())))),0)));
    }

    @Test
    public void enMoves2() {
        assertEq(CHARGE_TR,first(elt(callFightBeanEnabledMovesGet(displaying(beanFight(EN,facade(db())))),1)));
    }

    @Test
    public void still1() {
        assertFalse(callFightBeanIsStillEnabled(displaying(beanFight(EN,facade(db()))),0));
    }

    @Test
    public void still2() {
        assertTrue(callFightBeanIsStillEnabled(displaying(beanFight(EN,facade(db()))),1));
    }

    @Test
    public void roundsCount() {
        assertEq(LgInt.zero(),callFightBeanNbRoundsGet(displaying(beanFight(EN,facade(db())))));
    }

    @Test
    public void winningMoney() {
        assertEq(Rate.zero(),callFightBeanWinningMoneyGet(displaying(beanFight(EN,facade(db())))));
    }

    @Test
    public void nbFlees() {
        assertEq(0,callFightBeanNbFleeAttemptGet(displaying(beanFight(EN,facade(db())))));
    }

    @Test
    public void clickPlayer() {
        NaSt fBean_ = displaying(beanFight(EN, facade(db())));
        assertEq(PkScriptPages.WEB_FIGHT_HTML_TEAM_HTML,navigateFightPlayer(fBean_));
        assertTrue(forms(fBean_).contains(NO_TEAM));
        assertEq(Fight.CST_PLAYER,forms(fBean_).getValInt(NO_TEAM));
    }


    @Test
    public void clickFoe() {
        NaSt fBean_ = displaying(beanFight(EN, facade(db())));
        assertEq(PkScriptPages.WEB_FIGHT_HTML_TEAM_HTML,navigateFightFoe(fBean_));
        assertTrue(forms(fBean_).contains(NO_TEAM));
        assertEq(Fight.CST_FOE,forms(fBean_).getValInt(NO_TEAM));
    }

    @Test
    public void enMovesAdv1() {
        assertFalse(callActivityOfMoveIsIncrementCount(second(elt(callFightBeanEnabledMovesGet(displaying(beanFight(EN,facadeEnMoves(db())))),0))));
    }

    @Test
    public void enMovesAdv2() {
        assertTrue(callActivityOfMoveIsIncrementCount(second(elt(callFightBeanEnabledMovesGet(displaying(beanFight(EN,facadeEnMoves(db())))),1))));
    }

    @Test
    public void enMovesAdv3() {
        assertTrue(callActivityOfMoveIsEnabled(second(elt(callFightBeanEnabledMovesGet(displaying(beanFight(EN,facadeEnMoves(db())))),0))));
    }

    @Test
    public void enMovesAdv4() {
        assertFalse(callActivityOfMoveIsEnabled(second(elt(callFightBeanEnabledMovesGet(displaying(beanFight(EN,facadeEnMoves(db())))),1))));
    }

    @Test
    public void enMovesAdv5() {
        assertEq(1,callActivityOfMoveGetNbTurn(second(elt(callFightBeanEnabledMovesGet(displaying(beanFight(EN,facadeEnMovesAct(db())))),1))));
    }

    @Test
    public void nav1() {
        StringMap<TranslationsAppli> builtMessages_ = new StringMap<TranslationsAppli>();
        builtMessages_.addEntry(EN,MessagesInit.enFight());
        builtMessages_.addEntry(FR,MessagesInit.frFight());
        StringMap<String> builtOther_ = CssInit.ms();
        PkFight pk_ = new PkFight();
        NatNavigation nav_ = pk_.nav(new StringList(EN,FR), new FightGameInit(), PagesInit.buildFight(),builtOther_,builtMessages_);
        nav_.setLanguage(EN);
        pk_.setDataBase(facadeCalculation5(dbBaseCalc()));
        pk_.initializeRendSessionDoc(nav_);
        assertEq("<html xmlns:c=\"javahtml\"><head><title>Data about the current fight</title><link href=\""+PkScriptPages.REN_ADD_WEB_FIGHT_CSS_FIGHT_CSS+"\" rel=\"stylesheet\" type=\"text/css\"/><style>p{\n" +
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
                "</style></head><body><a c:command=\"fight.f\" href=\"\" n-a=\"0\">Refresh</a><br/><a c:command=\"fight.d\" href=\"\" n-a=\"1\">See details of calculation of damage and speed</a><br/><a c:command=\"fight.clickPlayer\" href=\"\" n-a=\"2\">Your team</a><br/><a c:command=\"fight.clickFoe\" href=\"\" n-a=\"3\">Foe team</a><br/>The multiplicity of the fight is 1.The number of elapsed rounds is 0.The number of attempts of flee is 0.The won money is 0.Here is the activity of the effects of the following moves at the global fight:<table><thead><tr><th>Move</th><th>Enabled while sending a fighter</th><th>Enabled</th><th>Number of rounds</th></tr></thead><tbody/></table></body></html>",nav_.getHtmlText());
    }

    @Test
    public void nav2() {
        StringMap<TranslationsAppli> builtMessages_ = new StringMap<TranslationsAppli>();
        builtMessages_.addEntry(EN,MessagesInit.enFight());
        builtMessages_.addEntry(FR,MessagesInit.frFight());
        StringMap<String> builtOther_ = CssInit.ms();
        PkFight pk_ = new PkFight();
        NatNavigation nav_ = pk_.nav(new StringList(EN,FR), new FightGameInit(), PagesInit.buildFight(),builtOther_,builtMessages_);
        nav_.setLanguage(EN);
        pk_.setDataBase(facadeCalculation7(dbBaseCalc()));
        pk_.initializeRendSessionDoc(nav_);
        assertEq("<html xmlns:c=\"javahtml\"><head><title>Data about the current fight</title><link href=\""+PkScriptPages.REN_ADD_WEB_FIGHT_CSS_FIGHT_CSS+"\" rel=\"stylesheet\" type=\"text/css\"/><style>p{\n" +
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
                "</style></head><body><a c:command=\"fight.f\" href=\"\" n-a=\"0\">Refresh</a><br/><a c:command=\"fight.d\" href=\"\" n-a=\"1\">See details of calculation of damage and speed</a><br/><a c:command=\"fight.clickPlayer\" href=\"\" n-a=\"2\">Your team</a><br/><a c:command=\"fight.clickFoe\" href=\"\" n-a=\"3\">Foe team</a><br/>The multiplicity of the fight is 1.The number of elapsed rounds is 0.The number of attempts of flee is 0.The won money is 0.Here is the activity of the effects of the following moves at the global fight:<table><thead><tr><th>Move</th><th>Enabled while sending a fighter</th><th>Enabled</th><th>Number of rounds</th></tr></thead><tbody/></table></body></html>",nav_.getHtmlText());
    }
}
