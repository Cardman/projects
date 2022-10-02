package cards.belote.beans;

import cards.belote.RulesBelote;
import cards.belote.enumerations.BeloteTrumpPartner;
import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.DealingBelote;
import cards.belote.enumerations.DeclaresBelote;
import cards.consts.MixCardsChoice;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Navigation;
import code.formathtml.analyze.blocks.AnaRendBlock;
import code.scripts.pages.cards.MessBelotePage;
import code.scripts.pages.cards.PagesBelotes;
import code.util.StringMap;
import code.util.consts.Constants;
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
        Struct res_ = callRulesBeloteBeanAnnoncesAutorisees(displaying(beanRules(EN, rules())));
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
        Struct res_ = callRulesBeloteBeanEncheresAutorisees(displaying(beanRules(EN, rules())));
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
    public void init() {
        StringMap<String> other_ = MessBelotePage.ms();
        AnaRendBlock.adjust(other_);
        BeloteStandardsRules stds_ = new BeloteStandardsRules();
        Navigation nav_ = stds_.nav(Constants.getAvailableLanguages(),EN,new RulesBeloteLoader(), PagesBelotes.buildRules(),other_,other_,"");
        stds_.setDataBaseRules(rules(DealingBelote.CLASSIC_2_VS_2, true, true));
        stds_.initializeRendSessionDoc(nav_);
        assertEq("<html xmlns:c=\"javahtml\"><head><link href=\"resources_cards/css/belote.css\" rel=\"stylesheet\" type=\"text/css\"/><style>h1 {\n" +
                "\tcolor: #0000FF;\n" +
                "}\n" +
                "</style></head><body><h1>Mix Cards</h1>at each launching<br/><h1>Deal all cards</h1>no<br/><h1>Allowed declaring</h1><ul><li>fold</li><li>suit</li><li>other suit</li></ul><br/><h1>Undertrumping a foe</h1>yes<br/><br/><h1>Allowed Bids at the beginning of the deal</h1><ul><li>hundred</li></ul><h1>Rules of playing trumps when a partner is leading the current trick</h1>undertrump overtrump<br/><h1>Players' repartition</h1>classic<br/><h1>End of game</h1>162 - 0, if the attack's team looses.<br/></body></html>",nav_.getHtmlText());
    }

    private RulesBelote rules() {
        return rules(DealingBelote.CLASSIC_2_VS_2, true, true);
    }
    private RulesBelote rules(DealingBelote _deal, boolean _sousCoupeAdv, boolean _comptePointsClassique) {
        RulesBelote rulesBelote_ = new RulesBelote();
        rulesBelote_.getCommon().setMixedCards(MixCardsChoice.EACH_LAUNCHING);
        rulesBelote_.getCommon().setGeneral(file(MixCardsChoice.EACH_LAUNCHING, AT_EACH_LAUNCHING));
        rulesBelote_.setDealing(_deal);
        rulesBelote_.getAllowedDeclares().put(DeclaresBelote.HUNDRED, BoolVal.TRUE);
        rulesBelote_.getCommon().setSpecific(file(DeclaresBelote.HUNDRED, HUNDRED)+RETURNE_LINE+file(BeloteTrumpPartner.UNDERTRUMP_OVERTRUMP, UNDERTRUMP_OVERTRUMP)+RETURNE_LINE+file(BidBelote.FOLD, FOLD)+RETURNE_LINE+file(BidBelote.SUIT, SUIT)+RETURNE_LINE+file(BidBelote.OTHER_SUIT, OTHER_SUIT)+RETURNE_LINE+fileDeal());
        rulesBelote_.setSousCoupeAdv(_sousCoupeAdv);
        rulesBelote_.setTrumpPartner(BeloteTrumpPartner.UNDERTRUMP_OVERTRUMP);
        rulesBelote_.setComptePointsClassique(_comptePointsClassique);
        return rulesBelote_;
    }
    private static String file(MixCardsChoice _b, String _value) {
        return BeloteBean.key(_b)+ SEP +_value;
    }
    private static String file(DeclaresBelote _b, String _value) {
        return BeloteBean.key(_b)+ SEP +_value;
    }
    private static String file(BeloteTrumpPartner _b, String _value) {
        return BeloteBean.key(_b)+ SEP +_value;
    }
    private static String file(BidBelote _b, String _value) {
        return BeloteBean.key(_b)+ SEP +_value;
    }
    private static String fileDeal() {
        return fileDealClassic()+ RETURNE_LINE +fileDealCoinche();
    }
    private static String fileDealClassic() {
        return BeloteBean.key(DealingBelote.CLASSIC_2_VS_2)+ SEP + CLASSIC;
    }
    private static String fileDealCoinche() {
        return BeloteBean.key(DealingBelote.COINCHE_2_VS_2)+ SEP + COINCHE;
    }
}
