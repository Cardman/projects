package cards.belote.beans;

import cards.belote.EndBeloteGame;
import cards.belote.ResultsBelote;
import cards.consts.LineDeal;
import cards.consts.beans.TakerResult;
import code.util.CustList;
import code.util.StringList;


public final class ResultsBeloteBean extends BeloteBean {

    private long capotAttaque;

    private long pointsAttaqueSansPrime;

    private long pointsAttaqueTemporaire;

    private long pointsAttaqueDefinitif;

    private long pointsDefenseSansPrime;

    private long pointsDefenseTemporaire;

    private long pointsDefenseDefinitif;

    private final TakerResult takerResult = new TakerResult();

    private String takerNickname;

    private StringList calledPlayersList;

    private String bidString;

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
        takerResult.setWinEqualityLoose(res_.getEndBeloteGame());
        if (playGame()) {
            int preneur_=getGame().getPreneur();
            pointsAttaqueTemporaire = end_.pointsAttackWithBonus();
            pointsDefenseTemporaire = end_.pointsDefenseWithBonus();
            takerNickname = getNicknames().get(preneur_);
            calledPlayersList = new StringList();
            for (int p: getGame().getTeamsRelation().partenaires(preneur_)) {
                calledPlayersList.add(getNicknames().get(p));
            }
            bidString = toString(getBid(), res_.getRes().getGeneral(), res_.getRes().getSpecific());
            pointsAttaqueDefinitif=end_.scoreDefinitifAttaque(pointsAttaqueTemporaire, pointsDefenseTemporaire);
            pointsDefenseDefinitif= EndBeloteGame.scoreDefinitifDefense(pointsAttaqueDefinitif,pointsDefenseTemporaire);
            takerResult.setDifferenceScoreTaker(res_.getDifferenceScoreTaker());
        }
        linesDeal = LineDeal.copy(getHistory());
    }

    public TakerResult getTakerResult() {
        return takerResult;
    }

    public boolean slam() {
        return capotAttaque > 0;
    }

    public long getPointsAttaqueSansPrime() {
        return pointsAttaqueSansPrime;
    }

    public long getPointsAttaqueTemporaire() {
        return pointsAttaqueTemporaire;
    }

    public long getPointsAttaqueDefinitif() {
        return pointsAttaqueDefinitif;
    }

    public long getPointsDefenseSansPrime() {
        return pointsDefenseSansPrime;
    }

    public long getPointsDefenseTemporaire() {
        return pointsDefenseTemporaire;
    }

    public long getPointsDefenseDefinitif() {
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

    public CustList<LineDeal> getLinesDeal() {
        return linesDeal;
    }

}
