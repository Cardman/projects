package cards.tarot.beans;

import code.bean.nat.SpecNatMethod;
import code.bean.nat.SpecialNatClass;
import code.bean.nat.StandardField;
import code.bean.nat.analyze.NatConfigurationCore;
import code.bean.nat.*;
import code.util.CustList;

public final class TarotStandardsResults extends TarotStandards {
    private static final String TYPE_RESULTS_TAROT_BEAN = "cards.tarot.beans.ResultsTarotBean";
    private static final String DIFFERENCE_SCORE_TAKER = "differenceScoreTaker";
    private static final String ADDITIONNAL_BONUSES_ATTACK = "additionnalBonusesAttack";
    private static final String ADDITIONNAL_BONUSES_DEFENSE = "additionnalBonusesDefense";
    private static final String LINES_DEAL = "linesDeal";
    private static final String CALLED_CARDS_LIST = "calledCardsList";
    private static final String CALLED_PLAYERS = "calledPlayers";
    private static final String ALONE_TRUMP_ACE_PLAYER = "aloneTrumpAcePlayer";
    private static final String TAKER = "taker";
    private static final String FINAL_USER_POSITION = "finalUserPosition";
    private static final String INITIAL_USER_POSITION = "initialUserPosition";
    private static final String MAX_DIFFERENCE = "maxDifference";
    private static final String MAX_DOUBLED_DIFFERENCE = "maxDoubledDifference";
    private static final String SCORE_TAKER_WITHOUT_DECLARING = "scoreTakerWithoutDeclaring";
    private static final String SCORE_TAKER = "scoreTaker";
    private static final String NEEDLY_SCORES_TAKER = "needlyScoresTaker";
    private static final String NUMBER_OUDLERS_TAKER = "numberOudlersTaker";
    private static final String BID_STRING = "bidString";
    private static final String ABSOLUTE_DIFF = "absoluteDiff";
    private static final String NO_SLAM_DEFENSE = "noSlamDefense";
    private static final String SLAM_DEFENSE = "slamDefense";
    private static final String NO_SLAM_ATTACK = "noSlamAttack";
    private static final String FAILED_SLAM_ATTACK = "failedSlamAttack";
    private static final String SUCCESSFUL_NO_DECLARED_SLAM_ATTACK = "successfulNoDeclaredSlamAttack";
    private static final String SUCCESSFUL_DECLARED_SLAM_ATTACK = "successfulDeclaredSlamAttack";
    private static final String FAILED_BID = "failedBid";
    private static final String MID_BID = "midBid";
    private static final String SUCCESSFUL_BID = "successfulBid";
    private static final String LOOSE = "loose";
    private static final String EQUALITY = "equality";
    private static final String WIN = "win";
    @Override
    protected void buildAddon() {
        buildResultsTarotBean();
        def();
    }

    private void buildResultsTarotBean(){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass std_ = new SpecialNatClass(fields_, methods_, TYPE_TAROT_BEAN);
        fields_.add( new StandardField(NUMBER_OUDLERS_TAKER, PRIM_INTEGER, new ResultsTarotBeanNumberOudlersTaker(),null));
        fields_.add( new StandardField(NEEDLY_SCORES_TAKER, PRIM_INTEGER, new ResultsTarotBeanNeedlyScoresTaker(),null));
        fields_.add( new StandardField(SCORE_TAKER, PRIM_INTEGER, new ResultsTarotBeanScoreTaker(),null));
        fields_.add( new StandardField(DIFFERENCE_SCORE_TAKER, PRIM_INTEGER, new ResultsTarotBeanDifferenceScoreTaker(),null));
        fields_.add( new StandardField(ADDITIONNAL_BONUSES_ATTACK, PRIM_INTEGER, new ResultsTarotBeanAdditionnalBonusesAttack(),null));
        fields_.add( new StandardField(ADDITIONNAL_BONUSES_DEFENSE, PRIM_INTEGER, new ResultsTarotBeanAdditionnalBonusesDefense(),null));
        fields_.add( new StandardField(SCORE_TAKER_WITHOUT_DECLARING, PRIM_INTEGER, new ResultsTarotBeanScoreTakerWithoutDeclaring(),null));
        fields_.add( new StandardField(MAX_DOUBLED_DIFFERENCE, PRIM_INTEGER, new ResultsTarotBeanMaxDoubledDifference(),null));
        fields_.add( new StandardField(MAX_DIFFERENCE, PRIM_INTEGER, new ResultsTarotBeanMaxDifference(),null));
        fields_.add( new StandardField(INITIAL_USER_POSITION, PRIM_INTEGER, new ResultsTarotBeanInitialUserPosition(),null));
        fields_.add( new StandardField(FINAL_USER_POSITION, PRIM_INTEGER, new ResultsTarotBeanFinalUserPosition(),null));
        fields_.add( new StandardField(TAKER, STRING, new ResultsTarotBeanTaker(),null));
        fields_.add( new StandardField(ALONE_TRUMP_ACE_PLAYER, STRING, new ResultsTarotBeanAloneTrumpAcePlayer(),null));
        fields_.add( new StandardField(CALLED_PLAYERS, TYPE_LIST, new ResultsTarotBeanCalledPlayers(),null));
        fields_.add( new StandardField(CALLED_CARDS_LIST, TYPE_LIST, new ResultsTarotBeanCalledCardsList(),null));
        fields_.add( new StandardField(LINES_DEAL, TYPE_LIST, new ResultsTarotBeanLinesDeal(),null));
        methods_.add( new SpecNatMethod(WIN, PRIM_BOOLEAN, new ResultsTarotBeanWin()));
        methods_.add( new SpecNatMethod(EQUALITY, PRIM_BOOLEAN, new ResultsTarotBeanEquality()));
        methods_.add( new SpecNatMethod(LOOSE, PRIM_BOOLEAN, new ResultsTarotBeanLoose()));
        methods_.add( new SpecNatMethod(SUCCESSFUL_BID, PRIM_BOOLEAN, new ResultsTarotBeanSuccessfulBid()));
        methods_.add( new SpecNatMethod(MID_BID, PRIM_BOOLEAN, new ResultsTarotBeanMidBid()));
        methods_.add( new SpecNatMethod(FAILED_BID, PRIM_BOOLEAN, new ResultsTarotBeanFailedBid()));
        methods_.add( new SpecNatMethod(SUCCESSFUL_DECLARED_SLAM_ATTACK, PRIM_BOOLEAN, new ResultsTarotBeanSuccessfulDeclaredSlamAttack()));
        methods_.add( new SpecNatMethod(SUCCESSFUL_NO_DECLARED_SLAM_ATTACK, PRIM_BOOLEAN, new ResultsTarotBeanSuccessfulNoDeclaredSlamAttack()));
        methods_.add( new SpecNatMethod(FAILED_SLAM_ATTACK, PRIM_BOOLEAN, new ResultsTarotBeanFailedSlamAttack()));
        methods_.add( new SpecNatMethod(NO_SLAM_ATTACK, PRIM_BOOLEAN, new ResultsTarotBeanNoSlamAttack()));
        methods_.add( new SpecNatMethod(SLAM_DEFENSE, PRIM_BOOLEAN, new ResultsTarotBeanSlamDefense()));
        methods_.add( new SpecNatMethod(NO_SLAM_DEFENSE, PRIM_BOOLEAN, new ResultsTarotBeanNoSlamDefense()));
        methods_.add( new SpecNatMethod(ABSOLUTE_DIFF, PRIM_INTEGER, new ResultsTarotBeanAbsoluteDiff()));
        methods_.add( new SpecNatMethod(BID_STRING, STRING, new ResultsTarotBeanBidString()));
        getStds().addEntry(TYPE_RESULTS_TAROT_BEAN, std_);
    }

    @Override
    public void initBeans(NatConfigurationCore _conf, String _language) {
        getBeansStruct().setValue(0,beanResults(_language));
    }

    NaSt beanResults(String _language) {
        return bean(new ResultsTarotBean(), _language);
    }
}
