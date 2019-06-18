package cards.tarot;

import cards.consts.Suit;
import cards.tarot.comparators.*;
import cards.tarot.enumerations.CardTarot;
import code.util.CustList;
import code.util.EnumList;
import code.util.EnumMap;

public final class GameTarotCommon {

    private GameTarotCommon(){
    }
    static EnumList<Suit> couleursPLonguePHaute(HandTarot _main,
                                                        EnumList<Suit> _couleurs) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        for (EnumList<Suit> groupeCouleurs_ : couleursTrieesPlusLongues(_main,
                _couleurs)) {
            for (EnumList<Suit> groupeCouleursBis_ : couleursTrieesPlusHautes(
                    _main, groupeCouleurs_)) {
                couleurs_.addAllElts(groupeCouleursBis_);
            }
        }
        return couleurs_;
    }

    static EnumList<Suit> couleursPLongueMHaute(HandTarot _main,
                                                        EnumList<Suit> _couleurs) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        for (EnumList<Suit> groupeCouleurs_ : couleursTrieesPlusLongues(_main,
                _couleurs)) {
            for (EnumList<Suit> groupeCouleursBis_ : couleursTrieesPlusBasses(
                    _main, groupeCouleurs_)) {
                couleurs_.addAllElts(groupeCouleursBis_);
            }
        }
        return couleurs_;
    }

    static EnumList<Suit> couleursMLongueMHaute(HandTarot _main,
                                                        EnumList<Suit> _couleurs) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        for (EnumList<Suit> groupeCouleurs_ : couleursTrieesPlusCourtes(_main,
                _couleurs)) {
            for (EnumList<Suit> groupeCouleursBis_ : couleursTrieesPlusBasses(
                    _main, groupeCouleurs_)) {
                couleurs_.addAllElts(groupeCouleursBis_);
            }
        }
        return couleurs_;
    }

    static EnumList<Suit> couleursPHauteMLongue(HandTarot _main,
                                                        EnumList<Suit> _couleurs) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        for (EnumList<Suit> groupeCouleurs_ : couleursTrieesPlusHautes(_main,
                _couleurs)) {
            for (EnumList<Suit> groupeCouleursBis_ : couleursTrieesPlusCourtes(
                    _main, groupeCouleurs_)) {
                couleurs_.addAllElts(groupeCouleursBis_);
            }
        }
        return couleurs_;
    }

    static EnumList<Suit> couleursLesPlusCourtes(HandTarot _main,
                                                         EnumList<Suit> _couleurs) {
        return couleursTrieesPlusCourtes(_main, _couleurs).first();
    }

    static CustList<EnumList<Suit>> couleursTrieesPlusCourtes(
            HandTarot _main, EnumList<Suit> _couleurs) {
        return _couleurs.getGroupsSameCompare(new HandTarotShortLengthComparator(_main));
    }

    static EnumList<Suit> couleursAvecLePlusPetitNbFigures(
            HandTarot _main, EnumList<Suit> _couleurs) {
        return couleursTrieesPlusGrandNbFigures(_main,_couleurs).last();
    }

    static EnumList<Suit> couleursLesPlusLongues(HandTarot _main,
                                                         EnumList<Suit> _couleurs) {
        return couleursTrieesPlusLongues(_main, _couleurs).first();
    }

    static CustList<EnumList<Suit>> couleursTrieesPlusLongues(
            HandTarot _main, EnumList<Suit> _couleurs) {
        return _couleurs.getGroupsSameCompare(new HandTarotLongLengthComparator(_main));
    }

    static EnumList<Suit> couleursAvecLePlusGrandNbFigures(
            HandTarot _main, EnumList<Suit> _couleurs) {
        return couleursTrieesPlusGrandNbFigures(_main,_couleurs).first();
    }

    static CustList<EnumList<Suit>> couleursTrieesPlusGrandNbFigures(
            HandTarot _main, EnumList<Suit> _couleurs) {
        return _couleurs.getGroupsSameCompare(new HandTarotCharLongLengthComparator(_main));
    }

    static EnumList<Suit> couleursLesPlusBasses(HandTarot _main,
                                                        EnumList<Suit> _couleurs) {
        return couleursTrieesPlusBasses(_main, _couleurs).first();
    }

    static CustList<EnumList<Suit>> couleursTrieesPlusBasses(
            HandTarot _main, EnumList<Suit> _couleurs) {
        return _couleurs.getGroupsSameCompare(new GameStrengthLowHandTarotComparator(_main));
    }

    static EnumList<Suit> couleursAvecLaPlusPetiteCarteBasse(HandTarot _main,
                                                                     EnumList<Suit> _couleurs) {
        return couleursTrieesAvecLaPlusPetiteCarteBasse(_main, _couleurs).first();
    }

    static CustList<EnumList<Suit>> couleursTrieesAvecLaPlusPetiteCarteBasse(
            HandTarot _main, EnumList<Suit> _couleurs) {
        return _couleurs.getGroupsSameCompare(new GameStrengthLowLastHandTarotComparator(_main));
    }

    static EnumList<Suit> couleursLesPlusHautes(HandTarot _main,
                                                        EnumList<Suit> _couleurs) {
        return couleursTrieesPlusHautes(_main, _couleurs).first();
    }

    static CustList<EnumList<Suit>> couleursTrieesPlusHautes(
            HandTarot _main, EnumList<Suit> _couleurs) {
        return _couleurs.getGroupsSameCompare(new GameStrengthGreatHandTarotComparator(_main));
    }

    static EnumList<Suit> couleursNonAtoutNonVides(HandTarot _main, EnumList<Suit> _couleurs) {
        return couleursNonAtoutAyantNbCartesSupEg(_main, _couleurs, 1);
    }
    static EnumList<Suit> couleursAvecFigures(HandTarot _main,
                                              EnumList<Suit> _couleurs) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        for (Suit couleur_ : _couleurs) {
            HandTarot main_ = _main.couleur(couleur_);
            if (main_.nombreDeFigures() == 0) {
                continue;
            }
            couleurs_.add(couleur_);
        }
        return couleurs_;
    }
    static EnumList<Suit> couleursAvecCartesBasses(HandTarot _main,
                                                   EnumList<Suit> _couleurs) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        for (Suit couleur_ : _couleurs) {
            HandTarot main_ = _main.couleur(couleur_);
            if (main_.cartesBasses(couleur_).estVide()) {
                continue;
            }
            couleurs_.add(couleur_);
        }
        return couleurs_;
    }static EnumList<Suit> couleursSansCartes(HandTarot _main,
                                              HandTarot _cartesExclues,
                                              EnumList<Suit> _couleurs) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        for (Suit couleur_ : _couleurs) {
            boolean passerAcouleurSuivante_ = false;
            HandTarot main_ = _main.couleur(couleur_);
            for(CardTarot c: _cartesExclues) {
                if(main_.contient(c)) {
                    passerAcouleurSuivante_ = true;
                    break;
                }
            }
            if (passerAcouleurSuivante_) {
                continue;
            }
            couleurs_.add(couleur_);
        }
        return couleurs_;
    }

    static EnumList<Suit> couleursSansFigures(HandTarot _main,
                                              EnumList<Suit> _couleurs) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        for (Suit couleur_ : _couleurs) {
            HandTarot main_ = _main.couleur(couleur_);
            if (main_.nombreDeFigures() > 0) {
                continue;
            }
            couleurs_.add(couleur_);
        }
        return couleurs_;
    }
    static EnumList<Suit> couleursNonAtoutAyantNbCartesInfEg(
            HandTarot _main, EnumList<Suit> _couleurs, int _nb) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        for (Suit couleur_ : _couleurs) {
            HandTarot main_ = _main.couleur(couleur_);
            if (main_.total() > _nb) {
                continue;
            }
            couleurs_.add(couleur_);
        }
        return couleurs_;
    }
    static int nombreDeCoupesFranches(EnumMap<Suit,HandTarot> _repartition) {
        int nombre_ = 0;
        for (Suit couleur_ : Suit.couleursOrdinaires()) {
            if (_repartition.getVal(couleur_).estVide()) {
                nombre_++;
            }
        }
        return nombre_;
    }
    static EnumList<Suit> couleursNonAtoutAyantNbCartesSupEg(
            HandTarot _main, EnumList<Suit> _couleurs, int _nb) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        for (Suit couleur_ : _couleurs) {
            HandTarot main_ = _main.couleur(couleur_);
            if (main_.total() < _nb) {
                continue;
            }
            couleurs_.add(couleur_);
        }
        return couleurs_;
    }
    static EnumList<Suit> couleursSansRoi(HandTarot _main,
                                           EnumList<Suit> _couleurs) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        for (Suit couleur_ : _couleurs) {
            HandTarot main_ = _main.couleur(couleur_);
            if (main_.tailleRois() > 0) {
                continue;
            }
            couleurs_.add(couleur_);
        }
        return couleurs_;
    }
    static EnumList<Suit> intersectionCouleurs(EnumList<Suit> _couleurs1, EnumList<Suit> _couleurs2) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        for (Suit couleur_ : _couleurs1) {
            if(!_couleurs2.containsObj(couleur_)) {
                continue;
            }
            couleurs_.add(couleur_);
        }
        return couleurs_;
    }
    static EnumList<Suit> complementaireCouleurs(EnumList<Suit> _couleurs, EnumList<Suit> _couleursExclues) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        for (Suit couleur_ : _couleurs) {
            if(_couleursExclues.containsObj(couleur_)) {
                continue;
            }
            couleurs_.add(couleur_);
        }
        return couleurs_;
    }
    static HandTarot inter(HandTarot _one, HandTarot _two) {
        HandTarot o_ = new HandTarot();
        for (CardTarot c: _one) {
            if (_two.contient(c)) {
                o_.ajouter(c);
            }
        }
        return o_;
    }
    static EnumMap<Suit,HandTarot> cartesMaitresses(
            EnumMap<Suit,HandTarot> _couleurs, EnumMap<Suit,HandTarot> _cartesJouees) {
        EnumMap<Suit,HandTarot> suits_ = new EnumMap<Suit,HandTarot>();
        HandTarot pileBase_ = HandTarot.pileBase();
        for (Suit i : Suit.couleursOrdinaires()) {
            HandTarot cartes_ = _couleurs.getVal(i);
            HandTarot couleurTotale_ = pileBase_.couleur(i);
            HandTarot cartesJoueesOuPossedees_ = new HandTarot();
            cartesJoueesOuPossedees_.ajouterCartes(_cartesJouees.getVal(i));
            cartesJoueesOuPossedees_.ajouterCartes(cartes_);
            cartesJoueesOuPossedees_.trierParForceEnCours(i);
            HandTarot cartesMaitresses_ = new HandTarot();
            int nbPlayedOrOwnedCards_ = cartesJoueesOuPossedees_.total();
            for (byte c = CustList.FIRST_INDEX; c < nbPlayedOrOwnedCards_; c++) {
                if (!CardTarot.eq(cartesJoueesOuPossedees_.carte(c),
                        couleurTotale_.carte(c))) {
                    break;
                }
                if (!cartes_.contient(cartesJoueesOuPossedees_.carte(c))) {
                    continue;
                }
                cartesMaitresses_.ajouter(cartesJoueesOuPossedees_.carte(c));
            }
            int nbLeadingCards_ = cartesMaitresses_.total();
            if (nbLeadingCards_ + nbLeadingCards_ + _cartesJouees.getVal(i).total() >= couleurTotale_
                    .total()) {
                for (CardTarot carte_ : cartes_) {
                    if (!cartesMaitresses_.contient(carte_)) {
                        cartesMaitresses_.ajouter(carte_);
                    }
                }
            }
            cartesMaitresses_.trierParForceEnCours(i);
            suits_.put(i,cartesMaitresses_);
        }
        return suits_;
    }
    public static int atoutsAvecExcuse(EnumMap<Suit, HandTarot> _couleurs) {
        return _couleurs.getVal(CardTarot.excuse().couleur()).total() + _couleurs.getVal(Suit.TRUMP).total();
    }

}
