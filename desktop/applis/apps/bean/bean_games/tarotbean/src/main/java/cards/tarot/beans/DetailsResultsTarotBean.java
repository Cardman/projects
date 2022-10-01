package cards.tarot.beans;

import cards.consts.Role;
import cards.tarot.EndTarotGame;
import cards.tarot.GameTarot;
import cards.tarot.ResultsTarot;
import cards.tarot.comparators.SortedHandfuls;
import cards.tarot.comparators.SortedMiseres;
import cards.tarot.enumerations.Handfuls;
import cards.tarot.enumerations.Miseres;
import code.maths.Rate;
import code.util.*;
import code.util.core.IndexConstants;


public final class DetailsResultsTarotBean extends TarotBean {

    private static final String EMPTY_STRING = "";

    private short differenceScoreTaker;

    private short basePoints;

    private int rate;

    private String small;

    private String playerSmall = EMPTY_STRING;

    private int multipliedTmp;

    private int sumPlayers;

    private CustList<TarotSumDeclaringPlayer> linesDeclaring;

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
        GameTarot game_ = res_.getGame();
        setGame(game_);
        setNicknames(res_.getRes().getNicknames());
        setHistory(res_.getRes().getHistory());
        setUser(res_.getRes().getUser());
        String loc_ = res_.getRes().getLoc();
        setLoc(loc_);
        byte nombreJoueurs_ = getGame().getNombreDeJoueurs();
        setBid(getGame().getContrat());
        linesDeclaring = new CustList<TarotSumDeclaringPlayer>();
        orderedPlayers = new CustList<RankingPlayerVariantGame>();
        pointsPlayers = new CustList<PointsPlayerVariantGame>();
        bonuses = new CustList<BonusesPlayers>();
        playersScores = new CustList<ScoresPlayers>();
        if (!getGame().getTricks().isEmpty()) {
            if (getBid().isJouerDonne()) {
                EndTarotGame end_ = getGame().getEndTarotGame();
                end_.setupSlams();
                short doubledScoreTaker_=end_.scorePreneurPlisDouble(getBid());
                short needlyScoresTaker_=end_.scoreNecessairePreneur(getBid());
                short scorePreneurPlis_=end_.scorePreneurPlis(doubledScoreTaker_, needlyScoresTaker_);
                differenceScoreTaker=(short) (scorePreneurPlis_-needlyScoresTaker_);
                basePoints=end_.base(doubledScoreTaker_,differenceScoreTaker);
                short scoreTakerWithoutDeclaring_=end_.scorePreneurSansAnnonces(differenceScoreTaker,basePoints);
                playerSmall = res_.getPlayerSmallBound();
                small = res_.getScoreSmallBound();
                rate = getGame().getContrat().getCoefficient();
                multipliedTmp = scoreTakerWithoutDeclaring_*getBid().getCoefficient();
                boolean existeAnnonce_=false;
                for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_<nombreJoueurs_; joueur_++){
                    if (!getGame().getAnnoncesMiseres(joueur_).isEmpty()) {
                        existeAnnonce_ = true;
                    }
                    if (!getGame().getAnnoncesPoignees(joueur_).isEmpty()) {
                        existeAnnonce_ = true;
                    }
                }
                if(existeAnnonce_) {
                    int sumPlayers_ = 0;
                    CustList<SortedHandfuls> handfulsTaker_ = end_.getHandfulsPointsForTaker(scoreTakerWithoutDeclaring_);
                    CustList<SortedMiseres> miseresTaker_ = end_.getMiseresPointsForTaker();
                    for (byte p = IndexConstants.FIRST_INDEX; p<nombreJoueurs_; p++){
                        TarotSumDeclaringPlayer line_ = new TarotSumDeclaringPlayer();
                        SortedHandfuls handfulsTakerLoc_ = handfulsTaker_.get(p);
                        line_.setNickname(getNicknames().get(p));
                        line_.setStatus(toString(game_.getTeamsRelation().statutDe(p), res_.getRes().getGeneral()));
                        StringMap<Short> hands_ = new StringMap<Short>();
                        for (EntryCust<Handfuls,Short> e: handfulsTakerLoc_.entryList()) {
                            Handfuls h_ = e.getKey();
                            hands_.addEntry(toString(h_, res_.getRes().getSpecific()), e.getValue());
                        }
                        line_.setHandfuls(hands_);
                        int sum_ = 0;
                        for (Handfuls h: getGame().getAnnoncesPoignees(p)) {
                            sumPlayers_ += handfulsTakerLoc_.getVal(h);
                            sum_ += handfulsTakerLoc_.getVal(h);
                        }
                        SortedMiseres miseresTakerLoc_ = miseresTaker_.get(p);
                        StringMap<Short> mis_ = new StringMap<Short>();
                        for (EntryCust<Miseres, Short> e: miseresTakerLoc_.entryList()) {
                            Miseres m_ = e.getKey();
                            mis_.addEntry(toString(m_, res_.getRes().getSpecific()), e.getValue());
                        }
                        line_.setMiseres(mis_);
                        for (Miseres m: getGame().getAnnoncesMiseres(p)) {
                            sumPlayers_ += miseresTakerLoc_.getVal(m);
                            sum_ += miseresTakerLoc_.getVal(m);
                        }
                        line_.setSum(sum_);
                        linesDeclaring.add(line_);
                    }
                    sumPlayers = sumPlayers_;
                }
                IdMap<Role,Rate> repartitionRate_=end_.coefficientsRepartition();
                for (byte p = IndexConstants.FIRST_INDEX; p<nombreJoueurs_; p++) {
                    ScoresPlayers scoresPayer_ = new ScoresPlayers();
                    scoresPayer_.setNickname(getNicknames().get(p));
                    scoresPayer_.setRate(repartitionRate_.getVal(game_.getTeamsRelation().statutDe(p)));
                    scoresPayer_.setScore(getGame().getScores().get(p));
                    playersScores.add(scoresPayer_);
                }
                additionnalBonusesAttack = end_.additionnalBonusesAttack(getBid());
                additionnalBonusesDefense = end_.additionnalBonusesDefense();
                diffAttackDefenseBonuses = additionnalBonusesAttack-additionnalBonusesDefense;
            }else {
                EndTarotGame end_ = getGame().getEndTarotGame();
                end_.setupPlayersWonTricks();
                Shorts positions_;
                Shorts positions1_;
                Shorts positions2_;
                Shorts positions3_;
                Shorts positions4_;
                Shorts doubledScoresPlayersTricks_ = new Shorts();
                Shorts needlyScoresPlayers_ = new Shorts();
                Shorts doublesDifferencesPlayers_ = new Shorts();
                Shorts additionnalBonuses_ =new Shorts();
                Shorts coefficients_;
                boolean pasJeuMisere_=getGame().pasJeuMisere();
                if(pasJeuMisere_) {
                    for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_<nombreJoueurs_; joueur_++) {
                        doubledScoresPlayersTricks_.add(end_.scoreJoueurPlisDouble( joueur_));
                        needlyScoresPlayers_.add(end_.scoreNecessaireJoueur(joueur_));
                        doublesDifferencesPlayers_.add(EndTarotGame.differenceJoueurDouble(needlyScoresPlayers_.last(),doubledScoresPlayersTricks_.last()));
                        additionnalBonuses_.add(end_.primeSupplementaire(joueur_));
                    }
                } else {
                    for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_<nombreJoueurs_; joueur_++) {
                        doubledScoresPlayersTricks_.add(end_.scoreJoueurPlisDouble(joueur_));
                        needlyScoresPlayers_.add(end_.scoreNecessaireJoueur(joueur_));
                        doublesDifferencesPlayers_.add(EndTarotGame.differenceJoueurDoubleMisere(needlyScoresPlayers_.last(),doubledScoresPlayersTricks_.last()));
                    }
                }
                positions_=res_.getPositionsDiff();
                positions1_ = res_.getPositionsOne();
                positions2_ = res_.getPositionsTwo();
                positions3_ = res_.getPositionsThree();
                positions4_ = res_.getPositionsFour();
                coefficients_=res_.getCoefficients();
                for (byte p = IndexConstants.FIRST_INDEX; p<nombreJoueurs_; p++){
                    RankingPlayerVariantGame or_ = new RankingPlayerVariantGame();
                    or_.setNickname(getNicknames().get(p));
                    or_.setPositionDiff(positions_.get(p));
                    or_.setPositionOudlers(positions1_.get(p));
                    or_.setPositionCharacters(positions2_.get(p));
                    or_.setPositionStrengthCharacters(positions3_.get(p));
                    or_.setFinalPosition(positions4_.get(p));
                    orderedPlayers.add(or_);
                }
                for (byte p = IndexConstants.FIRST_INDEX; p<nombreJoueurs_; p++){
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
                    for (byte p = IndexConstants.FIRST_INDEX; p<nombreJoueurs_; p++){
                        TarotSumDeclaringPlayer line_ = new TarotSumDeclaringPlayer();
                        SortedHandfuls handfulsTakerLoc_ = new SortedHandfuls();
                        for (Handfuls h: end_.calculHandfulsScorePlayer(p).get(p).getKeys()) {
                            handfulsTakerLoc_.put(h, (short)0);
                        }
                        line_.setNickname(getNicknames().get(p));
                        StringMap<Short> hands_ = new StringMap<Short>();
                        for (EntryCust<Handfuls,Short> e: handfulsTakerLoc_.entryList()) {
                            Handfuls h_ = e.getKey();
                            hands_.addEntry(toString(h_, res_.getRes().getSpecific()), e.getValue());
                        }
                        line_.setHandfuls(hands_);
                        SortedMiseres miseres_ = new SortedMiseres();
                        for (Miseres m: end_.calculMiseresScorePlayer(p).get(p).getKeys()) {
                            miseres_.put(m, (short)0);
                        }
                        StringMap<Short> mis_ = new StringMap<Short>();
                        for (EntryCust<Miseres, Short> e: miseres_.entryList()) {
                            Miseres m_ = e.getKey();
                            mis_.addEntry(toString(m_, res_.getRes().getSpecific()), e.getValue());
                        }
                        line_.setMiseres(mis_);
                        line_.setSum(0);
                        linesDeclaring.add(line_);
                    }
                    for (byte p = IndexConstants.FIRST_INDEX; p<nombreJoueurs_; p++) {
                        BonusesPlayers bon_ = new BonusesPlayers();
                        bon_.setNickname(getNicknames().get(p));
                        bon_.setBonus(additionnalBonuses_.get(p));
                        bonuses.add(bon_);
                    }
                }
            }
        }
    }

    public short getDifferenceScoreTaker() {
        return differenceScoreTaker;
    }

    public short getBasePoints() {
        return basePoints;
    }

    public int getRate() {
        return rate;
    }

    public String getSmall() {
        return small;
    }

    public String getPlayerSmall() {
        return playerSmall;
    }

    public int getMultipliedTmp() {
        return multipliedTmp;
    }

    public int getSumPlayers() {
        return sumPlayers;
    }

    public CustList<TarotSumDeclaringPlayer> getLinesDeclaring() {
        return linesDeclaring;
    }

    public CustList<ScoresPlayers> getPlayersScores() {
        return playersScores;
    }

    public int getAdditionnalBonusesAttack() {
        return additionnalBonusesAttack;
    }

    public int getAdditionnalBonusesDefense() {
        return additionnalBonusesDefense;
    }

    public int getDiffAttackDefenseBonuses() {
        return diffAttackDefenseBonuses;
    }

    public CustList<RankingPlayerVariantGame> getOrderedPlayers() {
        return orderedPlayers;
    }

    public CustList<PointsPlayerVariantGame> getPointsPlayers() {
        return pointsPlayers;
    }

    public CustList<BonusesPlayers> getBonuses() {
        return bonuses;
    }

}
