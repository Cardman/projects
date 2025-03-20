package cards.belote.beans;

import cards.belote.EndBeloteGame;
import cards.belote.ResultsBelote;
import cards.consts.LineDeal;
import cards.consts.beans.TakerResult;
import code.scripts.pages.cards.MessagesBelotePage;
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

    public void build() {
        beforeDisplaying();
        if (!playGame()) {
            getBuilder().formatMessage(MessagesBelotePage.APP_BEAN, "", MessagesBelotePage.M_SCORES);
            TakerResult.buildScores(getBuilder(), getNicknames(), linesDeal);
            return;
        }
        header(MessagesBelotePage.M_CALC_TITLE);
        elt(MessagesBelotePage.M_NEED, Long.toString(pointsDefenseTemporaire));
        elt(MessagesBelotePage.M_WON_TRICK, Long.toString(pointsAttaqueTemporaire));
        header(MessagesBelotePage.M_TAKER_TITLE);
        elt(MessagesBelotePage.M_TAKER, takerNickname);
        elt(MessagesBelotePage.M_TAKER_TEAM);
        if (calledPlayersList.isEmpty()) {
            elt(MessagesBelotePage.M_NONE);
        }
        getBuilder().setIndent(1);
        for (String s : calledPlayersList) {
            getBuilder().initLine();
            getBuilder().paintMetaLabelDisk();
            getBuilder().formatMessageDir(s);
            getBuilder().feedParents();
        }
        getBuilder().setIndent(0);
        elt(MessagesBelotePage.M_BID, bidString);
        header(MessagesBelotePage.M_RESULTS_TITLE);
        getBuilder().formatMessage(MessagesBelotePage.APP_BEAN, "", MessagesBelotePage.M_WITHOUT_DECL_ATT);
        getBuilder().formatMessageDir(Long.toString(pointsAttaqueSansPrime));
        getBuilder().formatMessage(MessagesBelotePage.APP_BEAN, "", MessagesBelotePage.M_WITHOUT_DECL_DEF);
        getBuilder().formatMessageDir(Long.toString(pointsDefenseSansPrime));
        getBuilder().formatMessage(MessagesBelotePage.APP_BEAN, "", MessagesBelotePage.M_WITH_DECL_ATT);
        getBuilder().formatMessageDir(Long.toString(pointsAttaqueTemporaire));
        getBuilder().formatMessage(MessagesBelotePage.APP_BEAN, "", MessagesBelotePage.M_WITH_DECL_DEF);
        getBuilder().formatMessageDir(Long.toString(pointsDefenseTemporaire));
        getBuilder().formatMessage(MessagesBelotePage.APP_BEAN, "", MessagesBelotePage.M_FIN_ATT);
        getBuilder().formatMessageDir(Long.toString(pointsAttaqueDefinitif));
        getBuilder().formatMessage(MessagesBelotePage.APP_BEAN, "", MessagesBelotePage.M_FIN_DEF);
        getBuilder().formatMessageDir(Long.toString(pointsDefenseDefinitif));
        if (getTakerResult().win()) {
            getBuilder().formatMessage(MessagesBelotePage.APP_BEAN, "", MessagesBelotePage.M_WIN);
        }
        if (getTakerResult().equality()) {
            getBuilder().formatMessage(MessagesBelotePage.APP_BEAN, "", MessagesBelotePage.M_EQUALITY);
        }
        if (getTakerResult().loose()) {
            getBuilder().formatMessage(MessagesBelotePage.APP_BEAN, "", MessagesBelotePage.M_LOOSE);
        }
        if (getTakerResult().successfulBid()) {
            getBuilder().formatMessage(MessagesBelotePage.APP_BEAN, "", MessagesBelotePage.M_SUCCESSFUL, bidString, Long.toString(getTakerResult().absoluteDiff()));
        }
        if (getTakerResult().midBid()) {
            getBuilder().formatMessage(MessagesBelotePage.APP_BEAN, "", MessagesBelotePage.M_MID, bidString);
        }
        if (getTakerResult().failedBid()) {
            getBuilder().formatMessage(MessagesBelotePage.APP_BEAN, "", MessagesBelotePage.M_FAILED, bidString, Long.toString(getTakerResult().absoluteDiff()));
        }
        if (slam()) {
            getBuilder().formatMessage(MessagesBelotePage.APP_BEAN, "", MessagesBelotePage.M_SLAM);
        } else {
            getBuilder().formatMessage(MessagesBelotePage.APP_BEAN, "", MessagesBelotePage.M_NOSLAM);
        }
        getBuilder().formatMessage(MessagesBelotePage.APP_BEAN, "", MessagesBelotePage.M_SCORES);
        TakerResult.buildScores(getBuilder(), getNicknames(), linesDeal);
    }

    private void header(String _key) {
        getBuilder().setHeader(1);
        getBuilder().formatMessage(MessagesBelotePage.APP_BEAN,"", _key);
        getBuilder().setHeader(0);
    }
    private void elt(String _key, String... _values) {
        getBuilder().initLine();
        getBuilder().paintMetaLabelDisk();
        getBuilder().formatMessage(MessagesBelotePage.APP_BEAN,"",_key);
        for (String s:_values) {
            getBuilder().formatMessageDir(s);
        }
        getBuilder().feedParents();
    }
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
