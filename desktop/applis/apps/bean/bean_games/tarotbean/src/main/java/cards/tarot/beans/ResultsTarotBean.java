package cards.tarot.beans;
import cards.consts.EndGameState;
import cards.tarot.EndTarotGame;
import cards.tarot.ResultsTarot;
import cards.tarot.enumerations.BonusTarot;
import cards.tarot.enumerations.CardTarot;
import code.util.CustList;
import code.util.*;
import code.util.StringList;
import code.util.core.IndexConstants;

final class ResultsTarotBean extends TarotBean {

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

    private CustList<TarotLineDeal> linesDeal;

    @Override
    public void beforeDisplaying() {
        ResultsTarot res_ = getResults();
        setGame(res_.getGame());
        setNicknames(res_.getNicknames());
        setScores(res_.getScores());
        setUser(res_.getUser());
        setLoc(res_.getLoc());
        byte nombreJoueurs_ = getGame().getNombreDeJoueurs();
        setBid(getGame().getContrat());
        short doubledScoreTaker_;
        Shorts doubledScoresPlayersTricks_ = new Shorts();
        Shorts needlyScoresPlayers_ = new Shorts();
        Shorts doublesDifferencesPlayers_ = new Shorts();
        linesDeal = new CustList<TarotLineDeal>();
        calledCardsList = new StringList();
        calledPlayers = new StringList();
        if(!getGame().getTricks().isEmpty()) {
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
                        maxDoubledDifference=(short)Math.max(maxDoubledDifference,doublesDifferencesPlayers_.last());
                    }
                    maxDifference=res_.getMaxDifference();
                    initialUserPosition=res_.getPositionsDiff().get(res_.getUser());
                } else {
                    for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_<nombreJoueurs_; joueur_++) {
                        doubledScoresPlayersTricks_.add(end_.scoreJoueurPlisDouble(joueur_));
                        needlyScoresPlayers_.add(end_.scoreNecessaireJoueur(joueur_));
                        doublesDifferencesPlayers_.add(EndTarotGame.differenceJoueurDoubleMisere(needlyScoresPlayers_.last(),doubledScoresPlayersTricks_.last()));
                        maxDoubledDifference=(short)Math.max(maxDoubledDifference,doublesDifferencesPlayers_.last());
                    }
                    maxDifference=res_.getMaxDifference();
                    initialUserPosition=res_.getPositionsDiff().get(res_.getUser());
                }
                finalUserPosition = res_.getFinalUserPosition();
            }
        }
        linesDeal = new CustList<TarotLineDeal>();
        int nbDeals_ = getScores().size();
        for(int i = IndexConstants.FIRST_INDEX; i<nbDeals_; i++) {
            TarotLineDeal l_ = new TarotLineDeal();
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

    String bidString() {
        ResultsTarot res_ = getResults();
        return toString(getBid(), res_.getRes().getSpecific());
    }

    boolean successfulDeclaredSlamAttack() {
        return additionnalBonusesAttack == BonusTarot.SLAM.getPoints();
    }

    boolean successfulNoDeclaredSlamAttack() {
        return additionnalBonusesAttack != BonusTarot.SLAM.getPoints()
                && additionnalBonusesAttack > 0;
    }

    boolean successfulSlamAttack() {
        return additionnalBonusesAttack > 0;
    }

    boolean failedSlamAttack() {
        return additionnalBonusesAttack < 0;
    }

    boolean noSlamAttack() {
        return additionnalBonusesAttack == 0;
    }

    boolean noSlamDefense() {
        return additionnalBonusesDefense == 0;
    }

    boolean slamDefense() {
        return additionnalBonusesDefense > 0;
    }

    short getBasePoints() {
        return basePoints;
    }

    short getScoreTaker() {
        return scoreTaker;
    }

    short getDifferenceScoreTaker() {
        return differenceScoreTaker;
    }

    String getTaker() {
        return taker;
    }

    short getAdditionnalBonusesAttack() {
        return additionnalBonusesAttack;
    }

    short getAdditionnalBonusesDefense() {
        return additionnalBonusesDefense;
    }

    short getScoreTakerWithoutDeclaring() {
        return scoreTakerWithoutDeclaring;
    }

    short getNeedlyScoresTaker() {
        return needlyScoresTaker;
    }

    short getMaxDoubledDifference() {
        return maxDoubledDifference;
    }

    short getMaxDifference() {
        return maxDifference;
    }

    EndGameState getWinEqualityLoose() {
        return winEqualityLoose;
    }

    byte getNumberOudlersTaker() {
        return numberOudlersTaker;
    }

    StringList getCalledPlayers() {
        return calledPlayers;
    }

    StringList getCalledCardsList() {
        return calledCardsList;
    }

    short getInitialUserPosition() {
        return initialUserPosition;
    }

    short getFinalUserPosition() {
        return finalUserPosition;
    }

    CustList<TarotLineDeal> getLinesDeal() {
        return linesDeal;
    }
}
