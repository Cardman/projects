package cards.president.beans;

import cards.consts.MixCardsChoice;
import cards.president.RulesPresident;
import cards.president.enumerations.EqualtyPlaying;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Navigation;
import code.formathtml.analyze.blocks.AnaRendBlock;
import code.scripts.pages.cards.MessPresidentPage;
import code.scripts.pages.cards.PagesPresidents;
import code.util.StringMap;
import code.util.consts.Constants;
import org.junit.Test;

public final class RulesPresidentBeanTest extends BeanPresidentCommonTs {

    private static final String AT_EACH_LAUNCHING = "at each launching";
    private static final String FORBIDDEN = "forbidden";
    private static final String SEP = ":";

    @Test
    public void getCartesBattues() {
        Struct bean_ = displaying(beanRules(EN, rules()));
        assertEq(AT_EACH_LAUNCHING,callRulesPresidentCartesBattues(bean_));
    }

    @Test
    public void getNbPlayers() {
        Struct bean_ = displaying(beanRules(EN, rules()));
        assertEq(4,callRulesPresidentBeanNbPlayers(bean_));
    }

    @Test
    public void getNbStacks() {
        Struct bean_ = displaying(beanRules(EN, rules()));
        assertEq(1,callRulesPresidentBeanNbStacks(bean_));
    }

    @Test
    public void getEqualty() {
        Struct bean_ = displaying(beanRules(EN, rules()));
        assertEq(FORBIDDEN,callRulesPresidentBeanEqualty(bean_));
    }

    @Test
    public void isPossibleReversing1() {
        Struct bean_ = displaying(beanRules(EN, rules(true, true, true, true, true, 4, 1)));
        assertTrue(callRulesPresidentBeanPossibleReversing(bean_));
    }

    @Test
    public void isPossibleReversing2() {
        Struct bean_ = displaying(beanRules(EN, rules(false, true, true, true, true, 4, 1)));
        assertFalse(callRulesPresidentBeanPossibleReversing(bean_));
    }

    @Test
    public void isHasToPlay1() {
        Struct bean_ = displaying(beanRules(EN, rules(true, true, true, true, true, 4, 1)));
        assertTrue(callRulesPresidentBeanHasToPlay(bean_));
    }

    @Test
    public void isHasToPlay2() {
        Struct bean_ = displaying(beanRules(EN, rules(true, false, true, true, true, 4, 1)));
        assertFalse(callRulesPresidentBeanHasToPlay(bean_));
    }

    @Test
    public void isLoosingIfFinishByBestCards1() {
        Struct bean_ = displaying(beanRules(EN, rules(true, true, true, true, true, 4, 1)));
        assertTrue(callRulesPresidentBeanLoosingIfFinishByBestCards(bean_));
    }

    @Test
    public void isLoosingIfFinishByBestCards2() {
        Struct bean_ = displaying(beanRules(EN, rules(true, true, false, true, true, 4, 1)));
        assertFalse(callRulesPresidentBeanLoosingIfFinishByBestCards(bean_));
    }

    @Test
    public void isSwitchCards1() {
        Struct bean_ = displaying(beanRules(EN, rules(true, true, true, true, true, 4, 1)));
        assertTrue(callRulesPresidentBeanSwitchCards(bean_));
    }

    @Test
    public void isSwitchCards2() {
        Struct bean_ = displaying(beanRules(EN, rules(true, true, true, false, true, 4, 1)));
        assertFalse(callRulesPresidentBeanSwitchCards(bean_));
    }

    @Test
    public void isLooserStartsFirst1() {
        Struct bean_ = displaying(beanRules(EN, rules(true, true, true, true, true, 4, 1)));
        assertTrue(callRulesPresidentBeanLooserStartsFirst(bean_));
    }

    @Test
    public void isLooserStartsFirst2() {
        Struct bean_ = displaying(beanRules(EN, rules(true, true, true, true, false, 4, 1)));
        assertFalse(callRulesPresidentBeanLooserStartsFirst(bean_));
    }

    @Test
    public void getNbCardsPerPlayerMin() {
        Struct bean_ = displaying(beanRules(EN, rules(true, true, true, true, true, 4, 1)));
        assertEq(13,callRulesPresidentBeanNbCardsPerPlayerMin(bean_));
    }

    @Test
    public void sameAmount1() {
        Struct bean_ = displaying(beanRules(EN, rules(true, true, true, true, true, 4, 1)));
        assertTrue(callRulesPresidentBeanSameAmount(bean_));
    }

    @Test
    public void sameAmount2() {
        Struct bean_ = displaying(beanRules(EN, rules(true, true, true, true, true, 5, 1)));
        assertFalse(callRulesPresidentBeanSameAmount(bean_));
    }

    @Test
    public void getNbCardsPerPlayerMax1() {
        Struct bean_ = displaying(beanRules(EN, rules(true, true, true, true, true, 4, 1)));
        assertEq(13,callRulesPresidentBeanNbCardsPerPlayerMax(bean_));
    }

    @Test
    public void getNbCardsPerPlayerMax2() {
        Struct bean_ = displaying(beanRules(EN, rules(true, true, true, true, true, 5, 1)));
        assertEq(11,callRulesPresidentBeanNbCardsPerPlayerMax(bean_));
    }

    @Test
    public void init() {
        StringMap<String> other_ = MessPresidentPage.ms();
        AnaRendBlock.adjust(other_);
        PresidentStandardsRules stds_ = new PresidentStandardsRules();
        Navigation nav_ = stds_.nav(Constants.getAvailableLanguages(),EN,new RulesPresidentLoader(),PagesPresidents.buildRules(),other_,other_,"");
        stds_.setDataBaseRules(rules(true, true, true, true, true, 4, 1));
        stds_.initializeRendSessionDoc(nav_);
        assertEq("<html xmlns:c=\"javahtml\"><head><link href=\"resources_cards/css/president.css\" rel=\"stylesheet\" type=\"text/css\"/><style>h1 {\n" +
                "\tcolor: #0000FF;\n" +
                "}\n" +
                "</style></head><body><h1>Mix Cards</h1>at each launching<br/><h1>Number of players</h1>4<br/><h1>Number of stacks</h1>1<br/>Number of cards per player: 13<br/><br/><h1>Equality</h1>forbidden<br/><h1>Possible reversing strength</h1>yes<br/><h1>Every player has to play if possible</h1>yes<br/><h1>A player looses if this player finish by the best cards</h1>yes<br/><h1>For next deals, players can switch their cards at the beginning</h1>yes<br/><h1>For next deals, the looser player starts first the first trick</h1>yes<br/></body></html>",nav_.getHtmlText());
    }


    private RulesPresident rules() {
        return rules(true, true, true, true, true, 4, 1);
    }
    private RulesPresident rules(boolean _possibleReversing, boolean _hasToPlay, boolean _loosingIfFinishByBestCards, boolean _switchCards, boolean _looserStartsFirst, int _nbPlayers, int _nbStacks) {
        RulesPresident rulesPresident_ = new RulesPresident();
        rulesPresident_.getCommon().setMixedCards(MixCardsChoice.EACH_LAUNCHING);
        rulesPresident_.setNbPlayers(_nbPlayers);
        rulesPresident_.setNbStacks(_nbStacks);
        rulesPresident_.setEqualty(EqualtyPlaying.FORBIDDEN);
        rulesPresident_.getCommon().setGeneral(file(MixCardsChoice.EACH_LAUNCHING, AT_EACH_LAUNCHING));
        rulesPresident_.getCommon().setSpecific(file(EqualtyPlaying.FORBIDDEN, FORBIDDEN));
        rulesPresident_.setPossibleReversing(_possibleReversing);
        rulesPresident_.setHasToPlay(_hasToPlay);
        rulesPresident_.setLoosingIfFinishByBestCards(_loosingIfFinishByBestCards);
        rulesPresident_.setSwitchCards(_switchCards);
        rulesPresident_.setLooserStartsFirst(_looserStartsFirst);
        return rulesPresident_;
    }
    private static String file(MixCardsChoice _b, String _value) {
        return RulesPresidentBean.key(_b)+ SEP +_value;
    }
    private static String file(EqualtyPlaying _b, String _value) {
        return RulesPresidentBean.key(_b)+ SEP +_value;
    }
}
