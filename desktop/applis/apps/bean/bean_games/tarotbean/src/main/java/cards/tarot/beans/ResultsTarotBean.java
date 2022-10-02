package cards.tarot.beans;

import cards.consts.EndGameState;
import cards.consts.LineDeal;
import cards.tarot.EndTarotGame;
import cards.tarot.ResultsTarot;
import cards.tarot.enumerations.BonusTarot;
import cards.tarot.enumerations.CardTarot;
import code.util.CustList;
import code.util.Shorts;
import code.util.StringList;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;

public final class ResultsTarotBean extends TarotBean {

    private short basePoints;

    private short scoreTaker;

    private short differenceScoreTaker;

    private String taker;

    private short additionnalBonusesAttack;

    private short additionnalBonusesDefense;

    private short scoreTakerWithoutDeclaring;

    private short needlyScoresTaker;

    private short maxDoubledDifference;

    private short maxDifference;

    private EndGameState winEqualityLoose;

    private byte numberOudlersTaker;

    private StringList calledPlayers;

    private StringList calledCardsList;

    private short initialUserPosition;

    private short finalUserPosition;

    private CustList<LineDeal> linesDeal;

    @Override
    public void beforeDisplaying() {
        ResultsTarot res_ = getResults();
        setGame(res_.getGame());
        setNicknames(res_.getRes().getNicknames());
        setHistory(res_.getRes().getHistory());
        setUser(res_.getRes().getUser());
        setLoc(res_.getRes().getLoc());
        setBid(getGame().getContrat());
        short doubledScoreTaker_;
        Shorts doubledScoresPlayersTricks_ = new Shorts();
        Shorts needlyScoresPlayers_ = new Shorts();
        Shorts doublesDifferencesPlayers_ = new Shorts();
        linesDeal = new CustList<LineDeal>();
        calledCardsList = new StringList();
        calledPlayers = new StringList();
        if(!getGame().getTricks().isEmpty()) {
            byte nombreJoueurs_ = getGame().getNombreDeJoueurs();
            if(getBid().isJouerDonne()) {
                EndTarotGame end_ = getGame().getEndTarotGame();
                end_.setupSlams();
                doubledScoreTaker_=end_.scorePreneurPlisDouble(getBid());
                numberOudlersTaker=end_.nombreBoutsPreneur(getBid());
                needlyScoresTaker=end_.scoreNecessairePreneur(getBid());
                short scorePreneurPlis_=end_.scorePreneurPlis(doubledScoreTaker_, needlyScoresTaker);
                differenceScoreTaker=(short) (scorePreneurPlis_-needlyScoresTaker);
                basePoints=end_.base(doubledScoreTaker_,differenceScoreTaker);
                scoreTakerWithoutDeclaring=end_.scorePreneurSansAnnonces(differenceScoreTaker,basePoints);
                additionnalBonusesAttack = end_.additionnalBonusesAttack(getBid());
                additionnalBonusesDefense = end_.additionnalBonusesDefense();
                winEqualityLoose=res_.getEndTarotGame();
                scoreTaker = (short) (doubledScoreTaker_/2);
                taker = getNicknames().get(getGame().getPreneur());
                for (byte p: getGame().getAppele()) {
                    calledPlayers.add(getNicknames().get(p));
                }
                for (CardTarot c: getGame().getCarteAppelee()) {
                    calledCardsList.add(toString(c, res_.getRes().getSpecific()));
                }
            } else {
                EndTarotGame end_ = getGame().getEndTarotGame();
                end_.setupPlayersWonTricks();
                boolean pasJeuMisere_=getGame().pasJeuMisere();
                if(pasJeuMisere_) {
                    for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_<nombreJoueurs_; joueur_++) {
                        doubledScoresPlayersTricks_.add(end_.scoreJoueurPlisDouble( joueur_));
                        needlyScoresPlayers_.add(end_.scoreNecessaireJoueur(joueur_));
                        doublesDifferencesPlayers_.add(EndTarotGame.differenceJoueurDouble(needlyScoresPlayers_.last(),doubledScoresPlayersTricks_.last()));
                        maxDoubledDifference=(short)NumberUtil.max(maxDoubledDifference,doublesDifferencesPlayers_.last());
                    }
                    maxDifference=res_.getMaxDifference();
                    initialUserPosition=res_.getPositionsDiff().get(res_.getRes().getUser());
                } else {
                    for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_<nombreJoueurs_; joueur_++) {
                        doubledScoresPlayersTricks_.add(end_.scoreJoueurPlisDouble(joueur_));
                        needlyScoresPlayers_.add(end_.scoreNecessaireJoueur(joueur_));
                        doublesDifferencesPlayers_.add(EndTarotGame.differenceJoueurDoubleMisere(needlyScoresPlayers_.last(),doubledScoresPlayersTricks_.last()));
                        maxDoubledDifference=(short)NumberUtil.max(maxDoubledDifference,doublesDifferencesPlayers_.last());
                    }
                    maxDifference=res_.getMaxDifference();
                    initialUserPosition=res_.getPositionsDiff().get(res_.getRes().getUser());
                }
                finalUserPosition = res_.getFinalUserPosition();
            }
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

    boolean successfulSlamAttack() {
        return additionnalBonusesAttack > 0;
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

    short getBasePoints() {
        return basePoints;
    }

    public short getScoreTaker() {
        return scoreTaker;
    }

    public short getDifferenceScoreTaker() {
        return differenceScoreTaker;
    }

    public String getTaker() {
        return taker;
    }

    public short getAdditionnalBonusesAttack() {
        return additionnalBonusesAttack;
    }

    public short getAdditionnalBonusesDefense() {
        return additionnalBonusesDefense;
    }

    public short getScoreTakerWithoutDeclaring() {
        return scoreTakerWithoutDeclaring;
    }

    public short getNeedlyScoresTaker() {
        return needlyScoresTaker;
    }

    public short getMaxDoubledDifference() {
        return maxDoubledDifference;
    }

    public short getMaxDifference() {
        return maxDifference;
    }

    EndGameState getWinEqualityLoose() {
        return winEqualityLoose;
    }

    public byte getNumberOudlersTaker() {
        return numberOudlersTaker;
    }

    public StringList getCalledPlayers() {
        return calledPlayers;
    }

    public StringList getCalledCardsList() {
        return calledCardsList;
    }

    public short getInitialUserPosition() {
        return initialUserPosition;
    }

    public short getFinalUserPosition() {
        return finalUserPosition;
    }

    public CustList<LineDeal> getLinesDeal() {
        return linesDeal;
    }
}
