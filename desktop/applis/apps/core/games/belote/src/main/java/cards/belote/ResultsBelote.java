package cards.belote;

import cards.consts.EndGameState;
import cards.consts.GameType;
import cards.consts.ResultsGame;
import code.util.*;


public final class ResultsBelote {
    private final ResultsGame res = new ResultsGame();
    private GameBelote game;

    private EndGameState endBeloteGame = EndGameState.EQUALLITY;

    private int differenceScoreTaker;

    public void initialize(StringList _pseudos, CustList<Longs> _scores) {
        getRes().setScores(_scores);
        res.setNicknames(_pseudos);
        Shorts scoresDeal_ = new Shorts();
        BidBeloteSuit bid_ = game.getBid();
        if(bid_.jouerDonne()) {
            int pointsAttaqueTemporaire_;
            int pointsAttaqueDefinitif_;
            int pointsDefenseTemporaire_;
            int pointsDefenseDefinitif_;
            EndBeloteGame end_ = game.getEndBeloteGame();
            pointsAttaqueTemporaire_ = end_.pointsAttackWithBonus();
            pointsDefenseTemporaire_ = end_.pointsDefenseWithBonus();
            pointsAttaqueDefinitif_=end_.scoreDefinitifAttaque(pointsAttaqueTemporaire_, pointsDefenseTemporaire_);
            pointsDefenseDefinitif_=end_.scoreDefinitifDefense(pointsAttaqueDefinitif_,pointsDefenseTemporaire_);
            game.setScores(end_.scores(pointsAttaqueDefinitif_, pointsDefenseDefinitif_));
            scoresDeal_=game.getScores();
            differenceScoreTaker = end_.getDiffAttackPointsMinusDefensePoints(scoresDeal_);
            endBeloteGame = end_.getUserState(res.getUser(),scoresDeal_);
        } else {
            int nbPl_ = game.getNombreDeJoueurs();
            for (int i = 0; i < nbPl_; i++) {
                scoresDeal_.add((short)0);
            }
        }
        GameType type_ = game.getType();
        long number_ = game.getNombre();
        int nbDeals_ = game.getRegles().getCommon().getNbDeals();
        calculateScores(scoresDeal_, type_, number_, nbDeals_);
    }

    void calculateScores(Shorts _scoresDeal, GameType _type, long _number, int _nbDeals) {
        res.calculateScores(_scoresDeal,_type,_number,_nbDeals);
    }
    public GameBelote getGame() {
        return game;
    }
    public void setGame(GameBelote _game) {
        game = _game;
    }

    public ResultsGame getRes() {
        return res;
    }

    public EndGameState getEndBeloteGame() {
        return endBeloteGame;
    }

    public int getDifferenceScoreTaker() {
        return differenceScoreTaker;
    }
}
