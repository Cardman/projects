package cards.tarot.beans;

import cards.consts.LineDeal;
import cards.consts.beans.TakerResult;
import cards.tarot.EndTarotGame;
import cards.tarot.ResultsTarot;
import cards.tarot.enumerations.BonusTarot;
import cards.tarot.enumerations.CardTarot;
import code.util.CustList;
import code.util.Longs;
import code.util.StringList;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class ResultsTarotBean extends TarotBean {

    private long scoreTaker;

//    private short differenceScoreTaker;

    private String taker;

    private long additionnalBonusesAttack;

    private long additionnalBonusesDefense;

    private long scoreTakerWithoutDeclaring;

    private long needlyScoresTaker;

    private long maxDoubledDifference;

    private long maxDifference;

    private final TakerResult takerResult = new TakerResult();
//    private EndGameState winEqualityLoose;

    private int numberOudlersTaker;

    private StringList calledPlayers;

    private StringList calledCardsList;

    private int initialUserPosition;

    private int finalUserPosition;

    private CustList<LineDeal> linesDeal;

    @Override
    public void beforeDisplaying() {
        ResultsTarot res_ = getResults();
        setGame(res_.getGame());
        setNicknames(res_.getRes().getNicknames());
        setHistory(res_.getRes().getHistory());
        setBid(getGame().getContrat());
        linesDeal = new CustList<LineDeal>();
        calledCardsList = new StringList();
        calledPlayers = new StringList();
        if(!getGame().getTricks().isEmpty()) {
            if(getBid().isJouerDonne()) {
                bid(res_);
            } else {
                noBid(res_);
            }
        }
        linesDeal = LineDeal.copy(getHistory());
    }

    private void noBid(ResultsTarot _bid) {
        Longs doubledScoresPlayersTricks_ = new Longs();
        Longs needlyScoresPlayers_ = new Longs();
        Longs doublesDifferencesPlayers_ = new Longs();
        EndTarotGame end_ = getGame().getEndTarotGame();
        end_.setupPlayersWonTricks();
        boolean pasJeuMisere_=getGame().pasJeuMisere();
        int nombreJoueurs_ = getGame().getNombreDeJoueurs();
        if(pasJeuMisere_) {
            for (int joueur_ = IndexConstants.FIRST_INDEX; joueur_<nombreJoueurs_; joueur_++) {
                doubledScoresPlayersTricks_.add(end_.scoreJoueurPlisDouble( joueur_));
                needlyScoresPlayers_.add(end_.scoreNecessaireJoueur(joueur_));
                doublesDifferencesPlayers_.add(EndTarotGame.differenceJoueurDouble(needlyScoresPlayers_.last(),doubledScoresPlayersTricks_.last()));
                maxDoubledDifference=NumberUtil.max(maxDoubledDifference,doublesDifferencesPlayers_.last());
            }
        } else {
            for (int joueur_ = IndexConstants.FIRST_INDEX; joueur_<nombreJoueurs_; joueur_++) {
                doubledScoresPlayersTricks_.add(end_.scoreJoueurPlisDouble(joueur_));
                needlyScoresPlayers_.add(end_.scoreNecessaireJoueur(joueur_));
                doublesDifferencesPlayers_.add(EndTarotGame.differenceJoueurDoubleMisere(needlyScoresPlayers_.last(),doubledScoresPlayersTricks_.last()));
                maxDoubledDifference=NumberUtil.max(maxDoubledDifference,doublesDifferencesPlayers_.last());
            }
        }
        maxDifference= _bid.getMaxDifference();
        initialUserPosition= _bid.getPositionsDiff().get(_bid.getRes().getUser());
        finalUserPosition = _bid.getFinalUserPosition();
    }

    private void bid(ResultsTarot _res) {
        long doubledScoreTaker_;
        EndTarotGame end_ = getGame().getEndTarotGame();
        end_.setupSlams();
        doubledScoreTaker_=end_.scorePreneurPlisDouble(getBid());
        numberOudlersTaker=end_.nombreBoutsPreneur(getBid());
        needlyScoresTaker=end_.scoreNecessairePreneur(getBid());
        long scorePreneurPlis_=end_.scorePreneurPlis(doubledScoreTaker_, needlyScoresTaker);
        takerResult.setDifferenceScoreTaker(scorePreneurPlis_ - needlyScoresTaker);
        long diff_ = takerResult.getDifferenceScoreTaker();
        scoreTakerWithoutDeclaring=end_.scorePreneurSansAnnonces(diff_,end_.base(doubledScoreTaker_, diff_));
        additionnalBonusesAttack = end_.additionnalBonusesAttack(getBid());
        additionnalBonusesDefense = end_.additionnalBonusesDefense();
        takerResult.setWinEqualityLoose(_res.getEndTarotGame());
        scoreTaker = doubledScoreTaker_/2;
        taker = getNicknames().get(getGame().getPreneur());
        for (int p: getGame().getAppele()) {
            calledPlayers.add(getNicknames().get(p));
        }
        for (CardTarot c: getGame().getCarteAppelee()) {
            calledCardsList.add(StringUtil.nullToEmpty(toString(c, _res.getRes().getGeneralCards())));
        }
    }

    public TakerResult getTakerResult() {
        return takerResult;
    }

    public String bidString() {
        ResultsTarot res_ = getResults();
        return toString(getBid(), res_.getRes().getSpecific());
    }

    public boolean successfulDeclaredSlamAttack() {
        return additionnalBonusesAttack == BonusTarot.SLAM.getPoints();
    }

    public boolean successfulNoDeclaredSlamAttack() {
        return additionnalBonusesAttack != BonusTarot.SLAM.getPoints()
                && additionnalBonusesAttack > 0;
    }

    public boolean failedSlamAttack() {
        return additionnalBonusesAttack < 0;
    }

    public boolean noSlamAttack() {
        return additionnalBonusesAttack == 0;
    }

    public boolean noSlamDefense() {
        return additionnalBonusesDefense == 0;
    }

    public boolean slamDefense() {
        return additionnalBonusesDefense > 0;
    }

    public long getScoreTaker() {
        return scoreTaker;
    }

    public String getTaker() {
        return taker;
    }

    public long getAdditionnalBonusesAttack() {
        return additionnalBonusesAttack;
    }

    public long getAdditionnalBonusesDefense() {
        return additionnalBonusesDefense;
    }

    public long getScoreTakerWithoutDeclaring() {
        return scoreTakerWithoutDeclaring;
    }

    public long getNeedlyScoresTaker() {
        return needlyScoresTaker;
    }

    public long getMaxDoubledDifference() {
        return maxDoubledDifference;
    }

    public long getMaxDifference() {
        return maxDifference;
    }

    public int getNumberOudlersTaker() {
        return numberOudlersTaker;
    }

    public StringList getCalledPlayers() {
        return calledPlayers;
    }

    public StringList getCalledCardsList() {
        return calledCardsList;
    }

    public int getInitialUserPosition() {
        return initialUserPosition;
    }

    public int getFinalUserPosition() {
        return finalUserPosition;
    }

    public CustList<LineDeal> getLinesDeal() {
        return linesDeal;
    }
}
