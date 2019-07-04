package cards.president;

import cards.consts.GameType;
import cards.president.enumerations.CardPresident;
import code.util.*;

import static org.junit.Assert.fail;

public abstract class CommonGamePresident {
    protected static GamePresidentBegin newGamePresidentBegin(GamePresident _g) {
        HandPresident playable_ = _g.cartesJouables(_g.getProgressingTrick().getEntameur());
        return new GamePresidentBegin(_g.getProgressingTrick(),_g.getTricks(),_g.isReversed(),_g.getRules(),playable_);
    }
    protected static GamePresidentProg newGamePresidentProg(GamePresident _g) {
        int count_ = _g.getProgressingTrick().total();
        byte player_ = _g.getProgressingTrick().getPlayer(count_, _g.getNombreDeJoueurs());
        HandPresident fullHand_ = _g.getDeal().main(player_);
        HandPresident playable_ = _g.cartesJouables(player_);
        return new GamePresidentProg(_g.getProgressingTrick(),_g.getTricks(),_g.isReversed(),_g.getRules(),playable_,fullHand_);
    }
    protected static boolean checkStrength(HandPresident _h, CardPresident _pres, boolean _reversed) {
        for (CardPresident c: _h) {
            if (c.strength(_reversed) != _pres.strength(_reversed)) {
                return false;
            }
        }
        return true;
    }
    protected static GamePresident newGamePresident(RulesPresident _r,
            CustList<HandPresident> _l, CustList<TrickPresident> _trs, TrickPresident _cur, int _dealer) {
        DealPresident deal_ = new DealPresident(_l, (byte) _dealer);
        GamePresident g_ = new GamePresident(GameType.EDIT,deal_,_r,new Bytes());
        g_.setProgressingTrick(_cur);
        g_.setTricks(_trs);
        g_.loadGame();
        CheckerGamePresidentWithRules.check(g_);
        if (!g_.getError().isEmpty()) {
            fail(g_.getError());
        }
        return g_;
    }
}
