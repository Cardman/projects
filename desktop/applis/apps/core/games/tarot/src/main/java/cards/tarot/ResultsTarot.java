package cards.tarot;

import cards.consts.EndGameState;
import cards.consts.GameType;
import cards.consts.ResultsGame;
import cards.consts.Role;
import cards.tarot.comparators.SortedHandfuls;
import cards.tarot.comparators.SortedMiseres;
import cards.tarot.enumerations.BidTarot;
import code.maths.Rate;
import code.util.*;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;


public final class ResultsTarot {

    private final ResultsGame res = new ResultsGame();

    private GameTarot game;

    private EndGameState endTarotGame = EndGameState.EQUALLITY;

    private int finalUserPosition;

    private long maxDifference;

    private Ints positionsDiff = new Ints();
    private Ints positionsOne = new Ints();
    private Ints positionsTwo = new Ints();
    private Ints positionsThree = new Ints();
    private Ints positionsFour = new Ints();
    private Longs coefficients = new Longs();
    private String scoreSmallBound = "";
    private String playerSmallBound = "";

    public void initialize(StringList _pseudos,
            CustList<Longs> _scores) {
        res.setNicknames(_pseudos);
        Longs scoresDeal_ = new Longs();
        long basePoints_;
        long doubledScoreTaker_;
        long differenceScoreTaker_;
        IdMap<Role,Rate> repartitionRate_;
        long additionnalBonusesAttack_;
        long additionnalBonusesDefense_;
        long scoreTakerWithoutDeclaring_;
        long needlyScoresTaker_;
        Longs additionnalBonuses_ =new Longs();
        long maxDoubledDifference_=0;
        int nombreJoueurs_=game.getNombreDeJoueurs();
        BidTarot contrat_=game.getContrat();
        CustList<SortedMiseres> miseresTaker_;
        CustList<SortedHandfuls> handfulsTaker_;
        Longs doubledScoresPlayersTricks_ = new Longs();
        Longs needlyScoresPlayers_ = new Longs();
        Longs doublesDifferencesPlayers_ = new Longs();
        if (game.getTricks().isEmpty()) {
            for (int joueur_ = IndexConstants.FIRST_INDEX; joueur_<nombreJoueurs_; joueur_++) {
                scoresDeal_.add(0L);
            }
            calculateScores(_scores,scoresDeal_);
            return;
        }
        EndTarotGame end_ = game.getEndTarotGame();
        if(contrat_.isJouerDonne()) {
            end_.setupSlams();
            miseresTaker_ = end_.getMiseresPointsForTaker();
            doubledScoreTaker_=end_.scorePreneurPlisDouble(contrat_);
            needlyScoresTaker_=end_.scoreNecessairePreneur(contrat_);
            long scorePreneurPlis_=end_.scorePreneurPlis(doubledScoreTaker_, needlyScoresTaker_);
            differenceScoreTaker_= scorePreneurPlis_-needlyScoresTaker_;
            playerSmallBound= end_.joueurPetitAuBout(_pseudos);
            scoreSmallBound = end_.scoreSmallBound();
            endTarotGame = end_.getUserState(differenceScoreTaker_, res.getUser());
            basePoints_=end_.base(doubledScoreTaker_,differenceScoreTaker_);
            scoreTakerWithoutDeclaring_=end_.scorePreneurSansAnnonces(differenceScoreTaker_,basePoints_);
            handfulsTaker_ = end_.getHandfulsPointsForTaker(scoreTakerWithoutDeclaring_);
            additionnalBonusesAttack_ = end_.additionnalBonusesAttack(contrat_);
            additionnalBonusesDefense_ = end_.additionnalBonusesDefense();
            long sommeTemporaire_= EndTarotGame.temporarySum(contrat_,scoreTakerWithoutDeclaring_, miseresTaker_, handfulsTaker_, additionnalBonusesAttack_, additionnalBonusesDefense_);
            repartitionRate_=end_.coefficientsRepartition();
            game.setScores(end_.calculateScores(repartitionRate_, sommeTemporaire_, scoreTakerWithoutDeclaring_));
            scoresDeal_=game.getScores();
        } else {
            end_.setupPlayersWonTricks();
            boolean pasJeuMisere_=game.pasJeuMisere();
            if(pasJeuMisere_) {
                for (int joueur_ = IndexConstants.FIRST_INDEX; joueur_<nombreJoueurs_; joueur_++) {
                    long ptsDb_ = end_.scoreJoueurPlisDouble(joueur_);
                    doubledScoresPlayersTricks_.add(ptsDb_);
                    long ptsNeed_ = end_.scoreNecessaireJoueur(joueur_);
                    needlyScoresPlayers_.add(ptsNeed_);
                    long diffDb_ = EndTarotGame.differenceJoueurDouble(ptsNeed_, ptsDb_);
                    doublesDifferencesPlayers_.add(diffDb_);
                    maxDoubledDifference_=NumberUtil.max(maxDoubledDifference_,diffDb_);
                    additionnalBonuses_.add(end_.primeSupplementaire(joueur_));
                }
                maxDifference=end_.differenceMax(maxDoubledDifference_);
                positionsDiff=EndTarotGame.positionsDifference(doublesDifferencesPlayers_);
                positionsOne = end_.changePositionsOne(positionsDiff, true);
                positionsTwo = end_.changePositionsTwo(positionsOne, true);
                positionsThree = end_.changePositionsThree(positionsTwo, true);
                positionsFour = end_.changePositionsFour(positionsThree, true);
                coefficients=end_.coefficients(positionsFour);
                game.setScores(end_.calculerScoresJoueurs(coefficients, maxDoubledDifference_, additionnalBonuses_));
            } else {
                for (int joueur_ = IndexConstants.FIRST_INDEX; joueur_<nombreJoueurs_; joueur_++) {
                    doubledScoresPlayersTricks_.add(end_.scoreJoueurPlisDouble(joueur_));
                    needlyScoresPlayers_.add(end_.scoreNecessaireJoueur(joueur_));
                    doublesDifferencesPlayers_.add(EndTarotGame.differenceJoueurDoubleMisere(needlyScoresPlayers_.last(),doubledScoresPlayersTricks_.last()));
                    maxDoubledDifference_= NumberUtil.max(maxDoubledDifference_,doublesDifferencesPlayers_.last());
                }
                maxDifference=end_.differenceMax(maxDoubledDifference_);
                positionsDiff=EndTarotGame.positionsDifference(doublesDifferencesPlayers_);
                positionsOne = end_.changePositionsOne(positionsDiff, false);
                positionsTwo = end_.changePositionsTwo(positionsOne, false);
                positionsThree = end_.changePositionsThree(positionsTwo, false);
                positionsFour = end_.changePositionsFour(positionsThree, false);
                coefficients=end_.coefficientsMisere(positionsFour);
                game.setScores(end_.calculerScoresJoueurs(coefficients,maxDoubledDifference_));
            }
            scoresDeal_=game.getScores();
            finalUserPosition = positionsFour.get(res.getUser());
        }
        calculateScores(_scores,scoresDeal_);
    }

    private void calculateScores(CustList<Longs> _scores,Longs _scoresDeal) {
        GameType type_ = game.getType();
        long number_ = game.getNumber();
        int nbDeals_ = game.getRegles().getCommon().getNbDeals();
        calculateScores(_scores,_scoresDeal, type_, number_, nbDeals_);
    }

    void calculateScores(CustList<Longs> _scores,Longs _scoresDeal, GameType _type, long _number, int _nbDeals) {
        res.calculateScores(_scores,_scoresDeal,_type,_number,_nbDeals);
    }

    public GameTarot getGame() {
        return game;
    }

    public void setGame(GameTarot _game) {
        game = _game;
    }

    public EndGameState getEndTarotGame() {
        return endTarotGame;
    }

    public int getFinalUserPosition() {
        return finalUserPosition;
    }

    public Ints getPositionsDiff() {
        return positionsDiff;
    }

    public long getMaxDifference() {
        return maxDifference;
    }

    public Ints getPositionsOne() {
        return positionsOne;
    }

    public Ints getPositionsTwo() {
        return positionsTwo;
    }

    public Ints getPositionsThree() {
        return positionsThree;
    }

    public Ints getPositionsFour() {
        return positionsFour;
    }

    public Longs getCoefficients() {
        return coefficients;
    }

    public String getPlayerSmallBound() {
        return playerSmallBound;
    }

    public String getScoreSmallBound() {
        return scoreSmallBound;
    }

    public ResultsGame getRes() {
        return res;
    }
}
