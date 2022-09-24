package cards.tarot.tsts;

import cards.consts.Suit;
import cards.tarot.GameTarot;
import cards.tarot.HandTarot;
import cards.tarot.RulesTarot;
import cards.tarot.TarotInfoPliEnCours;
import cards.tarot.enumerations.BidTarot;
import cards.tarot.enumerations.CardTarot;
import code.util.CustList;
import code.util.EnumList;
import code.util.IdMap;
import code.util.Ints;
import code.util.comparators.ComparatorBoolean;
import code.util.core.BoolVal;

public final class TstsTarot {
    private TstsTarot() {
    }

    public static BidTarot bid(BidTarot _bid, int _pl, int _taker) {
        if (_pl == _taker) {
            return _bid;
        } else {
            return BidTarot.FOLD;
        }
    }

    public static CustList<CustList<BoolVal>> initConf(int _nb) {
        CustList<CustList<BoolVal>> confidence_ = new CustList<CustList<BoolVal>>();
        for (int i = 0; i < _nb; i++) {
            CustList<BoolVal> b_ = new CustList<BoolVal>();
            for (int j = 0; j < _nb; j++) {
                b_.add(ComparatorBoolean.of(i==j));
            }
            confidence_.add(b_);
        }
        return confidence_;
    }

    public static EnumList<BidTarot> bids(RulesTarot _r, BidTarot _b) {
        EnumList<BidTarot> bids_ = new EnumList<BidTarot>();
        bids_.add(_b);
        int nb_ = _r.getDealing().getId().getNombreJoueurs();
        for (int i = 1; i < nb_; i++) {
            bids_.add(BidTarot.FOLD);
        }
        return bids_;
    }

    public static IdMap<Suit,CustList<HandTarot>> generateMult(int _nbPlayer) {
        IdMap<Suit,CustList<HandTarot>> e_ = new IdMap<Suit,CustList<HandTarot>>();
        EnumList<Suit> s_ = new EnumList<Suit>();
        s_.add(Suit.UNDEFINED);
        s_.add(Suit.TRUMP);
        s_.addAllElts(Suit.couleursOrdinaires());
        for (Suit s: s_) {
            CustList<HandTarot> l_ = new CustList<HandTarot>();
            for (int i = 0; i <= _nbPlayer; i++) {
                l_.add(new HandTarot());
            }
            e_.addEntry(s,l_);
        }
        return e_;
    }

    public static void sureCard(TarotInfoPliEnCours _info, int _p, CardTarot _c) {
        int nbPl_ = _info.getNbPlayers();
        for (int i = 0; i < nbPl_; i++) {
            if (i == _p) {
                HandTarot h_ = _info.getCartesCertaines().getVal(_c.getId().getCouleur()).get(_p);
                h_.ajouter(_c);
                HandTarot.trierParForceEnCours(h_.getCards(), _c.getId().getCouleur());
            } else {
                _info.getCartesPossibles().getVal(_c.getId().getCouleur()).get(i).removeCardIfPresent(_c);
            }
        }
    }

    public static Ints handLengths(GameTarot _g) {
        Ints handLengths_ = new Ints();
        int nombreCartesParJoueur_ = _g.getRegles().getDealing().getNombreCartesParJoueur();
        int nbPl_ = _g.getRegles().getDealing().getId().getNombreJoueurs();
        for (int i = 0; i < nbPl_; i++) {
            handLengths_.add(nombreCartesParJoueur_);
        }
        handLengths_.add(_g.getRegles().getDealing().getNombreCartesChien());
        int nbTr_ = _g.getTricks().size() - 1;
        for (int i = 0; i < nbPl_; i++) {
            handLengths_.set(i,handLengths_.get(i)-nbTr_);
        }
        for (int i: _g.getProgressingTrick().joueursAyantJoue((byte) nbPl_)) {
            handLengths_.set(i, handLengths_.get(i)-1);
        }
        return handLengths_;
    }
}
