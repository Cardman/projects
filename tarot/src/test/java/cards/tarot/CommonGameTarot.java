package cards.tarot;

import cards.consts.GameType;
import cards.consts.Suit;
import cards.tarot.enumerations.BidTarot;
import cards.tarot.enumerations.CallingCard;
import cards.tarot.enumerations.CardTarot;
import cards.tarot.enumerations.Miseres;
import code.util.CustList;
import code.util.EnumList;
import code.util.EnumMap;
import code.util.EqList;
import code.util.Numbers;

public abstract class CommonGameTarot {
    protected GameTarot newGameTarotWithourDecl(RulesTarot _r, CustList<TrickTarot> _trs, TrickTarot _prog,
                                              int _dealer,
                                              EnumList<BidTarot> _bids, HandTarot _calledCards, int _call, HandTarot _lastHand) {
        int nbPl_ = _r.getRepartition().getNombreJoueurs();
        EqList<EnumList<Miseres>> m_ = new EqList<EnumList<Miseres>>();
        EqList<HandTarot> h_ = new EqList<HandTarot>();
        for (int i = 0; i < nbPl_; i++) {
            m_.add(new EnumList<Miseres>());
            h_.add(new HandTarot());
        }
        return newGameTarot(_r,_trs,_prog,m_,h_,_dealer,_bids,_calledCards,_call,_lastHand);
    }
    protected GameTarot newGameTarot(RulesTarot _r, CustList<TrickTarot> _trs,TrickTarot _prog,
                                   EqList<EnumList<Miseres>> _m, EqList<HandTarot> _h, int _dealer,
                                   EnumList<BidTarot> _bids, HandTarot _calledCards, int _call, HandTarot _lastHand) {
        EqList<HandTarot> deal_ = new EqList<HandTarot>();
        deal_.add(_lastHand);
        GameTarot g_ = new GameTarot(GameType.RANDOM,new DealTarot(deal_, (byte) _dealer),_r);
        g_.setProgressingTrick(_prog);
        g_.setTricks(_trs);
        g_.setHandfuls(_h);
        g_.setDeclaresMiseres(_m);
        g_.setBids(_bids);
        g_.setCalledCards(_calledCards);
        if (_call > -1) {
            g_.getAppele().add((byte) _call);
        }
        byte player_ = g_.playerAfter((byte) _dealer);
        int taker_ = getTaker(_r,_dealer,_bids);
        BidTarot bid_ = BidTarot.FOLD;
        for (BidTarot b: _bids) {
            if (b.strongerThan(bid_)) {
                bid_ = b;
            }
            player_ = g_.playerAfter(player_);
        }
        g_.setPreneur((byte) taker_);
        g_.setContrat(bid_);
        if (!g_.avecContrat() || !bid_.isJouerDonne()) {
            g_.initEquipeDetermineeSansPreneur();
        } else if (_r.getRepartition().getAppel() == CallingCard.DEFINED) {
            g_.initEquipeDeterminee();
        } else if (_r.getRepartition().getAppel() == CallingCard.WITHOUT) {
            g_.initDefense();
        }
        CustList<TrickTarot> tricks_ = g_.unionPlis(false);
        byte starter_;
        byte trickWinner_;
        if (!tricks_.isEmpty()) {
            starter_ = _prog.getEntameur();
            trickWinner_ = _prog.getEntameur();
            for (TrickTarot t: tricks_) {
                if (!t.getVuParToutJoueur()) {
                    continue;
                }
                g_.retrieveCalledPlayers(t);
            }
            g_.retrieveCalledPlayers(_prog);
        } else if (_prog.getVuParToutJoueur()) {
            starter_ = _prog.getEntameur();
            trickWinner_ = _prog.getEntameur();
            g_.retrieveCalledPlayers(_prog);
        } else if (!g_.avecContrat()) {
            starter_ = g_.playerAfter((byte) _dealer);
            trickWinner_ = g_.getEntameur();
        } else if (g_.contrats() < g_.getNombreDeJoueurs()) {
            starter_ = g_.playerAfter((byte) _dealer);
            trickWinner_ = g_.getEntameur();
        } else if (g_.pasJeuApresPasse()) {
            //if existePreneur()
            starter_ = g_.getPreneur();
            trickWinner_ = g_.getPreneur();
        } else {
            starter_ = g_.playerAfter((byte) _dealer);
            trickWinner_ = g_.getEntameur();
        }
        g_.getAppele().removeDuplicates();
        for (int i: g_.getAppele()) {
            g_.getConfidence().get(i).set(g_.getPreneur(),true);
        }
        g_.setStarter(starter_);
        g_.setTrickWinner(trickWinner_);
        return g_;
    }

    protected int getTaker(RulesTarot _g, int _dealer, CustList<BidTarot> _bids) {
        byte player_ = _g.getRepartition().getNextPlayer(_dealer);
        int taker_ = CustList.INDEX_NOT_FOUND_ELT;
        BidTarot bid_ = BidTarot.FOLD;
        for (BidTarot b: _bids) {
            if (b.strongerThan(bid_)) {
                taker_ = player_;
                bid_ = b;
            }
            player_ = _g.getRepartition().getNextPlayer(player_);
        }
        return taker_;
    }
    protected GameTarotTrickInfo newGameTarotTrickInfo(GameTarot _g) {
        Numbers<Integer> handLengths_ = new Numbers<Integer>();
        int nombreCartesParJoueur_ = _g.getRegles().getRepartition().getNombreCartesParJoueur();
        int nbPl_ = _g.getRegles().getRepartition().getNombreJoueurs();
        for (int i = 0; i < nbPl_; i++) {
            handLengths_.add(nombreCartesParJoueur_);
        }
        handLengths_.add(_g.getRegles().getRepartition().getNombreCartesChien());
        int nbTr_ = _g.getTricks().size() - 1;
        for (int i = 0; i < nbPl_; i++) {
            handLengths_.set(i,handLengths_.get(i)-nbTr_);
        }
        for (int i: _g.getProgressingTrick().joueursAyantJoue((byte) nbPl_)) {
            handLengths_.set(i, handLengths_.get(i)-1);
        }
        return new GameTarotTrickInfo(_g.getProgressingTrick(), _g.getTricks(),
                _g.getDeclaresMiseres(),
                _g.getHandfuls(), _g.getContrat(), _g.getCalledCards(),
                handLengths_);
    }
    protected static void addSureCard(TarotInfoPliEnCours _info, int _p, CardTarot _c) {
        int nbPl_ = _info.getTeamsRelation().getNombreDeJoueurs();
        for (int i = 0; i < nbPl_; i++) {
            if (i == _p) {
                HandTarot h_ = _info.getCartesPossibles().getVal(_c.couleur()).get(_p);
                if (!h_.contient(_c)) {
                    h_.ajouter(_c);
                }
                h_ = _info.getCartesCertaines().getVal(_c.couleur()).get(_p);
                if (!h_.contient(_c)) {
                    h_.ajouter(_c);
                }
            } else {
                _info.getCartesPossibles().getVal(_c.couleur()).get(_p).removeCardIfPresent(_c);
            }
        }
    }
    protected static void addCard(EnumMap<Suit,EqList<HandTarot>> _poss, int _p, CardTarot _c) {
        HandTarot h_ = _poss.getVal(_c.couleur()).get(_p);
        if (h_.contient(_c)) {
            return;
        }
        h_.ajouter(_c);
    }


    protected static void removePossibleCard(TarotInfoPliEnCours _info, int _p, CardTarot _c) {
        if (_info.getCartesCertaines().getVal(_c.couleur()).get(_p).contient(_c)) {
            return;
        }
        HandTarot h_ = _info.getCartesPossibles().getVal(_c.couleur()).get(_p);
        h_.removeCardIfPresent(_c);
    }
    protected static void removeCard(EnumMap<Suit,EqList<HandTarot>> _poss, int _p, CardTarot _c) {
        HandTarot h_ = _poss.getVal(_c.couleur()).get(_p);
        h_.removeCardIfPresent(_c);
    }
}
