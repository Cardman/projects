package cards.president;

import cards.consts.GameType;
import cards.consts.LineDeal;
import cards.consts.ResultsGame;
import code.util.*;


public final class ResultsPresident {
    private final ResultsGame res = new ResultsGame();
    private GamePresident game;

    public void initialize(StringList _pseudos, CustList<Longs> _scores, Bytes _r) {
        res.setScores(_scores);
        res.setNicknames(_pseudos);
        LineDeal ld_ = new LineDeal();
        Longs sc_ = new Longs();
        ld_.setScores(sc_);
        ld_.setNumber(res.getScores().size());
        res.getHistory().add(ld_);
        res.getScores().add(sc_);
        int s_ = _r.size();
        for (int i = 0; i < s_; i++) {
            res.getScores().last().add((long)_r.get(i));
        }
//        if(getScores().size()==1) {
//            for(short score_:scoresDeal_) {
//                getScores().last().add((long)score_);
//            }
//        } else {
//            byte indice_=0;
//            for(short score_:scoresDeal_) {
//                getScores().last().add(score_+getScores().get(getScores().size()-2).get(indice_));
//                indice_++;
//            }
//        }
    }

    public void initialize(StringList _pseudos, CustList<Longs> _scores) {
        res.setNicknames(_pseudos);
        Shorts scoresDeal_ = new Shorts();
        Bytes rk_ = game.getNewRanks();
        int s_ = rk_.size();
        for (int i = 0; i < s_; i++) {
            scoresDeal_.add((short) rk_.get(i));
        }
        GameType type_ = game.getType();
        long number_ = game.getNumber();
        int nbDeals_ = game.getRules().getCommon().getNbDeals();
        calculateScores(_scores,scoresDeal_, type_, number_, nbDeals_);
    }

    void calculateScores(CustList<Longs> _scores,Shorts _scoresDeal, GameType _type, long _number, int _nbDeals) {
        res.calculateScores(_scores,_scoresDeal,_type,_number,_nbDeals);
    }
    public GamePresident getGame() {
        return game;
    }
    public void setGame(GamePresident _game) {
        game = _game;
    }

    public ResultsGame getRes() {
        return res;
    }

}
