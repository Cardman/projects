package cards.belote.beans;
import cards.belote.ResultsBelote;
import cards.consts.EndGameState;
import code.util.CustList;
import code.util.Numbers;
import code.util.StringList;


final class ResultsBeloteBean extends BeloteBean {

    private int capotAttaque;

    private int pointsAttaqueSansPrime;

    private int pointsAttaqueTemporaire;

    private int pointsAttaqueDefinitif;

    private int pointsDefenseSansPrime;

    private int pointsDefenseTemporaire;

    private int pointsDefenseDefinitif;

    private EndGameState winEqualityLoose;

    private String takerNickname;

    private StringList calledPlayersList;

    private String bidString;

    private int differenceScoreTaker;

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

    boolean win() {
        return winEqualityLoose == EndGameState.WIN;
    }

    boolean equality() {
        return winEqualityLoose == EndGameState.EQUALLITY;
    }

    boolean loose() {
        return winEqualityLoose == EndGameState.LOOSE;
    }

    boolean successfulBid() {
        return differenceScoreTaker > 0;
    }

    boolean midBid() {
        return differenceScoreTaker == 0;
    }

    boolean failedBid() {
        return differenceScoreTaker < 0;
    }

    int absoluteDiff() {
        return Math.abs(differenceScoreTaker);
    }

    boolean slam() {
        return capotAttaque > 0;
    }

    int getPointsAttaqueSansPrime() {
        return pointsAttaqueSansPrime;
    }

    int getPointsAttaqueTemporaire() {
        return pointsAttaqueTemporaire;
    }

    int getPointsAttaqueDefinitif() {
        return pointsAttaqueDefinitif;
    }

    int getPointsDefenseSansPrime() {
        return pointsDefenseSansPrime;
    }

    int getPointsDefenseTemporaire() {
        return pointsDefenseTemporaire;
    }

    int getPointsDefenseDefinitif() {
        return pointsDefenseDefinitif;
    }

    String getTakerNickname() {
        return takerNickname;
    }

    StringList getCalledPlayersList() {
        return calledPlayersList;
    }

    String getBidString() {
        return bidString;
    }

    int getDifferenceScoreTaker() {
        return differenceScoreTaker;
    }

    CustList<LineDeal> getLinesDeal() {
        return linesDeal;
    }

}
