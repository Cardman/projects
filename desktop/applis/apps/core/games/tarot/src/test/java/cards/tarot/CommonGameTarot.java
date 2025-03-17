package cards.tarot;

import cards.consts.GameType;
import cards.tarot.enumerations.*;
import code.util.*;

public abstract class CommonGameTarot extends EquallableTarotUtil {
//    protected static GameTarot newGameTarotWithourDecl(HandTarot _currentHand,RulesTarot _r, CustList<TrickTarot> _trs, TrickTarot _prog,
//                                                       int _dealer,
//                                                       IdList<BidTarot> _bids, HandTarot _calledCards, int _call, HandTarot _lastHand) {
//        int nbPl_ = _r.getDealing().getId().getNombreJoueurs();
//        TstsTarotTriplet triplet_ = new TstsTarotTriplet(nbPl_);
//        return newGameTarot(_currentHand,_r,_trs,_prog,triplet_.getMiseres(),triplet_.getHandfuls(),triplet_.getHands(),_dealer,_bids,_calledCards,_call,_lastHand);
//    }
    protected static GameTarot newGameTarotWithourDecl(HandTarot _currentHand,RulesTarot _r, CustList<TrickTarot> _trs, TrickTarot _prog,
                                                       int _dealer,
                                                       IdList<BidTarot> _bids, HandTarot _calledCards, int _call, DealTarot _lastHand) {
        int nbPl_ = _r.getDealing().getId().getNombreJoueurs();
        GameTarotContent triplet_ = new GameTarotContent(nbPl_);
        return newGameTarot(_currentHand,_r,_trs,_prog,triplet_.getDeclaresMiseres(),triplet_.getDeclaresHandfuls(),triplet_.getHandfuls(),_dealer,_bids,_calledCards,_call,_lastHand);
    }
    protected static GameTarot newGameTarotWithourDecl(RulesTarot _r, CustList<TrickTarot> _trs, TrickTarot _prog,
                                              int _dealer,
                                              IdList<BidTarot> _bids, HandTarot _calledCards, int _call, HandTarot _lastHand) {
        int nbPl_ = _r.getDealing().getId().getNombreJoueurs();
        GameTarotContent triplet_ = new GameTarotContent(nbPl_);
        return newGameTarot(_r,_trs,_prog,triplet_.getDeclaresMiseres(),triplet_.getDeclaresHandfuls(),triplet_.getHandfuls(),_dealer,_bids,_calledCards,_call,_lastHand);
    }
    protected static GameTarot newGameTarotWithourDecl(RulesTarot _r, CustList<TrickTarot> _trs, TrickTarot _prog,
                                              int _dealer,
                                              IdList<BidTarot> _bids, HandTarot _calledCards, int _call, DealTarot _lastHand) {
        int nbPl_ = _r.getDealing().getId().getNombreJoueurs();
        GameTarotContent triplet_ = new GameTarotContent(nbPl_);
        return newGameTarot(_r,_trs,_prog,triplet_.getDeclaresMiseres(),triplet_.getDeclaresHandfuls(),triplet_.getHandfuls(),_dealer,_bids,_calledCards,_call,_lastHand);
    }
//    protected static GameTarot newGameTarot(HandTarot _currentHand,RulesTarot _r, CustList<TrickTarot> _trs,TrickTarot _prog,
//                                            CustList<IdList<Miseres>> _m, CustList<IdList<Handfuls>> _dh, CustList<HandTarot> _h, int _dealer,
//                                            IdList<BidTarot> _bids, HandTarot _calledCards, int _call, HandTarot _lastHand) {
//        GameTarot g_ = newGameTarot(_r,_trs,_prog,_m,_dh,_h,_dealer,_bids,_calledCards,_call,_lastHand);
////        check(g_,_calledCards,_currentHand);
//        return g_;
//    }
    protected static GameTarot newGameTarot(HandTarot _currentHand,RulesTarot _r, CustList<TrickTarot> _trs,TrickTarot _prog,
                                            CustList<IdList<Miseres>> _m, CustList<IdList<Handfuls>> _dh, CustList<HandTarot> _h, int _dealer,
                                            IdList<BidTarot> _bids, HandTarot _calledCards, int _call, DealTarot _lastHand) {
        GameTarot g_ = newGameTarot(_r,_trs,_prog,_m,_dh,_h,_dealer,_bids,_calledCards,_call,_lastHand);
//        CheckerGameTarotWithRules.check(g_);
//        assertTrue("Error",g_.getError().isEmpty());
        return g_;
    }
    protected static GameTarot newGameTarot(RulesTarot _r, CustList<TrickTarot> _trs,TrickTarot _prog,
                                            CustList<IdList<Miseres>> _m, CustList<IdList<Handfuls>> _dh, CustList<HandTarot> _h, int _dealer,
                                   IdList<BidTarot> _bids, HandTarot _calledCards, int _call, HandTarot _lastHand) {
        CustList<HandTarot> deal_ = new CustList<HandTarot>();
        deal_.add(_lastHand);
        DealTarot donne_ = new DealTarot(deal_, _dealer);
        return newGameTarot(_r, _trs, _prog, _m, _dh, _h, _dealer, _bids, _calledCards, _call, donne_);
    }

    protected static GameTarot newGameTarot(RulesTarot _r, CustList<TrickTarot> _trs, TrickTarot _prog, CustList<IdList<Miseres>> _m, CustList<IdList<Handfuls>> _dh, CustList<HandTarot> _h, int _dealer, IdList<BidTarot> _bids, HandTarot _calledCards, int _call, DealTarot _donne) {
        GameTarot g_ = new GameTarot(GameType.RANDOM, _donne, _r);
        g_.setProgressingTrick(_prog);
        g_.setTricks(_trs);
        g_.setHandfuls(_h);
        g_.setDeclaresMiseres(_m);
        g_.setDeclaresHandfuls(_dh);
        g_.setBids(_bids);
        g_.setCalledCards(_calledCards);

        g_.loadGame();

//        if (_call > -1) {
//            g_.getAppele().add((byte) _call);
//        }
//        byte player_ = g_.playerAfter((byte) _dealer);
//        int taker_ = getTaker(_r, _dealer, _bids);
//        BidTarot bid_ = BidTarot.FOLD;
//        for (BidTarot b: _bids) {
//            if (b.strongerThan(bid_)) {
//                bid_ = b;
//            }
//            player_ = g_.playerAfter(player_);
//        }
//        g_.setPreneur((byte) taker_);
//        g_.setContrat(bid_);
//        if (!g_.avecContrat() || !bid_.isJouerDonne()) {
//            g_.initEquipeDetermineeSansPreneur();
//        } else if (_r.getDealing().getAppel() == CallingCard.DEFINED) {
//            g_.initEquipeDeterminee();
//        } else if (_r.getDealing().getAppel() == CallingCard.WITHOUT) {
//            g_.initDefense();
//        }
//        for (TrickTarot t: g_.getTricks()) {
//            if (!t.getVuParToutJoueur()) {
//                continue;
//            }
//            g_.retrieveCalledPlayers(t);
//        }
//        byte starter_;
//        byte trickWinner_;
//        if (_prog.getVuParToutJoueur()) {
//            starter_ = _prog.getEntameur();
//            trickWinner_ = _prog.getEntameur();
//            g_.retrieveCalledPlayers(_prog);
//        } else if (!g_.avecContrat()) {
//            starter_ = g_.playerAfter((byte) _dealer);
//            trickWinner_ = g_.getEntameur();
//        } else if (g_.contrats() < g_.getNombreDeJoueurs()) {
//            starter_ = g_.playerAfter((byte) _dealer);
//            trickWinner_ = g_.getEntameur();
//        } else if (g_.pasJeuApresPasse()) {
//            //if existePreneur()
//            starter_ = g_.getPreneur();
//            trickWinner_ = g_.getPreneur();
//        } else {
//            starter_ = g_.playerAfter((byte) _dealer);
//            trickWinner_ = g_.getEntameur();
//        }
//        g_.getAppele().removeDuplicates();
//        g_.confianceAppele();
//        for (int i: g_.getAppele()) {
//            g_.getConfidence().get(i).set(g_.getPreneur(), BoolVal.TRUE);
//        }
//        g_.setStarter(starter_);
//        g_.setTrickWinner(trickWinner_);
        return g_;
    }

//    protected static GameTarotProgTrickClassic newGameTarotProgTrickClassic(GameTarot _g,GameTarotTrickInfo _done, GameTarotTeamsRelation _teamsRelation,
//                                                                            HandTarot _calledCards, HandTarot _currentHand) {
////        check(_g,_calledCards,_currentHand);
//        return new GameTarotProgTrickClassic(_done,_teamsRelation,_calledCards,_currentHand);
//    }
    protected static GameTarotProgTrickClassic newGameTarotProgTrickClassicDeal(GameTarot _g,GameTarotTrickInfo _done, GameTarotTeamsRelation _teamsRelation,
                                                                            HandTarot _calledCards, HandTarot _currentHand) {
//        CheckerGameTarotWithRules.check(_g);
//        assertTrue("Error",_g.getError().isEmpty());
        return new GameTarotProgTrickClassic(_done,_teamsRelation,_calledCards,_currentHand);
    }

//    private static int det(IdMap<Suit,CustList<HandTarot>> _foundHands, Ints _lengths) {
//        int nb_ = _lengths.size();
//        for (int i = 0;i < nb_; i++) {
//            int s_ = 0;
//            for (EntryCust<Suit,CustList<HandTarot>> h: _foundHands.entryList()) {
//                s_ += h.getValue().get(i).total();
//            }
//            if (s_ != _lengths.get(i)) {
//                return i;
//            }
//        }
//        return -1;
//    }
//    protected static GameTarotBeginTrickClassic newGameTarotBeginTrickClassic(GameTarot _g,GameTarotTrickInfo _done, GameTarotTeamsRelation _teamsRelation,
//                                                                              HandTarot _calledCards, HandTarot _currentHand) {
////        check(_g,_calledCards,_currentHand);
//        return new GameTarotBeginTrickClassic(_done,_teamsRelation,_calledCards,_currentHand);
//    }
    protected static GameTarotBeginTrickClassic newGameTarotBeginTrickClassicDeal(GameTarot _g,GameTarotTrickInfo _done, GameTarotTeamsRelation _teamsRelation,
                                                                              HandTarot _calledCards, HandTarot _currentHand) {
//        CheckerGameTarotWithRules.check(_g);
//        assertTrue("Error",_g.getError().isEmpty());
        return new GameTarotBeginTrickClassic(_done,_teamsRelation,_calledCards,_currentHand);
    }
//    protected static GameTarotMisere newGameTarotMisere(GameTarot _g, GameTarotTrickInfo _done, GameTarotTeamsRelation _teamsRelation,
//                                                        HandTarot _currentHand) {
//        check(_g,_g.getCalledCards(),_currentHand);
//        return new GameTarotMisere(_done,_teamsRelation,_currentHand);
//    }
    protected static GameTarotMisere newGameTarotMisereDeal(GameTarot _g, GameTarotTrickInfo _done, GameTarotTeamsRelation _teamsRelation,
                                                        HandTarot _currentHand) {
//        CheckerGameTarotWithRules.check(_g);
//        assertTrue("Error",_g.getError().isEmpty());
        return new GameTarotMisere(_done,_teamsRelation,_currentHand);
    }
//    private static void check(GameTarot _g,
//                              HandTarot _calledCards, HandTarot _currentHand) {
//        Ints handLengths_ = new Ints();
//        int nombreCartesParJoueur_ = _g.getRegles().getDealing().getNombreCartesParJoueur();
//        int nbPl_ = _g.getRegles().getDealing().getId().getNombreJoueurs();
//        for (int i = 0; i < nbPl_; i++) {
//            handLengths_.add(nombreCartesParJoueur_);
//        }
//        handLengths_.add(_g.getRegles().getDealing().getNombreCartesChien());
//        int nbTr_ = _g.getTricks().size() - 1;
//        for (int i = 0; i < nbPl_; i++) {
//            handLengths_.set(i,handLengths_.get(i)-nbTr_);
//        }
//        for (int i: _g.getProgressingTrick().joueursAyantJoue((byte) nbPl_)) {
//            handLengths_.set(i, handLengths_.get(i)-1);
//        }
//        CustList<HandTarot> hands_ = new CustList<HandTarot>();
//        GameTarotTrickInfo info_ = new GameTarotTrickInfo(_g.getProgressingTrick(), _g.getTricks(),
//                _g.getDeclaresMiseres(),
//                _g.getHandfuls(), _g.getContrat(), _g.getCalledCards(),
//                handLengths_);
//        info_.addSeenDeck(_g.derniereMain(),_g.getTeamsRelation());
//        IdMap<Suit,CustList<HandTarot>> cartesPossibles_ = new IdMap<Suit,CustList<HandTarot>>();
//        CustList<HandTarot> possibleExcuse_ = info_.excusePossibleRegles(_currentHand);
//        cartesPossibles_.put(CardTarot.EXCUSE.getId().getCouleur(), possibleExcuse_);
//        cartesPossibles_.put(Suit.TRUMP,info_.atoutsPossiblesRegles(
//                _currentHand));
//        for (Suit couleur_ : Suit.couleursOrdinaires()) {
//            // On fait une boucle sur les
//            // couleurs autres que l'atout
//            cartesPossibles_.put(couleur_,info_.cartesPossiblesRegles(couleur_,
//                    _currentHand));
//        }
//        for (int i =0;i<nbPl_;i++) {
//            for (EntryCust<Suit,CustList<HandTarot>> h: cartesPossibles_.entryList()) {
//                h.getValue().get(i).supprimerCartes(_g.getTricks().first().getCartes());
//            }
//        }
//        CustList<TrickTarot> allTr_ = new CustList<TrickTarot>();
//        allTr_.addAllElts(_g.getTricks());
//        allTr_.add(_g.getPliEnCours());
//        HandTarot hPl_ = new HandTarot();
//        for (TrickTarot t: allTr_) {
//            hPl_.ajouterCartes(t.getCartes());
//        }
//        hPl_.ajouterCartes(_currentHand);
//        if (_currentHand.contientCartes(_calledCards) && !_calledCards.estVide()) {
//            _g.getAppele().add(_g.getPliEnCours().getNextPlayer((byte) nbPl_));
//            _g.getAppele().removeDuplicates();
//        }
//        assertTrue("too much",_g.getAppele().size() <= 1);
//        assertTrue("too much",_g.getAppele().size() <= _g.getRegles().getDealing().getNbAppeles());
//        if (!hPl_.contientCartes(_calledCards)) {
//            for (int i =0;i<nbPl_;i++) {
//                if (!_g.getAppele().containsObj(i)) {
//                    for (EntryCust<Suit,CustList<HandTarot>> h: cartesPossibles_.entryList()) {
//                        h.getValue().get(i).supprimerCartes(_calledCards);
//                    }
//                }
//            }
//        }
//        HandTarot hPlCh_ = new HandTarot();
//        for (CardTarot c: hPl_) {
//            assertTrue(StringUtil.concat("found ",c.name()),!hPlCh_.contient(c));
//            hPlCh_.ajouter(c);
//        }
//        assertTrue(StringUtil.concat("Error len",Long.toString(handLengths_.get(_g.getPliEnCours().getNextPlayer((byte) nbPl_))),",",Long.toString(_currentHand.total())),handLengths_.get(_g.getPliEnCours().getNextPlayer((byte) nbPl_)) == _currentHand.total());
//        IdMap<Hypothesis,IdMap<Suit,CustList<HandTarot>>> hypotheses_ = info_.cartesCertaines(cartesPossibles_);
//        cartesPossibles_ = hypotheses_.getVal(Hypothesis.POSSIBLE);
//        IdMap<Suit,CustList<HandTarot>> cartesCertaines_ = hypotheses_
//                .getVal(Hypothesis.SURE);
//        while (true) {
//            int det_ = det(cartesCertaines_, handLengths_);
//            if (det_ < 0) {
//                break;
//            }
//            HandTarot all_ = new HandTarot();
//            HandTarot del_ = new HandTarot();
//            for (EntryCust<Suit,CustList<HandTarot>> h: cartesPossibles_.entryList()) {
//                all_.ajouterCartes(h.getValue().get(det_));
//            }
//            HandTarot curFound_ = new HandTarot();
//            for (EntryCust<Suit,CustList<HandTarot>> h: cartesCertaines_.entryList()) {
//                curFound_.ajouterCartes(h.getValue().get(det_));
//            }
//            all_.supprimerCartes(curFound_);
//            int req_ = handLengths_.get(det_) - curFound_.total();
//            assertTrue("No enough",req_ < all_.total());
//            for (int i = req_; i < all_.total(); i++) {
//                del_.ajouter(all_.carte(i));
//            }
//            for (EntryCust<Suit,CustList<HandTarot>> h: cartesPossibles_.entryList()) {
//                h.getValue().get(det_).supprimerCartes(del_);
//            }
//            hypotheses_ = info_.cartesCertaines(cartesPossibles_);
//            cartesPossibles_ = hypotheses_.getVal(Hypothesis.POSSIBLE);
//            cartesCertaines_ = hypotheses_
//                    .getVal(Hypothesis.SURE);
//        }
//        for (int i = 0; i < nbPl_; i++) {
//            HandTarot h_ = new HandTarot();
//            for (EntryCust<Suit,CustList<HandTarot>> h: cartesCertaines_.entryList()) {
//                h_.ajouterCartes(h.getValue().get(i));
//            }
//            hands_.add(h_);
//        }
//        hands_.add(_g.derniereMain());
//        _g.getDeal().setDeal(hands_);
//        CheckerGameTarotWithRules.check(_g);
//        assertTrue("Error",_g.getError().isEmpty());
//    }
    protected static TrickTarot newFirstTrick(CustList<BidTarot> _bids, RulesTarot _rules, int _deal) {
        return new TrickTarot(getTaker(_rules,_deal,_bids));
    }
    protected static TrickTarot newClassicTrick(CustList<TrickTarot> _tr, RulesTarot _rules, int _deal) {
        return new TrickTarot(_tr.last().getRamasseur());
    }

    protected static TrickTarot newClassicTrickFirst(CustList<TrickTarot> _tr, RulesTarot _rules, int _deal) {
        return new TrickTarot(_rules.getDealing().getId().getNextPlayer(_deal));
    }
    protected static TrickTarot newSlamTrick(CustList<BidTarot> _bids, RulesTarot _rules, int _deal) {
        return new TrickTarot(getTaker(_rules,_deal,_bids));
    }
    protected static void faireConfiance(GameTarot _g, int _p, GameTarotTeamsRelation _tr) {
        int n_ = _g.getProgressingTrick().getNextPlayer(_g.getNombreDeJoueurs());
        _tr.faireConfiance(n_, _p);
    }
    protected static int getTaker(RulesTarot _g, int _dealer, CustList<BidTarot> _bids) {
//        byte player_ = _g.getDealing().getId().getNextPlayer(_dealer);
//        int taker_ = IndexConstants.INDEX_NOT_FOUND_ELT;
//        BidTarot bid_ = BidTarot.FOLD;
//        for (BidTarot b: _bids) {
//            if (b.strongerThan(bid_)) {
//                taker_ = player_;
//                bid_ = b;
//            }
//            player_ = _g.getDealing().getId().getNextPlayer(player_);
//        }
        return GameTarot.bidding(_g.getDealing(),_bids,_dealer).getTaker();
    }
//    protected static GameTarotTrickInfo newGameTarotTrickInfo(GameTarot _g, HandTarot _currentHand) {
////        check(_g,_g.getCalledCards(),_currentHand);
//        Ints handLengths_ = TstsTarot.handLengths(_g);
//        GameTarotTrickInfo gameTarotTrickInfo_ = new GameTarotTrickInfo(_g.getProgressingTrick(), _g.getTricks(),
//                _g.getDeclaresMiseres(),
//                _g.getHandfuls(), _g.getContrat(), _g.getCalledCards(),
//                handLengths_);
//        gameTarotTrickInfo_.addSeenDeck(_g.derniereMain(),_g.getTeamsRelation());
//        return gameTarotTrickInfo_;
//    }
    protected static GameTarotTrickInfo newGameTarotTrickInfoDeal(GameTarot _g, HandTarot _currentHand) {
//        CheckerGameTarotWithRules.check(_g);
//        assertTrue("Error",_g.getError().isEmpty());
        Ints handLengths_ = handLengths5(_g);
        GameTarotTrickInfo gameTarotTrickInfo_ = new GameTarotTrickInfo(_g.getProgressingTrick(), _g.getTricks(),
                _g.getDeclaresMiseres(),
                _g.getHandfuls(), _g.getContrat(), _g.getCalledCards(),
                handLengths_);
        gameTarotTrickInfo_.addSeenDeck(_g.derniereMain(),_g.getTeamsRelation(_g.buildConfidence()));
        return gameTarotTrickInfo_;
    }
    protected static GameTarotTrickInfo newGameTarotTrickInfoDeal6(GameTarot _g, HandTarot _currentHand) {
//        CheckerGameTarotWithRules.check(_g);
//        assertTrue("Error",_g.getError().isEmpty());
        Ints handLengths_ = handLengths6(_g);
        GameTarotTrickInfo gameTarotTrickInfo_ = new GameTarotTrickInfo(_g.getProgressingTrick(), _g.getTricks(),
                _g.getDeclaresMiseres(),
                _g.getHandfuls(), _g.getContrat(), _g.getCalledCards(),
                handLengths_);
        gameTarotTrickInfo_.addSeenDeck(_g.derniereMain(),_g.getTeamsRelation(_g.buildConfidence()));
        return gameTarotTrickInfo_;
    }
    protected static GameTarotTrickInfo newGameTarotTrickInfo(GameTarot _g) {
        return newGameTarotTrickInfo(_g,_g.getTeamsRelation(_g.buildConfidence()));
    }
    protected static GameTarotTrickInfo newGameTarotTrickInfo(GameTarot _g, GameTarotTeamsRelation _rel) {
        Ints handLengths_ = handLengths5(_g);
        GameTarotTrickInfo gameTarotTrickInfo_ = new GameTarotTrickInfo(_g.getProgressingTrick(), _g.getTricks(),
                _g.getDeclaresMiseres(),
                _g.getHandfuls(), _g.getContrat(), _g.getCalledCards(),
                handLengths_);
        gameTarotTrickInfo_.addSeenDeck(_g.derniereMain(),_rel);
        return gameTarotTrickInfo_;
    }
    protected static GameTarotTrickInfo newGameTarotTrickInfo4(GameTarot _g, GameTarotTeamsRelation _rel) {
        Ints handLengths_ = handLengths4(_g);
        GameTarotTrickInfo gameTarotTrickInfo_ = new GameTarotTrickInfo(_g.getProgressingTrick(), _g.getTricks(),
                _g.getDeclaresMiseres(),
                _g.getHandfuls(), _g.getContrat(), _g.getCalledCards(),
                handLengths_);
        gameTarotTrickInfo_.addSeenDeck(_g.derniereMain(),_rel);
        return gameTarotTrickInfo_;
    }
    protected static GameTarotTrickInfo newGameTarotTrickInfo6(GameTarot _g, GameTarotTeamsRelation _rel) {
        Ints handLengths_ = handLengths6(_g);
        GameTarotTrickInfo gameTarotTrickInfo_ = new GameTarotTrickInfo(_g.getProgressingTrick(), _g.getTricks(),
                _g.getDeclaresMiseres(),
                _g.getHandfuls(), _g.getContrat(), _g.getCalledCards(),
                handLengths_);
        gameTarotTrickInfo_.addSeenDeck(_g.derniereMain(),_rel);
        return gameTarotTrickInfo_;
    }

    protected static void addSureCard(TarotInfoPliEnCours _info, int _p, CardTarot _c) {
        HandTarot h_ = _info.getCartesCertaines().getVal(_c.getId().getCouleur()).get(_p);
        h_.ajouter(_c);
        HandTarot.trierParForceEnCours(h_.getCards(), _c.getId().getCouleur());
    }

    protected static void addPossibleCard(TarotInfoPliEnCours _info, int _p, CardTarot _c) {
        HandTarot h_ = _info.getCartesPossibles().getVal(_c.getId().getCouleur()).get(_p);
        h_.ajouter(_c);
        h_.trierParForceEnCours(_c.getId().getCouleur());
    }


    protected static void removePossibleCard(TarotInfoPliEnCours _info, int _p, CardTarot _c) {
        HandTarot h_ = _info.getCartesPossibles().getVal(_c.getId().getCouleur()).get(_p);
        h_.removeCardIfPresent(_c);
    }
}
