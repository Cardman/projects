package cards.president.beans;

import cards.consts.EnumCardsExporterUtil;
import cards.consts.MixCardsChoice;
import cards.president.RulesPresident;
import cards.president.enumerations.EqualtyPlaying;
import cards.president.enumerations.PresidentCardsExporterUtil;
import code.bean.nat.NatNavigation;
import code.scripts.pages.cards.MessPresidentPage;
import code.scripts.pages.cards.PagesPresidents;
import code.sml.util.*;
import code.util.*;
import org.junit.Test;

public final class RulesPresidentBeanTest extends BeanPresidentCommonTs {

    private static final String AT_EACH_LAUNCHING = "at each launching";
    private static final String FORBIDDEN = "forbidden";
    private static final String SEP = ":";

    @Test
    public void getCartesBattues() {
        assertEq(AT_EACH_LAUNCHING,callRulesPresidentCartesBattues(displaying(beanRules(EN, rules()))));
    }

    @Test
    public void getNbPlayers() {
        assertEq(4,callRulesPresidentBeanNbPlayers(displaying(beanRules(EN, rules()))));
    }

    @Test
    public void getNbStacks() {
        assertEq(1,callRulesPresidentBeanNbStacks(displaying(beanRules(EN, rules()))));
    }

    @Test
    public void getEqualty() {
        assertEq(FORBIDDEN,callRulesPresidentBeanEqualty(displaying(beanRules(EN, rules()))));
    }

    @Test
    public void isPossibleReversing1() {
        assertTrue(callRulesPresidentBeanPossibleReversing(displaying(beanRules(EN, rules(true, true, true, true, true, 4, 1)))));
    }

    @Test
    public void isPossibleReversing2() {
        assertFalse(callRulesPresidentBeanPossibleReversing(displaying(beanRules(EN, rules(false, true, true, true, true, 4, 1)))));
    }

    @Test
    public void isHasToPlay1() {
        assertTrue(callRulesPresidentBeanHasToPlay(displaying(beanRules(EN, rules(true, true, true, true, true, 4, 1)))));
    }

    @Test
    public void isHasToPlay2() {
        assertFalse(callRulesPresidentBeanHasToPlay(displaying(beanRules(EN, rules(true, false, true, true, true, 4, 1)))));
    }

    @Test
    public void isLoosingIfFinishByBestCards1() {
        assertTrue(callRulesPresidentBeanLoosingIfFinishByBestCards(displaying(beanRules(EN, rules(true, true, true, true, true, 4, 1)))));
    }

    @Test
    public void isLoosingIfFinishByBestCards2() {
        assertFalse(callRulesPresidentBeanLoosingIfFinishByBestCards(displaying(beanRules(EN, rules(true, true, false, true, true, 4, 1)))));
    }

    @Test
    public void isSwitchCards1() {
        assertTrue(callRulesPresidentBeanSwitchCards(displaying(beanRules(EN, rules(true, true, true, true, true, 4, 1)))));
    }

    @Test
    public void isSwitchCards2() {
        assertFalse(callRulesPresidentBeanSwitchCards(displaying(beanRules(EN, rules(true, true, true, false, true, 4, 1)))));
    }

    @Test
    public void isLooserStartsFirst1() {
        assertTrue(callRulesPresidentBeanLooserStartsFirst(displaying(beanRules(EN, rules(true, true, true, true, true, 4, 1)))));
    }

    @Test
    public void isLooserStartsFirst2() {
        assertFalse(callRulesPresidentBeanLooserStartsFirst(displaying(beanRules(EN, rules(true, true, true, true, false, 4, 1)))));
    }

    @Test
    public void getNbCardsPerPlayerMin() {
        assertEq(13,callRulesPresidentBeanNbCardsPerPlayerMin(displaying(beanRules(EN, rules(true, true, true, true, true, 4, 1)))));
    }

    @Test
    public void sameAmount1() {
        assertTrue(callRulesPresidentBeanSameAmount(displaying(beanRules(EN, rules(true, true, true, true, true, 4, 1)))));
    }

    @Test
    public void sameAmount2() {
        assertFalse(callRulesPresidentBeanSameAmount(displaying(beanRules(EN, rules(true, true, true, true, true, 5, 1)))));
    }

    @Test
    public void getNbCardsPerPlayerMax1() {
        assertEq(13,callRulesPresidentBeanNbCardsPerPlayerMax(displaying(beanRules(EN, rules(true, true, true, true, true, 4, 1)))));
    }

    @Test
    public void getNbCardsPerPlayerMax2() {
        assertEq(11,callRulesPresidentBeanNbCardsPerPlayerMax(displaying(beanRules(EN, rules(true, true, true, true, true, 5, 1)))));
    }

    @Test
    public void init1() {
        StringMap<String> other_ = MessPresidentPage.ms();
//        NavigationCore.adjust(other_);
        StringMap<TranslationsAppli> mes_ = new StringMap<TranslationsAppli>();
        mes_.addEntry(EN,MessPresidentPage.enPresident());
        mes_.addEntry(FR,MessPresidentPage.frPresident());
        PresidentStandardsRules stds_ = new PresidentStandardsRules();
        NatNavigation nav_ = stds_.nav(new StringList("en","fr"), new RulesPresidentLoader(),PagesPresidents.buildRules(),other_,mes_);
        nav_.setLanguage(EN);
        stds_.setDataBaseRules(rules(true, true, true, true, true, 4, 1));
        stds_.initializeRendSessionDoc(nav_);
        assertEq("<html xmlns:c=\"javahtml\"><head><link href=\"resources_cards/css/president.css\" rel=\"stylesheet\" type=\"text/css\"/><style>h1 {\n" +
                "\tcolor:blue;\n" +
                "}\n" +
                "</style></head><body><h1>Mix Cards</h1>at each launching<br/><h1>Number of players</h1>4<br/><h1>Number of stacks</h1>1<br/>Number of cards per player: 13<br/><br/><h1>Equality</h1>forbidden<br/><h1>Possible reversing strength</h1>yes<br/><h1>Every player has to play if possible</h1>yes<br/><h1>A player looses if this player finish by the best cards</h1>yes<br/><h1>For next deals, players can switch their cards at the beginning</h1>yes<br/><h1>For next deals, the looser player starts first the first trick</h1>yes<br/></body></html>",nav_.getHtmlText());
    }

    @Test
    public void init2() {
        StringMap<String> other_ = MessPresidentPage.ms();
//        NavigationCore.adjust(other_);
        StringMap<TranslationsAppli> mes_ = new StringMap<TranslationsAppli>();
        mes_.addEntry(EN,MessPresidentPage.enPresident());
        mes_.addEntry(FR,MessPresidentPage.frPresident());
        PresidentStandardsRules stds_ = new PresidentStandardsRules();
        NatNavigation nav_ = stds_.nav(new StringList("en","fr"), new RulesPresidentLoader(),PagesPresidents.buildRules(),other_,mes_);
        nav_.setLanguage(FR);
        stds_.setDataBaseRules(rules(true, true, true, true, true, 4, 1));
        stds_.initializeRendSessionDoc(nav_);
        assertEq("<html xmlns:c=\"javahtml\"><head><link href=\"resources_cards/css/president.css\" rel=\"stylesheet\" type=\"text/css\"/><style>h1 {\n" +
                "\tcolor:blue;\n" +
                "}\n" +
                "</style></head><body><h1>Battre les cartes</h1>at each launching<br/><h1>Nombre de joueurs</h1>4<br/><h1>Nombre de tas</h1>1<br/>Nombre de cartes par joueur: 13<br/><br/><h1>Égalité</h1>forbidden<br/><h1>Révolution possible</h1>oui<br/><h1>Chaque joueur doit jouer si possible</h1>oui<br/><h1>Un joueur perd si ce joueur finit par les meilleures cartes</h1>oui<br/><h1>Pour les donnes suivantes, les joueurs peuvent échanger leurs cartes au début</h1>oui<br/><h1>Pour les donnes suivantes, le perdant commence à jouer le premier pli</h1>oui<br/></body></html>",nav_.getHtmlText());
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
        StringMap<String> m_ = new StringMap<String>();
        m_.addEntry(EnumCardsExporterUtil.fromMixCardsChoice(MixCardsChoice.EACH_LAUNCHING),AT_EACH_LAUNCHING);
        rulesPresident_.getCommon().setGeneral(m_);
        StringMap<String> e_ = new StringMap<String>();
        e_.addEntry(PresidentCardsExporterUtil.EQUALTY+PresidentCardsExporterUtil.fromEqualtyPlaying(EqualtyPlaying.FORBIDDEN),FORBIDDEN);
        rulesPresident_.getCommon().setSpecific(e_);
        rulesPresident_.setPossibleReversing(_possibleReversing);
        rulesPresident_.setHasToPlay(_hasToPlay);
        rulesPresident_.setLoosingIfFinishByBestCards(_loosingIfFinishByBestCards);
        rulesPresident_.setSwitchCards(_switchCards);
        rulesPresident_.setLooserStartsFirst(_looserStartsFirst);
        return rulesPresident_;
    }
}
