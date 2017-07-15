package cards.belote.beans;
import code.bean.Accessible;
import code.util.CustList;
import code.util.Numbers;
import code.util.StringList;
import cards.belote.ResultsBelote;
import cards.consts.EndGameState;


public final class ResultsBeloteBean extends BeloteBean {

    private int capotAttaque;

    @Accessible
    private int pointsAttaqueSansPrime;

    @Accessible
    private int pointsAttaqueTemporaire;

    @Accessible
    private int pointsAttaqueDefinitif;

    @Accessible
    private int pointsDefenseSansPrime;

    @Accessible
    private int pointsDefenseTemporaire;

    @Accessible
    private int pointsDefenseDefinitif;

    private EndGameState winEqualityLoose;

    @Accessible
    private String takerNickname;

    @Accessible
    private StringList calledPlayersList;

    @Accessible
    private String bidString;

    @Accessible
    private int differenceScoreTaker;

    @Accessible
    private CustList<LineDeal> linesDeal;

    @Override
    public void beforeDisplaying() {
        ResultsBelote res_ = getResults();
        setGame(res_.getGame());
        setNicknames(res_.getNicknames());
        setScores(res_.getScores());
        setUser(res_.getUser());
        setLoc(res_.getLoc());
        byte nombreJoueurs_ = getGame().getNombreDeJoueurs();
        setBid(getGame().getContrat());
        capotAttaque=getGame().valeurCapot();
        pointsAttaqueSansPrime=getGame().pointsAttaqueSansPrime();
        pointsAttaqueTemporaire=pointsAttaqueSansPrime;
        pointsDefenseSansPrime=getGame().pointsDefenseSansPrime();
        pointsDefenseTemporaire=pointsDefenseSansPrime;
        if (playGame()) {
            byte preneur_=getGame().getPreneur();
            pointsAttaqueTemporaire = getGame().pointsAttackWithBonus();
            pointsDefenseTemporaire = getGame().pointsDefenseWithBonus();
            takerNickname = getNicknames().get(preneur_);
            calledPlayersList = new StringList();
            for (byte p: getGame().partenaires(preneur_)) {
                calledPlayersList.add(getNicknames().get(p));
            }
            bidString = getBid().toString(getLoc());
            pointsAttaqueDefinitif=getGame().scoreDefinitifAttaque(pointsAttaqueTemporaire, pointsDefenseTemporaire);
            pointsDefenseDefinitif=getGame().scoreDefinitifDefense(pointsAttaqueDefinitif,pointsDefenseTemporaire);
            winEqualityLoose = getGame().getUserState(res_.getUser());
            differenceScoreTaker = getGame().getDiffAttackPointsMinusDefensePoints();
        }
        linesDeal = new CustList<LineDeal>();
        int nbDeals_ = getScores().size();
        for(int i=CustList.FIRST_INDEX;i<nbDeals_;i++) {
            LineDeal l_ = new LineDeal();
            l_.setNumber(i);
            Numbers<Long> scores_ = new Numbers<Long>();
            for(byte joueur_=CustList.FIRST_INDEX;joueur_<nombreJoueurs_;joueur_++) {
                scores_.add(getScores().get(i).get(joueur_));
            }
            l_.setScores(scores_);
            linesDeal.add(l_);
        }
    }

    @Accessible
    private boolean win() {
        return winEqualityLoose == EndGameState.WIN;
    }

    @Accessible
    private boolean equality() {
        return winEqualityLoose == EndGameState.EQUALLITY;
    }

    @Accessible
    private boolean loose() {
        return winEqualityLoose == EndGameState.LOOSE;
    }

    @Accessible
    private boolean successfulBid() {
        return differenceScoreTaker > 0;
    }

    @Accessible
    private boolean midBid() {
        return differenceScoreTaker == 0;
    }

    @Accessible
    private boolean failedBid() {
        return differenceScoreTaker < 0;
    }

    @Accessible
    private int absoluteDiff() {
        return Math.abs(differenceScoreTaker);
    }

    @Accessible
    private boolean slam() {
        return capotAttaque > 0;
    }
}
