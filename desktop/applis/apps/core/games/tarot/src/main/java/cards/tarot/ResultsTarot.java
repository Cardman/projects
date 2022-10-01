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

    private short finalUserPosition;

    private short maxDifference;

    private Shorts positionsDiff = new Shorts();
    private Shorts positionsOne = new Shorts();
    private Shorts positionsTwo = new Shorts();
    private Shorts positionsThree = new Shorts();
    private Shorts positionsFour = new Shorts();
    private Shorts coefficients = new Shorts();
    private String scoreSmallBound = "";
    private String playerSmallBound = "";

    public void initialize(StringList _pseudos,
            CustList<Longs> _scores) {
        res.setNicknames(_pseudos);
        Shorts scoresDeal_ = new Shorts();
        short basePoints_;
        short doubledScoreTaker_;
        short differenceScoreTaker_;
        IdMap<Role,Rate> repartitionRate_;
        short additionnalBonusesAttack_;
        short additionnalBonusesDefense_;
        short scoreTakerWithoutDeclaring_;
        short needlyScoresTaker_;
        Shorts additionnalBonuses_ =new Shorts();
        short maxDoubledDifference_=0;
        byte nombreJoueurs_=game.getNombreDeJoueurs();
        BidTarot contrat_=game.getContrat();
        CustList<SortedMiseres> miseresTaker_;
        CustList<SortedHandfuls> handfulsTaker_;
        Shorts doubledScoresPlayersTricks_ = new Shorts();
        Shorts needlyScoresPlayers_ = new Shorts();
        Shorts doublesDifferencesPlayers_ = new Shorts();
        if (game.getTricks().isEmpty()) {
            for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_<nombreJoueurs_; joueur_++) {
                scoresDeal_.add((short)0);
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
            short scorePreneurPlis_=end_.scorePreneurPlis(doubledScoreTaker_, needlyScoresTaker_);
            differenceScoreTaker_=(short) (scorePreneurPlis_-needlyScoresTaker_);
            playerSmallBound= end_.joueurPetitAuBout(_pseudos);
            scoreSmallBound = end_.scoreSmallBound();
            endTarotGame = end_.getUserState(differenceScoreTaker_, res.getUser());
            basePoints_=end_.base(doubledScoreTaker_,differenceScoreTaker_);
            scoreTakerWithoutDeclaring_=end_.scorePreneurSansAnnonces(differenceScoreTaker_,basePoints_);
            handfulsTaker_ = end_.getHandfulsPointsForTaker(scoreTakerWithoutDeclaring_);
            additionnalBonusesAttack_ = end_.additionnalBonusesAttack(contrat_);
            additionnalBonusesDefense_ = end_.additionnalBonusesDefense();
            short sommeTemporaire_= EndTarotGame.temporarySum(contrat_,scoreTakerWithoutDeclaring_, miseresTaker_, handfulsTaker_, additionnalBonusesAttack_, additionnalBonusesDefense_);
            repartitionRate_=end_.coefficientsRepartition();
            game.setScores(end_.calculateScores(repartitionRate_, sommeTemporaire_, scoreTakerWithoutDeclaring_));
            scoresDeal_=game.getScores();
        } else {
            end_.setupPlayersWonTricks();
            boolean pasJeuMisere_=game.pasJeuMisere();
            if(pasJeuMisere_) {
                for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_<nombreJoueurs_; joueur_++) {
                    short ptsDb_ = end_.scoreJoueurPlisDouble(joueur_);
                    doubledScoresPlayersTricks_.add(ptsDb_);
                    short ptsNeed_ = end_.scoreNecessaireJoueur(joueur_);
                    needlyScoresPlayers_.add(ptsNeed_);
                    short diffDb_ = EndTarotGame.differenceJoueurDouble(ptsNeed_, ptsDb_);
                    doublesDifferencesPlayers_.add(diffDb_);
                    maxDoubledDifference_=(short)NumberUtil.max(maxDoubledDifference_,diffDb_);
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
                for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_<nombreJoueurs_; joueur_++) {
                    doubledScoresPlayersTricks_.add(end_.scoreJoueurPlisDouble(joueur_));
                    needlyScoresPlayers_.add(end_.scoreNecessaireJoueur(joueur_));
                    doublesDifferencesPlayers_.add(EndTarotGame.differenceJoueurDoubleMisere(needlyScoresPlayers_.last(),doubledScoresPlayersTricks_.last()));
                    maxDoubledDifference_=(short) NumberUtil.max(maxDoubledDifference_,doublesDifferencesPlayers_.last());
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

    private void calculateScores(CustList<Longs> _scores,Shorts _scoresDeal) {
        GameType type_ = game.getType();
        long number_ = game.getNumber();
        int nbDeals_ = game.getRegles().getCommon().getNbDeals();
        calculateScores(_scores,_scoresDeal, type_, number_, nbDeals_);
    }

    void calculateScores(CustList<Longs> _scores,Shorts _scoresDeal, GameType _type, long _number, int _nbDeals) {
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

    public short getFinalUserPosition() {
        return finalUserPosition;
    }

    public Shorts getPositionsDiff() {
        return positionsDiff;
    }

    public short getMaxDifference() {
        return maxDifference;
    }

    public Shorts getPositionsOne() {
        return positionsOne;
    }

    public Shorts getPositionsTwo() {
        return positionsTwo;
    }

    public Shorts getPositionsThree() {
        return positionsThree;
    }

    public Shorts getPositionsFour() {
        return positionsFour;
    }

    public Shorts getCoefficients() {
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
