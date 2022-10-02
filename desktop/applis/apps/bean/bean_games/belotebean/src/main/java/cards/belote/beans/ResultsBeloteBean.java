package cards.belote.beans;

import cards.belote.EndBeloteGame;
import cards.belote.ResultsBelote;
import cards.consts.EndGameState;
import cards.consts.LineDeal;
import code.util.CustList;
import code.util.StringList;
import code.util.core.NumberUtil;


public final class ResultsBeloteBean extends BeloteBean {

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
        setNicknames(res_.getRes().getNicknames());
        setHistory(res_.getRes().getHistory());
        setBid(getGame().getBid());
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
        linesDeal = LineDeal.copy(getHistory());
    }

    public boolean win() {
        return winEqualityLoose == EndGameState.WIN;
    }

    public boolean equality() {
        return winEqualityLoose == EndGameState.EQUALLITY;
    }

    public boolean loose() {
        return winEqualityLoose == EndGameState.LOOSE;
    }

    public boolean successfulBid() {
        return differenceScoreTaker > 0;
    }

    public boolean midBid() {
        return differenceScoreTaker == 0;
    }

    public boolean failedBid() {
        return differenceScoreTaker < 0;
    }

    public int absoluteDiff() {
        return NumberUtil.abs(differenceScoreTaker);
    }

    public boolean slam() {
        return capotAttaque > 0;
    }

    public int getPointsAttaqueSansPrime() {
        return pointsAttaqueSansPrime;
    }

    public int getPointsAttaqueTemporaire() {
        return pointsAttaqueTemporaire;
    }

    public int getPointsAttaqueDefinitif() {
        return pointsAttaqueDefinitif;
    }

    public int getPointsDefenseSansPrime() {
        return pointsDefenseSansPrime;
    }

    public int getPointsDefenseTemporaire() {
        return pointsDefenseTemporaire;
    }

    public int getPointsDefenseDefinitif() {
        return pointsDefenseDefinitif;
    }

    public String getTakerNickname() {
        return takerNickname;
    }

    public StringList getCalledPlayersList() {
        return calledPlayersList;
    }

    public String getBidString() {
        return bidString;
    }

    public int getDifferenceScoreTaker() {
        return differenceScoreTaker;
    }

    public CustList<LineDeal> getLinesDeal() {
        return linesDeal;
    }

}
