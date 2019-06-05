package cards.tarot;

import cards.consts.CardChar;
import cards.consts.Suit;
import cards.tarot.comparators.CalledSuitComparator;
import cards.tarot.enumerations.CardTarot;
import code.util.CustList;
import code.util.EnumList;
import code.util.EnumMap;

public final class GameTarotCallDiscard {
    private GameTarotBid infosBid;
    private int tailleChien;

    public GameTarotCallDiscard(GameTarotBid _infosBid, int _tailleChien) {
        infosBid = _infosBid;
        tailleChien = _tailleChien;
    }

    HandTarot strategieAppel() {
        HandTarot curHand_ = infosBid.getCurrentHand();
        EnumMap<Suit,HandTarot> repartition_ = curHand_.couleurs();
        if (GameTarotBid.estUnJeuDeChelemSur(repartition_, new HandTarot().couleurs())) {
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
        HandTarot rois_ = curHand_.figuresMain(CardChar.KING);
        HandTarot roisAppeler_ = infosBid.callableCards().figuresMain(CardChar.KING);
        if (rois_.total() < roisAppeler_.total()) {
            //0 <= rois.total() && rois.total() < roisAppeler.total()
            // ==> 0 < roisAppeler.total() ==> !roisAppeler.estVide()
            //il manque au moins un roi pour le preneur
            //il faut appeler un roi
            Suit couleurRoiAppele_;
            int nbAtoutLimite_ = curHand_.total() / 2;
            int nbAtouts_ = curHand_.couleur(Suit.TRUMP).total();
            EnumList<Suit> couleurs_;
            EnumList<Suit> couleursAppelables_ = GameTarotCommon.couleursNonAtoutNonVides(roisAppeler_,Suit.couleursOrdinaires());
            if (nbAtouts_ <= nbAtoutLimite_) {
                couleurs_ = GameTarotCommon.couleursNonAtoutAyantNbCartesInfEg(curHand_,
                        couleursAppelables_, 3);
                EnumList<Suit> couleursSansRoi_ = GameTarotCommon.couleursSansCartes(curHand_,
                        rois_,
                        couleurs_);
                couleursSansRoi_ =GameTarotCommon.couleursNonAtoutNonVides(curHand_, couleursSansRoi_);
                if (!couleursSansRoi_.isEmpty()) {
                    // il existe une couleur ayant moins de trois cartes sans roi
                    EnumList<Suit> couleursAvecFigues_ = GameTarotCommon.couleursAvecFigures(
                            curHand_, couleursSansRoi_);
                    if (!couleursAvecFigues_.isEmpty()) {
                        couleursAvecFigues_ = GameTarotCommon.couleursPLonguePHaute(
                                curHand_, couleursAvecFigues_);
                        couleurRoiAppele_ = couleursAvecFigues_.first();
                    } else {
                        couleursSansRoi_ = GameTarotCommon.couleursPLongueMHaute(curHand_,
                                couleursSansRoi_);
                        couleurRoiAppele_ = couleursSansRoi_.first();
                    }
                } else {
                    couleursSansRoi_ = GameTarotCommon.couleursSansCartes(curHand_,
                            rois_,
                            couleursAppelables_);
                    couleursSansRoi_ =GameTarotCommon.couleursNonAtoutNonVides(curHand_, couleursSansRoi_);
                    if (!couleursSansRoi_.isEmpty()) {
                        EnumList<Suit> couleursAvecFigues_ = GameTarotCommon.couleursAvecFigures(
                                curHand_, couleursSansRoi_);
                        if (!couleursAvecFigues_.isEmpty()) {
                            couleursAvecFigues_ = GameTarotCommon.couleursPHauteMLongue(
                                    curHand_, couleursAvecFigues_);
                            couleurRoiAppele_ = couleursAvecFigues_.first();
                        } else {
                            couleursSansRoi_ = GameTarotCommon.couleursMLongueMHaute(
                                    curHand_, couleursSansRoi_);
                            couleurRoiAppele_ = couleursSansRoi_.first();
                        }
                    } else {
                        //couleurs avec roi ou couleurs vides et pas tous les rois => au moins une couleur vide
                        couleursSansRoi_ = GameTarotCommon.couleursNonAtoutAyantNbCartesInfEg(
                                curHand_, couleursAppelables_, 0);
                        couleurRoiAppele_ = couleursSansRoi_.first();
                    }
                }
            } else {
                couleurs_ = GameTarotCommon.couleursNonAtoutNonVides(curHand_, couleursAppelables_);
                EnumList<Suit> couleursSansRoi_ = GameTarotCommon.couleursSansCartes(curHand_,
                        rois_,
                        couleurs_);
                if (!couleursSansRoi_.isEmpty()) {
                    couleursSansRoi_ = GameTarotCommon.couleursPLonguePHaute(curHand_,
                            couleursSansRoi_);
                    couleurRoiAppele_ = couleursSansRoi_.first();
                } else {
                    //couleurs avec roi ou couleurs vides et pas tous les rois => au moins une couleur vide
                    couleursSansRoi_ = GameTarotCommon.couleursNonAtoutAyantNbCartesInfEg(
                            curHand_, couleursAppelables_, 0);
                    couleurRoiAppele_ = couleursSansRoi_.first();
                }
            }
            return HandTarot.figureCouleur(couleurRoiAppele_, CardChar.KING);
        }
        HandTarot carteChoisie_ = new HandTarot();
        carteChoisie_.ajouter(couleurAappeler(infosBid.callableCards(), curHand_));
        return carteChoisie_;

    }
    static CardTarot couleurAappeler(
            HandTarot _cartesAppeler, HandTarot _mainPreneur) {
        EnumList<CardTarot> couleurs_ = new EnumList<CardTarot>();
        couleurs_.addAllElts(_cartesAppeler.getCards());
        couleurs_.sortElts(new CalledSuitComparator(_cartesAppeler,_mainPreneur));
        return couleurs_.first();
    }

    HandTarot strategieEcart(boolean _carteAppeleeExistante,HandTarot _carteAppelee,
                             EnumList<Suit> _couleursNonAppelees) {
        HandTarot mainPreneur_ = infosBid.getCurrentHand();
        EnumMap<Suit,HandTarot> repartition_ = mainPreneur_.couleurs();
        byte nombreJoueurs_ = infosBid.getNombreDeJoueurs();
        HandTarot ecartables_ = getCartesEcartables(tailleChien, repartition_);
        EnumMap<Suit,HandTarot> repEcartables_ = ecartables_.couleurs();
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
            for (byte carte_ = CustList.FIRST_INDEX; carte_ < reste_; carte_++) {
                ecart_.ajouter(atoutsEcartables_.carte(nbAtoutsEcartables_ - 1
                        - carte_));
            }
            return ecart_;
        }
        if (ecartables_.total() == tailleChien) {
            return ecartables_;
        }
        if(GameTarotCommon.nombreDeCoupesFranches(repartition_) == 0) {
            if(!GameTarotBid.maitreAtoutPourChelem(repartition_,nombreJoueurs_)) {
                //si le preneur n'est pas maitre de l'atout au debut du jeu
                if(mainPreneur_.contient(CardTarot.petit())) {
                    EnumList<Suit> couleursEntieresEcartables_ = couleursTotalEcartables(
                            mainPreneur_, tailleChien, ecart_,
                            Suit.couleursOrdinaires());
                    if(!couleursEntieresEcartables_.isEmpty()) {
                        //Sauver le PETIT sur une coupe franche courte
                        couleursEntieresEcartables_ =
                                GameTarotCommon.couleursAvecFigures(mainPreneur_, couleursEntieresEcartables_);
                        couleursEntieresEcartables_ =
                                GameTarotCommon.intersectionCouleurs(couleursEntieresEcartables_, _couleursNonAppelees);
                        if(!couleursEntieresEcartables_.isEmpty()) {
                            //couleurs non appelees entierement ecartables avec figures
                            couleursEntieresEcartables_ = GameTarotCommon.couleursLesPlusCourtes(mainPreneur_, couleursEntieresEcartables_);
                            couleursEntieresEcartables_ = GameTarotCommon.couleursLesPlusHautes(mainPreneur_, couleursEntieresEcartables_);
                            ecart_.ajouterCartes(mainPreneur_.couleur(couleursEntieresEcartables_.first()));
                        } else {
                            couleursEntieresEcartables_ = couleursTotalEcartables(
                                    mainPreneur_, tailleChien, ecart_,
                                    Suit.couleursOrdinaires());
                            couleursEntieresEcartables_ =
                                    GameTarotCommon.intersectionCouleurs(couleursEntieresEcartables_, _couleursNonAppelees);
                            if(!couleursEntieresEcartables_.isEmpty()) {
                                //couleurs non appelees entierement ecartables
                                couleursEntieresEcartables_ = GameTarotCommon.couleursLesPlusCourtes(mainPreneur_, couleursEntieresEcartables_);
                                couleursEntieresEcartables_ = GameTarotCommon.couleursLesPlusBasses(mainPreneur_, couleursEntieresEcartables_);
                                ecart_.ajouterCartes(mainPreneur_.couleur(couleursEntieresEcartables_.first()));
                            } else {
                                couleursEntieresEcartables_ = couleursTotalEcartables(
                                        mainPreneur_, tailleChien, ecart_,
                                        Suit.couleursOrdinaires());
                                couleursEntieresEcartables_ = GameTarotCommon.couleursLesPlusCourtes(mainPreneur_, couleursEntieresEcartables_);
                                couleursEntieresEcartables_ = GameTarotCommon.couleursLesPlusHautes(mainPreneur_, couleursEntieresEcartables_);
                                ecart_.ajouterCartes(mainPreneur_.couleur(couleursEntieresEcartables_.first()));
                            }
                        }
                    }
                }
            }
        }
        EnumMap<Suit,HandTarot> cartesMaitresses_ = cartesMaitressesDebutPartie(repartition_);
        if(GameTarotBid.maitreAtoutPourChelem(repartition_,nombreJoueurs_)) {
            HandTarot carteAppelee_ = new HandTarot();
            HandTarot cartesPseudosMaitres_ = new HandTarot();
            int nbCartesMaitresses_ = 0;
            int nbCartesCouleurs_ = 0;
            if (_carteAppeleeExistante) {
                EnumList<Suit> couleursAppelees_ = new EnumList<Suit>();
                for(CardTarot c: _carteAppelee) {
                    if(couleursAppelees_.containsObj(c.couleur())) {
                        continue;
                    }
                    couleursAppelees_.add(c.couleur());
                }
                carteAppelee_.ajouterCartes(_carteAppelee);
                for (Suit couleur_ : couleursAppelees_) {
                    cartesPseudosMaitres_.ajouterCartes(GameTarotBid.cartesPseudoMaitresses(repartition_,
                            carteAppelee_, new HandTarot().couleurs()).getVal(couleur_));
                }
                for (Suit couleur_ : Suit.couleursOrdinaires()) {
                    HandTarot main_ = cartesMaitresses_.getVal(couleur_);
                    if (main_.estVide()) {
                        continue;
                    }
                    if (!couleursAppelees_.containsObj(couleur_)) {
                        nbCartesMaitresses_ += cartesPseudosMaitres_.total();
                    } else {
                        nbCartesMaitresses_ += main_.total();
                    }
                }
            } else {
                for (HandTarot main_ : cartesMaitresses_.values()) {
                    nbCartesMaitresses_ += main_.total();
                }
            }
            for (Suit couleur_ : Suit.couleursOrdinaires()) {
                nbCartesCouleurs_ += repartition_.getVal(couleur_).total();
            }
            if (nbCartesMaitresses_ + tailleChien >= nbCartesCouleurs_) {
                HandTarot cartesNonMaitresses_ = cartesNonMaitressesDebut(mainPreneur_,
                        cartesMaitresses_, carteAppelee_, cartesPseudosMaitres_);
                ecart_.ajouterCartes(cartesNonMaitresses_);
                if (ecart_.total() == tailleChien) {
                    return ecart_;
                }
                //cartesPseudosMaitres est suppose etre trie decroissant
                for (CardTarot carte_ : cartesPseudosMaitres_) {
                    if (ecart_.total() == tailleChien) {
                        return ecart_;
                    }
                    if (repEcartables_.getVal(carte_.couleur()).contient(carte_)) {
                        ecart_.ajouter(carte_);
                    }
                }
                for (HandTarot main_ : cartesMaitresses_.values()) {
                    for (CardTarot carte_ : main_) {
                        if (ecart_.total() == tailleChien) {
                            return ecart_;
                        }
                        if (repEcartables_.getVal(carte_.couleur()).contient(carte_)) {
                            ecart_.ajouter(carte_);
                        }
                    }
                }
            } else {
                EnumList<Suit> couleurs_ = GameTarotCommon.couleursSansRoi(mainPreneur_, Suit.couleursOrdinaires());
                couleurs_ = GameTarotCommon.couleursAvecFigures(mainPreneur_, couleurs_);
                for(EnumList<Suit> suits_: GameTarotCommon.couleursTrieesPlusCourtes(mainPreneur_, couleurs_)) {
                    HandTarot cartesNonMaitresses_ = cartesNonMaitressesDebut(mainPreneur_,
                            cartesMaitresses_, carteAppelee_, cartesPseudosMaitres_);
                    for(Suit couleur_: suits_) {
                        cartesNonMaitresses_ = cartesNonMaitresses_.charCardsBySuit(couleur_);
                        cartesNonMaitresses_.trierParForceEnCours(couleur_);
                        for(CardTarot carte_: cartesNonMaitresses_) {
                            if (!mainPreneur_.contient(carte_)) {
                                continue;
                            }
                            if (ecart_.total() == tailleChien) {
                                return ecart_;
                            }
                            ecart_.ajouter(carte_);
                        }
                    }

                }
                couleurs_ = GameTarotCommon.couleursSansRoi(mainPreneur_, Suit.couleursOrdinaires());
                for(EnumList<Suit> suits_: GameTarotCommon.couleursTrieesPlusCourtes(mainPreneur_, couleurs_)) {
                    HandTarot cartesNonMaitresses_ = cartesNonMaitressesDebut(mainPreneur_,
                            cartesMaitresses_, carteAppelee_, cartesPseudosMaitres_);
                    for(Suit couleur_: suits_) {
                        cartesNonMaitresses_ = cartesNonMaitresses_.cartesBasses(couleur_);
                        cartesNonMaitresses_.trierParForceEcart(couleur_);
                        for(CardTarot carte_: cartesNonMaitresses_) {
                            if (!mainPreneur_.contient(carte_)) {
                                continue;
                            }
                            if (ecart_.total() == tailleChien) {
                                return ecart_;
                            }
                            ecart_.ajouter(carte_);
                        }
                    }

                }
                couleurs_ = Suit.couleursOrdinaires();
                for(EnumList<Suit> suits_: GameTarotCommon.couleursTrieesPlusCourtes(mainPreneur_, couleurs_)) {
                    HandTarot cartesNonMaitresses_ = cartesNonMaitressesDebut(mainPreneur_,
                            cartesMaitresses_, carteAppelee_, cartesPseudosMaitres_);
                    for(Suit couleur_: suits_) {
                        cartesNonMaitresses_ = cartesNonMaitresses_.cartesBasses(couleur_);
                        cartesNonMaitresses_.trierParForceEcart(couleur_);
                        for(CardTarot carte_: cartesNonMaitresses_) {
                            if (!mainPreneur_.contient(carte_)) {
                                continue;
                            }
                            if (ecart_.total() == tailleChien) {
                                return ecart_;
                            }
                            ecart_.ajouter(carte_);
                        }
                    }

                }
                couleurs_ = GameTarotCommon.couleursAvecFigures(mainPreneur_, _couleursNonAppelees);
                for(EnumList<Suit> suits_: GameTarotCommon.couleursTrieesPlusCourtes(mainPreneur_, couleurs_)) {
                    for(Suit couleur_: suits_) {
                        HandTarot figures_ = mainPreneur_.charCardsBySuit(couleur_);
                        figures_.trierParForceEnCours(couleur_);
                        for(CardTarot carte_: figures_) {
                            if (!ecartables_.contient(carte_)) {
                                continue;
                            }
                            if (ecart_.total() == tailleChien) {
                                return ecart_;
                            }
                            ecart_.ajouter(carte_);
                        }
                    }
                }
                couleurs_ = GameTarotCommon.couleursAvecFigures(mainPreneur_, Suit.couleursOrdinaires());
                for(EnumList<Suit> suits_: GameTarotCommon.couleursTrieesPlusCourtes(mainPreneur_, couleurs_)) {
                    for(Suit couleur_: suits_) {
                        HandTarot figures_ = mainPreneur_.charCardsBySuit(couleur_);
                        figures_.trierParForceEnCours(couleur_);
                        for(CardTarot carte_: figures_) {
                            if (!ecartables_.contient(carte_)) {
                                continue;
                            }
                            if (ecart_.total() == tailleChien) {
                                return ecart_;
                            }
                            ecart_.ajouter(carte_);
                        }
                    }
                }
            }
            return ecart_;
        }
        EnumList<Suit> couleurs_ = GameTarotCommon.couleursSansRoi(mainPreneur_, Suit.couleursOrdinaires());
        couleurs_ = GameTarotCommon.couleursAvecFigures(mainPreneur_, couleurs_);
        for(EnumList<Suit> suits_: GameTarotCommon.couleursTrieesPlusCourtes(mainPreneur_, couleurs_)) {
            for(Suit couleur_: suits_) {
                HandTarot figures_ = mainPreneur_.charCardsBySuit(couleur_);
                figures_.trierParForceEnCours(couleur_);
                for(CardTarot carte_: figures_) {
                    if (ecart_.total() == tailleChien) {
                        return ecart_;
                    }
                    ecart_.ajouter(carte_);
                }
            }
        }
        couleurs_ = couleursTotalEcartables(mainPreneur_, tailleChien, ecart_, _couleursNonAppelees);
        for(EnumList<Suit> suits_: GameTarotCommon.couleursTrieesPlusCourtes(mainPreneur_, couleurs_)) {
            for(EnumList<Suit> couleurs2_: GameTarotCommon.couleursTrieesPlusHautes(mainPreneur_, suits_)) {
                for(Suit s_: couleurs2_) {
                    HandTarot couleur_ = mainPreneur_.couleur(s_);
                    for(CardTarot carte_: couleur_) {
                        if (ecart_.total() == tailleChien) {
                            return ecart_;
                        }
                        ecart_.ajouter(carte_);
                    }
                }
            }
        }
        couleurs_ = GameTarotCommon.couleursSansRoi(mainPreneur_, Suit.couleursOrdinaires());
        for(EnumList<Suit> suits_: GameTarotCommon.couleursTrieesPlusCourtes(mainPreneur_, couleurs_)) {
            for(Suit couleur_: suits_) {
                HandTarot cartesBasses_ = mainPreneur_.cartesBasses(couleur_);
                cartesBasses_.trierParForceEcart(couleur_);
                for(CardTarot carte_: cartesBasses_) {
                    if (ecart_.total() == tailleChien) {
                        return ecart_;
                    }
                    ecart_.ajouter(carte_);
                }
            }
        }
        couleurs_ = Suit.couleursOrdinaires();
        for(EnumList<Suit> suits_: GameTarotCommon.couleursTrieesPlusCourtes(mainPreneur_, couleurs_)) {
            HandTarot cartesNonMaitresses_ = cartesNonMaitressesDebut(mainPreneur_,
                    cartesMaitresses_, new HandTarot(), new HandTarot());
            for(Suit couleur_: suits_) {
                cartesNonMaitresses_ = cartesNonMaitresses_.charCardsBySuit(couleur_);
                cartesNonMaitresses_.trierParForceEcart(couleur_);
                for(CardTarot carte_: cartesNonMaitresses_) {
                    if (!mainPreneur_.contient(carte_)) {
                        continue;
                    }
                    if (ecart_.total() == tailleChien) {
                        return ecart_;
                    }
                    ecart_.ajouter(carte_);
                }
            }
        }
        couleurs_ = Suit.couleursOrdinaires();
        for(EnumList<Suit> suits_: GameTarotCommon.couleursTrieesPlusCourtes(mainPreneur_, couleurs_)) {
            for(Suit couleur_: suits_) {
                HandTarot cartesBasses_ = mainPreneur_.cartesBasses(couleur_);
                cartesBasses_.trierParForceEcart(couleur_);
                for(CardTarot carte_: cartesBasses_) {
                    if (ecart_.total() == tailleChien) {
                        return ecart_;
                    }
                    ecart_.ajouter(carte_);
                }
            }
        }
        return ecart_;
    }
    static HandTarot getCartesEcartables(int _nombreCartesChien,
                                         EnumMap<Suit, HandTarot> _repartition) {
        HandTarot cartesEcartables_ = new HandTarot();
        int atoutsExcuse_ = GameTarotCommon.atoutsAvecExcuse(_repartition);
        int total_ = atoutsExcuse_;
        int rois_ = 0;
        for (Suit couleur_ : Suit.couleursOrdinaires()) {
            HandTarot main_ = _repartition.getVal(couleur_);
            if (!main_.estVide()) {
                total_ += main_.total();
                for (CardTarot c: main_) {
                    if (c.getNomFigure() == CardChar.KING) {
                        rois_++;
                        continue;
                    }
                    cartesEcartables_.ajouter(c);
                }
            }
        }
        if (total_ - rois_ - atoutsExcuse_ < _nombreCartesChien) {
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
                if (CardTarot.eq(c, CardTarot.vingtEtUn())) {
                    /*
                    Pas de 21 d atout
                    dans la main
                    */
                    continue;
                }
                if (CardTarot.eq(c, CardTarot.petit())) {
                    /*
                    Pas de Petit d atout dans
                    la main
                    */
                    continue;
                }
                cartesEcartables_.ajouter(c);
            }
        }
        return cartesEcartables_;
    }
    static EnumList<Suit> couleursTotalEcartables(HandTarot _mainPreneur,
                                                  int _tailleChien, HandTarot _ecart, EnumList<Suit> _couleursNonAppelees) {
        EnumList<Suit> couleursEntieresEcartables_ = GameTarotCommon.couleursSansRoi(
                _mainPreneur, _couleursNonAppelees);
        couleursEntieresEcartables_ = GameTarotCommon.couleursNonAtoutNonVides(
                _mainPreneur, couleursEntieresEcartables_);
        byte nombreCartesAjoutees_ = (byte) (_tailleChien - _ecart.total());
        couleursEntieresEcartables_ = GameTarotCommon.couleursNonAtoutAyantNbCartesInfEg(
                _mainPreneur, couleursEntieresEcartables_,
                nombreCartesAjoutees_);
        return couleursEntieresEcartables_;
    }
    static HandTarot cartesNonMaitressesDebut(
            HandTarot _curHand,
            EnumMap<Suit,HandTarot> _cartesMaitresses, HandTarot _carteAppelee,
            HandTarot _cartesPseudosMaitres) {
        HandTarot cartesNonMaitresses_ = new HandTarot();
        for (Suit couleur_ : Suit.couleursOrdinaires()) {
            HandTarot main_ = _cartesMaitresses.getVal(couleur_);
            HandTarot couleurTotale_ = HandTarot.couleurComplete(couleur_);
            couleurTotale_.trierParForceEnCours(couleur_);
            if (!_carteAppelee.estVide()) {
                if (_carteAppelee.premiereCarte().couleur() == couleur_) {
                    for (CardTarot carte_ : GameTarotCommon.inter(couleurTotale_,_curHand)) {
                        if (!_cartesPseudosMaitres.contient(carte_)) {
                            cartesNonMaitresses_.ajouter(carte_);
                        }
                    }
                } else {
                    for (CardTarot carte_ : GameTarotCommon.inter(couleurTotale_,_curHand)) {
                        if (!main_.contient(carte_)) {
                            cartesNonMaitresses_.ajouter(carte_);
                        }
                    }
                }
            } else {
                for (CardTarot carte_ : GameTarotCommon.inter(couleurTotale_,_curHand)) {
                    if (!main_.contient(carte_)) {
                        cartesNonMaitresses_.ajouter(carte_);
                    }
                }
            }
        }
        return cartesNonMaitresses_;
    }
    static EnumMap<Suit,HandTarot> cartesMaitressesDebutPartie(
            EnumMap<Suit,HandTarot> _couleurs) {
        return GameTarotCommon.cartesMaitresses(_couleurs, new HandTarot().couleurs());
    }
}
