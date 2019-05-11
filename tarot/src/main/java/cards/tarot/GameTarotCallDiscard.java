package cards.tarot;

import cards.consts.CardChar;
import cards.consts.Suit;
import cards.tarot.comparators.CalledSuitComparator;
import cards.tarot.enumerations.CallingCard;
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

    HandTarot strategieAppel(HandTarot _mainPreneur) {
        byte joueurs_ = infosBid.getNombreDeJoueurs();
        boolean figure_ = infosBid.getRules().getRepartition().getAppel() == CallingCard.CHARACTER_CARD;
        EnumMap<Suit,HandTarot> repartition_ = _mainPreneur.couleurs();
        if (GameTarotBid.estUnJeuDeChelemSur(repartition_, new HandTarot().couleurs())) {
            HandTarot figuresPreneur_ = _mainPreneur.figures();
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
        HandTarot rois_ = _mainPreneur.figuresMain(CardChar.KING);
        HandTarot roisAppeler_ = infosBid.callableCards().figuresMain(CardChar.KING);
        if (rois_.total() < roisAppeler_.total()) {
            //0 <= rois.total() && rois.total() < roisAppeler.total()
            // ==> 0 < roisAppeler.total() ==> !roisAppeler.estVide()
            //il manque au moins un roi pour le preneur
            //il faut appeler un roi
            Suit couleurRoiAppele_;
            int nbAtoutLimite_ = _mainPreneur.total() / 2;
            int nbAtouts_ = _mainPreneur.couleur(Suit.TRUMP).total();
            EnumList<Suit> couleurs_;
            EnumList<Suit> couleursAppelables_ = GameTarotCommon.couleursNonAtoutNonVides(roisAppeler_,Suit.couleursOrdinaires());
            if (nbAtouts_ <= nbAtoutLimite_) {
                couleurs_ = GameTarotCommon.couleursNonAtoutAyantNbCartesInfEg(_mainPreneur,
                        couleursAppelables_, 3);
                EnumList<Suit> couleursSansRoi_ = GameTarotCommon.couleursSansCartes(_mainPreneur,
                        rois_,
                        couleurs_);
                if (!couleursSansRoi_.isEmpty()) {
                    // il existe une couleur ayant moins de trois cartes sans roi
                    EnumList<Suit> couleursAvecFigues_ = GameTarotCommon.couleursAvecFigures(
                            _mainPreneur, couleursSansRoi_);
                    if (!couleursAvecFigues_.isEmpty()) {
                        couleursAvecFigues_ = GameTarotCommon.couleursPLonguePHaute(
                                _mainPreneur, couleursAvecFigues_);
                        couleurRoiAppele_ = couleursAvecFigues_.first();
                    } else {
                        couleursSansRoi_ = GameTarotCommon.couleursPLongueMHaute(_mainPreneur,
                                couleursSansRoi_);
                        couleurRoiAppele_ = couleursSansRoi_.first();
                    }
                } else {
                    couleursSansRoi_ = GameTarotCommon.couleursSansCartes(_mainPreneur,
                            rois_,
                            couleursAppelables_);
                    couleursSansRoi_ =GameTarotCommon.couleursNonAtoutNonVides(_mainPreneur, couleursSansRoi_);
                    if (!couleursSansRoi_.isEmpty()) {
                        EnumList<Suit> couleursAvecFigues_ = GameTarotCommon.couleursAvecFigures(
                                _mainPreneur, couleursSansRoi_);
                        if (!couleursAvecFigues_.isEmpty()) {
                            couleursAvecFigues_ = GameTarotCommon.couleursPHauteMLongue(
                                    _mainPreneur, couleursAvecFigues_);
                            couleurRoiAppele_ = couleursAvecFigues_.first();
                        } else {
                            couleursSansRoi_ = GameTarotCommon.couleursMLongueMHaute(
                                    _mainPreneur, couleursSansRoi_);
                            couleurRoiAppele_ = couleursSansRoi_.first();
                        }
                    } else {
                        //couleurs avec roi ou couleurs vides et pas tous les rois => au moins une couleur vide
                        couleursSansRoi_ = GameTarotCommon.couleursNonAtoutAyantNbCartesInfEg(
                                _mainPreneur, couleursAppelables_, 0);
                        couleurRoiAppele_ = couleursSansRoi_.first();
                    }
                }
            } else {
                couleurs_ = GameTarotCommon.couleursNonAtoutNonVides(_mainPreneur, couleursAppelables_);
                EnumList<Suit> couleursSansRoi_ = GameTarotCommon.couleursSansCartes(_mainPreneur,
                        rois_,
                        couleurs_);
                if (!couleursSansRoi_.isEmpty()) {
                    couleursSansRoi_ = GameTarotCommon.couleursPLonguePHaute(_mainPreneur,
                            couleursSansRoi_);
                    couleurRoiAppele_ = couleursSansRoi_.first();
                } else {
                    //couleurs avec roi ou couleurs vides et pas tous les rois => au moins une couleur vide
                    couleursSansRoi_ = GameTarotCommon.couleursNonAtoutAyantNbCartesInfEg(
                            _mainPreneur, couleursAppelables_, 0);
                    couleurRoiAppele_ = couleursSansRoi_.first();
                }
            }
            return HandTarot.figureCouleur(couleurRoiAppele_, CardChar.KING);
        }
        Suit couleur_ = couleurAappeler(infosBid.callableCards(), _mainPreneur);
        HandTarot carteChoisie_ = valeurAappeler(joueurs_, figure_, couleur_, repartition_);
        if(!carteChoisie_.estVide()) {
            return carteChoisie_;
        }
        return infosBid.callableCards();

    }
    static Suit couleurAappeler(
            HandTarot _cartesAppeler, HandTarot _mainPreneur) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        couleurs_.add(CardTarot.EXCUSE.couleur());
        couleurs_.add(Suit.TRUMP);
        for (Suit couleur_ :Suit.couleursOrdinaires()) {
            couleurs_.add(couleur_);
        }
        couleurs_.sortElts(new CalledSuitComparator(_cartesAppeler,_mainPreneur));
        return couleurs_.first();
    }
    static HandTarot valeurAappeler(byte _nombreDeJoueurs, boolean _figure,
                                    Suit _couleurChoisie,
                                    EnumMap<Suit,HandTarot> _repartition) {
        EnumList<CardChar> ordreFigureAppeler_ = new EnumList<CardChar>();
        HandTarot main_ = HandTarot.reunion(_repartition);
        CardChar roi_ = CardChar.KING;
        ordreFigureAppeler_.add(roi_);
        CardChar dame_ = CardChar.QUEEN;
        ordreFigureAppeler_.add(dame_);
        CardChar cavalier_ = CardChar.KNIGHT;
        ordreFigureAppeler_.add(cavalier_);
        CardChar valet_ = CardChar.JACK;
        ordreFigureAppeler_.add(valet_);
        if (!_figure) {
            for(CardChar f: ordreFigureAppeler_) {
                HandTarot figures_ = main_.figuresMain(f);
                if(figures_.total() < HandTarot.charCards(f).total()) {
                    return HandTarot.figureCouleur(_couleurChoisie, f);
                }
            }
            HandTarot atoutAChoisir_ = new HandTarot();
            if (!_repartition.getVal(Suit.TRUMP).contient(CardTarot.vingtEtUn())) {
                atoutAChoisir_.ajouter(CardTarot.vingtEtUn());
            } else if (_repartition.getVal(CardTarot.EXCUSE.couleur()).estVide()) {
                atoutAChoisir_.ajouter(CardTarot.excuse());
            } else {
                atoutAChoisir_.ajouter(CardTarot.petit());
            }
            return atoutAChoisir_;
        }
        int nombreDeCoupes_ = GameTarotCommon.nombreDeCoupesFranches(_repartition);
        for(CardChar f: ordreFigureAppeler_) {
            HandTarot figures_ = main_.figuresMain(f);
            if(figures_.total() != HandTarot.charCards(f).total() - nombreDeCoupes_) {
                return HandTarot.figureCouleur(_couleurChoisie, f);
            }
        }
        if (_nombreDeJoueurs == 4) {
            HandTarot atoutAChoisir_ = new HandTarot();
            if (!_repartition.getVal(Suit.TRUMP).contient(CardTarot.vingtEtUn())) {
                atoutAChoisir_.ajouter(CardTarot.vingtEtUn());
                return atoutAChoisir_;
            }
            if (_repartition.getVal(CardTarot.EXCUSE.couleur()).estVide()) {
                atoutAChoisir_.ajouter(CardTarot.excuse());
                return atoutAChoisir_;
            }
            if (!_repartition.getVal(Suit.TRUMP).contient(CardTarot.petit())) {
                atoutAChoisir_.ajouter(CardTarot.petit());
                return atoutAChoisir_;
            }
        }
        for(CardChar f: ordreFigureAppeler_) {
            HandTarot figures_ = main_.figuresMain(f);
            if(figures_.total() < HandTarot.charCards(f).total()) {
                return HandTarot.figureCouleur(_couleurChoisie, f);
            }
        }
        return new HandTarot();
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
                HandTarot cartesNonMaitresses_ = cartesNonMaitressesDebut(
                        cartesMaitresses_, carteAppelee_, cartesPseudosMaitres_);
                for (CardTarot c: cartesNonMaitresses_) {
                    if (!mainPreneur_.contient(c)) {
                        continue;
                    }
                    ecart_.ajouter(c);
                }
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
                    HandTarot cartesNonMaitresses_ = cartesNonMaitressesDebut(
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
                    HandTarot cartesNonMaitresses_ = cartesNonMaitressesDebut(
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
                    HandTarot cartesNonMaitresses_ = cartesNonMaitressesDebut(
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
            HandTarot cartesNonMaitresses_ = cartesNonMaitressesDebut(
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
            EnumMap<Suit,HandTarot> _cartesMaitresses, HandTarot _carteAppelee,
            HandTarot _cartesPseudosMaitres) {
        HandTarot cartesNonMaitresses_ = new HandTarot();
        for (Suit couleur_ : Suit.couleursOrdinaires()) {
            HandTarot main_ = _cartesMaitresses.getVal(couleur_);
            if (_cartesMaitresses.getVal(couleur_).estVide()) {
                continue;
            }
            HandTarot couleurTotale_ = HandTarot.couleurComplete(couleur_);
            couleurTotale_.trierParForceEnCours(couleur_);
            if (!_carteAppelee.estVide()) {
                if (_carteAppelee.premiereCarte().couleur() == couleur_) {
                    for (CardTarot carte_ : couleurTotale_) {
                        if (!_cartesPseudosMaitres.contient(carte_)) {
                            cartesNonMaitresses_.ajouter(carte_);
                        }
                    }
                } else {
                    for (CardTarot carte_ : couleurTotale_) {
                        if (!main_.contient(carte_)) {
                            cartesNonMaitresses_.ajouter(carte_);
                        }
                    }
                }
            } else {
                for (CardTarot carte_ : couleurTotale_) {
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
