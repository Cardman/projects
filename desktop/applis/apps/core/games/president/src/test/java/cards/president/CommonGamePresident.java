package cards.president;

import cards.consts.GameType;
import code.util.*;

public abstract class CommonGamePresident extends EquallablePresidentUtil {
    protected static GamePresidentBegin newGamePresidentBegin(GamePresident _g) {
        HandPresident playable_ = _g.cartesJouables(_g.getProgressingTrick().getEntameur());
        return new GamePresidentBegin(_g.getProgressingTrick(),_g.getTricks(),_g.isReversed(),_g.getRules(),playable_);
    }
    protected static GamePresidentProg newGamePresidentProg(GamePresident _g) {
        int count_ = _g.getProgressingTrick().total();
        byte player_ = _g.getProgressingTrick().getPlayer(count_, _g.getNombreDeJoueurs());
        HandPresident fullHand_ = _g.getDeal().hand(player_);
        HandPresident playable_ = _g.cartesJouables(player_);
        return new GamePresidentProg(_g.getProgressingTrick(),_g.getTricks(),_g.isReversed(),_g.getRules(),playable_,fullHand_);
    }

    protected static GamePresident newGamePresident(RulesPresident _r,
            CustList<HandPresident> _l, CustList<TrickPresident> _trs, TrickPresident _cur, int _dealer) {
        DealPresident deal_ = new DealPresident(_l, (byte) _dealer);
        GamePresident g_ = new GamePresident(GameType.EDIT,deal_,_r,new Bytes());
        g_.setProgressingTrick(_cur);
        g_.setTricks(_trs);
        g_.loadGame();
//        CheckerGamePresidentWithRules.check(g_);
//        assertTrue(g_.getError().isEmpty());
        return g_;
    }
}
