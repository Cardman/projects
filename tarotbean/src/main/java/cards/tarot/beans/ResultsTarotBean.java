package cards.tarot.beans;
import cards.consts.EndGameState;
import cards.tarot.GameTarot;
import cards.tarot.ResultsTarot;
import cards.tarot.enumerations.BonusTarot;
import cards.tarot.enumerations.CardTarot;
import code.util.CustList;
import code.util.Numbers;
import code.util.StringList;

final class ResultsTarotBean extends TarotBean {

    private static final String EMPTY_STRING = "";

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

    private String aloneTrumpAcePlayer;

    private short initialUserPosition;

    private short finalUserPosition;

    private CustList<LineDeal> linesDeal;

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
        short doubledScoreTaker_=0;
        Numbers<Short> positions_ = new Numbers<Short>();
        Numbers<Short> positions1_ = new Numbers<Short>();
        Numbers<Short> positions2_ = new Numbers<Short>();
        Numbers<Short> positions3_ = new Numbers<Short>();
        Numbers<Short> positions4_ = new Numbers<Short>();
        Numbers<Short> doubledScoresPlayersTricks_ = new Numbers<Short>();
        Numbers<Short> needlyScoresPlayers_ = new Numbers<Short>();
        Numbers<Short> doublesDifferencesPlayers_ = new Numbers<Short>();
        linesDeal = new CustList<LineDeal>();
        calledCardsList = new StringList();
        calledPlayers = new StringList();
        if(getBid().isJouerDonne()) {
            doubledScoreTaker_=getGame().scorePreneurPlisDouble();
            numberOudlersTaker=getGame().nombreBoutsPreneur();
            needlyScoresTaker=getGame().scoreNecessairePreneur();
            short scorePreneurPlis_=getGame().scorePreneurPlis(doubledScoreTaker_, needlyScoresTaker);
            differenceScoreTaker=(short) (scorePreneurPlis_-needlyScoresTaker);
            basePoints=getGame().base(doubledScoreTaker_,differenceScoreTaker);
            scoreTakerWithoutDeclaring=getGame().scorePreneurSansAnnonces(differenceScoreTaker,basePoints);
            additionnalBonusesAttack = getGame().additionnalBonusesAttack();
            additionnalBonusesDefense = getGame().additionnalBonusesDefense();
            winEqualityLoose=getGame().getUserState(scoreTakerWithoutDeclaring,res_.getUser());
            scoreTaker = (short) (doubledScoreTaker_/2);
            taker = getNicknames().get(getGame().getPreneur());
            for (byte p: getGame().getAppele()) {
                calledPlayers.add(getNicknames().get(p));
            }
            for (CardTarot c: getGame().getCarteAppelee()) {
                calledCardsList.add(c.toString(getLoc()));
            }
        } else if(!getGame().unionPlis(true).isEmpty()) {
            boolean pasJeuMisere_=getGame().pasJeuMisere();
            if(pasJeuMisere_) {
                for (byte joueur_ = CustList.FIRST_INDEX;joueur_<nombreJoueurs_;joueur_++) {
                    doubledScoresPlayersTricks_.add(getGame().scoreJoueurPlisDouble( joueur_));
                    needlyScoresPlayers_.add(getGame().scoreNecessaireJoueur(joueur_));
                    doublesDifferencesPlayers_.add(GameTarot.differenceJoueurDouble(needlyScoresPlayers_.last(),doubledScoresPlayersTricks_.last()));
                    maxDoubledDifference=(short)Math.max(maxDoubledDifference,doublesDifferencesPlayers_.last());
                }
                maxDifference=getGame().differenceMax(maxDoubledDifference);
                positions_=GameTarot.positionsDifference(doublesDifferencesPlayers_);
                initialUserPosition=positions_.get(res_.getUser());
                positions1_ = getGame().changePositionsOne(positions_,pasJeuMisere_);
                positions2_ = getGame().changePositionsTwo(positions1_,pasJeuMisere_);
                positions3_ = getGame().changePositionsThree(positions2_,pasJeuMisere_);
                positions4_ = getGame().changePositionsFour(positions3_, pasJeuMisere_);
            } else {
                for (byte joueur_ = CustList.FIRST_INDEX;joueur_<nombreJoueurs_;joueur_++) {
                    doubledScoresPlayersTricks_.add(getGame().scoreJoueurPlisDouble(joueur_));
                    needlyScoresPlayers_.add(getGame().scoreNecessaireJoueur(joueur_));
                    doublesDifferencesPlayers_.add(GameTarot.differenceJoueurDoubleMisere(needlyScoresPlayers_.last(),doubledScoresPlayersTricks_.last()));
                    maxDoubledDifference=(short)Math.max(maxDoubledDifference,doublesDifferencesPlayers_.last());
                }
                maxDifference=getGame().differenceMax(maxDoubledDifference);
                positions_=GameTarot.positionsDifference(doublesDifferencesPlayers_);
                initialUserPosition=positions_.get(res_.getUser());
                positions1_ = getGame().changePositionsOne(positions_,pasJeuMisere_);
                positions2_ = getGame().changePositionsTwo(positions1_,pasJeuMisere_);
                positions3_ = getGame().changePositionsThree(positions2_,pasJeuMisere_);
                positions4_ = getGame().changePositionsFour(positions3_, pasJeuMisere_);
            }
            finalUserPosition = positions4_.get(res_.getUser());
        } else {
            byte ind_ = getGame().joueurAyantPetitSec();
            if (ind_ != CustList.INDEX_NOT_FOUND_ELT) {
                aloneTrumpAcePlayer= getNicknames().get(ind_);
            } else {
                aloneTrumpAcePlayer = EMPTY_STRING;
            }
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

    String bidString() {
        return getBid().toString(getLoc());
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

    String getAloneTrumpAcePlayer() {
        return aloneTrumpAcePlayer;
    }

    short getInitialUserPosition() {
        return initialUserPosition;
    }

    short getFinalUserPosition() {
        return finalUserPosition;
    }

    CustList<LineDeal> getLinesDeal() {
        return linesDeal;
    }
}
