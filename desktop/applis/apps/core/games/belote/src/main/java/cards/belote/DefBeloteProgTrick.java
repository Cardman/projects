package cards.belote;

import cards.belote.enumerations.*;
import cards.consts.Suit;
import code.util.CustList;

final class DefBeloteProgTrick implements IntBeloteProgTrick {
    @Override
    public CardBelote carteDuJoueur(TrickBelote _trick, byte _joueur, byte _nbPlayers, CardBelote _def) {
        return _trick.carteDuJoueur(_joueur, _nbPlayers);
    }

    @Override
    public Suit couleurDemandee(TrickBelote _trick, Suit _def) {
        return _trick.couleurDemandee();
    }

    @Override
    public CustList<HandBelote> cartesRelativementMaitre(GameBeloteProgTrick _trick, Suit _couleurJoueur) {
        if (_couleurJoueur == Suit.UNDEFINED) {
            return new CustList<HandBelote>();
        }
        return _trick.cartesRelativementMaitre(_couleurJoueur);
    }
}
