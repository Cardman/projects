package cards.tarot.beans;

import code.bean.nat.SpecNatMethod;
import code.bean.nat.SpecialNatClass;
import code.bean.nat.StandardField;
import code.bean.nat.analyze.NatConfigurationCore;
import code.bean.nat.*;
import code.util.CustList;

public final class TarotStandardsDetailResults extends TarotStandards {
    private static final String TYPE_DETAILS_RESULTS_TAROT_BEAN = "cards.tarot.beans.DetailsResultsTarotBean";
    private static final String BONUSES = "bonuses";
    private static final String POINTS_PLAYERS = "pointsPlayers";
    private static final String ORDERED_PLAYERS = "orderedPlayers";
    private static final String PLAYERS_SCORES = "playersScores";
    private static final String LINES_DECLARING = "linesDeclaring";
    private static final String SMALL = "small";
    private static final String PLAYER_SMALL = "playerSmall";
    private static final String DIFFERENCE_SCORE_TAKER = "differenceScoreTaker";
    private static final String BASE_POINTS = "basePoints";
    private static final String DIFF_ATTACK_DEFENSE_BONUSES = "diffAttackDefenseBonuses";
    private static final String ADDITIONNAL_BONUSES_DEFENSE = "additionnalBonusesDefense";
    private static final String ADDITIONNAL_BONUSES_ATTACK = "additionnalBonusesAttack";
    private static final String SUM_PLAYERS = "sumPlayers";
    private static final String MULTIPLIED_TMP = "multipliedTmp";
    private static final String RATE = "rate";

    @Override
    protected void buildAddon() {
        buildDetailsResultsTarotBean();
        def();
    }

    @Override
    public void initBeans(NatConfigurationCore _conf, String _language) {
        getBeansStruct().setValue(0,beanDetailResults(_language));
    }

    private void buildDetailsResultsTarotBean(){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass std_ = new SpecialNatClass(fields_, methods_, TYPE_TAROT_BEAN);
        fields_.add( new StandardField(RATE, PRIM_INTEGER, new DetailsResultsTarotBeanRate(),null));
        fields_.add( new StandardField(MULTIPLIED_TMP, PRIM_INTEGER, new DetailsResultsTarotBeanMultipliedTmp(),null));
        fields_.add( new StandardField(SUM_PLAYERS, PRIM_INTEGER, new DetailsResultsTarotBeanSumPlayers(),null));
        fields_.add( new StandardField(ADDITIONNAL_BONUSES_ATTACK, PRIM_INTEGER, new DetailsResultsTarotBeanAdditionnalBonusesAttack(),null));
        fields_.add( new StandardField(ADDITIONNAL_BONUSES_DEFENSE, PRIM_INTEGER, new DetailsResultsTarotBeanAdditionnalBonusesDefense(),null));
        fields_.add( new StandardField(DIFF_ATTACK_DEFENSE_BONUSES, PRIM_INTEGER, new DetailsResultsTarotBeanDiffAttackDefenseBonuses(),null));
        fields_.add( new StandardField(BASE_POINTS, PRIM_INTEGER, new DetailsResultsTarotBeanBasePoints(),null));
        fields_.add( new StandardField(DIFFERENCE_SCORE_TAKER, PRIM_INTEGER, new DetailsResultsTarotBeanDifferenceScoreTaker(),null));
        fields_.add( new StandardField(PLAYER_SMALL, STRING, new DetailsResultsTarotBeanPlayerSmall(),null));
        fields_.add( new StandardField(SMALL, STRING, new DetailsResultsTarotBeanSmall(),null));
        fields_.add( new StandardField(LINES_DECLARING, TYPE_LIST, new DetailsResultsTarotBeanLinesDeclaring(),null));
        fields_.add( new StandardField(PLAYERS_SCORES, TYPE_LIST, new DetailsResultsTarotBeanPlayersScores(),null));
        fields_.add( new StandardField(ORDERED_PLAYERS, TYPE_LIST, new DetailsResultsTarotBeanOrderedPlayers(),null));
        fields_.add( new StandardField(POINTS_PLAYERS, TYPE_LIST, new DetailsResultsTarotBeanPointsPlayers(),null));
        fields_.add( new StandardField(BONUSES, TYPE_LIST, new DetailsResultsTarotBeanBonuses(),null));
        getStds().addEntry(TYPE_DETAILS_RESULTS_TAROT_BEAN, std_);
    }

    NaSt beanDetailResults(String _language) {
        return bean(new DetailsResultsTarotBean(), _language);
    }
}
