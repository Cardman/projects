package cards.belote.beans;

import code.bean.nat.SpecNatMethod;
import code.bean.nat.SpecialNatClass;
import code.bean.nat.StandardField;
import code.expressionlanguage.functionid.MethodModifier;
import code.formathtml.Configuration;
import code.util.CustList;

public final class BeloteStandardsResults extends BeloteStandards {
    private static final String TYPE_RESULTS_BELOTE_BEAN = "cards.belote.beans.ResultsBeloteBean";
    private static final String LINES_DEAL = "linesDeal";
    private static final String CALLED_PLAYERS_LIST = "calledPlayersList";
    private static final String BID_STRING = "bidString";
    private static final String TAKER_NICKNAME = "takerNickname";
    private static final String DIFFERENCE_SCORE_TAKER = "differenceScoreTaker";
    private static final String POINTS_DEFENSE_DEFINITIF = "pointsDefenseDefinitif";
    private static final String POINTS_DEFENSE_TEMPORAIRE = "pointsDefenseTemporaire";
    private static final String POINTS_DEFENSE_SANS_PRIME = "pointsDefenseSansPrime";
    private static final String POINTS_ATTAQUE_DEFINITIF = "pointsAttaqueDefinitif";
    private static final String POINTS_ATTAQUE_TEMPORAIRE = "pointsAttaqueTemporaire";
    private static final String POINTS_ATTAQUE_SANS_PRIME = "pointsAttaqueSansPrime";
    private static final String ABSOLUTE_DIFF = "absoluteDiff";
    private static final String SLAM = "slam";
    private static final String FAILED_BID = "failedBid";
    private static final String MID_BID = "midBid";
    private static final String SUCCESSFUL_BID = "successfulBid";
    private static final String LOOSE = "loose";
    private static final String EQUALITY = "equality";
    private static final String WIN = "win";

    @Override
    protected void buildAddon() {
        buildResultsBeloteBean();
        def();
    }
    private void buildResultsBeloteBean(){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass std_ = new SpecialNatClass(TYPE_RESULTS_BELOTE_BEAN, fields_, methods_, TYPE_BELOTE_BEAN);
        fields_.add( new StandardField(POINTS_ATTAQUE_SANS_PRIME, PRIM_INTEGER, false, false,new ResultsBeloteBeanPointsAttaqueSansPrime(),null));
        fields_.add( new StandardField(POINTS_ATTAQUE_TEMPORAIRE, PRIM_INTEGER, false, false,new ResultsBeloteBeanPointsAttaqueTemporaire(),null));
        fields_.add( new StandardField(POINTS_ATTAQUE_DEFINITIF, PRIM_INTEGER, false, false,new ResultsBeloteBeanPointsAttaqueDefinitif(),null));
        fields_.add( new StandardField(POINTS_DEFENSE_SANS_PRIME, PRIM_INTEGER, false, false,new ResultsBeloteBeanPointsDefenseSansPrime(),null));
        fields_.add( new StandardField(POINTS_DEFENSE_TEMPORAIRE, PRIM_INTEGER, false, false,new ResultsBeloteBeanPointsDefenseTemporaire(),null));
        fields_.add( new StandardField(POINTS_DEFENSE_DEFINITIF, PRIM_INTEGER, false, false,new ResultsBeloteBeanPointsDefenseDefinitif(),null));
        fields_.add( new StandardField(DIFFERENCE_SCORE_TAKER, PRIM_INTEGER, false, false,new ResultsBeloteBeanDifferenceScoreTaker(),null));
        fields_.add( new StandardField(BID_STRING, STRING, false, false,new ResultsBeloteBeanBidString(),null));
        fields_.add( new StandardField(TAKER_NICKNAME, STRING, false, false,new ResultsBeloteBeanTakerNickname(),null));
        fields_.add( new StandardField(CALLED_PLAYERS_LIST, TYPE_LIST, false, false,new ResultsBeloteBeanCalledPlayersList(),null));
        fields_.add( new StandardField(LINES_DEAL, TYPE_LIST, false, false,new ResultsBeloteBeanLinesDeal(),null));
        methods_.add( new SpecNatMethod(WIN, PRIM_BOOLEAN, false, MethodModifier.NORMAL,new ResultsBeloteBeanWin()));
        methods_.add( new SpecNatMethod(EQUALITY, PRIM_BOOLEAN, false, MethodModifier.NORMAL,new ResultsBeloteBeanEquality()));
        methods_.add( new SpecNatMethod(LOOSE, PRIM_BOOLEAN, false, MethodModifier.NORMAL,new ResultsBeloteBeanLoose()));
        methods_.add( new SpecNatMethod(SUCCESSFUL_BID, PRIM_BOOLEAN, false, MethodModifier.NORMAL,new ResultsBeloteBeanSuccessfulBid()));
        methods_.add( new SpecNatMethod(MID_BID, PRIM_BOOLEAN, false, MethodModifier.NORMAL,new ResultsBeloteBeanMidBid()));
        methods_.add( new SpecNatMethod(FAILED_BID, PRIM_BOOLEAN, false, MethodModifier.NORMAL,new ResultsBeloteBeanFailedBid()));
        methods_.add( new SpecNatMethod(SLAM, PRIM_BOOLEAN, false, MethodModifier.NORMAL,new ResultsBeloteBeanSlam()));
        methods_.add( new SpecNatMethod(ABSOLUTE_DIFF, PRIM_INTEGER, false, MethodModifier.NORMAL,new ResultsBeloteBeanAbsoluteDiff()));
        getStds().addEntry(TYPE_RESULTS_BELOTE_BEAN, std_);
    }

    @Override
    public void initBeans(Configuration _conf, String _language) {
        getBeansStruct().setValue(0, beanResults(_language));
    }

    BeloteBeanStruct beanResults(String _language) {
        return bean(new ResultsBeloteBean(), _language);
    }

}
