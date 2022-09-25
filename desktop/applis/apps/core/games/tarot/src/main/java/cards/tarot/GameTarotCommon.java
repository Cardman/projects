package cards.tarot;

import cards.consts.LeadingCards;
import cards.consts.Suit;
import cards.tarot.comparators.*;
import cards.tarot.enumerations.CardTarot;
import code.util.CustList;
import code.util.IdList;
import code.util.IdMap;

public final class GameTarotCommon {

    private GameTarotCommon(){
    }
    static IdList<Suit> couleursPLonguePHaute(HandTarot _main,
                                                        IdList<Suit> _couleurs) {
        IdList<Suit> couleurs_ = new IdList<Suit>();
        for (IdList<Suit> groupeCouleurs_ : couleursTrieesPlusLongues(_main,
                _couleurs)) {
            for (IdList<Suit> groupeCouleursBis_ : couleursTrieesPlusHautes(
                    _main, groupeCouleurs_)) {
                couleurs_.addAllElts(groupeCouleursBis_);
            }
        }
        return couleurs_;
    }

    static IdList<Suit> couleursPLongueMHaute(HandTarot _main,
                                                        IdList<Suit> _couleurs) {
        IdList<Suit> couleurs_ = new IdList<Suit>();
        for (IdList<Suit> groupeCouleurs_ : couleursTrieesPlusLongues(_main,
                _couleurs)) {
            for (IdList<Suit> groupeCouleursBis_ : couleursTrieesPlusBasses(
                    _main, groupeCouleurs_)) {
                couleurs_.addAllElts(groupeCouleursBis_);
            }
        }
        return couleurs_;
    }

    static IdList<Suit> couleursMLongueMHaute(HandTarot _main,
                                                        IdList<Suit> _couleurs) {
        IdList<Suit> couleurs_ = new IdList<Suit>();
        for (IdList<Suit> groupeCouleurs_ : couleursTrieesPlusCourtes(_main,
                _couleurs)) {
            for (IdList<Suit> groupeCouleursBis_ : couleursTrieesPlusBasses(
                    _main, groupeCouleurs_)) {
                couleurs_.addAllElts(groupeCouleursBis_);
            }
        }
        return couleurs_;
    }

    static IdList<Suit> couleursPHauteMLongue(HandTarot _main,
                                                        IdList<Suit> _couleurs) {
        IdList<Suit> couleurs_ = new IdList<Suit>();
        for (IdList<Suit> groupeCouleurs_ : couleursTrieesPlusHautes(_main,
                _couleurs)) {
            for (IdList<Suit> groupeCouleursBis_ : couleursTrieesPlusCourtes(
                    _main, groupeCouleurs_)) {
                couleurs_.addAllElts(groupeCouleursBis_);
            }
        }
        return couleurs_;
    }

    static IdList<Suit> couleursLesPlusCourtes(HandTarot _main,
                                                         IdList<Suit> _couleurs) {
        return couleursTrieesPlusCourtes(_main, _couleurs).first();
    }

    static CustList<IdList<Suit>> couleursTrieesPlusCourtes(
            HandTarot _main, IdList<Suit> _couleurs) {
        return _couleurs.getGroupsSameCompare(new HandTarotShortLengthComparator(_main));
    }

    static IdList<Suit> couleursAvecLePlusPetitNbFigures(
            HandTarot _main, IdList<Suit> _couleurs) {
        return couleursTrieesPlusGrandNbFigures(_main,_couleurs).last();
    }

    static IdList<Suit> couleursLesPlusLongues(HandTarot _main,
                                                         IdList<Suit> _couleurs) {
        return couleursTrieesPlusLongues(_main, _couleurs).first();
    }

    static CustList<IdList<Suit>> couleursTrieesPlusLongues(
            HandTarot _main, IdList<Suit> _couleurs) {
        return _couleurs.getGroupsSameCompare(new HandTarotLongLengthComparator(_main));
    }

    static IdList<Suit> couleursAvecLePlusGrandNbFigures(
            HandTarot _main, IdList<Suit> _couleurs) {
        return couleursTrieesPlusGrandNbFigures(_main,_couleurs).first();
    }

    static CustList<IdList<Suit>> couleursTrieesPlusGrandNbFigures(
            HandTarot _main, IdList<Suit> _couleurs) {
        return _couleurs.getGroupsSameCompare(new HandTarotCharLongLengthComparator(_main));
    }

    static IdList<Suit> couleursLesPlusBasses(HandTarot _main,
                                                        IdList<Suit> _couleurs) {
        return couleursTrieesPlusBasses(_main, _couleurs).first();
    }

    static CustList<IdList<Suit>> couleursTrieesPlusBasses(
            HandTarot _main, IdList<Suit> _couleurs) {
        return _couleurs.getGroupsSameCompare(new GameStrengthLowHandTarotComparator(_main));
    }

    static IdList<Suit> couleursAvecLaPlusPetiteCarteBasse(HandTarot _main,
                                                                     IdList<Suit> _couleurs) {
        return couleursTrieesAvecLaPlusPetiteCarteBasse(_main, _couleurs).first();
    }

    static CustList<IdList<Suit>> couleursTrieesAvecLaPlusPetiteCarteBasse(
            HandTarot _main, IdList<Suit> _couleurs) {
        return _couleurs.getGroupsSameCompare(new GameStrengthLowLastHandTarotComparator(_main));
    }

    static IdList<Suit> couleursLesPlusHautes(HandTarot _main,
                                                        IdList<Suit> _couleurs) {
        return couleursTrieesPlusHautes(_main, _couleurs).first();
    }

    static CustList<IdList<Suit>> couleursTrieesPlusHautes(
            HandTarot _main, IdList<Suit> _couleurs) {
        return _couleurs.getGroupsSameCompare(new GameStrengthGreatHandTarotComparator(_main));
    }

    static IdList<Suit> couleursNonAtoutNonVides(HandTarot _main, IdList<Suit> _couleurs) {
        return couleursNonAtoutAyantNbCartesSupEg(_main, _couleurs, 1);
    }
    static IdList<Suit> couleursAvecFigures(HandTarot _main,
                                              IdList<Suit> _couleurs) {
        IdList<Suit> couleurs_ = new IdList<Suit>();
        for (Suit couleur_ : _couleurs) {
            HandTarot main_ = _main.couleur(couleur_);
            if (main_.nombreDeFigures() == 0) {
                continue;
            }
            couleurs_.add(couleur_);
        }
        return couleurs_;
    }
    static IdList<Suit> couleursAvecCartesBasses(HandTarot _main,
                                                   IdList<Suit> _couleurs) {
        IdList<Suit> couleurs_ = new IdList<Suit>();
        for (Suit couleur_ : _couleurs) {
            HandTarot main_ = _main.couleur(couleur_);
            if (main_.cartesBasses(couleur_).estVide()) {
                continue;
            }
            couleurs_.add(couleur_);
        }
        return couleurs_;
    }static IdList<Suit> couleursSansCartes(HandTarot _main,
                                              HandTarot _cartesExclues,
                                              IdList<Suit> _couleurs) {
        IdList<Suit> couleurs_ = new IdList<Suit>();
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

    static IdList<Suit> couleursSansFigures(HandTarot _main,
                                              IdList<Suit> _couleurs) {
        IdList<Suit> couleurs_ = new IdList<Suit>();
        for (Suit couleur_ : _couleurs) {
            HandTarot main_ = _main.couleur(couleur_);
            if (main_.nombreDeFigures() > 0) {
                continue;
            }
            couleurs_.add(couleur_);
        }
        return couleurs_;
    }
    static IdList<Suit> couleursNonAtoutAyantNbCartesInfEg(
            HandTarot _main, IdList<Suit> _couleurs, int _nb) {
        IdList<Suit> couleurs_ = new IdList<Suit>();
        for (Suit couleur_ : _couleurs) {
            HandTarot main_ = _main.couleur(couleur_);
            if (main_.total() > _nb) {
                continue;
            }
            couleurs_.add(couleur_);
        }
        return couleurs_;
    }
    static int nombreDeCoupesFranches(IdMap<Suit,HandTarot> _repartition) {
        int nombre_ = 0;
        for (Suit couleur_ : Suit.couleursOrdinaires()) {
            if (_repartition.getVal(couleur_).estVide()) {
                nombre_++;
            }
        }
        return nombre_;
    }
    static IdList<Suit> couleursNonAtoutAyantNbCartesSupEg(
            HandTarot _main, IdList<Suit> _couleurs, int _nb) {
        IdList<Suit> couleurs_ = new IdList<Suit>();
        for (Suit couleur_ : _couleurs) {
            HandTarot main_ = _main.couleur(couleur_);
            if (main_.total() < _nb) {
                continue;
            }
            couleurs_.add(couleur_);
        }
        return couleurs_;
    }
    static IdList<Suit> couleursSansRoi(HandTarot _main,
                                           IdList<Suit> _couleurs) {
        IdList<Suit> couleurs_ = new IdList<Suit>();
        for (Suit couleur_ : _couleurs) {
            HandTarot main_ = _main.couleur(couleur_);
            if (main_.tailleRois() > 0) {
                continue;
            }
            couleurs_.add(couleur_);
        }
        return couleurs_;
    }
    static IdList<Suit> intersectionCouleurs(IdList<Suit> _couleurs1, IdList<Suit> _couleurs2) {
        IdList<Suit> couleurs_ = new IdList<Suit>();
        for (Suit couleur_ : _couleurs1) {
            if(!_couleurs2.containsObj(couleur_)) {
                continue;
            }
            couleurs_.add(couleur_);
        }
        return couleurs_;
    }
    static IdList<Suit> complementaireCouleurs(IdList<Suit> _couleurs, IdList<Suit> _couleursExclues) {
        IdList<Suit> couleurs_ = new IdList<Suit>();
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
    static IdMap<Suit,HandTarot> cartesMaitresses(
            IdMap<Suit,HandTarot> _couleurs, IdMap<Suit,HandTarot> _cartesJouees) {
        IdMap<Suit,HandTarot> suits_ = new IdMap<Suit,HandTarot>();
        HandTarot pileBase_ = HandTarot.pileBase();
        for (Suit i : Suit.couleursOrdinaires()) {
            HandTarot cartesMaitresses_ = cartesMaitresses(_couleurs, _cartesJouees, pileBase_, i);
            suits_.put(i,cartesMaitresses_);
        }
        return suits_;
    }

    static HandTarot cartesMaitresses(IdMap<Suit, HandTarot> _couleurs, IdMap<Suit, HandTarot> _cartesJouees, HandTarot _pileBase, Suit _i) {
        HandTarot cartes_ = _couleurs.getVal(_i);
        HandTarot couleurTotale_ = _pileBase.couleur(_i);
        HandTarot cartesJoueesOuPossedees_ = new HandTarot();
        HandTarot val_ = _cartesJouees.getVal(_i);
        HandTarot pla_;
        if (val_ == null) {
            pla_ = new HandTarot();
        } else {
            pla_ = val_;
        }
        cartesJoueesOuPossedees_.ajouterCartes(pla_);
        cartesJoueesOuPossedees_.ajouterCartes(cartes_);
        cartesJoueesOuPossedees_.trierParForceEnCours(_i);
        HandTarot retr_ = findLeading(cartes_, couleurTotale_, cartesJoueesOuPossedees_, pla_);
        retr_.trierParForceEnCours(_i);
        return retr_;
    }

    static HandTarot findLeading(HandTarot _cartes, HandTarot _couleurTotale, HandTarot _cartesJoueesOuPossedees, HandTarot _pla) {
        LeadingCards<CardTarot> calc_ = new LeadingCards<CardTarot>();
        calc_.leading(_cartesJoueesOuPossedees.getCards(), _cartes.getCards(), _pla.getCards(), _couleurTotale.getCards());
        HandTarot retr_ = new HandTarot();
        for (CardTarot c: calc_.getList()) {
            retr_.ajouter(c);
        }
        return retr_;
    }

    public static int atoutsAvecExcuse(IdMap<Suit, HandTarot> _couleurs) {
        return _couleurs.getVal(CardTarot.excuse().getId().getCouleur()).total() + _couleurs.getVal(Suit.TRUMP).total();
    }

}
