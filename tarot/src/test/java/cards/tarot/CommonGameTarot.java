package cards.tarot;

import cards.consts.GameType;
import cards.consts.Hypothesis;
import cards.consts.Suit;
import cards.tarot.enumerations.BidTarot;
import cards.tarot.enumerations.CallingCard;
import cards.tarot.enumerations.CardTarot;
import cards.tarot.enumerations.Miseres;
import code.util.*;

import static org.junit.Assert.fail;

public abstract class CommonGameTarot {
    protected static GameTarot newGameTarotWithourDecl(RulesTarot _r, CustList<TrickTarot> _trs, TrickTarot _prog,
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
    protected static GameTarot newGameTarot(RulesTarot _r, CustList<TrickTarot> _trs,TrickTarot _prog,
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
        for (TrickTarot t: g_.getTricks()) {
            if (!t.getVuParToutJoueur()) {
                continue;
            }
            g_.retrieveCalledPlayers(t);
        }
        byte starter_;
        byte trickWinner_;
        if (_prog.getVuParToutJoueur()) {
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

    protected static GameTarotProgTrickClassic newGameTarotProgTrickClassic(GameTarot _g,GameTarotTrickInfo _done, GameTarotTeamsRelation _teamsRelation,
                                                                            HandTarot _calledCards, HandTarot _currentHand) {
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
        EqList<HandTarot> hands_ = new EqList<HandTarot>();
        GameTarotTrickInfo info_ = new GameTarotTrickInfo(_g.getProgressingTrick(), _g.getTricks(),
                _g.getDeclaresMiseres(),
                _g.getHandfuls(), _g.getContrat(), _g.getCalledCards(),
                handLengths_);
        info_.addSeenDeck(_g.derniereMain(),_g.getTeamsRelation());
        EnumMap<Suit,EqList<HandTarot>> cartesPossibles_ = new EnumMap<Suit,EqList<HandTarot>>();
        EqList<HandTarot> possibleExcuse_ = info_.excusePossibleRegles(_currentHand);
        cartesPossibles_.put(CardTarot.EXCUSE.couleur(), possibleExcuse_);
        cartesPossibles_.put(Suit.TRUMP,info_.atoutsPossiblesRegles(
                _currentHand));
        for (Suit couleur_ : Suit.couleursOrdinaires()) {
            // On fait une boucle sur les
            // couleurs autres que l'atout
            cartesPossibles_.put(couleur_,info_.cartesPossiblesRegles(couleur_,
                    _currentHand));
        }
        for (int i =0;i<nbPl_;i++) {
            for (EntryCust<Suit,EqList<HandTarot>> h: cartesPossibles_.entryList()) {
                h.getValue().get(i).supprimerCartes(_g.getTricks().first().getCartes());
            }
        }
        if (handLengths_.get(_g.getPliEnCours().getNextPlayer((byte) nbPl_)) != _currentHand.total()) {
            fail(StringList.concat("Error len",Integer.toString(handLengths_.get(_g.getPliEnCours().getNextPlayer((byte) nbPl_))),",",Integer.toString(_currentHand.total())));
        }
        CustList<TrickTarot> allTr_ = new CustList<TrickTarot>();
        allTr_.addAllElts(_g.getTricks());
        allTr_.add(_g.getPliEnCours());
        HandTarot hPl_ = new HandTarot();
        for (TrickTarot t: allTr_) {
            hPl_.ajouterCartes(t.getCartes());
        }
        hPl_.ajouterCartes(_currentHand);
        HandTarot hPlCh_ = new HandTarot();
        for (CardTarot c: hPl_) {
            if (hPlCh_.contient(c)) {
                fail(StringList.concat("found ",c.name()));
            }
            hPlCh_.ajouter(c);
        }
        EnumMap<Hypothesis,EnumMap<Suit,EqList<HandTarot>>> hypotheses_ = info_.cartesCertaines(cartesPossibles_);
        cartesPossibles_ = hypotheses_.getVal(Hypothesis.POSSIBLE);
        EnumMap<Suit,EqList<HandTarot>> cartesCertaines_ = hypotheses_
                .getVal(Hypothesis.SURE);
        while (true) {
            int det_ = det(cartesCertaines_, handLengths_);
            if (det_ < 0) {
                break;
            }
            HandTarot all_ = new HandTarot();
            HandTarot del_ = new HandTarot();
            for (EntryCust<Suit,EqList<HandTarot>> h: cartesPossibles_.entryList()) {
                all_.ajouterCartes(h.getValue().get(det_));
            }
            HandTarot curFound_ = new HandTarot();
            for (EntryCust<Suit,EqList<HandTarot>> h: cartesCertaines_.entryList()) {
                curFound_.ajouterCartes(h.getValue().get(det_));
            }
            all_.supprimerCartes(curFound_);
            int req_ = handLengths_.get(det_) - curFound_.total();
            if (req_ >= all_.total()) {
                fail("No enough");
            }
            for (int i = req_; i < all_.total(); i++) {
                del_.ajouter(all_.carte(i));
            }
            for (EntryCust<Suit,EqList<HandTarot>> h: cartesPossibles_.entryList()) {
                h.getValue().get(det_).supprimerCartes(del_);
            }
            hypotheses_ = info_.cartesCertaines(cartesPossibles_);
            cartesPossibles_ = hypotheses_.getVal(Hypothesis.POSSIBLE);
            cartesCertaines_ = hypotheses_
                    .getVal(Hypothesis.SURE);
        }
        for (int i = 0; i < nbPl_; i++) {
            HandTarot h_ = new HandTarot();
            for (EntryCust<Suit,EqList<HandTarot>> h: cartesCertaines_.entryList()) {
                h_.ajouterCartes(h.getValue().get(i));
            }
            hands_.add(h_);
        }
        hands_.add(_g.derniereMain());
        _g.getDeal().setDeal(hands_);
        CheckerGameTarotWithRules.check(_g);
        if (!_g.getError().isEmpty()) {
            fail("Error");
        }
        return new GameTarotProgTrickClassic(_done,_teamsRelation,_calledCards,_currentHand);
    }

    private static int det(EnumMap<Suit,EqList<HandTarot>> _foundHands, Numbers<Integer> _lengths) {
        int nb_ = _lengths.size();
        for (int i = 0;i < nb_; i++) {
            int s_ = 0;
            for (EntryCust<Suit,EqList<HandTarot>> h: _foundHands.entryList()) {
                s_ += h.getValue().get(i).total();
            }
            if (s_ != _lengths.get(i)) {
                return i;
            }
        }
        return -1;
    }
    protected static GameTarotBeginTrickClassic newGameTarotBeginTrickClassic(GameTarot _g,GameTarotTrickInfo _done, GameTarotTeamsRelation _teamsRelation,
                                                                              HandTarot _calledCards, HandTarot _currentHand) {
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
        EqList<HandTarot> hands_ = new EqList<HandTarot>();
        GameTarotTrickInfo info_ = new GameTarotTrickInfo(_g.getProgressingTrick(), _g.getTricks(),
                _g.getDeclaresMiseres(),
                _g.getHandfuls(), _g.getContrat(), _g.getCalledCards(),
                handLengths_);
        info_.addSeenDeck(_g.derniereMain(),_g.getTeamsRelation());
        EnumMap<Suit,EqList<HandTarot>> cartesPossibles_ = new EnumMap<Suit,EqList<HandTarot>>();
        EqList<HandTarot> possibleExcuse_ = info_.excusePossibleRegles(_currentHand);
        cartesPossibles_.put(CardTarot.EXCUSE.couleur(), possibleExcuse_);
        cartesPossibles_.put(Suit.TRUMP,info_.atoutsPossiblesRegles(
                _currentHand));
        for (Suit couleur_ : Suit.couleursOrdinaires()) {
            // On fait une boucle sur les
            // couleurs autres que l'atout
            cartesPossibles_.put(couleur_,info_.cartesPossiblesRegles(couleur_,
                    _currentHand));
        }
        for (int i =0;i<nbPl_;i++) {
            for (EntryCust<Suit,EqList<HandTarot>> h: cartesPossibles_.entryList()) {
                h.getValue().get(i).supprimerCartes(_g.getTricks().first().getCartes());
            }
        }
        CustList<TrickTarot> allTr_ = new CustList<TrickTarot>();
        allTr_.addAllElts(_g.getTricks());
        allTr_.add(_g.getPliEnCours());
        HandTarot hPl_ = new HandTarot();
        for (TrickTarot t: allTr_) {
            hPl_.ajouterCartes(t.getCartes());
        }
        hPl_.ajouterCartes(_currentHand);
        HandTarot hPlCh_ = new HandTarot();
        for (CardTarot c: hPl_) {
            if (hPlCh_.contient(c)) {
                fail(StringList.concat("found ",c.name()));
            }
            hPlCh_.ajouter(c);
        }
        if (handLengths_.get(_g.getPliEnCours().getNextPlayer((byte) nbPl_)) != _currentHand.total()) {
            fail(StringList.concat("Error len",Integer.toString(handLengths_.get(_g.getPliEnCours().getNextPlayer((byte) nbPl_))),",",Integer.toString(_currentHand.total())));
        }
        EnumMap<Hypothesis,EnumMap<Suit,EqList<HandTarot>>> hypotheses_ = info_.cartesCertaines(cartesPossibles_);
        cartesPossibles_ = hypotheses_.getVal(Hypothesis.POSSIBLE);
        EnumMap<Suit,EqList<HandTarot>> cartesCertaines_ = hypotheses_
                .getVal(Hypothesis.SURE);
        while (true) {
            int det_ = det(cartesCertaines_, handLengths_);
            if (det_ < 0) {
                break;
            }
            HandTarot all_ = new HandTarot();
            HandTarot del_ = new HandTarot();
            for (EntryCust<Suit,EqList<HandTarot>> h: cartesPossibles_.entryList()) {
                all_.ajouterCartes(h.getValue().get(det_));
            }
            HandTarot curFound_ = new HandTarot();
            for (EntryCust<Suit,EqList<HandTarot>> h: cartesCertaines_.entryList()) {
                curFound_.ajouterCartes(h.getValue().get(det_));
            }
            all_.supprimerCartes(curFound_);
            int req_ = handLengths_.get(det_) - curFound_.total();
            if (req_ >= all_.total()) {
                fail("No enough");
            }
            for (int i = req_; i < all_.total(); i++) {
                del_.ajouter(all_.carte(i));
            }
            for (EntryCust<Suit,EqList<HandTarot>> h: cartesPossibles_.entryList()) {
                h.getValue().get(det_).supprimerCartes(del_);
            }
            hypotheses_ = info_.cartesCertaines(cartesPossibles_);
            cartesPossibles_ = hypotheses_.getVal(Hypothesis.POSSIBLE);
            cartesCertaines_ = hypotheses_
                    .getVal(Hypothesis.SURE);
        }
        for (int i = 0; i < nbPl_; i++) {
            HandTarot h_ = new HandTarot();
            for (EntryCust<Suit,EqList<HandTarot>> h: cartesCertaines_.entryList()) {
                h_.ajouterCartes(h.getValue().get(i));
            }
            hands_.add(h_);
        }
        hands_.add(_g.derniereMain());
        _g.getDeal().setDeal(hands_);
        CheckerGameTarotWithRules.check(_g);
        if (!_g.getError().isEmpty()) {
            fail("Error");
        }
        return new GameTarotBeginTrickClassic(_done,_teamsRelation,_calledCards,_currentHand);
    }
    protected static void faireConfiance(GameTarot _g, byte _p) {
        byte n_ = _g.getProgressingTrick().getNextPlayer(_g.getNombreDeJoueurs());
        _g.getTeamsRelation().faireConfiance(n_, _p);
    }
    protected static int getTaker(RulesTarot _g, int _dealer, CustList<BidTarot> _bids) {
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
    protected static GameTarotTrickInfo newGameTarotTrickInfo(GameTarot _g, HandTarot _currentHand) {
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
        GameTarotTrickInfo gameTarotTrickInfo_ = new GameTarotTrickInfo(_g.getProgressingTrick(), _g.getTricks(),
                _g.getDeclaresMiseres(),
                _g.getHandfuls(), _g.getContrat(), _g.getCalledCards(),
                handLengths_);
        gameTarotTrickInfo_.addSeenDeck(_g.derniereMain(),_g.getTeamsRelation());
        EnumMap<Suit,EqList<HandTarot>> cartesPossibles_ = new EnumMap<Suit,EqList<HandTarot>>();
        EqList<HandTarot> possibleExcuse_ = gameTarotTrickInfo_.excusePossibleRegles(_currentHand);
        cartesPossibles_.put(CardTarot.EXCUSE.couleur(), possibleExcuse_);
        cartesPossibles_.put(Suit.TRUMP,gameTarotTrickInfo_.atoutsPossiblesRegles(
                _currentHand));
        for (Suit couleur_ : Suit.couleursOrdinaires()) {
            // On fait une boucle sur les
            // couleurs autres que l'atout
            cartesPossibles_.put(couleur_,gameTarotTrickInfo_.cartesPossiblesRegles(couleur_,
                    _currentHand));
        }
        for (int i =0;i<nbPl_;i++) {
            for (EntryCust<Suit,EqList<HandTarot>> h: cartesPossibles_.entryList()) {
                h.getValue().get(i).supprimerCartes(_g.getTricks().first().getCartes());
            }
        }
        if (handLengths_.get(_g.getPliEnCours().getNextPlayer((byte) nbPl_)) != _currentHand.total()) {
            fail(StringList.concat("Error len",Integer.toString(handLengths_.get(_g.getPliEnCours().getNextPlayer((byte) nbPl_))),",",Integer.toString(_currentHand.total())));
        }
        CustList<TrickTarot> allTr_ = new CustList<TrickTarot>();
        allTr_.addAllElts(_g.getTricks());
        allTr_.add(_g.getPliEnCours());
        HandTarot hPl_ = new HandTarot();
        for (TrickTarot t: allTr_) {
            hPl_.ajouterCartes(t.getCartes());
        }
        hPl_.ajouterCartes(_currentHand);
        HandTarot hPlCh_ = new HandTarot();
        for (CardTarot c: hPl_) {
            if (hPlCh_.contient(c)) {
                fail(StringList.concat("found ",c.name()));
            }
            hPlCh_.ajouter(c);
        }
        EnumMap<Hypothesis,EnumMap<Suit,EqList<HandTarot>>> hypotheses_ = gameTarotTrickInfo_.cartesCertaines(cartesPossibles_);
        cartesPossibles_ = hypotheses_.getVal(Hypothesis.POSSIBLE);
        EnumMap<Suit,EqList<HandTarot>> cartesCertaines_ = hypotheses_
                .getVal(Hypothesis.SURE);
        while (true) {
            int det_ = det(cartesCertaines_, handLengths_);
            if (det_ < 0) {
                break;
            }
            HandTarot all_ = new HandTarot();
            HandTarot del_ = new HandTarot();
            for (EntryCust<Suit,EqList<HandTarot>> h: cartesPossibles_.entryList()) {
                all_.ajouterCartes(h.getValue().get(det_));
            }
            HandTarot curFound_ = new HandTarot();
            for (EntryCust<Suit,EqList<HandTarot>> h: cartesCertaines_.entryList()) {
                curFound_.ajouterCartes(h.getValue().get(det_));
            }
            all_.supprimerCartes(curFound_);
            int req_ = handLengths_.get(det_) - curFound_.total();
            if (req_ >= all_.total()) {
                fail("No enough");
            }
            for (int i = req_; i < all_.total(); i++) {
                del_.ajouter(all_.carte(i));
            }
            for (EntryCust<Suit,EqList<HandTarot>> h: cartesPossibles_.entryList()) {
                h.getValue().get(det_).supprimerCartes(del_);
            }
            hypotheses_ = gameTarotTrickInfo_.cartesCertaines(cartesPossibles_);
            cartesPossibles_ = hypotheses_.getVal(Hypothesis.POSSIBLE);
            cartesCertaines_ = hypotheses_
                    .getVal(Hypothesis.SURE);
        }
        EqList<HandTarot> hands_ = new EqList<HandTarot>();
        for (int i = 0; i < nbPl_; i++) {
            HandTarot h_ = new HandTarot();
            for (EntryCust<Suit,EqList<HandTarot>> h: cartesCertaines_.entryList()) {
                h_.ajouterCartes(h.getValue().get(i));
            }
            hands_.add(h_);
        }
        hands_.add(_g.derniereMain());
        _g.getDeal().setDeal(hands_);
        CheckerGameTarotWithRules.check(_g);
        if (!_g.getError().isEmpty()) {
            fail("Error");
        }
        return gameTarotTrickInfo_;
    }
    protected static GameTarotTrickInfo newGameTarotTrickInfo(GameTarot _g) {
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
        GameTarotTrickInfo gameTarotTrickInfo_ = new GameTarotTrickInfo(_g.getProgressingTrick(), _g.getTricks(),
                _g.getDeclaresMiseres(),
                _g.getHandfuls(), _g.getContrat(), _g.getCalledCards(),
                handLengths_);
        gameTarotTrickInfo_.addSeenDeck(_g.derniereMain(),_g.getTeamsRelation());
        return gameTarotTrickInfo_;
    }
    protected static void addSureCard(TarotInfoPliEnCours _info, int _p, CardTarot _c) {
        int nbPl_ = _info.getNbPlayers();
        HandTarot hc_ = _info.getCartesPossibles().getVal(_c.couleur()).get(_p);
        if (!hc_.contient(_c)) {
            return;
        }
        for (int i = 0; i < nbPl_; i++) {
            if (i == _p) {
                HandTarot h_ = _info.getCartesCertaines().getVal(_c.couleur()).get(_p);
                if (!h_.contient(_c)) {
                    h_.ajouter(_c);
                    h_.trierParForceEnCours(_c.couleur());
                }
            } else {
                _info.getCartesPossibles().getVal(_c.couleur()).get(i).removeCardIfPresent(_c);
            }
        }
    }
    protected static void addPossibleCard(TarotInfoPliEnCours _info, int _p, CardTarot _c) {
        HandTarot h_ = _info.getCartesPossibles().getVal(_c.couleur()).get(_p);
        if (h_.contient(_c)) {
            return;
        }
        h_.ajouter(_c);
        h_.trierParForceEnCours(_c.couleur());
    }


    protected static void removePossibleCard(TarotInfoPliEnCours _info, int _p, CardTarot _c) {
        if (_info.getCartesCertaines().getVal(_c.couleur()).get(_p).contient(_c)) {
            return;
        }
        HandTarot h_ = _info.getCartesPossibles().getVal(_c.couleur()).get(_p);
        h_.removeCardIfPresent(_c);
    }
    protected static void removeSureCard(TarotInfoPliEnCours _info, int _p, CardTarot _c) {
        HandTarot h_ = _info.getCartesCertaines().getVal(_c.couleur()).get(_p);
        h_.removeCardIfPresent(_c);
    }
}
