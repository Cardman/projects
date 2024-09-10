package cards.belote.beans;

import cards.belote.RulesBelote;
import cards.belote.enumerations.*;
import cards.consts.EnumCardsExporterUtil;
import cards.consts.MixCardsChoice;
import code.bean.nat.NatNavigation;
import code.bean.nat.*;
import code.scripts.confs.BeloteScriptPages;
import code.scripts.pages.cards.MessBelotePage;
import code.scripts.pages.cards.PagesBelotes;
import code.sml.util.*;
import code.util.*;
import code.util.core.BoolVal;
import org.junit.Test;

public final class RulesBeloteBeanTest extends BeanBeloteCommonTs {
    private static final String AT_EACH_LAUNCHING = "at each launching";
    private static final String HUNDRED = "hundred";
    private static final String UNDERTRUMP_OVERTRUMP = "undertrump overtrump";
    private static final String FOLD = "fold";
    private static final String SUIT = "suit";
    private static final String OTHER_SUIT = "other suit";
    private static final String CLASSIC = "classic";
    private static final String COINCHE = "coinche";

    @Test
    public void getCartesBattues() {
        assertEq(AT_EACH_LAUNCHING,callRulesBeloteBeanCartesBattues(displaying(beanRules(EN, rules()))));
    }

    @Test
    public void isDealAll1() {
        assertTrue(callRulesBeloteBeanDealAll(displaying(beanRules(EN, rules(DealingBelote.COINCHE_2_VS_2, true, true)))));
    }

    @Test
    public void isDealAll2() {
        assertFalse(callRulesBeloteBeanDealAll(displaying(beanRules(EN, rules()))));
    }

    @Test
    public void getAnnoncesAutorisees() {
        NaSt res_ = callRulesBeloteBeanAnnoncesAutorisees(displaying(beanRules(EN, rules())));
        assertSizeEq(1,res_);
        assertEq(HUNDRED,res_,0);
    }

    @Test
    public void isSousCoupeAdv1() {
        assertTrue(callRulesBeloteBeanSousCoupeAdv(displaying(beanRules(EN, rules()))));
    }

    @Test
    public void isSousCoupeAdv2() {
        assertFalse(callRulesBeloteBeanSousCoupeAdv(displaying(beanRules(EN, rules(DealingBelote.CLASSIC_2_VS_2, false, true)))));
    }

    @Test
    public void getGestionCoupePartenaire() {
        assertEq(UNDERTRUMP_OVERTRUMP,callRulesBeloteBeanGestionCoupePartenaire(displaying(beanRules(EN, rules()))));
    }

    @Test
    public void getEncheresAutorisees() {
        NaSt res_ = callRulesBeloteBeanEncheresAutorisees(displaying(beanRules(EN, rules())));
        assertSizeEq(3,res_);
        assertEq(FOLD,res_,0);
        assertEq(SUIT,res_,1);
        assertEq(OTHER_SUIT,res_,2);
    }

    @Test
    public void getRepartition1() {
        assertEq(CLASSIC,callRulesBeloteBeanRepartition(displaying(beanRules(EN, rules()))));
    }

    @Test
    public void getRepartition2() {
        assertEq(COINCHE,callRulesBeloteBeanRepartition(displaying(beanRules(EN, rules(DealingBelote.COINCHE_2_VS_2, true, true)))));
    }

    @Test
    public void isComptePointsClassique1() {
        assertTrue(callRulesBeloteBeanComptePointsClassique(displaying(beanRules(EN, rules(DealingBelote.CLASSIC_2_VS_2, true, true)))));
    }

    @Test
    public void isComptePointsClassique2() {
        assertFalse(callRulesBeloteBeanComptePointsClassique(displaying(beanRules(EN, rules(DealingBelote.CLASSIC_2_VS_2, true, false)))));
    }

    @Test
    public void init1() {
        StringMap<String> other_ = MessBelotePage.ms();
//        NavigationCore.adjust(other_);
        StringMap<TranslationsAppli> mes_ = new StringMap<TranslationsAppli>();
        mes_.addEntry(EN,MessBelotePage.enBelote());
        mes_.addEntry(FR,MessBelotePage.frBelote());
        BeloteStandardsRules stds_ = new BeloteStandardsRules();
        NatNavigation nav_ = stds_.nav(new StringList(EN,FR), new RulesBeloteLoader(), PagesBelotes.buildRules(),other_,mes_);
        nav_.setLanguage(EN);
        stds_.setDataBaseRules(rules(DealingBelote.CLASSIC_2_VS_2, true, true));
        stds_.initializeRendSessionDoc(nav_);
        assertEq("<html xmlns:c=\"javahtml\"><head><link href=\""+BeloteScriptPages.CSS+"\" rel=\"stylesheet\" type=\"text/css\"/><style>h1 {\n" +
                "\tcolor:blue;\n" +
                "}\n" +
                "</style></head><body><h1>Mix Cards</h1>at each launching<br/><h1>Deal all cards</h1>no<br/><h1>Allowed declaring</h1><ul><li>fold</li><li>suit</li><li>other suit</li></ul><br/><h1>Undertrumping a foe</h1>yes<br/><br/><h1>Allowed Bids at the beginning of the deal</h1><ul><li>hundred</li></ul><h1>Rules of playing trumps when a partner is leading the current trick</h1>undertrump overtrump<br/><h1>Players' repartition</h1>classic<br/><h1>End of game</h1>162 - 0, if the attack's team looses.<br/></body></html>",nav_.getHtmlText());
    }

    @Test
    public void init2() {
        StringMap<String> other_ = MessBelotePage.ms();
//        NavigationCore.adjust(other_);
        StringMap<TranslationsAppli> mes_ = new StringMap<TranslationsAppli>();
        mes_.addEntry(EN,MessBelotePage.enBelote());
        mes_.addEntry(FR,MessBelotePage.frBelote());
        BeloteStandardsRules stds_ = new BeloteStandardsRules();
        NatNavigation nav_ = stds_.nav(new StringList(EN,FR), new RulesBeloteLoader(), PagesBelotes.buildRules(),other_,mes_);
        nav_.setLanguage(FR);
        stds_.setDataBaseRules(rules(DealingBelote.CLASSIC_2_VS_2, true, true));
        stds_.initializeRendSessionDoc(nav_);
        assertEq("<html xmlns:c=\"javahtml\"><head><link href=\""+BeloteScriptPages.CSS+"\" rel=\"stylesheet\" type=\"text/css\"/><style>h1 {\n" +
                "\tcolor:blue;\n" +
                "}\n" +
                "</style></head><body><h1>Battre les cartes</h1>at each launching<br/><h1>Distribuer toutes les cartes</h1>non<br/><h1>Annonces autorisées</h1><ul><li>fold</li><li>suit</li><li>other suit</li></ul><br/><h1>Sous-couper un adversaire</h1>oui<br/><br/><h1>Enchères autorisées au début de la partie</h1><ul><li>hundred</li></ul><h1>Règles du jeu des atouts lorsqu'un partenaire est maître du pli courant</h1>undertrump overtrump<br/><h1>Répartition des joueurs</h1>classic<br/><h1>Fin de partie</h1>162 - 0, si l'attaque perd.<br/></body></html>",nav_.getHtmlText());
    }

    private RulesBelote rules() {
        return rules(DealingBelote.CLASSIC_2_VS_2, true, true);
    }
    private RulesBelote rules(DealingBelote _deal, boolean _sousCoupeAdv, boolean _comptePointsClassique) {
        RulesBelote rulesBelote_ = new RulesBelote();
        rulesBelote_.getCommon().setMixedCards(MixCardsChoice.EACH_LAUNCHING);
        StringMap<String> m_ = new StringMap<String>();
        m_.addEntry(EnumCardsExporterUtil.fromMixCardsChoice(MixCardsChoice.EACH_LAUNCHING),AT_EACH_LAUNCHING);
        rulesBelote_.getCommon().setGeneral(m_);
        rulesBelote_.setDealing(_deal);
        rulesBelote_.getAllowedDeclares().put(DeclaresBelote.HUNDRED, BoolVal.TRUE);
        StringMap<String> e_ = new StringMap<String>();
        e_.addEntry(BeloteCardsExporterUtil.BID+BeloteCardsExporterUtil.fromBidBelote(BidBelote.FOLD),FOLD);
        e_.addEntry(BeloteCardsExporterUtil.BID+BeloteCardsExporterUtil.fromBidBelote(BidBelote.SUIT),SUIT);
        e_.addEntry(BeloteCardsExporterUtil.BID+BeloteCardsExporterUtil.fromBidBelote(BidBelote.OTHER_SUIT),OTHER_SUIT);
        e_.addEntry(BeloteCardsExporterUtil.TRUMPING+BeloteCardsExporterUtil.fromBeloteTrumpPartner(BeloteTrumpPartner.UNDERTRUMP_OVERTRUMP),UNDERTRUMP_OVERTRUMP);
        e_.addEntry(BeloteCardsExporterUtil.DECLARE+BeloteCardsExporterUtil.fromDeclaresBelote(DeclaresBelote.HUNDRED),HUNDRED);
        e_.addEntry(BeloteCardsExporterUtil.DEAL+BeloteCardsExporterUtil.fromDealingBelote(DealingBelote.CLASSIC_2_VS_2),CLASSIC);
        e_.addEntry(BeloteCardsExporterUtil.DEAL+BeloteCardsExporterUtil.fromDealingBelote(DealingBelote.COINCHE_2_VS_2),COINCHE);
        e_.addEntry(BeloteCardsExporterUtil.LAST_TRICK,"Ten points at last trick");
        e_.addEntry(BeloteCardsExporterUtil.DECLARE_PAIR,"Belote rebelote");
        rulesBelote_.getCommon().setSpecific(e_);
        rulesBelote_.setSousCoupeAdv(_sousCoupeAdv);
        rulesBelote_.setTrumpPartner(BeloteTrumpPartner.UNDERTRUMP_OVERTRUMP);
        rulesBelote_.setComptePointsClassique(_comptePointsClassique);
        return rulesBelote_;
    }

}
