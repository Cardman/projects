package cards.tarot.beans;
import cards.consts.Status;
import cards.tarot.GameTarot;
import cards.tarot.ResultsTarot;
import cards.tarot.enumerations.BonusTarot;
import cards.tarot.enumerations.Handfuls;
import cards.tarot.enumerations.Miseres;
import code.maths.Rate;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EnumMap;
import code.util.Numbers;
import code.util.StringList;
import code.util.StringMap;
import code.util.TreeMap;
import code.util.comparators.ComparatorEnum;


final class DetailsResultsTarotBean extends TarotBean {

    private static final String ZERO = "0";

    private static final String RIGHT_PAR = ")";

    private static final String MINUS = "(-";

    private static final String EMPTY_STRING = "";

    private byte joueurPetitAuBout = -1;

    private short differenceScoreTaker;

    private short basePoints;

    private int rate;

    private String small;

    private String playerSmall;

    private int multipliedTmp;

    private int sumPlayers;

    private CustList<SumDeclaringPlayer> linesDeclaring;

    private CustList<ScoresPlayers> playersScores;

    private int additionnalBonusesAttack;

    private int additionnalBonusesDefense;

    private int diffAttackDefenseBonuses;

    private CustList<RankingPlayerVariantGame> orderedPlayers;

    private CustList<PointsPlayerVariantGame> pointsPlayers;

    private CustList<BonusesPlayers> bonuses;

    @Override
    public void beforeDisplaying() {
        ResultsTarot res_ = getResults();
        setGame(res_.getGame());
        setNicknames(res_.getNicknames());
        setScores(res_.getScores());
        setUser(res_.getUser());
        String loc_ = res_.getLoc();
        setLoc(loc_);
        byte nombreJoueurs_ = getGame().getNombreDeJoueurs();
        setBid(getGame().getContrat());
        linesDeclaring = new CustList<SumDeclaringPlayer>();
        orderedPlayers = new CustList<RankingPlayerVariantGame>();
        pointsPlayers = new CustList<PointsPlayerVariantGame>();
        bonuses = new CustList<BonusesPlayers>();
        playersScores = new CustList<ScoresPlayers>();
        if (getBid().isJouerDonne()) {
            short doubledScoreTaker_=getGame().scorePreneurPlisDouble();
            short needlyScoresTaker_=getGame().scoreNecessairePreneur();
            short scorePreneurPlis_=getGame().scorePreneurPlis(doubledScoreTaker_, needlyScoresTaker_);
            differenceScoreTaker=(short) (scorePreneurPlis_-needlyScoresTaker_);
            basePoints=getGame().base(doubledScoreTaker_,differenceScoreTaker);
            short scoreTakerWithoutDeclaring_=getGame().scorePreneurSansAnnonces(differenceScoreTaker,basePoints);
            joueurPetitAuBout=getGame().joueurPetitAuBout();
            playerSmall = EMPTY_STRING;
            if (joueurPetitAuBout>-1) {
                playerSmall = getNicknames().get(joueurPetitAuBout);
                if (getGame().aPourDefenseur(joueurPetitAuBout)) {
                    small = StringList.concat(MINUS,Long.toString(BonusTarot.SMALL_BOUND.getPoints()),RIGHT_PAR);
                } else {
                    small = String.valueOf(BonusTarot.SMALL_BOUND.getPoints());
                }
            } else {
                small = ZERO;
            }
            rate = getGame().getContrat().getCoefficient();
            multipliedTmp = scoreTakerWithoutDeclaring_*getBid().getCoefficient();
            boolean existeAnnonce_=false;
            for (byte joueur_ = CustList.FIRST_INDEX;joueur_<nombreJoueurs_;joueur_++){
                if (!getGame().getAnnoncesMiseres(joueur_).isEmpty()) {
                    existeAnnonce_ = true;
                }
                if (!getGame().getAnnoncesPoignees(joueur_).isEmpty()) {
                    existeAnnonce_ = true;
                }
            }
            if(existeAnnonce_) {
                int sumPlayers_ = 0;
                CustList<TreeMap<Handfuls,Short>> handfulsTaker_ = getGame().getHandfulsPointsForTaker(scoreTakerWithoutDeclaring_);
                CustList<TreeMap<Miseres,Short>> miseresTaker_ = getGame().getMiseresPointsForTaker();
                for (byte p = CustList.FIRST_INDEX;p<nombreJoueurs_;p++){
                    SumDeclaringPlayer line_ = new SumDeclaringPlayer();
                    TreeMap<Handfuls,Short> handfulsTakerLoc_ = handfulsTaker_.get(p);
                    line_.setNickname(getNicknames().get(p));
                    line_.setStatus(toString(getGame().statutDe(p),loc_));
                    StringMap<Short> str_ = new StringMap<Short>();
                    for (EntryCust<Handfuls,Short> e: handfulsTakerLoc_.entryList()) {
                        Handfuls h_ = e.getKey();
                        str_.addEntry(toString(h_,loc_), e.getValue());
                    }
                    line_.setHandfuls(str_);
                    int sum_ = 0;
                    for (Handfuls h: getGame().getAnnoncesPoignees(p)) {
                        sumPlayers_ += handfulsTakerLoc_.getVal(h);
                        sum_ += handfulsTakerLoc_.getVal(h);
                    }
                    TreeMap<Miseres,Short> miseresTakerLoc_ = miseresTaker_.get(p);
                    line_.setMiseres(miseresTakerLoc_);
                    for (Miseres m: getGame().getAnnoncesMiseres(p)) {
                        sumPlayers_ += miseresTakerLoc_.getVal(m);
                        sum_ += miseresTakerLoc_.getVal(m);
                    }
                    line_.setSum(sum_);
                    linesDeclaring.add(line_);
                }
                sumPlayers = sumPlayers_;
            }
            EnumMap<Status,Rate> repartitionRate_=getGame().coefficientsRepartition();
            for (byte p = CustList.FIRST_INDEX;p<nombreJoueurs_;p++) {
                ScoresPlayers scoresPayer_ = new ScoresPlayers();
                scoresPayer_.setNickname(getNicknames().get(p));
                scoresPayer_.setRate(repartitionRate_.getVal(getGame().statutDe(p)));
                scoresPayer_.setScore(getGame().getScores().get(p));
                playersScores.add(scoresPayer_);
            }
            additionnalBonusesAttack = getGame().additionnalBonusesAttack();
            additionnalBonusesDefense = getGame().additionnalBonusesDefense();
            diffAttackDefenseBonuses = additionnalBonusesAttack-additionnalBonusesDefense;
        }else if (!getGame().unionPlis(true).isEmpty()) {
            Numbers<Short> positions_ = new Numbers<Short>();
            Numbers<Short> positions1_ = new Numbers<Short>();
            Numbers<Short> positions2_ = new Numbers<Short>();
            Numbers<Short> positions3_ = new Numbers<Short>();
            Numbers<Short> positions4_ = new Numbers<Short>();
            Numbers<Short> doubledScoresPlayersTricks_ = new Numbers<Short>();
            Numbers<Short> needlyScoresPlayers_ = new Numbers<Short>();
            Numbers<Short> doublesDifferencesPlayers_ = new Numbers<Short>();
            Numbers<Short> additionnalBonuses_ =new Numbers<Short>();
            Numbers<Short> coefficients_ =new Numbers<Short>();
            boolean pasJeuMisere_=getGame().pasJeuMisere();
            if(pasJeuMisere_) {
                for (byte joueur_ = CustList.FIRST_INDEX;joueur_<nombreJoueurs_;joueur_++) {
                    doubledScoresPlayersTricks_.add(getGame().scoreJoueurPlisDouble( joueur_));
                    needlyScoresPlayers_.add(getGame().scoreNecessaireJoueur(joueur_));
                    doublesDifferencesPlayers_.add(GameTarot.differenceJoueurDouble(needlyScoresPlayers_.last(),doubledScoresPlayersTricks_.last()));
                    additionnalBonuses_.add(getGame().primeSupplementaire(joueur_));
                }
                positions_=GameTarot.positionsDifference(doublesDifferencesPlayers_);
                positions1_ = getGame().changePositionsOne(positions_,pasJeuMisere_);
                positions2_ = getGame().changePositionsTwo(positions1_,pasJeuMisere_);
                positions3_ = getGame().changePositionsThree(positions2_,pasJeuMisere_);
                positions4_ = getGame().changePositionsFour(positions3_, pasJeuMisere_);
                coefficients_=getGame().coefficients(positions4_);
            } else {
                for (byte joueur_ = CustList.FIRST_INDEX;joueur_<nombreJoueurs_;joueur_++) {
                    doubledScoresPlayersTricks_.add(getGame().scoreJoueurPlisDouble(joueur_));
                    needlyScoresPlayers_.add(getGame().scoreNecessaireJoueur(joueur_));
                    doublesDifferencesPlayers_.add(GameTarot.differenceJoueurDoubleMisere(needlyScoresPlayers_.last(),doubledScoresPlayersTricks_.last()));
                }
                positions_=GameTarot.positionsDifference(doublesDifferencesPlayers_);
                positions1_ = getGame().changePositionsOne(positions_,pasJeuMisere_);
                positions2_ = getGame().changePositionsTwo(positions1_,pasJeuMisere_);
                positions3_ = getGame().changePositionsThree(positions2_,pasJeuMisere_);
                positions4_ = getGame().changePositionsFour(positions3_, pasJeuMisere_);
                coefficients_=getGame().coefficientsMisere(positions4_);
            }
            for (byte p = CustList.FIRST_INDEX;p<nombreJoueurs_;p++){
                RankingPlayerVariantGame or_ = new RankingPlayerVariantGame();
                or_.setNickname(getNicknames().get(p));
                or_.setPositionDiff(positions_.get(p));
                or_.setPositionOudlers(positions1_.get(p));
                or_.setPositionCharacters(positions2_.get(p));
                or_.setPositionStrengthCharacters(positions3_.get(p));
                or_.setFinalPosition(positions4_.get(p));
                orderedPlayers.add(or_);
            }
            for (byte p = CustList.FIRST_INDEX;p<nombreJoueurs_;p++){
                PointsPlayerVariantGame or_ = new PointsPlayerVariantGame();
                or_.setNickname(getNicknames().get(p));
                or_.setPointsTricks(new Rate(doubledScoresPlayersTricks_.get(p),2));
                or_.setMinimumPoints(needlyScoresPlayers_.get(p));
                or_.setDifferenceScore(new Rate(doublesDifferencesPlayers_.get(p),2));
                or_.setRate(coefficients_.get(p));
                or_.setScore(getGame().getScores().get(p));
                pointsPlayers.add(or_);
            }
            if (getGame().pasJeuMisere()) {
                for (byte p = CustList.FIRST_INDEX;p<nombreJoueurs_;p++){
                    SumDeclaringPlayer line_ = new SumDeclaringPlayer();
                    TreeMap<Handfuls,Short> handfulsTakerLoc_ = new TreeMap<Handfuls,Short>(new ComparatorEnum<Handfuls>());
                    for (Handfuls h: getGame().calculHandfulsScorePlayer(p).get(p).getKeys()) {
                        handfulsTakerLoc_.put(h, (short)0);
                    }
                    line_.setNickname(getNicknames().get(p));
                    StringMap<Short> str_ = new StringMap<Short>();
                    for (EntryCust<Handfuls,Short> e: handfulsTakerLoc_.entryList()) {
                        Handfuls h_ = e.getKey();
                        str_.addEntry(toString(h_,loc_), e.getValue());
                    }
                    line_.setHandfuls(str_);
                    TreeMap<Miseres,Short> miseres_ = new TreeMap<Miseres,Short>(new ComparatorEnum<Miseres>());
                    for (Miseres m: getGame().calculMiseresScorePlayer(p).get(p).getKeys()) {
                        miseres_.put(m, (short)0);
                    }
                    line_.setMiseres(miseres_);
                    line_.setSum(0);
                    linesDeclaring.add(line_);
                }
                for (byte p = CustList.FIRST_INDEX;p<nombreJoueurs_;p++) {
                    BonusesPlayers bon_ = new BonusesPlayers();
                    bon_.setNickname(getNicknames().get(p));
                    bon_.setBonus(additionnalBonuses_.get(p));
                    bonuses.add(bon_);
                }
            }
        }
    }

    byte getJoueurPetitAuBout() {
        return joueurPetitAuBout;
    }

    short getDifferenceScoreTaker() {
        return differenceScoreTaker;
    }

    short getBasePoints() {
        return basePoints;
    }

    int getRate() {
        return rate;
    }

    String getSmall() {
        return small;
    }

    String getPlayerSmall() {
        return playerSmall;
    }

    int getMultipliedTmp() {
        return multipliedTmp;
    }

    int getSumPlayers() {
        return sumPlayers;
    }

    CustList<SumDeclaringPlayer> getLinesDeclaring() {
        return linesDeclaring;
    }

    CustList<ScoresPlayers> getPlayersScores() {
        return playersScores;
    }

    int getAdditionnalBonusesAttack() {
        return additionnalBonusesAttack;
    }

    int getAdditionnalBonusesDefense() {
        return additionnalBonusesDefense;
    }

    int getDiffAttackDefenseBonuses() {
        return diffAttackDefenseBonuses;
    }

    CustList<RankingPlayerVariantGame> getOrderedPlayers() {
        return orderedPlayers;
    }

    CustList<PointsPlayerVariantGame> getPointsPlayers() {
        return pointsPlayers;
    }

    CustList<BonusesPlayers> getBonuses() {
        return bonuses;
    }

}
