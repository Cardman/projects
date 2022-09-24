package cards.belote.tsts;

import cards.belote.BeloteInfoPliEnCours;
import cards.belote.BidBeloteSuit;
import cards.belote.GameBelote;
import cards.belote.HandBelote;
import cards.belote.enumerations.CardBelote;
import cards.consts.Order;
import cards.consts.Suit;
import code.util.CustList;
import code.util.EnumList;
import code.util.IdMap;
import code.util.Ints;

public final class TstsBelote {
    private TstsBelote() {
    }
    public static IdMap<Suit,CustList<HandBelote>> generate(int _nbPlayer) {
        IdMap<Suit,CustList<HandBelote>> e_ = new IdMap<Suit,CustList<HandBelote>>();
        EnumList<Suit> s_ = new EnumList<Suit>();
        s_.addAllElts(Suit.couleursOrdinaires());
        for (Suit s: s_) {
            CustList<HandBelote> l_ = new CustList<HandBelote>();
            for (int i = 0; i < _nbPlayer; i++) {
                l_.add(new HandBelote());
            }
            e_.addEntry(s,l_);
        }
        return e_;
    }
    public static IdMap<Suit, CustList<HandBelote>> generateMap(int _nbPlayer, BidBeloteSuit _b) {
        IdMap<Suit,CustList<HandBelote>> e_ = new IdMap<Suit,CustList<HandBelote>>();
        EnumList<Suit> s_ = new EnumList<Suit>();
        s_.addAllElts(Suit.couleursOrdinaires());
        for (Suit s: s_) {
            CustList<HandBelote> l_ = new CustList<HandBelote>();
            for (int i = 0; i < _nbPlayer; i++) {
                HandBelote h_ = new HandBelote();
                h_.setOrdre(HandBelote.order(_b,_b.getSuit(),s));
//                if(_b.getCouleurDominante()) {
//                    if(s!= _b.getSuit()) {
//                        h_.setOrdre(Order.SUIT);
//                    }
//                } else if(_b.ordreCouleur()) {
//                    h_.setOrdre(Order.SUIT);
//                }
                l_.add(h_);
            }
            e_.addEntry(s,l_);
        }
        return e_;
    }

    public static Ints handLengths(GameBelote _g) {
        Ints handLengths_ = new Ints();
        int nombreCartesParJoueur_ = _g.getRegles().getDealing().getNombreCartesParJoueur();
        int nbPl_ = _g.getRegles().getDealing().getId().getNombreJoueurs();
        for (int i = 0; i < nbPl_; i++) {
            handLengths_.add(nombreCartesParJoueur_);
        }
        int nbTr_ = _g.getTricks().size();
        for (int i = 0; i < nbPl_; i++) {
            handLengths_.set(i,handLengths_.get(i)-nbTr_);
        }
        for (int i: _g.getProgressingTrick().playersHavingPlayed((byte) nbPl_)) {
            handLengths_.set(i, handLengths_.get(i)-1);
        }
        return handLengths_;
    }

    public static void sureCard(BeloteInfoPliEnCours _info, int _p, CardBelote _c) {
        int nbPl_ = _info.getNbPlayers();
        Suit s_ = _c.getId().getCouleur();
//        HandBelote hc_ = _info.getCartesPossibles().getVal(s_).get(_p);
//        if (!hc_.contient(_c)) {
//            return;
//        }
        for (int i = 0; i < nbPl_; i++) {
            if (i == _p) {
                HandBelote h_ = _info.getCartesCertaines().getVal(s_).get(_p);
                possibleCard(_info, _c, s_, h_);
            } else {
//                int total_ = _info.getCartesPossibles().getVal(s_).get(i).total();
                _info.getCartesPossibles().getVal(s_).get(i).removeCardIfPresent(_c);
//                assert total_ == _info.getCartesPossibles().getVal(s_).get(i).total();
            }
        }
    }

    public static void possibleCard(BeloteInfoPliEnCours _info, CardBelote _c, Suit _suit, HandBelote _h) {
        possCard(_c, _info.getContrat(), _h, _suit);
    }

    public static void possCard(CardBelote _c, BidBeloteSuit _bid, HandBelote _h, Suit _suit) {
//        if (_h.contient(_c)) {
//            return;
//        }
        _h.ajouter(_c);
        Order order_ = HandBelote.order(_bid, _bid.getSuit(), _suit);
        _h.setOrdre(order_);
//        if (_bid.getCouleurDominante()) {
//            if (_suit == _bid.getSuit()) {
//                _h.setOrdre(Order.TRUMP);
//            } else {
//                _h.setOrdre(Order.SUIT);
//            }
//        } else if (_bid.ordreCouleur()) {
//            _h.setOrdre(Order.SUIT);
//        } else {
//            _h.setOrdre(Order.TRUMP);
//        }
        HandBelote.sortList(true,_h.premiereCarte().getId().getCouleur(),_h.getCards(),order_);
//        _h.trierUnicolore(true);
    }

    public static IdMap<Suit, CustList<HandBelote>> pos(BidBeloteSuit _b, HandBelote _p, HandBelote _cur) {
        IdMap<Suit, CustList<HandBelote>> poss_ = generateMap(4, _b);
        HandBelote rem_ = new HandBelote();
        rem_.ajouterCartes(HandBelote.pileBase());
        rem_.supprimerCartes(_cur);
        rem_.supprimerCartes(_p);
        for (int i = 1; i < 4; i++) {
            for (Suit s: Suit.couleursOrdinaires()) {
                for (CardBelote c: rem_.couleur(_b,s)) {
                    HandBelote h_ = poss_.getVal(c.getId().getCouleur()).get(i);
                    Order order_ = HandBelote.order(_b, _b.getSuit(), s);
                    h_.setOrdre(order_);
                    h_.ajouter(c);
                    HandBelote.sortList(true,s,h_.getCards(),order_);
                }
            }
        }
        return poss_;
    }
}
