package cards.belote.beans;
import cards.belote.EndBeloteGame;
import cards.belote.ResultsBelote;
import cards.consts.EndGameState;
import code.util.CustList;
import code.util.*;
import code.util.StringList;
import code.util.core.IndexConstants;


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

    private CustList<BeloteLineDeal> linesDeal;

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
        EndBeloteGame end_ = getGame().getEndBeloteGame();
        capotAttaque=end_.valeurCapot();
        pointsAttaqueSansPrime=end_.pointsAttaqueSansPrime();
        pointsAttaqueTemporaire=pointsAttaqueSansPrime;
        pointsDefenseSansPrime=end_.pointsDefenseSansPrime();
        pointsDefenseTemporaire=pointsDefenseSansPrime;
        winEqualityLoose = res_.getEndBeloteGame();
        if (playGame()) {
            byte preneur_=getGame().getPreneur();
            pointsAttaqueTemporaire = end_.pointsAttackWithBonus();
            pointsDefenseTemporaire = end_.pointsDefenseWithBonus();
            takerNickname = getNicknames().get(preneur_);
            calledPlayersList = new StringList();
            for (byte p: getGame().getTeamsRelation().partenaires(preneur_)) {
                calledPlayersList.add(getNicknames().get(p));
            }
            bidString = toString(getBid(), res_.getRes().getGeneral(), res_.getRes().getSpecific());
            pointsAttaqueDefinitif=end_.scoreDefinitifAttaque(pointsAttaqueTemporaire, pointsDefenseTemporaire);
            pointsDefenseDefinitif=end_.scoreDefinitifDefense(pointsAttaqueDefinitif,pointsDefenseTemporaire);
            differenceScoreTaker = res_.getDifferenceScoreTaker();
        }
        linesDeal = new CustList<BeloteLineDeal>();
        int nbDeals_ = getScores().size();
        for(int i = IndexConstants.FIRST_INDEX; i<nbDeals_; i++) {
            BeloteLineDeal l_ = new BeloteLineDeal();
            l_.setNumber(i);
            Longs scores_ = new Longs();
            for(byte joueur_ = IndexConstants.FIRST_INDEX; joueur_<nombreJoueurs_; joueur_++) {
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

    CustList<BeloteLineDeal> getLinesDeal() {
        return linesDeal;
    }

}
