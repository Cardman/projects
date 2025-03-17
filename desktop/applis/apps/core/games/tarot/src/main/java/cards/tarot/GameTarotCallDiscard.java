package cards.tarot;

import cards.consts.CardChar;
import cards.consts.Suit;
import cards.tarot.comparators.CalledSuitComparator;
import cards.tarot.enumerations.CardTarot;
import code.util.IdList;
import code.util.IdMap;
import code.util.core.IndexConstants;

public final class GameTarotCallDiscard {
    private final GameTarotBid infosBid;
    private final int tailleChien;

    public GameTarotCallDiscard(GameTarotBid _infosBid, int _tailleChien) {
        infosBid = _infosBid;
        tailleChien = _tailleChien;
    }

    HandTarot strategieAppel() {
        HandTarot curHand_ = infosBid.getCurrentHand();
        IdMap<Suit,HandTarot> repartition_ = curHand_.couleurs();
        if (GameTarotBid.estUnJeuDeChelemSur(repartition_, new HandTarot().couleurs())) {
            return slam();
        }
        HandTarot rois_ = curHand_.figuresMain(CardChar.KING);
        HandTarot roisAppeler_ = infosBid.callableCards().figuresMain(CardChar.KING);
        if (rois_.total() >= roisAppeler_.total()) {
            HandTarot carteChoisie_ = new HandTarot();
            carteChoisie_.ajouter(couleurAappeler(infosBid.callableCards(), curHand_));
            return carteChoisie_;
        }
        //0 <= rois.total() && rois.total() < roisAppeler.total()
        // ==> 0 < roisAppeler.total() ==> !roisAppeler.estVide()
        //il manque au moins un roi pour le preneur
        //il faut appeler un roi
        int nbAtoutLimite_ = curHand_.total() / 2;
        int nbAtouts_ = curHand_.couleur(Suit.TRUMP).total();
        IdList<Suit> couleurs_;
        IdList<Suit> couleursAppelables_ = GameTarotCommon.couleursNonAtoutNonVides(roisAppeler_, Suit.couleursOrdinaires());
        if (nbAtouts_ > nbAtoutLimite_) {
            couleurs_ = GameTarotCommon.couleursNonAtoutNonVides(curHand_, couleursAppelables_);
            IdList<Suit> couleursSansRoi_ = GameTarotCommon.couleursPLonguePHaute(curHand_,GameTarotCommon.couleursSansCartes(curHand_,
                    rois_,
                    couleurs_));
            //couleurs avec roi ou couleurs vides et pas tous les rois => au moins une couleur vide
            couleursSansRoi_.addAllElts(GameTarotCommon.complementaireCouleurs(GameTarotCommon.intersectionCouleurs(couleursAppelables_,GameTarotCommon.couleursNonAtoutAyantNbCartesInfEg(curHand_, couleursAppelables_, 0)),couleursSansRoi_));
            couleursSansRoi_.addAllElts(GameTarotCommon.complementaireCouleurs(couleursAppelables_,couleursSansRoi_));
            return HandTarot.figureCouleur(couleursSansRoi_.first(), CardChar.KING);
        }
        couleurs_ = GameTarotCommon.couleursNonAtoutAyantNbCartesInfEg(curHand_,
                couleursAppelables_, 3);
        IdList<Suit> couleursCourtesSansRoi_ = GameTarotCommon.couleursNonAtoutNonVides(curHand_, GameTarotCommon.couleursSansCartes(curHand_,
                rois_,
                couleurs_));
        if (!couleursCourtesSansRoi_.isEmpty()) {
            // il existe une couleur ayant moins de trois cartes sans roi
            IdList<Suit> couleursAvecFigues_ = GameTarotCommon.couleursPLonguePHaute(
                    curHand_, GameTarotCommon.couleursAvecFigures(
                    curHand_, couleursCourtesSansRoi_));
            couleursAvecFigues_.addAllElts(GameTarotCommon.complementaireCouleurs(GameTarotCommon.couleursPLongueMHaute(curHand_,
                    couleursCourtesSansRoi_),couleursAvecFigues_));
            return HandTarot.figureCouleur(couleursAvecFigues_.first(), CardChar.KING);
        }
        IdList<Suit> couleursSansRoi_ = GameTarotCommon.couleursNonAtoutNonVides(curHand_, GameTarotCommon.couleursSansCartes(curHand_,
                rois_,
                couleursAppelables_));
        if (couleursSansRoi_.isEmpty()) {
            //couleurs avec roi ou couleurs vides et pas tous les rois => au moins une couleur vide
            return HandTarot.figureCouleur(GameTarotCommon.couleursNonAtoutAyantNbCartesInfEg(
                    curHand_, couleursAppelables_, 0).first(), CardChar.KING);
        }
        IdList<Suit> couleursAvecFigues_ = GameTarotCommon.couleursPHauteMLongue(
                curHand_, GameTarotCommon.couleursAvecFigures(
                curHand_, couleursSansRoi_));
        couleursAvecFigues_.addAllElts(GameTarotCommon.complementaireCouleurs(GameTarotCommon.couleursMLongueMHaute(
                curHand_, couleursSansRoi_),couleursAvecFigues_));
        return HandTarot.figureCouleur(couleursAvecFigues_.first(), CardChar.KING);

    }

    private HandTarot slam() {
        HandTarot curHand_ = infosBid.getCurrentHand();
        HandTarot figuresPreneur_ = curHand_.figures();
        HandTarot cartesAppelerChelem_ = new HandTarot();
        for(CardTarot c: infosBid.callableCards()) {
            if(figuresPreneur_.contient(c)) {
                cartesAppelerChelem_.ajouter(c);
                return cartesAppelerChelem_;
            }
        }
        HandTarot h_ = new HandTarot();
        h_.ajouter(infosBid.callableCards().premiereCarte());
        return h_;
    }

    static CardTarot couleurAappeler(
            HandTarot _cartesAppeler, HandTarot _mainPreneur) {
        IdList<CardTarot> couleurs_ = new IdList<CardTarot>();
        couleurs_.addAllElts(_cartesAppeler.getCards());
        couleurs_.sortElts(new CalledSuitComparator(_cartesAppeler,_mainPreneur));
        return couleurs_.first();
    }

    HandTarot strategieEcart(HandTarot _carteAppelee) {
        HandTarot mainPreneur_ = infosBid.getCurrentHand();
        IdMap<Suit,HandTarot> repartition_ = mainPreneur_.couleurs();
        int nombreJoueurs_ = infosBid.getNombreDeJoueurs();
        HandTarot ecartables_ = getCartesEcartables(tailleChien, repartition_);
        IdMap<Suit,HandTarot> repEcartables_ = ecartables_.couleurs();
        HandTarot ecart_ = new HandTarot();
        Suit couleurAtout_ = Suit.TRUMP;
        if (!repEcartables_.getVal(couleurAtout_).estVide()) {
            // Si des atouts sont a ecarter
            for (Suit couleur_ : Suit.couleursOrdinaires()) {
                ecart_.ajouterCartes(repEcartables_.getVal(couleur_));
            }
            int reste_ = tailleChien - ecart_.total();
            HandTarot atoutsEcartables_ = repEcartables_.getVal(couleurAtout_);
            int nbAtoutsEcartables_ = atoutsEcartables_.total();
            for (int carte_ = IndexConstants.FIRST_INDEX; carte_ < reste_; carte_++) {
                ecart_.ajouter(atoutsEcartables_.carte(nbAtoutsEcartables_ - 1
                        - carte_));
            }
            return ecart_;
        }
        if (ecartables_.total() == tailleChien) {
            return ecartables_;
        }
        saveTrumpAce(_carteAppelee, ecart_);
        if(GameTarotBid.maitreAtoutPourChelem(repartition_,nombreJoueurs_)) {
            return slamDiscard(_carteAppelee, ecartables_, ecart_);
        }
        discardChars(_carteAppelee, mainPreneur_, ecartables_, ecart_);
        fullDiscardable(_carteAppelee,mainPreneur_, ecartables_, ecart_);
        withoutFirst(mainPreneur_, ecartables_, ecart_);
        notLeading(_carteAppelee, mainPreneur_, ecartables_, ecart_);
        defDiscard(mainPreneur_, ecartables_, ecart_);
        return ecart_;
    }

    private void discardChars(HandTarot _carteAppelee, HandTarot _mainPreneur, HandTarot _ecartables, HandTarot _ecart) {
        IdList<Suit> others_ = GameTarotCommon.couleursNonAtoutAyantNbCartesInfEg(_carteAppelee,Suit.couleursOrdinaires(),0);
        IdList<Suit> couleurs_ = GameTarotCommon.couleursSansRoi(_mainPreneur, others_);
        couleurs_ = GameTarotCommon.couleursAvecFigures(_mainPreneur, couleurs_);
        for(IdList<Suit> suits_: GameTarotCommon.couleursTrieesPlusCourtes(_mainPreneur, couleurs_)) {
            for(Suit couleur_: suits_) {
                HandTarot figures_ = _mainPreneur.charCardsBySuit(couleur_);
                figures_.trierParForceEnCours(couleur_);
                for(CardTarot carte_: figures_) {
                    addIfPossible(_ecart,carte_, _ecartables);
                }
            }
        }
    }

    private void fullDiscardable(HandTarot _carteAppelee,HandTarot _mainPreneur, HandTarot _ecartables, HandTarot _ecart) {
        IdList<Suit> others_ = GameTarotCommon.couleursNonAtoutAyantNbCartesInfEg(_carteAppelee,Suit.couleursOrdinaires(),0);
        IdList<Suit> couleurs_;
        couleurs_ = couleursTotalEcartables(_mainPreneur, tailleChien, _ecart, others_);
        for(IdList<Suit> suits_: GameTarotCommon.couleursTrieesPlusCourtes(_mainPreneur, couleurs_)) {
            for(IdList<Suit> couleurs2_: GameTarotCommon.couleursTrieesPlusHautes(_mainPreneur, suits_)) {
                for(Suit s_: couleurs2_) {
                    HandTarot couleur_ = _mainPreneur.couleur(s_);
                    for(CardTarot carte_: couleur_) {
                        addIfPossible(_ecart,carte_, _ecartables);
                    }
                }
            }
        }
    }

    private void withoutFirst(HandTarot _mainPreneur, HandTarot _ecartables, HandTarot _ecart) {
        IdList<Suit> couleurs_;
        couleurs_ = GameTarotCommon.couleursSansRoi(_mainPreneur, Suit.couleursOrdinaires());
        for(IdList<Suit> suits_: GameTarotCommon.couleursTrieesPlusCourtes(_mainPreneur, couleurs_)) {
            for(Suit couleur_: suits_) {
                HandTarot cartesBasses_ = _mainPreneur.cartesBasses(couleur_);
                cartesBasses_.trierParForceEcart(couleur_);
                for(CardTarot carte_: cartesBasses_) {
                    addIfPossible(_ecart,carte_, _ecartables);
                }
            }
        }
    }

    private void notLeading(HandTarot _carteAppelee, HandTarot _mainPreneur, HandTarot _ecartables, HandTarot _ecart) {
        IdMap<Suit,HandTarot> repartition_ = _mainPreneur.couleurs();
        IdMap<Suit,HandTarot> cartesMaitresses_ = cartesMaitressesDebutPartie(repartition_);
        HandTarot cartesPseudosMaitres_ = cartesPseudosMaitres(repartition_, couleursAppelees(_carteAppelee), carteAppelee(_carteAppelee));
        HandTarot cartesNonMaitresses_ = cartesNonMaitressesDebut(_mainPreneur,
                cartesMaitresses_, _carteAppelee, cartesPseudosMaitres_);
        IdList<Suit> couleurs_;
        couleurs_ = Suit.couleursOrdinaires();
        for(IdList<Suit> suits_: GameTarotCommon.couleursTrieesPlusCourtes(_mainPreneur, couleurs_)) {
//            HandTarot cartesNonMaitresses_ = cartesNonMaitressesDebut(_mainPreneur,
//                    cartesMaitresses_, _carteAppelee, cartesPseudosMaitres_);
            for(Suit couleur_: suits_) {
                HandTarot filter_ = cartesNonMaitresses_.charCardsBySuit(couleur_);
                filter_.trierParForceEcart(couleur_);
                for(CardTarot carte_: filter_) {
                    addIfPossible(_ecart,carte_, _ecartables);
                }
            }
        }
        for(IdList<Suit> suits_: GameTarotCommon.couleursTrieesPlusCourtes(_mainPreneur, couleurs_)) {
//            HandTarot cartesNonMaitresses_ = cartesNonMaitressesDebut(_mainPreneur,
//                    cartesMaitresses_, _carteAppelee, cartesPseudosMaitres_);
            for(Suit couleur_: suits_) {
                HandTarot filter_ = cartesNonMaitresses_.cartesBasses(couleur_);
                filter_.trierParForceEcart(couleur_);
                for(CardTarot carte_: filter_) {
                    addIfPossible(_ecart,carte_, _ecartables);
                }
            }
        }
    }

    private void defDiscard(HandTarot _mainPreneur, HandTarot _ecartables, HandTarot _ecart) {
        IdList<Suit> couleurs_;
        couleurs_ = Suit.couleursOrdinaires();
        for(IdList<Suit> suits_: GameTarotCommon.couleursTrieesPlusCourtes(_mainPreneur, couleurs_)) {
            for(Suit couleur_: suits_) {
                HandTarot cartesBasses_ = _mainPreneur.charCardsBySuit(couleur_);
                cartesBasses_.trierParForceEcart(couleur_);
                for(CardTarot carte_: cartesBasses_) {
                    addIfPossible(_ecart,carte_, _ecartables);
                }
            }
        }
        for(IdList<Suit> suits_: GameTarotCommon.couleursTrieesPlusCourtes(_mainPreneur, couleurs_)) {
            for(Suit couleur_: suits_) {
                HandTarot cartesBasses_ = _mainPreneur.cartesBasses(couleur_);
                cartesBasses_.trierParForceEcart(couleur_);
                for(CardTarot carte_: cartesBasses_) {
                    addIfPossible(_ecart,carte_, _ecartables);
                }
            }
        }
    }

    private void saveTrumpAce(HandTarot _carteAppelee, HandTarot _ecart) {
        HandTarot mainPreneur_ = infosBid.getCurrentHand();
        if (toDiscardSuit() && mainPreneur_.contient(CardTarot.petit())) {
            //si le preneur n'est pas maitre de l'atout au debut du jeu
            IdList<Suit> couleursEntieresEcartables_ = couleursTotalEcartables(
                    mainPreneur_, tailleChien, _ecart,
                    Suit.couleursOrdinaires());
            if (!couleursEntieresEcartables_.isEmpty()) {
                IdList<Suit> others_ = GameTarotCommon.couleursNonAtoutAyantNbCartesInfEg(_carteAppelee,Suit.couleursOrdinaires(),0);
                //Sauver le PETIT sur une coupe franche courte
                couleursEntieresEcartables_ =
                        GameTarotCommon.couleursAvecFigures(mainPreneur_, couleursEntieresEcartables_);
                couleursEntieresEcartables_ =
                        GameTarotCommon.intersectionCouleurs(couleursEntieresEcartables_, others_);
                if (!couleursEntieresEcartables_.isEmpty()) {
                    //couleurs non appelees entierement ecartables avec figures
                    couleursEntieresEcartables_ = GameTarotCommon.couleursLesPlusCourtes(mainPreneur_, couleursEntieresEcartables_);
                    couleursEntieresEcartables_ = GameTarotCommon.couleursLesPlusHautes(mainPreneur_, couleursEntieresEcartables_);
                    _ecart.ajouterCartes(mainPreneur_.couleur(couleursEntieresEcartables_.first()));
                } else {
                    couleursEntieresEcartables_ = couleursTotalEcartables(
                            mainPreneur_, tailleChien, _ecart,
                            Suit.couleursOrdinaires());
                    couleursEntieresEcartables_ =
                            GameTarotCommon.intersectionCouleurs(couleursEntieresEcartables_, others_);
                    if (!couleursEntieresEcartables_.isEmpty()) {
                        //couleurs non appelees entierement ecartables
                        couleursEntieresEcartables_ = GameTarotCommon.couleursLesPlusCourtes(mainPreneur_, couleursEntieresEcartables_);
                        couleursEntieresEcartables_ = GameTarotCommon.couleursLesPlusBasses(mainPreneur_, couleursEntieresEcartables_);
                        _ecart.ajouterCartes(mainPreneur_.couleur(couleursEntieresEcartables_.first()));
                    } else {
                        couleursEntieresEcartables_ = couleursTotalEcartables(
                                mainPreneur_, tailleChien, _ecart,
                                Suit.couleursOrdinaires());
                        couleursEntieresEcartables_ = GameTarotCommon.couleursLesPlusCourtes(mainPreneur_, couleursEntieresEcartables_);
                        couleursEntieresEcartables_ = GameTarotCommon.couleursLesPlusHautes(mainPreneur_, couleursEntieresEcartables_);
                        _ecart.ajouterCartes(mainPreneur_.couleur(couleursEntieresEcartables_.first()));
                    }
                }
            }
        }
    }

    private boolean toDiscardSuit() {
        HandTarot mainPreneur_ = infosBid.getCurrentHand();
        IdMap<Suit,HandTarot> repartition_ = mainPreneur_.couleurs();
        return GameTarotCommon.nombreDeCoupesFranches(repartition_) == 0 && !GameTarotBid.maitreAtoutPourChelem(repartition_, infosBid.getNombreDeJoueurs());
    }

    private HandTarot slamDiscard(HandTarot _carteAppelee, HandTarot _ecartables, HandTarot _ecart) {
        IdList<Suit> others_ = GameTarotCommon.couleursNonAtoutAyantNbCartesInfEg(_carteAppelee,Suit.couleursOrdinaires(),0);
        HandTarot mainPreneur_ = infosBid.getCurrentHand();
        IdMap<Suit,HandTarot> repartition_ = mainPreneur_.couleurs();
        IdMap<Suit,HandTarot> cartesMaitresses_ = cartesMaitressesDebutPartie(repartition_);
        IdList<Suit> couleursAppelees_ = couleursAppelees(_carteAppelee);
        HandTarot carteAppelee_ = carteAppelee(_carteAppelee);
        HandTarot cartesPseudosMaitres_ = cartesPseudosMaitres(repartition_, couleursAppelees_, carteAppelee_);
        int nbCartesMaitresses_ = nbCartesMaitressesEcart(_carteAppelee, cartesMaitresses_, couleursAppelees_, cartesPseudosMaitres_);
        int nbCartesCouleurs_ = nbCartesCouleursEcart(repartition_);
        if (nbCartesMaitresses_ + tailleChien >= nbCartesCouleurs_) {
            HandTarot cartesNonMaitresses_ = cartesNonMaitressesDebut(mainPreneur_,
                    cartesMaitresses_, carteAppelee_, cartesPseudosMaitres_);
//            _ecart.ajouterCartes(cartesNonMaitresses_);
//            if (_ecart.total() == tailleChien) {
//                return _ecart;
//            }
            for (CardTarot carte_ : cartesNonMaitresses_) {
                addIfPossible(_ecart,carte_, _ecartables);
            }
            //cartesPseudosMaitres est suppose etre trie decroissant
            for (CardTarot carte_ : cartesPseudosMaitres_) {
                addIfPossible(_ecart,carte_, _ecartables);
            }
            for (HandTarot main_ : cartesMaitresses_.values()) {
                for (CardTarot carte_ : main_) {
                    addIfPossible(_ecart,carte_, _ecartables);
                }
            }
            return _ecart;
        }
        saveWeakCards(_ecartables, _ecart, mainPreneur_, cartesMaitresses_, carteAppelee_, cartesPseudosMaitres_);
        slamLowCards(_ecartables, _ecart, mainPreneur_, cartesMaitresses_, carteAppelee_, cartesPseudosMaitres_, GameTarotCommon.couleursSansRoi(mainPreneur_, Suit.couleursOrdinaires()));
        slamLowCards(_ecartables, _ecart, mainPreneur_, cartesMaitresses_, carteAppelee_, cartesPseudosMaitres_, Suit.couleursOrdinaires());
        slamCharCards(_ecartables, _ecart, mainPreneur_, others_);
        slamCharCards(_ecartables, _ecart, mainPreneur_, Suit.couleursOrdinaires());
        defSlam(_ecartables, _ecart, mainPreneur_);
        return _ecart;
    }

    private void saveWeakCards(HandTarot _ecartables, HandTarot _ecart, HandTarot _mainPreneur, IdMap<Suit, HandTarot> _cartesMaitresses, HandTarot _carteAppelee, HandTarot _cartesPseudosMaitres) {
        IdList<Suit> couleurs_ = GameTarotCommon.couleursSansRoi(_mainPreneur, Suit.couleursOrdinaires());
        couleurs_ = GameTarotCommon.couleursAvecFigures(_mainPreneur, couleurs_);
        HandTarot cartesNonMaitresses_ = cartesNonMaitressesDebut(_mainPreneur,
                _cartesMaitresses, _carteAppelee, _cartesPseudosMaitres);
        for(IdList<Suit> suits_: GameTarotCommon.couleursTrieesPlusCourtes(_mainPreneur, couleurs_)) {
//            HandTarot cartesNonMaitresses_ = cartesNonMaitressesDebut(_mainPreneur,
//                    _cartesMaitresses, _carteAppelee, _cartesPseudosMaitres);
            for(Suit couleur_: suits_) {
                HandTarot filter_ = cartesNonMaitresses_.charCardsBySuit(couleur_);
                filter_.trierParForceEnCours(couleur_);
                for(CardTarot carte_: filter_) {
                    addIfPossible(_ecart,carte_, _ecartables);
                }
            }

        }
    }

    private void defSlam(HandTarot _ecartables, HandTarot _ecart, HandTarot _mainPreneur) {
        IdList<Suit> couleurs_;
        couleurs_ = Suit.couleursOrdinaires();
        for(IdList<Suit> suits_: GameTarotCommon.couleursTrieesPlusCourtes(_mainPreneur, couleurs_)) {
            for(Suit couleur_: suits_) {
                HandTarot figures_ = _mainPreneur.cartesBasses(couleur_);
                figures_.trierParForceEnCours(couleur_);
                for(CardTarot carte_: figures_) {
                    addIfPossible(_ecart,carte_, _ecartables);
                }
            }
        }
    }

    private void slamLowCards(HandTarot _ecartables, HandTarot _ecart, HandTarot _mainPreneur, IdMap<Suit, HandTarot> _cartesMaitresses, HandTarot _carteAppelee, HandTarot _cartesPseudosMaitres, IdList<Suit> _couleurs) {
        HandTarot cartesNonMaitresses_ = cartesNonMaitressesDebut(_mainPreneur,
                _cartesMaitresses, _carteAppelee, _cartesPseudosMaitres);
        for(IdList<Suit> suits_: GameTarotCommon.couleursTrieesPlusCourtes(_mainPreneur, _couleurs)) {
//            HandTarot cartesNonMaitresses_ = cartesNonMaitressesDebut(_mainPreneur,
//                    _cartesMaitresses, _carteAppelee, _cartesPseudosMaitres);
            for(Suit couleur_: suits_) {
                HandTarot filter_ = cartesNonMaitresses_.cartesBasses(couleur_);
                filter_.trierParForceEcart(couleur_);
                for(CardTarot carte_: filter_) {
                    addIfPossible(_ecart,carte_, _ecartables);
                }
            }

        }
    }

    private void slamCharCards(HandTarot _ecartables, HandTarot _ecart, HandTarot _mainPreneur, IdList<Suit> _suits) {
        IdList<Suit> couleurs_;
        couleurs_ = GameTarotCommon.couleursAvecFigures(_mainPreneur, _suits);
        for(IdList<Suit> suits_: GameTarotCommon.couleursTrieesPlusCourtes(_mainPreneur, couleurs_)) {
            for(Suit couleur_: suits_) {
                HandTarot figures_ = _mainPreneur.charCardsBySuit(couleur_);
                figures_.trierParForceEnCours(couleur_);
                for(CardTarot carte_: figures_) {
                    addIfPossible(_ecart,carte_, _ecartables);
                }
            }
        }
    }

    private int nbCartesCouleursEcart(IdMap<Suit, HandTarot> _repartition) {
        int nbCartesCouleurs_ = 0;
        for (Suit couleur_ : Suit.couleursOrdinaires()) {
            nbCartesCouleurs_ += _repartition.getVal(couleur_).total();
        }
        return nbCartesCouleurs_;
    }

    private int nbCartesMaitressesEcart(HandTarot _carteAppelee, IdMap<Suit, HandTarot> _cartesMaitresses, IdList<Suit> _couleursAppelees, HandTarot _cartesPseudosMaitres) {
        int nbCartesMaitresses_ = 0;
        if (!_carteAppelee.estVide()) {
            for (Suit couleur_ : Suit.couleursOrdinaires()) {
                HandTarot main_ = _cartesMaitresses.getVal(couleur_);
                if (_couleursAppelees.containsObj(couleur_)) {
                    nbCartesMaitresses_ += _cartesPseudosMaitres.total();
                } else {
                    nbCartesMaitresses_ += main_.total();
                }
            }
        } else {
            for (HandTarot main_ : _cartesMaitresses.values()) {
                nbCartesMaitresses_ += main_.total();
            }
        }
        return nbCartesMaitresses_;
    }

    private HandTarot carteAppelee(HandTarot _carteAppelee) {
        HandTarot carteAppelee_ = new HandTarot();
        carteAppelee_.ajouterCartes(_carteAppelee);
        return carteAppelee_;
    }

    private HandTarot cartesPseudosMaitres(IdMap<Suit, HandTarot> _repartition, IdList<Suit> _couleursAppelees, HandTarot _carteAppelee) {
        HandTarot cartesPseudosMaitres_ = new HandTarot();
        for (Suit couleur_ : GameTarotCommon.intersectionCouleurs(_couleursAppelees,Suit.couleursOrdinaires())) {
            cartesPseudosMaitres_.ajouterCartes(GameTarotBid.cartesPseudoMaitresses(_repartition,
                    _carteAppelee, new HandTarot().couleurs()).getVal(couleur_));
        }
        return cartesPseudosMaitres_;
    }

    private IdList<Suit> couleursAppelees(HandTarot _carteAppelee) {
        IdList<Suit> couleursAppelees_ = new IdList<Suit>();
        for(CardTarot c: _carteAppelee) {
            couleursAppelees_.add(c.getId().getCouleur());
        }
        couleursAppelees_.removeDuplicates();
        return couleursAppelees_;
    }

    private void addIfPossible(HandTarot _discard, CardTarot _c, HandTarot _dis) {
        if (_discard.total() >= tailleChien) {
            return;
        }
        if (_discard.contient(_c)) {
            return;
        }
        if (!_dis.contient(_c)) {
            return;
        }
        _discard.ajouter(_c);
    }
    static HandTarot getCartesEcartables(int _nombreCartesChien,
                                         IdMap<Suit, HandTarot> _repartition) {
        HandTarot cartesEcartables_ = new HandTarot();
        int total_ = 0;
        int rois_ = 0;
        for (Suit couleur_ : Suit.couleursOrdinaires()) {
            HandTarot main_ = _repartition.getVal(couleur_);
            total_ += main_.total();
            for (CardTarot c: main_) {
                if (c.getId().getNomFigure() == CardChar.KING) {
                    rois_++;
                    continue;
                }
                cartesEcartables_.ajouter(c);
            }
        }
        if (total_ - rois_ >= _nombreCartesChien) {
//            complete(cartesEcartables_,_nombreCartesChien,_repartition);
            return cartesEcartables_;
        }
            /*
        S il y a
        moins de
        cartes de
        couleur
        autres que
        des rois que
        de cartes a
        ecarter alors
        il existe
        forcement au
        moins un
        atout dans la
        main
        */
        HandTarot atouts_ = _repartition.getVal(Suit.TRUMP);
            /*
            atouts est trie dans le sens
            decroissant des numeros
            */
        for (CardTarot c: atouts_) {
            if (CardTarot.eq(c, CardTarot.vingtEtUn()) || CardTarot.eq(c, CardTarot.petit())) {
                continue;
            }
            /*
                Pas de 21 d atout
                Pas de Petit d atout
                */
            cartesEcartables_.ajouter(c);
        }
//        complete(cartesEcartables_,_nombreCartesChien,_repartition);
        return cartesEcartables_;
    }
//    static void complete(HandTarot _cartesEcartables, int _nombreCartesChien, IdMap<Suit, HandTarot> _repartition) {
//        if (_cartesEcartables.total() < _nombreCartesChien) {
//            for (CardTarot c: HandTarot.reunion(_repartition)) {
//                if (!_cartesEcartables.contient(c)) {
//                    _cartesEcartables.ajouter(c);
//                }
//            }
//        }
//    }
    static IdList<Suit> couleursTotalEcartables(HandTarot _mainPreneur,
                                                  int _tailleChien, HandTarot _ecart, IdList<Suit> _couleursNonAppelees) {
        IdList<Suit> couleursEntieresEcartables_ = GameTarotCommon.couleursSansRoi(
                _mainPreneur, _couleursNonAppelees);
        couleursEntieresEcartables_ = GameTarotCommon.couleursNonAtoutNonVides(
                _mainPreneur, couleursEntieresEcartables_);
        int nombreCartesAjoutees_ = _tailleChien - _ecart.total();
        couleursEntieresEcartables_ = GameTarotCommon.couleursNonAtoutAyantNbCartesInfEg(
                _mainPreneur, couleursEntieresEcartables_,
                nombreCartesAjoutees_);
        return couleursEntieresEcartables_;
    }
    static HandTarot cartesNonMaitressesDebut(
            HandTarot _curHand,
            IdMap<Suit,HandTarot> _cartesMaitresses, HandTarot _carteAppelee,
            HandTarot _cartesPseudosMaitres) {
        HandTarot cartesNonMaitresses_ = new HandTarot();
        for (Suit couleur_ : Suit.couleursOrdinaires()) {
            HandTarot main_ = _cartesMaitresses.getVal(couleur_);
            HandTarot couleurTotale_ = HandTarot.couleurComplete(couleur_);
            couleurTotale_.trierParForceEnCours(couleur_);
            if (!_carteAppelee.estVide()) {
                if (_carteAppelee.premiereCarte().getId().getCouleur() == couleur_) {
                    feed(couleurTotale_, _curHand, _cartesPseudosMaitres, cartesNonMaitresses_);
                } else {
                    feed(couleurTotale_, _curHand, main_, cartesNonMaitresses_);
                }
            } else {
                feed(couleurTotale_, _curHand, main_, cartesNonMaitresses_);
            }
        }
        return cartesNonMaitresses_;
    }

    private static void feed(HandTarot _couleurTotale, HandTarot _curHand, HandTarot _main, HandTarot _cartesNonMaitresses) {
        for (CardTarot carte_ : GameTarotCommon.inter(_couleurTotale, _curHand)) {
            if (!_main.contient(carte_)) {
                _cartesNonMaitresses.ajouter(carte_);
            }
        }
    }

    static IdMap<Suit,HandTarot> cartesMaitressesDebutPartie(
            IdMap<Suit,HandTarot> _couleurs) {
        return GameTarotCommon.cartesMaitresses(_couleurs, new HandTarot().couleurs());
    }
}
