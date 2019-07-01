package cards.belote;

import cards.belote.enumerations.CardBelote;
import cards.consts.GameType;
import cards.consts.Hypothesis;
import cards.consts.Order;
import cards.consts.Suit;
import code.util.*;

import static org.junit.Assert.fail;

public abstract class CommonGameBelote {
    protected static GameBelote newGameBeloteWithourDecl(HandBelote _currentHand, RulesBelote _r, CustList<TrickBelote> _trs, TrickBelote _prog,
                                                         int _dealer,
                                                         EqList<BidBeloteSuit> _bids, HandBelote _lastHand) {
        return newGameBelote(_currentHand,_r,_trs,_prog,_dealer,_bids, _lastHand);
    }
    protected static GameBelote newGameBeloteWithourDecl(RulesBelote _r, CustList<TrickBelote> _trs, TrickBelote _prog,
                                                         int _dealer,
                                                         EqList<BidBeloteSuit> _bids, HandBelote _lastHand) {
        return newGameBelote(_r,_trs,_prog,_dealer,_bids, _lastHand);
    }
    protected static GameBelote newGameBelote(HandBelote _currentHand, RulesBelote _r, CustList<TrickBelote> _trs, TrickBelote _prog,
                                              int _dealer,
                                              EqList<BidBeloteSuit> _bids, HandBelote _lastHand) {
        GameBelote g_ = newGameBelote(_r,_trs,_prog,_dealer,_bids,_lastHand);
        check(g_,_currentHand);
        return g_;
    }
    protected static GameBelote newGameBelote(RulesBelote _r, CustList<TrickBelote> _trs, TrickBelote _prog,
                                              int _dealer,
                                              EqList<BidBeloteSuit> _bids, HandBelote _lastHand) {
        EqList<HandBelote> deal_ = new EqList<HandBelote>();
        byte nombreDeJoueurs_ = (byte) _r.getDealing().getNombreJoueurs();
        for (int i = 0; i< nombreDeJoueurs_; i++) {
            deal_.add(new HandBelote());
        }
        deal_.add(_lastHand);
        GameBelote g_ = new GameBelote(GameType.RANDOM,new DealBelote(deal_, (byte) _dealer),_r);
        g_.setProgressingTrick(_prog);
        g_.setTricks(_trs);
        g_.setBids(_bids);
        byte player_ = g_.playerAfter((byte) _dealer);
        int taker_ = getTaker(_r,_dealer,_bids);
        BidBeloteSuit bid_ = new BidBeloteSuit();
        if (_r.dealAll()) {
            g_.setEndBidsFirstRound(false);
            for (BidBeloteSuit b: _bids) {
                if (b.getPoints() > bid_.getPoints()) {
                    bid_ = b;
                }
                player_ = g_.playerAfter(player_);
            }
            g_.setContrat(bid_);
        } else {
            g_.setEndBidsFirstRound(_bids.size() >= nombreDeJoueurs_);
            for (BidBeloteSuit b: _bids) {
                if (b.strongerThan(bid_)) {
                    bid_ = b;
                }
                player_ = g_.playerAfter(player_);
            }
            g_.setContrat(bid_);
        }
        byte starter_;
        byte trickWinner_;
        if (!_trs.isEmpty()) {
            starter_ = _prog.getEntameur();
            trickWinner_ = _prog.getEntameur();
        } else if (!_prog.estVide()) {
            starter_ = _prog.getEntameur();
            trickWinner_ = _prog.getEntameur();
        } else if (g_.keepBidding()) {
            starter_ = g_.playerAfter((byte) _dealer);
            trickWinner_ = starter_;
        } else {
            if (bid_.getPoints() >= HandBelote.pointsTotauxDixDeDer(bid_)) {
                starter_ = _prog.getEntameur();
                trickWinner_ = _prog.getEntameur();
            } else {
                starter_ = g_.playerAfter((byte) _dealer);
                trickWinner_ = starter_;
            }
        }
        g_.setPreneur((byte) taker_);
        g_.setEntameur(starter_);
        g_.setTrickWinner(trickWinner_);
        return g_;
    }

    protected static GameBeloteProgTrick newGameBeloteProgTrick(GameBelote _g, GameBeloteTrickInfo _done, GameBeloteTeamsRelation _teamsRelation,
                                                                       HandBelote _currentHand) {
        check(_g,_currentHand);
        return new GameBeloteProgTrick(_done,_teamsRelation,_currentHand);
    }

    private static int det(EnumMap<Suit,EqList<HandBelote>> _foundHands, Ints _lengths) {
        int nb_ = _lengths.size();
        for (int i = 0;i < nb_; i++) {
            int s_ = 0;
            for (EntryCust<Suit,EqList<HandBelote>> h: _foundHands.entryList()) {
                s_ += h.getValue().get(i).total();
            }
            if (s_ != _lengths.get(i)) {
                return i;
            }
        }
        return -1;
    }
    protected static GameBeloteBeginTrick newGameBeloteBeginTrick(GameBelote _g, GameBeloteTrickInfo _done, GameBeloteTeamsRelation _teamsRelation,
                                                                  HandBelote _currentHand) {
        check(_g,_currentHand);
        return new GameBeloteBeginTrick(_done,_teamsRelation,_currentHand);
    }
    private static void check(GameBelote _g,HandBelote _currentHand) {
        Ints handLengths_ = new Ints();
        int nombreCartesParJoueur_ = _g.getRegles().getRepartition().getNombreCartesParJoueur();
        int nbPl_ = _g.getRegles().getRepartition().getNombreJoueurs();
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
        EqList<HandBelote> hands_ = new EqList<HandBelote>();
        BidBeloteSuit bid_ = _g.getContrat();
        GameBeloteTrickInfo info_ = new GameBeloteTrickInfo(_g.getProgressingTrick(), _g.getTricks(),
                _g.getDeclares(),
                _g.getDeclaresBeloteRebelote(), bid_,
                handLengths_);
        info_.addSeenDeck(_g.getDistribution().derniereMain(),_g.getTeamsRelation());
        EnumMap<Suit,EqList<HandBelote>> cartesPossibles_ = new EnumMap<Suit,EqList<HandBelote>>();
        for(Suit couleur_:GameBeloteCommon.couleurs()) {
            //On fait une boucle sur les couleurs autres que l'atout
            if(bid_.getCouleur()!=couleur_&&!bid_.ordreAtout()) {
                cartesPossibles_.put(couleur_,info_.cartesPossiblesRegles(couleur_, _currentHand));
            } else {
                cartesPossibles_.put(couleur_,info_.atoutsPossiblesRegles(couleur_,_currentHand));
            }
        }
        CustList<TrickBelote> allTr_ = new CustList<TrickBelote>();
        allTr_.addAllElts(_g.getTricks());
        allTr_.add(_g.getPliEnCours());
        HandBelote hPl_ = new HandBelote();
        for (TrickBelote t: allTr_) {
            hPl_.ajouterCartes(t.getCartes());
        }
        hPl_.ajouterCartes(_currentHand);
        HandBelote hPlCh_ = new HandBelote();
        for (CardBelote c: hPl_) {
            if (hPlCh_.contient(c)) {
                fail(StringList.concat("found ",c.name()));
            }
            hPlCh_.ajouter(c);
        }
        if (handLengths_.get(_g.getPliEnCours().getNextPlayer((byte) nbPl_)) != _currentHand.total()) {
            fail(StringList.concat("Error len",Integer.toString(handLengths_.get(_g.getPliEnCours().getNextPlayer((byte) nbPl_))),",",Integer.toString(_currentHand.total())));
        }
        EnumMap<Hypothesis,EnumMap<Suit,EqList<HandBelote>>> hypotheses_ = info_.cartesCertaines(cartesPossibles_);
        cartesPossibles_ = hypotheses_.getVal(Hypothesis.POSSIBLE);
        EnumMap<Suit,EqList<HandBelote>> cartesCertaines_ = hypotheses_
                .getVal(Hypothesis.SURE);
        while (true) {
            int det_ = det(cartesCertaines_, handLengths_);
            if (det_ < 0) {
                break;
            }
            HandBelote all_ = new HandBelote();
            HandBelote del_ = new HandBelote();
            for (EntryCust<Suit,EqList<HandBelote>> h: cartesPossibles_.entryList()) {
                all_.ajouterCartes(h.getValue().get(det_));
            }
            HandBelote curFound_ = new HandBelote();
            for (EntryCust<Suit,EqList<HandBelote>> h: cartesCertaines_.entryList()) {
                curFound_.ajouterCartes(h.getValue().get(det_));
            }
            all_.supprimerCartes(curFound_);
            int req_ = handLengths_.get(det_) - curFound_.total();
            if (req_ >= all_.total()) {
                for (int i = 0; i < nbPl_; i++) {
                    HandBelote h_ = new HandBelote();
                    for (EntryCust<Suit,EqList<HandBelote>> h: cartesCertaines_.entryList()) {
                        h_.ajouterCartes(h.getValue().get(i));
                    }
                    hands_.add(h_);
                }
                fail("No enough");
            }
            for (int i = req_; i < all_.total(); i++) {
                del_.ajouter(all_.carte(i));
            }
            for (EntryCust<Suit,EqList<HandBelote>> h: cartesPossibles_.entryList()) {
                h.getValue().get(det_).supprimerCartes(del_);
            }
            hypotheses_ = info_.cartesCertaines(cartesPossibles_);
            cartesPossibles_ = hypotheses_.getVal(Hypothesis.POSSIBLE);
            cartesCertaines_ = hypotheses_
                    .getVal(Hypothesis.SURE);
        }
        for (int i = 0; i < nbPl_; i++) {
            HandBelote h_ = new HandBelote();
            for (EntryCust<Suit,EqList<HandBelote>> h: cartesCertaines_.entryList()) {
                h_.ajouterCartes(h.getValue().get(i));
            }
            hands_.add(h_);
        }
        if (_g.getRules().dealAll()) {
            hands_.add(new HandBelote());
        } else {
            EqList<HandBelote> handsFull_ = new EqList<HandBelote>();
            for (HandBelote h: hands_) {
                handsFull_.add(new HandBelote(h));
            }
            for (TrickBelote t: allTr_) {
                for (CardBelote c: t) {
                    int p_ = t.joueurAyantJoue(c, (byte) nbPl_);
                    handsFull_.get(p_).ajouter(c);
                }
            }
            HandBelote l_ = new HandBelote();
            CardBelote card_ = _g.getDistribution().derniereMain().premiereCarte();
            l_.ajouter(card_);
            byte taker_ = _g.getPreneur();
            for (int i : _g.getRules().getRepartition().getDistributionFin()) {
                int f_ = i - 1;
                for (byte j : _g.orderedPlayers(_g
                        .playerAfter(_g.getDistribution().getDonneur()))) {
                    int k_ = CustList.FIRST_INDEX;
                    int count_ = 0;
                    while (count_ < f_) {
                        CardBelote c_ = handsFull_.get(j).carte(k_);
                        if (c_ == card_) {
                            k_++;
                            continue;
                        }
                        l_.ajouter(c_);
                        k_++;
                        count_++;
                    }
                    if (j != taker_) {
                        while (true) {
                            CardBelote c_ = handsFull_.get(j).carte(k_);
                            if (c_ == card_) {
                                k_++;
                                continue;
                            }
                            l_.ajouter(c_);
                            break;
                        }
                    }
                }
            }
            hands_.add(l_);
        }
        _g.getDeal().setDeal(hands_);
        CheckerGameBeloteWithRules.check(_g);
        if (!_g.getError().isEmpty()) {
            fail("Error");
        }
    }
    protected static int getTaker(RulesBelote _g, int _dealer, CustList<BidBeloteSuit> _bids) {
        byte player_ = _g.getRepartition().getNextPlayer(_dealer);
        int taker_ = CustList.INDEX_NOT_FOUND_ELT;
        BidBeloteSuit bid_ = new BidBeloteSuit();
        for (BidBeloteSuit b: _bids) {
            if (b.strongerThan(bid_)) {
                taker_ = player_;
                bid_ = b;
            }
            player_ = _g.getRepartition().getNextPlayer(player_);
        }
        return taker_;
    }
    protected static GameBeloteTrickInfo newGameBeloteTrickInfo(GameBelote _g, HandBelote _currentHand) {
        check(_g,_currentHand);
        Ints handLengths_ = new Ints();
        int nombreCartesParJoueur_ = _g.getRegles().getRepartition().getNombreCartesParJoueur();
        int nbPl_ = _g.getRegles().getRepartition().getNombreJoueurs();
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
        GameBeloteTrickInfo gameBeloteTrickInfo_ = new GameBeloteTrickInfo(_g.getProgressingTrick(), _g.getTricks(),
                _g.getDeclares(),
                _g.getDeclaresBeloteRebelote(), _g.getContrat(),
                handLengths_);
        gameBeloteTrickInfo_.addSeenDeck(_g.getDistribution().derniereMain(),_g.getTeamsRelation());
        return gameBeloteTrickInfo_;
    }
    protected static GameBeloteTrickInfo newGameBeloteTrickInfo(GameBelote _g) {
        Ints handLengths_ = new Ints();
        int nombreCartesParJoueur_ = _g.getRegles().getRepartition().getNombreCartesParJoueur();
        int nbPl_ = _g.getRegles().getRepartition().getNombreJoueurs();
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
        GameBeloteTrickInfo gameBeloteTrickInfo_ = new GameBeloteTrickInfo(_g.getProgressingTrick(), _g.getTricks(),
                _g.getDeclares(),
                _g.getDeclaresBeloteRebelote(), _g.getContrat(),
                handLengths_);
        gameBeloteTrickInfo_.addSeenDeck(_g.getDistribution().derniereMain(),_g.getTeamsRelation());
        return gameBeloteTrickInfo_;
    }
    protected static void addSureCard(BeloteInfoPliEnCours _info, int _p, CardBelote _c) {
        int nbPl_ = _info.getNbPlayers();
        Suit s_ = _c.couleur();
        HandBelote hc_ = _info.getCartesPossibles().getVal(s_).get(_p);
        if (!hc_.contient(_c)) {
            return;
        }
        for (int i = 0; i < nbPl_; i++) {
            if (i == _p) {
                HandBelote h_ = _info.getCartesCertaines().getVal(s_).get(_p);
                if (!h_.contient(_c)) {
                    h_.ajouter(_c);
                    if (_info.getContrat().getCouleurDominante()) {
                        if (s_ == _info.getCouleurAtout()) {
                            h_.setOrdre(Order.TRUMP);
                        } else {
                            h_.setOrdre(Order.SUIT);
                        }
                    } else if (_info.getContrat().ordreCouleur()) {
                        h_.setOrdre(Order.SUIT);
                    } else {
                        h_.setOrdre(Order.TRUMP);
                    }
                    h_.trierUnicolore(true);
                }
            } else {
                _info.getCartesPossibles().getVal(s_).get(i).removeCardIfPresent(_c);
            }
        }
    }
    protected static void addPossibleCard(BeloteInfoPliEnCours _info, int _p, CardBelote _c) {
        Suit s_ = _c.couleur();
        HandBelote h_ = _info.getCartesPossibles().getVal(s_).get(_p);
        if (h_.contient(_c)) {
            return;
        }
        h_.ajouter(_c);
        if (_info.getContrat().getCouleurDominante()) {
            if (s_ == _info.getCouleurAtout()) {
                h_.setOrdre(Order.TRUMP);
            } else {
                h_.setOrdre(Order.SUIT);
            }
        } else if (_info.getContrat().ordreCouleur()) {
            h_.setOrdre(Order.SUIT);
        } else {
            h_.setOrdre(Order.TRUMP);
        }
        h_.trierUnicolore(true);
    }


    protected static void removePossibleCard(BeloteInfoPliEnCours _info, int _p, CardBelote _c) {
        if (_info.getCartesCertaines().getVal(_c.couleur()).get(_p).contient(_c)) {
            return;
        }
        HandBelote h_ = _info.getCartesPossibles().getVal(_c.couleur()).get(_p);
        h_.removeCardIfPresent(_c);
    }
    protected static void removeSureCard(BeloteInfoPliEnCours _info, int _p, CardBelote _c) {
        HandBelote h_ = _info.getCartesCertaines().getVal(_c.couleur()).get(_p);
        h_.removeCardIfPresent(_c);
    }
}
