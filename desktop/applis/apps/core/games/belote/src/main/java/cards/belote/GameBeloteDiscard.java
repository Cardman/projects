package cards.belote;

import cards.belote.enumerations.*;
import cards.consts.*;
import code.util.*;

public final class GameBeloteDiscard {
    private final int tailleEcart;
    private final HandBelote currentHand;
    private final IdMap<Suit, HandBelote> repartition;
    private final IdMap<Suit, HandBelote> cartesMaitresses;
    private final int nbCartesMaitresses;
    private final int nbCartesCouleurs;
    private final BidBeloteSuit bid;
    private final IdList<Suit> others;
    private final IdList<Suit> othersThenDom;

    public GameBeloteDiscard(GameBeloteBid _i, int _e) {
        currentHand = _i.getCurrentHand();
        bid = _i.getBid();
        repartition = currentHand.couleurs(bid);
        cartesMaitresses = cartesMaitressesDebutPartie(repartition, bid);
        this.tailleEcart = _e;
        nbCartesMaitresses = nbCartesMaitressesEcart();
        nbCartesCouleurs = nbCartesCouleursEcart();
        others = GameBeloteCommonPlaying.couleursNonAtouts(bid);
        othersThenDom = new IdList<Suit>(others);
        for (Suit s: Suit.couleursOrdinaires()) {
            if (s == bid.getSuit()) {
                othersThenDom.add(s);
            }
        }
    }
    
    HandBelote strategieEcart() {
        HandBelote ecart_ = new HandBelote();
        return slamDiscard(ecart_);
    }
    boolean majorDominantCards() {
        return nbCartesMaitresses + tailleEcart >= nbCartesCouleurs;
    }
    private HandBelote slamDiscard(HandBelote _ecart) {
        HandBelote cartesNonMaitresses_ = cartesNonMaitressesDebut(currentHand,
                cartesMaitresses,bid);
        if (majorDominantCards()) {
            _ecart.ajouterCartes(cartesNonMaitresses_);
            if (_ecart.total() == tailleEcart) {
                return _ecart;
            }
            for (Suit s: othersThenDom) {
                HandBelote main_ = HandBelote.nullToEmpty(cartesMaitresses.getVal(s));
                main_.trierUnicolore(false, bid);
                for (CardBelote carte_ : main_) {
                    addIfPossible(_ecart,carte_);
                }
            }
            for (Suit s: Suit.couleursOrdinaires()) {
                HandBelote main_ = HandBelote.nullToEmpty(repartition.getVal(s));
                main_.trierUnicolore(false, bid);
                for (CardBelote carte_ : main_) {
                    addIfPossible(_ecart,carte_);
                }
            }
            return _ecart;
        }
        saveWeakCards(_ecart);
        slamLowCards(_ecart, GameBeloteCommon.couleursSansCarteMaitresse(currentHand, cartesNonMaitresses_, others));
        slamLowCards(_ecart, others);
        slamCharCards(_ecart, others);
        slamCharCards(_ecart, Suit.couleursOrdinaires());
        defSlam(_ecart);
        return _ecart;
    }

    private void saveWeakCards(HandBelote _ecart) {
        HandBelote cartesNonMaitresses_ = cartesNonMaitressesDebut(currentHand,
                cartesMaitresses, bid);
        IdMap<Suit, HandBelote> repNonMaitre_ = cartesNonMaitresses_.couleurs(bid);
        IdList<Suit> couleurs_ = GameBeloteCommon.couleursSansCarteMaitresse(currentHand, cartesNonMaitresses_, others);
        couleurs_ = GameBeloteCommon.couleursAvecPoints(currentHand, bid, couleurs_);
        for(IdList<Suit> suits_: GameBeloteCommon.couleursTrieesPlusCourtes(repartition, couleurs_)) {
            for(Suit couleur_: suits_) {
                HandBelote filter_ = repNonMaitre_.getVal(couleur_).cartesPoints(bid);
                filter_.trierUnicolore(true,bid);
                for(CardBelote carte_: filter_) {
                    addIfPossible(_ecart,carte_);
                }
            }

        }
    }

    private void defSlam(HandBelote _ecart) {
        IdMap<Suit, HandBelote> repartition_ = repartition;
        IdList<Suit> couleurs_ = Suit.couleursOrdinaires();
        for(IdList<Suit> suits_: GameBeloteCommon.couleursTrieesPlusCourtes(repartition_, couleurs_)) {
            for(Suit couleur_: suits_) {
                HandBelote figures_ = repartition_.getVal(couleur_).cartesNonPoints(bid);
                figures_.trierUnicolore(true,bid);
                for(CardBelote carte_: figures_) {
                    addIfPossible(_ecart,carte_);
                }
            }
        }
    }

    private void slamLowCards(HandBelote _ecart, IdList<Suit> _couleurs) {
        HandBelote cartesNonMaitresses_ = cartesNonMaitressesDebut(currentHand,
                cartesMaitresses, bid);
        IdMap<Suit, HandBelote> couleurs_ = cartesNonMaitresses_.couleurs(bid);
        for(IdList<Suit> suits_: GameBeloteCommon.couleursTrieesPlusCourtes(repartition, _couleurs)) {
            for(Suit couleur_: suits_) {
                HandBelote low_  = couleurs_.getVal(couleur_).cartesNonPoints(bid);
                low_.trierUnicolore(false,bid);
                for(CardBelote carte_: low_) {
                    addIfPossible(_ecart,carte_);
                }
            }

        }
    }

    private void slamCharCards(HandBelote _ecart, IdList<Suit> _suits) {
        IdList<Suit> couleurs_ = GameBeloteCommon.couleursAvecPoints(currentHand, bid, _suits);
        IdMap<Suit, HandBelote> repartition_ = repartition;
        for(IdList<Suit> suits_: GameBeloteCommon.couleursTrieesPlusCourtes(repartition_, couleurs_)) {
            for(Suit couleur_: suits_) {
                HandBelote figures_ = repartition_.getVal(couleur_).cartesPoints(bid);
                figures_.trierUnicolore(true,bid);
                for(CardBelote carte_: figures_) {
                    addIfPossible(_ecart,carte_);
                }
            }
        }
    }

    private int nbCartesCouleursEcart() {
        int nbCartesCouleurs_ = 0;
        for (Suit couleur_ : Suit.couleursOrdinaires()) {
            nbCartesCouleurs_ += repartition.getVal(couleur_).total();
        }
        return nbCartesCouleurs_;
    }

    private int nbCartesMaitressesEcart() {
        int nbCartesMaitresses_ = 0;
        for (HandBelote main_ : cartesMaitresses.values()) {
            nbCartesMaitresses_ += main_.total();
        }
        return nbCartesMaitresses_;
    }

    private void addIfPossible(HandBelote _discard, CardBelote _c) {
        if (_discard.total() >= tailleEcart) {
            return;
        }
        if (_discard.contient(_c)) {
            return;
        }
        _discard.ajouter(_c);
    }
    static HandBelote cartesNonMaitressesDebut(
            HandBelote _curHand,
            IdMap<Suit,HandBelote> _cartesMaitresses, BidBeloteSuit _bs) {
        HandBelote cartesNonMaitresses_ = new HandBelote();
        for (Suit couleur_ : Suit.couleursOrdinaires()) {
            HandBelote main_ = _cartesMaitresses.getVal(couleur_);
            HandBelote couleurTotale_ = HandBelote.couleurComplete(couleur_,_bs);
            couleurTotale_.trierUnicolore(true,_bs);
            feed(couleurTotale_, _curHand, main_, cartesNonMaitresses_);
        }
        return cartesNonMaitresses_;
    }

    private static void feed(HandBelote _couleurTotale, HandBelote _curHand, HandBelote _main, HandBelote _cartesNonMaitresses) {
        for (CardBelote carte_ : GameBeloteCommon.inter(_couleurTotale, _curHand)) {
            if (!_main.contient(carte_)) {
                _cartesNonMaitresses.ajouter(carte_);
            }
        }
    }

    static IdMap<Suit,HandBelote> cartesMaitressesDebutPartie(
            IdMap<Suit,HandBelote> _couleurs, BidBeloteSuit _bs) {
        return GameBeloteCommon.cartesMaitresses(_couleurs, new HandBelote().couleurs(_bs), _bs);
    }
}
