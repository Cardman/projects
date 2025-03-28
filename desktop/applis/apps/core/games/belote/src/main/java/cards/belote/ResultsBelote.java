package cards.belote;

import cards.consts.EndGameState;
import cards.consts.GameType;
import cards.consts.ResultsGame;
import code.util.*;


public final class ResultsBelote {
    private final ResultsGame res = new ResultsGame();
    private GameBelote game;

    private EndGameState endBeloteGame = EndGameState.EQUALLITY;

    private long differenceScoreTaker;

    public void initialize(StringList _pseudos, CustList<Longs> _scores) {
        res.setNicknames(_pseudos);
        Longs scoresDeal_ = new Longs();
        BidBeloteSuit bid_ = game.getBid();
        if(bid_.jouerDonne()) {
            long pointsAttaqueTemporaire_;
            long pointsAttaqueDefinitif_;
            long pointsDefenseTemporaire_;
            long pointsDefenseDefinitif_;
            EndBeloteGame end_ = game.getEndBeloteGame();
            pointsAttaqueTemporaire_ = end_.pointsAttackWithBonus();
            pointsDefenseTemporaire_ = end_.pointsDefenseWithBonus();
            pointsAttaqueDefinitif_=end_.scoreDefinitifAttaque(pointsAttaqueTemporaire_, pointsDefenseTemporaire_);
            pointsDefenseDefinitif_= EndBeloteGame.scoreDefinitifDefense(pointsAttaqueDefinitif_,pointsDefenseTemporaire_);
            game.setScores(end_.scores(pointsAttaqueDefinitif_, pointsDefenseDefinitif_));
            scoresDeal_=game.getScores();
            differenceScoreTaker = end_.getDiffAttackPointsMinusDefensePoints(scoresDeal_);
            endBeloteGame = end_.getUserState(res.getUser(),scoresDeal_);
        } else {
            int nbPl_ = game.getNombreDeJoueurs();
            for (int i = 0; i < nbPl_; i++) {
                scoresDeal_.add(0L);
            }
        }
        GameType type_ = game.getType();
        long number_ = game.getNombre();
        int nbDeals_ = game.getRegles().getCommon().getNbDeals();
        calculateScores(_scores,scoresDeal_, type_, number_, nbDeals_);
    }

    void calculateScores(CustList<Longs> _scores,Longs _scoresDeal, GameType _type, long _number, int _nbDeals) {
        res.calculateScores(_scores,_scoresDeal,_type,_number,_nbDeals);
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

    public long getDifferenceScoreTaker() {
        return differenceScoreTaker;
    }
}
