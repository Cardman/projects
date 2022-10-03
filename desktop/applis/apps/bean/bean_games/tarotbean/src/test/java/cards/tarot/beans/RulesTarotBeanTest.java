package cards.tarot.beans;

import cards.consts.CoreResourcesAccess;
import cards.consts.MixCardsChoice;
import cards.tarot.RulesTarot;
import cards.tarot.enumerations.*;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Navigation;
import code.formathtml.analyze.blocks.AnaRendBlock;
import code.scripts.pages.cards.MessTarotPage;
import code.scripts.pages.cards.PagesTarots;
import code.util.IdList;
import code.util.StringMap;
import code.util.consts.Constants;
import org.junit.Test;

public final class RulesTarotBeanTest extends BeanTarotCommonTs {
    private static final String AT_EACH_LAUNCHING = "at each launching";
    private static final String DEAL_1_VS_4 = "1 vs 4";
    private static final String FOLD = "fold";
    private static final String LOW_CARDS = "low cards";
    private static final String SLAM = "slam";
    private static final String GUARD = "guard";
    private static final String NORMAL = "normal";
    private static final String FOUR = "four";
    private static final String ONE = "one";
    private static final String TWO = "two";
    private static final String THREE = "three";
    private static final String ATTACK_WINS = "attack wins";

    @Test
    public void getCartesBattues() {
        assertEq(AT_EACH_LAUNCHING,callRulesTarotBeanCartesBattues(displaying(beanRules(EN, rules()))));
    }

    @Test
    public void getRepartition() {
        assertEq(DEAL_1_VS_4,callRulesTarotBeanRepartition(displaying(beanRules(EN, rules()))));
    }

    @Test
    public void getMiseres() {
        Struct res_ = callRulesTarotBeanMiseres(displaying(beanRules(EN, rules())));
        assertSizeEq(1, res_);
        assertEq(LOW_CARDS, res_,0);
    }

    @Test
    public void getBids() {
        Struct res_ = callRulesTarotBeanContrats(displaying(beanRules(EN, rules())));
        assertSizeEq(3, res_);
        assertEq(FOLD, res_,0);
        assertEq(GUARD, res_,1);
        assertEq(SLAM, res_,2);
    }

    @Test
    public void getHanfuls() {
        Struct res_ = callRulesTarotBeanPoigneesAutorisees(displaying(beanRules(EN, rules())));
        assertSizeEq(4, res_);
        assertFirstEq(ONE, elt(res_,0));
        assertSecondEq(8, elt(res_,0));
        assertFirstEq(TWO, elt(res_,1));
        assertSecondEq(10, elt(res_,1));
        assertFirstEq(THREE, elt(res_,2));
        assertSecondEq(13, elt(res_,2));
        assertFirstEq(FOUR, elt(res_,3));
        assertSecondEq(14, elt(res_,3));
    }

    @Test
    public void getMode() {
        assertEq(NORMAL, callRulesTarotBeanMode(displaying(beanRules(EN, rules()))));
    }

    @Test
    public void getEnd() {
        assertEq(ATTACK_WINS, callRulesTarotBeanFinPartieTarot(displaying(beanRules(EN, rules()))));
    }

    @Test
    public void allowPlay1() {
        assertTrue(callRulesTarotBeanAllowPlayCalledSuit(displaying(beanRules(EN, rules(DealingTarot.DEAL_1_VS_4, true, true)))));
    }
    @Test
    public void allowPlay2() {
        assertFalse(callRulesTarotBeanAllowPlayCalledSuit(displaying(beanRules(EN, rules(DealingTarot.DEAL_1_VS_4, false, true)))));
    }

    @Test
    public void discard1() {
        assertTrue(callRulesTarotBeanDiscardAfterCall(displaying(beanRules(EN, rules(DealingTarot.DEAL_1_VS_4, true, true)))));
    }
    @Test
    public void discard2() {
        assertFalse(callRulesTarotBeanDiscardAfterCall(displaying(beanRules(EN, rules(DealingTarot.DEAL_2_VS_4_CALL_KING, true, false)))));
    }

    @Test
    public void init() {
        StringMap<String> other_ = MessTarotPage.ms();
        AnaRendBlock.adjust(other_);
        TarotStandardsRules stds_ = new TarotStandardsRules();
        Navigation nav_ = stds_.nav(Constants.getAvailableLanguages(),EN,new RulesTarotLoader(), PagesTarots.buildRules(),other_,other_,"");
        stds_.setDataBaseRules(rules(DealingTarot.DEAL_1_VS_4, true, true));
        stds_.initializeRendSessionDoc(nav_);
        assertEq("<html xmlns:c=\"javahtml\"><head><link href=\"resources_cards/css/tarot.css\" rel=\"stylesheet\" type=\"text/css\"/><style>h1 {\n" +
                "\tcolor: #0000FF;\n" +
                "}\n" +
                "td,caption{\n" +
                "\tborder:1px solid black;\n" +
                "}\n" +
                "</style></head><body><h1>Mix Cards</h1>at each launching<br/><h1>Players' repartition</h1>1 vs 4<br/><h1>Mode</h1>normal<br/><h1>Discarding after call</h1>yes<h1>Allow playing the called suit at the first round</h1>yes<br/><h1>Allowed Bids at the beginning of the deal</h1><ul><li>fold</li><li>guard</li><li>slam</li></ul><h1>Allowed declaring</h1><table><caption>Handfuls</caption><thead><tr><td>Handful</td><td>Number</td></tr></thead><tbody><tr><td>one</td><td>8</td></tr><tr><td>two</td><td>10</td></tr><tr><td>three</td><td>13</td></tr><tr><td>four</td><td>14</td></tr></tbody></table><br/>Allowed miseres:<br/><ul><li>low cards</li></ul><br/><h1>End of game</h1>attack wins</body></html>",nav_.getHtmlText());
    }

    private RulesTarot rules() {
        return rules(DealingTarot.DEAL_1_VS_4, true, true);
    }
    private RulesTarot rules(DealingTarot _deal, boolean _allowPlayCalledSuit, boolean _discardAfterCall) {
        RulesTarot rulesTarot_ = new RulesTarot((byte) _deal.getId().getNombreJoueurs());
        rulesTarot_.getCommon().setMixedCards(MixCardsChoice.EACH_LAUNCHING);
        rulesTarot_.getCommon().setGeneral(file(MixCardsChoice.EACH_LAUNCHING, AT_EACH_LAUNCHING));
        rulesTarot_.setDealing(_deal);
        IdList<BidTarot> bids_ = new IdList<BidTarot>();
        bids_.add(BidTarot.SLAM);
        rulesTarot_.allowSome(bids_);
        rulesTarot_.setMode(ModeTarot.NORMAL);
        rulesTarot_.getMiseres().add(Miseres.LOW_CARDS);
        rulesTarot_.getCommon().setSpecific(file(EndDealTarot.ATTACK_WIN, ATTACK_WINS)+RETURNE_LINE+file(Handfuls.ONE, ONE)+RETURNE_LINE+file(Handfuls.TWO, TWO)+RETURNE_LINE+file(Handfuls.THREE, THREE)+RETURNE_LINE+file(Handfuls.FOUR, FOUR)+RETURNE_LINE+file(ModeTarot.NORMAL, NORMAL)+RETURNE_LINE+file(DealingTarot.DEAL_1_VS_4,DEAL_1_VS_4)+RETURNE_LINE+file(Miseres.LOW_CARDS, LOW_CARDS)+RETURNE_LINE+file(BidTarot.SLAM, SLAM)+RETURNE_LINE+file(BidTarot.SLAM, SLAM)+RETURNE_LINE+file(BidTarot.FOLD, FOLD)+RETURNE_LINE+file(BidTarot.GUARD, GUARD));
        rulesTarot_.setEndDealTarot(EndDealTarot.ATTACK_WIN);
        rulesTarot_.setAllowPlayCalledSuit(_allowPlayCalledSuit);
        rulesTarot_.setDiscardAfterCall(_discardAfterCall);
        return rulesTarot_;
    }
    private static String file(MixCardsChoice _b, String _value) {
        return CoreResourcesAccess.key(_b)+ SEP +_value;
    }
    private static String file(Miseres _b, String _value) {
        return TarotResoucesAccess.key(_b)+ SEP +_value;
    }
    private static String file(Handfuls _b, String _value) {
        return TarotResoucesAccess.key(_b)+ SEP +_value;
    }
    private static String file(EndDealTarot _b, String _value) {
        return TarotResoucesAccess.key(_b)+ SEP +_value;
    }
    private static String file(ModeTarot _b, String _value) {
        return TarotResoucesAccess.key(_b)+ SEP +_value;
    }
    private static String file(BidTarot _b, String _value) {
        return TarotResoucesAccess.key(_b)+ SEP +_value;
    }
    private static String file(DealingTarot _b, String _value) {
        return TarotResoucesAccess.key(_b)+ SEP +_value;
    }
}
