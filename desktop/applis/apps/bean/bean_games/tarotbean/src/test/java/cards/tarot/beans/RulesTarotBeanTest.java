package cards.tarot.beans;

import cards.consts.EnumCardsExporterUtil;
import cards.consts.MixCardsChoice;
import cards.tarot.RulesTarot;
import cards.tarot.enumerations.*;
import code.util.IdList;
import code.util.*;
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
        StringList res_ = callRulesTarotBeanMiseres(displaying(beanRules(EN, rules())));
        assertEq(1, res_.size());
        assertEq(LOW_CARDS,res_.get(0));
    }
    @Test
    public void getMiseresNo() {
        assertEq(0, callRulesTarotBeanMiseres(displaying(beanRules(EN, rulesSh()))).size());
    }
    @Test
    public void getBids() {
        StringList res_ = callRulesTarotBeanContrats(displaying(beanRules(EN, rules())));
        assertEq(3, res_.size());
        assertEq(FOLD,res_.get(0));
        assertEq(GUARD,res_.get(1));
        assertEq(SLAM,res_.get(2));
    }

    @Test
    public void getHanfuls() {
        StringMap<Integer> res_ = callRulesTarotBeanPoigneesAutorisees(displaying(beanRules(EN, rules())));
        assertEq(4, res_.size());
        assertEq(ONE,res_.getKey(0));
        assertEq(8,res_.getValue(0));
        assertEq(TWO,res_.getKey(1));
        assertEq(10,res_.getValue(1));
        assertEq(THREE,res_.getKey(2));
        assertEq(13,res_.getValue(2));
        assertEq(FOUR,res_.getKey(3));
        assertEq(14,res_.getValue(3));
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

//    @Test
//    public void init1() {
//        StringMap<String> other_ = MessTarotPage.ms();
////        NavigationCore.adjust(other_);
//        StringMap<TranslationsAppli> mes_ = new StringMap<TranslationsAppli>();
//        mes_.addEntry(EN,MessTarotPage.enTarot());
//        mes_.addEntry(FR,MessTarotPage.frTarot());
//        TarotStandardsRules stds_ = new TarotStandardsRules();
//        NatNavigation nav_ = stds_.nav(new StringList(EN,FR), new RulesTarotLoader(), PagesTarots.buildRules(),other_,mes_);
//        nav_.setLanguage(EN);
//        stds_.setDataBaseRules(rules(DealingTarot.DEAL_1_VS_4, true, true));
//        stds_.initializeRendSessionDoc(nav_);
//        assertFalse(nav_.getHtmlText().isEmpty());
////        assertEq("<html xmlns:c=\"javahtml\"><head><link href=\""+TarotScriptPages.CSS+"\" rel=\"stylesheet\" type=\"text/css\"/><style>h1 {\n" +
////                "\tcolor:blue;\n" +
////                "}\n" +
////                "td,caption{\n" +
////                "\tborder:1px solid black;\n" +
////                "}\n" +
////                "</style></head><body><h1>Mix Cards</h1>at each launching<br/><h1>Players' repartition</h1>1 vs 4<br/><h1>Mode</h1>normal<br/><h1>Discarding after call</h1>yes<h1>Allow playing the called suit at the first round</h1>yes<br/><h1>Allowed Bids at the beginning of the deal</h1><ul><li>fold</li><li>guard</li><li>slam</li></ul><h1>Allowed declaring</h1><table><caption>Handfuls</caption><thead><tr><td>Handful</td><td>Number</td></tr></thead><tbody><tr><td>one</td><td>8</td></tr><tr><td>two</td><td>10</td></tr><tr><td>three</td><td>13</td></tr><tr><td>four</td><td>14</td></tr></tbody></table><br/>Allowed miseres:<br/><ul><li>low cards</li></ul><br/><h1>End of game</h1>attack wins</body></html>",nav_.getHtmlText());
//    }
//
//    @Test
//    public void init2() {
//        StringMap<String> other_ = MessTarotPage.ms();
////        NavigationCore.adjust(other_);
//        StringMap<TranslationsAppli> mes_ = new StringMap<TranslationsAppli>();
//        mes_.addEntry(EN,MessTarotPage.enTarot());
//        mes_.addEntry(FR,MessTarotPage.frTarot());
//        TarotStandardsRules stds_ = new TarotStandardsRules();
//        NatNavigation nav_ = stds_.nav(new StringList(EN,FR), new RulesTarotLoader(), PagesTarots.buildRules(),other_,mes_);
//        nav_.setLanguage(FR);
//        stds_.setDataBaseRules(rules(DealingTarot.DEAL_1_VS_4, true, true));
//        stds_.initializeRendSessionDoc(nav_);
//        assertFalse(nav_.getHtmlText().isEmpty());
////        assertEq("<html xmlns:c=\"javahtml\"><head><link href=\""+TarotScriptPages.CSS+"\" rel=\"stylesheet\" type=\"text/css\"/><style>h1 {\n" +
////                "\tcolor:blue;\n" +
////                "}\n" +
////                "td,caption{\n" +
////                "\tborder:1px solid black;\n" +
////                "}\n" +
////                "</style></head><body><h1>Battre les cartes</h1>at each launching<br/><h1>Répartition des joueurs</h1>1 vs 4<br/><h1>Mode</h1>normal<br/><h1>Ecart après appel</h1>oui<h1>Autoriser le jeu de la couleur appelée au premier tour</h1>oui<br/><h1>Enchères au début de la partie</h1><ul><li>fold</li><li>guard</li><li>slam</li></ul><h1>Annonces autorisées</h1><table><caption>Poignées</caption><thead><tr><td>Poignée</td><td>Nombre</td></tr></thead><tbody><tr><td>one</td><td>8</td></tr><tr><td>two</td><td>10</td></tr><tr><td>three</td><td>13</td></tr><tr><td>four</td><td>14</td></tr></tbody></table><br/>Misères autorisées:<br/><ul><li>low cards</li></ul><br/><h1>Fin de partie</h1>attack wins</body></html>",nav_.getHtmlText());
//    }

    private RulesTarot rules() {
        return rules(DealingTarot.DEAL_1_VS_4, true, true);
    }
    private RulesTarot rules(DealingTarot _deal, boolean _allowPlayCalledSuit, boolean _discardAfterCall) {
        RulesTarot rulesTarot_ = new RulesTarot(_deal.getId().getNombreJoueurs());
        rulesTarot_.getCommon().setMixedCards(MixCardsChoice.EACH_LAUNCHING);
        StringMap<String> m_ = new StringMap<String>();
        m_.addEntry(EnumCardsExporterUtil.fromMixCardsChoice(MixCardsChoice.EACH_LAUNCHING),AT_EACH_LAUNCHING);
        rulesTarot_.getCommon().setGeneral(m_);
        rulesTarot_.setDealing(_deal);
        IdList<BidTarot> bids_ = new IdList<BidTarot>();
        bids_.add(BidTarot.SLAM);
        rulesTarot_.allowSome(bids_);
        rulesTarot_.setMode(ModeTarot.NORMAL);
        rulesTarot_.getMiseres().add(Miseres.LOW_CARDS);
        StringMap<String> e_ = new  StringMap<String>();
        e_.addEntry(TarotCardsExporterUtil.BID+TarotCardsExporterUtil.fromBidTarot(BidTarot.FOLD),FOLD);
        e_.addEntry(TarotCardsExporterUtil.BID+TarotCardsExporterUtil.fromBidTarot(BidTarot.GUARD),GUARD);
        e_.addEntry(TarotCardsExporterUtil.BID+TarotCardsExporterUtil.fromBidTarot(BidTarot.SLAM),SLAM);
        e_.addEntry(TarotCardsExporterUtil.HANDFUL+TarotCardsExporterUtil.fromHandfuls(Handfuls.ONE),ONE);
        e_.addEntry(TarotCardsExporterUtil.HANDFUL+TarotCardsExporterUtil.fromHandfuls(Handfuls.TWO),TWO);
        e_.addEntry(TarotCardsExporterUtil.HANDFUL+TarotCardsExporterUtil.fromHandfuls(Handfuls.THREE),THREE);
        e_.addEntry(TarotCardsExporterUtil.HANDFUL+TarotCardsExporterUtil.fromHandfuls(Handfuls.FOUR),FOUR);
        e_.addEntry(TarotCardsExporterUtil.END_DEAL+TarotCardsExporterUtil.fromEndDealTarot(EndDealTarot.ATTACK_WIN),ATTACK_WINS);
        e_.addEntry(TarotCardsExporterUtil.MISERES+TarotCardsExporterUtil.fromMiseres(Miseres.LOW_CARDS),LOW_CARDS);
        e_.addEntry(TarotCardsExporterUtil.MODE+TarotCardsExporterUtil.fromModeTarot(ModeTarot.NORMAL),NORMAL);
        e_.addEntry(TarotCardsExporterUtil.DEAL+TarotCardsExporterUtil.fromDealingTarot(DealingTarot.DEAL_1_VS_4),DEAL_1_VS_4);
        rulesTarot_.getCommon().setSpecific(e_);
        rulesTarot_.setEndDealTarot(EndDealTarot.ATTACK_WIN);
        rulesTarot_.setAllowPlayCalledSuit(_allowPlayCalledSuit);
        rulesTarot_.setDiscardAfterCall(_discardAfterCall);
        return rulesTarot_;
    }
    private RulesTarot rulesSh() {
        return rulesSh(DealingTarot.DEAL_1_VS_4, true, true);
    }
    private RulesTarot rulesSh(DealingTarot _deal, boolean _allowPlayCalledSuit, boolean _discardAfterCall) {
        RulesTarot rulesTarot_ = new RulesTarot(_deal.getId().getNombreJoueurs());
        rulesTarot_.getCommon().setMixedCards(MixCardsChoice.EACH_LAUNCHING);
        StringMap<String> m_ = new StringMap<String>();
        m_.addEntry(EnumCardsExporterUtil.fromMixCardsChoice(MixCardsChoice.EACH_LAUNCHING),AT_EACH_LAUNCHING);
        rulesTarot_.getCommon().setGeneral(m_);
        rulesTarot_.setDealing(_deal);
        IdList<BidTarot> bids_ = new IdList<BidTarot>();
        bids_.add(BidTarot.SLAM);
        rulesTarot_.allowSome(bids_);
        rulesTarot_.setMode(ModeTarot.NORMAL);
        StringMap<String> e_ = new  StringMap<String>();
        e_.addEntry(TarotCardsExporterUtil.BID+TarotCardsExporterUtil.fromBidTarot(BidTarot.FOLD),FOLD);
        e_.addEntry(TarotCardsExporterUtil.BID+TarotCardsExporterUtil.fromBidTarot(BidTarot.GUARD),GUARD);
        e_.addEntry(TarotCardsExporterUtil.BID+TarotCardsExporterUtil.fromBidTarot(BidTarot.SLAM),SLAM);
        e_.addEntry(TarotCardsExporterUtil.HANDFUL+TarotCardsExporterUtil.fromHandfuls(Handfuls.ONE),ONE);
        e_.addEntry(TarotCardsExporterUtil.HANDFUL+TarotCardsExporterUtil.fromHandfuls(Handfuls.TWO),TWO);
        e_.addEntry(TarotCardsExporterUtil.HANDFUL+TarotCardsExporterUtil.fromHandfuls(Handfuls.THREE),THREE);
        e_.addEntry(TarotCardsExporterUtil.HANDFUL+TarotCardsExporterUtil.fromHandfuls(Handfuls.FOUR),FOUR);
        e_.addEntry(TarotCardsExporterUtil.END_DEAL+TarotCardsExporterUtil.fromEndDealTarot(EndDealTarot.ATTACK_WIN),ATTACK_WINS);
        e_.addEntry(TarotCardsExporterUtil.MISERES+TarotCardsExporterUtil.fromMiseres(Miseres.LOW_CARDS),LOW_CARDS);
        e_.addEntry(TarotCardsExporterUtil.MODE+TarotCardsExporterUtil.fromModeTarot(ModeTarot.NORMAL),NORMAL);
        e_.addEntry(TarotCardsExporterUtil.DEAL+TarotCardsExporterUtil.fromDealingTarot(DealingTarot.DEAL_1_VS_4),DEAL_1_VS_4);
        rulesTarot_.getCommon().setSpecific(e_);
        rulesTarot_.setEndDealTarot(EndDealTarot.ATTACK_WIN);
        rulesTarot_.setAllowPlayCalledSuit(_allowPlayCalledSuit);
        rulesTarot_.setDiscardAfterCall(_discardAfterCall);
        return rulesTarot_;
    }
}
