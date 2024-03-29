package cards.president.beans;

import code.bean.Bean;
import code.bean.nat.SpecNatMethod;
import code.bean.nat.SpecialNatClass;
import code.bean.nat.StandardField;
import code.bean.nat.analyze.NatConfigurationCore;
import code.util.CustList;

public final class PresidentStandardsRules extends PresidentStandards {

    private static final String TYPE_RULES_PRESIDENT_BEAN = "cards.president.beans.RulesPresidentBean";
    private static final String SAME_AMOUNT = "sameAmount";
    private static final String NB_CARDS_PER_PLAYER_MAX = "nbCardsPerPlayerMax";
    private static final String NB_CARDS_PER_PLAYER_MIN = "nbCardsPerPlayerMin";
    private static final String NB_STACKS = "nbStacks";
    private static final String NB_PLAYERS = "nbPlayers";
    private static final String LOOSER_STARTS_FIRST = "looserStartsFirst";
    private static final String SWITCH_CARDS = "switchCards";
    private static final String LOOSING_IF_FINISH_BY_BEST_CARDS = "loosingIfFinishByBestCards";
    private static final String HAS_TO_PLAY = "hasToPlay";
    private static final String POSSIBLE_REVERSING = "possibleReversing";
    private static final String EQUALTY = "equalty";
    private static final String CARTES_BATTUES = "cartesBattues";

    @Override
    public void buildOther() {
        buildRulesPresidentBean();
    }

    private void buildRulesPresidentBean() {
        CustList<StandardField> fields_ = new CustList<StandardField>();
        CustList<SpecNatMethod> methods_ = new CustList<SpecNatMethod>();
        SpecialNatClass std_ = new SpecialNatClass(fields_, methods_, TYPE_BEAN);
        fields_.add( new StandardField(CARTES_BATTUES, STRING, new RulesPresidentBeanCartesBattues(),null));
        fields_.add( new StandardField(EQUALTY, STRING, new RulesPresidentBeanEqualty(),null));
        fields_.add( new StandardField(POSSIBLE_REVERSING, PRIM_BOOLEAN, new RulesPresidentBeanPossibleReversing(),null));
        fields_.add( new StandardField(HAS_TO_PLAY, PRIM_BOOLEAN, new RulesPresidentBeanHasToPlay(),null));
        fields_.add( new StandardField(LOOSING_IF_FINISH_BY_BEST_CARDS, PRIM_BOOLEAN, new RulesPresidentBeanLoosingIfFinishByBestCards(),null));
        fields_.add( new StandardField(SWITCH_CARDS, PRIM_BOOLEAN, new RulesPresidentBeanSwitchCards(),null));
        fields_.add( new StandardField(LOOSER_STARTS_FIRST, PRIM_BOOLEAN, new RulesPresidentBeanLooserStartsFirst(),null));
        fields_.add( new StandardField(NB_PLAYERS, PRIM_INTEGER, new RulesPresidentBeanNbPlayers(),null));
        fields_.add( new StandardField(NB_STACKS, PRIM_INTEGER, new RulesPresidentBeanNbStacks(),null));
        fields_.add( new StandardField(NB_CARDS_PER_PLAYER_MIN, PRIM_INTEGER, new RulesPresidentBeanNbCardsPerPlayerMin(),null));
        fields_.add( new StandardField(NB_CARDS_PER_PLAYER_MAX, PRIM_INTEGER, new RulesPresidentBeanNbCardsPerPlayerMax(),null));
        methods_.add(new SpecNatMethod(SAME_AMOUNT, PRIM_BOOLEAN, new RulesPresidentBeanSameAmount()));
        getStds().addEntry(TYPE_RULES_PRESIDENT_BEAN, std_);
    }

    @Override
    public void initBeans(NatConfigurationCore _conf, String _language) {
        getBeansStruct().setValue(0,beanRules(_language));
    }

    PresidentBeanStruct beanRules(String _language) {
        PresidentBeanStruct b_ = bean(new RulesPresidentBean());
        Bean bean_ = b_.getBean();
        ((RulesPresidentBean)bean_).setDataBase(getDataBaseRules());
        bean_.setLanguage(_language);
        return b_;
    }
}
