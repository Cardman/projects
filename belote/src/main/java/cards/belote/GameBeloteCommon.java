package cards.belote;

import cards.belote.comparators.*;
import cards.belote.enumerations.CardBelote;
import cards.consts.Order;
import cards.consts.Suit;
import code.util.CustList;
import code.util.EnumList;
import code.util.EnumMap;
import code.util.EqList;

public final class GameBeloteCommon {

    private GameBeloteCommon(){
    }
    static EnumList<Suit> couleursNonAtoutNonVides(
            HandBelote _main, EnumList<Suit> _couleurs) {
        return couleursNonAtoutAyantNbCartesSupEg(_main,_couleurs,1);
    }
    static EnumList<Suit> couleursNonAtoutAyantNbCartesSupEg(
            HandBelote _main, EnumList<Suit> _couleurs, int _nb) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        for (Suit couleur_ : _couleurs) {
            if (_main.tailleCouleur(couleur_) < _nb) {
                continue;
            }
            couleurs_.add(couleur_);
        }
        return couleurs_;
    }

    static EnumList<Suit> couleursNonAtoutNonVides(
            EnumMap<Suit,HandBelote> _main, EnumList<Suit> _couleurs) {
        return couleursNonAtoutAyantNbCartesSupEg(_main,_couleurs,1);
    }
    static EnumList<Suit> couleursNonAtoutAyantNbCartesSupEg(
            EnumMap<Suit,HandBelote> _main, EnumList<Suit> _couleurs, int _nb) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        for (Suit couleur_ : _couleurs) {
            if (_main.getVal(couleur_).tailleCouleur(couleur_) < _nb) {
                continue;
            }
            couleurs_.add(couleur_);
        }
        return couleurs_;
    }


    static EnumList<Suit> couleursAvecNbPointsInfEg(HandBelote _main,
                                                            BidBeloteSuit _contrat,
                                                            EnumList<Suit> _couleurs,
                                                            int _nb) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        for (Suit couleur_ : _couleurs) {
            HandBelote main_ = _main.couleur(_contrat,couleur_);
            if (main_.nombreCartesPoints(_contrat) > _nb) {
                continue;
            }
            couleurs_.add(couleur_);
        }
        return couleurs_;
    }

    static EnumList<Suit> couleursAvecPoints(HandBelote _main,
                                                     BidBeloteSuit _contrat,
                                                     EnumList<Suit> _couleurs) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        for (Suit couleur_ : _couleurs) {
            HandBelote main_ = _main.couleur(_contrat,couleur_);
            if (main_.nombreCartesPoints(_contrat) == 0) {
                continue;
            }
            couleurs_.add(couleur_);
        }
        return couleurs_;
    }

    static EnumList<Suit> couleursAvecPoints(EnumMap<Suit,HandBelote> _main,
                                                     BidBeloteSuit _contrat,
                                                     EnumList<Suit> _couleurs) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        for (Suit couleur_ : _couleurs) {
            HandBelote main_ = _main.getVal(couleur_);
            if (main_.nombreCartesPoints(_contrat) == 0) {
                continue;
            }
            couleurs_.add(couleur_);
        }
        return couleurs_;
    }

    static EnumList<Suit> couleursSansPoint(EnumMap<Suit,HandBelote> _main,
                                                    BidBeloteSuit _contrat,
                                                    EnumList<Suit> _couleurs) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        for (Suit couleur_ : _couleurs) {
            HandBelote main_ = _main.getVal(couleur_);
            if (main_.nombreCartesPoints(_contrat) != 0) {
                continue;
            }
            couleurs_.add(couleur_);
        }
        return couleurs_;
    }

    static EnumList<Suit> couleursAvecCarteMaitresse(HandBelote _main,
                                                             HandBelote _cartesJouees, BidBeloteSuit _contrat, EnumList<Suit> _couleurs) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        EnumMap<Suit,HandBelote> couleursMains_ = _main.couleurs(_contrat);
        EnumMap<Suit,HandBelote> cartesJouees_ = _cartesJouees.couleurs(_contrat);
        for (Suit couleur_ : _couleurs) {
            HandBelote cartesMaitresses_ = cartesMaitresses(couleursMains_,
                    cartesJouees_,_contrat).getVal(couleur_);
            if (!cartesMaitresses_.estVide()) {
                couleurs_.add(couleur_);
            }
        }
        return couleurs_;
    }


    static EnumList<Suit> couleursLesPlusCourtes(EnumMap<Suit,HandBelote> _main,
                                                         EnumList<Suit> _couleurs) {
        return couleursTrieesPlusCourtes(_main, _couleurs).first();
    }

    static CustList<EnumList<Suit>> couleursTrieesPlusCourtes(
            EnumMap<Suit,HandBelote> _main, EnumList<Suit> _couleurs) {
        return _couleurs.getGroupsSameCompare(new HandBeloteShortLengthComparator(_main));
    }

    static EnumList<Suit> couleursAvecLePlusPetitNbPoints(
            EnumMap<Suit,HandBelote> _main,
            BidBeloteSuit _contrat,
            EnumList<Suit> _couleurs) {
        return couleursTrieesPlusPetitNbPoints(_main,_contrat,_couleurs).first();
    }

    static CustList<EnumList<Suit>> couleursTrieesPlusPetitNbPoints(
            EnumMap<Suit,HandBelote> _main,
            BidBeloteSuit _contrat,
            EnumList<Suit> _couleurs) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>(_couleurs);
        return couleurs_.getGroupsSameCompare(new HandBeloteLowNbPtsCards(_main, _contrat));
    }

    static EnumList<Suit> couleursLesPlusLongues(
            EnumMap<Suit,HandBelote> _main,
            EnumList<Suit> _couleurs) {
        return couleursTrieesPlusLongues(_main, _couleurs).first();
    }

    static CustList<EnumList<Suit>> couleursTrieesPlusLongues(
            EnumMap<Suit,HandBelote> _main, EnumList<Suit> _couleurs) {
        return _couleurs.getGroupsSameCompare(new HandBeloteLongLengthComparator(_main));
    }
    static EnumList<Suit> couleursAvecLePlusGrandNbPoints(
            EnumMap<Suit,HandBelote> _main,
            BidBeloteSuit _contrat,
            EnumList<Suit> _couleurs) {
        return couleursTrieesPlusGrandNbPoints(_main,_contrat,_couleurs).first();
    }

    static CustList<EnumList<Suit>> couleursTrieesPlusGrandNbPoints(
            EnumMap<Suit,HandBelote> _main,
            BidBeloteSuit _contrat,
            EnumList<Suit> _couleurs) {
        return _couleurs.getGroupsSameCompare(new HandBeloteGreatNbPtsCards(_main, _contrat));
    }


    static EnumList<Suit> couleursLesPlusBasses(
            EnumMap<Suit,HandBelote> _main,
            BidBeloteSuit _contrat,
            EnumList<Suit> _couleurs) {
        return couleursTrieesPlusBasses(_main, _contrat,_couleurs).first();
    }

    static CustList<EnumList<Suit>> couleursTrieesPlusBasses(
            EnumMap<Suit,HandBelote> _main,BidBeloteSuit _contrat,
            EnumList<Suit> _couleurs) {
        return _couleurs.getGroupsSameCompare(new GameStrengthLowHandBeloteComparator(_main, _contrat));
    }


    static EnumList<Suit> couleursLesPlusHautes(EnumMap<Suit,HandBelote> _main,
                                                        BidBeloteSuit _contrat,
                                                        EnumList<Suit> _couleurs) {
        return couleursTrieesPlusHautes(_main, _contrat, _couleurs).first();
    }

    static CustList<EnumList<Suit>> couleursTrieesPlusHautes(
            EnumMap<Suit,HandBelote> _main, BidBeloteSuit _contrat,
            EnumList<Suit> _couleurs) {
        return _couleurs.getGroupsSameCompare(new GameStrengthGreatHandBeloteComparator(_main, _contrat));
    }


    static EnumList<Suit> couleursAvecLaPlusPetiteCarteBasse(EnumMap<Suit,HandBelote> _main,
                                                                     BidBeloteSuit _contrat,
                                                                     EnumList<Suit> _couleurs) {
        return couleursTrieesAvecLaPlusPetiteCarteBasse(_main, _contrat, _couleurs).first();
    }

    static CustList<EnumList<Suit>> couleursTrieesAvecLaPlusPetiteCarteBasse(
            EnumMap<Suit,HandBelote> _main, BidBeloteSuit _contrat, EnumList<Suit> _couleurs) {
        return _couleurs.getGroupsSameCompare(new GameStrengthLowLastHandBeloteComparator(_main, _contrat));
    }
    static EnumList<Suit> couleursAvecLaPlusGrandeFigure(EnumMap<Suit,HandBelote> _main,
                                                                 BidBeloteSuit _contrat,
                                                                 EnumList<Suit> _couleurs) {
        return couleursTrieesAvecLaPlusGrandeFigure(_main, _contrat, _couleurs).first();
    }

    static CustList<EnumList<Suit>> couleursTrieesAvecLaPlusGrandeFigure(
            EnumMap<Suit,HandBelote> _main, BidBeloteSuit _contrat, EnumList<Suit> _couleurs) {
        return _couleurs.getGroupsSameCompare(new GameStrengthGreatFirstHandBeloteComparator(_main, _contrat));
    }
    static EnumMap<Suit,HandBelote> cartesMaitresses(
            EnumMap<Suit,HandBelote> _couleurs,
            EnumMap<Suit,HandBelote> _cartesJouees,
            BidBeloteSuit _contrat) {
        Suit couleurAtout_ = _contrat.getCouleur();
        EnumMap<Suit,HandBelote> suits_=new EnumMap<Suit,HandBelote>();
        for (Suit couleur_: couleurs()) {
            Order ordre_;
            if(!_contrat.getCouleurDominante()) {
                ordre_ = _contrat.getOrdre();
            } else if(couleur_ == couleurAtout_) {
                ordre_ = Order.TRUMP;
            } else {
                ordre_ = Order.SUIT;
            }
            HandBelote couleurTotale_ = HandBelote.couleurComplete(couleur_, ordre_);
            HandBelote cartes_= hand(_couleurs,couleur_);
            HandBelote cartesJoueesOuPossedees_=new HandBelote(ordre_);
            cartesJoueesOuPossedees_.ajouterCartes(hand(_cartesJouees,couleur_));
            //C'est la reunion des cartes jouees dans le jeu et de celles du joueur
            cartesJoueesOuPossedees_.ajouterCartes(cartes_);
            cartesJoueesOuPossedees_.trierUnicolore(true);


            HandBelote cartesMaitresses_ = new HandBelote(ordre_);
            int nbPlayedOrOwnedCards_ = cartesJoueesOuPossedees_.total();
            for (byte c = CustList.FIRST_INDEX; c < nbPlayedOrOwnedCards_; c++) {
                if (!CardBelote.eq(cartesJoueesOuPossedees_.carte(c),
                        couleurTotale_.carte(c))) {
                    break;
                }
                if (!cartes_.contient(cartesJoueesOuPossedees_.carte(c))) {
                    continue;
                }
                cartesMaitresses_.ajouter(cartesJoueesOuPossedees_.carte(c));
            }
            int nbLeadingCards_ = cartesMaitresses_.total();
            if (nbLeadingCards_ + nbLeadingCards_ + _cartesJouees.getVal(couleur_).total() >= couleurTotale_
                    .total()) {
                for (CardBelote carte_ : cartes_) {
                    if (!cartesMaitresses_.contient(carte_)) {
                        cartesMaitresses_.ajouter(carte_);
                    }
                }
            }
            cartesMaitresses_.trierUnicolore(true);
            suits_.put(couleur_,cartesMaitresses_);
        }
        return suits_;
    }
    public static HandBelote hand(EnumMap<Suit, HandBelote> _mains, Suit _couleur) {
        return _mains.getVal(_couleur);
    }
    static EqList<HandBelote> suite(EnumMap<Suit,EqList<HandBelote>> _mains, Suit _couleur) {
        return _mains.getVal(_couleur);
    }
    static HandBelote hand(EnumMap<Suit,EqList<HandBelote>> _mains, Suit _couleur, int _indice2) {
        return _mains.getVal(_couleur).get(_indice2);
    }
    static EnumList<Suit> couleurs() {
        return Suit.couleursOrdinaires();
    }
}
