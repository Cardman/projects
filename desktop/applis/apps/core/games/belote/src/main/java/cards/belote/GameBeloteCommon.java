package cards.belote;

import cards.belote.comparators.*;
import cards.belote.enumerations.CardBelote;
import cards.consts.LeadingCards;
import cards.consts.Order;
import cards.consts.Suit;
import code.util.CustList;
import code.util.IdList;
import code.util.IdMap;

public final class GameBeloteCommon {

    private GameBeloteCommon(){
    }
    static IdList<Suit> couleursNonAtoutNonVides(
            HandBelote _main, IdList<Suit> _couleurs) {
        return couleursNonAtoutAyantNbCartesSupEg(_main,_couleurs,1);
    }
    static IdList<Suit> couleursNonAtoutAyantNbCartesSupEg(
            HandBelote _main, IdList<Suit> _couleurs, int _nb) {
        IdList<Suit> couleurs_ = new IdList<Suit>();
        for (Suit couleur_ : _couleurs) {
            if (_main.tailleCouleur(couleur_) < _nb) {
                continue;
            }
            couleurs_.add(couleur_);
        }
        return couleurs_;
    }

    static IdList<Suit> couleursNonAtoutNonVides(
            IdMap<Suit,HandBelote> _main, IdList<Suit> _couleurs) {
        return couleursNonAtoutAyantNbCartesSupEg(_main,_couleurs,1);
    }
    static IdList<Suit> couleursNonAtoutAyantNbCartesSupEg(
            IdMap<Suit,HandBelote> _main, IdList<Suit> _couleurs, int _nb) {
        IdList<Suit> couleurs_ = new IdList<Suit>();
        for (Suit couleur_ : _couleurs) {
            if (_main.getVal(couleur_).tailleCouleur(couleur_) < _nb) {
                continue;
            }
            couleurs_.add(couleur_);
        }
        return couleurs_;
    }


    static IdList<Suit> couleursAvecNbPointsInfEg(HandBelote _main,
                                                            BidBeloteSuit _contrat,
                                                            IdList<Suit> _couleurs,
                                                            int _nb) {
        IdList<Suit> couleurs_ = new IdList<Suit>();
        for (Suit couleur_ : _couleurs) {
            HandBelote main_ = _main.couleur(_contrat,couleur_);
            if (main_.nombreCartesPoints(_contrat) > _nb) {
                continue;
            }
            couleurs_.add(couleur_);
        }
        return couleurs_;
    }

    static IdList<Suit> couleursAvecPoints(HandBelote _main,
                                                     BidBeloteSuit _contrat,
                                                     IdList<Suit> _couleurs) {
        IdList<Suit> couleurs_ = new IdList<Suit>();
        for (Suit couleur_ : _couleurs) {
            HandBelote main_ = _main.couleur(_contrat,couleur_);
            if (main_.nombreCartesPoints(_contrat) == 0) {
                continue;
            }
            couleurs_.add(couleur_);
        }
        return couleurs_;
    }

    static IdList<Suit> couleursAvecPoints(IdMap<Suit,HandBelote> _main,
                                                     BidBeloteSuit _contrat,
                                                     IdList<Suit> _couleurs) {
        IdList<Suit> couleurs_ = new IdList<Suit>();
        for (Suit couleur_ : _couleurs) {
            HandBelote main_ = _main.getVal(couleur_);
            if (main_.nombreCartesPoints(_contrat) == 0) {
                continue;
            }
            couleurs_.add(couleur_);
        }
        return couleurs_;
    }

    static IdList<Suit> couleursSansPoint(IdMap<Suit,HandBelote> _main,
                                                    BidBeloteSuit _contrat,
                                                    IdList<Suit> _couleurs) {
        IdList<Suit> couleurs_ = new IdList<Suit>();
        for (Suit couleur_ : _couleurs) {
            HandBelote main_ = _main.getVal(couleur_);
            if (main_.nombreCartesPoints(_contrat) != 0) {
                continue;
            }
            couleurs_.add(couleur_);
        }
        return couleurs_;
    }

    static IdList<Suit> couleursAvecCarteMaitresse(HandBelote _main,
                                                             HandBelote _cartesJouees, BidBeloteSuit _contrat, IdList<Suit> _couleurs) {
        IdList<Suit> couleurs_ = new IdList<Suit>();
        IdMap<Suit,HandBelote> couleursMains_ = _main.couleurs(_contrat);
        IdMap<Suit,HandBelote> cartesJouees_ = _cartesJouees.couleurs(_contrat);
        for (Suit couleur_ : _couleurs) {
            HandBelote cartesMaitresses_ = cartesMaitresses(couleursMains_,
                    cartesJouees_,_contrat).getVal(couleur_);
            if (!cartesMaitresses_.estVide()) {
                couleurs_.add(couleur_);
            }
        }
        return couleurs_;
    }


    static IdList<Suit> couleursLesPlusCourtes(IdMap<Suit,HandBelote> _main,
                                                         IdList<Suit> _couleurs) {
        return couleursTrieesPlusCourtes(_main, _couleurs).first();
    }

    static CustList<IdList<Suit>> couleursTrieesPlusCourtes(
            IdMap<Suit,HandBelote> _main, IdList<Suit> _couleurs) {
        return _couleurs.getGroupsSameCompare(new HandBeloteShortLengthComparator(_main));
    }

    static IdList<Suit> couleursAvecLePlusPetitNbPoints(
            IdMap<Suit,HandBelote> _main,
            BidBeloteSuit _contrat,
            IdList<Suit> _couleurs) {
        return couleursTrieesPlusPetitNbPoints(_main,_contrat,_couleurs).first();
    }

    private static CustList<IdList<Suit>> couleursTrieesPlusPetitNbPoints(
            IdMap<Suit, HandBelote> _main,
            BidBeloteSuit _contrat,
            IdList<Suit> _couleurs) {
        IdList<Suit> couleurs_ = new IdList<Suit>(_couleurs);
        return couleurs_.getGroupsSameCompare(new HandBeloteLowNbPtsCards(_main, _contrat));
    }

    static IdList<Suit> couleursLesPlusLongues(
            IdMap<Suit,HandBelote> _main,
            IdList<Suit> _couleurs) {
        return couleursTrieesPlusLongues(_main, _couleurs).first();
    }

    private static CustList<IdList<Suit>> couleursTrieesPlusLongues(
            IdMap<Suit, HandBelote> _main, IdList<Suit> _couleurs) {
        return _couleurs.getGroupsSameCompare(new HandBeloteLongLengthComparator(_main));
    }
    static IdList<Suit> couleursAvecLePlusGrandNbPoints(
            IdMap<Suit,HandBelote> _main,
            BidBeloteSuit _contrat,
            IdList<Suit> _couleurs) {
        return couleursTrieesPlusGrandNbPoints(_main,_contrat,_couleurs).first();
    }

    private static CustList<IdList<Suit>> couleursTrieesPlusGrandNbPoints(
            IdMap<Suit, HandBelote> _main,
            BidBeloteSuit _contrat,
            IdList<Suit> _couleurs) {
        return _couleurs.getGroupsSameCompare(new HandBeloteGreatNbPtsCards(_main, _contrat));
    }


    static IdList<Suit> couleursLesPlusBasses(
            IdMap<Suit,HandBelote> _main,
            BidBeloteSuit _contrat,
            IdList<Suit> _couleurs) {
        return couleursTrieesPlusBasses(_main, _contrat,_couleurs).first();
    }

    private static CustList<IdList<Suit>> couleursTrieesPlusBasses(
            IdMap<Suit, HandBelote> _main, BidBeloteSuit _contrat,
            IdList<Suit> _couleurs) {
        return _couleurs.getGroupsSameCompare(new GameStrengthLowHandBeloteComparator(_main, _contrat));
    }


    static IdList<Suit> couleursLesPlusHautes(IdMap<Suit,HandBelote> _main,
                                                        BidBeloteSuit _contrat,
                                                        IdList<Suit> _couleurs) {
        return couleursTrieesPlusHautes(_main, _contrat, _couleurs).first();
    }

    private static CustList<IdList<Suit>> couleursTrieesPlusHautes(
            IdMap<Suit, HandBelote> _main, BidBeloteSuit _contrat,
            IdList<Suit> _couleurs) {
        return _couleurs.getGroupsSameCompare(new GameStrengthGreatHandBeloteComparator(_main, _contrat));
    }


    static IdList<Suit> couleursAvecLaPlusPetiteCarteBasse(IdMap<Suit,HandBelote> _main,
                                                                     BidBeloteSuit _contrat,
                                                                     IdList<Suit> _couleurs) {
        return couleursTrieesAvecLaPlusPetiteCarteBasse(_main, _contrat, _couleurs).first();
    }

    private static CustList<IdList<Suit>> couleursTrieesAvecLaPlusPetiteCarteBasse(
            IdMap<Suit, HandBelote> _main, BidBeloteSuit _contrat, IdList<Suit> _couleurs) {
        return _couleurs.getGroupsSameCompare(new GameStrengthLowLastHandBeloteComparator(_main, _contrat));
    }
    static IdList<Suit> couleursAvecLaPlusGrandeFigure(IdMap<Suit,HandBelote> _main,
                                                                 BidBeloteSuit _contrat,
                                                                 IdList<Suit> _couleurs) {
        return couleursTrieesAvecLaPlusGrandeFigure(_main, _contrat, _couleurs).first();
    }

    private static CustList<IdList<Suit>> couleursTrieesAvecLaPlusGrandeFigure(
            IdMap<Suit, HandBelote> _main, BidBeloteSuit _contrat, IdList<Suit> _couleurs) {
        return _couleurs.getGroupsSameCompare(new GameStrengthGreatFirstHandBeloteComparator(_main, _contrat));
    }
    static IdMap<Suit,HandBelote> cartesMaitresses(
            IdMap<Suit,HandBelote> _couleurs,
            IdMap<Suit,HandBelote> _cartesJouees,
            BidBeloteSuit _contrat) {
        IdMap<Suit,HandBelote> suits_=new IdMap<Suit,HandBelote>();
        for (Suit couleur_: couleurs()) {
            HandBelote cartesMaitresses_ = cartesMaitresses(_couleurs, _cartesJouees, _contrat, couleur_);
            suits_.put(couleur_,cartesMaitresses_);
        }
        return suits_;
    }

    private static HandBelote cartesMaitresses(IdMap<Suit, HandBelote> _couleurs, IdMap<Suit, HandBelote> _cartesJouees, BidBeloteSuit _contrat, Suit _couleur) {
        Suit couleurAtout_ = _contrat.getSuit();
        Order ordre_ = order(_contrat, couleurAtout_, _couleur);
        HandBelote couleurTotale_ = HandBelote.couleurComplete(_couleur, ordre_);
        HandBelote cartes_= hand(_couleurs, _couleur);
        HandBelote cartesJoueesOuPossedees_=new HandBelote(ordre_);
        cartesJoueesOuPossedees_.ajouterCartes(hand(_cartesJouees, _couleur));
        //C'est la reunion des cartes jouees dans le jeu et de celles du joueur
        cartesJoueesOuPossedees_.ajouterCartes(cartes_);
        cartesJoueesOuPossedees_.trierUnicolore(true);


        LeadingCards<CardBelote> calc_ = new LeadingCards<CardBelote>();
        calc_.leading(cartesJoueesOuPossedees_.getCards(),cartes_.getCards(),hand(_cartesJouees, _couleur).getCards(),couleurTotale_.getCards());
        HandBelote retr_ = new HandBelote(ordre_);
        for (CardBelote c: calc_.getList()) {
            retr_.ajouter(c);
        }
        retr_.trierUnicolore(true);
        return retr_;
    }

    private static Order order(BidBeloteSuit _contrat, Suit _couleurAtout, Suit _couleur) {
        return HandBelote.order(_contrat, _couleurAtout, _couleur);
    }

    public static HandBelote hand(IdMap<Suit, HandBelote> _mains, Suit _couleur) {
        return _mains.getVal(_couleur);
    }
    static CustList<HandBelote> suite(IdMap<Suit,CustList<HandBelote>> _mains, Suit _couleur) {
        return _mains.getVal(_couleur);
    }
    static HandBelote hand(IdMap<Suit,CustList<HandBelote>> _mains, Suit _couleur, int _indice2) {
        return _mains.getVal(_couleur).get(_indice2);
    }
    static IdList<Suit> couleurs() {
        return Suit.couleursOrdinaires();
    }
}
