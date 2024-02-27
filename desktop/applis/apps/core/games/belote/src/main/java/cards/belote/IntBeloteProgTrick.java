package cards.belote;

import cards.belote.enumerations.CardBelote;
import cards.consts.Suit;
import code.util.CustList;

interface IntBeloteProgTrick {
    CardBelote carteDuJoueur(TrickBelote _trick, byte _joueur, byte _nbPlayers, CardBelote _def);
    Suit couleurDemandee(TrickBelote _trick, Suit _def);
    CustList<HandBelote> cartesRelativementMaitre(GameBeloteProgTrick _trick,
                                                  Suit _couleurJoueur);
}
