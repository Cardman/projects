package cards.belote;

import cards.belote.enumerations.CardBelote;
import cards.consts.Suit;
import code.util.CustList;

final class FixBeloteProgTrick implements IntBeloteProgTrick {
    @Override
    public CardBelote carteDuJoueur(TrickBelote _trick, byte _joueur, byte _nbPlayers, CardBelote _def) {
        return _def;
    }

    @Override
    public Suit couleurDemandee(TrickBelote _trick, Suit _def) {
        return _def;
    }

    @Override
    public CustList<HandBelote> cartesRelativementMaitre(GameBeloteProgTrick _trick, Suit _couleurJoueur) {
        return new CustList<HandBelote>();
    }
}
